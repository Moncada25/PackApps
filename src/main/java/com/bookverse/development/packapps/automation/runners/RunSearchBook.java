package com.bookverse.development.packapps.automation.runners;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;

import com.bookverse.development.packapps.automation.exceptions.SearchBookException;
import com.bookverse.development.packapps.automation.models.BookverseData;
import com.bookverse.development.packapps.automation.questions.TheTitle;
import com.bookverse.development.packapps.automation.tasks.LoginBookverse;
import com.bookverse.development.packapps.automation.tasks.SearchBook;
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
public class RunSearchBook {

  BookverseData bookverseData = (BookverseData) Resources.generalObject;

  @Before
  public void config() {
    setTheStage(Cast.whereEveryoneCan(
        BrowseTheWeb.with(DriverChrome.web().inTheWebPage(bookverseData.getUrl()))));
    theActorCalled("PackAppsUser");
  }

  @Test
  public void searchBook() {
    theActorInTheSpotlight().wasAbleTo(LoginBookverse.withCredentials(bookverseData));
    theActorInTheSpotlight().attemptsTo(SearchBook.inBookverse(bookverseData.getBook()));
    theActorInTheSpotlight().should(seeThat(TheTitle.ofModalWindow(), is(bookverseData.getBook())).
        orComplainWith(SearchBookException.class, ExceptionsMessages.SEARCH_BOOK_ERROR.getProperty()));
    Alerts.message("Test passed!", ""
        + "Book → " + bookverseData.getBook() + "\n"
        + "Author → " + theActorInTheSpotlight().recall("AUTHOR"));
  }

  @After
  public void close() {
    BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
  }
}
