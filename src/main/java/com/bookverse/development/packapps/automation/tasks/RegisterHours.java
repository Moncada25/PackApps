package com.bookverse.development.packapps.automation.tasks;

import static com.bookverse.development.packapps.automation.userinterfaces.ChronusLogin.STATUS_WEEK;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusTimesheet.AREA_NAME;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusTimesheet.CATEGORY_NAME;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusTimesheet.CLIENT_NAME;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusTimesheet.DAY;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusTimesheet.DESCRIPTION;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusTimesheet.NEXT_WEEK;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusTimesheet.PROJECT_NAME;
import static com.bookverse.development.packapps.automation.userinterfaces.ChronusTimesheet.REGISTER;
import static com.bookverse.development.packapps.automation.utils.Constants.CLIENT_HOURS;
import static com.bookverse.development.packapps.automation.utils.Constants.DEFAULT_AREA;
import static com.bookverse.development.packapps.automation.utils.Constants.DEFAULT_CLIENT;
import static com.bookverse.development.packapps.automation.utils.Constants.DEFAULT_PROJETC;
import static com.bookverse.development.packapps.automation.utils.Constants.OPEN;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

import com.bookverse.development.packapps.automation.interactions.WaitTime;
import com.bookverse.development.packapps.automation.models.Chronus;
import com.bookverse.development.packapps.core.Resources;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class RegisterHours implements Task {

  private Chronus chronus;

  public RegisterHours(Chronus chronus) {
    this.chronus = chronus;
  }

  public static RegisterHours inTimesheet(Chronus chronus) {
    return Tasks.instrumented(RegisterHours.class, chronus);
  }

  @Step("Register hours")
  @Override
  public <T extends Actor> void performAs(T actor) {

    DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
    String day = dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("en", "EN"));

    actor.attemptsTo(
        Check.whether(!STATUS_WEEK.resolveFor(actor).containsText(OPEN)).andIfSo(
            Click.on(NEXT_WEEK),
            WaitUntil.the(AREA_NAME.of(CLIENT_HOURS), isEnabled())
        ),
        SelectFromOptions.byVisibleText(DEFAULT_AREA).from(AREA_NAME.of(CLIENT_HOURS)),
        WaitTime.inSeconds(1),
        SelectFromOptions.byVisibleText(DEFAULT_CLIENT).from(CLIENT_NAME.of(CLIENT_HOURS)),
        WaitTime.inSeconds(1),
        SelectFromOptions.byVisibleText(DEFAULT_PROJETC).from(PROJECT_NAME.of(CLIENT_HOURS)),
        WaitTime.inSeconds(1),
        SelectFromOptions.byVisibleText(chronus.getCategory().toUpperCase()).from(CATEGORY_NAME.of(CLIENT_HOURS)),
        WaitTime.inSeconds(1),
        Enter.theValue(chronus.getDescription()).into(DESCRIPTION.of(CLIENT_HOURS)),
        Enter.theValue(chronus.getHours()).into(DAY.of(CLIENT_HOURS, day)),
        Click.on(REGISTER.of(CLIENT_HOURS))
    );

    Resources.setGeneralObject(null);
  }
}