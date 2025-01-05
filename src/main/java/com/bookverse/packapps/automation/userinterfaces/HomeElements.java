package com.bookverse.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class HomeElements {

  public static final Target IMG_USER_LOGGED = Target.the("Name of user logged")
      .locatedBy("//img[@alt = 'UserIcon']//following-sibling::h4");
  public static final Target IMAGE_AVATAR = Target.the("Image of user logged")
      .locatedBy("//img[@alt = 'UserIcon']");
  public static final Target BTN_SEARCH_BOOK = Target.the("Search book button")
      .locatedBy("//ul[@class = 'full-box list-unstyled text-right']//i[@class = 'zmdi zmdi-search']");
  public static final Target TXT_SEARCH_BOOK = Target.the("Search book field")
      .locatedBy("//input[@name = 'busqueda_inicial_libro']");
  public static final Target SEARCH_BOOK_BUTTON = Target.the("Search book button")
      .locatedBy("//button[@class = 'btn btn-primary btn-raised btn-sm']");
  public static final Target ALERT_ACCEPT = Target.the("Alert button accept")
      .locatedBy("//button[contains(text(), 'Aceptar') or contains(text(), 'OK')]");
  public static final Target ALERT_TITLE = Target.the("Alert button accept")
      .locatedBy("//h2[@class = 'swal2-title']");
  private HomeElements() {
  }
}