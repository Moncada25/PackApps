package com.bookverse.development.packapps.apps.store.signup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.ui.factory.Button;
import com.bookverse.development.packapps.utils.ui.factory.Form;

public class SignUpView extends JDialog {

  private transient SignUpService service = new SignUpService();
  private transient SignUpViewModel model = null;

  public SignUpView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JDialog parent) {
    setSize(450, 350);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Add User");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
    model.getTxtPassword().setText("");
  }

  private void createComponents() {
    setLayout(new GridLayout());
    setIconImage(new ImageIcon(Resources.getImage("añadir_usuario.png")).getImage());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    Map<String, JComponent> formFields = new LinkedHashMap<>();

    JTextField txtUser = new JTextField();

    JPasswordField txtPassword = new JPasswordField();
    txtPassword.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent evt) {
        Format.numbersAndText(evt.getKeyChar(), evt, String.valueOf(txtPassword.getPassword()), 20);
      }
    });
    new Form().setUserAndPassword(txtUser, txtPassword, formFields);

    JPasswordField txtCodManager = getjPasswordField();
    formFields.put("Manager Key", txtCodManager);

    JButton btnSignUp = new Button().setText("Save").setColor(Styles.TEXT_COLOR).build();
    btnSignUp.addActionListener(e -> service.clickOnSignUp(model));

    JButton btnReturn = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    btnReturn.addActionListener(e -> service.clickOnReturn(model));

    JPanel signUpPanel = new Form()
        .setTitle("Sign Up")
        .setFormFields(formFields)
        .addButton(btnReturn)
        .addButton(btnSignUp).build();
    add(signUpPanel, BorderLayout.CENTER);

    model = new SignUpViewModel(txtUser, btnSignUp, btnReturn, txtPassword, txtCodManager, this);
  }

  private JPasswordField getjPasswordField() {
    JPasswordField txtCodManager = new JPasswordField();
    txtCodManager.setHorizontalAlignment(SwingConstants.CENTER);
    txtCodManager.setPreferredSize(new Dimension(200, 30));
    txtCodManager.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          service.clickOnSignUp(model);
        }
      }

      @Override
      public void keyTyped(KeyEvent evt) {
        Format.numbersAndText(evt.getKeyChar(), evt, String.valueOf(txtCodManager.getPassword()), 20);
      }
    });
    return txtCodManager;
  }
}