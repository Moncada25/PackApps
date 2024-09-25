package com.bookverse.development.packapps.apps.views;

import com.bookverse.development.packapps.apps.utils.ui.KeyBindingsUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
import com.bookverse.development.packapps.apps.utils.ui.Resources;

import static com.bookverse.development.packapps.apps.services.OcrService.readText;
import static com.bookverse.development.packapps.apps.utils.ui.Resources.getFile;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;

public class OcrView extends JDialog implements MouseListener {

  private JLabel searchFile;
  private JLabel exit;
  private JTextArea text;

  public OcrView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
    KeyBindingsUtil.addCopyPasteKeyBindings(text, null, null);
  }

  private void createComponents() {

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    text = new JTextArea();
    text.setEnabled(false);
    JScrollPane scroll = new JScrollPane(text);
    scroll.setBounds(30, 60, 420, 200);
    add(scroll, BorderLayout.CENTER);

    add(getPanel(), BorderLayout.SOUTH);

    repaint();
  }

  public void start(JFrame parent) {
    setSize(560, 330);
    setLocationRelativeTo(parent);
    setMinimumSize(new Dimension(560, 330));
    setMaximumSize(new Dimension(1280, 720));
    setTitle("OCR");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    searchFile = Resources.getLabel("  SEARCH FILE  ", TEXT_COLOR, panel, Styles.MEDIUM);
    searchFile.setBorder(Styles.BORDER_BLUE);
    searchFile.addMouseListener(this);

    exit = Resources.getLabel("  RETURN  ", MAIN_COLOR, panel, Styles.MEDIUM);
    exit.setBorder(Styles.BORDER_RED);
    exit.addMouseListener(this);

    return panel;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == searchFile) {

      String response = readText(getFile(this), true);

      if ("Error".equals(response)) {
        Alerts.message("Message", "File not found");
        text.setText("");
        text.setEnabled(false);
      } else {
        text.setText(response);
        text.setEnabled(true);
      }
    } else if (e.getSource() == exit) {
      Effects.fadeOut(this);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (e.getSource() == searchFile) {
      searchFile.setCursor(Styles.HAND);
    } else if (e.getSource() == exit) {
      exit.setCursor(Styles.HAND);
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
