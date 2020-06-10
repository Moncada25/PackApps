package com.bookverse.development.packapps.automation.utils;

import static com.bookverse.development.packapps.automation.utils.Paths.BOOKVERSE_DEVELOPMENT;

import com.bookverse.development.packapps.automation.models.Bookverse;
import com.bookverse.development.packapps.automation.runners.RunRegisterUser;
import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.ArrayData;
import org.junit.runner.JUnitCore;

public class StartTests {

  public static void startRegisterUser() {

    if (Alerts.requestResponse(
        "Register new user with following data...\n\n"
            + "Name → " + ArrayData.getDataUser("Name") + "\n"
            + "Last name → " + ArrayData.getDataUser("LastName") + "\n"
            + "Phone → " + ArrayData.getDataUser("Phone") + "\n"
            + "Occupation → " + ArrayData.getDataUser("Occupation") + "\n"
            + "Address → " + ArrayData.getDataUser("Address") + "\n"
            + "Username → " + ArrayData.getDataUser("Username") + "\n"
            + "Password → " + ArrayData.getDataUser("Password") + "\n"
            + "Email → " + ArrayData.getDataUser("Email") + "\n"
            + "Gender → " + ArrayData.getDataUser("Gender") + "\n\n"
            + "Do you want run test?", "Bookverse Test")) {

      Resources.generalObject = new Bookverse(ArrayData.getDataUser("Username"),
          ArrayData.getDataUser("Password"),
          BOOKVERSE_DEVELOPMENT.getProperty(),
          "");
      JUnitCore.runClasses(RunRegisterUser.class);
    }
  }
}