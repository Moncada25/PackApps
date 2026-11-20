package com.bookverse.packapps.utils.ui;

import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;

public final class Effects {
  private Effects() {}

  private static void animate(Window window, boolean entering, Runnable done) {
    var timer = new Timer(50, null);
    int[] step = {0};
    timer.addActionListener(event -> {
      try {
        float fraction = ++step[0] / 5f;
        window.setOpacity(entering ? fraction : 1f - fraction);
        if (step[0] < 5) return;
      } catch (UnsupportedOperationException | java.awt.IllegalComponentStateException unsupported) {
        // Decorated windows cannot change opacity on every desktop.
      }
      timer.stop();
      done.run();
    });
    timer.start();
  }

  public static void fadeIn(Window window) { animate(window, true, () -> {}); }
  public static void fadeOut(JFrame window) { animate(window, false, () -> System.exit(0)); }
  public static void fadeOut(JDialog window) { animate(window, false, () -> window.setVisible(false)); }
}
