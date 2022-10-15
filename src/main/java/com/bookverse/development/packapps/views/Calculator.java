package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.Format.*;

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.utils.constants.Alerts;
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
  private JTextField txtResult;
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

    txtResult = new JTextField("");
    txtResult.setBounds(50, 14, 215, 43);
    txtResult.setHorizontalAlignment(JTextField.CENTER);
    add(txtResult);

    txtResult.addKeyListener(new KeyAdapter() {

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
          inputPoint();
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
        onlyNumberCalc(e.getKeyChar(), e, txtResult.getText(), 15);
        onlyAPoint(e.getKeyChar(), e, txtResult.getText());
      }
    });

    numbers = new JButton[3][3];
    int x = 50;
    int y = 100;
    int n = 7;

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {
        numbers[f][c] = resources.getButton(txtResult.getText() + n, null, this, this);
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

    btnEqual = resources.getButton("=", Settings.TEXT_COLOR, this, this);
    btnEqual.setBounds(222, 186, 43, 86);

    btnRoot = resources.getButton("√", null, this, this);
    btnRoot.setBounds(222, 143, 43, 43);

    btnPower = resources.getButton("^", null, this, this);
    btnPower.setBounds(222, 100, 43, 43);

    btnDelete = resources.getButton("←", Settings.MAIN_COLOR, this, this);
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

    if (txtResult.getText().length() < 15) {
      txtResult.setText(txtResult.getText() + "0");
    }
  }

  private void btnPointAP() {

    content = txtResult.getText();

    if (txtResult.getText().length() < 15) {

      if (content.length() <= 0) {
        txtResult.setText("0.");
      } else if (!txtResult.getText().contains(".")) {
        txtResult.setText(txtResult.getText() + ".");
        point = false;
      }
    }
  }

  private void inputPoint() {

    content = txtResult.getText();

    if (content.length() <= 0 && !point) {
      txtResult.setText("0");
      point = true;
    } else if (txtResult.getText().contains(".")) {
      point = true;
    }
  }

  private void btnAddAP() {

    if (!txtResult.getText().equals("")) {
      firstNumber = txtResult.getText();
      sign = "+";
      txtResult.setText("");
    }
  }

  private void btnLessAP() {

    if (!txtResult.getText().equals("")) {
      firstNumber = txtResult.getText();
      sign = "-";
      txtResult.setText("");
    }
  }

  private void btnPowerAP() {

    try {

      if (!txtResult.getText().equals("")) {
        firstNumber = txtResult.getText();
        sign = "x^y";
        txtResult.setText("");
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void btnRootAP() {

    try {

      content = txtResult.getText();

      if (content.length() > 0) {
        result = Math.sqrt(Double.parseDouble(content));
        txtResult.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void btnCleanAP() {
    txtResult.setText("");
    point = false;
    result = 0;
    txtResult.requestFocus();
  }

  private void btnMultiplyAP() {

    if (!txtResult.getText().equals("")) {
      firstNumber = txtResult.getText();
      sign = "*";
      txtResult.setText("");
    }
  }

  private void btnDivideAP() {

    if (!txtResult.getText().equals("")) {
      firstNumber = txtResult.getText();
      sign = "/";
      txtResult.setText("");
    }
  }

  private void btnEulerAP() {

    try {

      content = txtResult.getText();

      if (content.length() > 0) {
        result = Math.exp(Double.parseDouble(content));
        txtResult.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void btnNegativeAP() {

    try {

      content = txtResult.getText();

      if (content.length() > 0) {
        result = (-1) * Double.parseDouble(content);
        txtResult.setText(String.valueOf(result));
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void btnDeleteAP() {

    if (!txtResult.getText().equals("") && !txtResult.getText().equals("0")) {
      txtResult
          .setText(txtResult.getText().substring(0, txtResult.getText().length() - 1));
    } else {
      txtResult.setText("");
      point = false;
      result = 0;
      sw = 1;
      s = 1;
    }
  }

  private void inputDelete() {

    if (txtResult.getText().equals("") && !txtResult.getText().equals("0")) {
      txtResult.setText("");
      point = false;
      result = 0;
      sw = 1;
      s = 1;
    }
  }

  private void btnEqualAP() {

    try {

      String result;
      secondNumber = txtResult.getText();

      if (!secondNumber.equals("")) {
        result = operations(firstNumber, secondNumber, sign);
        txtResult.setText(result);
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  private void inputEqual() {

    try {

      String result;
      secondNumber = txtResult.getText().substring(1);

      if (!secondNumber.equals("")) {
        result = operations(firstNumber, secondNumber, sign);
        txtResult.setText(result);
      }

    } catch (Exception e) {
      Alerts.error(e, "Calculator");
    }
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 320, 320);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Estandar Calculator");
    Settings.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
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

        if (txtResult.getText().length() >= 15) {
          break;
        }

        if (!isNumber(txtResult.getText()) && !txtResult.getText().isEmpty()){
          Alerts.message("Number invalid", "Try again");
          txtResult.setText("");
          System.out.println("llegaaaaa");
        } else {

          if (e.getSource() == numbers[f][c] && sw == 1 && s == 1) {

            System.out.println("No llegaaaaa");
            txtResult.setText("");
            txtResult.setText(txtResult.getText() + numbers[f][c].getText());
            sw = 0;

          } else if (e.getSource() == numbers[f][c] && (sw == 0 || s == 0)) {
            txtResult.setText(txtResult.getText() + numbers[f][c].getText());
          }
        }



      }
    }
  }
}