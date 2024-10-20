package com.bookverse.development.packapps.automation.utils;

import com.bookverse.development.packapps.automation.models.Bookverse;
import com.bookverse.development.packapps.automation.runners.RegisterUserTest;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import org.junit.runner.JUnitCore;

public class StartTests {

  public static void startRegisterUser() {

    Bookverse bookverse = SetUser.toRegister();

    if (Alerts.requestResponse(
        "Register new user with following data...\n\n"
            + "Name → " + bookverse.getName() + "\n"
            + "Last name → " + bookverse.getLastName() + "\n"
            + "Phone → " + bookverse.getPhone() + "\n"
            + "Occupation → " + bookverse.getOccupation() + "\n"
            + "Address → " + bookverse.getAddress() + "\n"
            + "Username → " + bookverse.getUsername() + "\n"
            + "Password → " + bookverse.getPassword() + "\n"
            + "Email → " + bookverse.getEmail() + "\n"
            + "Gender → " + bookverse.getGender() + "\n\n"
            + "Do you want run test?", "Bookverse Test")) {

      Resources.setObject(bookverse);

      JUnitCore.runClasses(RegisterUserTest.class);
    }
  }
}