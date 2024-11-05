package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHome.IMAGE;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLogin.LOGIN_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLogin.PASSWORD_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLogin.USERNAME_FIELD;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.models.BookverseUser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;

public class LoginBookverse implements Task {

  private final BookverseUser bookverseUser;

  public LoginBookverse(BookverseUser bookverseUser) {
    this.bookverseUser = bookverseUser;
  }

  public static LoginBookverse withCredentials(BookverseUser bookverseUser) {
    return Tasks.instrumented(LoginBookverse.class, bookverseUser);
  }

  @Step("Login Bookverse")
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.wasAbleTo(
        WaitUntil.the(USERNAME_FIELD, isVisible()),
        Enter.theValue(bookverseUser.username()).into(USERNAME_FIELD),
        Enter.theValue(bookverseUser.password()).into(PASSWORD_FIELD),
        Click.on(LOGIN_BUTTON),
        WaitUntil.the(IMAGE, isVisible()).forNoMoreThan(5).seconds());
  }
}