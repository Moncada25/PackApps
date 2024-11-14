package com.bookverse.development.packapps.utils;

import java.util.function.Consumer;
import lombok.Data;

@Data
public class Timer implements Runnable {

  private int minutes;
  private int seconds;
  private boolean isRunning;
  private Runnable onTimeUp;
  private Consumer<String> onTimeUpdate;

  public Timer(
      int initialMinutes,
      int initialSeconds,
      Runnable onTimeUp,
      Consumer<String> onTimeUpdate
  ) {
    this.minutes = initialMinutes;
    this.seconds = initialSeconds;
    this.onTimeUp = onTimeUp;
    this.onTimeUpdate = onTimeUpdate;
  }

  @Override
  public void run() {
    isRunning = true;
    while (isRunning) {
      updateTimerLabel();
      GeneralUtils.waitSeconds(1);
      decrementTimer();
      if (minutes == 0 && seconds == 0) {
        isRunning = false;
        onTimeUp.run();
      }
    }
  }

  public void start() {
    new Thread(this).start();
  }

  public void stop() {
    isRunning = false;
  }

  private void decrementTimer() {
    if (seconds > 0) {
      seconds--;
    } else if (minutes > 0) {
      minutes--;
      seconds = 59;
    }
  }

  private void updateTimerLabel() {
    String min = (minutes < 10) ? "0" + minutes : String.valueOf(minutes);
    String sec = (seconds < 10) ? "0" + seconds : String.valueOf(seconds);
    onTimeUpdate.accept(min + ":" + sec);
  }
}