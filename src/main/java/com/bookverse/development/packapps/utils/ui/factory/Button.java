package com.bookverse.development.packapps.utils.ui.factory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import com.bookverse.development.packapps.utils.constants.Styles;

public class Button {
  private String text;
  private Color color = null;
  private Font font = Styles.MEDIUM;
  private int width = 0;
  private int height = 0;

  public Button setText(String text) {
    this.text = text;
    return this;
  }

  public Button setColor(Color color) {
    this.color = color;
    return this;
  }

  public Button setFont(Font font) {
    this.font = font;
    return this;
  }

  public Button setSize(int width, int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  public JButton build() {
    JButton button = new JButton(text);
    button.setBackground(color);
    button.setFont(font);

    if (width > 0 && height > 0) {
      button.setPreferredSize(new Dimension(width, height));
    }

    return button;
  }
}
