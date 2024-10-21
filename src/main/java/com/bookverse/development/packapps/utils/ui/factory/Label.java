package com.bookverse.development.packapps.utils.ui.factory;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Label {

  private String text;
  private Font font;
  private Color color;

  public Label setText(String text) {
    this.text = text;
    return this;
  }

  public Label setFont(Font font) {
    this.font = font;
    return this;
  }

  public Label setColor(Color color) {
    this.color = color;
    return this;
  }

  public JLabel build() {
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setForeground(color);
    label.setFont(font);
    return label;
  }

}
