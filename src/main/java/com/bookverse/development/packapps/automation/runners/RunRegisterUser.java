package com.bookverse.development.packapps.automation.runners;

import static com.bookverse.development.packapps.automation.utils.Constants.BOOKVERSE_DEV;
import static com.bookverse.development.packapps.automation.utils.Constants.USER_REGISTERED;
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
import net.serenitybdd.core.Serenity;
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
    setTheStage(Cast.whereEveryoneCan(BrowseTheWeb.with(WebDriverFactory.goToWeb(BOOKVERSE_DEV))));
    theActorCalled(Constants.ACTOR);
  }

  @Test
  public void registerNewUser() {
    theActorInTheSpotlight().wasAbleTo(RegisterUser.inBookverse(bookverse));
    theActorInTheSpotlight().attemptsTo(LoginBookverse.withCredentials(bookverse));
    theActorInTheSpotlight().should(
        seeThat(TheUser.logged(), is(bookverse.getName() + " " + bookverse.getLastName()))
            .orComplainWith(UserNotRegistered.class,
                ExceptionsMessages.REGISTER_USER_ERROR.getProperty()));
  }

  @After
  public void close() {
    BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();

    if (Serenity.sessionVariableCalled(USER_REGISTERED) == null) {
      Alerts.message("Test failed!", "User no registered");
    } else {
      Alerts.message("Test passed!", "User " + Serenity.sessionVariableCalled(USER_REGISTERED) + " is registered!");
    }
  }
}