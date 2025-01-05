package com.bookverse.packapps.automation.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.annotations.Feature;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.annotations.WithTags;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.steps.StepEventBus;
import com.bookverse.packapps.automation.models.BookverseUser;
import com.bookverse.packapps.automation.questions.TheUser;
import com.bookverse.packapps.automation.tasks.Login;
import com.bookverse.packapps.automation.tasks.RegisterUser;
import com.bookverse.packapps.automation.utils.constants.GeneralConstants;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.automation.utils.SerenitySession;
import com.bookverse.packapps.automation.utils.WebApp;
import com.bookverse.packapps.automation.utils.SerenityConf;
import com.bookverse.packapps.automation.utils.constants.SessionVariables;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

@Feature
@RunWith(SerenityRunner.class)
public class RegisterUserTest {

  @BeforeClass
  public static void setUp() {
    SerenitySession.createActorForWeb(SerenityConf.getDevUrl(), false, GeneralConstants.ACTOR);
  }

  @Test
  @Title("Register a new user in Bookverse")
  @WithTags({
      @WithTag("Bookverse"),
      @WithTag("RegisterUser")
  })
  public void registerNewUser() {

    Actor actor = OnStage.theActorInTheSpotlight();

    actor.wasAbleTo(RegisterUser.inBookverse());

    BookverseUser bookverseUser = SerenitySession.get(SessionVariables.USER_REGISTERED);

    actor.attemptsTo(Login.user(bookverseUser));
    actor.should(
        seeThat(TheUser.logged(), is(bookverseUser.name() + " " + bookverseUser.lastName()))
    );
  }

  @AfterClass
  public static void close() {
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