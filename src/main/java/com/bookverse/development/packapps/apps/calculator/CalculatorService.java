package com.bookverse.development.packapps.apps.calculator;

import javax.swing.JTextField;
import lombok.Data;
import com.bookverse.development.packapps.utils.ui.Alerts;

@Data
public class CalculatorService {

  private double result;
  private boolean point = false;
  private String firstNumber;
  private String sign;
  private int sw = 1;
  private int s = 1;

  private static String operations(String firstNumber, String secondNumber, String sign) {

    double result = switch (sign) {
      case "+" -> Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
      case "-" -> Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
      case "*" -> Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
      case "/" -> Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
      case "x^y" -> Math.pow(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));
      case "√" -> Math.sqrt(Double.parseDouble(firstNumber));
      default -> throw new IllegalStateException("Unexpected value: " + sign);
    };

    return String.valueOf(result);
  }

  public void clickOnZero(CalculatorViewModel model) {

    if (model.getTxtResult().getText().length() < 15) {
      model.getTxtResult().setText(model.getTxtResult().getText() + "0");
    }
  }

  public void clickOnPoint(JTextField field) {
    
    if (field.getText().length() < 15) {

      if (field.getText().isEmpty()) {
        field.setText("0.");
      } else if (!field.getText().contains(".")) {
        field.setText(field.getText() + ".");
        point = false;
      }
    }
  }

  public void keyPoint(JTextField field) {
    
    if (field.getText().isEmpty() && !point) {
      field.setText("0");
      point = true;
    } else if (field.getText().contains(".")) {
      point = true;
    }
  }

  public void clickOnAdd(JTextField field) {

    if (!field.getText().isEmpty()) {
      firstNumber = field.getText();
      sign = "+";
      field.setText("");
    }
  }

  public void clickOnLess(JTextField field) {

    if (!field.getText().isEmpty()) {
      firstNumber = field.getText();
      sign = "-";
      field.setText("");
    }
  }

  public void clickOnPotency(JTextField field) {

    try {

      if (!field.getText().isEmpty()) {
        firstNumber = field.getText();
        sign = "x^y";
        field.setText("");
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  public void clickOnRoot(JTextField field) {

    try {
      
      if (!field.getText().isEmpty()) {
        result = Double.parseDouble(operations(field.getText(), "0" , "√"));
        field.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  public void clickOnClean(JTextField field) {
    field.setText("");
    point = false;
    result = 0;
    field.requestFocus();
  }

  public void clickOnMultiply(JTextField field) {

    if (!field.getText().isEmpty()) {
      firstNumber = field.getText();
      sign = "*";
      field.setText("");
    }
  }

  public void clickOnDivide(JTextField field) {

    if (!field.getText().isEmpty()) {
      firstNumber = field.getText();
      sign = "/";
      field.setText("");
    }
  }

  public void clickOnEuler(JTextField field) {

    try {

      if (!field.getText().isEmpty()) {
        result = Math.exp(Double.parseDouble(field.getText()));
        field.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  public void clickOnNegative(JTextField field) {

    try {

      if (!field.getText().isEmpty()) {
        result = (-1) * Double.parseDouble(field.getText());
        field.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  public void clickOnDelete(JTextField field) {

    if (!field.getText().isEmpty() && !field.getText().equals("0")) {
      field.setText(field.getText().substring(0, field.getText().length() - 1));
    } else {
      field.setText("");
      point = false;
      result = 0;
      sw = 1;
      s = 1;
    }
  }

  public void clickOnEqual(JTextField field) {

    try {

      String secondNumber = field.getText();

      if (!secondNumber.isEmpty()) {
        field.setText(operations(firstNumber, secondNumber, sign));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }
}
