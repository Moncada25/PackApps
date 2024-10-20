package com.bookverse.development.packapps.apps.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
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
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.apps.tables.DicesTable;
import com.bookverse.development.packapps.apps.tables.GuessNumberTable;
import com.bookverse.development.packapps.apps.tables.HangmanTable;
import com.bookverse.development.packapps.apps.tables.InventoryTable;
import com.bookverse.development.packapps.apps.tables.LoansTable;
import com.bookverse.development.packapps.apps.tables.NotesTable;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.constants.Queries;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.apps.views.older.CashRegisterTable;
import com.bookverse.development.packapps.apps.views.older.PurchasesTable;
import com.bookverse.development.packapps.apps.views.older.PuzzleTable;
import com.bookverse.development.packapps.apps.views.older.SalesTable;
import com.bookverse.development.packapps.utils.constants.ArrayData;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.constants.AppThemes;

@Data
public class HomeService {

  private boolean isWork = true;
  private int background = 2;
  private HangmanTable hangmanTable;
  private GuessNumberTable guessNumberTable;
  private PuzzleTable puzzleTable;
  private DicesTable dicesTable;
  private NotesTable notesTable;
  private InventoryTable inventoryTable;
  private CashRegisterTable cashRegisterTable;
  private LoansTable loansTable;
  private PurchasesTable purchasesTable;
  private SalesTable salesTable;

  public void setTables(JFrame parent) {
    hangmanTable = new HangmanTable(parent, true);
    guessNumberTable = new GuessNumberTable(parent, true);
    puzzleTable = new PuzzleTable(parent, true);
    dicesTable = new DicesTable(parent, true);
    notesTable = new NotesTable(parent, true);
    inventoryTable = new InventoryTable(parent, true);
    cashRegisterTable = new CashRegisterTable(parent, true);
    loansTable = new LoansTable(parent, true);
    purchasesTable = new PurchasesTable(parent, true);
    salesTable = new SalesTable(parent, true);
  }

  public void guessNumberTableAP() {

    guessNumberTable.cleanTable();

    try {
      OlderRepository.readTable(
          guessNumberTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)),
          true
      );
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.GUESS_NUMBER);
    }

    guessNumberTable.setSize(830, 400);
    guessNumberTable.setLocationRelativeTo(null);
    guessNumberTable.setMinimumSize(new Dimension(830, 400));
    guessNumberTable.setMaximumSize(new Dimension(1280, 720));
    guessNumberTable.setTitle(DatabaseConstants.GUESS_NUMBER + " Information");
    Effects.fadeIn(guessNumberTable);
    guessNumberTable.setVisible(true);
  }

  public void hangmanTableAP() {

    hangmanTable.cleanTable();

    try {
      OlderRepository
          .readTable(hangmanTable.viewTable, Queries.getAllData(Format.tableName(
              DatabaseConstants.HANGMAN)), true);
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.HANGMAN);
    }

    hangmanTable.setSize(830, 400);
    hangmanTable.setLocationRelativeTo(null);
    hangmanTable.setMinimumSize(new Dimension(830, 400));
    hangmanTable.setMaximumSize(new Dimension(1280, 720));
    hangmanTable.setTitle(DatabaseConstants.HANGMAN + " Information");
    Effects.fadeIn(hangmanTable);
    hangmanTable.setVisible(true);
  }

  public boolean dicesTableAP(Window parent) {
    boolean aux = false;

    dicesTable.cleanTable();

    try {
      aux = OlderRepository.readTable(dicesTable.viewTable,
          Queries.getAllData(DatabaseConstants.DICES), true);
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.DICES);
    }

    if (aux) {
      parent.setVisible(false);
      dicesTable.setSize(830, 400);
      dicesTable.setLocationRelativeTo(null);
      dicesTable.setMinimumSize(new Dimension(830, 400));
      dicesTable.setMaximumSize(new Dimension(1280, 720));
      dicesTable.setTitle(DatabaseConstants.DICES + " Information");
      Effects.fadeIn(dicesTable);
      dicesTable.setVisible(true);
    }

    return aux;
  }

  public void notesTableAP() {
    notesTable.cleanTable();

    try {
      OlderRepository.readTable(notesTable.viewTable, Queries.getAllData(Format.tableName(
              DatabaseConstants.NOTES)),
          true);
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.NOTES);
    }

    notesTable.setSize(830, 400);
    notesTable.setLocationRelativeTo(null);
    notesTable.setMinimumSize(new Dimension(830, 400));
    notesTable.setMaximumSize(new Dimension(1280, 720));
    notesTable.setTitle(DatabaseConstants.NOTES + " Information");
    Effects.fadeIn(notesTable);
    notesTable.setVisible(true);
  }

  public void puzzleTableAP(Dialog parent) {
    puzzleTable.cleanTable();

    try {
      OlderRepository.readTable(puzzleTable.viewTable, Queries.getAllData(Format.tableName(
              DatabaseConstants.PUZZLE)),
          true);
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.PUZZLE);
    }

    puzzleTable.setSize(830, 400);
    puzzleTable.setLocationRelativeTo(null);
    puzzleTable.setMinimumSize(new Dimension(830, 400));
    puzzleTable.setMaximumSize(new Dimension(1280, 720));
    puzzleTable.setTitle(DatabaseConstants.PUZZLE + " Information");
    parent.setVisible(false);
    Effects.fadeIn(puzzleTable);
    puzzleTable.setVisible(true);
  }

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
            ArrayData.getPathBackground(i),
            ArrayData.getWidthBackground(i),
            ArrayData.getLongBackground(i),
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
        ArrayData.getWidthBackground(background - 1),
        ArrayData.getLongBackground(background - 1)
    );
    window.add(model.getWelcome(), BorderLayout.CENTER);

    changeBackgroundAP(
        model,
        ArrayData.getPathBackground(background - 1),
        ArrayData.getWidthBackground(background - 1),
        ArrayData.getLongBackground(background - 1),
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
}
