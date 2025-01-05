package com.bookverse.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;

public class LoginElements {

  public static final Target TXT_USER = InputField.withNameOrId("UserName");
  public static final Target TXT_PASSWORD = InputField.withNameOrId("UserPass");
  public static final Target BTN_LOGIN = Button.withText("Iniciar sesión");
  public static final Target BTN_SIGN_UP = Button.withText("Regístrate");

  private LoginElements() {
  }
}