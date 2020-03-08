package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.TableConstants.DICES;
import static com.bookverse.development.packapps.utils.TableConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.utils.TableConstants.HANGMAN;
import static com.bookverse.development.packapps.utils.ArrayData.LONG_IMAGES;
import static com.bookverse.development.packapps.utils.ArrayData.PATH_IMAGES;
import static com.bookverse.development.packapps.utils.ArrayData.WIDTH_IMAGES;

import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Index extends JFrame implements ActionListener {

  private static Resources resources = new Resources();
  private static int background = 2;
  private static JLabel welcome;
  public HangmanTable hangmanTable = new HangmanTable(this, true);
  public GuessNumberTable guessNumberTable = new GuessNumberTable(this, true);
  public RompecabezasTabla puzzleTable = new RompecabezasTabla(this, true);
  public DicesTable dicesTable = new DicesTable(this, true);
  public NotasTabla notesTable = new NotasTabla(this, true);
  public InventarioTabla inventoryTable = new InventarioTabla(this, true);
  public RegistradoraTabla cashRegisterTable = new RegistradoraTabla(this, true);
  public PrestamosTabla loansTable = new PrestamosTabla(this, true);
  public ComprasTabla purchasesTable = new ComprasTabla(this, true);
  public VentasTabla salesTable = new VentasTabla(this, true);
  private JMenuItem[] images = new JMenuItem[14];
  private boolean isWork = true;
  private JMenu changeBackground;
  private JMenuItem moreProfiles, moreSystems, moreBookverse, darkMode, textureMode, mintMode, classicMode, macMode, grayMode,
      texts, guessNumber, guessNumberHard, hangman, structures, dices, buyAndSell, numbers, puzzle4x4, puzzle5x5, puzzle6x6,
      roulette, triquiPvsP, triquiPvsCPU, tables, notes, yes_exit, email, comment, guessNumberTXT, hangmanTXT, dicesTXT, notesTXT,
      inventoryTXT, purchasesTXT, salesTXT, cashRegisterTXT, loansTXT, puzzleTXT, guessNumberEXCEL, hangmanEXCEL, dicesEXCEL, notesEXCEL,
      inventoryEXCEL, purchasesEXCEL, salesEXCEL, cashRegisterEXCEL, loansEXCEL, puzzleEXCEL, guessNumberPDF, hangmanPDF, dicesPDF,
      notesPDF, inventoryPDF, purchasesPDF, salesPDF, cashRegisterPDF, loansPDF, puzzlePDF, read, timesheet;

  public Index() {
    createComponents();
  }

  public static void main(String[] args) {

    try {

      UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

      UIManager.put("PasswordField.border", resources.core.MEDIO);
      UIManager.put("PasswordField.font", resources.core.MEDIUM);

      UIManager.put("TextField.border", resources.core.MEDIO);
      UIManager.put("TextField.font", resources.core.MEDIUM);

      UIManager.put("FileChooser.saveButtonText", "Save");
      UIManager.put("FileChooser.cancelButtonText", "Cancel");

      UIManager.put("RadioButton.font", resources.core.MEDIUM);

      UIManager.put("TextArea.font", resources.core.MEDIUM);

      UIManager.put("ComboBox.font", resources.core.MEDIUM);
      UIManager.put("ComboBox.foreground", resources.core.AZUL);

      UIManager.put("ScrollPane.border", resources.core.MEDIO);

      UIManager.put("MenuItem.foreground", resources.core.AZUL);
      UIManager.put("MenuItem.font", resources.core.MEDIUM);

      UIManager.put("Menu.foreground", resources.core.ROJO);
      UIManager.put("Menu.font", resources.core.MEDIUM);

      UIManager.put("Button.font", resources.core.MEDIUM);

      UIManager.put("Table.focusCellHighlightBorder", resources.core.MEDIO);
      UIManager.put("TableHeader.foreground", resources.core.ROJO);
      UIManager.put("TableHeader.font", resources.core.MEDIUM);
      UIManager.put("Table.font", resources.core.MEDIUM);
      UIManager.put("Table.foreground", resources.core.AZUL);

      UIManager.put("OptionPane.okButtonText", "Done");
      UIManager.put("OptionPane.cancelButtonText", "No, thanks.");
      UIManager.put("OptionPane.yesButtonText", "Yes, it is okay.");
      UIManager.put("OptionPane.noButtonText", "No, thanks.");
      UIManager.put("OptionPane.messageFont", resources.core.MEDIUM);
      UIManager.put("OptionPane.buttonFont", resources.core.MEDIUM);
      UIManager.put("OptionPane.messageForeground", resources.core.AZUL);

    } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
        | IllegalAccessException e) {
      Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, e);
    }

    Index window = new Index();

    welcome = new JLabel();
    window.setSize(WIDTH_IMAGES[background - 1], LONG_IMAGES[background - 1]);
    window.add(welcome, BorderLayout.CENTER);
    window.ImgAP(PATH_IMAGES[background - 1], WIDTH_IMAGES[background - 1],
        LONG_IMAGES[background - 1]);

    window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle(resources.core.getTitle());
    window.images[background - 1].setForeground(resources.core.ROJO);
    window.macMode.setForeground(resources.core.ROJO);
    resources.core.fadeIn(window);
    window.setVisible(true);
  }

  public void createComponents() {

    setIconImage(new ImageIcon(resources.getImage("more.png")).getImage());

    JMenuBar menuBar = new JMenuBar();

    JMenu about = resources.getMenu("About", "about");
    read = resources.getMenuItem("Developer", "developer", this);

    JMenu more = resources.getMenu("See More", "more");
    moreSystems = resources.getMenuItem("My Systems", "mysystems", this);
    moreProfiles = resources.getMenuItem("Profiles", "profiles", this);
    moreBookverse = resources.getMenuItem("Bookverse", "books", this);

    more.add(moreBookverse);
    more.addSeparator();
    more.add(moreProfiles);
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
    hangman = resources.getMenuItem("Hangman", "ahorcado", this);
    dices = resources.getMenuItem("Dices", "dado", this);
    roulette = resources.getMenuItem("Roulette", "ruleta", this);

    JMenu guessNumberMenu = resources.getMenu("Guess Number", "adivinar");
    this.guessNumber = resources.getMenuItem("Easy", "easy", this);
    guessNumberHard = resources.getMenuItem("Hard", "hard", this);

    guessNumberMenu.add(this.guessNumber);
    guessNumberMenu.addSeparator();
    guessNumberMenu.add(guessNumberHard);

    JMenu puzzle = resources.getMenu("Puzzle", "rompecabezas");
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
    notes = resources.getMenuItem("Notes", "notas", this);
    texts = resources.getMenuItem("Texts", "textos", this);

    changeBackground = resources.getMenu("Background", "background");

    IntStream.range(0, images.length).forEach(i -> {
      images[i] = new JMenuItem("Image " + (i + 1));
      images[i].setForeground(resources.core.AZUL);
      images[i].setIcon(new ImageIcon(resources.getImage("backs.png")));
      images[i].addActionListener(this);
      changeBackground.add(images[i]);
      changeBackground.addSeparator();
    });

    JMenu export = resources.getMenu("Export Data", "export");

    JMenu exportTXT = resources.getMenu("Text file", "txt");
    guessNumberTXT = resources.getMenuItem("Guess Number", "adivinar", this);
    hangmanTXT = resources.getMenuItem("Hangman", "ahorcado", this);
    purchasesTXT = resources.getMenuItem("Purchases", "comprar", this);
    dicesTXT = resources.getMenuItem("Dices", "dado", this);
    notesTXT = resources.getMenuItem("Notes", "notas", this);
    puzzleTXT = resources.getMenuItem("Puzzle", "rompecabezas", this);
    inventoryTXT = resources.getMenuItem("Inventory", "inventario", this);
    cashRegisterTXT = resources.getMenuItem("Cash Register", "registradora", this);
    loansTXT = resources.getMenuItem("Loans", "prestamos", this);
    salesTXT = resources.getMenuItem("Sales", "vender", this);

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

    JMenu exportEXCEL = resources.getMenu("Hoja de Cálculo", "excel");
    guessNumberEXCEL = resources.getMenuItem("Guess Number", "adivinar", this);
    hangmanEXCEL = resources.getMenuItem("Hangman", "ahorcado", this);
    purchasesEXCEL = resources.getMenuItem("Purchases", "comprar", this);
    dicesEXCEL = resources.getMenuItem("Dices", "dado", this);
    notesEXCEL = resources.getMenuItem("Notes", "notas", this);
    puzzleEXCEL = resources.getMenuItem("Puzzle", "rompecabezas", this);
    inventoryEXCEL = resources.getMenuItem("Inventory", "inventario", this);
    cashRegisterEXCEL = resources.getMenuItem("Cash Register", "registradora", this);
    loansEXCEL = resources.getMenuItem("Loans", "prestamos", this);
    salesEXCEL = resources.getMenuItem("Sales", "vender", this);

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
    guessNumberPDF = resources.getMenuItem("Guess Number", "adivinar", this);
    hangmanPDF = resources.getMenuItem("Hangman", "ahorcado", this);
    purchasesPDF = resources.getMenuItem("Purchases", "comprar", this);
    dicesPDF = resources.getMenuItem("Dices", "dado", this);
    notesPDF = resources.getMenuItem("Notes", "notas", this);
    puzzlePDF = resources.getMenuItem("Puzzle", "rompecabezas", this);
    inventoryPDF = resources.getMenuItem("Inventory", "inventario", this);
    cashRegisterPDF = resources.getMenuItem("Cash Register", "registradora", this);
    loansPDF = resources.getMenuItem("Loans", "prestamos", this);
    salesPDF = resources.getMenuItem("Sales", "vender", this);

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

  public void startNumbersAP() {
    Numbers window = new Numbers(this, true);
    window.setSize(980, 570);
    window.setMinimumSize(new Dimension(480, 380));
    window.setMaximumSize(new Dimension(1295, 820));
    window.setLocationRelativeTo(this);
    window.setTitle("Numbers");
    resources.core.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void structuresAP() {
    Estructuras window = new Estructuras(this, true);
    window.setSize(717, 380);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Estructuras de Datos");
    resources.core.fadeIn(window);
    setVisible(false);
    resources.core.instruccionesEstructuras();
    window.setVisible(true);
  }

  public void TriquiAP() {
    Triqui window = new Triqui(this, true, false);
    window.setSize(450, 400);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Player vs Player");
    resources.core.fadeIn(window);
    setVisible(false);
    resources.core.instruccionesTriqui();
    window.setVisible(true);
  }

  public void TriquiCPUAP() {
    Triqui window = new Triqui(this, true, true);
    window.setSize(450, 400);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Player vs CPU (beta)");
    resources.core.fadeIn(window);
    setVisible(false);
    resources.core.instruccionesTriqui();
    window.setVisible(true);
  }

  public void CompraventaAP() {
    Login window = new Login(this, true);
    window.setSize(375, 400);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Compraventa");
    resources.core.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void TextosAP() {
    Textos window = new Textos(this, true);
    window.setSize(530, 330);
    window.setLocationRelativeTo(this);
    window.setMinimumSize(new Dimension(530, 330));
    window.setMaximumSize(new Dimension(1280, 720));
    window.setTitle("Text Editor");
    resources.core.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void SendCommentAP() {
    Comment window = new Comment(this, true);
    window.setSize(485, 480);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Send Commentary");
    resources.core.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void SendEmailAP() {
    Email window = new Email(this, true);
    window.setSize(485, 480);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Send Email");
    resources.core.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void Rompecabezas4x4AP() {
    Rompecabezas window = new Rompecabezas(this, true, 4, 55, 3);
    window.setSize(490, 380);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Level Easy");
    resources.core.fadeIn(window);
    setVisible(false);
    resources.core.instruccionesRompe();
    window.setVisible(true);
  }

  public void Rompecabezas5x5AP() {
    Rompecabezas window = new Rompecabezas(this, true, 5, 50, 6);
    window.setSize(490, 380);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Level Medium");
    resources.core.fadeIn(window);
    setVisible(false);
    resources.core.instruccionesRompe();
    window.setVisible(true);
  }

  public void Rompecabezas6x6AP() {
    Rompecabezas window = new Rompecabezas(this, true, 6, 45, 10);
    window.setSize(490, 380);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Level Hard");
    resources.core.fadeIn(window);
    setVisible(false);
    resources.core.instruccionesRompe();
    window.setVisible(true);
  }

  public void CardAP() {
    Card window = new Card(this, true);
    window.setSize(523, 269);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Developed by");
    resources.core.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void NotasAP() {
    Notas window = new Notas(this, true);
    window.setSize(400, 500);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Calcular Notas");
    resources.core.fadeIn(window);
    setVisible(false);
    resources.core.instruccionesNotas();
    window.setVisible(true);
  }

  public void ImgAP(String nombre, int ancho, int largo) {

    try {

      resources.core.fadeIn(this);
      setVisible(false);
      ((JPanel) getContentPane()).setOpaque(false);
      welcome.setIcon(new ImageIcon(resources.getImage(nombre)));
      welcome.setSize(ancho, largo);
      setSize(ancho, largo + 80);
      setLocationRelativeTo(null);
      isWork = false;

      for (JMenuItem image : images) {
        image.setForeground(resources.core.AZUL);
      }

    } catch (Exception ignored) {
    }
  }

  public void guessNumberTableAP() {

    guessNumberTable.cleanTable();

    try {
      resources.database.readTable(guessNumberTable.viewTable,
          Querys.getAllData(Format.tableName(GUESS_NUMBER)), true);
    } catch (Exception e1) {
      resources.core.exception(e1);
    }

    guessNumberTable.setSize(830, 400);
    guessNumberTable.setLocationRelativeTo(null);
    guessNumberTable.setMinimumSize(new Dimension(830, 400));
    guessNumberTable.setMaximumSize(new Dimension(1280, 720));
    guessNumberTable.setTitle(GUESS_NUMBER + " Information");
    resources.core.fadeIn(guessNumberTable);
    guessNumberTable.setVisible(true);
  }

  public void hangmanTableAP() {
    hangmanTable.cleanTable();

    try {
      resources.database
          .readTable(hangmanTable.viewTable, Querys.getAllData(Format.tableName(HANGMAN)), true);
    } catch (Exception e1) {
      resources.core.exception(e1);
    }

    hangmanTable.setSize(830, 400);
    hangmanTable.setLocationRelativeTo(null);
    hangmanTable.setMinimumSize(new Dimension(830, 400));
    hangmanTable.setMaximumSize(new Dimension(1280, 720));
    hangmanTable.setTitle(HANGMAN+" Information");
    resources.core.fadeIn(hangmanTable);
    hangmanTable.setVisible(true);
  }

  public boolean dicesTableAP() {
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
      dicesTable.setTitle(DICES+" Information");
      resources.core.fadeIn(dicesTable);
      dicesTable.setVisible(true);
    }

    return aux;
  }

  public void NotasTableAP() {
    notesTable.limpiarTabla();

    try {
      resources.database.readTable(notesTable.notasTab, "select * from notas", true);
    } catch (Exception e1) {
      resources.core.exception(e1);
    }

    notesTable.setSize(830, 400);
    notesTable.setLocationRelativeTo(null);
    notesTable.setMinimumSize(new Dimension(830, 400));
    notesTable.setMaximumSize(new Dimension(1280, 720));
    notesTable.setTitle("Notes Information");
    resources.core.fadeIn(notesTable);
    notesTable.setVisible(true);
  }

  public void RompecabezasTableAP() {
    puzzleTable.limpiarTabla();

    try {
      resources.database.readTable(puzzleTable.rompeTab, "select * from rompecabezas", true);
    } catch (Exception e1) {
      resources.core.exception(e1);
    }

    puzzleTable.setSize(830, 400);
    puzzleTable.setLocationRelativeTo(null);
    puzzleTable.setMinimumSize(new Dimension(830, 400));
    puzzleTable.setMaximumSize(new Dimension(1280, 720));
    puzzleTable.setTitle("Rompecabezas Information");
    setVisible(false);
    resources.core.fadeIn(puzzleTable);
    puzzleTable.setVisible(true);
  }

  public void paintBackground(ActionEvent e) {

    IntStream.range(0, images.length).filter(i -> e.getSource() == images[i]).forEach(i -> {
      if (images[i].getForeground() != resources.core.ROJO) {
        ImgAP(PATH_IMAGES[i], WIDTH_IMAGES[i], LONG_IMAGES[i]);
        images[i].setForeground(resources.core.ROJO);
        background = i + 1;
        setVisible(true);
      } else {
        resources.core.miraWe(false);
      }
    });
  }

  public void paintUI() {
    darkMode.setForeground(resources.core.AZUL);
    textureMode.setForeground(resources.core.ROJO);
    mintMode.setForeground(resources.core.AZUL);
    classicMode.setForeground(resources.core.AZUL);
    macMode.setForeground(resources.core.AZUL);
    grayMode.setForeground(resources.core.AZUL);
  }

  public void getUI(String selectedUI) {

    paintUI();

    switch (selectedUI) {

      case "Gray":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

          UIManager.put("ComboBox.foreground", new Color(0, 0, 0));
          UIManager.put("MenuItem.foreground", resources.core.AZUL);
          UIManager.put("Menu.foreground", resources.core.ROJO);
          UIManager.put("Button.foreground", new Color(0, 0, 0));

          UIManager.put("Table.focusCellHighlightBorder", resources.core.MEDIO);
          UIManager.put("TableHeader.foreground", resources.core.ROJO);
          UIManager.put("Table.foreground", resources.core.AZUL);
          UIManager.put("OptionPane.messageForeground", resources.core.AZUL);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(WIDTH_IMAGES[background - 1], LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.ImgAP(PATH_IMAGES[background - 1], WIDTH_IMAGES[background - 1],
              LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.images[background - 1].setForeground(resources.core.ROJO);
          window.grayMode.setForeground(resources.core.ROJO);
          resources.core.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + resources.core.styleJOption()
                  + "<strong><center>Changes Saved</center></strong><br>"
                  + "Modified UI, enjoy the metallic aspect!</html>",
              "New Look!", JOptionPane.PLAIN_MESSAGE);

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;

      case "Texture":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");

          UIManager.put("MenuItem.foreground", resources.core.AZUL);
          UIManager.put("Menu.foreground", resources.core.ROJO);

          UIManager.put("ComboBox.foreground", resources.core.AZUL);
          UIManager.put("Table.foreground", resources.core.AZUL);
          UIManager.put("OptionPane.messageForeground", resources.core.AZUL);
          UIManager.put("Button.foreground", Color.BLACK);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(WIDTH_IMAGES[background - 1], LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.ImgAP(PATH_IMAGES[background - 1], WIDTH_IMAGES[background - 1],
              LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.images[background - 1].setForeground(resources.core.ROJO);
          window.textureMode.setForeground(resources.core.ROJO);
          resources.core.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + resources.core.styleJOption()
                  + "<strong><center>Changes Saved</center></strong><br>"
                  + "Modified UI, enjoy the textured aspect!</html>",
              "New Look!", JOptionPane.PLAIN_MESSAGE);

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;

      case "Dark":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");

          UIManager.put("MenuItem.foreground", resources.core.AZUL);
          UIManager.put("Menu.foreground", resources.core.ROJO);

          UIManager.put("ComboBox.foreground", Color.WHITE);
          UIManager.put("Table.foreground", Color.WHITE);
          UIManager.put("OptionPane.messageForeground", Color.WHITE);
          UIManager.put("Button.foreground", Color.WHITE);
          UIManager.put("MenuItem.foreground", resources.core.AZUL);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(WIDTH_IMAGES[background - 1], LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.ImgAP(PATH_IMAGES[background - 1], WIDTH_IMAGES[background - 1],
              LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.images[background - 1].setForeground(resources.core.ROJO);
          window.darkMode.setForeground(resources.core.ROJO);
          resources.core.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + resources.core.styleJOption()
                  + "<strong><center>Changes Saved</center></strong><br>"
                  + "Modified UI, enjoy the dark aspect!</html>",
              "New Look!", JOptionPane.PLAIN_MESSAGE);

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;

      case "Mac":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

          UIManager.put("MenuItem.foreground", resources.core.AZUL);
          UIManager.put("Menu.foreground", resources.core.ROJO);

          UIManager.put("ComboBox.foreground", resources.core.AZUL);
          UIManager.put("Table.foreground", resources.core.AZUL);
          UIManager.put("OptionPane.messageForeground", resources.core.AZUL);
          UIManager.put("Button.foreground", Color.BLACK);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(WIDTH_IMAGES[background - 1], LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.ImgAP(PATH_IMAGES[background - 1], WIDTH_IMAGES[background - 1],
              LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.images[background - 1].setForeground(resources.core.ROJO);
          window.macMode.setForeground(resources.core.ROJO);
          resources.core.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + resources.core.styleJOption()
                  + "<strong><center>Changes Saved</center></strong><br>"
                  + "Modified UI, enjoy the Mac OS aspect!</html>",
              "New Look!", JOptionPane.PLAIN_MESSAGE);

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;

      case "Mint":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

          UIManager.put("ComboBox.foreground", Color.BLACK);

          UIManager.put("MenuItem.foreground", resources.core.AZUL);
          UIManager.put("Menu.foreground", resources.core.ROJO);

          UIManager.put("Button.foreground", Color.BLACK);

          UIManager.put("Table.focusCellHighlightBorder", resources.core.MEDIO);
          UIManager.put("TableHeader.foreground", resources.core.ROJO);
          UIManager.put("Table.foreground", resources.core.AZUL);
          UIManager.put("OptionPane.messageForeground", resources.core.AZUL);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(WIDTH_IMAGES[background - 1], LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.ImgAP(PATH_IMAGES[background - 1], WIDTH_IMAGES[background - 1],
              LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.images[background - 1].setForeground(resources.core.ROJO);
          window.mintMode.setForeground(resources.core.ROJO);
          resources.core.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + resources.core.styleJOption()
                  + "<strong><center>Changes Saved</center></strong><br>"
                  + "Modified UI, enjoy the mint aspect!</html>",
              "New Look!", JOptionPane.PLAIN_MESSAGE);

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

          UIManager.put("MenuItem.foreground", resources.core.AZUL);
          UIManager.put("Menu.foreground", resources.core.ROJO);

          UIManager.put("Table.focusCellHighlightBorder", resources.core.MEDIO);
          UIManager.put("TableHeader.foreground", resources.core.ROJO);
          UIManager.put("Table.foreground", resources.core.AZUL);
          UIManager.put("OptionPane.messageForeground", resources.core.AZUL);

          setVisible(false);
          Index window = new Index();

          welcome = new JLabel();
          window.setSize(WIDTH_IMAGES[background - 1], LONG_IMAGES[background - 1]);
          window.add(welcome, BorderLayout.CENTER);
          window.ImgAP(PATH_IMAGES[background - 1], WIDTH_IMAGES[background - 1],
              LONG_IMAGES[background - 1]);

          window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          window.setResizable(false);
          window.setLocationRelativeTo(null);
          window.setTitle(resources.core.getTitle());
          window.images[background - 1].setForeground(resources.core.ROJO);
          window.classicMode.setForeground(resources.core.ROJO);
          resources.core.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + resources.core.styleJOption()
                  + "<strong><center>Changes Saved</center></strong><br>"
                  + "Modified UI, enjoy the classic aspect!</html>",
              "New Look!", JOptionPane.PLAIN_MESSAGE);

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
            | IllegalAccessException eq) {
          Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, eq);
        }

        break;
    }
  }

  public void TimesheetAP() {
    Timesheet window = new Timesheet(this, true);
    window.setSize(460, 300);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setTitle("Timesheet Entry");
    resources.core.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    isWork = true;

    paintBackground(e);

    if (e.getSource() == yes_exit) {
      resources.core.fadeOut(this);

    } else if (e.getSource() == timesheet) {

      TimesheetAP();
      setVisible(true);

    } else if (e.getSource() == grayMode) {

      if (grayMode.getForeground() != resources.core.ROJO) {
        getUI("Gray");
      } else {
        resources.core.miraWe(true);
      }

    } else if (e.getSource() == darkMode) {

      if (darkMode.getForeground() != resources.core.ROJO) {
        getUI("Dark");
      } else {
        resources.core.miraWe(true);
      }

    } else if (e.getSource() == textureMode) {

      if (textureMode.getForeground() != resources.core.ROJO) {
        getUI("Texture");
      } else {
        resources.core.miraWe(true);
      }

    } else if (e.getSource() == macMode) {

      if (macMode.getForeground() != resources.core.ROJO) {
        getUI("Mac");
      } else {
        resources.core.miraWe(true);
      }

    } else if (e.getSource() == mintMode) {

      if (mintMode.getForeground() != resources.core.ROJO) {
        getUI("Mint");
      } else {
        resources.core.miraWe(true);
      }

    } else if (e.getSource() == classicMode) {

      if (classicMode.getForeground() != resources.core.ROJO) {
        getUI("Classic");
      } else {
        resources.core.miraWe(true);
      }

    } else if (e.getSource() == email) {
      SendEmailAP();
      setVisible(true);
    } else if (e.getSource() == comment) {
      SendCommentAP();
      setVisible(true);
    } else if (e.getSource() == texts) {
      TextosAP();
      setVisible(true);
    } else if (e.getSource() == guessNumber) {
      new GuessNumber(this, true, false).start(this);
      setVisible(true);
    } else if (e.getSource() == guessNumberHard) {
      new GuessNumber(this, true, true).start(this);
      setVisible(true);
    } else if (e.getSource() == triquiPvsP) {
      TriquiAP();
      setVisible(true);
    } else if (e.getSource() == triquiPvsCPU) {
      TriquiCPUAP();
      setVisible(true);
    } else if (e.getSource() == hangman) {
      new Hangman(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == numbers) {
      startNumbersAP();
      setVisible(true);
    } else if (e.getSource() == puzzle4x4) {
      Rompecabezas4x4AP();
      setVisible(true);
    } else if (e.getSource() == puzzle5x5) {
      Rompecabezas5x5AP();
      setVisible(true);
    } else if (e.getSource() == puzzle6x6) {
      Rompecabezas6x6AP();
      setVisible(true);
    } else if (e.getSource() == structures) {
      structuresAP();
      setVisible(true);
    } else if (e.getSource() == dices) {
      new Dices(this, true).start(this);
      setVisible(true);
    } else if (e.getSource() == notes) {

      NotasAP();
      setVisible(true);

    } else if (e.getSource() == buyAndSell) {

      CompraventaAP();
      setVisible(true);

    } else if (e.getSource() == read) {

      CardAP();
      setVisible(true);

    } else if (resources.core.comprobarConexion("?Con?ctate para ver m?s!", isWork)) {

      if (e.getSource() == roulette) {

        resources.core.instruccionesRuleta();

        try {
          Desktop.getDesktop()
              .browse(new URL("https://mypackapps.000webhostapp.com/ruleta.php").toURI());
        } catch (Exception ex) {
          resources.core.exception(ex);
        }

      } else if (e.getSource() == moreSystems) {

        try {
          Desktop.getDesktop().browse(new URL("https://mypackapps.000webhostapp.com").toURI());
        } catch (Exception ex) {
          ex.getMessage();
        }

      } else if (e.getSource() == moreBookverse) {

        try {
          Desktop.getDesktop().browse(new URL("http://bookverse.vzpla.net").toURI());
        } catch (Exception ex) {
          ex.getMessage();
        }

      } else if (e.getSource() == moreProfiles) {

        try {
          Desktop.getDesktop().browse(new URL("http://perfilesitm.mipropia.com").toURI());
        } catch (Exception ex) {
          ex.getMessage();
        }

      } else if (e.getSource() == tables) {

        if (dicesTableAP()) {
          setVisible(true);
        }

      } else if (e.getSource() == guessNumberTXT) {

        try {
          guessNumberTable.cleanTable();
          resources.core.txt(guessNumberTable.viewTable, Querys.getAllData(Format.tableName(GUESS_NUMBER)), ".txt");
        } catch (Exception ex) {
        }

      } else if (e.getSource() == hangmanTXT) {

        try {
          hangmanTable.cleanTable();
          resources.core
              .txt(hangmanTable.viewTable, Querys.getAllData(Format.tableName(HANGMAN)), ".txt");
        } catch (Exception ex) {
        }

      } else if (e.getSource() == purchasesTXT) {

        try {
          purchasesTable.limpiarTabla();
          resources.core.txt(purchasesTable.comprasTab,
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from compras",
              ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == salesTXT) {

        try {
          salesTable.limpiarTabla();
          resources.core.txt(salesTable.ventasTab,
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from ventas",
              ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == dicesTXT) {

        try {
          dicesTable.cleanTable();
          resources.core.txt(dicesTable.viewTable, "select * from dados", ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == notesTXT) {

        try {
          notesTable.limpiarTabla();
          resources.core.txt(notesTable.notasTab, "select * from notas", ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == puzzleTXT) {

        try {
          puzzleTable.limpiarTabla();
          resources.core.txt(puzzleTable.rompeTab, "select * from rompecabezas", ".txt");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == inventoryTXT) {

        try {
          inventoryTable.limpiarTabla();
          resources.core.txt(inventoryTable.inventarioTab, "select * from inventario", ".txt");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == cashRegisterTXT) {

        try {
          cashRegisterTable.limpiarTabla();
          resources.core.txt(cashRegisterTable.registradoraTab,
              "select Usuario, Productos_Vendidos,Total_Ventas,Productos_Comprados,Total_Compras from registros",
              ".txt");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == loansTXT) {

        try {
          loansTable.limpiarTabla();
          resources.core.txt(loansTable.prestamosTab,
              "select Usuario, Nombre, Documento, Referencia, Tel?fono, Plazo, Valor from pr?stamos",
              ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == guessNumberPDF) {

        try {
          guessNumberTable.cleanTable();
          resources.core.pdf(guessNumberTable.viewTable, "Adivinar N?mero", "select * from adivinar",
              ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == hangmanPDF) {

        try {
          hangmanTable.cleanTable();
          resources.core.pdf(hangmanTable.viewTable, "Ahorcadito", "select * from ahorcado", ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == purchasesPDF) {

        try {
          purchasesTable.limpiarTabla();
          resources.core.pdf(purchasesTable.comprasTab, "Compras",
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from compras",
              ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == salesPDF) {

        try {
          salesTable.limpiarTabla();
          resources.core.pdf(salesTable.ventasTab, "Ventas",
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from ventas",
              ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == dicesPDF) {

        try {
          dicesTable.cleanTable();
          resources.core.pdf(dicesTable.viewTable, "Juego de Dados", "select * from dados", ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == notesPDF) {

        try {
          notesTable.limpiarTabla();
          resources.core.pdf(notesTable.notasTab, "Notas", "select * from notas", ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == puzzlePDF) {

        try {
          puzzleTable.limpiarTabla();
          resources.core.pdf(puzzleTable.rompeTab, "Rompecabezas", "select * from rompecabezas", ".pdf");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == inventoryPDF) {

        try {
          inventoryTable.limpiarTabla();
          resources.core
              .pdf(inventoryTable.inventarioTab, "Inventario", "select * from inventario", ".pdf");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == cashRegisterPDF) {

        try {
          cashRegisterTable.limpiarTabla();
          resources.core.pdf(cashRegisterTable.registradoraTab, "Registradora",
              "select Usuario, Productos_Vendidos,Total_Ventas,Productos_Comprados,Total_Compras from registros",
              ".pdf");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == loansPDF) {

        try {
          loansTable.limpiarTabla();
          resources.core.pdf(loansTable.prestamosTab, "Pr?stamos",
              "select Usuario, Nombre, Documento, Referencia, Tel?fono, Plazo, Valor from pr?stamos",
              ".pdf");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == guessNumberEXCEL) {

        try {
          guessNumberTable.cleanTable();
          resources.core.excel(guessNumberTable.viewTable, "select * from adivinar", ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == hangmanEXCEL) {

        try {
          hangmanTable.cleanTable();
          resources.core.excel(hangmanTable.viewTable, "select * from ahorcado", ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == purchasesEXCEL) {

        try {
          purchasesTable.limpiarTabla();
          resources.core.excel(purchasesTable.comprasTab,
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from compras",
              ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == salesEXCEL) {

        try {
          salesTable.limpiarTabla();
          resources.core.excel(salesTable.ventasTab,
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from ventas",
              ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == dicesEXCEL) {

        try {
          dicesTable.cleanTable();
          resources.core.excel(dicesTable.viewTable, "select * from dados", ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == notesEXCEL) {

        try {
          notesTable.limpiarTabla();
          resources.core.excel(notesTable.notasTab, "select * from notas", ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == puzzleEXCEL) {

        try {
          puzzleTable.limpiarTabla();
          resources.core.excel(puzzleTable.rompeTab, "select * from rompecabezas", ".xls");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == inventoryEXCEL) {

        try {
          inventoryTable.limpiarTabla();
          resources.core.excel(inventoryTable.inventarioTab, "select * from inventario", ".xls");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == cashRegisterEXCEL) {

        try {
          cashRegisterTable.limpiarTabla();
          resources.core.excel(cashRegisterTable.registradoraTab,
              "select Usuario, Productos_Vendidos,Total_Ventas,Productos_Comprados,Total_Compras from registros",
              ".xls");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == loansEXCEL) {

        try {
          loansTable.limpiarTabla();
          resources.core.excel(loansTable.prestamosTab,
              "select Usuario, Nombre, Documento, Referencia, Teléfono, Plazo, Valor from préstamos",
              ".xls");
        } catch (Exception ex) {

        }
      }
    }
  }
}