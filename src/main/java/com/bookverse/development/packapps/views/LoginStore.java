package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.BIG;
import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.core.Settings.SMALL;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;

import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginStore extends JDialog implements ActionListener {

  Resources resources = new Resources();

  private JButton btnLogin, btnExit, btnRegister;
  private JTextField txtUser;
  private JPasswordField txtPassword;

  public LoginStore(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(375, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Buy & Sell");
    Settings.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("compraventa.png")).getImage());

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(200, 225, 90, 30);

    JLabel alreadyRegister = resources
        .getLabel("<html><strong>Are you not registered?</strong></html>", MAIN_COLOR, this, SMALL);
    alreadyRegister.setBounds(110, 260, 300, 50);

    btnRegister = resources.getButton("Sign up", TEXT_COLOR, this, this);
    btnRegister.setBounds(135, 300, 100, 30);

    btnLogin = resources.getButton("Enter", TEXT_COLOR, this, this);
    btnLogin.setBounds(80, 225, 90, 30);

    JLabel title = resources
        .getLabel("<html><strong><em>Login store</em></strong></html>", MAIN_COLOR, this, BIG);
    title.setBounds(115, 5, 200, 40);

    JLabel user = resources
        .getLabel("<html><strong>Username</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    user.setBounds(145, 68, 100, 30);

    txtUser = new JTextField();
    txtUser.setBounds(110, 100, 150, 30);
    txtUser.setHorizontalAlignment(JTextField.CENTER);
    add(txtUser);

    txtUser.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtUserKeyPressed(e);
      }

      private void txtUserKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnEnterAP();
          setVisible(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent evt) {
        txtUsuarioKeyTyped(evt);
      }

      private void txtUsuarioKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, txtUser.getText(), 12);
      }
    });

    JLabel password = resources
        .getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    password.setBounds(145, 143, 120, 30);

    txtPassword = new JPasswordField();
    txtPassword.setBounds(110, 175, 150, 30);
    txtPassword.setHorizontalAlignment(JTextField.CENTER);
    add(txtPassword);

    txtPassword.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtPassKeyPressed(e);
      }

      private void txtPassKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnEnterAP();
          setVisible(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent evt) {
        txtCodKeyTyped(evt);
      }

      private void txtCodKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, String.valueOf(txtPassword.getPassword()), 20);
      }
    });
  }

  private void btnEnterAP() {

    if (txtUser.getText().trim().equals("") || String.valueOf(txtPassword.getPassword()).trim()
        .equals("")) {
      Alerts.inputSomethingText();
    } else {

      if (Database.searchUserRegister(txtUser.getText(),
          Settings.encrypt(String.valueOf(txtPassword.getPassword()), true))) {

        Database.recordLogin("Online", txtUser.getText());
        new HomeStore(this, true).start(this, txtUser.getText());

        txtPassword.setText("");
        txtUser.setText("");

      } else {
        Alerts.message("Verify!", "Incorrect data, try again.");
        txtPassword.setText("");
        txtUser.setText("");
        txtUser.requestFocus();
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnExit) {
      Settings.fadeOut(this);
    } else if (Settings.verifyConnection("Make sure you are connected to a network", true)) {

      if (e.getSource() == btnLogin) {
        btnEnterAP();
        setVisible(true);
      } else if (e.getSource() == btnRegister) {
        new SignUp(this, true).start(this);
        setVisible(true);
      }
    }
  }
}