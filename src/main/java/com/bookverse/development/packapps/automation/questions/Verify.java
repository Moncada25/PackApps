package com.bookverse.development.packapps.automation.questions;

import static com.bookverse.development.packapps.automation.userinterfaces.BookverseSearchElements.TITLE_MODAL;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.thucydides.core.annotations.Step;

public class Verify implements Question<Boolean> {

  private String title;

  public Verify(String title) {
    this.title = title;
  }

  public static Verify titleModal(String title) {
    return new Verify(title);
  }

  @Subject("Verify title of modal book #title")
  @Override
  public Boolean answeredBy(Actor actor) {
    return TITLE_MODAL.of(title).resolveFor(actor).isVisible();
  }
}
