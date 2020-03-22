package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixElements.LOGIN_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixElements.MODAL;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixElements.NEXT_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixElements.PASS;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixElements.PASS_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixElements.USER;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.models.UltimatixData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class Login implements Task {

  private UltimatixData ultimatixData;

  public Login(UltimatixData ultimatixData) {
    this.ultimatixData = ultimatixData;
  }

  public static Login withUltimatix(UltimatixData ultimatixData){
    return Tasks.instrumented(Login.class, ultimatixData);
  }

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