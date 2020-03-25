package com.bookverse.development.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class BookverseHomeElements {

  public static final Target USER_LOGGED = Target.the("Name of user logged")
      .locatedBy("//h4[contains(. , '{0}')]");
  public static final Target IMAGE = Target.the("Image of user logged")
      .locatedBy("//img[@alt = 'UserIcon']");
  public static final Target SEARCH_BOOK = Target.the("Search book button")
      .locatedBy(
          "//ul[@class = 'full-box list-unstyled text-right']//i[@class = 'zmdi zmdi-search']");
  public static final Target SEARCH_BOOK_FIELD = Target.the("Search book field")
      .locatedBy("//input[@name = 'busqueda_inicial_libro']");
  public static final Target SEARCH_BOOK_BUTTON = Target.the("Search book button")
      .locatedBy("//button[@class = 'btn btn-primary btn-raised btn-sm']");
  public static final Target ALERT_ACCEPT = Target.the("Alert button accept")
      .locatedBy("//button[@class = 'swal2-confirm swal2-styled']");
  public static final Target ALERT_CANCEL = Target.the("Alert button cancel")
      .locatedBy("//button[@class = 'swal2-cancel swal2-styled']");

  private BookverseHomeElements() {
  }
}
