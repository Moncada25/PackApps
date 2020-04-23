package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLogin.LOGIN_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLogin.MODAL;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLogin.NEXT_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLogin.PASS;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLogin.PASS_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLogin.USER;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.models.Ultimatix;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class LoginUltimatix implements Task {

  private Ultimatix ultimatix;

  public LoginUltimatix(Ultimatix ultimatix) {
    this.ultimatix = ultimatix;
  }

  public static LoginUltimatix withCredentials(Ultimatix ultimatix) {
    return Tasks.instrumented(LoginUltimatix.class, ultimatix);
  }

  @Step("Login Ultimatix")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        WaitUntil.the(USER, isVisible()),
        Enter.theValue(ultimatix.getUser()).into(USER),
        Click.on(NEXT_BUTTON),
        WaitUntil.the(PASS_BUTTON, isVisible()),
        Click.on(PASS_BUTTON),
        WaitUntil.the(MODAL, isNotVisible()),
        Enter.theValue(ultimatix.getPass()).into(PASS),
        Click.on(LOGIN_BUTTON));
  }
}