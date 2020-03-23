package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHomeElements.IMAGE;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLoginElements.LOGIN_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLoginElements.PASSWORD_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLoginElements.USERNAME_FIELD;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.models.BookverseData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class BookverseLogin implements Task {

  private BookverseData bookverseData;

  public BookverseLogin(BookverseData bookverseData) {
    this.bookverseData = bookverseData;
  }

  public static BookverseLogin withCredentials(BookverseData bookverseData) {
    return Tasks.instrumented(BookverseLogin.class, bookverseData);
  }

  @Step("Login Bookverse")
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.wasAbleTo(
        WaitUntil.the(USERNAME_FIELD, isVisible()),
        Enter.theValue(bookverseData.getUser()).into(USERNAME_FIELD),
        Enter.theValue(bookverseData.getPassword()).into(PASSWORD_FIELD),
        Click.on(LOGIN_BUTTON),
        WaitUntil.the(IMAGE, isVisible()));
  }
}
