package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.BIG;
import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.core.Settings.SMALL;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.models.DataSet;
import com.bookverse.development.packapps.utils.constants.Alerts;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
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
  private JRadioButton toDeveloper, toOther;
  private String receiver = DataSet.getDeveloperEmail();

  public Email(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(485, 480);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Send Email");
    Settings.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  public void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(resources.getImage("email.png")).getImage());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    btnSend = resources.getButton("Send", TEXT_COLOR, this, this);
    btnSend.setBounds(140, 400, 86, 30);

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(250, 400, 86, 30);

    JLabel title = resources
        .getLabel("<html><strong><em>Write email</em></strong></html>", MAIN_COLOR, this, BIG);
    title.setBounds(160, 10, 370, 30);

    JLabel username = resources
        .getLabel("<html><strong>Email</strong></html>", TEXT_COLOR, this, MEDIUM);
    username.setBounds(105, 60, 100, 50);

    JLabel password = resources
        .getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this, MEDIUM);
    password.setBounds(310, 60, 370, 50);

    required1 = resources.getLabel("*", MAIN_COLOR, this, MEDIUM);
    required1.setBounds(152, 74, 12, 12);
    required1.addMouseListener(this);

    required2 = resources.getLabel("*", MAIN_COLOR, this, MEDIUM);
    required2.setBounds(389, 74, 12, 12);
    required2.addMouseListener(this);

    JLabel lblReceiver = resources
        .getLabel("<html><strong>Receiver: </strong></html>", MAIN_COLOR, this, SMALL);
    lblReceiver.setBounds(30, 135, 65, 30);
    add(lblReceiver);

    ButtonGroup buttonGroup = new ButtonGroup();

    toDeveloper = new JRadioButton("<html><strong>Developer</strong></html>");
    toDeveloper.setBounds(100, 137, 95, 30);
    toDeveloper.setForeground(TEXT_COLOR);
    toDeveloper.setFont(SMALL);
    toDeveloper.addMouseListener(this);
    add(toDeveloper);
    buttonGroup.add(toDeveloper);
    toDeveloper.setSelected(true);

    toOther = new JRadioButton("<html><strong>Other</strong></html>");
    toOther.setBounds(195, 137, 250, 30);
    toOther.setFont(SMALL);
    toOther.addMouseListener(this);
    toOther.setForeground(TEXT_COLOR);
    add(toOther);
    buttonGroup.add(toOther);

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
    message.setBounds(30, 165, 370, 30);

    text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    scroll.setBounds(30, 195, 420, 185);
    add(scroll);
  }

  private void sendMail(String sender, String password, String body) {

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

      if(toDeveloper.isSelected()){
        Alerts.message("Success!", "<center>Email sent</center> <br>"
            + "Feedback sent successfully, your opinion is very important to us.");
      }else{
        Alerts.message("Success!", "Your email was sent successfully.");
      }

      txtUser.setText("");
      this.password.setText("");
      text.setText("");

    } catch (MessagingException me) {
      Alerts.message("Error", "Email not sent");
    }
  }

  private void btnSendAP() {

    if (Settings.verifyConnection("Make sure you are connected to a network", true)) {

      if (text.getText().trim().length() < 5 || txtUser.getText().trim().length() < 10) {
        Alerts.message("Verify", "Fields too short");
        text.requestFocus();
      } else {

        if (txtUser.getText().endsWith("@gmail.com")) {
          sendMail(txtUser.getText(), String.valueOf(password.getPassword()), text.getText());
        } else {
          Alerts.message("Verify!", "Invalid email, make sure it's Gmail.");
          txtUser.requestFocus();
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnSend) {
      btnSendAP();
    } else if (e.getSource() == btnExit) {
      Settings.fadeOut(this);
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

    if (e.getSource() == toOther) {
      receiver = Alerts.inputText("Input receiver email");

      if (receiver == null || receiver.trim().equals("") || !receiver.contains("@") || !receiver
          .contains(".")) {
        receiver = DataSet.getDeveloperEmail();
        toDeveloper.setSelected(true);
        toOther.setText("<html><strong>Other</strong></html>");
        Alerts.message("Verify!", "Email invalid");
      } else {
        toOther.setText("<html><strong>" + receiver + "</strong></html>");
      }

    } else if (e.getSource() == toDeveloper) {
      toOther.setText("<html><strong>Other</strong></html>");
      receiver = DataSet.getDeveloperEmail();
    }
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