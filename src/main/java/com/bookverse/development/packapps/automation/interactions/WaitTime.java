package com.bookverse.development.packapps.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

public class WaitTime implements Interaction {

    private double time;

    public WaitTime(double time) {
        this.time = time;
    }

    @Step("Waiting...")
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
