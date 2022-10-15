package com.bookverse.development.packapps.apps.views;

import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;

import com.bookverse.development.packapps.utils.Crypto;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextsView extends JDialog implements MouseListener {

  private JLabel encrypt, decrypt, upperCase, lowerCase, exit;
  private JTextArea text;
  private Resources resources = new Resources();

  public TextsView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(530, 330);
    setLocationRelativeTo(parent);
    setMinimumSize(new Dimension(530, 330));
    setMaximumSize(new Dimension(1280, 720));
    setTitle("Texts");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  public JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBorder(Resources.getBorder("Select action"));

    encrypt = resources.getLabel("  Encrypt  ", TEXT_COLOR, panel, Styles.MEDIUM);
    encrypt.setBorder(Styles.BORDER_BLUE);
    encrypt.addMouseListener(this);

    upperCase = resources.getLabel("  UpperCase  ", TEXT_COLOR, panel, Styles.MEDIUM);
    upperCase.setBorder(Styles.BORDER_BLUE);
    upperCase.addMouseListener(this);

    exit = resources.getLabel("  Return  ", MAIN_COLOR, panel, Styles.MEDIUM);
    exit.setBorder(Styles.BORDER_RED);
    exit.addMouseListener(this);

    lowerCase = resources.getLabel("  LowerCase  ", TEXT_COLOR, panel, Styles.MEDIUM);
    lowerCase.setBorder(Styles.BORDER_BLUE);
    lowerCase.addMouseListener(this);

    decrypt = resources.getLabel("  Decrypt  ", TEXT_COLOR, panel, Styles.MEDIUM);
    decrypt.setBorder(Styles.BORDER_BLUE);
    decrypt.addMouseListener(this);

    return panel;
  }

  private void createComponents() {

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    JLabel title = resources
        .getLabel("<html><strong>Write Text...</strong></html>", MAIN_COLOR,
            this, Styles.MEDIUM);
    title.setBounds(30, 15, 370, 50);
    add(title, BorderLayout.NORTH);

    text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    scroll.setBounds(30, 60, 420, 200);
    add(scroll, BorderLayout.CENTER);

    add(getPanel(), BorderLayout.SOUTH);

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
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == upperCase) {

      if (!text.getText().equals("")) {
        text.setText(text.getText().toUpperCase());
      } else {
        Alerts.inputSomethingText();
      }

    } else if (e.getSource() == lowerCase) {

      if (!text.getText().equals("")) {
        text.setText(text.getText().toLowerCase());
      } else {
        Alerts.inputSomethingText();
      }

    } else if (e.getSource() == encrypt) {

      if (!text.getText().equals("")) {
        text.setText(Crypto.encrypt(text.getText(), false));
      } else {
        Alerts.inputSomethingText();
      }

    } else if (e.getSource() == decrypt) {

      if (!text.getText().equals("")) {
        text.setText(Crypto.decrypt(text.getText(), false));
      } else {
        Alerts.inputSomethingText();
      }

    } else if (e.getSource() == exit) {
      Effects.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == upperCase) {
      upperCase.setCursor(Styles.HAND);
    } else if (e.getSource() == lowerCase) {
      lowerCase.setCursor(Styles.HAND);
    } else if (e.getSource() == encrypt) {
      encrypt.setCursor(Styles.HAND);
    } else if (e.getSource() == decrypt) {
      decrypt.setCursor(Styles.HAND);
    } else if (e.getSource() == exit) {
      exit.setCursor(Styles.HAND);
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