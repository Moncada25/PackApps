package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHome.IMAGE;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLogin.LOGIN_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLogin.PASSWORD_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLogin.USERNAME_FIELD;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.models.Bookverse;
import com.bookverse.development.packapps.utils.ArrayData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class LoginBookverse implements Task {

  private Bookverse bookverse;

  public LoginBookverse(Bookverse bookverse) {
    this.bookverse = bookverse;
  }

  public static LoginBookverse withCredentials(Bookverse bookverse) {
    return Tasks.instrumented(LoginBookverse.class, bookverse);
  }

  @Step("Login Bookverse")
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.remember("USER_REGISTERED",
        ArrayData.getDataUser("Name") + " " + ArrayData.getDataUser("LastName"));

    actor.wasAbleTo(
        WaitUntil.the(USERNAME_FIELD, isVisible()),
        Enter.theValue(bookverse.getUser()).into(USERNAME_FIELD),
        Enter.theValue(bookverse.getPassword()).into(PASSWORD_FIELD),
        Click.on(LOGIN_BUTTON),
        WaitUntil.the(IMAGE, isVisible()));
  }
}