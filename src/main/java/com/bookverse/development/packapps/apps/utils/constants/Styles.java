package com.bookverse.development.packapps.apps.utils.constants;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class Styles {

  public static final Color TEXT_COLOR = new Color(21, 87, 163);
  public static final Color MAIN_COLOR = new Color(220, 12, 12);
  public static final Border BORDER_BLUE = BorderFactory.createLineBorder(TEXT_COLOR, 2, true);
  public static final Border BORDER_RED = BorderFactory.createLineBorder(MAIN_COLOR, 2, true);
  public static final Cursor POINT = new Cursor(Cursor.CROSSHAIR_CURSOR);
  public static final Cursor LOADER = new Cursor(Cursor.WAIT_CURSOR);
  public static final Cursor RESIZE = new Cursor(Cursor.NE_RESIZE_CURSOR);
  public static final Cursor TEXT = new Cursor(Cursor.TEXT_CURSOR);
  public static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);
  public static final Font SMALL = new Font("Cambria", Font.PLAIN, 15);
  public static final Font MEDIUM = new Font("Cambria", Font.PLAIN, 18);
  public static final Font BIG = new Font("Cambria", Font.PLAIN, 28);

  private Styles() {
  }
}
