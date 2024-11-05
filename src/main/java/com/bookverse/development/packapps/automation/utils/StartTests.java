package com.bookverse.development.packapps.automation.utils;

import com.bookverse.development.packapps.automation.models.BookverseUser;
import com.bookverse.development.packapps.automation.runners.RegisterUserTest;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import org.junit.runner.JUnitCore;

public class StartTests {

  public static void startRegisterUser() {

    BookverseUser bookverseUser = UserData.toRegister();

    if (Alerts.requestResponse(
        "Register new user with following data...\n\n"
            + "Name → " + bookverseUser.name() + "\n"
            + "Last name → " + bookverseUser.lastName() + "\n"
            + "Phone → " + bookverseUser.phone() + "\n"
            + "Occupation → " + bookverseUser.occupation() + "\n"
            + "Address → " + bookverseUser.address() + "\n"
            + "Username → " + bookverseUser.username() + "\n"
            + "Password → " + bookverseUser.password() + "\n"
            + "Email → " + bookverseUser.email() + "\n"
            + "Gender → " + bookverseUser.gender() + "\n\n"
            + "Do you want run test?", "Bookverse Test")) {

      Resources.setObject(bookverseUser);

      JUnitCore.runClasses(RegisterUserTest.class);
    }
  }
}