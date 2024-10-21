package com.bookverse.development.packapps.views.older;

import static com.bookverse.development.packapps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.utils.ui.Effects.fadeIn;

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Effects;
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

public class Pi extends JDialog implements ActionListener, MouseListener {

  
  private JButton btnCalculateFirst, btnCalculateSecond;
  private JLabel message;
  private JTextField txtFirst, txtSecond;

  public Pi(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 430, 280);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Pi π");
    fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);

    JLabel lblFirst = Resources
        .getLabel("<html><strong>First sum of series</strong></html>", MAIN_COLOR, this, MEDIUM);
    lblFirst.setBounds(25, 70, 280, 30);

    JLabel firstFormula = Resources
        .getLabel("π = 4/1 - 4/3 + 4/5 - 4/7 + 4/9...", TEXT_COLOR, this, MEDIUM);
    firstFormula.setBounds(25, 90, 280, 60);

    btnCalculateFirst = Resources.getButton("Show", TEXT_COLOR, this, this);
    btnCalculateFirst.setBounds(315, 110, 75, 25);

    txtFirst = new JTextField();
    txtFirst.setBounds(315, 75, 100, 25);
    txtFirst.setHorizontalAlignment(JTextField.CENTER);
    add(txtFirst);

    txtFirst.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt1KeyPressed(e);
      }

      private void txt1KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtFirst.getText().length() > 0) {
          firstCalculate(Double.parseDouble(txtFirst.getText()));
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
        Format.onlyNumbers(e.getKeyChar(), e, txtFirst.getText(), 9);
      }
    });

    JLabel lblSecond = Resources
        .getLabel("<html><strong>Second sum of series</strong></html>", MAIN_COLOR, this, MEDIUM);
    lblSecond.setBounds(25, 160, 280, 30);

    JLabel secondFormula = Resources
        .getLabel("π²/6 = 1/1² + 1/2² + 1/3² + 1/4²...", TEXT_COLOR, this, MEDIUM);
    secondFormula.setBounds(25, 180, 280, 60);

    btnCalculateSecond = Resources.getButton("Show", TEXT_COLOR, this, this);
    btnCalculateSecond.setBounds(315, 200, 75, 25);

    txtSecond = new JTextField();
    txtSecond.setBounds(315, 165, 100, 25);
    txtSecond.setHorizontalAlignment(JTextField.CENTER);
    add(txtSecond);

    txtSecond.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt2KeyPressed(e);
      }

      private void txt2KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtSecond.getText().length() > 0) {
          secondCalculate(Double.parseDouble(txtSecond.getText()));
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
        Format.onlyNumbers(e.getKeyChar(), e, txtSecond.getText(), 9);
      }
    });

    message = Resources
        .getLabel("<html><em><strong>Approach to π</strong></em></html>", MAIN_COLOR, this,
            BIG);
    message.addMouseListener(this);
    message.setBounds(120, 8, 250, 30);
  }

  private void secondCalculate(double numberElements) {

    double fra = 0, x = 4, n = 1, pi = 0, cont = 0;

    while (cont < numberElements) {
      fra = x / n;
      pi = pi + fra;
      x = x * (-1);
      n = n + 2;
      cont = cont + 1;
    }
    Alerts.message("Result", pi + "...");
  }

  private void firstCalculate(double numberElements) {

    double fra1 = 0, num = 1, den = 1, pi = 0, resul = 0, pot = 2, i;

    for (i = 1; i <= numberElements; i++) {

      fra1 = num / Math.pow(den, pot);
      resul = resul + fra1;
      den = den + 1;
    }

    pi = Math.sqrt(resul * 6);

    Alerts.message("Result", pi + "...");
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnCalculateFirst) {

      if (txtFirst.getText().length() > 0) {
        firstCalculate(Double.parseDouble(txtFirst.getText()));
      } else {
        Alerts.inputSomethingText();
      }

    } else if (e.getSource() == btnCalculateSecond) {

      if (txtSecond.getText().length() > 0) {
        secondCalculate(Double.parseDouble(txtSecond.getText()));
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