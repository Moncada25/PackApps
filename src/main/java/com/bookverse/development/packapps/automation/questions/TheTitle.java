package com.bookverse.development.packapps.automation.questions;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import com.bookverse.development.packapps.automation.userinterfaces.SearchElements;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

@AllArgsConstructor
public class TheTitle implements Question<Boolean> {

  private String title;

  public static TheTitle ofModalIs(String title) {
    return new TheTitle(title);
  }

  @Subject("validates that title of modal book is #title")
  @Override
  public Boolean answeredBy(Actor actor) {
    actor.attemptsTo(WaitUntil.the(SearchElements.TITLE_MODAL, isVisible()));

    actor.attemptsTo(
        Ensure.that(Text.of(SearchElements.TITLE_MODAL).answeredBy(actor)).isEqualTo(title)
    );

    return true;
  }
}