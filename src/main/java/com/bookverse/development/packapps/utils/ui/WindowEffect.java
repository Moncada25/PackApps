package com.bookverse.development.packapps.utils.ui;

import com.bookverse.development.packapps.utils.constants.Alerts;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class WindowEffect {

  public static final int EXIT = 3;
  public static final int DISPOSE = 2;

  public WindowEffect() {
  }

  public static void JFrameFadeOut(final float opacityStart, final float opacityEnd,
      final float opacityDecrement, final long sleep, final JFrame frame,
      final int actionAfterFade) {

    (new Thread(() -> {
      for (float i = opacityStart; i >= opacityEnd; i -= opacityDecrement) {
        try {
          Thread.sleep(sleep);
          frame.setOpacity(i);
        } catch (Exception var3) {
          Alerts.message("Error", var3.getMessage());
        }
      }

      WindowEffect.action(actionAfterFade, frame);
    })).start();
  }

  public static void JFrameFadeIn(final float opacityStart, final float opacityEnd,
      final float opacityIncrement, final long sleep, final JFrame frame) {

    (new Thread(() -> {
      for (float i = opacityStart; i <= opacityEnd; i += opacityIncrement) {
        try {
          Thread.sleep(sleep);
          frame.setOpacity(i);
        } catch (Exception var3) {
          Alerts.message("Error", var3.getMessage());
        }
      }

      frame.setOpacity(opacityEnd);
    })).start();
  }

  public static void JDialogFadeOut(final float opacityStart, final float opacityEnd,
      final float opacityDecrement, final long sleep, final JDialog dialog,
      final int actionAfterFade) {
    (new Thread(() -> {
      for (float i = opacityStart; i >= opacityEnd; i -= opacityDecrement) {
        try {
          Thread.sleep(sleep);
          dialog.setOpacity(i);
        } catch (Exception var3) {
          Alerts.message("Error", var3.getMessage());
        }
      }

      WindowEffect.action(actionAfterFade, dialog);
    })).start();

  }

  public static void JDialogFadeIn(final float opacityStart, final float opacityEnd,
      final float opacityDecrement, final long sleep, final JDialog dialog) {
    (new Thread(() -> {
      for (float i = opacityStart; i <= opacityEnd; i += opacityDecrement) {
        try {
          Thread.sleep(sleep);
          dialog.setOpacity(i);
        } catch (Exception var3) {
          Alerts.message("Error", var3.getMessage());
        }
      }

    })).start();
  }

  private static void action(int option, JFrame frame) {
    switch (option) {
      case 1:
      case 2:
        frame.setVisible(false);
        break;
      case 3:
        System.exit(0);
    }
  }

  private static void action(int option, JDialog frame) {
    switch (option) {
      case 1:
      case 2:
        frame.setVisible(false);
        break;
      case 3:
        System.exit(0);
    }
  }
}