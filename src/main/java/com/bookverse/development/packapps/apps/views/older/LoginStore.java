package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.SMALL;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;

import com.bookverse.development.packapps.apps.utils.other.Crypto;
import com.bookverse.development.packapps.apps.utils.other.GeneralUtils;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginStore extends JDialog implements ActionListener {

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
    setTitle("Store");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("compraventa.png")).getImage());

    btnExit = Resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(200, 225, 90, 30);

    JLabel alreadyRegister = Resources
        .getLabel("<html><strong>Are you not registered?</strong></html>", MAIN_COLOR, this, SMALL);
    alreadyRegister.setBounds(110, 260, 300, 50);

    btnRegister = Resources.getButton("Sign up", TEXT_COLOR, this, this);
    btnRegister.setBounds(135, 300, 100, 30);

    btnLogin = Resources.getButton("Enter", TEXT_COLOR, this, this);
    btnLogin.setBounds(80, 225, 90, 30);

    JLabel title = Resources
        .getLabel("<html><strong><em>Login</em></strong></html>", MAIN_COLOR, this, BIG);
    title.setBounds(152, 5, 200, 40);

    JLabel user = Resources
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

    JLabel password = Resources
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

    if (txtUser.getText().trim().isEmpty() || String.valueOf(txtPassword.getPassword()).trim()
        .isEmpty()) {
      Alerts.inputSomethingText();
    } else {

      if (OlderRepository.searchUserRegister(txtUser.getText(),
          Crypto.encrypt(String.valueOf(txtPassword.getPassword()), true))) {

        OlderRepository.recordLogin("Online", txtUser.getText());
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
      Effects.fadeOut(this);
    } else if (GeneralUtils.verifyConnection("Make sure you are connected to a network", true)) {

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