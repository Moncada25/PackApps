package com.bookverse.development.packapps.automation.runners;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

import com.bookverse.development.packapps.automation.exceptions.RegisterUserException;
import com.bookverse.development.packapps.automation.models.BookverseData;
import com.bookverse.development.packapps.automation.questions.TheUser;
import com.bookverse.development.packapps.automation.tasks.LoginBookverse;
import com.bookverse.development.packapps.automation.tasks.RegisterUser;
import com.bookverse.development.packapps.automation.utils.DriverChrome;
import com.bookverse.development.packapps.automation.utils.ExceptionsMessages;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("unchecked")
@RunWith(SerenityRunner.class)
public class RunRegisterUser {

  BookverseData bookverseData = (BookverseData) Resources.generalObject;

  @Before
  public void config() {
    setTheStage(Cast.whereEveryoneCan(
        BrowseTheWeb.with(DriverChrome.web().inTheWebPage(bookverseData.getUrl()))));
    theActorCalled("PackAppsUser");
  }

  @Test
  public void registerNewUser() {
    theActorInTheSpotlight().wasAbleTo(RegisterUser.inBookverse());
    theActorInTheSpotlight().attemptsTo(LoginBookverse.withCredentials(bookverseData));
    theActorInTheSpotlight().should(
        seeThat(TheUser.logged(),
            is(theActorInTheSpotlight().recall("USER_REGISTERED").toString()))
            .orComplainWith(RegisterUserException.class,
                ExceptionsMessages.REGISTER_USER_ERROR.getProperty()));
    if (theActorInTheSpotlight().recall("USER_REGISTERED") == null) {
      Alerts.message("Test failed!", "User no registered");
    } else {
      Alerts.message("Test passed!", "User " + theActorInTheSpotlight().recall("USER_REGISTERED")
          + " is registered!");
    }

  }

  @After
  public void close() {
    BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
  }
}
