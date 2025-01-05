package com.bookverse.packapps.automation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import com.bookverse.packapps.automation.userinterfaces.HomeElements;
import com.bookverse.packapps.automation.userinterfaces.SearchElements;
import com.bookverse.packapps.automation.utils.constants.SessionVariables;
import com.bookverse.packapps.automation.utils.SerenitySession;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SearchBook implements Task {

  private final String book;

  public SearchBook(String book) {
    this.book = book;
  }

  public static SearchBook inBookverse(String book) {
    return Tasks.instrumented(SearchBook.class, book);
  }

  @Step("Search the book #book")
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(
        WaitUntil.the(HomeElements.BTN_SEARCH_BOOK, isVisible()),
        Click.on(HomeElements.BTN_SEARCH_BOOK),
        Enter.theValue(book).into(HomeElements.TXT_SEARCH_BOOK),
        Click.on(HomeElements.SEARCH_BOOK_BUTTON),
        Click.on(HomeElements.ALERT_ACCEPT),
        WaitUntil.the(SearchElements.SEARCH_RESULT.of(book), isVisible())
    );

    actor.attemptsTo(Click.on(SearchElements.OPEN_BOOK.of(book)));

    SerenitySession.set(
        SessionVariables.AUTHOR, Text.of(SearchElements.AUTHOR_BOOK.of(book)).answeredBy(actor)
    );
  }
}