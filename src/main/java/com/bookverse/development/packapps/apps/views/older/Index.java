package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.AppConfig.TITLE;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.BORDER_BLUE;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.DICES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.HANGMAN;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.INVENTORY;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.LOANS;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.NOTES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.PURCHASES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.PUZZLE;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.SALES;

import com.bookverse.development.packapps.apps.views.DicesGameView;
import com.bookverse.development.packapps.apps.views.EmailView;
import com.bookverse.development.packapps.apps.views.FeedbackView;
import com.bookverse.development.packapps.apps.views.NotesView;
import com.bookverse.development.packapps.apps.views.ProfessionalCardView;
import com.bookverse.development.packapps.apps.views.OcrView;
import com.bookverse.development.packapps.apps.views.QrView;
import com.bookverse.development.packapps.apps.views.StructuresView;
import com.bookverse.development.packapps.apps.views.TextsView;
import com.bookverse.development.packapps.apps.views.WhatsAppView;
import com.bookverse.development.packapps.automation.utils.StartTests;
import com.bookverse.development.packapps.apps.utils.other.GeneralUtilities;
import com.bookverse.development.packapps.apps.utils.other.Config;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.repositories.OlderRepository;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.constants.ArrayData;
import com.bookverse.development.packapps.apps.utils.other.ExportFile;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.constants.Queries;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
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
  protected JMenuItem defaultMode;
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
  protected JMenuItem yesExit;
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
  protected JMenuItem ocr;
  protected JMenuItem qr;
  protected JMenuItem searchBook;
  protected JMenuItem registerUser;
  protected JMenuItem sendWhatsApp;
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
    window.setTitle(Config.get(TITLE.getProperty()));
    window.wallpapers[background - 1].setForeground(MAIN_COLOR);
    window.classicMode.setForeground(MAIN_COLOR);
    Effects.fadeIn(window);
    window.setVisible(true);
  }

  private void createComponents() {

    setIconImage(new ImageIcon(Resources.getImage("more.png")).getImage());

    JMenuBar menuBar = new JMenuBar();

    JMenu about = Resources.getMenu("About", "about");
    read = Resources.getMenuItem("Developer", "developer", this);

    JMenu more = Resources.getMenu("See More", "more");
    moreSystems = Resources.getMenuItem("My Systems", "mysystems", this);
    moreBookverse = Resources.getMenuItem("Bookverse", "books", this);

    more.add(moreBookverse);
    more.addSeparator();
    more.add(moreSystems);

    about.add(read);
    about.addSeparator();
    about.add(more);

    JMenu exit = Resources.getMenu("Exit", "exit");
    yesExit = Resources.getMenuItem("Are you sure?", "salir", this);

    JMenu send = Resources.getMenu("Send Feedback", "send");
    email = Resources.getMenuItem("Email", "email", this);
    comment = Resources.getMenuItem("Comment", "feedback", this);

    send.add(comment);
    send.addSeparator();
    send.add(email);

    exit.add(yesExit);
    exit.addSeparator();
    exit.add(send);

    JMenu games = Resources.getMenu("Games", "games");
    hangman = Resources.getMenuItem(HANGMAN, "ahorcado", this);
    dices = Resources.getMenuItem(DICES, "dado", this);
    roulette = Resources.getMenuItem("Roulette", "ruleta", this);

    JMenu guessNumberMenu = Resources.getMenu(GUESS_NUMBER, "adivinar");
    guessNumber = Resources.getMenuItem("Easy", "easy", this);
    guessNumberHard = Resources.getMenuItem("Hard", "hard", this);

    guessNumberMenu.add(guessNumber);
    guessNumberMenu.addSeparator();
    guessNumberMenu.add(guessNumberHard);

    JMenu puzzle = Resources.getMenu(PUZZLE, "rompecabezas");
    puzzle4x4 = Resources.getMenuItem("Easy", "easy", this);
    puzzle5x5 = Resources.getMenuItem("Medium", "medio", this);
    puzzle6x6 = Resources.getMenuItem("Hard", "hard", this);

    puzzle.add(puzzle4x4);
    puzzle.addSeparator();
    puzzle.add(puzzle5x5);
    puzzle.addSeparator();
    puzzle.add(puzzle6x6);

    JMenu ticTacToe = Resources.getMenu("Tic Tac Toe", "triqui");
    ticTacToePvsP = Resources.getMenuItem("Player vs Player", "jvsj", this);
    ticTacToePvsCPU = Resources.getMenuItem("Player vs CPU (beta)", "jvscpu", this);

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
    games.addSeparator();
    games.add(roulette);
    games.addSeparator();
    games.add(ticTacToe);

    JMenu scores = Resources.getMenu("Data", "data");
    tables = Resources.getMenuItem("Database", "tabla", this);

    scores.add(tables);

    JMenu tools = Resources.getMenu("Utils", "tools");
    store = Resources.getMenuItem("Store", "compraventa", this);
    sendWhatsApp = Resources.getMenuItem("Send Message", "whatsapp", this);
    structures = Resources.getMenuItem("Structures", "estructuras", this);
    numbers = Resources.getMenuItem("Numbers", "numeritos", this);
    notes = Resources.getMenuItem(NOTES, "notas", this);
    texts = Resources.getMenuItem("Texts", "textos", this);

    JMenu export = Resources.getMenu("Export Data", "export");

    JMenu exportTXT = Resources.getMenu("Document TXT", "txt");
    guessNumberTXT = Resources.getMenuItem(GUESS_NUMBER, "adivinar", this);
    hangmanTXT = Resources.getMenuItem(HANGMAN, "ahorcado", this);
    purchasesTXT = Resources.getMenuItem(PURCHASES, "comprar", this);
    dicesTXT = Resources.getMenuItem(DICES, "dado", this);
    notesTXT = Resources.getMenuItem(NOTES, "notas", this);
    puzzleTXT = Resources.getMenuItem(PUZZLE, "rompecabezas", this);
    inventoryTXT = Resources.getMenuItem(INVENTORY, "inventario", this);
    cashRegisterTXT = Resources.getMenuItem(CASH_REGISTER, "registradora", this);
    loansTXT = Resources.getMenuItem(LOANS, "prestamos", this);
    salesTXT = Resources.getMenuItem(SALES, "vender", this);

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

    JMenu exportEXCEL = Resources.getMenu("Document XLS", "excel");
    guessNumberEXCEL = Resources.getMenuItem(GUESS_NUMBER, "adivinar", this);
    hangmanEXCEL = Resources.getMenuItem(HANGMAN, "ahorcado", this);
    purchasesEXCEL = Resources.getMenuItem(PURCHASES, "comprar", this);
    dicesEXCEL = Resources.getMenuItem(DICES, "dado", this);
    notesEXCEL = Resources.getMenuItem(NOTES, "notas", this);
    puzzleEXCEL = Resources.getMenuItem(PUZZLE, "rompecabezas", this);
    inventoryEXCEL = Resources.getMenuItem(INVENTORY, "inventario", this);
    cashRegisterEXCEL = Resources.getMenuItem(CASH_REGISTER, "registradora", this);
    loansEXCEL = Resources.getMenuItem(LOANS, "prestamos", this);
    salesEXCEL = Resources.getMenuItem(SALES, "vender", this);

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

    JMenu exportPDF = Resources.getMenu("Document PDF", "pdf");
    guessNumberPDF = Resources.getMenuItem(GUESS_NUMBER, "adivinar", this);
    hangmanPDF = Resources.getMenuItem(HANGMAN, "ahorcado", this);
    purchasesPDF = Resources.getMenuItem(PURCHASES, "comprar", this);
    dicesPDF = Resources.getMenuItem(DICES, "dado", this);
    notesPDF = Resources.getMenuItem(NOTES, "notas", this);
    puzzlePDF = Resources.getMenuItem(PUZZLE, "rompecabezas", this);
    inventoryPDF = Resources.getMenuItem(INVENTORY, "inventario", this);
    cashRegisterPDF = Resources.getMenuItem(CASH_REGISTER, "registradora", this);
    loansPDF = Resources.getMenuItem(LOANS, "prestamos", this);
    salesPDF = Resources.getMenuItem(SALES, "vender", this);

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

    JMenu tasks = Resources.getMenu("Tasks", "task");
    searchBook = Resources.getMenuItem("Search Book", "searchBook", this);
    registerUser = Resources.getMenuItem("Register User", "añadir_usuario", this);

    tasks.addSeparator();
    tasks.add(searchBook);
    tasks.addSeparator();
    tasks.add(registerUser);

    changeBackground = Resources.getMenu("Background", "background");

    IntStream.range(0, wallpapers.length).forEach(i -> {
      wallpapers[i] = new JMenuItem("Image " + (i + 1));
      wallpapers[i].setForeground(TEXT_COLOR);
      wallpapers[i].setIcon(new ImageIcon(Resources.getImage("backs.png")));
      wallpapers[i].addActionListener(this);
      changeBackground.add(wallpapers[i]);
      changeBackground.addSeparator();
    });

    JMenu mode = Resources.getMenu("Theme", "mode");
    defaultMode = Resources.getMenuItem("Default", "default_theme", this);
    darkMode = Resources.getMenuItem("Dark", "dark", this);
    textureMode = Resources.getMenuItem("Texture", "texture", this);
    macMode = Resources.getMenuItem("Mac OS", "mac", this);
    grayMode = Resources.getMenuItem("Metallic", "gray", this);
    mintMode = Resources.getMenuItem("Mint", "mint", this);
    classicMode = Resources.getMenuItem("Classic", "classic", this);

    mode.add(classicMode);
    mode.addSeparator();
    mode.add(darkMode);
    mode.addSeparator();
    mode.add(defaultMode);
    mode.addSeparator();
    mode.add(macMode);
    mode.addSeparator();
    mode.add(grayMode);
    mode.addSeparator();
    mode.add(mintMode);
    mode.addSeparator();
    mode.add(textureMode);

    JMenu changeUI = Resources.getMenu("Change UI", "UI");
    changeUI.add(mode);
    changeUI.addSeparator();
    changeUI.add(changeBackground);

    ocr = Resources.getMenuItem("OCR", "ocr", this);

    qr = Resources.getMenuItem("QR", "qr", this);

    tools.add(changeUI);
    tools.addSeparator();
    tools.add(export);
    tools.addSeparator();
    tools.add(notes);
    tools.addSeparator();
    tools.add(numbers);
    tools.addSeparator();
    tools.add(ocr);
    tools.addSeparator();
    tools.add(qr);
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
      Effects.fadeIn(this);
      setVisible(false);
      ((JPanel) getContentPane()).setOpaque(false);
      welcome.setIcon(new ImageIcon(Resources.getImage(name)));
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
      OlderRepository.readTable(guessNumberTable.viewTable,
          Queries.getAllData(Format.tableName(GUESS_NUMBER)), true);
    } catch (Exception e1) {
      Alerts.error(e1, GUESS_NUMBER);
    }

    guessNumberTable.setSize(830, 400);
    guessNumberTable.setLocationRelativeTo(null);
    guessNumberTable.setMinimumSize(new Dimension(830, 400));
    guessNumberTable.setMaximumSize(new Dimension(1280, 720));
    guessNumberTable.setTitle(GUESS_NUMBER + " Information");
    Effects.fadeIn(guessNumberTable);
    guessNumberTable.setVisible(true);
  }

  protected void hangmanTableAP() {

    hangmanTable.cleanTable();

    try {
      OlderRepository
          .readTable(hangmanTable.viewTable, Queries.getAllData(Format.tableName(HANGMAN)), true);
    } catch (Exception e1) {
      Alerts.error(e1, HANGMAN);
    }

    hangmanTable.setSize(830, 400);
    hangmanTable.setLocationRelativeTo(null);
    hangmanTable.setMinimumSize(new Dimension(830, 400));
    hangmanTable.setMaximumSize(new Dimension(1280, 720));
    hangmanTable.setTitle(HANGMAN + " Information");
    Effects.fadeIn(hangmanTable);
    hangmanTable.setVisible(true);
  }

  protected boolean dicesTableAP() {
    boolean aux = false;

    dicesTable.cleanTable();

    try {
      aux = OlderRepository.readTable(dicesTable.viewTable, Queries.getAllData(DICES), true);
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
      Effects.fadeIn(dicesTable);
      dicesTable.setVisible(true);
    }

    return aux;
  }

  protected void notesTableAP() {
    notesTable.cleanTable();

    try {
      OlderRepository.readTable(notesTable.viewTable, Queries.getAllData(Format.tableName(NOTES)), true);
    } catch (Exception e1) {
      Alerts.error(e1, NOTES);
    }

    notesTable.setSize(830, 400);
    notesTable.setLocationRelativeTo(null);
    notesTable.setMinimumSize(new Dimension(830, 400));
    notesTable.setMaximumSize(new Dimension(1280, 720));
    notesTable.setTitle(NOTES + " Information");
    Effects.fadeIn(notesTable);
    notesTable.setVisible(true);
  }

  protected void puzzleTableAP() {
    puzzleTable.cleanTable();

    try {
      OlderRepository.readTable(puzzleTable.viewTable, Queries.getAllData(Format.tableName(PUZZLE)), true);
    } catch (Exception e1) {
      Alerts.error(e1, PUZZLE);
    }

    puzzleTable.setSize(830, 400);
    puzzleTable.setLocationRelativeTo(null);
    puzzleTable.setMinimumSize(new Dimension(830, 400));
    puzzleTable.setMaximumSize(new Dimension(1280, 720));
    puzzleTable.setTitle(PUZZLE + " Information");
    setVisible(false);
    Effects.fadeIn(puzzleTable);
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

      case "Default":

        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

          UIManager.put("ComboBox.foreground", new Color(0, 0, 0));
          UIManager.put("MenuItem.foreground", TEXT_COLOR);
          UIManager.put("Menu.foreground", MAIN_COLOR);
          UIManager.put("Button.foreground", new Color(0, 0, 0));

          UIManager.put("Table.focusCellHighlightBorder", BORDER_BLUE);
          UIManager.put("TableHeader.foreground", MAIN_COLOR);
          UIManager.put("Table.foreground", TEXT_COLOR);
          UIManager.put("OptionPane.messageForeground", TEXT_COLOR);

          dispose();
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
          Effects.fadeIn(window);
          window.setVisible(true);
          Alerts.changeUI("Default");

        } catch (UnsupportedLookAndFeelException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }

        break;

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

          dispose();
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
          Effects.fadeIn(window);
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

          dispose();
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
          Effects.fadeIn(window);
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

          dispose();
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
          Effects.fadeIn(window);
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

          dispose();
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
          Effects.fadeIn(window);
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

          dispose();
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
          Effects.fadeIn(window);
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

          dispose();
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
          Effects.fadeIn(window);
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

    if (e.getSource() == yesExit) {
      Effects.fadeOut(this);
    } else if (e.getSource() == searchBook) {
      new ConsultBook(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == sendWhatsApp) {
      new WhatsAppView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == registerUser) {
      StartTests.startRegisterUser();
    } else if (e.getSource() == ocr) {
      new OcrView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == qr) {
      new QrView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == defaultMode) {

      if (defaultMode.getForeground() != MAIN_COLOR) {
        setUI("Default");
      } else {
        Alerts.elementApplied(true);
      }

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
      new EmailView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == comment) {
      new FeedbackView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == texts) {
      new TextsView(this, true).start(this);
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
    } else if (e.getSource() == structures) {
      new StructuresView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == dices) {
      new DicesGameView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == notes) {
      new NotesView(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == store) {
      new LoginStore(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == read) {
      new ProfessionalCardView(this, true).start(this);
      setVisible(true);
    } else if (GeneralUtilities.verifyConnection("Connect to see more!", isWork)) {

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
          ExportFile.txt(guessNumberTable.viewTable, Queries.getAllData(Format.tableName(GUESS_NUMBER)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanTXT) {

        try {
          hangmanTable.cleanTable();
          ExportFile.txt(hangmanTable.viewTable, Queries.getAllData(Format.tableName(HANGMAN)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, HANGMAN);
        }

      } else if (e.getSource() == purchasesTXT) {

        try {
          purchasesTable.cleanTable();
          ExportFile.txt(purchasesTable.viewTable,
              Queries.getAllData(Format.tableName(PURCHASES)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, PURCHASES);
        }

      } else if (e.getSource() == salesTXT) {

        try {
          salesTable.cleanTable();
          ExportFile.txt(salesTable.viewTable,
              Queries.getAllData(Format.tableName(SALES)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, SALES);
        }

      } else if (e.getSource() == dicesTXT) {

        try {
          dicesTable.cleanTable();
          ExportFile.txt(dicesTable.viewTable, Queries.getAllData(Format.tableName(DICES)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesTXT) {

        try {
          notesTable.cleanTable();
          ExportFile.txt(notesTable.viewTable, Queries.getAllData(Format.tableName(NOTES)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzleTXT) {

        try {
          puzzleTable.cleanTable();
          ExportFile.txt(puzzleTable.viewTable, Queries.getAllData(Format.tableName(PUZZLE)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryTXT) {

        try {
          inventoryTable.cleanTable();
          ExportFile.txt(inventoryTable.viewTable, Queries.getAllData(Format.tableName(INVENTORY)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, INVENTORY);
        }
      } else if (e.getSource() == cashRegisterTXT) {

        try {
          cashRegisterTable.cleanTable();
          ExportFile.txt(cashRegisterTable.viewTable,
              Queries.getAllData(Format.tableName(CASH_REGISTER)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, CASH_REGISTER);
        }
      } else if (e.getSource() == loansTXT) {

        try {
          loansTable.cleanTable();
          ExportFile.txt(loansTable.viewTable,
              Queries.getAllData(Format.tableName(LOANS)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, LOANS);
        }

      } else if (e.getSource() == guessNumberPDF) {

        try {
          guessNumberTable.cleanTable();
          ExportFile.pdf(guessNumberTable.viewTable, GUESS_NUMBER,
              Queries.getAllData(Format.tableName(GUESS_NUMBER)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanPDF) {

        try {
          hangmanTable.cleanTable();
          ExportFile.pdf(hangmanTable.viewTable, HANGMAN, Queries.getAllData(Format.tableName(HANGMAN)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, HANGMAN);
        }

      } else if (e.getSource() == purchasesPDF) {

        try {
          purchasesTable.cleanTable();
          ExportFile.pdf(purchasesTable.viewTable, PURCHASES,
              Queries.getAllData(Format.tableName(PURCHASES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, PURCHASES);
        }

      } else if (e.getSource() == salesPDF) {

        try {
          salesTable.cleanTable();
          ExportFile.pdf(salesTable.viewTable, SALES,
              Queries.getAllData(Format.tableName(SALES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, SALES);
        }

      } else if (e.getSource() == dicesPDF) {

        try {
          dicesTable.cleanTable();
          ExportFile.pdf(dicesTable.viewTable, DICES, Queries.getAllData(
              Format.tableName(DICES)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesPDF) {

        try {
          notesTable.cleanTable();
          ExportFile.pdf(notesTable.viewTable, NOTES, Queries
                  .getAllData(Format.tableName(NOTES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzlePDF) {

        try {
          puzzleTable.cleanTable();
          ExportFile.pdf(puzzleTable.viewTable, PUZZLE,
              Queries.getAllData(Format.tableName(PUZZLE)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryPDF) {

        try {
          inventoryTable.cleanTable();
          ExportFile.pdf(inventoryTable.viewTable, INVENTORY,
              Queries.getAllData(Format.tableName(INVENTORY)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, INVENTORY);
        }
      } else if (e.getSource() == cashRegisterPDF) {

        try {
          cashRegisterTable.cleanTable();
          ExportFile.pdf(cashRegisterTable.viewTable, CASH_REGISTER,
              Queries.getAllData(Format.tableName(CASH_REGISTER)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, CASH_REGISTER);
        }
      } else if (e.getSource() == loansPDF) {

        try {
          loansTable.cleanTable();
          ExportFile.pdf(loansTable.viewTable, LOANS,
              Queries.getAllData(Format.tableName(LOANS)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, LOANS);
        }
      } else if (e.getSource() == guessNumberEXCEL) {

        try {
          guessNumberTable.cleanTable();
          ExportFile
              .excel(guessNumberTable.viewTable, Queries.getAllData(Format.tableName(GUESS_NUMBER)),
                  ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanEXCEL) {

        try {
          hangmanTable.cleanTable();
          ExportFile
              .excel(hangmanTable.viewTable, Queries.getAllData(Format.tableName(HANGMAN)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, HANGMAN);
        }

      } else if (e.getSource() == purchasesEXCEL) {

        try {
          purchasesTable.cleanTable();
          ExportFile.excel(purchasesTable.viewTable,
              Queries.getAllData(Format.tableName(PURCHASES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, PURCHASES);
        }

      } else if (e.getSource() == salesEXCEL) {

        try {
          salesTable.cleanTable();
          ExportFile.excel(salesTable.viewTable,
              Queries.getAllData(Format.tableName(SALES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, SALES);
        }

      } else if (e.getSource() == dicesEXCEL) {

        try {
          dicesTable.cleanTable();
          ExportFile.excel(dicesTable.viewTable, Queries.getAllData(Format.tableName(DICES)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesEXCEL) {

        try {
          notesTable.cleanTable();
          ExportFile.excel(notesTable.viewTable, Queries.getAllData(Format.tableName(NOTES)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzleEXCEL) {

        try {
          puzzleTable.cleanTable();
          ExportFile.excel(puzzleTable.viewTable, Queries.getAllData(Format.tableName(PUZZLE)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryEXCEL) {

        try {
          inventoryTable.cleanTable();
          ExportFile.excel(inventoryTable.viewTable, Queries.getAllData(Format.tableName(INVENTORY)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, INVENTORY);
        }
      } else if (e.getSource() == cashRegisterEXCEL) {

        try {
          cashRegisterTable.cleanTable();
          ExportFile.excel(cashRegisterTable.viewTable,
              Queries.getAllData(Format.tableName(CASH_REGISTER)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, CASH_REGISTER);
        }
      } else if (e.getSource() == loansEXCEL) {

        try {
          loansTable.cleanTable();
          ExportFile.excel(loansTable.viewTable,
              Queries.getAllData(Format.tableName(LOANS)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, LOANS);
        }
      }
    }
  }
}