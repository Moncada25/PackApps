package com.bookverse.development.packapps.apps.numbers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.bookverse.development.packapps.apps.numbers.calculator.CalculatorView;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.views.older.OtherThings;
import com.bookverse.development.packapps.apps.numbers.phi.PhiView;
import com.bookverse.development.packapps.views.older.Pi;
import com.bookverse.development.packapps.apps.numbers.primenumber.PrimeNumberView;
import com.bookverse.development.packapps.utils.ui.factory.Label;

public class NumbersView extends JDialog {

  public NumbersView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(1280, 750);
    setLocationRelativeTo(parent);
    setResizable(false);
    setTitle("Numbers");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    add(getPanel(), BorderLayout.SOUTH);

    ImageIcon image = new ImageIcon(Resources.getImage("math.jpg"));
    JLabel welcome = new Label().build();
    welcome.setIcon(image);
    welcome.setSize(1280, 720);
    welcome.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Effects.fadeOut(NumbersView.this);
      }
    });
    add(welcome, BorderLayout.CENTER);
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBackground(new Color(0, 0, 0));
    panel.setBorder(Resources.getBorder("Select action"));

    JLabel btnPhi = new Label().setText("  Phi φ  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    btnPhi.setBorder(Styles.BORDER_BLUE);
    btnPhi.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        new PhiView(NumbersView.this, true).start(NumbersView.this);
        setVisible(true);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnPhi.setCursor(Styles.HAND);
      }
    });
    panel.add(btnPhi);

    JLabel btnPrimeNumbers = new Label().setText("  Primes  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    btnPrimeNumbers.setBorder(Styles.BORDER_BLUE);
    btnPrimeNumbers.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        new PrimeNumberView(NumbersView.this, true).start(NumbersView.this);
        setVisible(true);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnPrimeNumbers.setCursor(Styles.HAND);
      }
    });
    panel.add(btnPrimeNumbers);

    JLabel btnCalculator = new Label().setText("  Calculator  ")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    btnCalculator.setBorder(Styles.BORDER_RED);
    btnCalculator.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        new CalculatorView(NumbersView.this, true).start(NumbersView.this);
        setVisible(true);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnCalculator.setCursor(Styles.HAND);
      }
    });
    panel.add(btnCalculator);

    JLabel btnOther = new Label().setText("  Hacks  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    btnOther.setBorder(Styles.BORDER_BLUE);
    btnOther.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        new OtherThings(NumbersView.this, true).start(NumbersView.this);
        setVisible(true);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnOther.setCursor(Styles.HAND);
      }
    });
    panel.add(btnOther);

    JLabel btnPi = new Label().setText("  Pi π  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    btnPi.setBorder(Styles.BORDER_BLUE);
    btnPi.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        new Pi(NumbersView.this, true).start(NumbersView.this);
        setVisible(true);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        btnPi.setCursor(Styles.HAND);
      }
    });
    panel.add(btnPi);

    return panel;
  }
}