package com.bookverse.development.packapps.apps.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.utils.constants.Alerts;
import com.bookverse.development.packapps.utils.ui.Resources;

import static com.bookverse.development.packapps.utils.Format.isNumber;
import static com.bookverse.development.packapps.utils.Format.onlyAPoint;
import static com.bookverse.development.packapps.utils.Format.onlyNumberCalc;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnAdd;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnClean;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnDelete;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnDivide;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnEqual;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnEuler;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnLess;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnMultiply;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnNegative;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnPoint;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnPotency;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnRoot;
import static com.bookverse.development.packapps.apps.calculator.Service.clickOnZero;
import static com.bookverse.development.packapps.apps.calculator.Service.keyPoint;
import static com.bookverse.development.packapps.apps.calculator.Service.s;
import static com.bookverse.development.packapps.apps.calculator.Service.sw;

public class View extends JDialog implements ActionListener {

  private final Resources R = new Resources();
  private JButton[][] numbers;
  private JButton btnZero, btnPoint, btnAdd, btnLess, btnDivide, btnMultiply, btnNegative, btnRoot, btnEuler, btnPower, btnEqual, btnDelete, btnClean;
  private JTextField txtResult;

  public View(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(R.getImage("numeritos.png")).getImage());

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
          clickOnAdd(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
          clickOnLess(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          clickOnEqual(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_DIVIDE) {
          clickOnDivide(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
          clickOnMultiply(txtResult);
        } else if (e.getKeyCode() == 110 || e.getKeyCode() == 46) {
          keyPoint(txtResult);
        } else if (e.getKeyCode() == 8) {
          clickOnDelete(txtResult);
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
        numbers[f][c] = R.getButton(txtResult.getText() + n, null, this, this);
        numbers[f][c].setBounds(x, y, 43, 43);
        n++;
        x = x + 43;
      }
      n -= 6;
      x = 50;
      y = y + 43;
    }

    btnZero = R.getButton("0", null, this, this);
    btnZero.setBounds(50, 229, 86, 43);

    btnPoint = R.getButton(".", null, this, this);
    btnPoint.setBounds(136, 229, 43, 43);

    btnAdd = R.getButton("+", null, this, this);
    btnAdd.setBounds(179, 229, 43, 43);

    btnLess = R.getButton("-", null, this, this);
    btnLess.setBounds(179, 186, 43, 43);

    btnMultiply = R.getButton("*", null, this, this);
    btnMultiply.setBounds(179, 143, 43, 43);

    btnDivide = R.getButton("/", null, this, this);
    btnDivide.setBounds(179, 100, 43, 43);

    btnEqual = R.getButton("=", Settings.TEXT_COLOR, this, this);
    btnEqual.setBounds(222, 186, 43, 86);

    btnRoot = R.getButton("√", null, this, this);
    btnRoot.setBounds(222, 143, 43, 43);

    btnPower = R.getButton("^", null, this, this);
    btnPower.setBounds(222, 100, 43, 43);

    btnDelete = R.getButton("←", Settings.MAIN_COLOR, this, this);
    btnDelete.setBounds(179, 57, 86, 43);

    btnClean = R.getButton("C", null, this, this);
    btnClean.setBounds(136, 57, 43, 43);

    btnEuler = R.getButton("e^", null, this, this);
    btnEuler.setBounds(93, 57, 43, 43);

    btnNegative = R.getButton("±", null, this, this);
    btnNegative.setBounds(50, 57, 43, 43);
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
      clickOnZero(btnZero);
    } else if (e.getSource() == btnPoint) {
      clickOnPoint(txtResult);
    } else if (e.getSource() == btnDelete) {
      clickOnDelete(txtResult);
    } else if (e.getSource() == btnClean) {
      clickOnClean(txtResult);
    } else if (e.getSource() == btnNegative) {
      clickOnNegative(txtResult);
    } else if (e.getSource() == btnEuler) {
      clickOnEuler(txtResult);
    } else if (e.getSource() == btnDivide) {
      clickOnDivide(txtResult);
    } else if (e.getSource() == btnMultiply) {
      clickOnMultiply(txtResult);
    } else if (e.getSource() == btnLess) {
      clickOnLess(txtResult);
    } else if (e.getSource() == btnAdd) {
      clickOnAdd(txtResult);
    } else if (e.getSource() == btnRoot) {
      clickOnRoot(txtResult);
    } else if (e.getSource() == btnPower) {
      clickOnPotency(txtResult);
    } else if (e.getSource() == btnEqual) {
      clickOnEqual(txtResult);
    }

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {

        if (txtResult.getText().length() >= 15) {
          break;
        }

        if (!isNumber(txtResult.getText()) && !txtResult.getText().isEmpty()){
          Alerts.message("Number invalid", "Try again");
          txtResult.setText("");
        } else {

          if (e.getSource() == numbers[f][c] && sw == 1 && s == 1) {

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
