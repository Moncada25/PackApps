package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;

import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.utils.Alerts;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR extends JDialog implements MouseListener {

  private JLabel searchFile;
  private JLabel exit;
  private JTextArea text;
  private Resources resources = new Resources();

  public OCR(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private String getPath() {

    JFileChooser chooser = new JFileChooser();
    chooser.addChoosableFileFilter(
        new FileNameExtensionFilter("Images (PNG - JPG)", "png", "jpg", "pdf"));
    chooser.setAcceptAllFileFilterUsed(false);

    String path = "";
    try {

      if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        path = chooser.getSelectedFile().getAbsolutePath();
      }

    } catch (Exception ex) {
      Alerts.error(ex, "OCR");
    }
    return path;
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
    Settings.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    searchFile = resources.getLabel("  SEARCH FILE  ", TEXT_COLOR, panel, Settings.MEDIUM);
    searchFile.setBorder(Settings.BORDER_BLUE);
    searchFile.addMouseListener(this);

    exit = resources.getLabel("  RETURN  ", MAIN_COLOR, panel, Settings.MEDIUM);
    exit.setBorder(Settings.BORDER_RED);
    exit.addMouseListener(this);

    return panel;
  }

  private String readText(String image) {

    Tesseract tesseract = new Tesseract();
    tesseract.setDatapath("src/main/resources/tessdata");
    tesseract.setLanguage("eng");
    tesseract.setPageSegMode(1);
    tesseract.setOcrEngineMode(1);

    try {
      return tesseract.doOCR(new File(image));
    } catch (TesseractException e) {
      return "Error";
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == searchFile) {

      String response = readText(getPath());

      if ("Error".equals(response)) {
        Alerts.message("Message", "File not found");
        text.setText("");
        text.setEnabled(false);
      } else {
        text.setText(response);
        text.setEnabled(true);
      }
    } else if (e.getSource() == exit) {
      Settings.fadeOut(this);
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
      searchFile.setCursor(Settings.HAND);
    } else if (e.getSource() == exit) {
      exit.setCursor(Settings.HAND);
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}