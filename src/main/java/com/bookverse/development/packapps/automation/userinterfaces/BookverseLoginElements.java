package com.bookverse.development.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class BookverseLoginElements {

  public static final Target USERNAME_FIELD = Target.the("Username field in login")
      .locatedBy("//input[@id = 'UserName']");
  public static final Target PASSWORD_FIELD = Target.the("Password field in login")
      .locatedBy("//input[@id = 'UserPass']");
  public static final Target LOGIN_BUTTON = Target.the("Login button")
      .locatedBy("//input[@value = 'Iniciar sesión']");
  public static final Target REGISTER_BUTTON = Target.the("Register button")
      .locatedBy("//input[@id = 'btnRegistrarNuevo']");
  private BookverseLoginElements() {
  }
}
