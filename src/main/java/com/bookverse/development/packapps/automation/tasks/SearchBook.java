package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHomeElements.ALERT_ACCEPT;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHomeElements.SEARCH_BOOK;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHomeElements.SEARCH_BOOK_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHomeElements.SEARCH_BOOK_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseSearchElements.AUTHOR_BOOK;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseSearchElements.OPEN_BOOK;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseSearchElements.SEARCH_RESULT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class SearchBook implements Task {

  private String book;

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
        WaitUntil.the(SEARCH_BOOK, isVisible()),
        Click.on(SEARCH_BOOK),
        Enter.theValue(book).into(SEARCH_BOOK_FIELD),
        Click.on(SEARCH_BOOK_BUTTON),
        Click.on(ALERT_ACCEPT),
        WaitUntil.the(SEARCH_RESULT.of(book), isVisible()));
    actor.remember("AUTHOR", Text.of(AUTHOR_BOOK.of(book)).viewedBy(actor).asString());
    actor.attemptsTo(Click.on(OPEN_BOOK.of(book)));
  }
}
