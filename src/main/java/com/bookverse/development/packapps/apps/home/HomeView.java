package com.bookverse.development.packapps.apps.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
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
import com.bookverse.development.packapps.utils.constants.AppConfig;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.views.DicesGameView;
import com.bookverse.development.packapps.views.EmailView;
import com.bookverse.development.packapps.views.FeedbackView;
import com.bookverse.development.packapps.apps.notes.NotesView;
import com.bookverse.development.packapps.views.ProfessionalCardView;
import com.bookverse.development.packapps.views.OcrView;
import com.bookverse.development.packapps.apps.qr.QrView;
import com.bookverse.development.packapps.views.StructuresView;
import com.bookverse.development.packapps.views.TextsView;
import com.bookverse.development.packapps.views.WhatsAppView;
import com.bookverse.development.packapps.automation.utils.StartTests;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.other.Config;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.constants.ArrayData;
import com.bookverse.development.packapps.utils.other.ExportFile;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.database.Queries;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.views.older.ConsultBook;
import com.bookverse.development.packapps.views.older.GuessNumber;
import com.bookverse.development.packapps.views.older.Hangman;
import com.bookverse.development.packapps.views.older.LoginStore;
import com.bookverse.development.packapps.views.older.Numbers;
import com.bookverse.development.packapps.views.older.Puzzle;
import com.bookverse.development.packapps.views.older.TicTacToe;
import com.bookverse.development.packapps.utils.ui.factory.Menu;
import com.bookverse.development.packapps.utils.ui.factory.MenuItem;
import com.bookverse.development.packapps.utils.constants.AppThemes;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.tables.DicesTable;
import com.bookverse.development.packapps.apps.tables.GuessNumberTable;
import com.bookverse.development.packapps.apps.tables.HangmanTable;
import com.bookverse.development.packapps.apps.tables.InventoryTable;
import com.bookverse.development.packapps.apps.tables.LoansTable;
import com.bookverse.development.packapps.apps.tables.NotesTable;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.views.older.CashRegisterTable;
import com.bookverse.development.packapps.views.older.PurchasesTable;
import com.bookverse.development.packapps.views.older.PuzzleTable;
import com.bookverse.development.packapps.views.older.SalesTable;

public class HomeView extends JFrame {

  private static HomeService service = new HomeService();
  private static HomeViewModel model = new HomeViewModel();
  private HangmanTable hangmanTable = new HangmanTable(this, true);
  private GuessNumberTable guessNumberTable = new GuessNumberTable(this, true);
  private PuzzleTable puzzleTable = new PuzzleTable(this, true);
  private DicesTable dicesTable = new DicesTable(this, true);
  private NotesTable notesTable = new NotesTable(this, true);
  private InventoryTable inventoryTable = new InventoryTable(this, true);
  private CashRegisterTable cashRegisterTable = new CashRegisterTable(this, true);
  private LoansTable loansTable = new LoansTable(this, true);
  private PurchasesTable purchasesTable = new PurchasesTable(this, true);
  private SalesTable salesTable = new SalesTable(this, true);

  public HomeView() {
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
      Logger.getLogger(HomeView.class.getName()).log(Level.SEVERE, null, e);
    }

    HomeView window = new HomeView();

    model.setWelcome(new JLabel());
    window.setSize(
        ArrayData.getWidthBackground(service.getBackground() - 1),
        ArrayData.getLongBackground(service.getBackground() - 1)
    );
    window.add(model.getWelcome(), BorderLayout.CENTER);
    service.changeBackgroundAP(
        model,
        ArrayData.getPathBackground(service.getBackground() - 1),
        ArrayData.getWidthBackground(service.getBackground() - 1),
        ArrayData.getLongBackground(service.getBackground() - 1), window
    );

