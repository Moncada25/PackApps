package com.bookverse.packapps.apps.store.signup;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpViewModel {
  private JTextField txtUser;
  private JButton btnSignUp;
  private JButton btnReturn;
  private JPasswordField txtPassword;
  private JPasswordField txtCodManager;
  private JDialog parent;
}
