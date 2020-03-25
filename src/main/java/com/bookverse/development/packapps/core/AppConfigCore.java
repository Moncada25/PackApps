/*
Date 23/12/18
8748 view p = 336.46 : 26 class
2282 model p = 207.45 : 11 class
1229 controller p = 307.25 : 4 class
21 images : 1 class
12280 total  : 42 class
*/

/*
Date 23/01/19
11912 view p = 321.94 : 37 class
956 model p = 478 : 2 class
2773 controller p = 554.6 : 5 class
19 resources : 1 class ? 93 images
15660 total  : 45 class
*/

/*
Date 10/06/19
13430 view p = 344.35 : 39 class
954 model p = 477 : 2 class
1375 controller p = 1375 : 1 class
100 resources p = 33.33 : 3 class ? 108 images
15859 total  : 45 class
*/

package com.bookverse.development.packapps.core;

import com.bookverse.development.packapps.models.AppConfigModel;
import com.bookverse.development.packapps.utils.Format;
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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;

public class AppConfigCore {

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

      JOptionPane.showMessageDialog(null, "<html>" + Format.style()
              + "<strong>El texto introducido no tiene contenido encriptado o la llave es incorrecta.</strong></html>",
          "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

      return text;
    } else {
      return base64EncryptedString;
    }
  }

  private static String setSecretKey() {

    boolean canContinue;
    JPasswordField pass = new JPasswordField(10);
    String value;

    do {

      int action = JOptionPane.showConfirmDialog(null, pass, "Enter secret key",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.PLAIN_MESSAGE);

      value = new String(pass.getPassword());

      if (action >= 0 && value.length() >= 2) {
        canContinue = true;
      } else {
        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style()
                + "<strong>Invalid text or length too short.</strong></html>",
            "Warnings",
            JOptionPane.PLAIN_MESSAGE);
        canContinue = false;
      }

    } while (!canContinue);

    return value;
  }

  public static boolean loginDBA() {

    JPasswordField jPasswordField = new JPasswordField(10);

    boolean canContinue = true;

    while (canContinue) {

      int action = JOptionPane
          .showConfirmDialog(null, jPasswordField, "DBA's Password", JOptionPane.PLAIN_MESSAGE,
              JOptionPane.PLAIN_MESSAGE);

      if (action >= 0) {

        String password = new String(jPasswordField.getPassword());

        if (password.length() != 0) {

          if (AppConfigModel.getPasswordDBA().equals(password)) {
            return true;
          } else {
            JOptionPane.showMessageDialog(null,
                "<html>" + Format.style() + "<strong>Incorrect password</strong></html>",
                "Error", JOptionPane.PLAIN_MESSAGE);
            jPasswordField.setText("");
          }

        } else {
          canContinue = false;
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
        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong>" + request + "</strong></html>",
            "No internet connection", JOptionPane.PLAIN_MESSAGE);
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
      return AppConfigModel.getDefaultEncryptKey();
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