package com.bookverse.development.packapps.automation.questions;

import com.bookverse.development.packapps.models.Resources;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

public class VerifyStatus implements Question<Boolean> {

  public static VerifyStatus ofGeneralObject(){
    return new VerifyStatus();
  }

  @Subject("Verify status of general object")
  @Override
  public Boolean answeredBy(Actor actor) {
    return Resources.generalObject == null;
  }
}
