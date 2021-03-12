package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.ChronusLogin.LOGIN_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusLogin.PASS;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusLogin.STATUS_WEEK;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusLogin.USER;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.models.Chronus;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class LoginChronus implements Task {

  private Chronus chronus;

  public LoginChronus(Chronus chronus) {
    this.chronus = chronus;
  }

  public static LoginChronus withCredentials(Chronus chronus) {
    return Tasks.instrumented(LoginChronus.class, chronus);
  }

  @Step("Login Chronus")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        WaitUntil.the(USER, isVisible()),
        Enter.theValue(chronus.getUser()).into(USER),
        Enter.theValue(chronus.getPass()).into(PASS),
        Click.on(LOGIN_BUTTON),
        WaitUntil.the(STATUS_WEEK, isVisible()).forNoMoreThan(5).seconds()
    );
  }
}