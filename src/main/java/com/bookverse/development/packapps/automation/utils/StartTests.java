package com.bookverse.development.packapps.automation.utils;

import static com.bookverse.development.packapps.automation.utils.Constants.BOOKVERSE_PRODUCTION;

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
            + "Password → " + ArrayData.getDataUser("Pass") + "\n"
            + "Email → " + ArrayData.getDataUser("Email") + "\n"
            + "Gender → " + ArrayData.getDataUser("Gender") + "\n\n"
            + "Do you want run test?", "Bookverse Test")) {

      Resources.setGeneralObject(new Bookverse(ArrayData.getDataUser("Username"),
          ArrayData.getDataUser("Pass"),
          BOOKVERSE_PRODUCTION,
          ""));

      JUnitCore.runClasses(RunRegisterUser.class);
    }
  }
}