package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BORDER_BLUE;
import static com.bookverse.development.packapps.core.AppConfig.BORDER_RED;
import static com.bookverse.development.packapps.core.AppConfig.HAND;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.getBorder;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Resources;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

public class Numbers extends JDialog implements MouseListener {

  private JLabel btnPrimeNumbers, btnCalculator, btnPhi, btnPi, btnOther, welcome;

  private Resources resources = new Resources();

  public Numbers(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  @NotNull
  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBackground(new Color(0, 0, 0));
    panel.setBorder(getBorder("Select action"));

    btnPhi = resources.getLabel("  Phi φ  ", TEXT_COLOR, panel, MEDIUM);
    btnPhi.setBorder(BORDER_BLUE);
    btnPhi.addMouseListener(this);

    btnPi = resources.getLabel("  Pi π  ", TEXT_COLOR, panel, MEDIUM);
    btnPi.setBorder(BORDER_BLUE);
    btnPi.addMouseListener(this);

    btnCalculator = resources.getLabel("  Calculator  ", MAIN_COLOR, panel, MEDIUM);
    btnCalculator.setBorder(BORDER_RED);
    btnCalculator.addMouseListener(this);

    btnPrimeNumbers = resources.getLabel("  Primes  ", TEXT_COLOR, panel, MEDIUM);
    btnPrimeNumbers.setBorder(BORDER_BLUE);
    btnPrimeNumbers.addMouseListener(this);

    btnOther = resources.getLabel("  Others  ", TEXT_COLOR, panel, MEDIUM);
    btnOther.setBorder(BORDER_BLUE);
    btnOther.addMouseListener(this);

    return panel;
  }

  public void start(JFrame parent) {
    setSize(980, 570);
    setMinimumSize(new Dimension(480, 380));
    setMaximumSize(new Dimension(1295, 820));
    setLocationRelativeTo(parent);
    setTitle("Numbers");
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    add(getPanel(), BorderLayout.SOUTH);

    ImageIcon image = new ImageIcon(resources.getImage("math.jpg"));
    welcome = new JLabel();
    welcome.setIcon(image);
    welcome.setSize(1280, 720);
    welcome.addMouseListener(this);
    add(welcome, BorderLayout.CENTER);

    repaint();
  }

  @Override
  public void paint(Graphics g) {
    Dimension d = getSize();
    Dimension m = getMaximumSize();
    boolean resize = d.width > m.width || d.height > m.height;
    d.width = Math.min(m.width, d.width);
    d.height = Math.min(m.height, d.height);
    if (resize) {
      Point p = getLocation();
      setVisible(false);
      setSize(d);
      setLocation(p);
      setVisible(true);
    }
    super.paint(g);
  }

  @Override
  public void mouseClicked(@NotNull MouseEvent e) {

    if (e.getSource() == btnPrimeNumbers) {
      new PrimeNumber(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == btnCalculator) {
      new Calculator(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == btnPhi) {
      new Phi(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == btnPi) {
      new Pi(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == btnOther) {
      new OtherThings(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == welcome) {
      AppConfig.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(@NotNull MouseEvent e) {

    if (e.getSource() == btnPhi) {
      btnPhi.setCursor(HAND);
    } else if (e.getSource() == btnOther) {
      btnOther.setCursor(HAND);
    } else if (e.getSource() == btnCalculator) {
      btnCalculator.setCursor(HAND);
    } else if (e.getSource() == btnPi) {
      btnPi.setCursor(HAND);
    } else if (e.getSource() == btnPrimeNumbers) {
      btnPrimeNumbers.setCursor(HAND);
    }
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