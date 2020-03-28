package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHomeElements.IMAGE;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLoginElements.LOGIN_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLoginElements.PASSWORD_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLoginElements.USERNAME_FIELD;
import static com.bookverse.development.packapps.utils.ArrayData.DATA_NEW_USER;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.models.BookverseData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class LoginBookverse implements Task {

  private BookverseData bookverseData;

  public LoginBookverse(BookverseData bookverseData) {
    this.bookverseData = bookverseData;
  }

  public static LoginBookverse withCredentials(BookverseData bookverseData) {
    return Tasks.instrumented(LoginBookverse.class, bookverseData);
  }

  @Step("Login Bookverse")
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.remember("USER_REGISTERED", DATA_NEW_USER.get("Name") + " " + DATA_NEW_USER.get("LastName"));

    actor.wasAbleTo(
        WaitUntil.the(USERNAME_FIELD, isVisible()),
        Enter.theValue(bookverseData.getUser()).into(USERNAME_FIELD),
        Enter.theValue(bookverseData.getPassword()).into(PASSWORD_FIELD),
        Click.on(LOGIN_BUTTON),
        WaitUntil.the(IMAGE, isVisible()));
  }
}
