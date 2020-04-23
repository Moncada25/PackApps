package com.bookverse.development.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class UltimatixTimesheet {

  public static final Target DAY_ENTRY = Target.the("Day entry")
      .locatedBy("(//div[@class = 'calendarContainer']//div[@class = 'notFilledEffort'])[1]");
  public static final Target HOURS_TEST = Target.the("Hours test input")
      .locatedBy("//input[@id = 'effortAssign10']");
  public static final Target HOURS_DOC = Target.the("Hours documentation input")
      .locatedBy("//input[@id = 'effortAssign00']");
  public static final Target SUBMIT_HOURS = Target.the("Button submit")
      .locatedBy("//input[@value = 'Submit']");

  private UltimatixTimesheet() {
  }
}