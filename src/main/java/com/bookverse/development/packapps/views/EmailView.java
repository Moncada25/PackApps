package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.utils.other.Config;
import com.bookverse.development.packapps.utils.ui.KeyBindingsUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;

import static com.bookverse.development.packapps.services.EmailService.clickOnSend;
import static com.bookverse.development.packapps.services.EmailService.receiver;

import static com.bookverse.development.packapps.utils.constants.AppConfig.DEVELOPER_EMAIL;
import static com.bookverse.development.packapps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.utils.constants.Styles.SMALL;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;

public class EmailView extends JDialog implements ActionListener, MouseListener {

  private JLabel required1, required2;
  private JButton btnSend, btnExit;
  private JTextArea text;
  private JTextField txtUser;
  private JPasswordField password;
  
  private JRadioButton toDeveloper, toOther;

  public EmailView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
    KeyBindingsUtil.addCopyPasteKeyBindings(text, null, null);
  }

  public void start(JFrame parent) {
    setSize(485, 480);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Send Email");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  public void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(Resources.getImage("email.png")).getImage());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    btnSend = Resources.getButton("Send", TEXT_COLOR, this, this);
    btnSend.setBounds(140, 400, 86, 30);

    btnExit = Resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(250, 400, 86, 30);

    JLabel title = Resources
        .getLabel("<html><strong><em>Write email</em></strong></html>", MAIN_COLOR, this, BIG);
    title.setBounds(160, 10, 370, 30);

    JLabel username = Resources
        .getLabel("<html><strong>Email</strong></html>", TEXT_COLOR, this, MEDIUM);
    username.setBounds(105, 60, 100, 50);

    JLabel password = Resources
        .getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this, MEDIUM);
    password.setBounds(310, 60, 370, 50);

    required1 = Resources.getLabel("*", MAIN_COLOR, this, MEDIUM);
    required1.setBounds(152, 74, 12, 12);
    required1.addMouseListener(this);

    required2 = Resources.getLabel("*", MAIN_COLOR, this, MEDIUM);
    required2.setBounds(389, 74, 12, 12);
    required2.addMouseListener(this);

    JLabel lblReceiver = Resources
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

    JLabel message = Resources
        .getLabel("<html><strong>Message</strong></html>", TEXT_COLOR, this, MEDIUM);
    message.setBounds(30, 165, 370, 30);

    text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    scroll.setBounds(30, 195, 420, 185);
    add(scroll);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnSend) {
      clickOnSend(text, txtUser, password, toDeveloper.isSelected());
    } else if (e.getSource() == btnExit) {
      Effects.fadeOut(this);
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

      if (receiver == null || receiver.trim().isEmpty() || !receiver.contains("@") || !receiver.contains(".")) {
        receiver = Config.get(DEVELOPER_EMAIL.getProperty());
        toDeveloper.setSelected(true);
        toOther.setText("<html><strong>Other</strong></html>");
        Alerts.message("Verify!", "Email invalid");
      } else {
        toOther.setText("<html><strong>" + receiver + "</strong></html>");
      }

    } else if (e.getSource() == toDeveloper) {
      toOther.setText("<html><strong>Other</strong></html>");
      receiver = Config.get(DEVELOPER_EMAIL.getProperty());
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