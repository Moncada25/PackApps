package com.bookverse.packapps.utils;

import java.awt.Desktop;
import java.net.Socket;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import com.bookverse.packapps.utils.constants.Configs;
import com.bookverse.packapps.utils.ui.Alerts;

public final class GeneralUtils {

  private GeneralUtils() {
  }

  public static int getIntRandom(int min, int max) {
    return new SecureRandom().nextInt(max - min + 1) + min;
  }

  public static boolean loginDBA() {

    boolean canContinue = true;

    while (canContinue) {

      String password = Alerts.inputPassword("DBA's Password");

      if (!password.isEmpty()) {

        if (getConfig(Configs.PASSWORD_DBA).equals(password)) {
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
      Desktop.getDesktop().browse(URI.create(url));
    } catch (Exception e) {
      Alerts.error(e, "Can't open the URL " + url);
    }
  }

  public static void waitSeconds(int seconds) {
    LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(seconds * 1000L));
  }

  public static String getConfig(String key) {

    Properties properties = new Properties();

    try {
      properties.load(GeneralUtils.class.getResourceAsStream(Configs.FILE_PROPERTIES));
    } catch (Exception e) {
      Alerts.error(e, "Failure reading config properties file");
    }

    return properties.getProperty(key);
  }
}
