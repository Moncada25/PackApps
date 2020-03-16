package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfig.SMALL;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.fadeIn;

import com.bookverse.development.packapps.core.AppConfig;
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

  public LoginStore() {
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(375, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Buy and Sell");
    fadeIn(this);
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
        .getLabel("<html><strong>Are you not registered?</strong></html>",
            MAIN_COLOR, this,
            SMALL);
    alreadyRegister.setBounds(112, 260, 300, 50);

    btnRegister = resources.getButton("Sign up", TEXT_COLOR, this, this);
    btnRegister.setBounds(130, 300, 110, 30);

    btnLogin = resources.getButton("Enter", TEXT_COLOR, this, this);
    btnLogin.setBounds(80, 225, 90, 30);

    JLabel tittle = resources
        .getLabel("<html><strong><em>Login store</em></strong></html>",
            MAIN_COLOR, this,
            BIG);
    tittle.setBounds(100, 5, 200, 40);

    JLabel user = resources
        .getLabel("<html><strong>Username</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    user.setBounds(145, 60, 100, 30);

    txtUser = new JTextField();
    txtUser.setBounds(110, 100, 150, 30);
    txtUser.setHorizontalAlignment(JTextField.CENTER);
    add(txtUser);

    txtUser.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtUsuarioKeyPressed(e);
      }

      private void txtUsuarioKeyPressed(KeyEvent e) {

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
    password.setBounds(145, 135, 120, 30);

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

      HomeStore homeStore = new HomeStore(this, true);

      if (Database.searchUserRegiter(txtUser.getText(),
          AppConfig.encrypt(String.valueOf(txtPassword.getPassword()), true))) {

        Database.recordLogin("Online", txtUser.getText());

        homeStore.setSize(620, 380);
        homeStore.setResizable(false);
        homeStore.setLocationRelativeTo(null);
        homeStore.setTitle("Welcome " + txtUser.getText() + "!");
        AppConfig.fadeIn(homeStore);
        setVisible(false);
        homeStore.setVisible(true);
        txtPassword.setText("");
        txtUser.setText("");

      } else {
        Alerts.message("Warning", "Incorrect data, try again.");
        txtPassword.setText("");
        txtUser.setText("");
        txtUser.requestFocus();
      }
    }
  }

  public void btnRegistrarAP() {

    Registrar reg = new Registrar(this, true);

    reg.setSize(460, 300);
    reg.setResizable(false);
    reg.setLocationRelativeTo(null);
    reg.setTitle("Añadir Usuario");
    fadeIn(reg);
    setVisible(false);
    reg.setVisible(true);
    txtPassword.setText("");
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnExit) {
      AppConfig.fadeOut(this);
    } else if (AppConfig.verifyConnection("Make sure you are connected to a network", true)) {

      if (e.getSource() == btnLogin) {
        btnEnterAP();
        setVisible(true);
      } else if (e.getSource() == btnRegister) {
        btnRegistrarAP();
        setVisible(true);
      }
    }
  }
}