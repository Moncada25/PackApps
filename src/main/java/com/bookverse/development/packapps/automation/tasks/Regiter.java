package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.TimesheetElements.HOURS_DOC;
import static com.bookverse.development.packapps.automation.userinterfaces.TimesheetElements.SUBMIT_HOURS;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixElements.OK_MODAL;
import static com.bookverse.development.packapps.automation.userinterfaces.UltimatixElements.TIMESHEET;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class Regiter implements Task {

  private String hours;

  public Regiter(String hours) {
    this.hours = hours;
  }

  public static Regiter hours(String hours){
    return Tasks.instrumented(Regiter.class, hours);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        WaitUntil.the(TIMESHEET, isVisible()),
        Click.on(TIMESHEET),
        WaitUntil.the(OK_MODAL, isVisible()),
        //Enter.theValue(hours).into(HOURS_DOC),
        Click.on(OK_MODAL),
        WaitUntil.the(HOURS_DOC, isVisible()));
    //Resources.generalObject = null;
  }
}
