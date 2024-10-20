package com.bookverse.development.packapps.utils.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import javax.swing.filechooser.FileNameExtensionFilter;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import com.bookverse.development.packapps.utils.constants.Styles;

public final class Resources {

  private Resources(){}

  @Getter
  private static Object object;

  public static void setObject(Object object) {
    Resources.object = object;
  }

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

  public static JMenuItem getMenuItem(String name, String image, ActionListener listener) {
    JMenuItem item = new JMenuItem(name);
    item.setIcon(new ImageIcon(getImage(image + ".png")));
    item.addActionListener(listener);
    return item;
  }

  public static JMenuItem getMenuItem(String name, String image) {
    JMenuItem item = new JMenuItem(name);
    item.setIcon(new ImageIcon(getImage(image + ".png")));
    return item;
  }

  public static JMenu getMenu(String name, String image) {
    JMenu menu = new JMenu(name);
    menu.setIcon(new ImageIcon(getImage(image + ".png")));
    return menu;
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
      Alerts.error(ex, "OCR");
    }
    return path;
  }

  public static void addMenu(JMenu menu, JMenuItem... items) {
    for (JMenuItem item : items) {
      menu.add(item);
      menu.addSeparator();
    }
  }
}