package com.bookverse.development.packapps.automation.stepdefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.junit.Assert.assertNull;

import com.bookverse.development.packapps.automation.models.UltimatixData;
import com.bookverse.development.packapps.automation.tasks.Login;
import com.bookverse.development.packapps.automation.tasks.Regiter;
import com.bookverse.development.packapps.automation.utils.DriverChrome;
import com.bookverse.development.packapps.core.Resources;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TimesheetStepDefinitions {

    UltimatixData ultimatixData = (UltimatixData) Resources.generalObject;

    @Before
    public void config() {
        setTheStage(new Cast());
        theActorCalled("PackAppsUser");
        theActorInTheSpotlight().can(BrowseTheWeb.with(DriverChrome.web().inTheWebPage(ultimatixData.getUrl())));
    }

    @Test
    public void login() {
        theActorInTheSpotlight().wasAbleTo(
            Login.withUltimatix(ultimatixData),
            Regiter.hours(ultimatixData.getHours()));
        assertNull("Don't null general object", Resources.generalObject);
    }

    @After
    public void close() {
        BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
    }
}