package com.bookverse.development.packapps.utils.other;

import com.bookverse.development.packapps.utils.ui.Alerts;
import java.awt.Desktop;
import java.net.Socket;
import java.net.URI;
import java.security.SecureRandom;

import static com.bookverse.development.packapps.utils.constants.AppConfig.PASSWORD_DBA;

public final class GeneralUtils {

  private GeneralUtils() {
  }

  public static int getIntRandom(int min, int max) {
    return new SecureRandom().nextInt(max - min + 1) + 1;
  }

  public static boolean loginDBA() {

    boolean canContinue = true;

    while (canContinue) {

      String password = Alerts.inputPassword("DBA's Password");

      if (!password.isEmpty()) {

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
    try (Socket socket = new Socket("www.google.com", 80)) {
      return socket.isConnected();
    } catch (Exception e) {
      if (show) {
        Alerts.message("No internet connection", request);
      }
    }
    return false;
  }

  public static void openUrl(String url) {
    try {
      Desktop.getDesktop().browse(new URI(url));
    } catch (Exception e) {
      Alerts.error(e, "Can't open the URL " + url);
    }
  }
}
