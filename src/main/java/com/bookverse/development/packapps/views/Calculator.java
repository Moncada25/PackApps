package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import org.jetbrains.annotations.NotNull;

public class Calculator extends JDialog implements ActionListener {

  private JButton[][] numbers;
  private JButton btnZero, btnPoint, btnAdd, btnLess, btnDivide, btnMultiply, btnNegative, btnRoot, btnEuler, btnPower, btnEqual, btnDelete, btnClean;
  private JTextField btnResult;
  private int sw = 1, s = 1;
  private double result;
  private boolean point = false;
  private String firstNumber, secondNumber, sign, content;

  private Resources resources = new Resources();

  public Calculator(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(resources.getImage("numeritos.png")).getImage());

    btnResult = new JTextField("");
    btnResult.setBounds(50, 14, 215, 43);
    btnResult.setHorizontalAlignment(JTextField.CENTER);
    add(btnResult);

    btnResult.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtResultKeyPressed(e);
      }

      private void txtResultKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ADD) {
          btnAddAP();
        } else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
          btnLessAP();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          inputEqual();
        } else if (e.getKeyCode() == KeyEvent.VK_DIVIDE) {
          btnDivideAP();
        } else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
          btnMultiplyAP();
        } else if (e.getKeyCode() == 110 || e.getKeyCode() == 46) {
          InputPoint();
        } else if (e.getKeyCode() == 8) {
          inputDelete();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txtResultKeyTyped(e);
      }

      private void txtResultKeyTyped(KeyEvent e) {
        Format.onlyNumberCalc(e.getKeyChar(), e, btnResult.getText(), 15);
        Format.onlyAPoint(e.getKeyChar(), e, btnResult.getText());
      }
    });

    numbers = new JButton[3][3];
    int x = 50;
    int y = 100;
    int n = 7;

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {
        numbers[f][c] = resources.getButton(btnResult.getText() + n, null, this, this);
        numbers[f][c].setBounds(x, y, 43, 43);
        n++;
        x = x + 43;
      }
      n -= 6;
      x = 50;
      y = y + 43;
    }

    btnZero = resources.getButton("0", null, this, this);
    btnZero.setBounds(50, 229, 86, 43);

    btnPoint = resources.getButton(".", null, this, this);
    btnPoint.setBounds(136, 229, 43, 43);

    btnAdd = resources.getButton("+", null, this, this);
    btnAdd.setBounds(179, 229, 43, 43);

    btnLess = resources.getButton("-", null, this, this);
    btnLess.setBounds(179, 186, 43, 43);

    btnMultiply = resources.getButton("*", null, this, this);
    btnMultiply.setBounds(179, 143, 43, 43);

    btnDivide = resources.getButton("/", null, this, this);
    btnDivide.setBounds(179, 100, 43, 43);

    btnEqual = resources.getButton("=", AppConfig.TEXT_COLOR, this, this);
    btnEqual.setBounds(222, 186, 43, 86);

    btnRoot = resources.getButton("?", null, this, this);
    btnRoot.setBounds(222, 143, 43, 43);

    btnPower = resources.getButton("^", null, this, this);
    btnPower.setBounds(222, 100, 43, 43);

    btnDelete = resources.getButton("?", AppConfig.MAIN_COLOR, this, this);
    btnDelete.setBounds(179, 57, 86, 43);

    btnClean = resources.getButton("C", null, this, this);
    btnClean.setBounds(136, 57, 43, 43);

    btnEuler = resources.getButton("e^", null, this, this);
    btnEuler.setBounds(93, 57, 43, 43);

    btnNegative = resources.getButton("±", null, this, this);
    btnNegative.setBounds(50, 57, 43, 43);
  }

  @NotNull
  private String operations(String firstNumber, String secondNumber, String sign) {

    double resultCalculator = 0.0;

    switch (sign) {
      case "+":
        resultCalculator = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
        break;
      case "-":
        resultCalculator = Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
        break;
      case "*":
        resultCalculator = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
        break;
      case "/":
        resultCalculator = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
        break;
      case "x^y":
        resultCalculator = Math
            .pow(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + sign);
    }

    return String.valueOf(resultCalculator);
  }

  private void btnZeroAP() {

    if (btnResult.getText().length() < 15) {
      btnResult.setText(btnResult.getText() + "0");
    }
  }

  private void btnPointAP() {

    content = btnResult.getText();

    if (btnResult.getText().length() < 15) {

      if (content.length() <= 0) {
        btnResult.setText("0.");
      } else if (!btnResult.getText().contains(".")) {
        btnResult.setText(btnResult.getText() + ".");
        point = false;
      }
    }
  }

  private void InputPoint() {

    content = btnResult.getText();

    if (content.length() <= 0 && !point) {
      btnResult.setText("0");
      point = true;
    } else if (btnResult.getText().contains(".")) {
      point = true;
    }
  }

  private void btnAddAP() {

    if (!btnResult.getText().equals("")) {
      firstNumber = btnResult.getText();
      sign = "+";
      btnResult.setText("");
    }
  }

  private void btnLessAP() {

    if (!btnResult.getText().equals("")) {
      firstNumber = btnResult.getText();
      sign = "-";
      btnResult.setText("");
    }
  }

  private void btnPowerAP() {

    try {

      if (!btnResult.getText().equals("")) {
        firstNumber = btnResult.getText();
        sign = "x^y";
        btnResult.setText("");
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void btnRootAP() {

    try {

      content = btnResult.getText();

      if (content.length() > 0) {
        result = Math.sqrt(Double.parseDouble(content));
        btnResult.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void btnCleanAP() {
    btnResult.setText("");
    point = false;
    result = 0;
    btnResult.requestFocus();
  }

  private void btnMultiplyAP() {

    if (!btnResult.getText().equals("")) {
      firstNumber = btnResult.getText();
      sign = "*";
      btnResult.setText("");
    }
  }

  private void btnDivideAP() {

    if (!btnResult.getText().equals("")) {
      firstNumber = btnResult.getText();
      sign = "/";
      btnResult.setText("");
    }
  }

  private void btnEulerAP() {

    try {

      content = btnResult.getText();

      if (content.length() > 0) {
        result = Math.exp(Double.parseDouble(content));
        btnResult.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void btnNegativeAP() {

    try {

      content = btnResult.getText();

      if (content.length() > 0) {
        result = (-1) * Double.parseDouble(content);
        btnResult.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void btnDeleteAP() {

    if (!btnResult.getText().equals("") && !btnResult.getText().equals("0")) {
      btnResult
          .setText(btnResult.getText().substring(0, btnResult.getText().length() - 1));
    } else {
      btnResult.setText("");
      point = false;
      result = 0;
      sw = 1;
      s = 1;
    }
  }

  private void inputDelete() {

    if (btnResult.getText().equals("") && !btnResult.getText().equals("0")) {
      btnResult.setText("");
      point = false;
      result = 0;
      sw = 1;
      s = 1;
    }
  }

  private void btnEqualAP() {

    try {

      String result;
      secondNumber = btnResult.getText();

      if (!secondNumber.equals("")) {
        result = operations(firstNumber, secondNumber, sign);
        btnResult.setText(result);
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void inputEqual() {

    try {

      String result;
      secondNumber = btnResult.getText().substring(1);

      if (!secondNumber.equals("")) {
        result = operations(firstNumber, secondNumber, sign);
        btnResult.setText(result);
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnZero) {
      btnZeroAP();
    } else if (e.getSource() == btnPoint) {
      btnPointAP();
    } else if (e.getSource() == btnDelete) {
      btnDeleteAP();
    } else if (e.getSource() == btnClean) {
      btnCleanAP();
    } else if (e.getSource() == btnNegative) {
      btnNegativeAP();
    } else if (e.getSource() == btnEuler) {
      btnEulerAP();
    } else if (e.getSource() == btnDivide) {
      btnDivideAP();
    } else if (e.getSource() == btnMultiply) {
      btnMultiplyAP();
    } else if (e.getSource() == btnLess) {
      btnLessAP();
    } else if (e.getSource() == btnAdd) {
      btnAddAP();
    } else if (e.getSource() == btnRoot) {
      btnRootAP();
    } else if (e.getSource() == btnPower) {
      btnPowerAP();
    } else if (e.getSource() == btnEqual) {
      btnEqualAP();
    }

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {

        if (btnResult.getText().length() >= 15) {
          break;
        }

        if (e.getSource() == numbers[f][c] && sw == 1 && s == 1) {
          btnResult.setText("");
          btnResult.setText(btnResult.getText() + numbers[f][c].getText());
          sw = 0;

        } else if (e.getSource() == numbers[f][c] && (sw == 0 || s == 0)) {
          btnResult.setText(btnResult.getText() + numbers[f][c].getText());
        }
      }
    }
  }
}