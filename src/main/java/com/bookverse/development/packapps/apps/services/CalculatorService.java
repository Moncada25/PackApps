package com.bookverse.development.packapps.apps.services;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.bookverse.development.packapps.apps.utils.ui.Alerts;

public final class CalculatorService {

  private static double result;
  private static boolean point = false;
  private static String firstNumber;
  private static String sign;
  public static int sw = 1, s = 1;
  
  private static String operations(String firstNumber, String secondNumber, String sign) {

    double result;

    switch (sign) {
      case "+":
        result = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
        break;
      case "-":
        result = Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
        break;
      case "*":
        result = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
        break;
      case "/":
        result = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
        break;
      case "x^y":
        result = Math.pow(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));
        break;
      case "√":
        result = Math.sqrt(Double.parseDouble(firstNumber));
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + sign);
    }

    return String.valueOf(result);
  }

  public static void clickOnZero(JButton button) {

    if (button.getText().length() < 15) {
      button.setText(button.getText() + "0");
    }
  }

  public static void clickOnPoint(JTextField field) {
    
    if (field.getText().length() < 15) {

      if (field.getText().isEmpty()) {
        field.setText("0.");
      } else if (!field.getText().contains(".")) {
        field.setText(field.getText() + ".");
        point = false;
      }
    }
  }

  public static void keyPoint(JTextField field) {
    
    if (field.getText().isEmpty() && !point) {
      field.setText("0");
      point = true;
    } else if (field.getText().contains(".")) {
      point = true;
    }
  }

  public static void clickOnAdd(JTextField field) {

    if (!field.getText().equals("")) {
      firstNumber = field.getText();
      sign = "+";
      field.setText("");
    }
  }

  public static void clickOnLess(JTextField field) {

    if (!field.getText().equals("")) {
      firstNumber = field.getText();
      sign = "-";
      field.setText("");
    }
  }

  public static void clickOnPotency(JTextField field) {

    try {

      if (!field.getText().equals("")) {
        firstNumber = field.getText();
        sign = "x^y";
        field.setText("");
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  public static void clickOnRoot(JTextField field) {

    try {
      
      if (field.getText().length() > 0) {
        result = Double.parseDouble(operations(field.getText(), "0" , "√"));
        field.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  public static void clickOnClean(JTextField field) {
    field.setText("");
    point = false;
    result = 0;
    field.requestFocus();
  }

  public static void clickOnMultiply(JTextField field) {

    if (!field.getText().equals("")) {
      firstNumber = field.getText();
      sign = "*";
      field.setText("");
    }
  }

  public static void clickOnDivide(JTextField field) {

    if (!field.getText().equals("")) {
      firstNumber = field.getText();
      sign = "/";
      field.setText("");
    }
  }

  public static void clickOnEuler(JTextField field) {

    try {

      if (field.getText().length() > 0) {
        result = Math.exp(Double.parseDouble(field.getText()));
        field.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  public static void clickOnNegative(JTextField field) {

    try {

      if (field.getText().length() > 0) {
        result = (-1) * Double.parseDouble(field.getText());
        field.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  public static void clickOnDelete(JTextField field) {

    if (!field.getText().equals("") && !field.getText().equals("0")) {
      field.setText(field.getText().substring(0, field.getText().length() - 1));
    } else {
      field.setText("");
      point = false;
      result = 0;
      sw = 1;
      s = 1;
    }
  }

  public static void clickOnEqual(JTextField field) {

    try {

      String secondNumber = field.getText();

      if (!secondNumber.equals("")) {
        field.setText(operations(firstNumber, secondNumber, sign));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private CalculatorService(){
  }
}
