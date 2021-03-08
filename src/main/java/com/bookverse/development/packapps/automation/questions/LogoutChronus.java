package com.bookverse.development.packapps.automation.questions;

import static com.bookverse.development.packapps.automation.userinterfaces.ChronusLogin.LOGIN_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusTimesheet.LOGOUT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class LogoutChronus implements Question<Boolean> {

  public static LogoutChronus successful() {
    return new LogoutChronus();
  }

  @Subject("Logout from Chronus")
  @Override
  public Boolean answeredBy(Actor actor) {

    actor.attemptsTo(
        Click.on(LOGOUT),
        WaitUntil.the(LOGIN_BUTTON, isVisible())
    );

    return LOGIN_BUTTON.resolveFor(actor).isVisible();
  }
}