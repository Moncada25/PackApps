package com.bookverse.development.packapps.apps.utils.ui;

import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;

public final class Effects {

  private static final int EXIT = 3;
  private static final int DISPOSE = 2;

  private static void fadeOut(final float opacityStart, final float opacityEnd,
      final float opacityDecrement, final long sleep, final Window window,
      final int actionAfterFade) {

    new Thread(() -> {
      for (float i = opacityStart; i >= opacityEnd; i -= opacityDecrement) {
        try {
          Thread.sleep(sleep);
          window.setOpacity(i);
        } catch (Exception e) {
          Alerts.message("Error", e.getMessage());
        }
      }
      action(actionAfterFade, window);
    }).start();
  }

  private static void fadeIn(final float opacityStart, final float opacityEnd,
      final float opacityIncrement, final long sleep, final Window window) {

    new Thread(() -> {
      for (float i = opacityStart; i <= opacityEnd; i += opacityIncrement) {
        try {
          Thread.sleep(sleep);
          window.setOpacity(i);
        } catch (Exception e) {
          Alerts.message("Error", e.getMessage());
        }
      }
      window.setOpacity(opacityEnd);
    }).start();
  }

  private static void action(int option, Window window) {
    if (option == EXIT) {
      System.exit(0);
    } else {
      window.setVisible(false);
    }
  }

  public static void fadeIn(JDialog window) {
    fadeIn(0f, 1f, 0.2f, 50, window);
  }

  public static void fadeIn(JFrame window) {
    fadeIn(0f, 1f, 0.2f, 50, window);
  }

  public static void fadeOut(JFrame window) {
    fadeOut(1f, 0f, 0.2f, 50, window, EXIT);
  }

  public static void fadeOut(JDialog window) {
    fadeOut(1f, 0f, 0.2f, 50, window, DISPOSE);
  }

  private Effects() {
  }
}