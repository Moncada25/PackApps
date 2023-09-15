package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHome.ALERT_ACCEPT;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHome.ALERT_TITLE;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLogin.REGISTER_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.ADDRESS_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.EMAIL_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.ENTER_LOGIN;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.FIRST_PASSWORD_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.GENDER_CHECK;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.LAST_NAME_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.NAME_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.OCCUPATION_LIST;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.PHONE_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.REGISTER_SUBMIT;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.SECOND_PASSWORD_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegister.USERNAME_FIELD;
import static com.bookverse.development.packapps.automation.utils.Constants.ALERT_ERROR;
import static com.bookverse.development.packapps.automation.utils.Constants.USER_REGISTERED;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.models.Bookverse;
import net.serenitybdd.core.Serenity;
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
import net.thucydides.core.annotations.Step;

public class RegisterUser implements Task {

  private final Bookverse bookverse;

  public RegisterUser(Bookverse bookverse) {
    this.bookverse = bookverse;
  }

  public static RegisterUser inBookverse(Bookverse bookverse) {
    return Tasks.instrumented(RegisterUser.class, bookverse);
  }

  @Step("Register new user in Bookverse")
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(
        WaitUntil.the(REGISTER_BUTTON, isVisible()).forNoMoreThan(5).seconds(),
        Click.on(REGISTER_BUTTON)
    );

    actor.attemptsTo(
        WaitUntil.the(NAME_FIELD, isVisible()).forNoMoreThan(5).seconds(),
        Enter.theValue(bookverse.getName()).into(NAME_FIELD),
        Enter.theValue(bookverse.getLastName()).into(LAST_NAME_FIELD),
        Enter.theValue("1234567").into(PHONE_FIELD),
        SelectFromOptions.byValue(bookverse.getOccupation()).from(OCCUPATION_LIST),
        Enter.theValue(bookverse.getAddress()).into(ADDRESS_FIELD)
    );

    actor.attemptsTo(
        Scroll.to(USERNAME_FIELD).andAlignToTop(),
        Enter.theValue(bookverse.getName()).into(USERNAME_FIELD),
        Enter.theValue(bookverse.getPassword()).into(FIRST_PASSWORD_FIELD),
        Enter.theValue(bookverse.getPassword()).into(SECOND_PASSWORD_FIELD),
        Enter.theValue(bookverse.getEmail()).into(EMAIL_FIELD),
        Click.on(GENDER_CHECK.of(bookverse.getGender())),
        Click.on(REGISTER_SUBMIT)
    );

    actor.attemptsTo(
        WaitUntil.the(ALERT_ACCEPT, isVisible()).forNoMoreThan(2).seconds(),
        Click.on(ALERT_ACCEPT)
    );

    actor.attemptsTo(
        WaitUntil.the(ALERT_ACCEPT, isVisible()).forNoMoreThan(2).seconds(),
        Ensure.that(Text.of(ALERT_TITLE).answeredBy(actor)).isNotEqualTo(ALERT_ERROR),
        Click.on(ALERT_ACCEPT),
        WaitUntil.the(ENTER_LOGIN, isClickable()).forNoMoreThan(5).seconds(),
        Click.on(ENTER_LOGIN)
    );

    Serenity.setSessionVariable(USER_REGISTERED).to(bookverse.getName() + " " + bookverse.getLastName());
  }
}