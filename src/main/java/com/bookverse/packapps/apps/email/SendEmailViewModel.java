package com.bookverse.packapps.apps.email;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendEmailViewModel {
  private JTextArea text;
  private JTextField txtEmail;
  private JPasswordField txtPassword;
  private JRadioButton toDeveloper;
  private JRadioButton toOther;
}
