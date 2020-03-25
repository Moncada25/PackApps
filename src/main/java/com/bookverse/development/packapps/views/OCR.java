package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfigCore.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfigCore.TEXT_COLOR;

import com.bookverse.development.packapps.core.AppConfigCore;
import com.bookverse.development.packapps.models.Resources;
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
    chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images (PNG - JPG)", "png"));
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
    AppConfigCore.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    searchFile = resources.getLabel("  SEARCH FILE  ", TEXT_COLOR, panel, AppConfigCore.MEDIUM);
    searchFile.setBorder(AppConfigCore.BORDER_BLUE);
    searchFile.addMouseListener(this);

    exit = resources.getLabel("  RETURN  ", MAIN_COLOR, panel, AppConfigCore.MEDIUM);
    exit.setBorder(AppConfigCore.BORDER_RED);
    exit.addMouseListener(this);

    return panel;
  }

  private String readText(String image) {

    ITesseract itesseract = new Tesseract();

    try {
      return itesseract.doOCR(new File(image));
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
      AppConfigCore.fadeOut(this);
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
      searchFile.setCursor(AppConfigCore.HAND);
    } else if (e.getSource() == exit) {
      exit.setCursor(AppConfigCore.HAND);
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
