package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.BORDER_BLUE;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.BORDER_RED;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.HAND;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.apps.utils.ui.Resources.getBorder;

import com.bookverse.development.packapps.apps.views.CalculatorView;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
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

  

  public Numbers(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  @NotNull
  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBackground(new Color(0, 0, 0));
    panel.setBorder(getBorder("Select action"));

    btnPhi = Resources.getLabel("  Phi φ  ", TEXT_COLOR, panel, MEDIUM);
    btnPhi.setBorder(BORDER_BLUE);
    btnPhi.addMouseListener(this);

    btnPrimeNumbers = Resources.getLabel("  Primes  ", TEXT_COLOR, panel, MEDIUM);
    btnPrimeNumbers.setBorder(BORDER_BLUE);
    btnPrimeNumbers.addMouseListener(this);

    btnCalculator = Resources.getLabel("  Calculator  ", MAIN_COLOR, panel, MEDIUM);
    btnCalculator.setBorder(BORDER_RED);
    btnCalculator.addMouseListener(this);

    btnOther = Resources.getLabel("  Hacks  ", TEXT_COLOR, panel, MEDIUM);
    btnOther.setBorder(BORDER_BLUE);
    btnOther.addMouseListener(this);

    btnPi = Resources.getLabel("  Pi π  ", TEXT_COLOR, panel, MEDIUM);
    btnPi.setBorder(BORDER_BLUE);
    btnPi.addMouseListener(this);

    return panel;
  }

  public void start(JFrame parent) {
    setSize(980, 570);
    setMinimumSize(new Dimension(480, 380));
    setMaximumSize(new Dimension(1295, 820));
    setLocationRelativeTo(parent);
    setTitle("Numbers");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    add(getPanel(), BorderLayout.SOUTH);

    ImageIcon image = new ImageIcon(Resources.getImage("math.jpg"));
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
      new CalculatorView(this, true).start(this);
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
      Effects.fadeOut(this);
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