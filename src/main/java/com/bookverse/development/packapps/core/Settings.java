package com.bookverse.development.packapps.core;

import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.AppConfig;
import com.bookverse.development.packapps.utils.WindowEffect;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;

public class Settings {

  public static final Color TEXT_COLOR = new Color(21, 87, 163);
  public static final Color MAIN_COLOR = new Color(220, 12, 12);
  public static final Border BORDER_BLUE = BorderFactory.createLineBorder(TEXT_COLOR, 2, true);
  public static final Border BORDER_RED = BorderFactory.createLineBorder(MAIN_COLOR, 2, true);
  public static final Cursor POINT = new Cursor(Cursor.CROSSHAIR_CURSOR);
  public static final Cursor LOADER = new Cursor(Cursor.WAIT_CURSOR);
  public static final Cursor RESIZE = new Cursor(Cursor.NE_RESIZE_CURSOR);
  public static final Cursor TEXT = new Cursor(Cursor.TEXT_CURSOR);
  public static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);
  public static final Font SMALL = new Font("Cambria", Font.ITALIC, 15);
  public static final Font MEDIUM = new Font("Cambria", Font.ITALIC, 18);
  public static final Font BIG = new Font("Cambria", Font.ITALIC, 28);

  @NotNull
  public static String getDate() {

    Calendar date = new GregorianCalendar();
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

    return dateFormat.format(date.getTime()) + " - " + date.get(Calendar.HOUR_OF_DAY) + ":"
        + date.get(Calendar.MINUTE);
  }

  public static int getIntRandom(int min, int max) {
    return (int) Math.floor(Math.random() * (min - max + 1) + max);
  }

  public static String encrypt(String text, boolean isEmail) {

    String base64EncryptedString = "";

    try {

      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestOfPassword = md.digest(getSecretKey(isEmail).getBytes(StandardCharsets.UTF_8));
      byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

      SecretKey key = new SecretKeySpec(keyBytes, "DESede");
      Cipher cipher = Cipher.getInstance("DESede");
      cipher.init(Cipher.ENCRYPT_MODE, key);

      byte[] plainTextBytes = text.getBytes(StandardCharsets.UTF_8);
      byte[] buf = cipher.doFinal(plainTextBytes);
      byte[] base64Bytes = Base64.encodeBase64(buf);
      base64EncryptedString = new String(base64Bytes);

    } catch (Exception ex) {

    }
    return base64EncryptedString;
  }

  public static String decrypt(String text, boolean isEmail) {

    String base64EncryptedString = "";

    try {
      byte[] message = Base64.decodeBase64(text.getBytes(StandardCharsets.UTF_8));
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestOfPassword = md.digest(getSecretKey(isEmail).getBytes(StandardCharsets.UTF_8));
      byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
      SecretKey key = new SecretKeySpec(keyBytes, "DESede");

      Cipher decipher = Cipher.getInstance("DESede");
      decipher.init(Cipher.DECRYPT_MODE, key);

      byte[] plainText = decipher.doFinal(message);

      base64EncryptedString = new String(plainText, StandardCharsets.UTF_8);

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (base64EncryptedString.equals("")) {

      Alerts.message("Verify!",
          "The text entered does not have encrypted content or the key is incorrect");
      return text;
    } else {
      return base64EncryptedString;
    }
  }

  private static String setSecretKey() {

    boolean canContinue;
    String value;

    do {

      value = Alerts.inputPassword("Enter secret key");

      if (value.length() >= 2) {
        canContinue = true;
      } else {
        Alerts.message("Warnings", "Invalid text or length too short.");
        canContinue = false;
      }

    } while (!canContinue);

    return value;
  }

  public static boolean loginDBA() {

    boolean canContinue = true;

    while (canContinue) {

      String password = Alerts.inputPassword("DBA's Password");

      if (password.length() != 0) {

        if (AppConfig.PASSWORD_DBA.getProperty().equals(password)) {
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
        e.printStackTrace();
      }
    }
    return false;
  }

  @NotNull
  public static Border getBorder(String tittle) {

    TitledBorder border = BorderFactory.createTitledBorder(BORDER_BLUE, tittle);

    border.setTitleColor(MAIN_COLOR);
    border.setTitleFont(MEDIUM);
    border.setTitleJustification(TitledBorder.CENTER);

    return border;
  }

  private static String getSecretKey(boolean isSetSecretKey) {

    if (!isSetSecretKey) {
      return setSecretKey();
    } else {
      return AppConfig.DEFAULT_ENCRYPT_KEY.getProperty();
    }
  }

  public static void fadeIn(JDialog window) {
    WindowEffect.JDialogFadeIn(0f, 1f, 0.2f, 50, window);
  }

  public static void fadeIn(JFrame window) {
    WindowEffect.JFrameFadeIn(0f, 1f, 0.2f, 50, window);
  }

  public static void fadeOut(JFrame window) {
    WindowEffect.JFrameFadeOut(1f, 0f, 0.2f, 50, window, WindowEffect.EXIT);
  }

  public static void fadeOut(JDialog window) {
    WindowEffect.JDialogFadeOut(1f, 0f, 0.2f, 50, window, WindowEffect.DISPOSE);
  }
}