package com.bookverse.development.packapps.automation.runners;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.bookverse.development.packapps.automation.exceptions.RegisterUserException;
import com.bookverse.development.packapps.automation.models.BookverseData;
import com.bookverse.development.packapps.automation.questions.VerifyUser;
import com.bookverse.development.packapps.automation.tasks.BookverseLogin;
import com.bookverse.development.packapps.automation.tasks.BookverseRegister;
import com.bookverse.development.packapps.automation.utils.DriverChrome;
import com.bookverse.development.packapps.automation.utils.GenericConstants;
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
    setTheStage(Cast.whereEveryoneCan(BrowseTheWeb.with(DriverChrome.web().inTheWebPage(bookverseData.getUrl()))));
    theActorCalled("PackAppsUser");
  }

  @Test
  public void registerNewUser() {
    theActorInTheSpotlight().wasAbleTo(BookverseRegister.newUser());
    theActorInTheSpotlight().attemptsTo(BookverseLogin.withCredentials(bookverseData));
    theActorInTheSpotlight().should(seeThat(VerifyUser.registered(theActorInTheSpotlight().recall("USER_REGISTERED"))).
        orComplainWith(RegisterUserException.class, GenericConstants.REGISTER_USER_ERROR));
  }

  @After
  public void close() {
    BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
    Alerts.message("User registered!!",
        "User " + theActorInTheSpotlight().recall("USER_REGISTERED") + " is registered!");
  }
}
