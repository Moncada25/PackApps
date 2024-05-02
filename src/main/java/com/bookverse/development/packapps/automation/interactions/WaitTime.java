package com.bookverse.development.packapps.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.annotations.Step;

public class WaitTime implements Interaction {

  private final double time;

  public WaitTime(double time) {
    this.time = time;
  }

  @Step("Waiting... #time seconds")
  @Override
  public <T extends Actor> void performAs(T actor) {

    try {
      Thread.sleep((long) time * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static WaitTime inSeconds(double time) {
    return Tasks.instrumented(WaitTime.class, time);
  }
}
