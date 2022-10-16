package com.bookverse.development.packapps.apps.views;

import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Resources;

import static com.bookverse.development.packapps.apps.utils.other.Format.isNumber;
import static com.bookverse.development.packapps.apps.utils.other.Format.onlyAPoint;
import static com.bookverse.development.packapps.apps.utils.other.Format.onlyNumberCalc;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnAdd;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnClean;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnDelete;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnDivide;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnEqual;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnEuler;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnLess;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnMultiply;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnNegative;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnPoint;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnPotency;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnRoot;
import static com.bookverse.development.packapps.apps.services.CalculatorService.clickOnZero;
import static com.bookverse.development.packapps.apps.services.CalculatorService.keyPoint;
import static com.bookverse.development.packapps.apps.services.CalculatorService.s;
import static com.bookverse.development.packapps.apps.services.CalculatorService.sw;

public class CalculatorView extends JDialog implements ActionListener {

  private Resources resources = new Resources();
  private JButton[][] numbers;
  private JButton btnZero, btnPoint, btnAdd, btnLess, btnDivide, btnMultiply, btnNegative, btnRoot, btnEuler, btnPower, btnEqual, btnDelete, btnClean;
  private JTextField txtResult;

  public CalculatorView(JDialog parent, boolean modal) {
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

    btnEqual = resources.getButton("=", Styles.TEXT_COLOR, this, this);
    btnEqual.setBounds(222, 186, 43, 86);

    btnRoot = resources.getButton("√", null, this, this);
    btnRoot.setBounds(222, 143, 43, 43);

    btnPower = resources.getButton("^", null, this, this);
    btnPower.setBounds(222, 100, 43, 43);

    btnDelete = resources.getButton("←", Styles.MAIN_COLOR, this, this);
    btnDelete.setBounds(179, 57, 86, 43);

    btnClean = resources.getButton("C", null, this, this);
    btnClean.setBounds(136, 57, 43, 43);

    btnEuler = resources.getButton("e^", null, this, this);
    btnEuler.setBounds(93, 57, 43, 43);

    btnNegative = resources.getButton("±", null, this, this);
    btnNegative.setBounds(50, 57, 43, 43);
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 320, 320);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Calculator");
    Effects.fadeIn(this);
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
