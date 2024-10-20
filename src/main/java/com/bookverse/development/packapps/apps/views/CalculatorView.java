package com.bookverse.development.packapps.apps.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.apps.services.CalculatorService;
import com.bookverse.development.packapps.utils.other.Format;
import javax.swing.SwingConstants;

public class CalculatorView extends JDialog implements ActionListener {

  private JButton[][] numbers;
  private JButton btnZero;
  private JButton btnPoint;
  private JButton btnAdd;
  private JButton btnLess;
  private JButton btnDivide;
  private JButton btnMultiply;
  private JButton btnNegative;
  private JButton btnRoot;
  private JButton btnEuler;
  private JButton btnPower;
  private JButton btnEqual;
  private JButton btnDelete;
  private JButton btnClean;
  private JTextField txtResult;

  public CalculatorView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(Resources.getImage("numeritos.png")).getImage());

    txtResult = new JTextField("");
    txtResult.setBounds(50, 14, 215, 43);
    txtResult.setHorizontalAlignment(SwingConstants.CENTER);
    add(txtResult);

    txtResult.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        txtResultKeyPressed(e);
      }

      private void txtResultKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ADD) {
          CalculatorService.clickOnAdd(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
          CalculatorService.clickOnLess(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          CalculatorService.clickOnEqual(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_DIVIDE) {
          CalculatorService.clickOnDivide(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
          CalculatorService.clickOnMultiply(txtResult);
        } else if (e.getKeyCode() == 110 || e.getKeyCode() == 46) {
          CalculatorService.keyPoint(txtResult);
        } else if (e.getKeyCode() == 8) {
          CalculatorService.clickOnDelete(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
        Format.onlyNumbersAndPoint(e.getKeyChar(), e, txtResult.getText(), 15);
        txtResultKeyTyped(e);
      }

      private void txtResultKeyTyped(KeyEvent e) {
        Format.onlyNumberCalc(e.getKeyChar(), e, txtResult.getText(), 15);
        Format.onlyAPoint(e.getKeyChar(), e, txtResult.getText());
      }
    });

    numbers = new JButton[3][3];
    int x = 50;
    int y = 100;
    int n = 7;

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {
        numbers[f][c] = Resources.getButton(txtResult.getText() + n, null, this, this);
        numbers[f][c].setBounds(x, y, 43, 43);
        n++;
        x = x + 43;
      }
      n -= 6;
      x = 50;
      y = y + 43;
    }

    btnZero = Resources.getButton("0", null, this, this);
    btnZero.setBounds(50, 229, 86, 43);

    btnPoint = Resources.getButton(".", null, this, this);
    btnPoint.setBounds(136, 229, 43, 43);

    btnAdd = Resources.getButton("+", null, this, this);
    btnAdd.setBounds(179, 229, 43, 43);

    btnLess = Resources.getButton("-", null, this, this);
    btnLess.setBounds(179, 186, 43, 43);

    btnMultiply = Resources.getButton("*", null, this, this);
    btnMultiply.setBounds(179, 143, 43, 43);

    btnDivide = Resources.getButton("/", null, this, this);
    btnDivide.setBounds(179, 100, 43, 43);

    btnEqual = Resources.getButton("=", Styles.TEXT_COLOR, this, this);
    btnEqual.setBounds(222, 186, 43, 86);

    btnRoot = Resources.getButton("√", null, this, this);
    btnRoot.setBounds(222, 143, 43, 43);

    btnPower = Resources.getButton("^", null, this, this);
    btnPower.setBounds(222, 100, 43, 43);

    btnDelete = Resources.getButton("←", Styles.MAIN_COLOR, this, this);
    btnDelete.setBounds(179, 57, 86, 43);

    btnClean = Resources.getButton("C", null, this, this);
    btnClean.setBounds(136, 57, 43, 43);

    btnEuler = Resources.getButton("e^", null, this, this);
    btnEuler.setBounds(93, 57, 43, 43);

    btnNegative = Resources.getButton("±", null, this, this);
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
      CalculatorService.clickOnZero(btnZero);
    } else if (e.getSource() == btnPoint) {
      CalculatorService.clickOnPoint(txtResult);
    } else if (e.getSource() == btnDelete) {
      CalculatorService.clickOnDelete(txtResult);
    } else if (e.getSource() == btnClean) {
      CalculatorService.clickOnClean(txtResult);
    } else if (e.getSource() == btnNegative) {
      CalculatorService.clickOnNegative(txtResult);
    } else if (e.getSource() == btnEuler) {
      CalculatorService.clickOnEuler(txtResult);
    } else if (e.getSource() == btnDivide) {
      CalculatorService.clickOnDivide(txtResult);
    } else if (e.getSource() == btnMultiply) {
      CalculatorService.clickOnMultiply(txtResult);
    } else if (e.getSource() == btnLess) {
      CalculatorService.clickOnLess(txtResult);
    } else if (e.getSource() == btnAdd) {
      CalculatorService.clickOnAdd(txtResult);
    } else if (e.getSource() == btnRoot) {
      CalculatorService.clickOnRoot(txtResult);
    } else if (e.getSource() == btnPower) {
      CalculatorService.clickOnPotency(txtResult);
    } else if (e.getSource() == btnEqual) {
      CalculatorService.clickOnEqual(txtResult);
    }

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {

        if (txtResult.getText().length() >= 15) {
          break;
        }

        if (e.getSource() == numbers[f][c] && CalculatorService.sw == 1 && CalculatorService.s == 1) {

          txtResult.setText("");
          txtResult.setText(txtResult.getText() + numbers[f][c].getText());
          CalculatorService.sw = 0;

        } else if (e.getSource() == numbers[f][c] && (
            CalculatorService.sw == 0 || CalculatorService.s == 0)) {
          txtResult.setText(txtResult.getText() + numbers[f][c].getText());
        }
      }
    }
  }
}
