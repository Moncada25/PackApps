package com.bookverse.packapps.automation.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.steps.StepEventBus;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.annotations.Feature;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.annotations.WithTags;
import com.bookverse.packapps.automation.models.BookverseUser;
import com.bookverse.packapps.automation.questions.TheBook;
import com.bookverse.packapps.automation.tasks.SearchBook;
import com.bookverse.packapps.automation.utils.constants.GeneralConstants;
import com.bookverse.packapps.automation.utils.WebApp;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.automation.utils.SerenitySession;
import com.bookverse.packapps.automation.tasks.Login;
import com.bookverse.packapps.automation.utils.GeneralUtils;
import com.bookverse.packapps.automation.utils.SerenityConf;
import com.bookverse.packapps.automation.utils.constants.SessionVariables;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

@Feature
@RunWith(SerenityRunner.class)
public class SearchBookTest {

  @BeforeClass
  public static void setUp() {
    SerenitySession.createActorForWeb(SerenityConf.getProdUrl(), false, GeneralConstants.ACTOR);
  }

  @Test
  @Title("Search a book in Bookverse")
  @WithTags({
      @WithTag("Bookverse"),
      @WithTag("SearchBook")
  })
  public void searchBook() {

    Actor actor = OnStage.theActorInTheSpotlight();
    BookverseUser bookverseUser = GeneralUtils.getUser();

    actor.wasAbleTo(Login.user(bookverseUser));
    actor.attemptsTo(SearchBook.inBookverse(bookverseUser.book()));
    actor.should(seeThat(TheBook.hasTitle(), is(bookverseUser.book())));
  }

  @AfterClass
  public static void close() {
    WebApp.stop();
    String testStatus = StepEventBus.getEventBus()
        .getBaseStepListener()
        .getCurrentTestOutcome()
        .getResult()
        .toString();

    BookverseUser bookverseUser = SerenitySession.get(SessionVariables.USER_LOGGED);
    String author = SerenitySession.get(SessionVariables.AUTHOR);

    Alerts.message(
        testStatus, "Book → " + bookverseUser.book() + "\n" + "Author → " + author
    );
  }
}