package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.BORDER_BLUE;
import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;
import static com.bookverse.development.packapps.utils.DatabaseConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.utils.DatabaseConstants.DICES;
import static com.bookverse.development.packapps.utils.DatabaseConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.utils.DatabaseConstants.HANGMAN;
import static com.bookverse.development.packapps.utils.DatabaseConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.DatabaseConstants.LOANS;
import static com.bookverse.development.packapps.utils.DatabaseConstants.NOTES;
import static com.bookverse.development.packapps.utils.DatabaseConstants.PURCHASES;
import static com.bookverse.development.packapps.utils.DatabaseConstants.PUZZLE;
import static com.bookverse.development.packapps.utils.DatabaseConstants.QUESTIONS_AND_ANSWERS;
import static com.bookverse.development.packapps.utils.DatabaseConstants.SALES;

import com.bookverse.development.packapps.automation.utils.StartTests;
import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.models.DataSet;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.ArrayData;
import com.bookverse.development.packapps.utils.Export;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.Queries;
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jetbrains.annotations.NotNull;

public class Index extends JFrame implements ActionListener {

  protected static int background = 2;
  private static Resources resources = new Resources();
  private static JLabel welcome;
  protected HangmanTable hangmanTable = new HangmanTable(this, true);
  protected GuessNumberTable guessNumberTable = new GuessNumberTable(this, true);
  protected PuzzleTable puzzleTable = new PuzzleTable(this, true);
  protected DicesTable dicesTable = new DicesTable(this, true);
  protected NotesTable notesTable = new NotesTable(this, true);
  protected InventoryTable inventoryTable = new InventoryTable(this, true);
  protected CashRegisterTable cashRegisterTable = new CashRegisterTable(this, true);
  protected LoansTable loansTable = new LoansTable(this, true);
  protected PurchasesTable purchasesTable = new PurchasesTable(this, true);
  protected SalesTable salesTable = new SalesTable(this, true);
  protected JMenuItem[] wallpapers = new JMenuItem[14];
  protected JMenu changeBackground;
  protected JMenuItem moreSystems;
  protected JMenuItem moreBookverse;
  protected JMenuItem darkMode;
  protected JMenuItem textureMode;
  protected JMenuItem mintMode;
  protected JMenuItem classicMode;
  protected JMenuItem macMode;
  protected JMenuItem grayMode;
  protected JMenuItem texts;
  protected JMenuItem guessNumber;
  protected JMenuItem guessNumberHard;
  protected JMenuItem hangman;
  protected JMenuItem structures;
  protected JMenuItem dices;
  protected JMenuItem store;
  protected JMenuItem numbers;
  protected JMenuItem puzzle4x4;
  protected JMenuItem puzzle5x5;
  protected JMenuItem puzzle6x6;
  protected JMenuItem roulette;
  protected JMenuItem ticTacToePvsP;
  protected JMenuItem ticTacToePvsCPU;
  protected JMenuItem tables;
  protected JMenuItem notes;
  protected JMenuItem yes_exit;
  protected JMenuItem email;
  protected JMenuItem comment;
  protected JMenuItem guessNumberTXT;
  protected JMenuItem hangmanTXT;
  protected JMenuItem dicesTXT;
  protected JMenuItem notesTXT;
  protected JMenuItem inventoryTXT;
  protected JMenuItem purchasesTXT;
  protected JMenuItem salesTXT;
  protected JMenuItem cashRegisterTXT;
  protected JMenuItem loansTXT;
  protected JMenuItem puzzleTXT;
  protected JMenuItem guessNumberEXCEL;
  protected JMenuItem hangmanEXCEL;
  protected JMenuItem dicesEXCEL;
  protected JMenuItem notesEXCEL;
  protected JMenuItem inventoryEXCEL;
  protected JMenuItem purchasesEXCEL;
  protected JMenuItem salesEXCEL;
  protected JMenuItem cashRegisterEXCEL;
  protected JMenuItem loansEXCEL;
  protected JMenuItem puzzleEXCEL;
  protected JMenuItem guessNumberPDF;
  protected JMenuItem hangmanPDF;
  protected JMenuItem dicesPDF;
  protected JMenuItem notesPDF;
  protected JMenuItem inventoryPDF;
  protected JMenuItem purchasesPDF;
  protected JMenuItem salesPDF;
  protected JMenuItem cashRegisterPDF;
  protected JMenuItem loansPDF;
  protected JMenuItem puzzlePDF;
  protected JMenuItem read;
  protected JMenuItem OCR;
  protected JMenuItem searchBook;
  protected JMenuItem registerUser;
  protected JMenuItem sendWhatsApp;
  protected JMenuItem questionsAndAnswers;
  private boolean isWork = true;

