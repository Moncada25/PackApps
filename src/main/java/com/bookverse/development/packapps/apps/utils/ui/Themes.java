package com.bookverse.development.packapps.apps.utils.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import com.bookverse.development.packapps.apps.utils.constants.ArrayData;
import com.bookverse.development.packapps.apps.views.older.Index;
import com.bookverse.development.packapps.apps.utils.constants.Styles;

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

  public static void setWallpapers(Index parent, JMenu wallpaper) {
    IntStream.range(0, Themes.wallpapers.length).forEach(i -> {
      Themes.wallpapers[i] = new JMenuItem("Image " + (i + 1));
      Themes.wallpapers[i].setForeground(Styles.TEXT_COLOR);
      Themes.wallpapers[i].setIcon(new ImageIcon(Resources.getImage("backs.png")));
      Themes.wallpapers[i].addActionListener(parent);
      wallpaper.add(Themes.wallpapers[i]);
      wallpaper.addSeparator();
    });
  }

  public static void setThemes(Index parent, JMenu mode) {
    defaultMode = Resources.getMenuItem("Default", "default_theme", parent);
    darkMode = Resources.getMenuItem("Dark", "dark", parent);
    textureMode = Resources.getMenuItem("Texture", "texture", parent);
    macMode = Resources.getMenuItem("Mac OS", "mac", parent);
    grayMode = Resources.getMenuItem("Metallic", "gray", parent);
    mintMode = Resources.getMenuItem("Mint", "mint", parent);
    classicMode = Resources.getMenuItem("Classic", "classic", parent);

    Resources.addMenu(
        mode,
        Themes.defaultMode,
        Themes.darkMode,
        Themes.textureMode,
        Themes.macMode,
        Themes.grayMode,
        Themes.mintMode,
        Themes.classicMode
    );
  }

  public static void paintUI() {
    darkMode.setForeground(Styles.TEXT_COLOR);
    textureMode.setForeground(Styles.TEXT_COLOR);
    mintMode.setForeground(Styles.TEXT_COLOR);
    classicMode.setForeground(Styles.TEXT_COLOR);
    macMode.setForeground(Styles.TEXT_COLOR);
    grayMode.setForeground(Styles.TEXT_COLOR);
  }

  public static void paintBackground(ActionEvent e, JFrame parent) {

    isWork = true;

    IntStream.range(0, wallpapers.length).filter(i -> e.getSource() == wallpapers[i]).forEach(i -> {
      if (wallpapers[i].getForeground() != Styles.MAIN_COLOR) {
        changeBackgroundAP(
            ArrayData.getPathBackground(i),
            ArrayData.getWidthBackground(i),
            ArrayData.getLongBackground(i),
            parent
        );
        wallpapers[i].setForeground(Styles.MAIN_COLOR);
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
        image.setForeground(Styles.TEXT_COLOR);
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
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Styles.TEXT_COLOR);
        UIManager.put("Table.foreground", Styles.TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
      }

      case DARK -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("MenuItem.foreground", Color.WHITE);
      }

      case MAC -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        UIManager.put("MenuItem.foreground", Styles.TEXT_COLOR);
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Styles.TEXT_COLOR);
        UIManager.put("Table.foreground", Styles.TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
      }

      case MINT -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("MenuItem.foreground", Styles.TEXT_COLOR);
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Table.focusCellHighlightBorder", Styles.BORDER_BLUE);
        UIManager.put("TableHeader.foreground", Styles.MAIN_COLOR);
        UIManager.put("Table.foreground", Styles.TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);
      }

      case CLASSIC -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("MenuItem.foreground", Styles.TEXT_COLOR);
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("Table.focusCellHighlightBorder", Styles.BORDER_BLUE);
        UIManager.put("TableHeader.foreground", Styles.MAIN_COLOR);
        UIManager.put("Table.foreground", Styles.TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);
      }

      default -> throw new IllegalStateException("Unexpected value: " + selectedUI);
    }

    if (selectedUI.equals(DEFAULT) || selectedUI.equals(GRAY)) {
      UIManager.put("ComboBox.foreground", new Color(0, 0, 0));
      UIManager.put("MenuItem.foreground", Styles.TEXT_COLOR);
      UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
      UIManager.put("Button.foreground", new Color(0, 0, 0));
      UIManager.put("Table.focusCellHighlightBorder", Styles.BORDER_BLUE);
      UIManager.put("TableHeader.foreground", Styles.MAIN_COLOR);
      UIManager.put("Table.foreground", Styles.TEXT_COLOR);
      UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);
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

    window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(parent.getTitle());
    wallpapers[background - 1].setForeground(Styles.MAIN_COLOR);

    switch (selectedUI) {
      case DEFAULT -> defaultMode.setForeground(Styles.MAIN_COLOR);
      case GRAY -> grayMode.setForeground(Styles.MAIN_COLOR);
      case TEXTURE -> textureMode.setForeground(Styles.MAIN_COLOR);
      case DARK -> darkMode.setForeground(Styles.MAIN_COLOR);
      case MAC -> macMode.setForeground(Styles.MAIN_COLOR);
      case MINT -> mintMode.setForeground(Styles.MAIN_COLOR);
      case CLASSIC -> classicMode.setForeground(Styles.MAIN_COLOR);
      default -> throw new IllegalStateException("Unexpected value: " + selectedUI);
    }

    Effects.fadeIn(window);
    window.setVisible(true);
    Alerts.changeUI(selectedUI);
  }
}
