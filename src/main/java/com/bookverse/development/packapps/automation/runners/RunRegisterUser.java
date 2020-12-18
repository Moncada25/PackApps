package com.bookverse.development.packapps.automation.runners;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

import com.bookverse.development.packapps.automation.exceptions.UserNotRegistered;
import com.bookverse.development.packapps.automation.models.Bookverse;
import com.bookverse.development.packapps.automation.questions.TheUser;
import com.bookverse.development.packapps.automation.tasks.LoginBookverse;
import com.bookverse.development.packapps.automation.tasks.RegisterUser;
import com.bookverse.development.packapps.automation.utils.Constants;
import com.bookverse.development.packapps.automation.utils.ExceptionsMessages;
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
public class RunRegisterUser {

  Bookverse bookverse = (Bookverse) Resources.getGeneralObject();

  @Before
  public void config() {
    System.out.println("Pasa 1");
    setTheStage(Cast.whereEveryoneCan(
        BrowseTheWeb.with(WebDriverFactory.goToWeb(bookverse.getUrl()))));
    theActorCalled(Constants.ACTOR);
    System.out.println("Crea actor");
  }

  @Test
  public void registerNewUser() {
    theActorInTheSpotlight().wasAbleTo(RegisterUser.inBookverse());
    theActorInTheSpotlight().attemptsTo(LoginBookverse.withCredentials(bookverse));
    theActorInTheSpotlight().should(seeThat(TheUser.logged(), is(theActorInTheSpotlight().recall("USER_REGISTERED").toString()))
            .orComplainWith(UserNotRegistered.class,
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