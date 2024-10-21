package com.bookverse.development.packapps.services;

import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Alerts;

public final class EmailService {

  public static String receiver;

  private static void sendEmail(String sender, String password, String body, boolean toDeveloper) {

    String affair = "Email from PackApps";

    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.user", sender);
    props.put("mail.smtp.clave", password);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.port", "587");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
      message.setFrom(new InternetAddress(sender));
      message.addRecipients(Message.RecipientType.TO, receiver);
      message.setSubject(affair);
      message.setText(body);
      Transport transport = session.getTransport("smtp");
      transport.connect("smtp.gmail.com", sender, password);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();

      if(toDeveloper){
        Alerts.message(
            "Success!", "<center>Email sent</center> <br>" +
                "Feedback sent successfully, your opinion is very important to us."
        );
      }else{
        Alerts.message("Success!", "Your email was sent successfully.");
      }

    } catch (MessagingException me) {
      Alerts.message("Error", "Email not sent");
    }
  }

  public static void clickOnSend(JTextArea area, JTextField user, JPasswordField password, boolean toDeveloper) {

    if (GeneralUtils.verifyConnection("Make sure you are connected to a network", true)) {

      if (area.getText().trim().length() < 5 || user.getText().trim().length() < 10) {
        Alerts.message("Verify", "Fields too short");
        area.requestFocus();
      } else {

        if (user.getText().endsWith("@gmail.com")) {
          sendEmail(user.getText(), String.valueOf(password.getPassword()), area.getText(), toDeveloper);

          user.setText("");
          password.setText("");
          area.setText("");
        } else {
          Alerts.message("Verify!", "Invalid email, make sure it's Gmail.");
          user.requestFocus();
        }
      }
    }
  }

  private EmailService() {
  }
}
