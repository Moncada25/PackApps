package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.BIG;
import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.core.Settings.SMALL;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;

import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
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
import org.jetbrains.annotations.NotNull;

public class PrimeNumber extends JDialog implements Runnable, ActionListener, MouseListener {

  private Resources resources = new Resources();
  private JButton btnStartChronometer, btnSearchPrime, btnVerifyPrime;
  private JLabel title;
  private JLabel chronometer;
  private JTextField txtSince, txtUntil, txtVerify;
  private boolean chronometerActive;

  public PrimeNumber(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);

    btnStartChronometer = resources.getButton("Start", TEXT_COLOR, this, this);
    btnStartChronometer.setBounds(315, 195, 75, 25);

    JLabel lblSearchPrime = resources
        .getLabel("<html><strong>Search range</strong></html>", MAIN_COLOR, this, MEDIUM);
    lblSearchPrime.setBounds(25, 70, 120, 30);

    btnSearchPrime = resources.getButton("Show", TEXT_COLOR, this, this);
    btnSearchPrime.setBounds(315, 75, 75, 25);

    JLabel lblVerifyPrime = resources
        .getLabel("<html><strong>Verify</strong></html>", MAIN_COLOR, this, MEDIUM);
    lblVerifyPrime.setBounds(25, 130, 100, 30);

    txtVerify = new JTextField();
    txtVerify.setBounds(160, 135, 70, 25);
    txtVerify.setHorizontalAlignment(JTextField.CENTER);
    add(txtVerify);

    txtVerify.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtVerifyKeyPressed(e);
      }

      private void txtVerifyKeyPressed(@NotNull KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtVerify.getText().length() > 0) {
          verifyPrimeNumber(Integer.parseInt(txtVerify.getText()));
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txtVerifyKeyTyped(e);
      }

      private void txtVerifyKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtVerify.getText(), 6);
      }
    });

    btnVerifyPrime = resources.getButton("Show", TEXT_COLOR, this, this);
    btnVerifyPrime.setBounds(315, 135, 75, 25);

    txtSince = new JTextField();
    txtSince.setBounds(160, 75, 50, 25);
    txtSince.setHorizontalAlignment(JTextField.CENTER);
    add(txtSince);

    txtSince.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtSinceKeyPressed(e);
      }

      private void txtSinceKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txtSinceKeyTyped(e);
      }

      private void txtSinceKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtSince.getText(), 4);
      }
    });

    JLabel since = resources
        .getLabel("<html><strong>Since</strong></html>", TEXT_COLOR, this, SMALL);
    since.setBounds(160, 90, 60, 30);

    txtUntil = new JTextField();
    txtUntil.setBounds(220, 75, 50, 25);
    txtUntil.setHorizontalAlignment(JTextField.CENTER);
    add(txtUntil);

    txtUntil.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtUntilKeyPressed(e);
      }

      private void txtUntilKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txtSinceKeyTyped(e);
      }

      private void txtSinceKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtUntil.getText(), 4);
      }
    });

    JLabel until = resources
        .getLabel("<html><strong>Until</strong></html>", TEXT_COLOR, this, SMALL);
    until.setBounds(220, 90, 60, 30);

    JLabel btnChronometer = resources
        .getLabel("<html><strong>Chronometer</strong></html>", MAIN_COLOR, this, MEDIUM);
    btnChronometer.setBounds(25, 190, 130, 30);

    chronometer = resources.getLabel("00:00:000", TEXT_COLOR, this, MEDIUM);
    chronometer.setBounds(160, 190, 100, 30);

    title = resources
        .getLabel("<html><em><strong>Prime Numbers</strong></em></html>", MAIN_COLOR, this, BIG);
    title.addMouseListener(this);
    title.setBounds(110, 8, 280, 40);
  }

  private void verifyPrimeNumber(int num) {

    String divisors = "";
    int contGN = 1, contD = 0, cont = 0;

    while (contGN <= num) {

      if (num % contGN == 0) {
        contD++;
        cont++;
        divisors += "[" + contGN + "]";
      }

      if (cont == 10) {
        divisors += "\n";
        cont = 0;
      }

      contGN++;
    }

    if (contD == 2) {
      Alerts.message("Number of divisors:  " + contD,
          num + " is a prime number <br> Unique divisors: " + divisors);
    } else {
      Alerts.message("Number of divisors: " + contD,
          num + " it's not a prime number <br> Divisors: " + divisors);
    }
  }

  private void searchPrimeNumbers(int since, int until) {

    if (since <= until) {

      int sum = 0;
      String divisors = "";
      int cont = 0, quantity = 0;

      for (int i = since; i <= until; i++) {

        int contGN = 1, contD = 0;

        while (contGN <= i) {

          if (i % contGN == 0) {
            contD++;
          }

          contGN++;
        }

        if (contD == 2) {
          divisors += "[" + i + "]";
          sum += i;
          cont++;
          quantity++;

          if (cont == 30) {
            divisors += "\n";
            cont = 0;
          }
        }
      }
      Alerts.message("Quantity: " + quantity + " - Sum: " + sum, divisors);
    } else {
      Alerts.message("Verify!", "Illogical range");
    }
  }

  private void startChronometer() {

    if (chronometerActive) {
      stopChronometer();
      chronometerActive = false;
      btnStartChronometer.setText("Start");
    } else {
      btnStartChronometer.setText("Stop");
      chronometerActive = true;
      Thread thread = new Thread(this);
      thread.start();
    }
  }

  private void stopChronometer() {
    chronometerActive = false;
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 430, 270);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Prime Numbers");
    Settings.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void run() {
    int minutes = 0, seconds = 0, milesimas = 0;
    String min, seg, mil;

    try {

      while (chronometerActive) {
        Thread.sleep(4);
        milesimas += 4;

        if (milesimas == 1000) {
          milesimas = 0;
          seconds += 1;

          if (seconds == 60) {
            seconds = 0;
            minutes++;
          }
        }

        if (minutes < 10) {
          min = "0" + minutes;
        } else {
          min = Integer.toString(minutes);
        }

        if (seconds < 10) {
          seg = "0" + seconds;
        } else {
          seg = String.valueOf(seconds);
        }

        if (milesimas < 10) {
          mil = "00" + milesimas;
        } else if (milesimas < 100) {
          mil = "0" + milesimas;
        } else {
          mil = String.valueOf(milesimas);
        }

        chronometer.setText(min + ":" + seg + ":" + mil);
      }
    } catch (Exception e) {
      Alerts.error(e, "Prime Numbers");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnSearchPrime) {

      if (txtSince.getText().length() > 0 && txtUntil.getText().length() > 0) {
        searchPrimeNumbers(Integer.parseInt(txtSince.getText()), Integer.parseInt(
            txtUntil.getText()));
      } else {
        Alerts.inputSomethingText();
      }

    } else if (e.getSource() == btnVerifyPrime) {

      if (txtVerify.getText().length() > 0) {
        verifyPrimeNumber(Integer.parseInt(txtVerify.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    } else if (e.getSource() == btnStartChronometer) {
      startChronometer();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == title) {
      Settings.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }
}