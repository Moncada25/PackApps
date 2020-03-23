package com.bookverse.development.packapps.automation.questions;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseSearchElements.TITLE_MODAL;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

public class VerifyTitle implements Question<Boolean> {

  private String title;

  public VerifyTitle(String title) {
    this.title = title;
  }

  public static VerifyTitle ofModal(String title) {
    return new VerifyTitle(title);
  }

  @Subject("Verify title of modal book is #title")
  @Override
  public Boolean answeredBy(Actor actor) {
    return TITLE_MODAL.of(title).resolveFor(actor).isVisible();
  }
}
