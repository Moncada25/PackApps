package com.bookverse.development.packapps.apps.home;

import com.bookverse.development.packapps.utils.ui.MenuBuilder;
import com.bookverse.development.packapps.utils.ui.MenuItemBuilder;
import java.awt.BorderLayout;
import java.awt.Color;
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
import com.bookverse.development.packapps.utils.constants.AppConfig;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Themes;
import com.bookverse.development.packapps.apps.views.DicesGameView;
import com.bookverse.development.packapps.apps.views.EmailView;
import com.bookverse.development.packapps.apps.views.FeedbackView;
import com.bookverse.development.packapps.apps.notes.NotesView;
import com.bookverse.development.packapps.apps.views.ProfessionalCardView;
import com.bookverse.development.packapps.apps.views.OcrView;
import com.bookverse.development.packapps.apps.views.QrView;
import com.bookverse.development.packapps.apps.views.StructuresView;
import com.bookverse.development.packapps.apps.views.TextsView;
import com.bookverse.development.packapps.apps.views.WhatsAppView;
import com.bookverse.development.packapps.automation.utils.StartTests;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.other.Config;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.constants.ArrayData;
import com.bookverse.development.packapps.utils.other.ExportFile;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.constants.Queries;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.apps.views.older.ConsultBook;
import com.bookverse.development.packapps.apps.views.older.GuessNumber;
import com.bookverse.development.packapps.apps.views.older.Hangman;
import com.bookverse.development.packapps.apps.views.older.LoginStore;
import com.bookverse.development.packapps.apps.views.older.Numbers;
import com.bookverse.development.packapps.apps.views.older.Puzzle;
import com.bookverse.development.packapps.apps.views.older.TicTacToe;

public class HomeView extends JFrame {

  private transient HomeService service = new HomeService();
  
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

    Themes.welcome = new JLabel();
    window.setSize(
        ArrayData.getWidthBackground(Themes.background - 1),
        ArrayData.getLongBackground(Themes.background - 1)
    );
    window.add(Themes.welcome, BorderLayout.CENTER);
    Themes.changeBackgroundAP(
        ArrayData.getPathBackground(Themes.background - 1),
        ArrayData.getWidthBackground(Themes.background - 1),
        ArrayData.getLongBackground(Themes.background - 1), window
    );

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
    JMenu about = new MenuBuilder().setText("About").setImage("about").build();
    JMenu more = new MenuBuilder().setText("See More").setImage("more").build();
    JMenu exit = new MenuBuilder().setText("Exit").setImage("exit").build();
    JMenu send = Resources.getMenu("Send Feedback", "send");
    JMenu games = Resources.getMenu("Games", "games");
    JMenu guessNumberMenu = Resources.getMenu(DatabaseConstants.GUESS_NUMBER, "adivinar");
    JMenu puzzle = Resources.getMenu(DatabaseConstants.PUZZLE, "rompecabezas");
    JMenu ticTacToe = Resources.getMenu("Tic Tac Toe", "triqui");
    JMenu scores = Resources.getMenu("Data", "data");
    JMenu tools = Resources.getMenu("Utils", "tools");
    JMenu export = Resources.getMenu("Export Data", "export");
    JMenu exportTXT = Resources.getMenu("Document TXT", "txt");
    JMenu exportEXCEL = Resources.getMenu("Document XLS", "excel");
    JMenu exportPDF = Resources.getMenu("Document PDF", "pdf");
    JMenu tasks = Resources.getMenu("Tasks", "task");
    JMenu changeBackground = Resources.getMenu("Background", "background");
    JMenu mode = Resources.getMenu("Theme", "mode");
    JMenu changeUI = Resources.getMenu("Change UI", "UI");