  public Index() {
    createComponents();
  }

  protected static void run() {

    try {

      UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");

      UIManager.put("PasswordField.border", BORDER_BLUE);
      UIManager.put("PasswordField.font", MEDIUM);

      UIManager.put("TextField.border", BORDER_BLUE);
      UIManager.put("TextField.font", MEDIUM);

      UIManager.put("FileChooser.saveButtonText", "Save");
      UIManager.put("FileChooser.cancelButtonText", "Cancel");

      UIManager.put("RadioButton.font", MEDIUM);

      UIManager.put("TextArea.font", MEDIUM);

      UIManager.put("ComboBox.font", MEDIUM);
      UIManager.put("ComboBox.foreground", Color.BLACK);

      UIManager.put("ScrollPane.border", BORDER_BLUE);

      UIManager.put("MenuItem.foreground", Color.WHITE);
      UIManager.put("MenuItem.font", MEDIUM);

      UIManager.put("MenuItem.foreground", TEXT_COLOR);
      UIManager.put("Menu.foreground", MAIN_COLOR);
      UIManager.put("Menu.font", MEDIUM);

      UIManager.put("Button.font", MEDIUM);
      UIManager.put("Button.foreground", Color.BLACK);

      UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
      UIManager.put("TableHeader.foreground", MAIN_COLOR);
      UIManager.put("TableHeader.font", MEDIUM);
      UIManager.put("Table.font", MEDIUM);
      UIManager.put("Table.foreground", TEXT_COLOR);

      UIManager.put("OptionPane.okButtonText", "Done");
      UIManager.put("OptionPane.cancelButtonText", "No, thanks.");
      UIManager.put("OptionPane.yesButtonText", "Yes, it's okay.");
      UIManager.put("OptionPane.noButtonText", "No, thanks.");
      UIManager.put("OptionPane.messageFont", MEDIUM);
      UIManager.put("OptionPane.buttonFont", MEDIUM);
      UIManager.put("OptionPane.messageForeground", TEXT_COLOR);

    } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
        | IllegalAccessException e) {
      Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, e);
    }

    Index window = new Index();

    welcome = new JLabel();
    window.setSize(ArrayData.getWidthBackground(background - 1),
        ArrayData.getLongBackground(background - 1));
    window.add(welcome, BorderLayout.CENTER);
    window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
        ArrayData.getWidthBackground(background - 1),
        ArrayData.getLongBackground(background - 1));

    window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(DataSet.getTitleApp());
    window.wallpapers[background - 1].setForeground(MAIN_COLOR);
    window.classicMode.setForeground(MAIN_COLOR);
    Settings.fadeIn(window);
    window.setVisible(true);
  }

  private void createComponents() {

    setIconImage(new ImageIcon(resources.getImage("more.png")).getImage());

    JMenuBar menuBar = new JMenuBar();

    JMenu about = resources.getMenu("About", "about");
    read = resources.getMenuItem("Developer", "developer", this);

    JMenu more = resources.getMenu("See More", "more");
    moreSystems = resources.getMenuItem("My Systems", "mysystems", this);
    moreBookverse = resources.getMenuItem("Bookverse", "books", this);

    more.add(moreBookverse);
    more.addSeparator();
    more.add(moreSystems);

    about.add(read);
    about.addSeparator();
    about.add(more);

    JMenu exit = resources.getMenu("Exit", "exit");
    yes_exit = resources.getMenuItem("Are you sure?", "salir", this);

    JMenu send = resources.getMenu("Send Feedback", "send");
    email = resources.getMenuItem("Email", "email", this);
    comment = resources.getMenuItem("Comment", "coment", this);

    send.add(comment);
    send.addSeparator();
    send.add(email);

    exit.add(yes_exit);
    exit.addSeparator();
    exit.add(send);

    JMenu games = resources.getMenu("Games", "games");
    hangman = resources.getMenuItem(HANGMAN, "ahorcado", this);
    dices = resources.getMenuItem(DICES, "dado", this);
    roulette = resources.getMenuItem("Roulette", "ruleta", this);

    JMenu guessNumberMenu = resources.getMenu(GUESS_NUMBER, "adivinar");
    guessNumber = resources.getMenuItem("Easy", "easy", this);
    guessNumberHard = resources.getMenuItem("Hard", "hard", this);

    guessNumberMenu.add(guessNumber);
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

    questionsAndAnswers = resources.getMenuItem(QUESTIONS_AND_ANSWERS, "questions", this);

    JMenu ticTacToe = resources.getMenu("Tic Tac Toe", "triqui");
    ticTacToePvsP = resources.getMenuItem("Player vs Player", "jvsj", this);
    ticTacToePvsCPU = resources.getMenuItem("Player vs CPU (beta)", "jvscpu", this);

    ticTacToe.add(ticTacToePvsP);
    ticTacToe.addSeparator();
    ticTacToe.add(ticTacToePvsCPU);

    games.add(dices);
    games.addSeparator();
    games.add(guessNumberMenu);
    games.addSeparator();
    games.add(hangman);
    games.addSeparator();
    games.add(puzzle);
    games.addSeparator();
    games.add(questionsAndAnswers);
    games.addSeparator();
    games.add(roulette);
    games.addSeparator();
    games.add(ticTacToe);

    JMenu scores = resources.getMenu("Data", "data");
    tables = resources.getMenuItem("Database", "tabla", this);

    scores.add(tables);

    JMenu tools = resources.getMenu("Tools", "tools");
    store = resources.getMenuItem("Store", "compraventa", this);
    sendWhatsApp = resources.getMenuItem("Send Message", "whatsapp", this);
    structures = resources.getMenuItem("Structures", "estructuras", this);
    numbers = resources.getMenuItem("Numbers", "numeritos", this);
    notes = resources.getMenuItem(NOTES, "notas", this);
    texts = resources.getMenuItem("Texts", "textos", this);

    JMenu export = resources.getMenu("Export Data", "export");

    JMenu exportTXT = resources.getMenu("Document TXT", "txt");
    guessNumberTXT = resources.getMenuItem(GUESS_NUMBER, "adivinar", this);
    hangmanTXT = resources.getMenuItem(HANGMAN, "ahorcado", this);
    purchasesTXT = resources.getMenuItem(PURCHASES, "comprar", this);
    dicesTXT = resources.getMenuItem(DICES, "dado", this);
    notesTXT = resources.getMenuItem(NOTES, "notas", this);
    puzzleTXT = resources.getMenuItem(PUZZLE, "rompecabezas", this);
    inventoryTXT = resources.getMenuItem(INVENTORY, "inventario", this);
    cashRegisterTXT = resources.getMenuItem(CASH_REGISTER, "registradora", this);
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
    cashRegisterEXCEL = resources.getMenuItem(CASH_REGISTER, "registradora", this);
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
    cashRegisterPDF = resources.getMenuItem(CASH_REGISTER, "registradora", this);
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
    searchBook = resources.getMenuItem("Search Book", "searchBook", this);
    registerUser = resources.getMenuItem("Register User", "añadir_usuario", this);

    tasks.addSeparator();
    tasks.add(searchBook);
    tasks.addSeparator();
    tasks.add(registerUser);

    changeBackground = resources.getMenu("Background", "background");

    IntStream.range(0, wallpapers.length).forEach(i -> {
      wallpapers[i] = new JMenuItem("Image " + (i + 1));
      wallpapers[i].setForeground(TEXT_COLOR);
      wallpapers[i].setIcon(new ImageIcon(resources.getImage("backs.png")));
      wallpapers[i].addActionListener(this);
      changeBackground.add(wallpapers[i]);
      changeBackground.addSeparator();
    });

    JMenu mode = resources.getMenu("Theme", "mode");
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

    JMenu changeUI = resources.getMenu("Change UI", "UI");
    changeUI.add(mode);
    changeUI.addSeparator();
    changeUI.add(changeBackground);

    OCR = resources.getMenuItem("OCR", "ocr", this);

    tools.add(changeUI);
    tools.addSeparator();
    tools.add(export);
    tools.addSeparator();
    tools.add(notes);
    tools.addSeparator();
    tools.add(numbers);
    tools.addSeparator();
    tools.add(OCR);
    tools.addSeparator();
    tools.addSeparator();
    tools.add(store);
    tools.addSeparator();
    tools.add(sendWhatsApp);
    tools.addSeparator();
    tools.add(structures);
    tools.addSeparator();
    tools.add(tasks);
    tools.addSeparator();
    tools.add(texts);

    menuBar.add(games);
    menuBar.add(scores);
    menuBar.add(tools);
    menuBar.add(about);
    menuBar.add(exit);

    add(menuBar, BorderLayout.NORTH);
  }

  private void changeBackgroundAP(String name, int width, int length) {

    try {
      Settings.fadeIn(this);
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
      Database.readTable(guessNumberTable.viewTable,
          Queries.getAllData(Format.tableName(GUESS_NUMBER)), true);
    } catch (Exception e1) {
      Alerts.error(e1, GUESS_NUMBER);
    }

    guessNumberTable.setSize(830, 400);
    guessNumberTable.setLocationRelativeTo(null);
    guessNumberTable.setMinimumSize(new Dimension(830, 400));
    guessNumberTable.setMaximumSize(new Dimension(1280, 720));
    guessNumberTable.setTitle(GUESS_NUMBER + " Information");
    Settings.fadeIn(guessNumberTable);
    guessNumberTable.setVisible(true);
  }

  protected void hangmanTableAP() {

    hangmanTable.cleanTable();

    try {
      Database
          .readTable(hangmanTable.viewTable, Queries.getAllData(Format.tableName(HANGMAN)), true);
    } catch (Exception e1) {
      Alerts.error(e1, HANGMAN);
    }

    hangmanTable.setSize(830, 400);
    hangmanTable.setLocationRelativeTo(null);
    hangmanTable.setMinimumSize(new Dimension(830, 400));
    hangmanTable.setMaximumSize(new Dimension(1280, 720));
    hangmanTable.setTitle(HANGMAN + " Information");
    Settings.fadeIn(hangmanTable);
    hangmanTable.setVisible(true);
  }

  protected boolean dicesTableAP() {
    boolean aux = false;

    dicesTable.cleanTable();

    try {
      aux = Database.readTable(dicesTable.viewTable, Queries.getAllData(DICES), true);
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
      Settings.fadeIn(dicesTable);
      dicesTable.setVisible(true);
    }

    return aux;
  }

  protected void notesTableAP() {
    notesTable.cleanTable();

    try {
      Database.readTable(notesTable.viewTable, Queries.getAllData(Format.tableName(NOTES)), true);
    } catch (Exception e1) {
      Alerts.error(e1, NOTES);
    }

    notesTable.setSize(830, 400);
    notesTable.setLocationRelativeTo(null);
    notesTable.setMinimumSize(new Dimension(830, 400));
    notesTable.setMaximumSize(new Dimension(1280, 720));
    notesTable.setTitle(NOTES + " Information");
    Settings.fadeIn(notesTable);
    notesTable.setVisible(true);
  }

  protected void puzzleTableAP() {
    puzzleTable.cleanTable();

    try {
      Database.readTable(puzzleTable.viewTable, Queries.getAllData(Format.tableName(PUZZLE)), true);
    } catch (Exception e1) {
      Alerts.error(e1, PUZZLE);
    }

    puzzleTable.setSize(830, 400);
    puzzleTable.setLocationRelativeTo(null);
    puzzleTable.setMinimumSize(new Dimension(830, 400));
    puzzleTable.setMaximumSize(new Dimension(1280, 720));
    puzzleTable.setTitle(PUZZLE + " Information");
    setVisible(false);
    Settings.fadeIn(puzzleTable);
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
        changeBackgroundAP(ArrayData.getPathBackground(i), ArrayData.getWidthBackground(i),
            ArrayData.getLongBackground(i));
        wallpapers[i].setForeground(MAIN_COLOR);
        background = i + 1;
        setVisible(true);
      } else {
        Alerts.elementApplied(false);
      }
    });
  }

  private void setUI(@NotNull String selectedUI) {

    paintUI();

    switch (selectedUI) {

      case "Gray":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

          UIManager.put("ComboBox.foreground", new Color(0, 0, 0));
          UIManager.put("MenuItem.foreground", TEXT_COLOR);
          UIManager.put("Menu.foreground", MAIN_COLOR);
          UIManager.put("Button.foreground", new Color(0, 0, 0));

          UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
          UIManager.put("TableHeader.foreground", MAIN_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
              ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.grayMode.setForeground(MAIN_COLOR);
          Settings.fadeIn(window);
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

          UIManager.put("MenuItem.foreground", Color.WHITE);
          UIManager.put("Menu.foreground", MAIN_COLOR);

          UIManager.put("ComboBox.foreground", TEXT_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
          UIManager.put("Button.foreground", Color.BLACK);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
              ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.textureMode.setForeground(MAIN_COLOR);
          Settings.fadeIn(window);
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

          UIManager.put("Menu.foreground", MAIN_COLOR);
          UIManager.put("ComboBox.foreground", Color.WHITE);
          UIManager.put("Table.foreground", Color.WHITE);
          UIManager.put("OptionPane.messageForeground", Color.WHITE);
          UIManager.put("Button.foreground", Color.WHITE);
          UIManager.put("MenuItem.foreground", Color.WHITE);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
              ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.darkMode.setForeground(MAIN_COLOR);
          Settings.fadeIn(window);
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
          window.setSize(ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
              ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.macMode.setForeground(MAIN_COLOR);
          Settings.fadeIn(window);
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

          UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
          UIManager.put("TableHeader.foreground", MAIN_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
              ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.mintMode.setForeground(MAIN_COLOR);
          Settings.fadeIn(window);
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

          UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
          UIManager.put("TableHeader.foreground", MAIN_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));
          window.add(welcome, BorderLayout.CENTER);
          window.changeBackgroundAP(ArrayData.getPathBackground(background - 1),
              ArrayData.getWidthBackground(background - 1),
              ArrayData.getLongBackground(background - 1));

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(getTitle());
          window.wallpapers[background - 1].setForeground(MAIN_COLOR);
          window.classicMode.setForeground(MAIN_COLOR);
          Settings.fadeIn(window);
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
      Settings.fadeOut(this);
    } else if (e.getSource() == searchBook) {
      new ConsultBook(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == sendWhatsApp) {
      new WhatsApp(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == registerUser) {
      StartTests.startRegisterUser();
    } else if (e.getSource() == OCR) {
      new OCR(this, true).start(this);
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
    } else if (e.getSource() == ticTacToePvsP) {
      new TicTacToe(this, true, false).start(this);
      setVisible(true);
    } else if (e.getSource() == ticTacToePvsCPU) {
      new TicTacToe(this, true, true).start(this);
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
    } else if (e.getSource() == questionsAndAnswers) {
      new QuestionsAndAnswers(this, true).start(this);
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
    } else if (e.getSource() == store) {
      new LoginStore(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == read) {
      new Card(this, true).start(this);
      setVisible(true);
    } else if (Settings.verifyConnection("Connect to see more!", isWork)) {

      if (e.getSource() == roulette) {

        Alerts.instruccionesRuleta();

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

      } else if (e.getSource() == tables) {

        if (dicesTableAP()) {
          setVisible(true);
        }

      } else if (e.getSource() == guessNumberTXT) {

        try {
          guessNumberTable.cleanTable();
          Export.txt(guessNumberTable.viewTable, Queries.getAllData(Format.tableName(GUESS_NUMBER)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanTXT) {

        try {
          hangmanTable.cleanTable();
          Export.txt(hangmanTable.viewTable, Queries.getAllData(Format.tableName(HANGMAN)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, HANGMAN);
        }

      } else if (e.getSource() == purchasesTXT) {

        try {
          purchasesTable.cleanTable();
          Export.txt(purchasesTable.viewTable,
              Queries.getAllData(Format.tableName(PURCHASES)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, PURCHASES);
        }

      } else if (e.getSource() == salesTXT) {

        try {
          salesTable.cleanTable();
          Export.txt(salesTable.viewTable,
              Queries.getAllData(Format.tableName(SALES)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, SALES);
        }

      } else if (e.getSource() == dicesTXT) {

        try {
          dicesTable.cleanTable();
          Export.txt(dicesTable.viewTable, Queries.getAllData(Format.tableName(DICES)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesTXT) {

        try {
          notesTable.cleanTable();
          Export.txt(notesTable.viewTable, Queries.getAllData(Format.tableName(NOTES)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzleTXT) {

        try {
          puzzleTable.cleanTable();
          Export.txt(puzzleTable.viewTable, Queries.getAllData(Format.tableName(PUZZLE)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryTXT) {

        try {
          inventoryTable.cleanTable();
          Export.txt(inventoryTable.viewTable, Queries.getAllData(Format.tableName(INVENTORY)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, INVENTORY);
        }
      } else if (e.getSource() == cashRegisterTXT) {

        try {
          cashRegisterTable.cleanTable();
          Export.txt(cashRegisterTable.viewTable,
              Queries.getAllData(Format.tableName(CASH_REGISTER)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, CASH_REGISTER);
        }
      } else if (e.getSource() == loansTXT) {

        try {
          loansTable.cleanTable();
          Export.txt(loansTable.viewTable,
              Queries.getAllData(Format.tableName(LOANS)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, LOANS);
        }

      } else if (e.getSource() == guessNumberPDF) {

        try {
          guessNumberTable.cleanTable();
          Export.pdf(guessNumberTable.viewTable, GUESS_NUMBER,
              Queries.getAllData(Format.tableName(GUESS_NUMBER)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanPDF) {

        try {
          hangmanTable.cleanTable();
          Export.pdf(hangmanTable.viewTable, HANGMAN, Queries.getAllData(Format.tableName(HANGMAN)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, HANGMAN);
        }

      } else if (e.getSource() == purchasesPDF) {

        try {
          purchasesTable.cleanTable();
          Export.pdf(purchasesTable.viewTable, PURCHASES,
              Queries.getAllData(Format.tableName(PURCHASES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, PURCHASES);
        }

      } else if (e.getSource() == salesPDF) {

        try {
          salesTable.cleanTable();
          Export.pdf(salesTable.viewTable, SALES,
              Queries.getAllData(Format.tableName(SALES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, SALES);
        }

      } else if (e.getSource() == dicesPDF) {

        try {
          dicesTable.cleanTable();
          Export.pdf(dicesTable.viewTable, DICES, Queries.getAllData(
              Format.tableName(DICES)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesPDF) {

        try {
          notesTable.cleanTable();
          Export.pdf(notesTable.viewTable, NOTES, Queries
                  .getAllData(Format.tableName(NOTES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzlePDF) {

        try {
          puzzleTable.cleanTable();
          Export.pdf(puzzleTable.viewTable, PUZZLE,
              Queries.getAllData(Format.tableName(PUZZLE)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryPDF) {

        try {
          inventoryTable.cleanTable();
          Export.pdf(inventoryTable.viewTable, INVENTORY,
              Queries.getAllData(Format.tableName(INVENTORY)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, INVENTORY);
        }
      } else if (e.getSource() == cashRegisterPDF) {

        try {
          cashRegisterTable.cleanTable();
          Export.pdf(cashRegisterTable.viewTable, CASH_REGISTER,
              Queries.getAllData(Format.tableName(CASH_REGISTER)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, CASH_REGISTER);
        }
      } else if (e.getSource() == loansPDF) {

        try {
          loansTable.cleanTable();
          Export.pdf(loansTable.viewTable, LOANS,
              Queries.getAllData(Format.tableName(LOANS)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, LOANS);
        }
      } else if (e.getSource() == guessNumberEXCEL) {

        try {
          guessNumberTable.cleanTable();
          Export
              .excel(guessNumberTable.viewTable, Queries.getAllData(Format.tableName(GUESS_NUMBER)),
                  ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanEXCEL) {

        try {
          hangmanTable.cleanTable();
          Export
              .excel(hangmanTable.viewTable, Queries.getAllData(Format.tableName(HANGMAN)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, HANGMAN);
        }

      } else if (e.getSource() == purchasesEXCEL) {

        try {
          purchasesTable.cleanTable();
          Export.excel(purchasesTable.viewTable,
              Queries.getAllData(Format.tableName(PURCHASES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, PURCHASES);
        }

      } else if (e.getSource() == salesEXCEL) {

        try {
          salesTable.cleanTable();
          Export.excel(salesTable.viewTable,
              Queries.getAllData(Format.tableName(SALES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, SALES);
        }

      } else if (e.getSource() == dicesEXCEL) {

        try {
          dicesTable.cleanTable();
          Export.excel(dicesTable.viewTable, Queries.getAllData(Format.tableName(DICES)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesEXCEL) {

        try {
          notesTable.cleanTable();
          Export.excel(notesTable.viewTable, Queries.getAllData(Format.tableName(NOTES)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzleEXCEL) {

        try {
          puzzleTable.cleanTable();
          Export.excel(puzzleTable.viewTable, Queries.getAllData(Format.tableName(PUZZLE)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryEXCEL) {

        try {
          inventoryTable.cleanTable();
          Export.excel(inventoryTable.viewTable, Queries.getAllData(Format.tableName(INVENTORY)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, INVENTORY);
        }
      } else if (e.getSource() == cashRegisterEXCEL) {

        try {
          cashRegisterTable.cleanTable();
          Export.excel(cashRegisterTable.viewTable,
              Queries.getAllData(Format.tableName(CASH_REGISTER)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, CASH_REGISTER);
        }
      } else if (e.getSource() == loansEXCEL) {

        try {
          loansTable.cleanTable();
          Export.excel(loansTable.viewTable,
              Queries.getAllData(Format.tableName(LOANS)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, LOANS);
        }
      }
    }
  }
}