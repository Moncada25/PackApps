package com.bookverse.development.packapps.views.older;

import static com.bookverse.development.packapps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.utils.constants.Styles.SMALL;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;

import com.bookverse.development.packapps.utils.other.Crypto;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Effects;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.SwingConstants;

public class LoginStore extends JDialog implements ActionListener {

  private JButton btnLogin;
  private JButton btnExit;
  private JButton btnRegister;
  private JTextField txtUser;
  private JPasswordField txtPassword;

  public LoginStore(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(400, 350);
    setResizable(true);
    setLocationRelativeTo(parent);
    setTitle("Store");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {
    setLayout(new GridBagLayout());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("compraventa.png")).getImage());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 20, 10, 20); // Add padding between elements and side borders
    gbc.fill = GridBagConstraints.BOTH; // Make components fill the space
    gbc.weightx = 1.0; // Allow horizontal expansion
    gbc.weighty = 1.0; // Allow vertical expansion

    // Header
    JLabel title = Resources.getLabel("<html><strong><em>Login</em></strong></html>", MAIN_COLOR, this, BIG);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    add(title, gbc);

    // Center
    JLabel user = Resources.getLabel("<html><strong>Username</strong></html>", TEXT_COLOR, this, MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.WEST;
    add(user, gbc);

    txtUser = new JTextField();
    txtUser.setHorizontalAlignment(SwingConstants.CENTER);
    txtUser.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnEnterAP();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      @Override
      public void keyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, txtUser.getText(), 12);
      }
    });
    gbc.gridx = 1;
    gbc.gridy = 1;
    add(txtUser, gbc);

    JLabel password = Resources.getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this, MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(password, gbc);

    txtPassword = new JPasswordField();
    txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
    txtPassword.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnEnterAP();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      @Override
      public void keyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, String.valueOf(txtPassword.getPassword()), 20);
      }
    });
    gbc.gridx = 1;
    gbc.gridy = 2;
    add(txtPassword, gbc);

    btnExit = Resources.getButton("Return", MAIN_COLOR, this, this);
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(btnExit, gbc);

    btnLogin = Resources.getButton("Enter", TEXT_COLOR, this, this);
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    add(btnLogin, gbc);

    // Footer
    JLabel alreadyRegister = Resources.getLabel("<html><strong>Are you not registered?</strong></html>", MAIN_COLOR, this, SMALL);
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    add(alreadyRegister, gbc);

    btnRegister = Resources.getButton("Sign up", TEXT_COLOR, this, this);
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    add(btnRegister, gbc);
  }

  private void btnEnterAP() {
    if (txtUser.getText().trim().isEmpty() || String.valueOf(txtPassword.getPassword()).trim().isEmpty()) {
      Alerts.inputSomethingText();
    } else {
      if (OlderRepository.searchUserRegister(txtUser.getText(), Crypto.encrypt(String.valueOf(txtPassword.getPassword()), true))) {
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
      } else if (e.getSource() == btnRegister) {
        new SignUp(this, true).start(this);
      }
    }
  }
}