    window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(Config.get(AppConfig.TITLE.getProperty()));
    model.getWallpapers()[service.getBackground() - 1].setForeground(Styles.MAIN_COLOR);
    model.getTextureMode().setForeground(Styles.MAIN_COLOR);
    Effects.fadeIn(window);
    window.setVisible(true);
  }

  public void openGuessNumberTable() {

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

  public void openHangmanTable() {

    hangmanTable.cleanTable();

    try {
      OlderRepository.readTable(
          hangmanTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.HANGMAN)),
          true
      );
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

  public boolean openDicesTable(Window parent) {
    boolean aux = false;

    dicesTable.cleanTable();

    try {
      aux = OlderRepository.readTable(
          dicesTable.viewTable, Queries.getAllData(DatabaseConstants.DICES), true
      );
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

  public void openNotesTable() {
    notesTable.cleanTable();

    try {
      OlderRepository.readTable(
          notesTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.NOTES)),
          true
      );
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

  public void openPuzzleTable(Dialog parent) {
    puzzleTable.cleanTable();

    try {
      OlderRepository.readTable(
          puzzleTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.PUZZLE)),
          true
      );
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

  private void createComponents() {

    setIconImage(new ImageIcon(Resources.getImage("more.png")).getImage());

    JMenuBar menuBar = new JMenuBar();
    JMenu about = new Menu().setText("About").setImage("about").build();
    JMenu more = new Menu().setText("See More").setImage("more").build();
    JMenu exit = new Menu().setText("Exit").setImage("exit").build();
    JMenu send = new Menu().setText("Send Feedback").setImage("send").build();
    JMenu games = new Menu().setText("Games").setImage("games").build();
    JMenu guessNumberMenu = new Menu().setText("Guess Number").setImage("adivinar").build();
    JMenu puzzle = new Menu().setText("Puzzle").setImage("rompecabezas").build();
    JMenu ticTacToe = new Menu().setText("Tic Tac Toe").setImage("triqui").build();
    JMenu scores = new Menu().setText("Data").setImage("data").build();
    JMenu tools = new Menu().setText("Tools").setImage("tools").build();
    JMenu export = new Menu().setText("Export").setImage("export").build();
    JMenu exportTXT = new Menu().setText("Document TXT").setImage("txt").build();
    JMenu exportEXCEL = new Menu().setText("Document XLS").setImage("excel").build();
    JMenu exportPDF = new Menu().setText("Document PDF").setImage("pdf").build();
    JMenu tasks = new Menu().setText("Tasks").setImage("task").build();
    JMenu changeBackground = new Menu().setText("Wallpaper").setImage("background").build();
    JMenu themes = new Menu().setText("Theme").setImage("mode").build();
    JMenu changeUI = new Menu().setText("Change UI").setImage("UI").build();

    JMenuItem card = new MenuItem().setText("Developer").setImage("developer").build();
    card.addActionListener(e -> {
      new ProfessionalCardView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem moreSystems = new MenuItem().setText("My Systems").setImage("mysystems").build();
    moreSystems.addActionListener(e -> {
          if (GeneralUtils.verifyConnection("Connect to see more!", service.isWork())) {
            GeneralUtils.openUrl("https://mypackapps.000webhostapp.com");
          }
        }
    );

    JMenuItem moreBookverse = new MenuItem().setText("Bookverse").setImage("books").build();
    moreBookverse.addActionListener(e -> {
          if (GeneralUtils.verifyConnection("Connect to see more!", service.isWork())) {
            GeneralUtils.openUrl("https://bookverse.vzpla.net");
          }
        }
    );

    Resources.addMenu(more, moreSystems, moreBookverse);
    Resources.addMenu(about, card, more);

    JMenuItem yesExit = new MenuItem().setText("Are you sure?").setImage("salir").build();
    yesExit.addActionListener(e -> Effects.fadeOut(this));

    JMenuItem email = new MenuItem().setText("Email").setImage("email").build();
    email.addActionListener(e -> {
      new EmailView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem comment = new MenuItem().setText("Comment").setImage("feedback").build();
    comment.addActionListener(e -> {
      new FeedbackView(this, true).start(this);
      setVisible(true);
    });

    Resources.addMenu(send, email, comment);
    Resources.addMenu(exit, yesExit, send);

    JMenuItem hangman = new MenuItem().setText("Hangman").setImage("ahorcado").build();
    hangman.addActionListener(e -> {
      new Hangman(this, true).start(this);
      setVisible(true);
    });

    JMenuItem dices = new MenuItem().setText("Dices").setImage("dado").build();
    dices.addActionListener(e -> {
      new DicesGameView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem roulette = new MenuItem().setText("Roulette").setImage("ruleta").build();
    roulette.addActionListener(e -> {
          if (GeneralUtils.verifyConnection("Connect to see more!", service.isWork())) {
            GeneralUtils.openUrl("https://mypackapps.000webhostapp.com/ruleta.php");
          }
        }
    );

    JMenuItem guessNumber = new MenuItem().setText("Easy").setImage("easy").build();
    guessNumber.addActionListener(e -> {
      new GuessNumber(this, true, false).start(this);
      setVisible(true);
    });

    JMenuItem guessNumberHard = new MenuItem().setText("Hard").setImage("hard").build();
    guessNumberHard.addActionListener(e -> {
      new GuessNumber(this, true, true).start(this);
      setVisible(true);
    });

    Resources.addMenu(guessNumberMenu, guessNumber, guessNumberHard);

    JMenuItem puzzle4x4 = new MenuItem().setText("Easy").setImage("easy").build();
    puzzle4x4.addActionListener(e -> {
      new Puzzle(this, true, 4, 55, 3).start(this);
      setVisible(true);
    });

    JMenuItem puzzle5x5 = new MenuItem().setText("Medium").setImage("medio").build();
    puzzle5x5.addActionListener(e -> {
      new Puzzle(this, true, 5, 50, 6).start(this);
      setVisible(true);
    });

    JMenuItem puzzle6x6 = new MenuItem().setText("Hard").setImage("hard").build();
    puzzle6x6.addActionListener(e -> {
      new Puzzle(this, true, 6, 45, 10).start(this);
      setVisible(true);
    });

    Resources.addMenu(puzzle, puzzle4x4, puzzle5x5, puzzle6x6);

    JMenuItem ticTacToePvsP = new MenuItem().setText("Player vs Player").setImage("jvsj").build();
    ticTacToePvsP.addActionListener(e -> {
      new TicTacToe(this, true, false).start(this);
      setVisible(true);
    });

    JMenuItem ticTacToePvsCPU = new MenuItem().setText("Player vs CPU (beta)").setImage("jvscpu").build();
    ticTacToePvsCPU.addActionListener(e -> {
      new TicTacToe(this, true, true).start(this);
      setVisible(true);
    });

    Resources.addMenu(ticTacToe, ticTacToePvsP, ticTacToePvsCPU);
    Resources.addMenu(games, hangman, dices, roulette, guessNumberMenu, puzzle, ticTacToe);

    JMenuItem database = new MenuItem().setText("Database").setImage("tabla").build();
    database.addActionListener(e -> {
      if (openDicesTable(this)) {
        setVisible(true);
      }
    });

    scores.add(database);

    JMenuItem store = new MenuItem().setText("Store").setImage("compraventa").build();
    store.addActionListener(e -> {
      new LoginStore(this, true).start(this);
      setVisible(true);
    });

    JMenuItem whatsApp = new MenuItem().setText("Send Message").setImage("whatsapp").build();
    whatsApp.addActionListener(e -> {
      new WhatsAppView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem structures = new MenuItem().setText("Structures").setImage("estructuras").build();
    structures.addActionListener(e -> {
      new StructuresView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem numbers = new MenuItem().setText("Numbers").setImage("numeritos").build();
    numbers.addActionListener(e -> {
      new Numbers(this, true).start(this);
      setVisible(true);
    });

    JMenuItem notes = new MenuItem().setText("Notes").setImage("notas").build();
    notes.addActionListener(e -> {
      new NotesView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem texts = new MenuItem().setText("Texts").setImage("textos").build();
    texts.addActionListener(e -> {
      new TextsView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem guessNumberTXT = new MenuItem().setText("Guess Number").setImage("adivinar").build();
    guessNumberTXT.addActionListener(e -> {
      guessNumberTable.cleanTable();
      ExportFile.txt(
          guessNumberTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)), ".txt"
      );
    });

    JMenuItem hangmanTXT = new MenuItem().setText("Hangman").setImage("ahorcado").build();
    hangmanTXT.addActionListener(e -> {
      hangmanTable.cleanTable();
      ExportFile.txt(
          hangmanTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.HANGMAN)), ".txt"
      );
    });

    JMenuItem purchasesTXT = new MenuItem().setText("Purchases").setImage("comprar").build();
    purchasesTXT.addActionListener(e -> {
      purchasesTable.cleanTable();
      ExportFile.txt(
          purchasesTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.PURCHASES)), ".txt"
      );
    });

    JMenuItem dicesTXT = new MenuItem().setText("Dices").setImage("dado").build();
    dicesTXT.addActionListener(e -> {
      dicesTable.cleanTable();
      ExportFile.txt(
          dicesTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.DICES)), ".txt"
      );
    });

    JMenuItem notesTXT = new MenuItem().setText("Notes").setImage("notas").build();
    notesTXT.addActionListener(e -> {
      notesTable.cleanTable();
      ExportFile.txt(
          notesTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.NOTES)), ".txt"
      );
    });

    JMenuItem puzzleTXT = new MenuItem().setText("Puzzle").setImage("rompecabezas").build();
    puzzleTXT.addActionListener(e -> {
      puzzleTable.cleanTable();
      ExportFile.txt(
          puzzleTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.PUZZLE)), ".txt"
      );
    });

    JMenuItem inventoryTXT = new MenuItem().setText("Inventory").setImage("inventario").build();
    inventoryTXT.addActionListener(e -> {
      inventoryTable.cleanTable();
      ExportFile.txt(
          inventoryTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.INVENTORY)), ".txt"
      );
    });

    JMenuItem cashRegisterTXT = new MenuItem().setText("Cash Register").setImage("registradora").build();
    cashRegisterTXT.addActionListener(e -> {
      cashRegisterTable.cleanTable();
      ExportFile.txt(
          cashRegisterTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.CASH_REGISTER)), ".txt"
      );
    });

    JMenuItem loansTXT = new MenuItem().setText("Loans").setImage("prestamos").build();
    loansTXT.addActionListener(e -> {
      loansTable.cleanTable();
      ExportFile.txt(
          loansTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.LOANS)), ".txt"
      );
    });

    JMenuItem salesTXT = new MenuItem().setText("Sales").setImage("vender").build();
    salesTXT.addActionListener(e -> {
      salesTable.cleanTable();
      ExportFile.txt(
          salesTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.SALES)), ".txt"
      );
    });

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

    JMenuItem guessNumberEXCEL = new MenuItem().setText("Guess Number").setImage("adivinar").build();
    guessNumberEXCEL.addActionListener(e -> {
      guessNumberTable.cleanTable();
      ExportFile.excel(
          guessNumberTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)),
          ".xls"
      );
    });

    JMenuItem hangmanEXCEL = new MenuItem().setText("Hangman").setImage("ahorcado").build();
    hangmanEXCEL.addActionListener(e -> {
      hangmanTable.cleanTable();
      ExportFile.excel(
          hangmanTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.HANGMAN)),
          ".xls"
      );
    });

    JMenuItem purchasesEXCEL = new MenuItem().setText("Purchases").setImage("comprar").build();
    purchasesEXCEL.addActionListener(e -> {
      purchasesTable.cleanTable();
      ExportFile.excel(
          purchasesTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.PURCHASES)),
          ".xls"
      );
    });

    JMenuItem dicesEXCEL = new MenuItem().setText("Dices").setImage("dado").build();
    dicesEXCEL.addActionListener(e -> {
      dicesTable.cleanTable();
      ExportFile.excel(
          dicesTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.DICES)),
          ".xls"
      );
    });

    JMenuItem notesEXCEL = new MenuItem().setText("Notes").setImage("notas").build();
    notesEXCEL.addActionListener(e -> {
      notesTable.cleanTable();
      ExportFile.excel(
          notesTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.NOTES)),
          ".xls"
      );
    });

    JMenuItem puzzleEXCEL = new MenuItem().setText("Puzzle").setImage("rompecabezas").build();
    puzzleEXCEL.addActionListener(e -> {
      puzzleTable.cleanTable();
      ExportFile.excel(
          puzzleTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.PUZZLE)),
          ".xls"
      );
    });

    JMenuItem inventoryEXCEL = new MenuItem().setText("Inventory").setImage("inventario").build();
    inventoryEXCEL.addActionListener(e -> {
      inventoryTable.cleanTable();
      ExportFile.excel(
          inventoryTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.INVENTORY)),
          ".xls"
      );
    });

    JMenuItem cashRegisterEXCEL = new MenuItem().setText("Cash Register").setImage("registradora").build();
    cashRegisterEXCEL.addActionListener(e -> {
      cashRegisterTable.cleanTable();
      ExportFile.excel(
          cashRegisterTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.CASH_REGISTER)),
          ".xls"
      );
    });

    JMenuItem loansEXCEL = new MenuItem().setText("Loans").setImage("prestamos").build();
    loansEXCEL.addActionListener(e -> {
      loansTable.cleanTable();
      ExportFile.excel(
          loansTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.LOANS)),
          ".xls"
      );
    });

    JMenuItem salesEXCEL = new MenuItem().setText("Sales").setImage("vender").build();
    salesEXCEL.addActionListener(e -> {
      salesTable.cleanTable();
      ExportFile.excel(
          salesTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.SALES)),
          ".xls"
      );
    });

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

    JMenuItem guessNumberPDF = new MenuItem().setText("Guess Number").setImage("adivinar").build();
    guessNumberPDF.addActionListener(e -> {
      guessNumberTable.cleanTable();
      ExportFile.pdf(
          guessNumberTable.viewTable,
          DatabaseConstants.GUESS_NUMBER,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)),
          ".pdf"
      );
    });

    JMenuItem hangmanPDF = new MenuItem().setText("Hangman").setImage("ahorcado").build();
    hangmanPDF.addActionListener(e -> {
      hangmanTable.cleanTable();
      ExportFile.pdf(
          hangmanTable.viewTable,
          DatabaseConstants.HANGMAN,
          Queries.getAllData(Format.tableName(DatabaseConstants.HANGMAN)),
          ".pdf"
      );
    });

    JMenuItem purchasesPDF = new MenuItem().setText("Purchases").setImage("comprar").build();
    purchasesPDF.addActionListener(e -> {
      purchasesTable.cleanTable();
      ExportFile.pdf(
          purchasesTable.viewTable,
          DatabaseConstants.PURCHASES,
          Queries.getAllData(Format.tableName(DatabaseConstants.PURCHASES)),
          ".pdf"
      );
    });

    JMenuItem dicesPDF = new MenuItem().setText("Dices").setImage("dado").build();
    dicesPDF.addActionListener(e -> {
      dicesTable.cleanTable();
      ExportFile.pdf(
          dicesTable.viewTable,
          DatabaseConstants.DICES,
          Queries.getAllData(Format.tableName(DatabaseConstants.DICES)),
          ".pdf"
      );
    });

    JMenuItem notesPDF = new MenuItem().setText("Notes").setImage("notas").build();
    notesPDF.addActionListener(e -> {
      notesTable.cleanTable();
      ExportFile.pdf(
          notesTable.viewTable,
          DatabaseConstants.NOTES,
          Queries.getAllData(Format.tableName(DatabaseConstants.NOTES)),
          ".pdf"
      );
    });

    JMenuItem puzzlePDF = new MenuItem().setText("Puzzle").setImage("rompecabezas").build();
    puzzlePDF.addActionListener(e -> {
      puzzleTable.cleanTable();
      ExportFile.pdf(
          puzzleTable.viewTable,
          DatabaseConstants.PUZZLE,
          Queries.getAllData(Format.tableName(DatabaseConstants.PUZZLE)),
          ".pdf"
      );
    });

    JMenuItem inventoryPDF = new MenuItem().setText("Inventory").setImage("inventario").build();
    inventoryPDF.addActionListener(e -> {
      inventoryTable.cleanTable();
      ExportFile.pdf(
          inventoryTable.viewTable,
          DatabaseConstants.INVENTORY,
          Queries.getAllData(Format.tableName(DatabaseConstants.INVENTORY)),
          ".pdf"
      );
    });

    JMenuItem cashRegisterPDF = new MenuItem().setText("Cash Register").setImage("registradora").build();
    cashRegisterPDF.addActionListener(e -> {
      cashRegisterTable.cleanTable();
      ExportFile.pdf(
          cashRegisterTable.viewTable,
          DatabaseConstants.CASH_REGISTER,
          Queries.getAllData(Format.tableName(DatabaseConstants.CASH_REGISTER)),
          ".pdf"
      );
    });

    JMenuItem loansPDF = new MenuItem().setText("Loans").setImage("prestamos").build();
    loansPDF.addActionListener(e -> {
      loansTable.cleanTable();
      ExportFile.pdf(
          loansTable.viewTable,
          DatabaseConstants.LOANS,
          Queries.getAllData(Format.tableName(DatabaseConstants.LOANS)),
          ".pdf"
      );
    });

    JMenuItem salesPDF = new MenuItem().setText("Sales").setImage("vender").build();
    salesPDF.addActionListener(e -> {
      salesTable.cleanTable();
      ExportFile.pdf(
          salesTable.viewTable,
          DatabaseConstants.SALES,
          Queries.getAllData(Format.tableName(DatabaseConstants.SALES)),
          ".pdf"
      );
    });

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

    JMenuItem searchBook = new MenuItem().setText("Search Book").setImage("searchBook").build();
    searchBook.addActionListener(e -> {
      new ConsultBook(this, true).start(this);
      setVisible(true);
    });

    JMenuItem registerUser = new MenuItem().setText("Register User").setImage("añadir_usuario").build();
    registerUser.addActionListener(e -> StartTests.startRegisterUser());

    Resources.addMenu(tasks, searchBook, registerUser);

    createWallpapers(changeBackground);
    createThemes();

    Resources.addMenu(
        themes,
        model.getDefaultMode(),
        model.getDarkMode(),
        model.getTextureMode(),
        model.getMacMode(),
        model.getGrayMode(),
        model.getMintMode(),
        model.getClassicMode()
    );

    Resources.addMenu(changeUI, themes, changeBackground);

    JMenuItem ocr = new MenuItem().setText("OCR").setImage("ocr").build();
    ocr.addActionListener(e -> {
      new OcrView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem qr = new MenuItem().setText("QR").setImage("qr").build();
    qr.addActionListener(e -> {
      new QrView(this, true).start(this);
      setVisible(true);
    });

    Resources.addMenu(
        tools,
        changeUI,
        export,
        notes,
        ocr,
        qr,
        numbers,
        store,
        whatsApp,
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

  private void createThemes() {

    model.setDefaultMode(new MenuItem().setText("Default").setImage("default_theme").build());
    model.getDefaultMode().addActionListener(e -> {
      if (model.getDefaultMode().getForeground() == Styles.MAIN_COLOR) {
        Alerts.elementApplied(true);
        return;
      }

      service.setTheme(model, AppThemes.DEFAULT, this);
    });

    model.setDarkMode(new MenuItem().setText("Dark").setImage("dark").build());
    model.getDarkMode().addActionListener(e -> {
      if (model.getDarkMode().getForeground() == Styles.MAIN_COLOR) {
        Alerts.elementApplied(true);
        return;
      }

      service.setTheme(model, AppThemes.DARK, this);
    });

    model.setTextureMode(new MenuItem().setText("Texture").setImage("texture").build());
    model.getTextureMode().addActionListener(e -> {
      if (model.getTextureMode().getForeground() == Styles.MAIN_COLOR) {
        Alerts.elementApplied(true);
        return;
      }

      service.setTheme(model, AppThemes.TEXTURE, this);
    });

    model.setMacMode(new MenuItem().setText("Mac OS").setImage("mac").build());
    model.getMacMode().addActionListener(e -> {
      if (model.getMacMode().getForeground() == Styles.MAIN_COLOR) {
        Alerts.elementApplied(true);
        return;
      }

      service.setTheme(model, AppThemes.MAC, this);
    });

    model.setGrayMode(new MenuItem().setText("Metallic").setImage("gray").build());
    model.getGrayMode().addActionListener(e -> {
      if (model.getGrayMode().getForeground() == Styles.MAIN_COLOR) {
        Alerts.elementApplied(true);
        return;
      }

      service.setTheme(model, AppThemes.GRAY, this);
    });

    model.setMintMode(new MenuItem().setText("Mint").setImage("mint").build());
    model.getMintMode().addActionListener(e -> {
      if (model.getDarkMode().getForeground() == Styles.MAIN_COLOR) {
        Alerts.elementApplied(true);
        return;
      }

      service.setTheme(model, AppThemes.MINT, this);
    });

    model.setClassicMode(new MenuItem().setText("Classic").setImage("classic").build());
    model.getClassicMode().addActionListener(e -> {
      if (model.getClassicMode().getForeground() == Styles.MAIN_COLOR) {
        Alerts.elementApplied(true);
        return;
      }

      service.setTheme(model, AppThemes.CLASSIC, this);
    });
  }

  private void createWallpapers(JMenu changeBackground) {
    IntStream.range(0, model.getWallpapers().length).forEach(i -> {
      model.getWallpapers()[i] = new JMenuItem("Image " + (i + 1));
      model.getWallpapers()[i].setForeground(Styles.TEXT_COLOR);
      model.getWallpapers()[i].setIcon(new ImageIcon(Resources.getImage("backs.png")));
      model.getWallpapers()[i].addActionListener(e -> service.setWallpaper(model, e, this));
      changeBackground.add(model.getWallpapers()[i]);
      changeBackground.addSeparator();
    });
  }
}
