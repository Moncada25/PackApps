package com.bookverse.development.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class BookverseRegister {

  public static final Target NAME_FIELD = Target.the("Name of client")
      .locatedBy("//input[@name = 'nombre-reg']");
  public static final Target LAST_NAME_FIELD = Target.the("Last name of client")
      .locatedBy("//input[@name = 'apellido-reg']");
  public static final Target PHONE_FIELD = Target.the("Phone of client")
      .locatedBy("//input[@name = 'telefono-reg']");
  public static final Target OCCUPATION_LIST = Target.the("List of occupations of client")
      .locatedBy("//select[@name = 'categoria-up']");
  public static final Target ADDRESS_FIELD = Target.the("Address of client")
      .locatedBy("//textarea[@name = 'direccion-reg']");
  public static final Target USERNAME_FIELD = Target.the("Username of client")
      .locatedBy("//input[@name = 'usuario-reg']");
  public static final Target FIRST_PASSWORD_FIELD = Target.the("Password of client")
      .locatedBy("//input[@name = 'password1-reg']");
  public static final Target SECOND_PASSWORD_FIELD = Target.the("Confirm password of client")
      .locatedBy("//input[@name = 'password2-reg']");
  public static final Target EMAIL_FIELD = Target.the("Email of client")
      .locatedBy("//input[@name = 'email-reg']");
  public static final Target GENDER_CHECK = Target.the("Gender of client")
      .locatedBy(
          "//div[@class = 'form-group']//div[@class = 'radio radio-primary']/label[contains(. , '{0}')]");
  public static final Target REGISTER_SUBMIT = Target.the("Button submit and register")
      .locatedBy("//button[@class]");
  public static final Target ENTER_LOGIN = Target.the("Button go to login")
      .locatedBy("//input[@id = 'btnIngresar']//ancestor::a");

  private BookverseRegister() {
  }
}