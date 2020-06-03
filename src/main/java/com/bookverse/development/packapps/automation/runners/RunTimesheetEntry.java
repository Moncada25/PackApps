package com.bookverse.development.packapps.automation.runners;

import static com.bookverse.development.packapps.automation.utils.ExceptionsMessages.TIMESHEET_ENTRY_ERROR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.bookverse.development.packapps.automation.exceptions.TimesheetNotRegistered;
import com.bookverse.development.packapps.automation.models.Ultimatix;
import com.bookverse.development.packapps.automation.questions.TheStatus;
import com.bookverse.development.packapps.automation.tasks.LoginUltimatix;
import com.bookverse.development.packapps.automation.tasks.RegisterHours;
import com.bookverse.development.packapps.automation.utils.DriverChrome;
import com.bookverse.development.packapps.core.Resources;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("unchecked")
@RunWith(SerenityRunner.class)
public class RunTimesheetEntry {

  Ultimatix ultimatix = (Ultimatix) Resources.generalObject;

  @Before
  public void config() {
    setTheStage(Cast.whereEveryoneCan(
        BrowseTheWeb.with(DriverChrome.web().inTheWebPage(ultimatix.getUrl()))));
    theActorCalled("PackAppsUser");
  }

  @Test
  public void registerTimeSheet() {
    theActorInTheSpotlight().wasAbleTo(LoginUltimatix.withCredentials(ultimatix));
    theActorInTheSpotlight().attemptsTo(RegisterHours.inTimesheetEntry(ultimatix.getHours()));
    theActorInTheSpotlight().should(seeThat(TheStatus.ofGeneralObject())
        .orComplainWith(TimesheetNotRegistered.class, TIMESHEET_ENTRY_ERROR.getProperty()));
  }

  @After
  public void close() {
    BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
  }
}