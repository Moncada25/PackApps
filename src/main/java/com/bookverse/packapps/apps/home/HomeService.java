package com.bookverse.packapps.apps.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;
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
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.constants.Themes;
import com.bookverse.packapps.utils.ui.Wallpaper;

@Data
public class HomeService {

  private boolean isWork = true;
  private int background = 2;
  private static final List<Wallpaper> WALLPAPERS = List.of(
      new Wallpaper("img1.jpg", 529, 660),
      new Wallpaper("img2.jpg", 1100, 618),
      new Wallpaper("img3.jpg", 960, 540),
      new Wallpaper("img4.jpg", 800, 531),
      new Wallpaper("img5.jpg", 1150, 646),
      new Wallpaper("img6.jpg", 1150, 646),
      new Wallpaper("img7.jpg", 700, 648),
      new Wallpaper("img8.jpg", 600, 625),
      new Wallpaper("img9.jpg", 640, 427),
      new Wallpaper("img10.jpg", 650, 650),
      new Wallpaper("img11.jpg", 920, 602),
      new Wallpaper("img12.jpg", 650, 650),
      new Wallpaper("img13.jpg", 600, 644),
      new Wallpaper("img14.jpg", 538, 660)
  );

  public void changeBackgroundAP(
      HomeViewModel model,
      String name,
      int width,
      int length,
      JFrame parent
  ) {

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

    IntStream.range(0, model.getWallpapers().length)
        .filter(i -> e.getSource() == model.getWallpapers()[i]).forEach(i -> {
          if (model.getWallpapers()[i].getForeground() != Styles.MAIN_COLOR) {

            Wallpaper wallpaper = getWallpaper(i);

            changeBackgroundAP(
                model,
                wallpaper.name(),
                wallpaper.width(),
                wallpaper.height(),
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

      case Themes.DEFAULT ->
          UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      case Themes.GRAY ->
          UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
      case Themes.TEXTURE -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        UIManager.put("MenuItem.foreground", Color.WHITE);
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Styles.TEXT_COLOR);
        UIManager.put("Table.foreground", Styles.TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
      }

      case Themes.DARK -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("MenuItem.foreground", Color.WHITE);
      }

      case Themes.MAC -> {
        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        UIManager.put("MenuItem.foreground", Styles.TEXT_COLOR);
        UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
        UIManager.put("ComboBox.foreground", Styles.TEXT_COLOR);
        UIManager.put("Table.foreground", Styles.TEXT_COLOR);
        UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);
        UIManager.put("Button.foreground", Color.BLACK);
      }

      case Themes.MINT -> {
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

      case Themes.CLASSIC -> {
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

    if (selectedUI.equals(Themes.DEFAULT) || selectedUI.equals(Themes.GRAY)) {
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

    Wallpaper wallpaper = getWallpaper(background - 1);

    window.setSize(wallpaper.width(), wallpaper.height());
    window.add(model.getWelcome(), BorderLayout.CENTER);

    changeBackgroundAP(
        model,
        wallpaper.name(),
        wallpaper.width(),
        wallpaper.height(),
        parent
    );

    window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(parent.getTitle());
    model.getWallpapers()[background - 1].setForeground(Styles.MAIN_COLOR);

    switch (selectedUI) {
      case Themes.DEFAULT -> model.getDefaultMode().setForeground(Styles.MAIN_COLOR);
      case Themes.GRAY -> model.getGrayMode().setForeground(Styles.MAIN_COLOR);
      case Themes.TEXTURE -> model.getTextureMode().setForeground(Styles.MAIN_COLOR);
      case Themes.DARK -> model.getDarkMode().setForeground(Styles.MAIN_COLOR);
      case Themes.MAC -> model.getMacMode().setForeground(Styles.MAIN_COLOR);
      case Themes.MINT -> model.getMintMode().setForeground(Styles.MAIN_COLOR);
      case Themes.CLASSIC -> model.getClassicMode().setForeground(Styles.MAIN_COLOR);
      default -> throw new IllegalStateException("Unexpected value: " + selectedUI);
    }

    Effects.fadeIn(window);
    window.setVisible(true);
    Alerts.changeUI(selectedUI);
  }

  public Wallpaper getWallpaper(int index) {
    return WALLPAPERS.get(index);
  }
}
