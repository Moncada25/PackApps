package com.bookverse.development.packapps.utils.ui;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

public class MenuBuilder {

  private String text;
  private String image;

  public MenuBuilder setText(String text) {
    this.text = text;
    return this;
  }

  public MenuBuilder setImage(String image) {
    this.image = image;
    return this;
  }

  public JMenu build() {
    JMenu item = new JMenu(text);
    item.setIcon(new ImageIcon(Resources.getImage(image + ".png")));
    return item;
  }
}
