package com.bookverse.development.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class UltimatixElements {

  public static final Target USER = Target.the("Username field").locatedBy("//input[@id='form1']");
  public static final Target NEXT_BUTTON = Target.the("Next button").locatedBy("//button[@id='proceed-button']");
  public static final Target PASS_BUTTON = Target.the("Password button").locatedBy("//button[@id='password-btn']");
  public static final Target PASS = Target.the("Password field").locatedBy("//input[@id='password-login']");
  public static final Target LOGIN_BUTTON = Target.the("Login button").locatedBy("//button[@id='form-submit']");
  public static final Target TIMESHEET = Target.the("Timesheet link").locatedBy("//div[@class = 'col-sm-12 ColumnTrending']//a[. = 'Timesheet Entry']");
  public static final Target MODAL = Target.the("Modal from page").locatedBy("//div[@id = 'imgContainer2']");
  public static final Target OK_MODAL = Target.the("Button ok of modal from page").locatedBy("//div[. = 'Secured Access to Enterprise Applications']//following::div//input[@value = 'OK']");

  private UltimatixElements(){}
}
