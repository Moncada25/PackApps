package com.bookverse.development.packapps.automation.questions;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHomeElements.USER_LOGGED;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

public class VerifyUser implements Question<Boolean> {

  private String user;

  public VerifyUser(String user) {
    this.user = user;
  }

  public static VerifyUser registered(String user) {
    return new VerifyUser(user);
  }

  @Subject("Verify that user #user is registered")
  @Override
  public Boolean answeredBy(Actor actor) {
    return USER_LOGGED.of(user).resolveFor(actor).isCurrentlyVisible();
  }
}
