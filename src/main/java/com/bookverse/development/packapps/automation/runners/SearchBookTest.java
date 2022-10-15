package com.bookverse.development.packapps.automation.runners;

import static com.bookverse.development.packapps.automation.utils.Constants.BOOKVERSE_DEV;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

import com.bookverse.development.packapps.automation.exceptions.BookNotFound;
import com.bookverse.development.packapps.automation.models.Bookverse;
import com.bookverse.development.packapps.automation.questions.TheTitle;
import com.bookverse.development.packapps.automation.tasks.LoginBookverse;
import com.bookverse.development.packapps.automation.tasks.SearchBook;
import com.bookverse.development.packapps.automation.utils.Constants;
import com.bookverse.development.packapps.automation.utils.ExceptionsMessages;
import com.bookverse.development.packapps.automation.utils.WebDriverFactory;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class SearchBookTest {

  Bookverse bookverse = (Bookverse) Resources.getObject();

  @Before
  public void config() {
    setTheStage(Cast.whereEveryoneCan(BrowseTheWeb.with(WebDriverFactory.goToWeb(BOOKVERSE_DEV))));
    theActorCalled(Constants.ACTOR);
  }

  @Test
  public void searchBook() {
    theActorInTheSpotlight().wasAbleTo(LoginBookverse.withCredentials(bookverse));
    theActorInTheSpotlight().attemptsTo(SearchBook.inBookverse(bookverse.getBook()));
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