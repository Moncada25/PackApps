package com.bookverse.packapps.automation.tasks;

import com.bookverse.packapps.automation.utils.constants.SessionVariables;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import com.bookverse.packapps.automation.models.BookverseUser;
import com.bookverse.packapps.automation.userinterfaces.HomeElements;
import com.bookverse.packapps.automation.userinterfaces.LoginElements;
import com.bookverse.packapps.automation.utils.SerenitySession;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

@AllArgsConstructor
public class Login implements Task {

  private BookverseUser bookverseUser;

  public static Login user(BookverseUser bookverseUser) {
    return Tasks.instrumented(Login.class, bookverseUser);
  }

  @Step("{0} login Bookverse page with credentials")
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.wasAbleTo(
        WaitUntil.the(LoginElements.TXT_USER, isVisible()),
        Enter.theValue(bookverseUser.name()).into(LoginElements.TXT_USER),
        Enter.theValue(bookverseUser.password()).into(LoginElements.TXT_PASSWORD),
        Click.on(LoginElements.BTN_LOGIN),
        WaitUntil.the(HomeElements.IMAGE_AVATAR, isVisible()).forNoMoreThan(5).seconds()
    );

    SerenitySession.set(SessionVariables.USER_LOGGED, bookverseUser);
  }
}