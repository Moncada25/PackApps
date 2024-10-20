package com.bookverse.development.packapps.utils.ui.factory;

import com.bookverse.development.packapps.utils.ui.Resources;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

public class Menu {

  private String text;
  private String image;

  public Menu setText(String text) {
    this.text = text;
    return this;
  }

  public Menu setImage(String image) {
    this.image = image;
    return this;
  }

  public JMenu build() {
    JMenu item = new JMenu(text);
    item.setIcon(new ImageIcon(Resources.getImage(image + ".png")));
    return item;
  }
}
