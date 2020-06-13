package com.bookverse.development.packapps.automation.questions;

import com.bookverse.development.packapps.core.Resources;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

public class TheStatus implements Question<Boolean> {

  public static TheStatus ofGeneralObject() {
    return new TheStatus();
  }

  @Subject("Verify status of general object")
  @Override
  public Boolean answeredBy(Actor actor) {
    return Resources.getGeneralObject() == null;
  }
}