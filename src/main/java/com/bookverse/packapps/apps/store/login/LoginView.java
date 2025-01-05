package com.bookverse.packapps.apps.store.login;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.bookverse.packapps.apps.store.signup.SignUpView;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Button;
import com.bookverse.packapps.utils.ui.factory.Form;

public class LoginView extends JDialog {

  private transient LoginService service = new LoginService();

  public LoginView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(400, 350);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Store");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {
    setLayout(new GridLayout(1, 1));
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("compraventa.png")).getImage());

    Map<String, JComponent> formFields = new LinkedHashMap<>();
    JTextField txtUser = new JTextField();

    JPasswordField txtPassword = new JPasswordField();
    txtPassword.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent evt) {
        Format.numbersAndText(evt.getKeyChar(), evt, String.valueOf(txtPassword.getPassword()), 20);
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          service.clickOnEnter(txtUser, txtPassword, LoginView.this);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }
    });
    new Form().setUserAndPassword(txtUser, txtPassword, formFields);

    JButton btnExit = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    btnExit.addActionListener(e -> Effects.fadeOut(this));

    JButton btnEnter = new Button().setText("Enter").setColor(Styles.TEXT_COLOR).build();
    btnEnter.addActionListener(e -> service.clickOnEnter(txtUser, txtPassword, this));

    JButton btnRegister = new Button().setText("Sign up").setColor(Styles.TEXT_COLOR).build();
    btnRegister.addActionListener(e -> {
      new SignUpView(this, true).start(this);
      setVisible(true);
    });

    JPanel loginPanel = new Form()
        .setTitle("Login")
        .setFormFields(formFields)
        .addButton(btnExit)
        .addButton(btnEnter)
        .setExtraText("Are you not registered?")
        .setExtraButton(btnRegister).build();

    add(loginPanel, BorderLayout.CENTER);
  }}