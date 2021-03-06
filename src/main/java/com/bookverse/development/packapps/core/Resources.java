package com.bookverse.development.packapps.core;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import org.jetbrains.annotations.NotNull;

public class Resources {

  private static Object generalObject;

  public static Object getGeneralObject() {
    return generalObject;
  }

  public static void setGeneralObject(Object generalObject) {
    Resources.generalObject = generalObject;
  }

  public URL getImage(String image) {
    return this.getClass().getResource("/" + image);
  }

  public JMenuItem getMenuItem(String name, String image, ActionListener listener) {
    JMenuItem item = new JMenuItem(name);
    item.setIcon(new ImageIcon(getImage(image + ".png")));
    item.addActionListener(listener);
    return item;
  }

  public JMenu getMenu(String name, String image) {
    JMenu menu = new JMenu(name);
    menu.setIcon(new ImageIcon(getImage(image + ".png")));
    return menu;
  }

  public JButton getButton(String name, Color color, ActionListener listener,
      @NotNull Container container) {
    JButton button = new JButton(name);
    button.setBackground(color);
    container.add(button);
    button.addActionListener(listener);
    return button;
  }

  public JLabel getLabel(String text, Color color, @NotNull Container container, Font font) {
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setForeground(color);
    label.setFont(font);
    container.add(label);
    return label;
  }
}