    JMenuItem professionalCard = new MenuItemBuilder()
        .setText("Developer")
        .setImage("developer")
        .build();
    professionalCard.addActionListener(e -> {
      new ProfessionalCardView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem moreSystems = new MenuItemBuilder()
        .setText("My Systems")
        .setImage("mysystems")
        .build();
    moreSystems.addActionListener(e -> {
          if (GeneralUtils.verifyConnection("Connect to see more!", Themes.isWork)) {
            GeneralUtils.openUrl("https://mypackapps.000webhostapp.com");
          }
        }
    );

    JMenuItem moreBookverse = new MenuBuilder().setText("Bookverse").setImage("books").build();
    moreBookverse.addActionListener(e -> {
          if (GeneralUtils.verifyConnection("Connect to see more!", Themes.isWork)) {
            GeneralUtils.openUrl("https://bookverse.vzpla.net");
          }
        }
    );

    Resources.addMenu(more, moreSystems, moreBookverse);
    Resources.addMenu(about, professionalCard, more);

    JMenuItem yesExit = Resources.getMenuItem("Are you sure?", "salir");
    yesExit.addActionListener(e -> Effects.fadeOut(this));

    JMenuItem email = Resources.getMenuItem("Email", "email");
    email.addActionListener(e -> {
      new EmailView(this, true).start(this);
      setVisible(true);
    });
    
    JMenuItem comment = Resources.getMenuItem("Comment", "feedback");
    comment.addActionListener(e -> {
      new FeedbackView(this, true).start(this);
      setVisible(true);
    });

    Resources.addMenu(send, email, comment);
    Resources.addMenu(exit, yesExit, send);

    JMenuItem hangman = Resources.getMenuItem(DatabaseConstants.HANGMAN, "ahorcado");
    hangman.addActionListener(e -> {
      new Hangman(this, true).start(this);
      setVisible(true);
    });

    JMenuItem dices = Resources.getMenuItem(DatabaseConstants.DICES, "dado");
    dices.addActionListener(e -> {
      new DicesGameView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem roulette = Resources.getMenuItem("Roulette", "ruleta");
    roulette.addActionListener(e -> {
          if (GeneralUtils.verifyConnection("Connect to see more!", Themes.isWork)) {
            GeneralUtils.openUrl("https://mypackapps.000webhostapp.com/ruleta.php");
          }
        }
    );

    JMenuItem guessNumber = Resources.getMenuItem("Easy", "easy");
    guessNumber.addActionListener(e -> {
      new GuessNumber(this, true, false).start(this);
      setVisible(true);
    });

    JMenuItem guessNumberHard = Resources.getMenuItem("Hard", "hard");
    guessNumberHard.addActionListener(e -> {
      new GuessNumber(this, true, true).start(this);
      setVisible(true);
    });

    Resources.addMenu(guessNumberMenu, guessNumber, guessNumberHard);

    JMenuItem puzzle4x4 = Resources.getMenuItem("Easy", "easy");
    puzzle4x4.addActionListener(e -> {
      new Puzzle(this, true, 4, 55, 3).start(this);
      setVisible(true);
    });

    JMenuItem puzzle5x5 = Resources.getMenuItem("Medium", "medio");
    puzzle5x5.addActionListener(e -> {
      new Puzzle(this, true, 5, 50, 6).start(this);
      setVisible(true);
    });

    JMenuItem puzzle6x6 = Resources.getMenuItem("Hard", "hard");
    puzzle6x6.addActionListener(e -> {
      new Puzzle(this, true, 6, 45, 10).start(this);
      setVisible(true);
    });

    Resources.addMenu(puzzle, puzzle4x4, puzzle5x5, puzzle6x6);

    JMenuItem ticTacToePvsP = Resources.getMenuItem("Player vs Player", "jvsj");
    ticTacToePvsP.addActionListener(e -> {
      new TicTacToe(this, true, false).start(this);
      setVisible(true);
    });

    JMenuItem ticTacToePvsCPU = Resources.getMenuItem("Player vs CPU (beta)", "jvscpu");
    ticTacToePvsCPU.addActionListener(e -> {
      new TicTacToe(this, true, true).start(this);
      setVisible(true);
    });

    Resources.addMenu(ticTacToe, ticTacToePvsP, ticTacToePvsCPU);
    Resources.addMenu(games, hangman, dices, roulette, guessNumberMenu, puzzle, ticTacToe);

    JMenuItem database = Resources.getMenuItem("Database", "tabla");
    database.addActionListener(e -> {
      if (service.dicesTableAP(this)) {
        setVisible(true);
      }
    });

    scores.add(database);

    JMenuItem store = Resources.getMenuItem("Store", "compraventa");
    store.addActionListener(e -> {
      new LoginStore(this, true).start(this);
      setVisible(true);
    });

    JMenuItem sendWhatsApp = Resources.getMenuItem("Send Message", "whatsapp");
    sendWhatsApp.addActionListener(e -> {
      new WhatsAppView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem structures = Resources.getMenuItem("Structures", "estructuras");
    structures.addActionListener(e -> {
      new StructuresView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem numbers = Resources.getMenuItem("Numbers", "numeritos");
    numbers.addActionListener(e -> {
      new Numbers(this, true).start(this);
      setVisible(true);
    });

    JMenuItem notes = Resources.getMenuItem(DatabaseConstants.NOTES, "notas");
    notes.addActionListener(e -> {
      new NotesView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem texts = Resources.getMenuItem("Texts", "textos");
    texts.addActionListener(e -> {
      new TextsView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem guessNumberTXT = Resources.getMenuItem(DatabaseConstants.GUESS_NUMBER, "adivinar");
    guessNumberTXT.addActionListener(e -> {
      service.getGuessNumberTable().cleanTable();
      ExportFile.txt(
          service.getGuessNumberTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)), ".txt"
      );
    });

    JMenuItem hangmanTXT = Resources.getMenuItem(DatabaseConstants.HANGMAN, "ahorcado");
    hangmanTXT.addActionListener(e -> {
      service.getHangmanTable().cleanTable();
      ExportFile.txt(
          service.getHangmanTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.HANGMAN)), ".txt"
      );
    });

    JMenuItem purchasesTXT = Resources.getMenuItem(DatabaseConstants.PURCHASES, "comprar");
    purchasesTXT.addActionListener(e -> {
      service.getPurchasesTable().cleanTable();
      ExportFile.txt(
          service.getPurchasesTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.PURCHASES)), ".txt"
      );
    });

    JMenuItem dicesTXT = Resources.getMenuItem(DatabaseConstants.DICES, "dado");
    dicesTXT.addActionListener(e -> {
      service.getDicesTable().cleanTable();
      ExportFile.txt(
          service.getDicesTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.DICES)), ".txt"
      );
    });

    JMenuItem notesTXT = Resources.getMenuItem(DatabaseConstants.NOTES, "notas");
    notesTXT.addActionListener(e -> {
      service.getNotesTable().cleanTable();
      ExportFile.txt(
          service.getNotesTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.NOTES)), ".txt"
      );
    });

    JMenuItem puzzleTXT = Resources.getMenuItem(DatabaseConstants.PUZZLE, "rompecabezas");
    puzzleTXT.addActionListener(e -> {
      service.getPuzzleTable().cleanTable();
      ExportFile.txt(
          service.getPuzzleTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.PUZZLE)), ".txt"
      );
    });

