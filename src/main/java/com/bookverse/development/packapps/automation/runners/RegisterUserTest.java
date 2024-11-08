package com.bookverse.development.packapps.automation.runners;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.steps.StepEventBus;
import com.bookverse.development.packapps.automation.models.BookverseUser;
import com.bookverse.development.packapps.automation.questions.TheUser;
import com.bookverse.development.packapps.automation.tasks.Login;
import com.bookverse.development.packapps.automation.tasks.RegisterUser;
import com.bookverse.development.packapps.automation.utils.constants.GeneralConstants;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.automation.utils.SerenitySession;
import com.bookverse.development.packapps.automation.utils.WebApp;
import com.bookverse.development.packapps.automation.utils.SerenityConf;
import com.bookverse.development.packapps.automation.utils.constants.SessionVariables;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

@RunWith(SerenityRunner.class)
public class RegisterUserTest {

  private Actor actor;

  @Before
  public void setUp() {
    SerenitySession.createActorForWeb(SerenityConf.getDevUrl(), false, GeneralConstants.ACTOR);
    actor = OnStage.theActorInTheSpotlight();
  }

  @Test
  public void registerNewUser() {
    actor.wasAbleTo(RegisterUser.inBookverse());

    BookverseUser bookverseUser = SerenitySession.get(SessionVariables.USER_REGISTERED);

    actor.attemptsTo(Login.user(bookverseUser));
    actor.should(
        seeThat(TheUser.logged(), is(bookverseUser.name() + " " + bookverseUser.lastName()))
    );
  }

  @After
  public void close() {
    WebApp.stop();
    String testStatus = StepEventBus.getEventBus()
        .getBaseStepListener()
        .getCurrentTestOutcome()
        .getResult()
        .toString();

    BookverseUser newUser = SerenitySession.get(SessionVariables.USER_REGISTERED);
    String message = newUser == null
        ? "User no registered"
        : "User " + newUser.name() + " was registered!";

    Alerts.message(testStatus, message);
  }
}