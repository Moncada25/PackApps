package com.bookverse.development.packapps.automation.questions;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseHome.USER_LOGGED;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.bookverse.development.packapps.automation.interactions.WaitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class TheUser implements Question<String> {

  public static TheUser logged() {
    return new TheUser();
  }

  @Subject("Verify that user is registered")
  @Override
  public String answeredBy(Actor actor) {

    actor.attemptsTo(
        WaitUntil.the(USER_LOGGED, isVisible()),
        WaitTime.inSeconds(5)
    );

    return Text.of(USER_LOGGED).answeredBy(actor);
  }
}