package com.bookverse.development.packapps.apps.email;

import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.constants.AppConfig;
import com.bookverse.development.packapps.utils.other.Config;
import com.bookverse.development.packapps.utils.other.Format;

public class SendEmailService {

  private String receiver;

  public void sendEmailToOther(SendEmailViewModel model) {

    receiver = Alerts.inputText("Input receiver email");

    if (receiver == null || receiver.isEmpty()) {
      model.getToDeveloper().setSelected(true);
      return;
    }

    if (Format.isEmail(receiver)) {
      model.getToOther().setText(receiver);
    } else {
      receiver = Config.get(AppConfig.DEVELOPER_EMAIL.getProperty());
      model.getToDeveloper().setSelected(true);
      model.getToOther().setText("Other");
      Alerts.message("Verify!", "Invalid email, make sure it's Gmail.");
    }
  }

  public void sendEmailToDeveloper(SendEmailViewModel model) {
    model.getToOther().setText("Other");
    receiver = Config.get(AppConfig.DEVELOPER_EMAIL.getProperty());
  }

  private void sendEmail(SendEmailViewModel model) {

    String affair = "Email from PackApps";

    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.user", model.getTxtEmail().getText());
    props.put("mail.smtp.clave", String.valueOf(model.getTxtPassword().getPassword()));
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.port", "587");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
      message.setFrom(new InternetAddress(model.getTxtEmail().getText()));
      message.addRecipients(Message.RecipientType.TO, receiver);
      message.setSubject(affair);
      message.setText(model.getText().getText());
      Transport transport = session.getTransport("smtp");
      transport.connect(
          "smtp.gmail.com",
          model.getTxtEmail().getText(),
          String.valueOf(model.getTxtPassword().getPassword())
      );
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();

      if(model.getToDeveloper().isSelected()){
        Alerts.message(
            "Success!", "<center>Email sent</center> <br>" +
                "Feedback sent successfully, your opinion is very important to us."
        );
      }else{
        Alerts.message("Success!", "Your email was sent successfully.");
      }

    } catch (MessagingException me) {
      model.getToDeveloper().setSelected(true);
      model.getToOther().setText("Other");
      Alerts.error(me, "Send Email");
    }
  }

  public void clickOnSend(SendEmailViewModel model) {

    if (GeneralUtils.verifyConnection("Make sure you are connected to a network", true)) {

      if (model.getText().getText().trim().length() < 5 || model.getTxtEmail().getText().trim().length() < 10) {
        Alerts.message("Verify", "Fields too short");
        model.getText().requestFocus();
      } else {

        if (model.getTxtEmail().getText().endsWith("@gmail.com")) {
          sendEmail(model);

          model.getTxtEmail().setText("");
          model.getTxtPassword().setText("");
          model.getText().setText("");
        } else {
          Alerts.message("Verify!", "Invalid email, make sure it's Gmail.");
          model.getTxtEmail().requestFocus();
        }
      }
    }
  }
}
