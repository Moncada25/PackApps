package com.bookverse.development.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ChronusLogin {

  public static final Target USER = Target.the("Username field").locatedBy("//input[contains(@id, 'UserName')]");
  public static final Target PASS = Target.the("Password field")
      .locatedBy("//input[contains(@id, 'Password')]");
  public static final Target LOGIN_BUTTON = Target.the("Login button")
      .locatedBy("//input[contains(@id, 'LoginButton')]");
  public static final Target STATUS_WEEK = Target.the("Status week")
      .locatedBy("//span[contains(@id, 'LabelStatus')]");

  private ChronusLogin() {
  }
}