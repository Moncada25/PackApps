package com.bookverse.development.packapps.automation.runners;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

import com.bookverse.development.packapps.automation.models.Bookverse;
import com.bookverse.development.packapps.automation.questions.TheUser;
import com.bookverse.development.packapps.automation.tasks.LoginBookverse;
import com.bookverse.development.packapps.automation.tasks.RegisterUser;
import com.bookverse.development.packapps.automation.utils.Constants;
import com.bookverse.development.packapps.automation.utils.WebDriverFactory;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RegisterUserTest {

  Bookverse bookverse = (Bookverse) Resources.getObject();

  @Test
  public void registerNewUser() {

    setTheStage(Cast.whereEveryoneCan(
        BrowseTheWeb.with(WebDriverFactory.goToWeb(Constants.BOOKVERSE_DEV)))
    );
    theActorCalled(Constants.ACTOR);

    theActorInTheSpotlight().wasAbleTo(RegisterUser.inBookverse(bookverse));
    theActorInTheSpotlight().attemptsTo(LoginBookverse.withCredentials(bookverse));
    theActorInTheSpotlight().should(
        seeThat(TheUser.logged(), is(bookverse.getName() + " " + bookverse.getLastName()))
    );

    BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();

    String user = Serenity.sessionVariableCalled(Constants.USER_REGISTERED);

    if (user == null) {
      Alerts.message("Test failed!", "User no registered");
    } else {
      Alerts.message("Test passed!", "User " + user + " is registered!");
    }
  }
}