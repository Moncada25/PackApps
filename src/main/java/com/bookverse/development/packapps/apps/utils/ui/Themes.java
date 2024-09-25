package com.bookverse.development.packapps.apps.utils.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import com.bookverse.development.packapps.apps.utils.constants.ArrayData;
import com.bookverse.development.packapps.apps.views.older.Index;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.BORDER_BLUE;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public final class Themes {

  public static final String DEFAULT = "Default";
  public static final String GRAY = "Gray";
  public static final String DARK = "Dark";
  public static final String TEXTURE = "Texture";
  public static final String MINT = "Mint";
  public static final String CLASSIC = "Classic";
  public static final String MAC = "Mac";
  public static boolean isWork = true;
  public static JLabel welcome;
  public static int background = 2;
  public static JMenuItem[] wallpapers = new JMenuItem[14];
  public static JMenuItem darkMode;
  public static JMenuItem defaultMode;
  public static JMenuItem textureMode;
  public static JMenuItem mintMode;
  public static JMenuItem classicMode;
  public static JMenuItem macMode;
  public static JMenuItem grayMode;

  private Themes() {
  }

  public static void paintUI() {
    darkMode.setForeground(TEXT_COLOR);
    textureMode.setForeground(TEXT_COLOR);
    mintMode.setForeground(TEXT_COLOR);
    classicMode.setForeground(TEXT_COLOR);
    macMode.setForeground(TEXT_COLOR);
    grayMode.setForeground(TEXT_COLOR);
  }

  public static void paintBackground(ActionEvent e, JFrame parent) {

    IntStream.range(0, wallpapers.length).filter(i -> e.getSource() == wallpapers[i]).forEach(i -> {
      if (wallpapers[i].getForeground() != MAIN_COLOR) {
        changeBackgroundAP(
            ArrayData.getPathBackground(i),
            ArrayData.getWidthBackground(i),
            ArrayData.getLongBackground(i),
            parent
        );
        wallpapers[i].setForeground(MAIN_COLOR);
        background = i + 1;
        parent.setVisible(true);
      } else {
        Alerts.elementApplied(false);
      }
    });
  }

  public static void changeBackgroundAP(String name, int width, int length, JFrame parent) {

    try {
      Effects.fadeIn(parent);
      parent.setVisible(false);
      ((JPanel) parent.getContentPane()).setOpaque(false);
      welcome.setIcon(new ImageIcon(Resources.getImage(name)));
      welcome.setSize(width, length);
      parent.setSize(width, length + 80);
      parent.setLocationRelativeTo(null);
      isWork = false;

      for (JMenuItem image : wallpapers) {
        image.setForeground(TEXT_COLOR);
      }

    } catch (Exception exception) {
      Alerts.error(exception, "Change Background");
    }
  }

  @SneakyThrows
  public static void setUI(@NotNull String selectedUI, JFrame parent) {

    paintUI();

    switch (selectedUI) {

      case DEFAULT -> UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      case GRAY -> UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
      case TEXTURE -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        UIManager.put("MenuItem.foreground", Color.WHITE);
        UIManager.put("Menu.foreground", MAIN_COLOR);
        UIManager.put("ComboBox.foreground", TEXT_COLOR);
        UIManager.put("Table.foreground", TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
      }

      case DARK -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        UIManager.put("Menu.foreground", MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("MenuItem.foreground", Color.WHITE);
      }

      case MAC -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        UIManager.put("MenuItem.foreground", TEXT_COLOR);
        UIManager.put("Menu.foreground", MAIN_COLOR);
        UIManager.put("ComboBox.foreground", TEXT_COLOR);
        UIManager.put("Table.foreground", TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
      }

      case MINT -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("MenuItem.foreground", TEXT_COLOR);
        UIManager.put("Menu.foreground", MAIN_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
        UIManager.put("TableHeader.foreground", MAIN_COLOR);
        UIManager.put("Table.foreground", TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
      }

      case CLASSIC -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("MenuItem.foreground", TEXT_COLOR);
        UIManager.put("Menu.foreground", MAIN_COLOR);
        UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
        UIManager.put("TableHeader.foreground", MAIN_COLOR);
        UIManager.put("Table.foreground", TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
      }

      default -> throw new IllegalStateException("Unexpected value: " + selectedUI);
    }

    if (selectedUI.equals(DEFAULT) || selectedUI.equals(GRAY)) {
      UIManager.put("ComboBox.foreground", new Color(0, 0, 0));
      UIManager.put("MenuItem.foreground", TEXT_COLOR);
      UIManager.put("Menu.foreground", MAIN_COLOR);
      UIManager.put("Button.foreground", new Color(0, 0, 0));
      UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
      UIManager.put("TableHeader.foreground", MAIN_COLOR);
      UIManager.put("Table.foreground", TEXT_COLOR);
      UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
    }

    parent.dispose();

    Index window = new Index();
    welcome = new JLabel();

    window.setSize(
        ArrayData.getWidthBackground(background - 1),
        ArrayData.getLongBackground(background - 1)
    );
    window.add(welcome, BorderLayout.CENTER);

    changeBackgroundAP(
        ArrayData.getPathBackground(background - 1),
        ArrayData.getWidthBackground(background - 1),
        ArrayData.getLongBackground(background - 1),
        parent
    );

    window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(parent.getTitle());
    wallpapers[background - 1].setForeground(MAIN_COLOR);

    switch (selectedUI) {
      case DEFAULT -> defaultMode.setForeground(MAIN_COLOR);
      case GRAY -> grayMode.setForeground(MAIN_COLOR);
      case TEXTURE -> textureMode.setForeground(MAIN_COLOR);
      case DARK -> darkMode.setForeground(MAIN_COLOR);
      case MAC -> macMode.setForeground(MAIN_COLOR);
      case MINT -> mintMode.setForeground(MAIN_COLOR);
      case CLASSIC -> classicMode.setForeground(MAIN_COLOR);
      default -> throw new IllegalStateException("Unexpected value: " + selectedUI);
    }

    Effects.fadeIn(window);
    window.setVisible(true);
    Alerts.changeUI(selectedUI);
  }
}
