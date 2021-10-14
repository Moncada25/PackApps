package com.bookverse.development.packapps.automation.runners;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

import com.bookverse.development.packapps.automation.exceptions.BookNotFound;
import com.bookverse.development.packapps.automation.models.Bookverse;
import com.bookverse.development.packapps.automation.questions.TheTitle;
import com.bookverse.development.packapps.automation.tasks.LoginBookverse;
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
public class RunSearchBook {

  Bookverse bookverse = (Bookverse) Resources.getGeneralObject();

  @Before
  public void config() {
    setTheStage(Cast.whereEveryoneCan(BrowseTheWeb.with(WebDriverFactory.goToWeb(bookverse.getUrl()))));
    theActorCalled(Constants.ACTOR);
  }

  @Test
  public void searchBook() {
    theActorInTheSpotlight().wasAbleTo(LoginBookverse.withCredentials(bookverse));
    theActorInTheSpotlight().attemptsTo(
        com.bookverse.development.packapps.automation.tasks.SearchBook.inBookverse(bookverse.getBook()));
    theActorInTheSpotlight().should(seeThat(TheTitle.ofModalWindow(), is(bookverse.getBook())).
        orComplainWith(BookNotFound.class,
            ExceptionsMessages.SEARCH_BOOK_ERROR.getProperty()));
    Alerts.message("Test passed!", ""
        + "Book → " + bookverse.getBook() + "\n"
        + "Author → " + theActorInTheSpotlight().recall("AUTHOR"));
  }

  @After
  public void close() {
    BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
  }
}