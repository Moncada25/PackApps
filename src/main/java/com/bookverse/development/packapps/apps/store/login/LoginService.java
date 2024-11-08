package com.bookverse.development.packapps.apps.store.login;

import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import lombok.Data;
import com.bookverse.development.packapps.apps.store.home.HomeView;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.Crypto;
import com.bookverse.development.packapps.utils.ui.Alerts;

@Data
public class LoginService {

  public void clickOnEnter(JTextField txtUser, JPasswordField txtPassword, JDialog parent) {
    if (txtUser.getText().trim().isEmpty() || String.valueOf(txtPassword.getPassword()).trim().isEmpty()) {
      Alerts.inputSomethingText();
    } else {

      String password = Crypto.encrypt(String.valueOf(txtPassword.getPassword()), true);

      if (OlderRepository.searchStoreUser(txtUser.getText(), password)) {
        OlderRepository.recordLogin("Online", txtUser.getText());
        new HomeView(parent, true).start(parent, txtUser.getText());
        txtUser.setText("");
        txtUser.requestFocus();
        txtPassword.setText("");
        parent.setVisible(true);
      } else {
        Alerts.message("Verify!", "User or password incorrect.");
        txtPassword.setText("");
        txtUser.setText("");
        txtUser.requestFocus();
      }
    }
  }
}
