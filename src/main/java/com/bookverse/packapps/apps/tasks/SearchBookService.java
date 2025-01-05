package com.bookverse.packapps.apps.tasks;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.runner.JUnitCore;
import com.bookverse.packapps.automation.models.BookverseUser;
import com.bookverse.packapps.automation.runners.SearchBookTest;
import com.bookverse.packapps.automation.utils.GeneralUtils;
import com.bookverse.packapps.utils.ui.Alerts;

public class SearchBookService {

  public void clickOnRun(JTextField txtUser, JPasswordField txtPassword, JComboBox<String> books) {

    if (txtUser.getText().length() >= 4 && String.valueOf(txtPassword.getPassword()).length() >= 4) {

      BookverseUser bookverseUser = new BookverseUser(
          txtUser.getText(),
          String.valueOf(txtPassword.getPassword()),
          String.valueOf(books.getSelectedItem())
      );

      GeneralUtils.setUser(bookverseUser);

      JUnitCore.runClasses(SearchBookTest.class);
    } else {
      Alerts.inputSomethingText();
      txtUser.requestFocus();
    }
  }
}
