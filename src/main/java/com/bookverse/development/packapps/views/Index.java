package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Core.TEXT_COLOR;
import static com.bookverse.development.packapps.core.Core.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.ArrayData.PATH_IMAGES;
import static com.bookverse.development.packapps.utils.ViewConstants.DICES;
import static com.bookverse.development.packapps.utils.ViewConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.utils.ViewConstants.HANGMAN;
import static com.bookverse.development.packapps.utils.ViewConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.ViewConstants.LOANS;
import static com.bookverse.development.packapps.utils.ViewConstants.NOTES;
import static com.bookverse.development.packapps.utils.ViewConstants.PURCHASES;
import static com.bookverse.development.packapps.utils.ViewConstants.PUZZLE;
import static com.bookverse.development.packapps.utils.ViewConstants.RECORDS;
import static com.bookverse.development.packapps.utils.ViewConstants.SALES;

import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.ArrayData;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.Querys;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.scene.control.Alert;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Index extends JFrame implements ActionListener {

  protected static int background = 10;
  private static Resources resources = new Resources();
  private static JLabel welcome;
  protected HangmanTable hangmanTable = new HangmanTable(this, true);
  protected GuessNumberTable guessNumberTable = new GuessNumberTable(this, true);
  protected PuzzleTable puzzleTable = new PuzzleTable(this, true);
  protected DicesTable dicesTable = new DicesTable(this, true);
  protected NotesTable notesTable = new NotesTable(this, true);
  protected InventarioTabla inventoryTable = new InventarioTabla(this, true);
  protected RegistradoraTabla cashRegisterTable = new RegistradoraTabla(this, true);
  protected PrestamosTabla loansTable = new PrestamosTabla(this, true);
  protected ComprasTabla purchasesTable = new ComprasTabla(this, true);
  protected VentasTabla salesTable = new VentasTabla(this, true);
  protected JMenuItem[] wallpapers = new JMenuItem[14];
  protected JMenu changeBackground;
  protected JMenuItem moreBacklog, moreSystems, moreBookverse, darkMode, textureMode, mintMode, classicMode, macMode, grayMode,
      texts, guessNumber, guessNumberHard, hangman, structures, dices, buyAndSell, numbers, puzzle4x4, puzzle5x5, puzzle6x6,
      roulette, triquiPvsP, triquiPvsCPU, tables, notes, yes_exit, email, comment, guessNumberTXT, hangmanTXT, dicesTXT, notesTXT,
      inventoryTXT, purchasesTXT, salesTXT, cashRegisterTXT, loansTXT, puzzleTXT, guessNumberEXCEL, hangmanEXCEL, dicesEXCEL, notesEXCEL,
      inventoryEXCEL, purchasesEXCEL, salesEXCEL, cashRegisterEXCEL, loansEXCEL, puzzleEXCEL, guessNumberPDF, hangmanPDF, dicesPDF,
      notesPDF, inventoryPDF, purchasesPDF, salesPDF, cashRegisterPDF, loansPDF, puzzlePDF, read, timesheet;
  private boolean isWork = true;

  public Index() {
    createComponents();
  }

  public static void main(String[] args) {

    try {

      UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");

      UIManager.put("PasswordField.border", resources.core.MEDIO);
      UIManager.put("PasswordField.font", resources.core.MEDIUM);

      UIManager.put("TextField.border", resources.core.MEDIO);
      UIManager.put("TextField.font", resources.core.MEDIUM);

      UIManager.put("FileChooser.saveButtonText", "Save");
      UIManager.put("FileChooser.cancelButtonText", "Cancel");

      UIManager.put("RadioButton.font", resources.core.MEDIUM);

      UIManager.put("TextArea.font", resources.core.MEDIUM);

      UIManager.put("ComboBox.font", resources.core.MEDIUM);
      UIManager.put("ComboBox.foreground", TEXT_COLOR);

      UIManager.put("ScrollPane.border", resources.core.MEDIO);

      UIManager.put("MenuItem.foreground", TEXT_COLOR);
      UIManager.put("MenuItem.font", resources.core.MEDIUM);

      UIManager.put("Menu.foreground", MAIN_COLOR);
      UIManager.put("Menu.font", resources.core.MEDIUM);

      UIManager.put("Button.font", resources.core.MEDIUM);
      UIManager.put("Button.foreground", Color.BLACK);

      UIManager.put("Table.focusCellHighlightBorder", resources.core.MEDIO);
      UIManager.put("TableHeader.foreground", MAIN_COLOR);
      UIManager.put("TableHeader.font", resources.core.MEDIUM);
      UIManager.put("Table.font", resources.core.MEDIUM);
      UIManager.put("Table.foreground", TEXT_COLOR);

      UIManager.put("OptionPane.okButtonText", "Done");
      UIManager.put("OptionPane.cancelButtonText", "No, thanks.");
      UIManager.put("OptionPane.yesButtonText", "Yes, it is okay.");
      UIManager.put("OptionPane.noButtonText", "No, thanks.");
      UIManager.put("OptionPane.messageFont", resources.core.MEDIUM);
      UIManager.put("OptionPane.buttonFont", resources.core.MEDIUM);
      UIManager.put("OptionPane.messageForeground", TEXT_COLOR);

    } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
        | IllegalAccessException e) {
      Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, e);
    }

    Index window = new Index();

    welcome = new JLabel();
    window.setSize(ArrayData.WIDTH_IMAGES[background - 1], ArrayData.LONG_IMAGES[background - 1]);
    window.add(welcome, BorderLayout.CENTER);
    window.changeBackgroundAP(PATH_IMAGES[background - 1], ArrayData.WIDTH_IMAGES[background - 1],
        ArrayData.LONG_IMAGES[background - 1]);

    window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(resources.core.getTitle());
    window.wallpapers[background - 1].setForeground(MAIN_COLOR);
    window.textureMode.setForeground(MAIN_COLOR);
    resources.core.fadeIn(window);
    window.setVisible(true);
  }

  private void createComponents() {

    setIconImage(new ImageIcon(resources.getImage("more.png")).getImage());

    JMenuBar menuBar = new JMenuBar();

    JMenu about = resources.getMenu("About", "about");
    read = resources.getMenuItem("Developer", "developer", this);

    JMenu more = resources.getMenu("See More", "more");
    moreSystems = resources.getMenuItem("My Systems", "mysystems", this);
    moreBacklog = resources.getMenuItem("Backlog", "profiles", this);
    moreBookverse = resources.getMenuItem("Bookverse", "books", this);

    more.add(moreBookverse);
    more.addSeparator();
    more.add(moreBacklog);
    more.addSeparator();
    more.add(moreSystems);

    about.add(read);
    about.addSeparator();
    about.add(more);

    JMenu exit = resources.getMenu("Exit", "exit");
    yes_exit = resources.getMenuItem("Are you sure?", "salir", this);

    JMenu send = resources.getMenu("Send Feedback", "send");
    email = resources.getMenuItem("Email (priority)", "email", this);
    comment = resources.getMenuItem("Comment (simple)", "coment", this);

    send.add(comment);
    send.addSeparator();
    send.add(email);

    exit.add(yes_exit);
    exit.addSeparator();
    exit.add(send);

    JMenu apps = resources.getMenu("Games", "games");
    hangman = resources.getMenuItem(HANGMAN, "ahorcado", this);
    dices = resources.getMenuItem(DICES, "dado", this);
    roulette = resources.getMenuItem("Roulette", "ruleta", this);

    JMenu guessNumberMenu = resources.getMenu(GUESS_NUMBER, "adivinar");
    this.guessNumber = resources.getMenuItem("Easy", "easy", this);
    guessNumberHard = resources.getMenuItem("Hard", "hard", this);

    guessNumberMenu.add(this.guessNumber);
    guessNumberMenu.addSeparator();
    guessNumberMenu.add(guessNumberHard);

    JMenu puzzle = resources.getMenu(PUZZLE, "rompecabezas");
    puzzle4x4 = resources.getMenuItem("Easy", "easy", this);
    puzzle5x5 = resources.getMenuItem("Medium", "medio", this);
    puzzle6x6 = resources.getMenuItem("Hard", "hard", this);

    puzzle.add(puzzle4x4);
    puzzle.addSeparator();
    puzzle.add(puzzle5x5);
    puzzle.addSeparator();
    puzzle.add(puzzle6x6);

    JMenu triqui = resources.getMenu("Triqui", "triqui");
    triquiPvsP = resources.getMenuItem("Player vs Player", "jvsj", this);
    triquiPvsCPU = resources.getMenuItem("Player vs CPU (beta)", "jvscpu", this);

    triqui.add(triquiPvsP);
    triqui.addSeparator();
    triqui.add(triquiPvsCPU);

    apps.add(guessNumberMenu);
    apps.addSeparator();
    apps.add(hangman);
    apps.addSeparator();
    apps.add(dices);
    apps.addSeparator();
    apps.add(puzzle);
    apps.addSeparator();
    apps.add(roulette);
    apps.addSeparator();
    apps.add(triqui);

    JMenu scores = resources.getMenu("Data", "data");
    tables = resources.getMenuItem("Database", "tabla", this);

    scores.add(tables);

    JMenu tools = resources.getMenu("Tools", "tools");
    buyAndSell = resources.getMenuItem("Buy and Sell", "compraventa", this);
    structures = resources.getMenuItem("Structures", "estructuras", this);
    numbers = resources.getMenuItem("Numbers", "numeritos", this);
    notes = resources.getMenuItem(NOTES, "notas", this);
    texts = resources.getMenuItem("Texts", "textos", this);

    changeBackground = resources.getMenu("Background", "background");

    IntStream.range(0, wallpapers.length).forEach(i -> {
      wallpapers[i] = new JMenuItem("Image " + (i + 1));
      wallpapers[i].setForeground(TEXT_COLOR);
      wallpapers[i].setIcon(new ImageIcon(resources.getImage("backs.png")));
      wallpapers[i].addActionListener(this);
      changeBackground.add(wallpapers[i]);
      changeBackground.addSeparator();
    });

    JMenu export = resources.getMenu("Export Data", "export");

    JMenu exportTXT = resources.getMenu("Document TXT", "txt");
    guessNumberTXT = resources.getMenuItem(GUESS_NUMBER, "adivinar", this);
    hangmanTXT = resources.getMenuItem(HANGMAN, "ahorcado", this);
    purchasesTXT = resources.getMenuItem(PURCHASES, "comprar", this);
    dicesTXT = resources.getMenuItem(DICES, "dado", this);
    notesTXT = resources.getMenuItem(NOTES, "notas", this);
    puzzleTXT = resources.getMenuItem(PUZZLE, "rompecabezas", this);
    inventoryTXT = resources.getMenuItem(INVENTORY, "inventario", this);
    cashRegisterTXT = resources.getMenuItem(RECORDS, "registradora", this);
    loansTXT = resources.getMenuItem(LOANS, "prestamos", this);
    salesTXT = resources.getMenuItem(SALES, "vender", this);

    exportTXT.add(guessNumberTXT);
    exportTXT.addSeparator();
    exportTXT.add(hangmanTXT);
    exportTXT.addSeparator();
    exportTXT.add(purchasesTXT);
    exportTXT.addSeparator();
    exportTXT.add(dicesTXT);
    exportTXT.addSeparator();
    exportTXT.add(inventoryTXT);
    exportTXT.addSeparator();
    exportTXT.add(notesTXT);
    exportTXT.addSeparator();
    exportTXT.add(loansTXT);
    exportTXT.addSeparator();
    exportTXT.add(cashRegisterTXT);
    exportTXT.addSeparator();
    exportTXT.add(puzzleTXT);
    exportTXT.addSeparator();
    exportTXT.add(salesTXT);

    JMenu exportEXCEL = resources.getMenu("Document XLS", "excel");
    guessNumberEXCEL = resources.getMenuItem(GUESS_NUMBER, "adivinar", this);
    hangmanEXCEL = resources.getMenuItem(HANGMAN, "ahorcado", this);
    purchasesEXCEL = resources.getMenuItem(PURCHASES, "comprar", this);
    dicesEXCEL = resources.getMenuItem(DICES, "dado", this);
    notesEXCEL = resources.getMenuItem(NOTES, "notas", this);
    puzzleEXCEL = resources.getMenuItem(PUZZLE, "rompecabezas", this);
    inventoryEXCEL = resources.getMenuItem(INVENTORY, "inventario", this);
    cashRegisterEXCEL = resources.getMenuItem(RECORDS, "registradora", this);
    loansEXCEL = resources.getMenuItem(LOANS, "prestamos", this);
    salesEXCEL = resources.getMenuItem(SALES, "vender", this);

    exportEXCEL.add(guessNumberEXCEL);
    exportEXCEL.addSeparator();
    exportEXCEL.add(hangmanEXCEL);
    exportEXCEL.addSeparator();
    exportEXCEL.add(purchasesEXCEL);
    exportEXCEL.addSeparator();
    exportEXCEL.add(dicesEXCEL);
    exportEXCEL.addSeparator();
    exportEXCEL.add(inventoryEXCEL);
    exportEXCEL.addSeparator();
    exportEXCEL.add(notesEXCEL);
    exportEXCEL.addSeparator();
    exportEXCEL.add(loansEXCEL);
    exportEXCEL.addSeparator();
    exportEXCEL.add(cashRegisterEXCEL);
    exportEXCEL.addSeparator();
    exportEXCEL.add(puzzleEXCEL);
    exportEXCEL.addSeparator();
    exportEXCEL.add(salesEXCEL);

    JMenu exportPDF = resources.getMenu("Document PDF", "pdf");
    guessNumberPDF = resources.getMenuItem(GUESS_NUMBER, "adivinar", this);
    hangmanPDF = resources.getMenuItem(HANGMAN, "ahorcado", this);
    purchasesPDF = resources.getMenuItem(PURCHASES, "comprar", this);
    dicesPDF = resources.getMenuItem(DICES, "dado", this);
    notesPDF = resources.getMenuItem(NOTES, "notas", this);
    puzzlePDF = resources.getMenuItem(PUZZLE, "rompecabezas", this);
    inventoryPDF = resources.getMenuItem(INVENTORY, "inventario", this);
    cashRegisterPDF = resources.getMenuItem(RECORDS, "registradora", this);
    loansPDF = resources.getMenuItem(LOANS, "prestamos", this);
    salesPDF = resources.getMenuItem(SALES, "vender", this);

    exportPDF.add(guessNumberPDF);
    exportPDF.addSeparator();
    exportPDF.add(hangmanPDF);
    exportPDF.addSeparator();
    exportPDF.add(purchasesPDF);
    exportPDF.addSeparator();
    exportPDF.add(dicesPDF);
    exportPDF.addSeparator();
    exportPDF.add(inventoryPDF);
    exportPDF.addSeparator();
    exportPDF.add(notesPDF);
    exportPDF.addSeparator();
    exportPDF.add(loansPDF);
    exportPDF.addSeparator();
    exportPDF.add(cashRegisterPDF);
    exportPDF.addSeparator();
    exportPDF.add(puzzlePDF);
    exportPDF.addSeparator();
    exportPDF.add(salesPDF);

    export.add(exportTXT);
    export.addSeparator();
    export.add(exportPDF);
    export.addSeparator();
    export.add(exportEXCEL);

    JMenu tasks = resources.getMenu("Tasks", "task");
    timesheet = resources.getMenuItem("Timesheet Entry", "timesheet", this);
    tasks.add(timesheet);

    JMenu mode = resources.getMenu("UI", "mode");
    darkMode = resources.getMenuItem("Dark", "dark", this);
    textureMode = resources.getMenuItem("Texture", "texture", this);
    macMode = resources.getMenuItem("Mac OS", "mac", this);
    grayMode = resources.getMenuItem("Metallic", "gray", this);
    mintMode = resources.getMenuItem("Mint", "mint", this);
    classicMode = resources.getMenuItem("Classic", "classic", this);

    mode.add(classicMode);
    mode.addSeparator();
    mode.add(darkMode);
    mode.addSeparator();
    mode.add(macMode);
    mode.addSeparator();
    mode.add(grayMode);
    mode.addSeparator();
    mode.add(mintMode);
    mode.addSeparator();
    mode.add(textureMode);

    tools.add(changeBackground);
    tools.addSeparator();
    tools.add(buyAndSell);
    tools.addSeparator();
    tools.add(structures);
    tools.addSeparator();
    tools.add(export);
    tools.addSeparator();
    tools.add(notes);
    tools.addSeparator();
    tools.add(numbers);
    tools.addSeparator();
    tools.add(texts);
    tools.addSeparator();
    tools.add(tasks);
    tools.addSeparator();
    tools.add(mode);

    menuBar.add(apps);
    menuBar.add(scores);
    menuBar.add(tools);
    menuBar.add(about);
    menuBar.add(exit);

    add(menuBar, BorderLayout.NORTH);
  }

  protected void changeBackgroundAP(String name, int width, int length) {

    try {
      resources.core.fadeIn(this);
      setVisible(false);
      ((JPanel) getContentPane()).setOpaque(false);
      welcome.setIcon(new ImageIcon(resources.getImage(name)));
      welcome.setSize(width, length);
      setSize(width, length + 80);
      setLocationRelativeTo(null);
      isWork = false;

      for (JMenuItem image : wallpapers) {
        image.setForeground(TEXT_COLOR);
      }

    } catch (Exception ignored) {
      Alerts.error(ignored, "Change Background");
    }
  }

  protected void guessNumberTableAP() {

    guessNumberTable.cleanTable();

    try {
      resources.database.readTable(guessNumberTable.viewTable,
          Querys.getAllData(Format.tableName(GUESS_NUMBER)), true);
    } catch (Exception e1) {
      Alerts.error(e1, GUESS_NUMBER);
    }

    guessNumberTable.setSize(830, 400);
    guessNumberTable.setLocationRelativeTo(null);
    guessNumberTable.setMinimumSize(new Dimension(830, 400));
    guessNumberTable.setMaximumSize(new Dimension(1280, 720));
    guessNumberTable.setTitle(GUESS_NUMBER + " Information");
    resources.core.fadeIn(guessNumberTable);
    guessNumberTable.setVisible(true);
  }

  protected void hangmanTableAP() {
    hangmanTable.cleanTable();

    try {
      resources.database
          .readTable(hangmanTable.viewTable, Querys.getAllData(Format.tableName(HANGMAN)), true);
    } catch (Exception e1) {
      Alerts.error(e1, HANGMAN);
    }

    hangmanTable.setSize(830, 400);
    hangmanTable.setLocationRelativeTo(null);
    hangmanTable.setMinimumSize(new Dimension(830, 400));
    hangmanTable.setMaximumSize(new Dimension(1280, 720));
    hangmanTable.setTitle(HANGMAN + " Information");
    resources.core.fadeIn(hangmanTable);
    hangmanTable.setVisible(true);
  }

  protected boolean dicesTableAP() {
    boolean aux = false;

    dicesTable.cleanTable();

    try {
      aux = resources.database.readTable(dicesTable.viewTable, Querys.getAllData(DICES), true);
    } catch (Exception e1) {
      Alerts.error(e1, DICES);
    }

    if (aux) {
      setVisible(false);
      dicesTable.setSize(830, 400);
      dicesTable.setLocationRelativeTo(null);
      dicesTable.setMinimumSize(new Dimension(830, 400));
      dicesTable.setMaximumSize(new Dimension(1280, 720));
      dicesTable.setTitle(DICES + " Information");
      resources.core.fadeIn(dicesTable);
      dicesTable.setVisible(true);
    }

    return aux;
  }

  protected void notesTableAP() {
    notesTable.cleanTable();

    try {
      resources.database
          .readTable(notesTable.viewTable, Querys.getAllData(Format.tableName(NOTES)), true);
    } catch (Exception e1) {
      Alerts.error(e1, NOTES);
    }

    notesTable.setSize(830, 400);
    notesTable.setLocationRelativeTo(null);
    notesTable.setMinimumSize(new Dimension(830, 400));
    notesTable.setMaximumSize(new Dimension(1280, 720));
    notesTable.setTitle(NOTES + " Information");
    resources.core.fadeIn(notesTable);
    notesTable.setVisible(true);
  }

  protected void puzzleTableAP() {
    puzzleTable.cleanTable();

    try {
      resources.database
          .readTable(puzzleTable.viewTable, Querys.getAllData(Format.tableName(PUZZLE)), true);
    } catch (Exception e1) {
      Alerts.error(e1, PUZZLE);
    }

    puzzleTable.setSize(830, 400);
    puzzleTable.setLocationRelativeTo(null);
    puzzleTable.setMinimumSize(new Dimension(830, 400));
    puzzleTable.setMaximumSize(new Dimension(1280, 720));
    puzzleTable.setTitle(PUZZLE + " Information");
    setVisible(false);
    resources.core.fadeIn(puzzleTable);
    puzzleTable.setVisible(true);
  }

  private void paintUI() {
    darkMode.setForeground(TEXT_COLOR);
    textureMode.setForeground(TEXT_COLOR);
    mintMode.setForeground(TEXT_COLOR);
    classicMode.setForeground(TEXT_COLOR);
    macMode.setForeground(TEXT_COLOR);
    grayMode.setForeground(TEXT_COLOR);
  }

  private void paintBackground(ActionEvent e) {

    IntStream.range(0, wallpapers.length).filter(i -> e.getSource() == wallpapers[i]).forEach(i -> {
      if (wallpapers[i].getForeground() != MAIN_COLOR) {
        changeBackgroundAP(PATH_IMAGES[i], ArrayData.WIDTH_IMAGES[i], ArrayData.LONG_IMAGES[i]);
        wallpapers[i].setForeground(MAIN_COLOR);
        background = i + 1;
        setVisible(true);
      } else {
        Alerts.elementApplied(false);
      }
    });
  }

  private void setUI(String selectedUI) {

    paintUI();

    switch (selectedUI) {

      case "Gray":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

          UIManager.put("ComboBox.foreground", new Color(0, 0, 0));
          UIManager.put("MenuItem.foreground", TEXT_COLOR);
          UIManager.put("Menu.foreground", MAIN_COLOR);
          UIManager.put("Button.foreground", new Color(0, 0, 0));

          UIManager.put("Table.focusCellHighlightBorder", resources.core.MEDIO);
          UIManager.put("TableHeader.foreground", MAIN_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(PATH_IMAGES[background - 1],
              ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.grayMode.setForeground(MAIN_COLOR);
          resources.core.fadeIn(window);
          window.setVisible(true);
          Alerts.changeUI("Gray");

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;

      case "Texture":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");

          UIManager.put("MenuItem.foreground", TEXT_COLOR);
          UIManager.put("Menu.foreground", MAIN_COLOR);

          UIManager.put("ComboBox.foreground", TEXT_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
          UIManager.put("Button.foreground", Color.BLACK);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(PATH_IMAGES[background - 1],
              ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.textureMode.setForeground(MAIN_COLOR);
          resources.core.fadeIn(window);
          window.setVisible(true);
          Alerts.changeUI("Texture");

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;

      case "Dark":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");

          UIManager.put("MenuItem.foreground", TEXT_COLOR);
          UIManager.put("Menu.foreground", MAIN_COLOR);

          UIManager.put("ComboBox.foreground", Color.WHITE);
          UIManager.put("Table.foreground", Color.WHITE);
          UIManager.put("OptionPane.messageForeground", Color.WHITE);
          UIManager.put("Button.foreground", Color.WHITE);
          UIManager.put("MenuItem.foreground", TEXT_COLOR);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(PATH_IMAGES[background - 1],
              ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.darkMode.setForeground(MAIN_COLOR);
          resources.core.fadeIn(window);
          window.setVisible(true);

          Alerts.changeUI("Dark");


        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;

      case "Mac":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

          UIManager.put("MenuItem.foreground", TEXT_COLOR);
          UIManager.put("Menu.foreground", MAIN_COLOR);

          UIManager.put("ComboBox.foreground", TEXT_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
          UIManager.put("Button.foreground", Color.BLACK);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(PATH_IMAGES[background - 1],
              ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.macMode.setForeground(MAIN_COLOR);
          resources.core.fadeIn(window);
          window.setVisible(true);
          Alerts.changeUI("Mac OS");

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;

      case "Mint":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

          UIManager.put("ComboBox.foreground", Color.BLACK);

          UIManager.put("MenuItem.foreground", TEXT_COLOR);
          UIManager.put("Menu.foreground", MAIN_COLOR);

          UIManager.put("Button.foreground", Color.BLACK);

          UIManager.put("Table.focusCellHighlightBorder", resources.core.MEDIO);
          UIManager.put("TableHeader.foreground", MAIN_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(PATH_IMAGES[background - 1],
              ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.mintMode.setForeground(MAIN_COLOR);
          resources.core.fadeIn(window);
          window.setVisible(true);
          Alerts.changeUI("Mint");

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;

      case "Classic":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");

          UIManager.put("ComboBox.foreground", Color.BLACK);

          UIManager.put("Button.foreground", Color.BLACK);

          UIManager.put("MenuItem.foreground", TEXT_COLOR);
          UIManager.put("Menu.foreground", MAIN_COLOR);

          UIManager.put("Table.focusCellHighlightBorder", resources.core.MEDIO);
          UIManager.put("TableHeader.foreground", MAIN_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(PATH_IMAGES[background - 1],
              ArrayData.WIDTH_IMAGES[background - 1],
              ArrayData.LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.classicMode.setForeground(MAIN_COLOR);
          resources.core.fadeIn(window);
          window.setVisible(true);
          Alerts.changeUI("Classic");

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    isWork = true;

    paintBackground(e);

    if (e.getSource() == yes_exit) {
      resources.core.fadeOut(this);
    } else if (e.getSource() == timesheet) {
      new Timesheet(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == grayMode) {

      if (grayMode.getForeground() != MAIN_COLOR) {
        setUI("Gray");
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == darkMode) {

      if (darkMode.getForeground() != MAIN_COLOR) {
        setUI("Dark");
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == textureMode) {

      if (textureMode.getForeground() != MAIN_COLOR) {
        setUI("Texture");
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == macMode) {

      if (macMode.getForeground() != MAIN_COLOR) {
        setUI("Mac");
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == mintMode) {

      if (mintMode.getForeground() != MAIN_COLOR) {
        setUI("Mint");
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == classicMode) {

      if (classicMode.getForeground() != MAIN_COLOR) {
        setUI("Classic");
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == email) {
      new Email(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == comment) {
      new Comment(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == texts) {
      new Texts(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == guessNumber) {
      new GuessNumber(this, true, false).start(this);
      setVisible(true);
    } else if (e.getSource() == guessNumberHard) {
      new GuessNumber(this, true, true).start(this);
      setVisible(true);
    } else if (e.getSource() == triquiPvsP) {
      new Triqui(this, true, false).start(this);
      setVisible(true);
    } else if (e.getSource() == triquiPvsCPU) {
      new Triqui(this, true, true).start(this);
      setVisible(true);
    } else if (e.getSource() == hangman) {
      new Hangman(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == numbers) {
      new Numbers(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == puzzle4x4) {
      new Puzzle(this, true, 4, 55, 3).start(this);
      setVisible(true);
    } else if (e.getSource() == puzzle5x5) {
      new Puzzle(this, true, 5, 50, 6).start(this);
      setVisible(true);
    } else if (e.getSource() == puzzle6x6) {
      new Puzzle(this, true, 6, 45, 10).start(this);
      setVisible(true);
    } else if (e.getSource() == structures) {
      new Structures(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == dices) {
      new Dices(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == notes) {
      new Notes(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == buyAndSell) {
      new Login(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == read) {
      new Card(this, true).start(this);
      setVisible(true);
    } else if (resources.core.comprobarConexion("Connect to see more!", isWork)) {

      if (e.getSource() == roulette) {

        resources.core.instruccionesRuleta();

        try {
          Desktop.getDesktop()
              .browse(new URL("https://mypackapps.000webhostapp.com/ruleta.php").toURI());
        } catch (Exception ex) {
          Alerts.error(ex, "Opening URL");
        }

      } else if (e.getSource() == moreSystems) {

        try {
          Desktop.getDesktop().browse(new URL("https://mypackapps.000webhostapp.com").toURI());
        } catch (Exception ex) {
          Alerts.error(ex, "Opening URL");
        }

      } else if (e.getSource() == moreBookverse) {

        try {
          Desktop.getDesktop().browse(new URL("http://bookverse.vzpla.net").toURI());
        } catch (Exception ex) {
          Alerts.error(ex, "Opening URL");
        }

      } else if (e.getSource() == moreBacklog) {
        Alerts.message("Recuerdaaaa", "Poner la app de backlog en un servidor y poner acá el link!! XD");
      } else if (e.getSource() == tables) {

        if (dicesTableAP()) {
          setVisible(true);
        }

      } else if (e.getSource() == guessNumberTXT) {

        try {
          guessNumberTable.cleanTable();
          resources.core
              .txt(guessNumberTable.viewTable, Querys.getAllData(Format.tableName(GUESS_NUMBER)),
                  ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanTXT) {

        try {
          hangmanTable.cleanTable();
          resources.core
              .txt(hangmanTable.viewTable, Querys.getAllData(Format.tableName(HANGMAN)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, HANGMAN);
        }

      } else if (e.getSource() == purchasesTXT) {

        try {
          purchasesTable.limpiarTabla();
          resources.core.txt(purchasesTable.comprasTab,
              Querys.getAllData(Format.tableName(PURCHASES)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, PURCHASES);
        }

      } else if (e.getSource() == salesTXT) {

        try {
          salesTable.limpiarTabla();
          resources.core.txt(salesTable.ventasTab,
              Querys.getAllData(Format.tableName(SALES)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, SALES);
        }

      } else if (e.getSource() == dicesTXT) {

        try {
          dicesTable.cleanTable();
          resources.core
              .txt(dicesTable.viewTable, Querys.getAllData(Format.tableName(DICES)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesTXT) {

        try {
          notesTable.cleanTable();
          resources.core
              .txt(notesTable.viewTable, Querys.getAllData(Format.tableName(NOTES)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzleTXT) {

        try {
          puzzleTable.cleanTable();
          resources.core
              .txt(puzzleTable.viewTable, Querys.getAllData(Format.tableName(PUZZLE)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryTXT) {

        try {
          inventoryTable.limpiarTabla();
          resources.core
              .txt(inventoryTable.inventarioTab, Querys.getAllData(Format.tableName(INVENTORY)),
                  ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, INVENTORY);
        }
      } else if (e.getSource() == cashRegisterTXT) {

        try {
          cashRegisterTable.limpiarTabla();
          resources.core.txt(cashRegisterTable.registradoraTab,
              Querys.getAllData(Format.tableName(RECORDS)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, RECORDS);
        }
      } else if (e.getSource() == loansTXT) {

        try {
          loansTable.limpiarTabla();
          resources.core.txt(loansTable.prestamosTab,
              Querys.getAllData(Format.tableName(LOANS)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, LOANS);
        }

      } else if (e.getSource() == guessNumberPDF) {

        try {
          guessNumberTable.cleanTable();
          resources.core.pdf(guessNumberTable.viewTable, GUESS_NUMBER,
              Querys.getAllData(Format.tableName(GUESS_NUMBER)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanPDF) {

        try {
          hangmanTable.cleanTable();
          resources.core
              .pdf(hangmanTable.viewTable, HANGMAN, Querys.getAllData(Format.tableName(HANGMAN)),
                  ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, HANGMAN);
        }

      } else if (e.getSource() == purchasesPDF) {

        try {
          purchasesTable.limpiarTabla();
          resources.core.pdf(purchasesTable.comprasTab, PURCHASES,
              Querys.getAllData(Format.tableName(PURCHASES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, PURCHASES);
        }

      } else if (e.getSource() == salesPDF) {

        try {
          salesTable.limpiarTabla();
          resources.core.pdf(salesTable.ventasTab, SALES,
              Querys.getAllData(Format.tableName(SALES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, SALES);
        }

      } else if (e.getSource() == dicesPDF) {

        try {
          dicesTable.cleanTable();
          resources.core.pdf(dicesTable.viewTable, DICES, Querys.getAllData(
              Format.tableName(DICES)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesPDF) {

        try {
          notesTable.cleanTable();
          resources.core.pdf(notesTable.viewTable, NOTES, Querys.getAllData(Format.tableName(NOTES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzlePDF) {

        try {
          puzzleTable.cleanTable();
          resources.core.pdf(puzzleTable.viewTable, PUZZLE,
              Querys.getAllData(Format.tableName(PUZZLE)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryPDF) {

        try {
          inventoryTable.limpiarTabla();
          resources.core
              .pdf(inventoryTable.inventarioTab, INVENTORY,
                  Querys.getAllData(Format.tableName(INVENTORY)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, INVENTORY);
        }
      } else if (e.getSource() == cashRegisterPDF) {

        try {
          cashRegisterTable.limpiarTabla();
          resources.core.pdf(cashRegisterTable.registradoraTab, RECORDS,
              Querys.getAllData(Format.tableName(RECORDS)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, RECORDS);
        }
      } else if (e.getSource() == loansPDF) {

        try {
          loansTable.limpiarTabla();
          resources.core.pdf(loansTable.prestamosTab, LOANS,
              Querys.getAllData(Format.tableName(LOANS)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, LOANS);
        }
      } else if (e.getSource() == guessNumberEXCEL) {

        try {
          guessNumberTable.cleanTable();
          resources.core
              .excel(guessNumberTable.viewTable, Querys.getAllData(Format.tableName(GUESS_NUMBER)),
                  ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanEXCEL) {

        try {
          hangmanTable.cleanTable();
          resources.core
              .excel(hangmanTable.viewTable, Querys.getAllData(Format.tableName(HANGMAN)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, HANGMAN);
        }

      } else if (e.getSource() == purchasesEXCEL) {

        try {
          purchasesTable.limpiarTabla();
          resources.core.excel(purchasesTable.comprasTab,
              Querys.getAllData(Format.tableName(PURCHASES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, PURCHASES);
        }

      } else if (e.getSource() == salesEXCEL) {

        try {
          salesTable.limpiarTabla();
          resources.core.excel(salesTable.ventasTab,
              Querys.getAllData(Format.tableName(SALES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, SALES);
        }

      } else if (e.getSource() == dicesEXCEL) {

        try {
          dicesTable.cleanTable();
          resources.core
              .excel(dicesTable.viewTable, Querys.getAllData(Format.tableName(DICES)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesEXCEL) {

        try {
          notesTable.cleanTable();
          resources.core
              .excel(notesTable.viewTable, Querys.getAllData(Format.tableName(NOTES)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzleEXCEL) {

        try {
          puzzleTable.cleanTable();
          resources.core
              .excel(puzzleTable.viewTable, Querys.getAllData(Format.tableName(PUZZLE)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryEXCEL) {

        try {
          inventoryTable.limpiarTabla();
          resources.core
              .excel(inventoryTable.inventarioTab, Querys.getAllData(Format.tableName(INVENTORY)),
                  ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, INVENTORY);
        }
      } else if (e.getSource() == cashRegisterEXCEL) {

        try {
          cashRegisterTable.limpiarTabla();
          resources.core.excel(cashRegisterTable.registradoraTab,
              Querys.getAllData(Format.tableName(RECORDS)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, RECORDS);
        }
      } else if (e.getSource() == loansEXCEL) {

        try {
          loansTable.limpiarTabla();
          resources.core.excel(loansTable.prestamosTab,
              Querys.getAllData(Format.tableName(LOANS)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, LOANS);
        }
      }
    }
  }
}