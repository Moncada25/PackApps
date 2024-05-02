//package com.bookverse.development.packapps.apps.utils.ui;
//
//import static com.bookverse.development.packapps.apps.utils.constants.Styles.BORDER_BLUE;
//import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
//import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
//
//import com.bookverse.development.packapps.apps.utils.constants.ArrayData;
//import com.bookverse.development.packapps.apps.views.older.Index;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JLabel;
//import javax.swing.JMenuItem;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//import org.jetbrains.annotations.NotNull;
//
//public final class Themes {
//
//  private Themes() {
//  }
//
//  private void paintUI(List<JMenuItem> themeItems) {
//
//    for (JMenuItem themeItem : themeItems) {
//      themeItem.setForeground(TEXT_COLOR);
//    }
////    darkMode.setForeground(TEXT_COLOR);
////    textureMode.setForeground(TEXT_COLOR);
////    mintMode.setForeground(TEXT_COLOR);
////    classicMode.setForeground(TEXT_COLOR);
////    macMode.setForeground(TEXT_COLOR);
////    grayMode.setForeground(TEXT_COLOR);
//  }
//
//  private void setUI(@NotNull String selectedUI, List<JMenuItem> themeItems) {
//
//    paintUI(themeItems);
//
//    switch (selectedUI) {
//
//      case "Default":
//
//        try {
//          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//          UIManager.put("ComboBox.foreground", new Color(0, 0, 0));
//          UIManager.put("MenuItem.foreground", TEXT_COLOR);
//          UIManager.put("Menu.foreground", MAIN_COLOR);
//          UIManager.put("Button.foreground", new Color(0, 0, 0));
//
//          UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
//          UIManager.put("TableHeader.foreground", MAIN_COLOR);
//          UIManager.put("Table.foreground", TEXT_COLOR);
//          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
//
//          dispose();
//          Index window = new Index();
//
//          welcome = new JLabel();
//          window.setSize(ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//          window.add(welcome, BorderLayout.CENTER);
//          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
//              ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//
//          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//          window.setResizable(false);
//          window.setLocationRelativeTo(null);
//          window.setTitle(getTitle());
//          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
//          window.grayMode.setForeground(MAIN_COLOR);
//          Effects.fadeIn(window);
//          window.setVisible(true);
//          Alerts.changeUI("Default");
//
//        } catch (UnsupportedLookAndFeelException eq) {
//          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//          throw new RuntimeException(e);
//        }
//
//        break;
//
//      case "Gray":
//
//        try {
//          UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
//
//          UIManager.put("ComboBox.foreground", new Color(0, 0, 0));
//          UIManager.put("MenuItem.foreground", TEXT_COLOR);
//          UIManager.put("Menu.foreground", MAIN_COLOR);
//          UIManager.put("Button.foreground", new Color(0, 0, 0));
//
//          UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
//          UIManager.put("TableHeader.foreground", MAIN_COLOR);
//          UIManager.put("Table.foreground", TEXT_COLOR);
//          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
//
//          dispose();
//          Index window = new Index();
//
//          welcome = new JLabel();
//          window.setSize(ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//          window.add(welcome, BorderLayout.CENTER);
//          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
//              ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//
//          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//          window.setResizable(false);
//          window.setLocationRelativeTo(null);
//          window.setTitle(getTitle());
//          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
//          window.grayMode.setForeground(MAIN_COLOR);
//          Effects.fadeIn(window);
//          window.setVisible(true);
//          Alerts.changeUI("Gray");
//
//        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
//                 | IllegalAccessException eq) {
//          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
//        }
//
//        break;
//
//      case "Texture":
//
//        try {
//          UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
//
//          UIManager.put("MenuItem.foreground", Color.WHITE);
//          UIManager.put("Menu.foreground", MAIN_COLOR);
//
//          UIManager.put("ComboBox.foreground", TEXT_COLOR);
//          UIManager.put("Table.foreground", TEXT_COLOR);
//          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
//          UIManager.put("Button.foreground", Color.BLACK);
//
//          dispose();
//          Index window = new Index();
//
//          welcome = new JLabel();
//          window.setSize(ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//          window.add(welcome, BorderLayout.CENTER);
//          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
//              ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//
//          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//          window.setResizable(false);
//          window.setLocationRelativeTo(null);
//          window.setTitle(getTitle());
//          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
//          window.textureMode.setForeground(MAIN_COLOR);
//          Effects.fadeIn(window);
//          window.setVisible(true);
//          Alerts.changeUI("Texture");
//
//        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
//                 | IllegalAccessException eq) {
//          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
//        }
//
//        break;
//
//      case "Dark":
//
//        try {
//          UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
//
//          UIManager.put("Menu.foreground", MAIN_COLOR);
//          UIManager.put("ComboBox.foreground", Color.WHITE);
//          UIManager.put("Table.foreground", Color.WHITE);
//          UIManager.put("OptionPane.messageForeground", Color.WHITE);
//          UIManager.put("Button.foreground", Color.WHITE);
//          UIManager.put("MenuItem.foreground", Color.WHITE);
//
//          dispose();
//          Index window = new Index();
//
//          welcome = new JLabel();
//          window.setSize(ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//          window.add(welcome, BorderLayout.CENTER);
//          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
//              ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//
//          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//          window.setResizable(false);
//          window.setLocationRelativeTo(null);
//          window.setTitle(getTitle());
//          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
//          window.darkMode.setForeground(MAIN_COLOR);
//          Effects.fadeIn(window);
//          window.setVisible(true);
//
//          Alerts.changeUI("Dark");
//
//
//        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
//                 | IllegalAccessException eq) {
//          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
//        }
//
//        break;
//
//      case "Mac":
//
//        try {
//          UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//
//          UIManager.put("MenuItem.foreground", TEXT_COLOR);
//          UIManager.put("Menu.foreground", MAIN_COLOR);
//
//          UIManager.put("ComboBox.foreground", TEXT_COLOR);
//          UIManager.put("Table.foreground", TEXT_COLOR);
//          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
//          UIManager.put("Button.foreground", Color.BLACK);
//
//          dispose();
//          Index window = new Index();
//
//          welcome = new JLabel();
//          window.setSize(ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//          window.add(welcome, BorderLayout.CENTER);
//          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
//              ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//
//          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//          window.setResizable(false);
//          window.setLocationRelativeTo(null);
//          window.setTitle(getTitle());
//          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
//          window.macMode.setForeground(MAIN_COLOR);
//          Effects.fadeIn(window);
//          window.setVisible(true);
//          Alerts.changeUI("Mac OS");
//
//        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
//                 | IllegalAccessException eq) {
//          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
//        }
//
//        break;
//
//      case "Mint":
//
//        try {
//          UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//
//          UIManager.put("ComboBox.foreground", Color.BLACK);
//
//          UIManager.put("MenuItem.foreground", TEXT_COLOR);
//          UIManager.put("Menu.foreground", MAIN_COLOR);
//
//          UIManager.put("Button.foreground", Color.BLACK);
//
//          UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
//          UIManager.put("TableHeader.foreground", MAIN_COLOR);
//          UIManager.put("Table.foreground", TEXT_COLOR);
//          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
//
//          dispose();
//          Index window = new Index();
//
//          welcome = new JLabel();
//          window.setSize(ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//          window.add(welcome, BorderLayout.CENTER);
//          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
//              ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//
//          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//          window.setResizable(false);
//          window.setLocationRelativeTo(null);
//          window.setTitle(getTitle());
//          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
//          window.mintMode.setForeground(MAIN_COLOR);
//          Effects.fadeIn(window);
//          window.setVisible(true);
//          Alerts.changeUI("Mint");
//
//        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
//                 | IllegalAccessException eq) {
//          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
//        }
//
//        break;
//
//      case "Classic":
//
//        try {
//          UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
//
//          UIManager.put("ComboBox.foreground", Color.BLACK);
//
//          UIManager.put("Button.foreground", Color.BLACK);
//
//          UIManager.put("MenuItem.foreground", TEXT_COLOR);
//          UIManager.put("Menu.foreground", MAIN_COLOR);
//
//          UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
//          UIManager.put("TableHeader.foreground", MAIN_COLOR);
//          UIManager.put("Table.foreground", TEXT_COLOR);
//          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
//
//          dispose();
//          Index window = new Index();
//
//          welcome = new JLabel();
//          window.setSize(ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//          window.add(welcome, BorderLayout.CENTER);
//          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
//              ArrayData.getWidthBackground(background - 1),
//              ArrayData.getLongBackground(background - 1));
//
//          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//          window.setResizable(false);
//          window.setLocationRelativeTo(null);
//          window.setTitle(getTitle());
//          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
//          window.classicMode.setForeground(MAIN_COLOR);
//          Effects.fadeIn(window);
//          window.setVisible(true);
//          Alerts.changeUI("Classic");
//
//        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
//                 | IllegalAccessException eq) {
//          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
//        }
//
//        break;
//    }
//  }
//}
