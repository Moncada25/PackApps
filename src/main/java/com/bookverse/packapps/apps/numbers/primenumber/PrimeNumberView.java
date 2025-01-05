package com.bookverse.packapps.apps.numbers.primenumber;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import lombok.SneakyThrows;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Button;
import com.bookverse.packapps.utils.ui.factory.Label;

public class PrimeNumberView extends JDialog implements Runnable {

  private transient PrimeNumberService service = new PrimeNumberService();
  private JButton btnStartChronometer;
  private JLabel chronometer;
  private boolean chronometerActive;

  public PrimeNumberView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 430, 270);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Prime Numbers");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {
    setLayout(null);

    addLabel("Prime Numbers", Styles.MAIN_COLOR, Styles.BIG, 85, 8, 250, 40);
    addLabel("Search range", Styles.MAIN_COLOR, Styles.MEDIUM, 20, 70, 120, 30);

    JTextField txtSince = createTextField(160, 75, 50, 4);
    addLabel("Since", Styles.TEXT_COLOR, Styles.SMALL, 150, 93, 70, 30);

    JTextField txtUntil = createTextField(250, 75, 50, 4);
    addLabel("Until", Styles.TEXT_COLOR, Styles.SMALL, 240, 93, 70, 30);

    addButton(75, e -> {
      if (!txtSince.getText().isEmpty() && !txtUntil.getText().isEmpty()) {
        service.searchPrimeNumbers(Integer.parseInt(txtSince.getText()),
            Integer.parseInt(txtUntil.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    });

    addLabel("Verify", Styles.MAIN_COLOR, Styles.MEDIUM, 25, 130, 100, 30);

    JTextField txtVerify = createTextField(160, 135, 140, 6);
    addButton(135, e -> {
      if (!txtVerify.getText().isEmpty()) {
        service.verifyPrimeNumber(Integer.parseInt(txtVerify.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    });

    addLabel("Chronometer", Styles.MAIN_COLOR, Styles.MEDIUM, 20, 190, 130, 30);

    btnStartChronometer = new Button().setText("Start").setColor(Styles.TEXT_COLOR).build();
    btnStartChronometer.setBounds(315, 195, 75, 25);
    add(btnStartChronometer);
    btnStartChronometer.addActionListener(e -> startChronometer());

    chronometer = new Label().setText("00:00:000").setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM).build();
    chronometer.setBounds(160, 190, 100, 30);
    add(chronometer);
  }

  private void addLabel(String text, Color color, Font font, int x, int y, int width, int height) {
    JLabel label = new Label().setText(text).setColor(color).setFont(font).build();
    label.setBounds(x, y, width, height);
    add(label);
  }

  private JTextField createTextField(int x, int y, int width, int maxLength) {
    JTextField textField = new JTextField();
    textField.setBounds(x, y, width, 25);
    textField.setHorizontalAlignment(SwingConstants.CENTER);
    textField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, textField.getText(), maxLength);
      }
    });
    add(textField);
    return textField;
  }

  private void addButton(int y, ActionListener actionListener) {
    JButton button = new Button().setText("Show").setColor(Styles.TEXT_COLOR).build();
    button.setBounds(315, y, 75, 25);
    add(button);
    button.addActionListener(actionListener);
  }

  private void startChronometer() {
    chronometerActive = !chronometerActive;
    btnStartChronometer.setText(chronometerActive ? "Stop" : "Start");

    if (chronometerActive) {
      new Thread(this).start();
    }
  }

  @Override
  @SneakyThrows
  public void run() {
    long startTime = System.currentTimeMillis();
    int minutes;
    int seconds;
    int milliseconds;

    while (chronometerActive) {
      long currentTime = System.currentTimeMillis();
      long elapsedTime = currentTime - startTime;

      minutes = (int) (elapsedTime / 60000);
      seconds = (int) ((elapsedTime / 1000) % 60);
      milliseconds = (int) (elapsedTime % 1000);

      String min = String.format("%02d", minutes);
      String sec = String.format("%02d", seconds);
      String mil = String.format("%03d", milliseconds);

      chronometer.setText(min + ":" + sec + ":" + mil);

      LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10));
    }
  }
}