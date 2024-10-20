package com.bookverse.development.packapps.apps.utils.other;

import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;

public final class Format {

  @NotNull
  public static String tableName(@NotNull String table) {
    return table.replaceAll("\\s", "").toLowerCase();
  }

  public static void onlyAPoint(char num, KeyEvent evt, String data) {
    if (num == '.' && data.contains(".")) {
      evt.consume();
    }
  }

  public static void onlyText(char txt, KeyEvent evt, String data, int length) {
    boolean isInvalid = !isLetterOrSpecial(txt) || data.length() >= length;

    if (isInvalid) {
      handleInvalidInput(data, length, txt, evt);
    }
  }

  public static void onlyNumbers(char num, KeyEvent evt, String data, int length) {
    boolean isInvalid = !isDigit(num) || data.length() >= length;

    if (isInvalid) {
      handleInvalidInput(data, length, num, evt);
    }
  }

  public static void onlyAlfa(char txt, KeyEvent evt, String data, int length) {
    boolean isInvalid = !isLetterOrDigit(txt) || data.length() >= length;

    if (isInvalid) {
      handleInvalidInput(data, length, txt, evt);
    }
  }

  public static void onlyNumbersAndPoint(char num, KeyEvent evt, String data, int length) {
    boolean isInvalid = !isDigit(num) && num != '.' || data.length() >= length;

    if (isInvalid) {
      handleInvalidInput(data, length, num, evt);
    }
  }

  public static void onlyNumberCalc(char num, KeyEvent evt, String data, int length) {
    boolean isInvalid = !isCalculatorInput(num) || data.length() >= length;

    if (isInvalid) {
      handleInvalidInput(data, length, num, evt);
    }
  }

  public static void middlePoint(char num, KeyEvent evt, String data) {
    if (num == '.' && (data.contains(".") || data.isEmpty() || data.length() > 1)) {
      evt.consume();
    }
  }

  public static void onlyBinary(char num, KeyEvent evt, String data) {
    boolean isInvalid = !isBinary(num) || data.length() > 8;

    if (isInvalid) {
      handleInvalidInput(data, 8, num, evt);
    }
  }

  private static void handleInvalidInput(String data, int length, char input, KeyEvent evt) {
    if (data.length() >= length && input != KeyEvent.VK_ENTER) {
      Alerts.inputLarge();
    } else if (!isAllowedKey(input)) {
      Alerts.invalidInput();
    }
    evt.consume();
  }

  private static boolean isAllowedKey(char key) {
    return key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_DELETE || key == KeyEvent.VK_BACK_SPACE;
  }

  private static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  private static boolean isLetter(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == 'ñ' || c == 'Ñ' || c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú' || c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú';
  }

  private static boolean isLetterOrDigit(char c) {
    return isLetter(c) || isDigit(c);
  }

  private static boolean isLetterOrSpecial(char c) {
    return isLetter(c) || c == ' ';
  }

  private static boolean isCalculatorInput(char c) {
    return isDigit(c) || c == '.' || c == '+' || c == '-' || c == '*' || c == '/';
  }

  private static boolean isBinary(char c) {
    return c >= '0' && c <= '1';
  }

  @NotNull
  @Contract(pure = true)
  public static String style() {
    return
        "<style type='text/css'>" +
            "	strong {" +
            " 		color:rgb(21, 87, 163);" +
            " 	} "
            + "center{" +
            "text-decoration: underline"
            + "}" +
            "</style>";
  }

  public static boolean verifyDocument(String document) {
    return document.length() < 8 || document.length() > 10;
  }

  public static boolean verifyCredentials(String credential) {
    return credential.length() >= 4 && credential.length() <= 15;
  }

  public static boolean verifyReference(@NotNull String reference) {
    return reference.length() < 5 || reference.length() > 15;
  }

  public static boolean verifyPhone(String phone) {
    return phone.length() != 10 && phone.length() != 7;
  }

  public static boolean verifyPrice(double price) {
    return !(price > 10);
  }

  @NotNull
  public static String getDate() {

    Calendar date = new GregorianCalendar();
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

    return dateFormat.format(date.getTime()) + " - " + date.get(Calendar.HOUR_OF_DAY) + ":"
        + date.get(Calendar.MINUTE);
  }

  public static String getNow() {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"));
  }

  private Format() {
    throw new IllegalStateException("Utility class");
  }
}