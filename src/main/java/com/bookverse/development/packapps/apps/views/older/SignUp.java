package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.AppConfig.STORE_MANAGER_KEY;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.USERS;

import com.bookverse.development.packapps.apps.utils.other.Crypto;
import com.bookverse.development.packapps.apps.utils.other.Config;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.repositories.OlderRepository;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends JDialog implements ActionListener {

  

  private JTextField txtUser;
  private JButton btnSignUp, btnReturn;
  private JPasswordField txtPassword, txtCodManager;

  public SignUp(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(Resources.getImage("añadir_usuario.png")).getImage());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    btnSignUp = Resources.getButton("Save", TEXT_COLOR, this, this);
    btnSignUp.setBounds(75, 215, 86, 30);

    btnReturn = Resources.getButton("Return", MAIN_COLOR, this, this);
    btnReturn.setBounds(300, 215, 86, 30);

    JLabel title = Resources
        .getLabel("<html><strong><em>Sign Up</em></strong></html>", MAIN_COLOR, this, BIG);
    title.setBounds(175, 5, 200, 40);

    JLabel lblUser = Resources
        .getLabel("<html><strong>Username</strong></html>", TEXT_COLOR, this, MEDIUM);
    lblUser.setBounds(30, 60, 180, 30);

    txtUser = new JTextField();
    txtUser.setBounds(250, 65, 150, 30);
    txtUser.setHorizontalAlignment(JTextField.CENTER);
    add(txtUser);

    txtUser.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtUsuarioKeyTyped(evt);
      }

      private void txtUsuarioKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, txtUser.getText(), 12);
      }
    });

    JLabel lblPassword = Resources
        .getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this, MEDIUM);
    lblPassword.setBounds(30, 110, 120, 30);

    txtPassword = new JPasswordField();
    txtPassword.setHorizontalAlignment(JTextField.CENTER);
    txtPassword.setBounds(250, 115, 150, 30);
    add(txtPassword);

    txtPassword.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtCodKeyTyped(evt);
      }

      private void txtCodKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, String.valueOf(txtPassword.getPassword()), 20);
      }
    });

    JLabel codManager = Resources
        .getLabel("<html><strong>Manager Key</strong></html>", TEXT_COLOR, this, MEDIUM);
    codManager.setBounds(30, 160, 180, 30);

    txtCodManager = new JPasswordField();
    txtCodManager.setHorizontalAlignment(JTextField.CENTER);
    txtCodManager.setBounds(250, 165, 150, 30);
    add(txtCodManager);

    txtCodManager.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnSignUpAP();
        }
      }

      public void keyTyped(KeyEvent evt) {
        txtCodKeyTyped(evt);
      }

      private void txtCodKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, String.valueOf(txtCodManager.getPassword()), 20);
      }
    });
  }

  public void start(JDialog parent) {
    setSize(460, 300);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Add User");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
    txtPassword.setText("");
  }

  private void btnReturnAP() {
    txtUser.setText("");
    txtPassword.setText("");
    txtCodManager.setText("");
    txtUser.setEnabled(true);
    txtPassword.setEnabled(true);
    Effects.fadeOut(this);
  }

  private void btnSignUpAP() {

    if (String.valueOf(txtCodManager.getPassword())
        .equals(Config.get(STORE_MANAGER_KEY.getProperty()))) {

      txtUser.setEnabled(true);
      txtPassword.setEnabled(true);
      txtUser.requestFocus();

      if (Format.verifyCredentials(txtUser.getText()) && Format
          .verifyCredentials(String.valueOf(txtPassword.getPassword()))) {

        if (!OlderRepository.userAlreadyExist(txtUser.getText())) {

          String[] data = {USERS, txtUser.getText(), Crypto.encrypt(String.valueOf(
              txtPassword.getPassword()), true), "Offline"};

          OlderRepository.insertData(data);
          Alerts.message("Message", "Registered user!");

          txtUser.setText("");
          txtPassword.setText("");
          txtCodManager.setText("");
          Effects.fadeOut(this);
        } else {
          Alerts.message("Message", "User already exists, please try to login.");
          Effects.fadeOut(this);
        }

      } else {
        Alerts.message("Message", "The username and / or password are too weak, please try again.");
      }

    } else {
      Alerts.message("Message", "Wrong manager key, please try again.");
      txtUser.setEnabled(false);
      txtPassword.setEnabled(false);
      txtCodManager.setText("");
      txtCodManager.requestFocus();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnReturn) {
      btnReturnAP();
    } else if (e.getSource() == btnSignUp) {
      btnSignUpAP();
    }
  }
}