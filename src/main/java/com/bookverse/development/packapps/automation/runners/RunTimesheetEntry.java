package com.bookverse.development.packapps.automation.runners;

import static com.bookverse.development.packapps.automation.utils.GenericConstants.TIMESHEET_ENTRY_ERROR;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.junit.Assert.assertNull;

import com.bookverse.development.packapps.automation.exceptions.TimesheetEntryException;
import com.bookverse.development.packapps.automation.models.UltimatixData;
import com.bookverse.development.packapps.automation.questions.VerifyStatus;
import com.bookverse.development.packapps.automation.tasks.UltimatixLogin;
import com.bookverse.development.packapps.automation.tasks.UltimatixTimesheetRegister;
import com.bookverse.development.packapps.automation.utils.DriverChrome;
import com.bookverse.development.packapps.models.Resources;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("unchecked")
@RunWith(SerenityRunner.class)
public class RunTimesheetEntry {

  UltimatixData ultimatixData = (UltimatixData) Resources.generalObject;

  @Before
  public void config() {
    setTheStage(Cast.whereEveryoneCan(
        BrowseTheWeb.with(DriverChrome.web().inTheWebPage(ultimatixData.getUrl()))));
    theActorCalled("PackAppsUser");
  }

  @Test
  public void registerTimeSheet() {
    theActorInTheSpotlight().wasAbleTo(UltimatixLogin.withCredentials(ultimatixData));
    theActorInTheSpotlight().attemptsTo(UltimatixTimesheetRegister.hours(ultimatixData.getHours()));
    theActorInTheSpotlight().should(seeThat(VerifyStatus.ofGeneralObject()).orComplainWith(TimesheetEntryException.class, TIMESHEET_ENTRY_ERROR));
  }

  @After
  public void close() {
    BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
  }
}