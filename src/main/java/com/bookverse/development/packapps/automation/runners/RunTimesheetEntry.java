package com.bookverse.development.packapps.automation.runners;

import static com.bookverse.development.packapps.automation.utils.ExceptionsMessages.TIMESHEET_ENTRY_ERROR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.bookverse.development.packapps.automation.exceptions.TimesheetNotRegistered;
import com.bookverse.development.packapps.automation.models.Chronus;
import com.bookverse.development.packapps.automation.questions.LogoutChronus;
import com.bookverse.development.packapps.automation.tasks.LoginChronus;
import com.bookverse.development.packapps.automation.tasks.RegisterHours;
import com.bookverse.development.packapps.automation.utils.Constants;
import com.bookverse.development.packapps.automation.utils.WebDriverFactory;
import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RunTimesheetEntry {

  Chronus chronus = (Chronus) Resources.getGeneralObject();

  @Before
  public void config() {
    setTheStage(Cast.whereEveryoneCan(
        BrowseTheWeb.with(WebDriverFactory.goToWeb(chronus.getUrl()))));
    theActorCalled(Constants.ACTOR);
  }

  @Test
  public void registerTimeSheet() {
    theActorInTheSpotlight().wasAbleTo(LoginChronus.withCredentials(chronus));
    theActorInTheSpotlight().attemptsTo(RegisterHours.inTimesheet(chronus));
    theActorInTheSpotlight().should(seeThat(LogoutChronus.successful()).orComplainWith(TimesheetNotRegistered.class, TIMESHEET_ENTRY_ERROR.getProperty()));

    Alerts.message("Test passed!", "Timesheet filled.");
  }

  @After
  public void close() {
    BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
  }
}