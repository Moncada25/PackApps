package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixTimesheet.HOURS_DOC;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLogin.OK_MODAL;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixLogin.TIMESHEET;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.core.Resources;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class RegisterHours implements Task {

  private String hours;

  public RegisterHours(String hours) {
    this.hours = hours;
  }

  public static RegisterHours inTimesheetEntry(String hours) {
    return Tasks.instrumented(RegisterHours.class, hours);
  }

  @Step("Register hours")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        WaitUntil.the(TIMESHEET, isVisible()),
        Click.on(TIMESHEET),
        WaitUntil.the(OK_MODAL, isVisible()),
        Click.on(OK_MODAL),
        WaitUntil.the(HOURS_DOC, isVisible()),
        Enter.theValue(hours).into(HOURS_DOC));

    Resources.generalObject = null;
  }
}