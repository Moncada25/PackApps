package com.bookverse.development.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ChronusTimesheet {

  public static final Target CLIENT_NAME = Target.the("Client name")
      .locatedBy("//h4[contains(., '{0}')]/following::select[contains(@id, 'ClInsert')][1]");
  public static final Target AREA_NAME = Target.the("Area name")
      .locatedBy("//h4[contains(., '{0}')]/following::select[contains(@id, 'ArInsert')][1]");
  public static final Target PROJECT_NAME = Target.the("Project name")
      .locatedBy("//h4[contains(., '{0}')]/following::select[contains(@id, 'PrInsert')][1]");
  public static final Target CATEGORY_NAME = Target.the("Category name")
      .locatedBy("//h4[contains(., '{0}')]/following::select[contains(@id, 'CatInsert')][1]");
  public static final Target DESCRIPTION = Target.the("Description field ")
      .locatedBy("//h4[contains(., '{0}')]/following::input[contains(@id, 'DescInsert')][1]");
  public static final Target NEXT_WEEK = Target.the("Description field ")
      .locatedBy("//input[contains(@id, 'ButtonNextWeek')]");
  public static final Target DAY = Target.the("Day field ")
      .locatedBy("//h4[contains(., '{0}')]/following::input[contains(@id, '{1}Insert')][1]");
  public static final Target REGISTER = Target.the("Day field ")
      .locatedBy("//h4[contains(., '{0}')]/following::input[contains(@id, 'btInsert')][1]");
  public static final Target LOGOUT = Target.the("Logout button")
      .locatedBy("//a[contains(@id, 'LoginStatus1')]");
  public static final Target CLOSE_WEEK = Target.the("Close week button")
      .locatedBy("//input[contains(@id, 'btnClosedWeek')]");
  public static final Target YES_CONTINUE = Target.the("Yes continue button")
      .locatedBy("//input[contains(@id, 'btnAddressSi')]");
  public static final Target ACCEPT_WEEK_CLOSED = Target.the("Accept week closed button")
      .locatedBy("//input[contains(@id, 'btnAceptar')]");

  private ChronusTimesheet() {
  }
}