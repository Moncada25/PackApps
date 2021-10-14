package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHome.ALERT_ACCEPT;
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
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.utils.ArrayData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class RegisterUser implements Task {

  public static RegisterUser inBookverse() {
    return Tasks.instrumented(RegisterUser.class);
  }

  @Step("Register new user in Bookverse")
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.wasAbleTo(
        WaitUntil.the(REGISTER_BUTTON, isVisible()).forNoMoreThan(5).seconds(),
        Click.on(REGISTER_BUTTON),
        WaitUntil.the(NAME_FIELD, isVisible()),
        Enter.theValue(ArrayData.getDataUser("Name")).into(NAME_FIELD),
        Enter.theValue(ArrayData.getDataUser("LastName")).into(LAST_NAME_FIELD),
        Enter.theValue(ArrayData.getDataUser("Phone")).into(PHONE_FIELD),
        SelectFromOptions.byValue(ArrayData.getDataUser("Occupation")).from(OCCUPATION_LIST),
        Enter.theValue(ArrayData.getDataUser("Address")).into(ADDRESS_FIELD),
        Scroll.to(USERNAME_FIELD).andAlignToTop(),
        Enter.theValue(ArrayData.getDataUser("Username")).into(USERNAME_FIELD),
        Enter.theValue(ArrayData.getDataUser("Pass")).into(FIRST_PASSWORD_FIELD),
        Enter.theValue(ArrayData.getDataUser("Pass")).into(SECOND_PASSWORD_FIELD),
        Enter.theValue(ArrayData.getDataUser("Email")).into(EMAIL_FIELD),
        Click.on(GENDER_CHECK.of(ArrayData.getDataUser("Gender"))),
        Click.on(REGISTER_SUBMIT),
        WaitUntil.the(ALERT_ACCEPT, isVisible()),
        Click.on(ALERT_ACCEPT),
        WaitUntil.the(ALERT_ACCEPT, isVisible()),
        Click.on(ALERT_ACCEPT),
        WaitUntil.the(ENTER_LOGIN, isClickable()),
        Click.on(ENTER_LOGIN));
  }
}