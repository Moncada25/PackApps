package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;

import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Phi extends JDialog implements ActionListener, MouseListener {

  
  private JButton btnCalculateFirst, btnCalculateSecond, btnProportion;
  private JLabel message;
  private JLabel value;
  private JTextField txtFirst, txtSecond, txtProportion;
  private double phi;

  public Phi(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);

    JLabel lblFirst = Resources
        .getLabel("<html><strong>Fibonacci's series</strong></html>", MAIN_COLOR, this, MEDIUM);
    lblFirst.setBounds(25, 70, 260, 30);

    JLabel firstFormula = Resources
        .getLabel("<html><strong>Nth Fibonacci number</strong></html>", MAIN_COLOR, this, MEDIUM);
    firstFormula.setBounds(25, 120, 260, 30);

    btnCalculateFirst = Resources.getButton("Show", TEXT_COLOR, this, this);
    btnCalculateFirst.setBounds(360, 75, 70, 25);

    txtFirst = new JTextField();
    txtFirst.setBounds(295, 75, 50, 25);
    txtFirst.setHorizontalAlignment(JTextField.CENTER);
    add(txtFirst);

    txtFirst.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt1KeyPressed(e);
      }

      private void txt1KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtFirst.getText().length() > 0) {
          generateFibonacci(Integer.parseInt(txtFirst.getText()));
          value.setText(phi + " ...");
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txt1KeyTyped(e);
      }

      private void txt1KeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtFirst.getText(), 2);
      }
    });

    JLabel lblSecond = Resources
        .getLabel("<html><strong>Approximate value</strong></html>", MAIN_COLOR, this, MEDIUM);
    lblSecond.setBounds(25, 220, 260, 30);

    value = Resources.getLabel("", TEXT_COLOR, this, MEDIUM);
    value.setBounds(200, 220, 280, 30);

    JLabel secondFormula = Resources
        .getLabel("<html><strong>Golden ratio</strong></html>", MAIN_COLOR, this, MEDIUM);
    secondFormula.setBounds(25, 170, 280, 30);

    btnProportion = Resources.getButton("Show", TEXT_COLOR, this, this);
    btnProportion.setBounds(360, 175, 70, 25);

    btnCalculateSecond = Resources.getButton("Show", TEXT_COLOR, this, this);
    btnCalculateSecond.setBounds(360, 125, 70, 25);

    txtSecond = new JTextField();
    txtSecond.setBounds(295, 125, 50, 25);
    txtSecond.setHorizontalAlignment(JTextField.CENTER);
    add(txtSecond);

    txtSecond.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt2KeyPressed(e);
      }

      private void txt2KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtSecond.getText().length() > 0) {
          getFibonacci(Integer.parseInt(txtSecond.getText()));
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txt2KeyTyped(e);
      }

      private void txt2KeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtSecond.getText(), 2);
      }
    });

    txtProportion = new JTextField();
    txtProportion.setBounds(295, 175, 50, 25);
    txtProportion.setHorizontalAlignment(JTextField.CENTER);
    add(txtProportion);

    txtProportion.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt3KeyPressed(e);
      }

      private void txt3KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtProportion.getText().length() > 0) {
          proportionAurea(Double.parseDouble(txtProportion.getText()));
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txt3KeyTyped(e);
      }

      private void txt3KeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtProportion.getText(), 4);
      }
    });

    message = Resources
        .getLabel("<html><em><strong>Approach to φ</strong></em></html>", MAIN_COLOR, this,
            BIG);
    message.addMouseListener(this);
    message.setBounds(120, 8, 250, 30);
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 440, 300);
    setBounds(0, 0, 440, 300);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Phi φ");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void generateFibonacci(int lim) {

    String numbers = "";

    double num1 = 0, num2 = 1, f = 0;

    for (int i = 0; i < lim; i++) {

      if (i % 10 == 0 && i != 0 && i != 1 && i != 2) {
        numbers += "\n";
      }

      f = num1 + num2;
      num2 = num1;
      num1 = f;

      numbers += "[" + String.format("%.0f", f) + "]";
    }

    if(num2 != 0)
      phi = num1 / num2;

    Alerts.message("Result", numbers);
  }

  private void getFibonacci(int n)  {

    double fi, number;

    fi = (1 + Math.sqrt(5)) / 2;

    number = (1 / Math.sqrt(5)) * (Math.pow(fi, n) - (Math.pow(-1 / fi, n)));
    Alerts.message("Result",
        "N° " + n + " in the Fibonacci's series is " + String.format("%.0f", number));
  }

  private void proportionAurea(double length) {

    double a, b;

    a = length / ((1 + Math.sqrt(5)) / 2);
    b = length - a;
    Alerts.message("Result",
        "Portion A: " + String.format("%.2f", a) + " <br>Portion B: " + String.format("%.2f", b));
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnCalculateFirst) {

      if (txtFirst.getText().length() > 0) {
        generateFibonacci(Integer.parseInt(txtFirst.getText()));
        value.setText(phi + "...");
      } else {
        Alerts.inputSomethingText();
      }

    } else if (e.getSource() == btnCalculateSecond) {

      if (txtSecond.getText().length() > 0) {
        getFibonacci(Integer.parseInt(txtSecond.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    } else if (e.getSource() == btnProportion) {

      if (txtProportion.getText().length() > 0) {
        proportionAurea(Double.parseDouble(txtProportion.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == message) {
      Effects.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {

  }

  @Override
  public void mouseExited(MouseEvent arg0) {

  }

  @Override
  public void mousePressed(MouseEvent arg0) {

  }

  @Override
  public void mouseReleased(MouseEvent arg0) {

  }
}