    JMenuItem inventoryTXT = Resources.getMenuItem(DatabaseConstants.INVENTORY, "inventario");
    inventoryTXT.addActionListener(e -> {
      service.getInventoryTable().cleanTable();
      ExportFile.txt(
          service.getInventoryTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.INVENTORY)), ".txt"
      );
    });

    JMenuItem cashRegisterTXT = Resources.getMenuItem(
        DatabaseConstants.CASH_REGISTER, "registradora"
    );
    cashRegisterTXT.addActionListener(e -> {
      service.getCashRegisterTable().cleanTable();
      ExportFile.txt(
          service.getCashRegisterTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.CASH_REGISTER)), ".txt"
      );
    });

    JMenuItem loansTXT = Resources.getMenuItem(DatabaseConstants.LOANS, "prestamos");
    loansTXT.addActionListener(e -> {
      service.getLoansTable().cleanTable();
      ExportFile.txt(
          service.getLoansTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.LOANS)), ".txt"
      );
    });

    JMenuItem salesTXT = Resources.getMenuItem(DatabaseConstants.SALES, "vender");
    salesTXT.addActionListener(e -> {
      service.getSalesTable().cleanTable();
      ExportFile.txt(
          service.getSalesTable().viewTable,
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

    JMenuItem guessNumberEXCEL = Resources.getMenuItem(DatabaseConstants.GUESS_NUMBER, "adivinar");
    guessNumberEXCEL.addActionListener(e -> {
      service.getGuessNumberTable().cleanTable();
      ExportFile.excel(
          service.getGuessNumberTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)),
          ".xls"
      );
    });

    JMenuItem hangmanEXCEL = Resources.getMenuItem(DatabaseConstants.HANGMAN, "ahorcado");
    hangmanEXCEL.addActionListener(e -> {
      service.getHangmanTable().cleanTable();
      ExportFile.excel(
          service.getHangmanTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.HANGMAN)),
          ".xls"
      );
    });

    JMenuItem purchasesEXCEL = Resources.getMenuItem(DatabaseConstants.PURCHASES, "comprar");
    purchasesEXCEL.addActionListener(e -> {
      service.getPurchasesTable().cleanTable();
      ExportFile.excel(
          service.getPurchasesTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.PURCHASES)),
          ".xls"
      );
    });

    JMenuItem dicesEXCEL = Resources.getMenuItem(DatabaseConstants.DICES, "dado");
    dicesEXCEL.addActionListener(e -> {
      service.getDicesTable().cleanTable();
      ExportFile.excel(
          service.getDicesTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.DICES)),
          ".xls"
      );
    });

    JMenuItem notesEXCEL = Resources.getMenuItem(DatabaseConstants.NOTES, "notas");
    notesEXCEL.addActionListener(e -> {
      service.getNotesTable().cleanTable();
      ExportFile.excel(
          service.getNotesTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.NOTES)),
          ".xls"
      );
    });

    JMenuItem puzzleEXCEL = Resources.getMenuItem(DatabaseConstants.PUZZLE, "rompecabezas");
    puzzleEXCEL.addActionListener(e -> {
      service.getPuzzleTable().cleanTable();
      ExportFile.excel(
          service.getPuzzleTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.PUZZLE)),
          ".xls"
      );
    });

    JMenuItem inventoryEXCEL = Resources.getMenuItem(DatabaseConstants.INVENTORY, "inventario");
    inventoryEXCEL.addActionListener(e -> {
      service.getInventoryTable().cleanTable();
      ExportFile.excel(
          service.getInventoryTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.INVENTORY)),
          ".xls"
      );
    });

    JMenuItem cashRegisterEXCEL = Resources.getMenuItem(DatabaseConstants.CASH_REGISTER, "registradora");
    cashRegisterEXCEL.addActionListener(e -> {
      service.getCashRegisterTable().cleanTable();
      ExportFile.excel(
          service.getCashRegisterTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.CASH_REGISTER)),
          ".xls"
      );
    });

    JMenuItem loansEXCEL = Resources.getMenuItem(DatabaseConstants.LOANS, "prestamos");
    loansEXCEL.addActionListener(e -> {
      service.getLoansTable().cleanTable();
      ExportFile.excel(
          service.getLoansTable().viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.LOANS)),
          ".xls"
      );
    });

    JMenuItem salesEXCEL = Resources.getMenuItem(DatabaseConstants.SALES, "vender");
    salesEXCEL.addActionListener(e -> {
      service.getSalesTable().cleanTable();
      ExportFile.excel(
          service.getSalesTable().viewTable,
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

    JMenuItem guessNumberPDF = Resources.getMenuItem(DatabaseConstants.GUESS_NUMBER, "adivinar");
    guessNumberPDF.addActionListener(e -> {
      service.getGuessNumberTable().cleanTable();
      ExportFile.pdf(
          service.getGuessNumberTable().viewTable,
          DatabaseConstants.GUESS_NUMBER,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)),
          ".pdf"
      );
    });

    JMenuItem hangmanPDF = Resources.getMenuItem(DatabaseConstants.HANGMAN, "ahorcado");
    hangmanPDF.addActionListener(e -> {
      service.getHangmanTable().cleanTable();
      ExportFile.pdf(
          service.getHangmanTable().viewTable,
          DatabaseConstants.HANGMAN,
          Queries.getAllData(Format.tableName(DatabaseConstants.HANGMAN)),
          ".pdf"
      );
    });
    
    JMenuItem purchasesPDF = Resources.getMenuItem(DatabaseConstants.PURCHASES, "comprar");
    purchasesPDF.addActionListener(e -> {
      service.getPurchasesTable().cleanTable();
      ExportFile.pdf(
          service.getPurchasesTable().viewTable,
          DatabaseConstants.PURCHASES,
          Queries.getAllData(Format.tableName(DatabaseConstants.PURCHASES)),
          ".pdf"
      );
    });

    JMenuItem dicesPDF = Resources.getMenuItem(DatabaseConstants.DICES, "dado");
    dicesPDF.addActionListener(e -> {
      service.getDicesTable().cleanTable();
      ExportFile.pdf(
          service.getDicesTable().viewTable,
          DatabaseConstants.DICES,
          Queries.getAllData(Format.tableName(DatabaseConstants.DICES)),
          ".pdf"
      );
    });

    JMenuItem notesPDF = Resources.getMenuItem(DatabaseConstants.NOTES, "notas");
    notesPDF.addActionListener(e -> {
      service.getNotesTable().cleanTable();
      ExportFile.pdf(
          service.getNotesTable().viewTable,
          DatabaseConstants.NOTES,
          Queries.getAllData(Format.tableName(DatabaseConstants.NOTES)),
          ".pdf"
      );
    });

    JMenuItem puzzlePDF = Resources.getMenuItem(DatabaseConstants.PUZZLE, "rompecabezas");
    puzzlePDF.addActionListener(e -> {
      service.getPuzzleTable().cleanTable();
      ExportFile.pdf(
          service.getPuzzleTable().viewTable,
          DatabaseConstants.PUZZLE,
          Queries.getAllData(Format.tableName(DatabaseConstants.PUZZLE)),
          ".pdf"
      );
    });

    JMenuItem inventoryPDF = Resources.getMenuItem(DatabaseConstants.INVENTORY, "inventario");
    inventoryPDF.addActionListener(e -> {
      service.getInventoryTable().cleanTable();
      ExportFile.pdf(
          service.getInventoryTable().viewTable,
          DatabaseConstants.INVENTORY,
          Queries.getAllData(Format.tableName(DatabaseConstants.INVENTORY)),
          ".pdf"
      );
    });

    JMenuItem cashRegisterPDF = Resources.getMenuItem(DatabaseConstants.CASH_REGISTER, "registradora");
    cashRegisterPDF.addActionListener(e -> {
      service.getCashRegisterTable().cleanTable();
      ExportFile.pdf(
          service.getCashRegisterTable().viewTable,
          DatabaseConstants.CASH_REGISTER,
          Queries.getAllData(Format.tableName(DatabaseConstants.CASH_REGISTER)),
          ".pdf"
      );
    });

    JMenuItem loansPDF = Resources.getMenuItem(DatabaseConstants.LOANS, "prestamos");
    loansPDF.addActionListener(e -> {
      service.getLoansTable().cleanTable();
      ExportFile.pdf(
          service.getLoansTable().viewTable,
          DatabaseConstants.LOANS,
          Queries.getAllData(Format.tableName(DatabaseConstants.LOANS)),
          ".pdf"
      );
    });

    JMenuItem salesPDF = Resources.getMenuItem(DatabaseConstants.SALES, "vender");
    salesPDF.addActionListener(e -> {
      service.getSalesTable().cleanTable();
      ExportFile.pdf(
          service.getSalesTable().viewTable,
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

    JMenuItem searchBook = Resources.getMenuItem("Search Book", "searchBook");
    searchBook.addActionListener(e -> {
      new ConsultBook(this, true).start(this);
      setVisible(true);
    });

    JMenuItem registerUser = Resources.getMenuItem("Register User", "añadir_usuario");
    registerUser.addActionListener(e -> StartTests.startRegisterUser());

    Resources.addMenu(tasks, searchBook, registerUser);


    Themes.setWallpapers(this, changeBackground);


    Themes.setThemes(this, mode);

    Resources.addMenu(changeUI, mode, changeBackground);

    JMenuItem ocr = Resources.getMenuItem("OCR", "ocr");
    ocr.addActionListener(e -> {
      new OcrView(this, true).start(this);
      setVisible(true);
    });

    JMenuItem qr = Resources.getMenuItem("QR", "qr");
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

    service.setTables(this);
  }
}
