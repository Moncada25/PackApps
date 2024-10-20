package com.bookverse.development.packapps.apps.views.older;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.bookverse.development.packapps.apps.utils.constants.AppConfig;
import com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.apps.utils.constants.Styles;
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

      UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");

      UIManager.put("PasswordField.border", Styles.BORDER_BLUE);
      UIManager.put("PasswordField.font", Styles.MEDIUM);

      UIManager.put("TextField.border", Styles.BORDER_BLUE);
      UIManager.put("TextField.font", Styles.MEDIUM);

      UIManager.put("FileChooser.saveButtonText", "Save");
      UIManager.put("FileChooser.cancelButtonText", "Cancel");

      UIManager.put("RadioButton.font", Styles.MEDIUM);

      UIManager.put("TextArea.font", Styles.MEDIUM);

      UIManager.put("ComboBox.font", Styles.MEDIUM);
      UIManager.put("ComboBox.foreground", Styles.TEXT_COLOR);

      UIManager.put("ScrollPane.border", Styles.BORDER_BLUE);

      UIManager.put("MenuItem.foreground", Color.WHITE);
      UIManager.put("MenuItem.font", Styles.MEDIUM);

      UIManager.put("Menu.foreground", Styles.MAIN_COLOR);
      UIManager.put("Menu.font", Styles.MEDIUM);

      UIManager.put("Button.font", Styles.MEDIUM);
      UIManager.put("Button.foreground", Color.BLACK);

      UIManager.put("Table.focusCellHighlightBorder", Styles.BORDER_BLUE);
      UIManager.put("TableHeader.foreground", Styles.MAIN_COLOR);
      UIManager.put("TableHeader.font", Styles.MEDIUM);
      UIManager.put("Table.font", Styles.MEDIUM);
      UIManager.put("Table.foreground", Styles.TEXT_COLOR);

      UIManager.put("OptionPane.okButtonText", "Done");
      UIManager.put("OptionPane.cancelButtonText", "No, thanks.");
      UIManager.put("OptionPane.yesButtonText", "Yes, it's okay.");
      UIManager.put("OptionPane.noButtonText", "No, thanks.");
      UIManager.put("OptionPane.messageFont", Styles.MEDIUM);
      UIManager.put("OptionPane.buttonFont", Styles.MEDIUM);
      UIManager.put("OptionPane.messageForeground", Styles.TEXT_COLOR);

    } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
             | IllegalAccessException e) {
      Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, e);
    }

    Index window = new Index();

    Themes.welcome = new JLabel();
    window.setSize(ArrayData.getWidthBackground(Themes.background - 1), ArrayData.getLongBackground(Themes.background - 1));
    window.add(Themes.welcome, BorderLayout.CENTER);
    Themes.changeBackgroundAP(ArrayData.getPathBackground(Themes.background - 1), ArrayData.getWidthBackground(Themes.background - 1), ArrayData.getLongBackground(Themes.background - 1), window);

    window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(Config.get(AppConfig.TITLE.getProperty()));
    Themes.wallpapers[Themes.background - 1].setForeground(Styles.MAIN_COLOR);
    Themes.textureMode.setForeground(Styles.MAIN_COLOR);
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

    Resources.addMenu(more, moreSystems, moreBookverse);
    Resources.addMenu(about, read, more);

    JMenu exit = Resources.getMenu("Exit", "exit");
    yesExit = Resources.getMenuItem("Are you sure?", "salir", this);

    JMenu send = Resources.getMenu("Send Feedback", "send");
    email = Resources.getMenuItem("Email", "email", this);
    comment = Resources.getMenuItem("Comment", "feedback", this);

    Resources.addMenu(send, email, comment);
    Resources.addMenu(exit, yesExit, send);

    JMenu games = Resources.getMenu("Games", "games");
    hangman = Resources.getMenuItem(DatabaseConstants.HANGMAN, "ahorcado", this);
    dices = Resources.getMenuItem(DatabaseConstants.DICES, "dado", this);
    roulette = Resources.getMenuItem("Roulette", "ruleta", this);

    JMenu guessNumberMenu = Resources.getMenu(DatabaseConstants.GUESS_NUMBER, "adivinar");
    guessNumber = Resources.getMenuItem("Easy", "easy", this);
    guessNumberHard = Resources.getMenuItem("Hard", "hard", this);

    Resources.addMenu(guessNumberMenu, guessNumber, guessNumberHard);

    JMenu puzzle = Resources.getMenu(DatabaseConstants.PUZZLE, "rompecabezas");
    puzzle4x4 = Resources.getMenuItem("Easy", "easy", this);
    puzzle5x5 = Resources.getMenuItem("Medium", "medio", this);
    puzzle6x6 = Resources.getMenuItem("Hard", "hard", this);

    Resources.addMenu(puzzle, puzzle4x4, puzzle5x5, puzzle6x6);

    JMenu ticTacToe = Resources.getMenu("Tic Tac Toe", "triqui");
    ticTacToePvsP = Resources.getMenuItem("Player vs Player", "jvsj", this);
    ticTacToePvsCPU = Resources.getMenuItem("Player vs CPU (beta)", "jvscpu", this);

    Resources.addMenu(ticTacToe, ticTacToePvsP, ticTacToePvsCPU);
    Resources.addMenu(games, hangman, dices, roulette, guessNumberMenu, puzzle, ticTacToe);

    JMenu scores = Resources.getMenu("Data", "data");
    tables = Resources.getMenuItem("Database", "tabla", this);

    scores.add(tables);

    JMenu tools = Resources.getMenu("Utils", "tools");
    store = Resources.getMenuItem("Store", "compraventa", this);
    sendWhatsApp = Resources.getMenuItem("Send Message", "whatsapp", this);
    structures = Resources.getMenuItem("Structures", "estructuras", this);
    numbers = Resources.getMenuItem("Numbers", "numeritos", this);
    notes = Resources.getMenuItem(DatabaseConstants.NOTES, "notas", this);
    texts = Resources.getMenuItem("Texts", "textos", this);

    JMenu export = Resources.getMenu("Export Data", "export");

    JMenu exportTXT = Resources.getMenu("Document TXT", "txt");
    guessNumberTXT = Resources.getMenuItem(DatabaseConstants.GUESS_NUMBER, "adivinar", this);
    hangmanTXT = Resources.getMenuItem(DatabaseConstants.HANGMAN, "ahorcado", this);
    purchasesTXT = Resources.getMenuItem(DatabaseConstants.PURCHASES, "comprar", this);
    dicesTXT = Resources.getMenuItem(DatabaseConstants.DICES, "dado", this);
    notesTXT = Resources.getMenuItem(DatabaseConstants.NOTES, "notas", this);
    puzzleTXT = Resources.getMenuItem(DatabaseConstants.PUZZLE, "rompecabezas", this);
    inventoryTXT = Resources.getMenuItem(DatabaseConstants.INVENTORY, "inventario", this);
    cashRegisterTXT = Resources.getMenuItem(DatabaseConstants.CASH_REGISTER, "registradora", this);
    loansTXT = Resources.getMenuItem(DatabaseConstants.LOANS, "prestamos", this);
    salesTXT = Resources.getMenuItem(DatabaseConstants.SALES, "vender", this);

    Resources.addMenu(
        exportTXT,
        guessNumberTXT,
        hangmanTXT,
        purchasesTXT,
        dicesTXT,
        notesTXT,
        puzzleTXT,
        inventoryTXT,
        cashRegisterTXT,
        loansTXT,
        salesTXT
    );

    JMenu exportEXCEL = Resources.getMenu("Document XLS", "excel");
    guessNumberEXCEL = Resources.getMenuItem(DatabaseConstants.GUESS_NUMBER, "adivinar", this);
    hangmanEXCEL = Resources.getMenuItem(DatabaseConstants.HANGMAN, "ahorcado", this);
    purchasesEXCEL = Resources.getMenuItem(DatabaseConstants.PURCHASES, "comprar", this);
    dicesEXCEL = Resources.getMenuItem(DatabaseConstants.DICES, "dado", this);
    notesEXCEL = Resources.getMenuItem(DatabaseConstants.NOTES, "notas", this);
    puzzleEXCEL = Resources.getMenuItem(DatabaseConstants.PUZZLE, "rompecabezas", this);
    inventoryEXCEL = Resources.getMenuItem(DatabaseConstants.INVENTORY, "inventario", this);
    cashRegisterEXCEL = Resources.getMenuItem(DatabaseConstants.CASH_REGISTER, "registradora", this);
    loansEXCEL = Resources.getMenuItem(DatabaseConstants.LOANS, "prestamos", this);
    salesEXCEL = Resources.getMenuItem(DatabaseConstants.SALES, "vender", this);

    Resources.addMenu(
        exportEXCEL,
        guessNumberEXCEL,
        hangmanEXCEL,
        purchasesEXCEL,
        dicesEXCEL,
        notesEXCEL,
        puzzleEXCEL,
        inventoryEXCEL,
        cashRegisterEXCEL,
        loansEXCEL,
        salesEXCEL
    );

    JMenu exportPDF = Resources.getMenu("Document PDF", "pdf");
    guessNumberPDF = Resources.getMenuItem(DatabaseConstants.GUESS_NUMBER, "adivinar", this);
    hangmanPDF = Resources.getMenuItem(DatabaseConstants.HANGMAN, "ahorcado", this);
    purchasesPDF = Resources.getMenuItem(DatabaseConstants.PURCHASES, "comprar", this);
    dicesPDF = Resources.getMenuItem(DatabaseConstants.DICES, "dado", this);
    notesPDF = Resources.getMenuItem(DatabaseConstants.NOTES, "notas", this);
    puzzlePDF = Resources.getMenuItem(DatabaseConstants.PUZZLE, "rompecabezas", this);
    inventoryPDF = Resources.getMenuItem(DatabaseConstants.INVENTORY, "inventario", this);
    cashRegisterPDF = Resources.getMenuItem(DatabaseConstants.CASH_REGISTER, "registradora", this);
    loansPDF = Resources.getMenuItem(DatabaseConstants.LOANS, "prestamos", this);
    salesPDF = Resources.getMenuItem(DatabaseConstants.SALES, "vender", this);

    Resources.addMenu(
        exportPDF,
        guessNumberPDF,
        hangmanPDF,
        purchasesPDF,
        dicesPDF,
        notesPDF,
        puzzlePDF,
        inventoryPDF,
        cashRegisterPDF,
        loansPDF,
        salesPDF
    );

    Resources.addMenu(export, exportTXT, exportEXCEL, exportPDF);

    JMenu tasks = Resources.getMenu("Tasks", "task");
    searchBook = Resources.getMenuItem("Search Book", "searchBook", this);
    registerUser = Resources.getMenuItem("Register User", "añadir_usuario", this);

    Resources.addMenu(tasks, searchBook, registerUser);

    changeBackground = Resources.getMenu("Background", "background");
    Themes.setWallpapers(this, changeBackground);

    JMenu mode = Resources.getMenu("Theme", "mode");
    Themes.setThemes(this, mode);

    JMenu changeUI = Resources.getMenu("Change UI", "UI");
    Resources.addMenu(changeUI, mode, changeBackground);

    ocr = Resources.getMenuItem("OCR", "ocr", this);
    qr = Resources.getMenuItem("QR", "qr", this);

    Resources.addMenu(
        tools,
        changeUI,
        export,
        notes,
        ocr,
        qr,
        numbers,
        store,
        sendWhatsApp,
        structures,
        tasks,
        texts
    );

    menuBar.add(games);
    menuBar.add(scores);
    menuBar.add(tools);
    menuBar.add(about);
    menuBar.add(exit);

    add(menuBar, BorderLayout.NORTH);
  }

  protected void guessNumberTableAP() {

    guessNumberTable.cleanTable();

    try {
      OlderRepository.readTable(guessNumberTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)), true);
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

  protected void hangmanTableAP() {

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

  protected boolean dicesTableAP() {
    boolean aux = false;

    dicesTable.cleanTable();

    try {
      aux = OlderRepository.readTable(dicesTable.viewTable, Queries.getAllData(DatabaseConstants.DICES), true);
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.DICES);
    }

    if (aux) {
      setVisible(false);
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

  protected void notesTableAP() {
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

  protected void puzzleTableAP() {
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
    setVisible(false);
    Effects.fadeIn(puzzleTable);
    puzzleTable.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

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

      if (Themes.defaultMode.getForeground() != Styles.MAIN_COLOR) {
        Themes.setUI(Themes.DEFAULT, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.grayMode) {

      if (Themes.grayMode.getForeground() != Styles.MAIN_COLOR) {
        Themes.setUI(Themes.GRAY, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.darkMode) {

      if (Themes.darkMode.getForeground() != Styles.MAIN_COLOR) {
        Themes.setUI(Themes.DARK, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.textureMode) {

      if (Themes.textureMode.getForeground() != Styles.MAIN_COLOR) {
        Themes.setUI(Themes.TEXTURE, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.macMode) {

      if (Themes.macMode.getForeground() != Styles.MAIN_COLOR) {
        Themes.setUI(Themes.MAC, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.mintMode) {

      if (Themes.mintMode.getForeground() != Styles.MAIN_COLOR) {
        Themes.setUI(Themes.MINT, this);
      } else {
        Alerts.elementApplied(true);
      }

    } else if (e.getSource() == Themes.classicMode) {

      if (Themes.classicMode.getForeground() != Styles.MAIN_COLOR) {
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
          Desktop.getDesktop().browse(URI.create("https://bookverse.vzpla.net"));
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
              Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanTXT) {

        try {
          hangmanTable.cleanTable();
          ExportFile.txt(hangmanTable.viewTable, Queries.getAllData(Format.tableName(
                  DatabaseConstants.HANGMAN)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.HANGMAN);
        }

      } else if (e.getSource() == purchasesTXT) {

        try {
          purchasesTable.cleanTable();
          ExportFile.txt(purchasesTable.viewTable,
              Queries.getAllData(Format.tableName(DatabaseConstants.PURCHASES)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.PURCHASES);
        }

      } else if (e.getSource() == salesTXT) {

        try {
          salesTable.cleanTable();
          ExportFile.txt(salesTable.viewTable,
              Queries.getAllData(Format.tableName(DatabaseConstants.SALES)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.SALES);
        }

      } else if (e.getSource() == dicesTXT) {

        try {
          dicesTable.cleanTable();
          ExportFile.txt(dicesTable.viewTable, Queries.getAllData(Format.tableName(
              DatabaseConstants.DICES)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.DICES);
        }

      } else if (e.getSource() == notesTXT) {

        try {
          notesTable.cleanTable();
          ExportFile.txt(notesTable.viewTable, Queries.getAllData(Format.tableName(
              DatabaseConstants.NOTES)), ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.NOTES);
        }

      } else if (e.getSource() == puzzleTXT) {

        try {
          puzzleTable.cleanTable();
          ExportFile.txt(puzzleTable.viewTable, Queries.getAllData(Format.tableName(
                  DatabaseConstants.PUZZLE)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.PUZZLE);
        }
      } else if (e.getSource() == inventoryTXT) {

        try {
          inventoryTable.cleanTable();
          ExportFile.txt(inventoryTable.viewTable, Queries.getAllData(Format.tableName(
                  DatabaseConstants.INVENTORY)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.INVENTORY);
        }
      } else if (e.getSource() == cashRegisterTXT) {

        try {
          cashRegisterTable.cleanTable();
          ExportFile.txt(cashRegisterTable.viewTable,
              Queries.getAllData(Format.tableName(DatabaseConstants.CASH_REGISTER)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.CASH_REGISTER);
        }
      } else if (e.getSource() == loansTXT) {

        try {
          loansTable.cleanTable();
          ExportFile.txt(loansTable.viewTable,
              Queries.getAllData(Format.tableName(DatabaseConstants.LOANS)),
              ".txt");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.LOANS);
        }

      } else if (e.getSource() == guessNumberPDF) {

        try {
          guessNumberTable.cleanTable();
          ExportFile.pdf(guessNumberTable.viewTable, DatabaseConstants.GUESS_NUMBER,
              Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanPDF) {

        try {
          hangmanTable.cleanTable();
          ExportFile.pdf(hangmanTable.viewTable, DatabaseConstants.HANGMAN, Queries.getAllData(Format.tableName(
                  DatabaseConstants.HANGMAN)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.HANGMAN);
        }

      } else if (e.getSource() == purchasesPDF) {

        try {
          purchasesTable.cleanTable();
          ExportFile.pdf(purchasesTable.viewTable, DatabaseConstants.PURCHASES,
              Queries.getAllData(Format.tableName(DatabaseConstants.PURCHASES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.PURCHASES);
        }

      } else if (e.getSource() == salesPDF) {

        try {
          salesTable.cleanTable();
          ExportFile.pdf(salesTable.viewTable, DatabaseConstants.SALES,
              Queries.getAllData(Format.tableName(DatabaseConstants.SALES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.SALES);
        }

      } else if (e.getSource() == dicesPDF) {

        try {
          dicesTable.cleanTable();
          ExportFile.pdf(dicesTable.viewTable, DatabaseConstants.DICES, Queries.getAllData(
              Format.tableName(DatabaseConstants.DICES)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.DICES);
        }

      } else if (e.getSource() == notesPDF) {

        try {
          notesTable.cleanTable();
          ExportFile.pdf(notesTable.viewTable, DatabaseConstants.NOTES, Queries
                  .getAllData(Format.tableName(DatabaseConstants.NOTES)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.NOTES);
        }

      } else if (e.getSource() == puzzlePDF) {

        try {
          puzzleTable.cleanTable();
          ExportFile.pdf(puzzleTable.viewTable, DatabaseConstants.PUZZLE,
              Queries.getAllData(Format.tableName(DatabaseConstants.PUZZLE)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.PUZZLE);
        }
      } else if (e.getSource() == inventoryPDF) {

        try {
          inventoryTable.cleanTable();
          ExportFile.pdf(inventoryTable.viewTable, DatabaseConstants.INVENTORY,
              Queries.getAllData(Format.tableName(DatabaseConstants.INVENTORY)), ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.INVENTORY);
        }
      } else if (e.getSource() == cashRegisterPDF) {

        try {
          cashRegisterTable.cleanTable();
          ExportFile.pdf(cashRegisterTable.viewTable, DatabaseConstants.CASH_REGISTER,
              Queries.getAllData(Format.tableName(DatabaseConstants.CASH_REGISTER)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.CASH_REGISTER);
        }
      } else if (e.getSource() == loansPDF) {

        try {
          loansTable.cleanTable();
          ExportFile.pdf(loansTable.viewTable, DatabaseConstants.LOANS,
              Queries.getAllData(Format.tableName(DatabaseConstants.LOANS)),
              ".pdf");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.LOANS);
        }
      } else if (e.getSource() == guessNumberEXCEL) {

        try {
          guessNumberTable.cleanTable();
          ExportFile
              .excel(guessNumberTable.viewTable, Queries.getAllData(Format.tableName(
                      DatabaseConstants.GUESS_NUMBER)),
                  ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.GUESS_NUMBER);
        }

      } else if (e.getSource() == hangmanEXCEL) {

        try {
          hangmanTable.cleanTable();
          ExportFile
              .excel(hangmanTable.viewTable, Queries.getAllData(Format.tableName(
                  DatabaseConstants.HANGMAN)), ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.HANGMAN);
        }

      } else if (e.getSource() == purchasesEXCEL) {

        try {
          purchasesTable.cleanTable();
          ExportFile.excel(purchasesTable.viewTable,
              Queries.getAllData(Format.tableName(DatabaseConstants.PURCHASES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.PURCHASES);
        }

      } else if (e.getSource() == salesEXCEL) {

        try {
          salesTable.cleanTable();
          ExportFile.excel(salesTable.viewTable,
              Queries.getAllData(Format.tableName(DatabaseConstants.SALES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.SALES);
        }

      } else if (e.getSource() == dicesEXCEL) {

        try {
          dicesTable.cleanTable();
          ExportFile.excel(dicesTable.viewTable, Queries.getAllData(Format.tableName(
                  DatabaseConstants.DICES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.DICES);
        }

      } else if (e.getSource() == notesEXCEL) {

        try {
          notesTable.cleanTable();
          ExportFile.excel(notesTable.viewTable, Queries.getAllData(Format.tableName(
                  DatabaseConstants.NOTES)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.NOTES);
        }

      } else if (e.getSource() == puzzleEXCEL) {

        try {
          puzzleTable.cleanTable();
          ExportFile.excel(puzzleTable.viewTable, Queries.getAllData(Format.tableName(
                  DatabaseConstants.PUZZLE)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.PUZZLE);
        }
      } else if (e.getSource() == inventoryEXCEL) {

        try {
          inventoryTable.cleanTable();
          ExportFile.excel(inventoryTable.viewTable,
              Queries.getAllData(Format.tableName(DatabaseConstants.INVENTORY)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.INVENTORY);
        }
      } else if (e.getSource() == cashRegisterEXCEL) {

        try {
          cashRegisterTable.cleanTable();
          ExportFile.excel(cashRegisterTable.viewTable,
              Queries.getAllData(Format.tableName(DatabaseConstants.CASH_REGISTER)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.CASH_REGISTER);
        }
      } else if (e.getSource() == loansEXCEL) {

        try {
          loansTable.cleanTable();
          ExportFile.excel(loansTable.viewTable,
              Queries.getAllData(Format.tableName(DatabaseConstants.LOANS)),
              ".xls");
        } catch (Exception ex) {
          Alerts.error(ex, DatabaseConstants.LOANS);
        }
      }
    }
  }
}
