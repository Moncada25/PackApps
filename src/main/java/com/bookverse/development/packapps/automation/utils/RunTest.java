package com.bookverse.development.packapps.automation.utils;

import static com.bookverse.development.packapps.automation.utils.Paths.BOOKVERSE_DEVELOPMENT;
import static com.bookverse.development.packapps.utils.ArrayData.DATA_NEW_USER;

import com.bookverse.development.packapps.automation.models.BookverseData;
import com.bookverse.development.packapps.automation.runners.RunRegisterUser;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import org.junit.runner.JUnitCore;

public class RunTest {

  public static void registerNewUser() {

    if (Alerts.requestResponse(
        "Register new user with following data...\n\n"
            + "Name → " + DATA_NEW_USER.get("Name") + "\n"
            + "Last name → " + DATA_NEW_USER.get("LastName") + "\n"
            + "Phone → " + DATA_NEW_USER.get("Phone") + "\n"
            + "Occupation → " + DATA_NEW_USER.get("Occupation") + "\n"
            + "Address → " + DATA_NEW_USER.get("Address") + "\n"
            + "Username → " + DATA_NEW_USER.get("Username") + "\n"
            + "Password → " + DATA_NEW_USER.get("Password") + "\n"
            + "Email → " + DATA_NEW_USER.get("Email") + "\n"
            + "Gender → " + DATA_NEW_USER.get("Gender") + "\n\n"
            + "Do you want run test?", "Bookverse Test")) {

      Resources.generalObject = new BookverseData(DATA_NEW_USER.get("Username"),
          DATA_NEW_USER.get("Password"),
          BOOKVERSE_DEVELOPMENT.getProperty(),
          "");
      JUnitCore.runClasses(RunRegisterUser.class);
    }
  }
}