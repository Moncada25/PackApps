package com.bookverse.packapps.automation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import com.bookverse.packapps.automation.models.BookverseUser;
import com.bookverse.packapps.automation.userinterfaces.HomeElements;
import com.bookverse.packapps.automation.userinterfaces.LoginElements;
import com.bookverse.packapps.automation.userinterfaces.SignUpElements;
import com.bookverse.packapps.automation.utils.constants.GeneralConstants;
import com.bookverse.packapps.automation.utils.SerenitySession;
import com.bookverse.packapps.automation.utils.GeneralUtils;
import com.bookverse.packapps.automation.utils.constants.SessionVariables;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegisterUser implements Task {

  public static RegisterUser inBookverse() {
    return Tasks.instrumented(RegisterUser.class);
  }

  @Step("{0} registers new account in Bookverse")
  @Override
  public <T extends Actor> void performAs(T actor) {

    BookverseUser bookverseUser = GeneralUtils.getRegisterUser();

    actor.attemptsTo(
        WaitUntil.the(LoginElements.BTN_SIGN_UP, isVisible()).forNoMoreThan(5).seconds(),
        Click.on(LoginElements.BTN_SIGN_UP)
    );

    actor.attemptsTo(
        WaitUntil.the(SignUpElements.TXT_NAME, isVisible()).forNoMoreThan(5).seconds(),
        Enter.theValue(bookverseUser.name()).into(SignUpElements.TXT_NAME),
        Enter.theValue(bookverseUser.lastName()).into(SignUpElements.TXT_LAST_NAME),
        Enter.theValue("1234567").into(SignUpElements.TXT_PHONE),
        SelectFromOptions.byValue(bookverseUser.occupation()).from(SignUpElements.LIST_OCCUPATION),
        Enter.theValue(bookverseUser.address()).into(SignUpElements.TXT_ADDRESS)
    );

    actor.attemptsTo(
        Scroll.to(SignUpElements.TXT_USERNAME).andAlignToTop(),
        Enter.theValue(bookverseUser.name()).into(SignUpElements.TXT_USERNAME),
        Enter.theValue(bookverseUser.password()).into(SignUpElements.TXT_FIRST_PASSWORD),
        Enter.theValue(bookverseUser.password()).into(SignUpElements.TXT_SECOND_PASSWORD),
        Enter.theValue(bookverseUser.email()).into(SignUpElements.TXT_EMAIL),
        Click.on(SignUpElements.CHK_GENDER.of(bookverseUser.gender())),
        Click.on(SignUpElements.BTN_SIGN_UP)
    );

    actor.attemptsTo(
        WaitUntil.the(HomeElements.ALERT_ACCEPT, isVisible()).forNoMoreThan(2).seconds(),
        Click.on(HomeElements.ALERT_ACCEPT)
    );

    actor.attemptsTo(
        WaitUntil.the(HomeElements.ALERT_ACCEPT, isVisible()).forNoMoreThan(2).seconds(),
        Ensure.that(Text.of(HomeElements.ALERT_TITLE).answeredBy(actor)
        ).isNotEqualTo(GeneralConstants.ALERT_ERROR),
        Click.on(HomeElements.ALERT_ACCEPT),
        WaitUntil.the(SignUpElements.BTN_LOGIN_BACK, isClickable()).forNoMoreThan(5).seconds(),
        Click.on(SignUpElements.BTN_LOGIN_BACK)
    );

    SerenitySession.set(SessionVariables.USER_REGISTERED, bookverseUser);
  }
}