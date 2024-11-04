package com.bookverse.development.packapps.apps.home;

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
import javax.swing.WindowConstants;
import lombok.Data;
import lombok.SneakyThrows;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.constants.AppThemes;

@Data
public class HomeService {

  private boolean isWork = true;
  private int background = 2;

  public void changeBackgroundAP(HomeViewModel model, String name, int width, int length, JFrame parent) {

    try {
      Effects.fadeIn(parent);
      parent.setVisible(false);
      ((JPanel) parent.getContentPane()).setOpaque(false);
      model.getWelcome().setIcon(new ImageIcon(Resources.getImage(name)));
      model.getWelcome().setSize(width, length);
      parent.setSize(width, length + 80);
      parent.setLocationRelativeTo(null);
      isWork = false;

      for (JMenuItem image : model.getWallpapers()) {
        image.setForeground(Styles.TEXT_COLOR);
      }

    } catch (Exception exception) {
      Alerts.error(exception, "Change Background");
    }
  }

  public void setWallpaper(HomeViewModel model, ActionEvent e, JFrame parent) {

    isWork = true;

    IntStream.range(0, model.getWallpapers().length).filter(i -> e.getSource() == model.getWallpapers()[i]).forEach(i -> {
      if (model.getWallpapers()[i].getForeground() != Styles.MAIN_COLOR) {
        changeBackgroundAP(
            model,
            getPathBackground(i),
            getWidthBackground(i),
            getLongBackground(i),
            parent
        );
        model.getWallpapers()[i].setForeground(Styles.MAIN_COLOR);
        background = i + 1;
        parent.setVisible(true);
      } else {
        Alerts.elementApplied(false);
      }
    });
  }

  @SneakyThrows
  public void setTheme(HomeViewModel model, String selectedUI, JFrame parent) {

    model.getDarkMode().setForeground(Styles.TEXT_COLOR);
    model.getTextureMode().setForeground(Styles.TEXT_COLOR);
    model.getMintMode().setForeground(Styles.TEXT_COLOR);
    model.getClassicMode().setForeground(Styles.TEXT_COLOR);
    model.getMacMode().setForeground(Styles.TEXT_COLOR);
    model.getGrayMode().setForeground(Styles.TEXT_COLOR);

    switch (selectedUI) {

      case AppThemes.DEFAULT -> UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      case AppThemes.GRAY -> UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
      case AppThemes.TEXTURE -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        UIManager.put("MenuItem.foreground", Color.WHITE);
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Styles.TEXT_COLOR);
        UIManager.put("Table.foreground", Styles.TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
      }

      case AppThemes.DARK -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("MenuItem.foreground", Color.WHITE);
      }

      case AppThemes.MAC -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        UIManager.put("MenuItem.foreground", Styles.TEXT_COLOR);
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Styles.TEXT_COLOR);
        UIManager.put("Table.foreground", Styles.TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
      }

      case AppThemes.MINT -> {
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

      case AppThemes.CLASSIC -> {
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

    if (selectedUI.equals(AppThemes.DEFAULT) || selectedUI.equals(AppThemes.GRAY)) {
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

    HomeView window = new HomeView();
    model.setWelcome(new JLabel());

    window.setSize(
        getWidthBackground(background - 1), getLongBackground(background - 1)
    );
    window.add(model.getWelcome(), BorderLayout.CENTER);

    changeBackgroundAP(
        model,
        getPathBackground(background - 1),
        getWidthBackground(background - 1),
        getLongBackground(background - 1),
        parent
    );

    window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(parent.getTitle());
    model.getWallpapers()[background - 1].setForeground(Styles.MAIN_COLOR);

    switch (selectedUI) {
      case AppThemes.DEFAULT -> model.getDefaultMode().setForeground(Styles.MAIN_COLOR);
      case AppThemes.GRAY -> model.getGrayMode().setForeground(Styles.MAIN_COLOR);
      case AppThemes.TEXTURE -> model.getTextureMode().setForeground(Styles.MAIN_COLOR);
      case AppThemes.DARK -> model.getDarkMode().setForeground(Styles.MAIN_COLOR);
      case AppThemes.MAC -> model.getMacMode().setForeground(Styles.MAIN_COLOR);
      case AppThemes.MINT -> model.getMintMode().setForeground(Styles.MAIN_COLOR);
      case AppThemes.CLASSIC -> model.getClassicMode().setForeground(Styles.MAIN_COLOR);
      default -> throw new IllegalStateException("Unexpected value: " + selectedUI);
    }

    Effects.fadeIn(window);
    window.setVisible(true);
    Alerts.changeUI(selectedUI);
  }

  public String getPathBackground(int index) {

    String[] backgrounds = {
        "img1.jpg",
        "img2.jpg",
        "img3.jpg",
        "img4.jpg",
        "img5.jpg",
        "img6.jpg",
        "img7.jpg",
        "img8.jpg",
        "img9.jpg",
        "img10.jpg",
        "img11.jpg",
        "img12.jpg",
        "img13.jpg",
        "img14.jpg"
    };

    return backgrounds[index];
  }

  public int getWidthBackground(int index) {

    int[] widthBackgrounds = {
        529, 1100, 960, 800, 1150,
        1150, 700, 600, 640, 650,
        920, 650, 600, 538};

    return widthBackgrounds[index];
  }

  public int getLongBackground(int index) {

    int[] longBackgrounds = {
        660, 618, 540, 531, 646,
        646, 648, 625, 427, 650,
        602, 650, 644, 660};

    return longBackgrounds[index];
  }
}
