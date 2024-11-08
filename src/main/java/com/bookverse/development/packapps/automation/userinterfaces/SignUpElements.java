package com.bookverse.development.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.InputField;
import net.serenitybdd.screenplay.ui.TextArea;

public class SignUpElements {

  public static final Target TXT_NAME = InputField.withNameOrId("nombre-reg");
  public static final Target TXT_LAST_NAME = InputField.withNameOrId("apellido-reg");
  public static final Target TXT_PHONE = InputField.withNameOrId("telefono-reg");
  public static final Target LIST_OCCUPATION = Target.the("List of occupations of client")
      .locatedBy("//select[@name = 'categoria-up']");
  public static final Target TXT_ADDRESS = TextArea.withNameOrId("direccion-reg");
  public static final Target TXT_USERNAME = InputField.withNameOrId("usuario-reg");
  public static final Target TXT_FIRST_PASSWORD = InputField.withNameOrId("password1-reg");
  public static final Target TXT_SECOND_PASSWORD = InputField.withNameOrId("password2-reg");
  public static final Target TXT_EMAIL = InputField.withNameOrId("email-reg");
  public static final Target CHK_GENDER = Target.the("Gender of client")
      .locatedBy("//div[@class = 'form-group']//div[@class = 'radio radio-primary']/label[contains(. , '{0}')]");
  public static final Target BTN_SIGN_UP = Target.the("Button submit and register")
      .locatedBy("//button[@class]");
  public static final Target BTN_LOGIN_BACK = Target.the("Button go to login")
      .locatedBy("//input[@id = 'btnIngresar']//ancestor::a");

  private SignUpElements() {
  }
}