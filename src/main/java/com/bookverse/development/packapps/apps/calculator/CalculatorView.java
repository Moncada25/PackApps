package com.bookverse.development.packapps.apps.calculator;

import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.factory.Button;

public class CalculatorView extends JDialog {

  private transient CalculatorService service = new CalculatorService();
  private transient CalculatorViewModel model = new CalculatorViewModel();

  public CalculatorView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(Resources.getImage("numeritos.png")).getImage());

    JTextField txtResult = new JTextField("");
    txtResult.setBounds(50, 14, 215, 43);
    txtResult.setHorizontalAlignment(SwingConstants.CENTER);
    add(txtResult);
    model.setTxtResult(txtResult);

    txtResult.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        txtResultKeyPressed(e);
      }

      private void txtResultKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ADD) {
          service.clickOnAdd(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
          service.clickOnLess(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          service.clickOnEqual(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_DIVIDE) {
          service.clickOnDivide(txtResult);
        } else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
          service.clickOnMultiply(txtResult);
        } else if (e.getKeyCode() == 110 || e.getKeyCode() == 46) {
          service.keyPoint(txtResult);
        } else if (e.getKeyCode() == 8) {
          service.clickOnDelete(txtResult);
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

    createNumbers();

    JButton btnZero = new Button().setText("0").build();
    btnZero.setBounds(50, 229, 86, 43);
    add(btnZero);
    btnZero.addActionListener(e -> service.clickOnZero(model));

    JButton btnPoint = new Button().setText(".").build();
    btnPoint.setBounds(136, 229, 43, 43);
    add(btnPoint);
    btnPoint.addActionListener(e -> service.clickOnPoint(txtResult));

    JButton btnAdd = new Button().setText("+").build();
    btnAdd.setBounds(179, 229, 43, 43);
    add(btnAdd);
    btnAdd.addActionListener(e -> service.clickOnAdd(txtResult));

    JButton btnLess = new Button().setText("-").build();
    btnLess.setBounds(179, 186, 43, 43);
    add(btnLess);
    btnLess.addActionListener(e -> service.clickOnLess(txtResult));

    JButton btnMultiply = new Button().setText("*").build();
    btnMultiply.setBounds(179, 143, 43, 43);
    add(btnMultiply);
    btnMultiply.addActionListener(e -> service.clickOnMultiply(txtResult));

    JButton btnDivide = new Button().setText("/").build();
    btnDivide.setBounds(179, 100, 43, 43);
    add(btnDivide);
    btnDivide.addActionListener(e -> service.clickOnDivide(txtResult));

    JButton btnEqual = new Button().setText("=").setColor(Styles.TEXT_COLOR).build();
    btnEqual.setBounds(222, 186, 43, 86);
    add(btnEqual);
    btnEqual.addActionListener(e -> service.clickOnEqual(txtResult));

    JButton btnRoot = new Button().setText("√").build();
    btnRoot.setBounds(222, 143, 43, 43);
    add(btnRoot);
    btnRoot.addActionListener(e -> service.clickOnRoot(txtResult));

    JButton btnPower = new Button().setText("^").build();
    btnPower.setBounds(222, 100, 43, 43);
    add(btnPower);
    btnPower.addActionListener(e -> service.clickOnPotency(txtResult));

    JButton btnDelete = new Button().setText("←").setColor(Styles.MAIN_COLOR).build();
    btnDelete.setBounds(179, 57, 86, 43);
    add(btnDelete);
    btnDelete.addActionListener(e -> service.clickOnDelete(txtResult));

    JButton btnClean = new Button().setText("C").build();
    btnClean.setBounds(136, 57, 43, 43);
    add(btnClean);
    btnClean.addActionListener(e -> service.clickOnClean(txtResult));

    JButton btnEuler = new Button().setText("e^").build();
    btnEuler.setBounds(93, 57, 43, 43);
    add(btnEuler);
    btnEuler.addActionListener(e -> service.clickOnEuler(txtResult));

    JButton btnNegative = new Button().setText("±").build();
    btnNegative.setBounds(50, 57, 43, 43);
    add(btnNegative);
    btnNegative.addActionListener(e -> service.clickOnNegative(txtResult));
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

  private void createNumbers() {

    JButton[][] numbers = new JButton[3][3];

    int x = 50;
    int y = 100;
    int n = 7;

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {
        numbers[f][c] = new Button().setText(String.valueOf(n)).build();
        numbers[f][c].setBounds(x, y, 43, 43);
        add(numbers[f][c]);

        int finalF = f;
        int finalC = c;

        numbers[f][c].addActionListener(e -> {
          if (model.getTxtResult().getText().length() < 15) {

            if (service.getSw() == 1 && service.getS() == 1) {

              model.getTxtResult().setText("");
              model.getTxtResult().setText(model.getTxtResult().getText() + numbers[finalF][finalC].getText());
              service.setSw(0);

            } else if (service.getSw() == 0 || service.getS() == 0) {
              model.getTxtResult().setText(model.getTxtResult().getText() + numbers[finalF][finalC].getText());
            }
          }
        });
        n++;
        x = x + 43;
      }
      n -= 6;
      x = 50;
      y = y + 43;
    }

    model.setNumbers(numbers);
  }
}
