package com.bookverse.development.packapps.utils;

import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.bookverse.development.packapps.utils.ui.Alerts;

public class Format {

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
    if ((txt < 'a' || txt > 'z') && (txt < 'A' || txt > 'Z') && txt != KeyEvent.VK_ESCAPE
        && txt != KeyEvent.VK_ENTER && txt != KeyEvent.VK_DELETE && txt != ' '
        || data.length() > length - 1) {

      if (data.length() > length - 1 && txt != KeyEvent.VK_ENTER) {
        Alerts.inputLarge();
      } else if (txt == KeyEvent.VK_DELETE) {
        Alerts.invalidInput();
      }
      evt.consume();
    }
  }

  public static void anyone(char txt, KeyEvent evt, String data, int length) {

    if (data.length() > length - 1) {

      if (data.length() > length - 1 && txt != KeyEvent.VK_ENTER) {
        Alerts.inputLarge();
      }
      evt.consume();
    }
  }

  public static void onlyNumbers(char num, KeyEvent evt, String data, int length) {
    boolean match =
        (num < '0' || num > '9') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
            && num != KeyEvent.VK_DELETE && num != KeyEvent.VK_BACK_SPACE;

    if (match || data.length() > length - 1) {

      if (data.length() > length - 1 && num != KeyEvent.VK_ENTER) {
        Alerts.inputLarge();
      } else if (match) {
        Alerts.invalidInput();
      }
      evt.consume();
    }
  }

  public static void onlyAlfa(char txt, KeyEvent evt, String data, int length) {
    if ((txt < 'a' || txt > 'z') && (txt < 'A' || txt > 'Z') && (txt < '0' || txt > '9')
        && txt != KeyEvent.VK_ESCAPE && txt != KeyEvent.VK_ENTER && txt != KeyEvent.VK_DELETE
        && txt != ' ' || data.length() > length - 1) {

      if (data.length() > length - 1 && txt != KeyEvent.VK_ENTER) {
        Alerts.inputLarge();
      } else if ((txt < 'a' || txt > 'z') && (txt < '0' || txt > '9') && txt != KeyEvent.VK_ESCAPE
          && txt != KeyEvent.VK_ENTER && txt != KeyEvent.VK_DELETE && txt != KeyEvent.VK_BACK_SPACE) {
        Alerts.invalidInput();
      }
      evt.consume();
    }
  }

  public static void onlyNumbersAndPoint(char num, KeyEvent evt, String data, int length) {
    boolean match =
        (num < '0' || num > '9') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
            && num != KeyEvent.VK_DELETE && num != KeyEvent.VK_BACK_SPACE && num != '.';
    if (match || data.length() > length - 1) {

      if (data.length() > length - 1 && num != KeyEvent.VK_ENTER && num != KeyEvent.VK_ESCAPE) {
        Alerts.inputLarge();
      } else if (match) {
        Alerts.invalidInput();
      }
      evt.consume();
    }
  }

  public static void onlyNumberCalc(char num, KeyEvent evt, String data, int length) {
    if ((num < '0' || num > '9') && num != '+' && num != '-' && num != '*' && num != '/'
        && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER && num != KeyEvent.VK_DELETE
        && num != KeyEvent.VK_BACK_SPACE && (num != '.')
        || data.length() > length - 1) {

      if (data.length() > length - 1 && num != KeyEvent.VK_ENTER && num != KeyEvent.VK_ESCAPE) {
        Alerts.inputLarge();
      } else if ((num < '0' || num > '9') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
          && num != KeyEvent.VK_DELETE && num != KeyEvent.VK_BACK_SPACE && (num != '.') && num != '+' && num != '-' && num != '*' && num != '/') {
        Alerts.invalidInput();
      }
      evt.consume();
    }
  }

  public static void middlePoint(char num, KeyEvent evt, String data) {

    if (num == '.' && (data.contains(".") || data.equals("") || data.length() > 1)) {
      evt.consume();
    }
  }

  public static void onlyBinary(char num, KeyEvent evt, String data) {
    if ((num < '0' || num > '1') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
        && num != KeyEvent.VK_DELETE && num != KeyEvent.VK_BACK_SPACE || data.length() > 8) {

      if (data.length() > 8 && num != KeyEvent.VK_ENTER) {
        Alerts.inputLarge();
      } else if (num != KeyEvent.VK_ENTER) {
        Alerts.invalidInput();
      }
      evt.consume();
    }
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

  public static boolean isNumber(String text){

    try {
      int number = Integer.parseInt(text);
      return true;
    }catch (NumberFormatException e){
      return false;
    }
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
}