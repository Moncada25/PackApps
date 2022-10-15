package com.bookverse.development.packapps.utils;

import static com.bookverse.development.packapps.utils.constants.AppConfig.PASSWORD_DBA;

import com.bookverse.development.packapps.utils.ui.Alerts;
import java.io.IOException;
import java.net.Socket;

public final class GeneralUtilities {



  private GeneralUtilities() {
  }

  public static int getIntRandom(int min, int max) {
    return (int) Math.floor(Math.random() * (min - max + 1) + max);
  }

  public static boolean loginDBA() {

    boolean canContinue = true;

    while (canContinue) {

      String password = Alerts.inputPassword("DBA's Password");

      if (password.length() != 0) {

        if (Config.get(PASSWORD_DBA.getProperty()).equals(password)) {
          return true;
        } else {
          Alerts.message("Error", "Incorrect password");
        }

      } else {
        canContinue = false;
      }
    }

    return false;
  }

  public static boolean verifyConnection(String request, boolean show) {

    String dirWeb = "www.google.com";
    int puerto = 80;
    Socket socket = null;

    try {
      socket = new Socket(dirWeb, puerto);

      return socket.isConnected();

    } catch (Exception e) {

      if (show) {
        Alerts.message("No internet connection", request);
      }

    } finally {

      try {

        if (socket != null) {
          socket.close();
        }

      } catch (IOException e) {
        Alerts.message("Error", e.getMessage());
      }
    }
    return false;
  }
}
