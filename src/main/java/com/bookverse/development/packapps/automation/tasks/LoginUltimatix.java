package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLoginElements.LOGIN_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLoginElements.MODAL;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLoginElements.NEXT_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLoginElements.PASS;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLoginElements.PASS_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLoginElements.USER;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.models.UltimatixData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class LoginUltimatix implements Task {

  private UltimatixData ultimatixData;

  public LoginUltimatix(UltimatixData ultimatixData) {
    this.ultimatixData = ultimatixData;
  }

  public static LoginUltimatix withCredentials(UltimatixData ultimatixData){
    return Tasks.instrumented(LoginUltimatix.class, ultimatixData);
  }

  @Step("Login Ultimatix")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        WaitUntil.the(USER, isVisible()),
        Enter.theValue(ultimatixData.getUser()).into(USER),
        Click.on(NEXT_BUTTON),
        WaitUntil.the(PASS_BUTTON, isVisible()),
        Click.on(PASS_BUTTON),
        WaitUntil.the(MODAL, isNotVisible()),
        Enter.theValue(ultimatixData.getPass()).into(PASS),
        Click.on(LOGIN_BUTTON));
  }
}