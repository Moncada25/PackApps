package com.bookverse.development.packapps.utils.ui;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class MenuItemBuilder {

  private String text;
  private String image;

  public MenuItemBuilder setText(String text) {
    this.text = text;
    return this;
  }

  public MenuItemBuilder setImage(String image) {
    this.image = image;
    return this;
  }

  public JMenuItem build() {
    JMenuItem item = new JMenuItem(text);
    item.setIcon(new ImageIcon(Resources.getImage(image + ".png")));
    return item;
  }
}
