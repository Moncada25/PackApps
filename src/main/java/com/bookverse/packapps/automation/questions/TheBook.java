package com.bookverse.packapps.automation.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import com.bookverse.packapps.automation.userinterfaces.SearchElements;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class TheBook implements Question<String> {

  public static TheBook hasTitle() {
    return new TheBook();
  }

  @Override
  @Subject("validates that title is displayed")
  public String answeredBy(Actor actor) {
    actor.attemptsTo(
        WaitUntil.the(SearchElements.TITLE_MODAL, isVisible()).forNoMoreThan(5).seconds()
    );

    return Text.of(SearchElements.TITLE_MODAL).answeredBy(actor);
  }
}