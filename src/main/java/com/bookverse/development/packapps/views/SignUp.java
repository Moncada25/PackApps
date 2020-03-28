package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.BIG;
import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;
import static com.bookverse.development.packapps.utils.DatabaseConstants.USERS;

import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.AppConfigUtility;
import com.bookverse.development.packapps.utils.Format;
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

  Resources resources = new Resources();

  private JTextField txtUser;
  private JButton btnSignUp, btnReturn;
  private JPasswordField txtPassword, txtCodManager;

  public SignUp(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(resources.getImage("añadir_usuario.png")).getImage());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    btnSignUp = resources.getButton("Save", TEXT_COLOR, this, this);
    btnSignUp.setBounds(75, 215, 86, 30);

    btnReturn = resources.getButton("Return", MAIN_COLOR, this, this);
    btnReturn.setBounds(300, 215, 86, 30);

    JLabel title = resources
        .getLabel("<html><strong><em>Sign Up</em></strong></html>", MAIN_COLOR, this, BIG);
    title.setBounds(175, 5, 200, 40);

    JLabel lblUser = resources
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

    JLabel lblPassword = resources
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

    JLabel codManager = resources
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
    Settings.fadeIn(this);
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
    Settings.fadeOut(this);
  }

  private void btnSignUpAP() {

    if (String.valueOf(txtCodManager.getPassword())
        .equals(AppConfigUtility.STORE_MANAGER_KEY.getProperty())) {

      txtUser.setEnabled(true);
      txtPassword.setEnabled(true);
      txtUser.requestFocus();

      if (Format.verifyCredentials(txtUser.getText()) && Format
          .verifyCredentials(String.valueOf(txtPassword.getPassword()))) {

        if (!Database.userAlreadyExist(txtUser.getText())) {

          String[] data = {USERS, txtUser.getText(), Settings.encrypt(String.valueOf(
              txtPassword.getPassword()), true), "Offline"};

          Database.insertData(data);
          Alerts.message("Message", "Registered user!");

          txtUser.setText("");
          txtPassword.setText("");
          txtCodManager.setText("");
          Settings.fadeOut(this);
        } else {
          Alerts.message("Message", "User already exists, please try to login.");
          Settings.fadeOut(this);
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