package com.bookverse.development.packapps.automation.questions;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseSearchElements.TITLE_MODAL;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.support.ui.Wait;

public class TheTitle implements Question<String> {

  public static TheTitle ofModalWindow() {
    return new TheTitle();
  }

  @Subject("Verify title of modal book is #title")
  @Override
  public String answeredBy(Actor actor) {
    actor.attemptsTo(WaitUntil.the(TITLE_MODAL, isVisible()));
    return Text.of(TITLE_MODAL).viewedBy(actor).asString();
  }
}
