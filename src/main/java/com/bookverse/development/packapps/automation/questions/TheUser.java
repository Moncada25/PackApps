package com.bookverse.development.packapps.automation.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import com.bookverse.development.packapps.automation.userinterfaces.HomeElements;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class TheUser implements Question<String> {

  public static TheUser logged() {
    return new TheUser();
  }

  @Subject("Verify that user is registered")
  @Override
  public String answeredBy(Actor actor) {

    actor.attemptsTo(
        WaitUntil.the(HomeElements.IMG_USER_LOGGED, isVisible()).forNoMoreThan(5).seconds()
    );

    return Text.of(HomeElements.IMG_USER_LOGGED).answeredBy(actor);
  }
}