package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHomeElements.ALERT_ACCEPT;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseLoginElements.REGISTER_BUTTON;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.ADDRESS_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.EMAIL_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.ENTER_LOGIN;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.FIRST_PASSWORD_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.GENDER_CHECK;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.LAST_NAME_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.NAME_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.OCCUPATION_LIST;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.PHONE_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.REGISTER_SUBMIT;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.SECOND_PASSWORD_FIELD;
import static com.bookverse.development.packapps.automation.userinterfaces.BookverseRegisterElements.USERNAME_FIELD;
import static com.bookverse.development.packapps.utils.ArrayData.DATA_NEW_USER;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotCurrentlyVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class BookverseRegister implements Task {

  public static BookverseRegister newUser() {
    return Tasks.instrumented(BookverseRegister.class);
  }

  @Step("Register new user in Bookverse")
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.wasAbleTo(
        WaitUntil.the(REGISTER_BUTTON, isClickable()),
        Click.on(REGISTER_BUTTON),
        WaitUntil.the(NAME_FIELD, isVisible()),
        Enter.theValue(DATA_NEW_USER.get("Name")).into(NAME_FIELD),
        Enter.theValue(DATA_NEW_USER.get("LastName")).into(LAST_NAME_FIELD),
        Enter.theValue(DATA_NEW_USER.get("Phone")).into(PHONE_FIELD),
        SelectFromOptions.byValue(DATA_NEW_USER.get("Occupation")).from(OCCUPATION_LIST),
        Enter.theValue(DATA_NEW_USER.get("Address")).into(ADDRESS_FIELD),
        Enter.theValue(DATA_NEW_USER.get("Username")).into(USERNAME_FIELD),
        Enter.theValue(DATA_NEW_USER.get("Password")).into(FIRST_PASSWORD_FIELD),
        Enter.theValue(DATA_NEW_USER.get("Password")).into(SECOND_PASSWORD_FIELD),
        Enter.theValue(DATA_NEW_USER.get("Email")).into(EMAIL_FIELD),
        Click.on(GENDER_CHECK.of(DATA_NEW_USER.get("Gender"))),
        Click.on(REGISTER_SUBMIT),
        Click.on(ALERT_ACCEPT),
        Click.on(ALERT_ACCEPT),
        WaitUntil.the(ALERT_ACCEPT, isNotCurrentlyVisible()),
        Click.on(ENTER_LOGIN)
    );
    actor.remember("USER_REGISTERED", DATA_NEW_USER.get("Name")+" "+ DATA_NEW_USER.get("LastName"));
  }
}
