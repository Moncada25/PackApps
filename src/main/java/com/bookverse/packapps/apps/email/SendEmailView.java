package com.bookverse.packapps.apps.email;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.SwingConstants;
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
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.KeyBindingsUtil;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Button;
import com.bookverse.packapps.utils.ui.factory.Label;

public class SendEmailView extends JDialog {

  private transient SendEmailService service = new SendEmailService();
  private transient SendEmailViewModel model = null;

  public SendEmailView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
    KeyBindingsUtil.addCopyPasteKeyBindings(model.getText(), null, null);
    KeyBindingsUtil.addCopyPasteKeyBindings(model.getTxtEmail(), null, null);
    KeyBindingsUtil.addCopyPasteKeyBindings(model.getTxtPassword(), null, null);
  }

  public void start(JFrame parent) {
    setSize(500, 400);
    setResizable(true);
    setLocationRelativeTo(parent);
    setMinimumSize(new Dimension(500, 400));
    setTitle("Send Email");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  public void createComponents() {
    setLayout(new GridBagLayout());
    setIconImage(new ImageIcon(Resources.getImage("email.png")).getImage());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 20, 5, 20);

    JLabel title = new Label().setText("Send Email")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.BIG)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    add(title, gbc);

    JLabel lblEmail = new Label().setText("Email")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.WEST;
    add(lblEmail, gbc);

    JTextField txtEmail = new JTextField();
    txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.NONE;
    txtEmail.setPreferredSize(new Dimension(270, 30));
    txtEmail.setFont(Styles.SMALL);
    add(txtEmail, gbc);

    JLabel lblPassword = new Label().setText("Password")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.WEST;
    add(lblPassword, gbc);

    JPasswordField txtPassword = new JPasswordField();
    txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.fill = GridBagConstraints.NONE;
    txtPassword.setPreferredSize(new Dimension(270, 30));
    txtPassword.setFont(Styles.SMALL);
    add(txtPassword, gbc);

    JLabel lblReceiver = new Label().setText("Receiver")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    add(lblReceiver, gbc);

    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton toDeveloper = new JRadioButton("Developer");
    toDeveloper.setForeground(Styles.TEXT_COLOR);
    toDeveloper.setFont(Styles.SMALL);
    toDeveloper.addActionListener(e -> service.sendEmailToDeveloper(model));
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    add(toDeveloper, gbc);
    buttonGroup.add(toDeveloper);
    toDeveloper.setSelected(true);

    JRadioButton toOther = new JRadioButton("Other");
    toOther.setFont(Styles.SMALL);
    toOther.setForeground(Styles.TEXT_COLOR);
    toOther.addActionListener(e -> service.sendEmailToOther(model));
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    add(toOther, gbc);
    buttonGroup.add(toOther);

    JLabel message = new Label().setText("Message")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    add(message, gbc);

    JTextArea text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 2;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    add(scroll, gbc);

    JButton btnSend = new Button().setText("Send").setColor(Styles.TEXT_COLOR).build();
    gbc.gridx = 0;
    gbc.gridy = 10;
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.NONE;
    btnSend.addActionListener(e -> service.clickOnSend(model));
    btnSend.setPreferredSize(new Dimension(120, 30));
    add(btnSend, gbc);

    JButton btnExit = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    gbc.gridx = 1;
    gbc.gridy = 10;
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.NONE;
    btnExit.addActionListener(e -> Effects.fadeOut(this));
    btnExit.setPreferredSize(new Dimension(120, 30));
    add(btnExit, gbc);

    model = new SendEmailViewModel(text, txtEmail, txtPassword, toDeveloper, toOther);
  }
}