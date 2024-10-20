package com.bookverse.development.packapps.utils.ui.factory;

import com.bookverse.development.packapps.utils.ui.Resources;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class MenuItem {

  private String text;
  private String image;

  public MenuItem setText(String text) {
    this.text = text;
    return this;
  }

  public MenuItem setImage(String image) {
    this.image = image;
    return this;
  }

  public JMenuItem build() {
    JMenuItem item = new JMenuItem(text);
    item.setIcon(new ImageIcon(Resources.getImage(image + ".png")));
    return item;
  }
}
