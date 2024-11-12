package com.bookverse.development.packapps.utils.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jetbrains.annotations.NotNull;

import com.bookverse.development.packapps.utils.constants.Styles;

public final class Resources {

  private Resources(){}

  @NotNull
  public static Border getBorder(String title) {

    TitledBorder border = BorderFactory.createTitledBorder(Styles.BORDER_BLUE, title);

    border.setTitleColor(Styles.MAIN_COLOR);
    border.setTitleFont(Styles.MEDIUM);
    border.setTitleJustification(TitledBorder.CENTER);

    return border;
  }

  public static URL getImage(String image) {
    return Resources.class.getResource("/" + image);
  }

  public static JButton getButton(
      String name,
      Color color,
      ActionListener listener,
      @NotNull Container container
  ) {
    JButton button = new JButton(name);
    button.setBackground(color);
    container.add(button);
    button.addActionListener(listener);
    return button;
  }

  public static JLabel getLabel(String text, Color color, @NotNull Container container, Font font) {
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setForeground(color);
    label.setFont(font);
    container.add(label);
    return label;
  }

  public static String getFile(JDialog parent) {

    JFileChooser chooser = new JFileChooser();
    chooser.addChoosableFileFilter(
        new FileNameExtensionFilter("Images (PNG - JPG)", "png", "jpg", "pdf"));
    chooser.setAcceptAllFileFilterUsed(false);

    String path = "";
    try {

      if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
        path = chooser.getSelectedFile().getAbsolutePath();
      }

    } catch (Exception ex) {
      Alerts.error(ex, "Select file");
    }
    return path;
  }
}