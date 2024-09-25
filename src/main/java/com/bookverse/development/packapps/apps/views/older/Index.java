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

import com.bookverse.development.packapps.apps.utils.ui.Themes;
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
import com.bookverse.development.packapps.apps.utils.other.GeneralUtils;
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
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Index extends JFrame implements ActionListener {

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
  protected JMenu changeBackground;
  protected JMenuItem moreSystems;
  protected JMenuItem moreBookverse;
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

    Themes.welcome = new JLabel();
    window.setSize(ArrayData.getWidthBackground(Themes.background - 1),
        ArrayData.getLongBackground(Themes.background - 1));
    window.add(Themes.welcome, BorderLayout.CENTER);
    Themes.changeBackgroundAP(ArrayData.getPathBackground(Themes.background - 1),
        ArrayData.getWidthBackground(Themes.background - 1),
        ArrayData.getLongBackground(Themes.background - 1), window);

    window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(Config.get(TITLE.getProperty()));
    Themes.wallpapers[Themes.background - 1].setForeground(MAIN_COLOR);
    Themes.classicMode.setForeground(MAIN_COLOR);
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

    createMenu(more, moreSystems, moreBookverse);
    createMenu(about, read, more);

    JMenu exit = Resources.getMenu("Exit", "exit");
    yesExit = Resources.getMenuItem("Are you sure?", "salir", this);

    JMenu send = Resources.getMenu("Send Feedback", "send");
    email = Resources.getMenuItem("Email", "email", this);
    comment = Resources.getMenuItem("Comment", "feedback", this);

    createMenu(send, email, comment);
    createMenu(exit, yesExit, send);

    JMenu games = Resources.getMenu("Games", "games");
    hangman = Resources.getMenuItem(HANGMAN, "ahorcado", this);
    dices = Resources.getMenuItem(DICES, "dado", this);
    roulette = Resources.getMenuItem("Roulette", "ruleta", this);

    JMenu guessNumberMenu = Resources.getMenu(GUESS_NUMBER, "adivinar");
    guessNumber = Resources.getMenuItem("Easy", "easy", this);
    guessNumberHard = Resources.getMenuItem("Hard", "hard", this);

    createMenu(guessNumberMenu, guessNumber, guessNumberHard);

    JMenu puzzle = Resources.getMenu(PUZZLE, "rompecabezas");
    puzzle4x4 = Resources.getMenuItem("Easy", "easy", this);
    puzzle5x5 = Resources.getMenuItem("Medium", "medio", this);
    puzzle6x6 = Resources.getMenuItem("Hard", "hard", this);

    createMenu(puzzle, puzzle4x4, puzzle5x5, puzzle6x6);

    JMenu ticTacToe = Resources.getMenu("Tic Tac Toe", "triqui");
    ticTacToePvsP = Resources.getMenuItem("Player vs Player", "jvsj", this);
    ticTacToePvsCPU = Resources.getMenuItem("Player vs CPU (beta)", "jvscpu", this);

    createMenu(ticTacToe, ticTacToePvsP, ticTacToePvsCPU);
    createMenu(games, hangman, dices, roulette, guessNumberMenu, puzzle, ticTacToe);

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

    createMenu(exportTXT, guessNumberTXT, hangmanTXT, purchasesTXT, dicesTXT, notesTXT, puzzleTXT,
        inventoryTXT, cashRegisterTXT, loansTXT, salesTXT);

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

    createMenu(exportEXCEL, guessNumberEXCEL, hangmanEXCEL, purchasesEXCEL, dicesEXCEL, notesEXCEL,
        puzzleEXCEL, inventoryEXCEL, cashRegisterEXCEL, loansEXCEL, salesEXCEL);

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

    createMenu(exportPDF, guessNumberPDF, hangmanPDF, purchasesPDF, dicesPDF, notesPDF, puzzlePDF,
        inventoryPDF, cashRegisterPDF, loansPDF, salesPDF);
    createMenu(export, exportTXT, exportEXCEL, exportPDF);

    JMenu tasks = Resources.getMenu("Tasks", "task");
    searchBook = Resources.getMenuItem("Search Book", "searchBook", this);
    registerUser = Resources.getMenuItem("Register User", "añadir_usuario", this);

    createMenu(tasks, searchBook, registerUser);

    changeBackground = Resources.getMenu("Background", "background");

    IntStream.range(0, Themes.wallpapers.length).forEach(i -> {
      Themes.wallpapers[i] = new JMenuItem("Image " + (i + 1));
      Themes.wallpapers[i].setForeground(TEXT_COLOR);
      Themes.wallpapers[i].setIcon(new ImageIcon(Resources.getImage("backs.png")));
      Themes.wallpapers[i].addActionListener(this);
      changeBackground.add(Themes.wallpapers[i]);
      changeBackground.addSeparator();
    });

    JMenu mode = Resources.getMenu("Theme", "mode");
    Themes.defaultMode = Resources.getMenuItem("Default", "default_theme", this);
    Themes.darkMode = Resources.getMenuItem("Dark", "dark", this);
    Themes.textureMode = Resources.getMenuItem("Texture", "texture", this);
    Themes.macMode = Resources.getMenuItem("Mac OS", "mac", this);
    Themes.grayMode = Resources.getMenuItem("Metallic", "gray", this);
    Themes.mintMode = Resources.getMenuItem("Mint", "mint", this);
    Themes.classicMode = Resources.getMenuItem("Classic", "classic", this);

    createMenu(
        mode,
        Themes.defaultMode,
        Themes.darkMode,
        Themes.textureMode,
        Themes.macMode,
        Themes.grayMode,
        Themes.mintMode,
        Themes.classicMode
    );

    JMenu changeUI = Resources.getMenu("Change UI", "UI");

    createMenu(changeUI, mode, changeBackground);

    ocr = Resources.getMenuItem("OCR", "ocr", this);
    qr = Resources.getMenuItem("QR", "qr", this);

    createMenu(tools, changeUI, export, notes, ocr, qr, store, sendWhatsApp, structures, tasks,
        texts);

    menuBar.add(games);
    menuBar.add(scores);
    menuBar.add(tools);
    menuBar.add(about);
    menuBar.add(exit);

    add(menuBar, BorderLayout.NORTH);
  }

  private void createMenu(JMenu menu, JMenuItem... items) {
    for (JMenuItem item : items) {
      menu.add(item);
      menu.addSeparator();
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
      OlderRepository.readTable(notesTable.viewTable, Queries.getAllData(Format.tableName(NOTES)),
          true);
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
      OlderRepository.readTable(puzzleTable.viewTable, Queries.getAllData(Format.tableName(PUZZLE)),
          true);
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

  @Override
  public void actionPerformed(ActionEvent e) {

    Themes.isWork = true;
    Themes.paintBackground(e, this);

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
    } else if (e.getSource() == Themes.defaultMode) {

      if (Themes.defaultMode.getForeground() != MAIN_COLOR) {
        Themes.setUI(Themes.DEFAULT, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.grayMode) {

      if (Themes.grayMode.getForeground() != MAIN_COLOR) {
        Themes.setUI(Themes.GRAY, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.darkMode) {

      if (Themes.darkMode.getForeground() != MAIN_COLOR) {
        Themes.setUI(Themes.DARK, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.textureMode) {

      if (Themes.textureMode.getForeground() != MAIN_COLOR) {
        Themes.setUI(Themes.TEXTURE, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.macMode) {

      if (Themes.macMode.getForeground() != MAIN_COLOR) {
        Themes.setUI(Themes.MAC, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.mintMode) {

      if (Themes.mintMode.getForeground() != MAIN_COLOR) {
        Themes.setUI(Themes.MINT, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.classicMode) {

      if (Themes.classicMode.getForeground() != MAIN_COLOR) {
        Themes.setUI(Themes.CLASSIC, this);
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
    } else if (GeneralUtils.verifyConnection("Connect to see more!", Themes.isWork)) {

      if (e.getSource() == roulette) {

        Alerts.instruccionesRuleta();

        try {
          Desktop.getDesktop()
              .browse(URI.create("https://mypackapps.000webhostapp.com/ruleta.php"));
        } catch (Exception ex) {
          Alerts.error(ex, "Opening URL");
        }

      } else if (e.getSource() == moreSystems) {

        try {
          Desktop.getDesktop().browse(URI.create("https://mypackapps.000webhostapp.com"));
        } catch (Exception ex) {
          Alerts.error(ex, "Opening URL");
        }

      } else if (e.getSource() == moreBookverse) {

        try {
          Desktop.getDesktop().browse(URI.create("http://bookverse.vzpla.net"));
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
          ExportFile.txt(guessNumberTable.viewTable,
              Queries.getAllData(Format.tableName(GUESS_NUMBER)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanTXT) {

        try {
          hangmanTable.cleanTable();
          ExportFile.txt(hangmanTable.viewTable, Queries.getAllData(Format.tableName(HANGMAN)),
              ".txt");
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
          ExportFile.txt(puzzleTable.viewTable, Queries.getAllData(Format.tableName(PUZZLE)),
              ".txt");
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
          ExportFile.pdf(hangmanTable.viewTable, HANGMAN, Queries.getAllData(Format.tableName(
                  HANGMAN)),
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
          ExportFile.excel(dicesTable.viewTable, Queries.getAllData(Format.tableName(DICES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DICES);
        }

      } else if (e.getSource() == notesEXCEL) {

        try {
          notesTable.cleanTable();
          ExportFile.excel(notesTable.viewTable, Queries.getAllData(Format.tableName(NOTES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, NOTES);
        }

      } else if (e.getSource() == puzzleEXCEL) {

        try {
          puzzleTable.cleanTable();
          ExportFile.excel(puzzleTable.viewTable, Queries.getAllData(Format.tableName(PUZZLE)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, PUZZLE);
        }
      } else if (e.getSource() == inventoryEXCEL) {

        try {
          inventoryTable.cleanTable();
          ExportFile.excel(inventoryTable.viewTable,
              Queries.getAllData(Format.tableName(INVENTORY)),
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
