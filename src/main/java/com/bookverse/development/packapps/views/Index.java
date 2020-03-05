package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.ArrayData.LONG_IMAGES;
import static com.bookverse.development.packapps.utils.ArrayData.PATH_IMAGES;
import static com.bookverse.development.packapps.utils.ArrayData.WIDTH_IMAGES;

import com.bookverse.development.packapps.core.Resources;
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

  public static Resources img = new Resources();
  private static int background = 9;

  private static JLabel welcome;
  public AhorcadoTabla hangmanTable = new AhorcadoTabla(this, true);
  public AdivinarTabla guessTable = new AdivinarTabla(this, true);
  public RompecabezasTabla puzzleTable = new RompecabezasTabla(this, true);
  public DadosTabla dicesTable = new DadosTabla(this, true);
  public NotasTabla notesTable = new NotasTabla(this, true);
  public InventarioTabla inventoryTable = new InventarioTabla(this, true);
  public RegistradoraTabla cashRegisterTable = new RegistradoraTabla(this, true);
  public PrestamosTabla loansTable = new PrestamosTabla(this, true);
  public ComprasTabla purchasesTable = new ComprasTabla(this, true);
  public VentasTabla salesTable = new VentasTabla(this, true);
  private JMenuItem[] images = new JMenuItem[14];
  private boolean isWork = true;
  private JMenu changeBackground;
  private JMenuItem moreProfiles;
  private JMenuItem moreSystems;
  private JMenuItem moreBookverse;
  private JMenuItem darkMode;
  private JMenuItem textureMode;
  private JMenuItem mintMode;
  private JMenuItem classicMode;
  private JMenuItem macMode;
  private JMenuItem grayMode;
  private JMenuItem texts;
  private JMenuItem guess;
  private JMenuItem guessHard;
  private JMenuItem hangman;
  private JMenuItem structures;
  private JMenuItem dices;
  private JMenuItem buyAndSell;
  private JMenuItem numbers;
  private JMenuItem puzzle4x4;
  private JMenuItem puzzle5x5;
  private JMenuItem puzzle6x6;
  private JMenuItem roulette;
  private JMenuItem triquiPvsP;
  private JMenuItem triquiPvsCPU;
  private JMenuItem tables;
  private JMenuItem notes;
  private JMenuItem yes_exit;
  private JMenuItem email;
  private JMenuItem comment;
  private JMenuItem guessTXT;
  private JMenuItem hangmanTXT;
  private JMenuItem dicesTXT;
  private JMenuItem notesTXT;
  private JMenuItem inventoryTXT;
  private JMenuItem purchasesTXT;
  private JMenuItem salesTXT;
  private JMenuItem cashRegisterTXT;
  private JMenuItem loansTXT;
  private JMenuItem puzzleTXT;
  private JMenuItem guessEXCEL;
  private JMenuItem hangmanEXCEL;
  private JMenuItem dicesEXCEL;
  private JMenuItem notesEXCEL;
  private JMenuItem inventoryEXCEL;
  private JMenuItem purchasesEXCEL;
  private JMenuItem salesEXCEL;
  private JMenuItem cashRegisterEXCEL;
  private JMenuItem loansEXCEL;
  private JMenuItem puzzleEXCEL;
  private JMenuItem guessPDF;
  private JMenuItem hangmanPDF;
  private JMenuItem dicesPDF;
  private JMenuItem notesPDF, inventoryPDF;
  private JMenuItem purchasesPDF;
  private JMenuItem salesPDF;
  private JMenuItem cashRegisterPDF;
  private JMenuItem loansPDF;
  private JMenuItem puzzlePDF;
  private JMenuItem read;
  private JMenuItem timesheet;

  public Index() {
    createComponents();
  }

  public static void main(String[] args) {

    try {

      UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

      UIManager.put("PasswordField.border", img.cr.MEDIO);
      UIManager.put("PasswordField.font", img.cr.MEDIUM);

      UIManager.put("TextField.border", img.cr.MEDIO);
      UIManager.put("TextField.font", img.cr.MEDIUM);

      UIManager.put("FileChooser.saveButtonText", "Save");
      UIManager.put("FileChooser.cancelButtonText", "Cancel");

      UIManager.put("RadioButton.font", img.cr.MEDIUM);

      UIManager.put("TextArea.font", img.cr.MEDIUM);

      UIManager.put("ComboBox.font", img.cr.MEDIUM);
      UIManager.put("ComboBox.foreground", img.cr.AZUL);

      UIManager.put("ScrollPane.border", img.cr.MEDIO);

      UIManager.put("MenuItem.foreground", img.cr.AZUL);
      UIManager.put("MenuItem.font", img.cr.MEDIUM);

      UIManager.put("Menu.foreground", img.cr.ROJO);
      UIManager.put("Menu.font", img.cr.MEDIUM);

      UIManager.put("Button.font", img.cr.MEDIUM);

      UIManager.put("Table.focusCellHighlightBorder", img.cr.MEDIO);
      UIManager.put("TableHeader.foreground", img.cr.ROJO);
      UIManager.put("TableHeader.font", img.cr.MEDIUM);
      UIManager.put("Table.font", img.cr.MEDIUM);
      UIManager.put("Table.foreground", img.cr.AZUL);

      UIManager.put("OptionPane.okButtonText", "Done");
      UIManager.put("OptionPane.cancelButtonText", "No, thanks.");
      UIManager.put("OptionPane.yesButtonText", "Yes, it is okay.");
      UIManager.put("OptionPane.noButtonText", "No, thanks.");
      UIManager.put("OptionPane.messageFont", img.cr.MEDIUM);
      UIManager.put("OptionPane.buttonFont", img.cr.MEDIUM);
      UIManager.put("OptionPane.messageForeground", img.cr.AZUL);

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
    window.setTitle(img.cr.getTitle());
    window.images[background - 1].setForeground(img.cr.ROJO);
    window.macMode.setForeground(img.cr.ROJO);
    img.cr.fadeIn(window);
    window.setVisible(true);
  }

  public void createComponents() {

    setIconImage(new ImageIcon(img.getImage("more.png")).getImage());

    JMenuBar menuBar = new JMenuBar();

    JMenu about = img.getMenu("About", "about");
    read = img.getMenuItem("Developer", "developer", this);

    JMenu more = img.getMenu("See More", "more");
    moreSystems = img.getMenuItem("My Systems", "mysystems", this);
    moreProfiles = img.getMenuItem("Profiles", "profiles", this);
    moreBookverse = img.getMenuItem("Bookverse", "books", this);

    more.add(moreBookverse);
    more.addSeparator();
    more.add(moreProfiles);
    more.addSeparator();
    more.add(moreSystems);

    about.add(read);
    about.addSeparator();
    about.add(more);

    JMenu exit = img.getMenu("Exit", "exit");
    yes_exit = img.getMenuItem("Are you sure?", "salir", this);

    JMenu send = img.getMenu("Send Feedback", "send");
    email = img.getMenuItem("Email (priority)", "email", this);
    comment = img.getMenuItem("Comment (simple)", "coment", this);

    send.add(comment);
    send.addSeparator();
    send.add(email);

    exit.add(yes_exit);
    exit.addSeparator();
    exit.add(send);

    JMenu apps = img.getMenu("Games", "games");
    hangman = img.getMenuItem("Hangman", "ahorcado", this);
    dices = img.getMenuItem("Dices", "dado", this);
    roulette = img.getMenuItem("Roulette", "ruleta", this);

    JMenu guessNumber = img.getMenu("Guess Number", "adivinar");
    guess = img.getMenuItem("Easy", "easy", this);
    guessHard = img.getMenuItem("Hard", "hard", this);

    guessNumber.add(guess);
    guessNumber.addSeparator();
    guessNumber.add(guessHard);

    JMenu puzzle = img.getMenu("Puzzle", "rompecabezas");
    puzzle4x4 = img.getMenuItem("Easy", "easy", this);
    puzzle5x5 = img.getMenuItem("Medium", "medio", this);
    puzzle6x6 = img.getMenuItem("Hard", "hard", this);

    puzzle.add(puzzle4x4);
    puzzle.addSeparator();
    puzzle.add(puzzle5x5);
    puzzle.addSeparator();
    puzzle.add(puzzle6x6);

    JMenu triqui = img.getMenu("Triqui", "triqui");
    triquiPvsP = img.getMenuItem("Player vs Player", "jvsj", this);
    triquiPvsCPU = img.getMenuItem("Player vs CPU (beta)", "jvscpu", this);

    triqui.add(triquiPvsP);
    triqui.addSeparator();
    triqui.add(triquiPvsCPU);

    apps.add(guessNumber);
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

    JMenu scores = img.getMenu("Data", "data");
    tables = img.getMenuItem("Database", "tabla", this);

    scores.add(tables);

    JMenu tools = img.getMenu("Tools", "tools");
    buyAndSell = img.getMenuItem("Buy and Sell", "compraventa", this);
    structures = img.getMenuItem("Structures", "estructuras", this);
    numbers = img.getMenuItem("Numbers", "numeritos", this);
    notes = img.getMenuItem("Notes", "notas", this);
    texts = img.getMenuItem("Texts", "textos", this);

    changeBackground = img.getMenu("Background", "background");

    IntStream.range(0, images.length).forEach(i -> {
      images[i] = new JMenuItem("Image " + (i + 1));
      images[i].setForeground(img.cr.AZUL);
      images[i].setIcon(new ImageIcon(img.getImage("backs.png")));
      images[i].addActionListener(this);
      changeBackground.add(images[i]);
      changeBackground.addSeparator();
    });

    JMenu export = img.getMenu("Export Data", "export");

    JMenu exportTXT = img.getMenu("Text file", "txt");
    guessTXT = img.getMenuItem("Guess Number", "adivinar", this);
    hangmanTXT = img.getMenuItem("Hangman", "ahorcado", this);
    purchasesTXT = img.getMenuItem("Purchases", "comprar", this);
    dicesTXT = img.getMenuItem("Dices", "dado", this);
    notesTXT = img.getMenuItem("Notes", "notas", this);
    puzzleTXT = img.getMenuItem("Puzzle", "rompecabezas", this);
    inventoryTXT = img.getMenuItem("Inventory", "inventario", this);
    cashRegisterTXT = img.getMenuItem("Cash Register", "registradora", this);
    loansTXT = img.getMenuItem("Loans", "prestamos", this);
    salesTXT = img.getMenuItem("Sales", "vender", this);

    exportTXT.add(guessTXT);
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

    JMenu exportEXCEL = img.getMenu("Hoja de Cálculo", "excel");
    guessEXCEL = img.getMenuItem("Guess Number", "adivinar", this);
    hangmanEXCEL = img.getMenuItem("Hangman", "ahorcado", this);
    purchasesEXCEL = img.getMenuItem("Purchases", "comprar", this);
    dicesEXCEL = img.getMenuItem("Dices", "dado", this);
    notesEXCEL = img.getMenuItem("Notes", "notas", this);
    puzzleEXCEL = img.getMenuItem("Puzzle", "rompecabezas", this);
    inventoryEXCEL = img.getMenuItem("Inventory", "inventario", this);
    cashRegisterEXCEL = img.getMenuItem("Cash Register", "registradora", this);
    loansEXCEL = img.getMenuItem("Loans", "prestamos", this);
    salesEXCEL = img.getMenuItem("Sales", "vender", this);

    exportEXCEL.add(guessEXCEL);
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

    JMenu exportPDF = img.getMenu("Document PDF", "pdf");
    guessPDF = img.getMenuItem("Guess Number", "adivinar", this);
    hangmanPDF = img.getMenuItem("Hangman", "ahorcado", this);
    purchasesPDF = img.getMenuItem("Purchases", "comprar", this);
    dicesPDF = img.getMenuItem("Dices", "dado", this);
    notesPDF = img.getMenuItem("Notes", "notas", this);
    puzzlePDF = img.getMenuItem("Puzzle", "rompecabezas", this);
    inventoryPDF = img.getMenuItem("Inventory", "inventario", this);
    cashRegisterPDF = img.getMenuItem("Cash Register", "registradora", this);
    loansPDF = img.getMenuItem("Loans", "prestamos", this);
    salesPDF = img.getMenuItem("Sales", "vender", this);

    exportPDF.add(guessPDF);
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

    JMenu tasks = img.getMenu("Tasks", "task");
    timesheet = img.getMenuItem("Timesheet Entry", "timesheet", this);
    tasks.add(timesheet);

    JMenu mode = img.getMenu("UI", "mode");
    darkMode = img.getMenuItem("Dark", "dark", this);
    textureMode = img.getMenuItem("Texture", "texture", this);
    macMode = img.getMenuItem("Mac OS", "mac", this);
    grayMode = img.getMenuItem("Metallic", "gray", this);
    mintMode = img.getMenuItem("Mint", "mint", this);
    classicMode = img.getMenuItem("Classic", "classic", this);

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
    img.cr.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void structuresAP() {
    Estructuras window = new Estructuras(this, true);
    window.setSize(717, 380);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Estructuras de Datos");
    img.cr.fadeIn(window);
    setVisible(false);
    img.cr.instruccionesEstructuras();
    window.setVisible(true);
  }

  public void AhorcaditoAP() {
    Ahorcado window = new Ahorcado(this, true);
    window.setSize(750, 500);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Ahorcadito");
    img.cr.fadeIn(window);
    setVisible(false);
    img.cr.instruccionesAhorcado();
    window.setVisible(true);
  }

  public void TriquiAP() {
    Triqui window = new Triqui(this, true, false);
    window.setSize(450, 400);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Player vs Player");
    img.cr.fadeIn(window);
    setVisible(false);
    img.cr.instruccionesTriqui();
    window.setVisible(true);
  }

  public void TriquiCPUAP() {
    Triqui window = new Triqui(this, true, true);
    window.setSize(450, 400);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Player vs CPU (beta)");
    img.cr.fadeIn(window);
    setVisible(false);
    img.cr.instruccionesTriqui();
    window.setVisible(true);
  }

  public void CompraventaAP() {
    Login window = new Login(this, true);
    window.setSize(375, 400);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Compraventa");
    img.cr.fadeIn(window);
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
    img.cr.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void SendCommentAP() {
    Comment window = new Comment(this, true);
    window.setSize(485, 480);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Send Commentary");
    img.cr.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void SendEmailAP() {
    Email window = new Email(this, true);
    window.setSize(485, 480);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Send Email");
    img.cr.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void DadosAP() {
    Dados window = new Dados(this, true);
    window.setSize(450, 400);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Juego de Dados ?L?nzalos!");
    img.cr.fadeIn(window);
    setVisible(false);
    img.cr.instruccionesDados();
    window.setVisible(true);
  }

  public void Rompecabezas4x4AP() {
    Rompecabezas window = new Rompecabezas(this, true, 4, 55, 3);
    window.setSize(490, 380);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Level Easy");
    img.cr.fadeIn(window);
    setVisible(false);
    img.cr.instruccionesRompe();
    window.setVisible(true);
  }

  public void Rompecabezas5x5AP() {
    Rompecabezas window = new Rompecabezas(this, true, 5, 50, 6);
    window.setSize(490, 380);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Level Medium");
    img.cr.fadeIn(window);
    setVisible(false);
    img.cr.instruccionesRompe();
    window.setVisible(true);
  }

  public void Rompecabezas6x6AP() {
    Rompecabezas window = new Rompecabezas(this, true, 6, 45, 10);
    window.setSize(490, 380);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Level Hard");
    img.cr.fadeIn(window);
    setVisible(false);
    img.cr.instruccionesRompe();
    window.setVisible(true);
  }

  public void CardAP() {
    Card window = new Card(this, true);
    window.setSize(523, 269);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Developed by");
    img.cr.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  public void NotasAP() {
    Notas window = new Notas(this, true);
    window.setSize(400, 500);
    window.setResizable(false);
    window.setLocationRelativeTo(this);
    window.setTitle("Calcular Notas");
    img.cr.fadeIn(window);
    setVisible(false);
    img.cr.instruccionesNotas();
    window.setVisible(true);
  }

  public void ImgAP(String nombre, int ancho, int largo) {

    try {

      img.cr.fadeIn(this);
      setVisible(false);
      ((JPanel) getContentPane()).setOpaque(false);
      welcome.setIcon(new ImageIcon(img.getImage(nombre)));
      welcome.setSize(ancho, largo);
      setSize(ancho, largo + 80);
      setLocationRelativeTo(null);
      isWork = false;

      for (JMenuItem image : images) {
        image.setForeground(img.cr.AZUL);
      }

    } catch (Exception ignored) {
    }
  }

  public void AdivinarTableAP() {

    guessTable.limpiarTabla();

    try {
      img.db.importarTabla(guessTable.adivinarTab, "select * from adivinar", true);
    } catch (Exception e1) {
      img.cr.exception(e1);
    }

    guessTable.setSize(830, 400);
    guessTable.setLocationRelativeTo(null);
    guessTable.setMinimumSize(new Dimension(830, 400));
    guessTable.setMaximumSize(new Dimension(1280, 720));
    guessTable.setTitle("Adivinar Information");
    img.cr.fadeIn(guessTable);
    guessTable.setVisible(true);
  }

  public void ahorcadoTableAP() {
    hangmanTable.limpiarTabla();

    try {
      img.db.importarTabla(hangmanTable.ahorcadoTab, "select * from ahorcado", true);
    } catch (Exception e1) {
      img.cr.exception(e1);
    }

    hangmanTable.setSize(830, 400);
    hangmanTable.setLocationRelativeTo(null);
    hangmanTable.setMinimumSize(new Dimension(830, 400));
    hangmanTable.setMaximumSize(new Dimension(1280, 720));
    hangmanTable.setTitle("Ahorcadito Information");
    img.cr.fadeIn(hangmanTable);
    hangmanTable.setVisible(true);
  }

  public boolean DadosTableAP() {
    boolean aux = false;

    dicesTable.limpiarTabla();

    try {
      aux = img.db.importarTabla(dicesTable.dadosTab, "select * from dados", true);
    } catch (Exception e1) {
      img.cr.exception(e1);
    }

    if (aux) {
      setVisible(false);
      dicesTable.setSize(830, 400);
      dicesTable.setLocationRelativeTo(null);
      dicesTable.setMinimumSize(new Dimension(830, 400));
      dicesTable.setMaximumSize(new Dimension(1280, 720));
      dicesTable.setTitle("Dados Information");
      img.cr.fadeIn(dicesTable);
      dicesTable.setVisible(true);
    }

    return aux;
  }

  public void NotasTableAP() {
    notesTable.limpiarTabla();

    try {
      img.db.importarTabla(notesTable.notasTab, "select * from notas", true);
    } catch (Exception e1) {
      img.cr.exception(e1);
    }

    notesTable.setSize(830, 400);
    notesTable.setLocationRelativeTo(null);
    notesTable.setMinimumSize(new Dimension(830, 400));
    notesTable.setMaximumSize(new Dimension(1280, 720));
    notesTable.setTitle("Notes Information");
    img.cr.fadeIn(notesTable);
    notesTable.setVisible(true);
  }

  public void RompecabezasTableAP() {
    puzzleTable.limpiarTabla();

    try {
      img.db.importarTabla(puzzleTable.rompeTab, "select * from rompecabezas", true);
    } catch (Exception e1) {
      img.cr.exception(e1);
    }

    puzzleTable.setSize(830, 400);
    puzzleTable.setLocationRelativeTo(null);
    puzzleTable.setMinimumSize(new Dimension(830, 400));
    puzzleTable.setMaximumSize(new Dimension(1280, 720));
    puzzleTable.setTitle("Rompecabezas Information");
    setVisible(false);
    img.cr.fadeIn(puzzleTable);
    puzzleTable.setVisible(true);
  }

  public void paintBackground(ActionEvent e) {

    IntStream.range(0, images.length).filter(i -> e.getSource() == images[i]).forEach(i -> {
      if (images[i].getForeground() != img.cr.ROJO) {
        ImgAP(PATH_IMAGES[i], WIDTH_IMAGES[i], LONG_IMAGES[i]);
        images[i].setForeground(img.cr.ROJO);
        background = i + 1;
        setVisible(true);
      } else {
        img.cr.miraWe(false);
      }
    });
  }

  public void paintUI() {
    darkMode.setForeground(img.cr.AZUL);
    textureMode.setForeground(img.cr.ROJO);
    mintMode.setForeground(img.cr.AZUL);
    classicMode.setForeground(img.cr.AZUL);
    macMode.setForeground(img.cr.AZUL);
    grayMode.setForeground(img.cr.AZUL);
  }

  public void getUI(String selectedUI) {

    paintUI();

    switch (selectedUI) {

      case "Gray":

        try {
          UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

          UIManager.put("ComboBox.foreground", new Color(0, 0, 0));
          UIManager.put("MenuItem.foreground", img.cr.AZUL);
          UIManager.put("Menu.foreground", img.cr.ROJO);
          UIManager.put("Button.foreground", new Color(0, 0, 0));

          UIManager.put("Table.focusCellHighlightBorder", img.cr.MEDIO);
          UIManager.put("TableHeader.foreground", img.cr.ROJO);
          UIManager.put("Table.foreground", img.cr.AZUL);
          UIManager.put("OptionPane.messageForeground", img.cr.AZUL);

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
          window.setTitle(img.cr.getTitle());
          window.images[background - 1].setForeground(img.cr.ROJO);
          window.grayMode.setForeground(img.cr.ROJO);
          img.cr.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + img.cr.styleJOption()
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

          UIManager.put("MenuItem.foreground", img.cr.AZUL);
          UIManager.put("Menu.foreground", img.cr.ROJO);

          UIManager.put("ComboBox.foreground", img.cr.AZUL);
          UIManager.put("Table.foreground", img.cr.AZUL);
          UIManager.put("OptionPane.messageForeground", img.cr.AZUL);
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
          window.setTitle(img.cr.getTitle());
          window.images[background - 1].setForeground(img.cr.ROJO);
          window.textureMode.setForeground(img.cr.ROJO);
          img.cr.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + img.cr.styleJOption()
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

          UIManager.put("MenuItem.foreground", img.cr.AZUL);
          UIManager.put("Menu.foreground", img.cr.ROJO);

          UIManager.put("ComboBox.foreground", Color.WHITE);
          UIManager.put("Table.foreground", Color.WHITE);
          UIManager.put("OptionPane.messageForeground", Color.WHITE);
          UIManager.put("Button.foreground", Color.WHITE);
          UIManager.put("MenuItem.foreground", img.cr.AZUL);

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
          window.setTitle(img.cr.getTitle());
          window.images[background - 1].setForeground(img.cr.ROJO);
          window.darkMode.setForeground(img.cr.ROJO);
          img.cr.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + img.cr.styleJOption()
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

          UIManager.put("MenuItem.foreground", img.cr.AZUL);
          UIManager.put("Menu.foreground", img.cr.ROJO);

          UIManager.put("ComboBox.foreground", img.cr.AZUL);
          UIManager.put("Table.foreground", img.cr.AZUL);
          UIManager.put("OptionPane.messageForeground", img.cr.AZUL);
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
          window.setTitle(img.cr.getTitle());
          window.images[background - 1].setForeground(img.cr.ROJO);
          window.macMode.setForeground(img.cr.ROJO);
          img.cr.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + img.cr.styleJOption()
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

          UIManager.put("MenuItem.foreground", img.cr.AZUL);
          UIManager.put("Menu.foreground", img.cr.ROJO);

          UIManager.put("Button.foreground", Color.BLACK);

          UIManager.put("Table.focusCellHighlightBorder", img.cr.MEDIO);
          UIManager.put("TableHeader.foreground", img.cr.ROJO);
          UIManager.put("Table.foreground", img.cr.AZUL);
          UIManager.put("OptionPane.messageForeground", img.cr.AZUL);

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
          window.setTitle(img.cr.getTitle());
          window.images[background - 1].setForeground(img.cr.ROJO);
          window.mintMode.setForeground(img.cr.ROJO);
          img.cr.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + img.cr.styleJOption()
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

          UIManager.put("MenuItem.foreground", img.cr.AZUL);
          UIManager.put("Menu.foreground", img.cr.ROJO);

          UIManager.put("Table.focusCellHighlightBorder", img.cr.MEDIO);
          UIManager.put("TableHeader.foreground", img.cr.ROJO);
          UIManager.put("Table.foreground", img.cr.AZUL);
          UIManager.put("OptionPane.messageForeground", img.cr.AZUL);

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
          window.setTitle(img.cr.getTitle());
          window.images[background - 1].setForeground(img.cr.ROJO);
          window.classicMode.setForeground(img.cr.ROJO);
          img.cr.fadeIn(window);
          window.setVisible(true);

          JOptionPane.showMessageDialog(null,
              "<html>" + img.cr.styleJOption()
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
    img.cr.fadeIn(window);
    setVisible(false);
    window.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    isWork = true;

    paintBackground(e);

    if (e.getSource() == yes_exit) {
      img.cr.fadeOut(this);

    } else if (e.getSource() == timesheet) {

      TimesheetAP();
      setVisible(true);

    } else if (e.getSource() == grayMode) {

      if (grayMode.getForeground() != img.cr.ROJO) {
        getUI("Gray");
      } else {
        img.cr.miraWe(true);
      }

    } else if (e.getSource() == darkMode) {

      if (darkMode.getForeground() != img.cr.ROJO) {
        getUI("Dark");
      } else {
        img.cr.miraWe(true);
      }

    } else if (e.getSource() == textureMode) {

      if (textureMode.getForeground() != img.cr.ROJO) {
        getUI("Texture");
      } else {
        img.cr.miraWe(true);
      }

    } else if (e.getSource() == macMode) {

      if (macMode.getForeground() != img.cr.ROJO) {
        getUI("Mac");
      } else {
        img.cr.miraWe(true);
      }

    } else if (e.getSource() == mintMode) {

      if (mintMode.getForeground() != img.cr.ROJO) {
        getUI("Mint");
      } else {
        img.cr.miraWe(true);
      }

    } else if (e.getSource() == classicMode) {

      if (classicMode.getForeground() != img.cr.ROJO) {
        getUI("Classic");
      } else {
        img.cr.miraWe(true);
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
    } else if (e.getSource() == guess) {
      new GuessNumber(this, true, false).start(this);
      setVisible(true);
    } else if (e.getSource() == guessHard) {
      new GuessNumber(this, true, true).start(this);
      setVisible(true);
    } else if (e.getSource() == triquiPvsP) {
      TriquiAP();
      setVisible(true);
    } else if (e.getSource() == triquiPvsCPU) {
      TriquiCPUAP();
      setVisible(true);
    } else if (e.getSource() == hangman) {
      AhorcaditoAP();
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
      DadosAP();
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

    } else if (img.cr.comprobarConexion("?Con?ctate para ver m?s!", isWork)) {

      if (e.getSource() == roulette) {

        img.cr.instruccionesRuleta();

        try {
          Desktop.getDesktop()
              .browse(new URL("https://mypackapps.000webhostapp.com/ruleta.php").toURI());
        } catch (Exception ex) {
          img.cr.exception(ex);
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

        if (DadosTableAP()) {
          setVisible(true);
        }

      } else if (e.getSource() == guessTXT) {

        try {
          guessTable.limpiarTabla();
          img.cr.txt(guessTable.adivinarTab, "select * from adivinar", ".txt");
        } catch (Exception ex) {
        }

      } else if (e.getSource() == hangmanTXT) {

        try {
          hangmanTable.limpiarTabla();
          img.cr.txt(hangmanTable.ahorcadoTab, "select * from ahorcado", ".txt");
        } catch (Exception ex) {
        }

      } else if (e.getSource() == purchasesTXT) {

        try {
          purchasesTable.limpiarTabla();
          img.cr.txt(purchasesTable.comprasTab,
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from compras",
              ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == salesTXT) {

        try {
          salesTable.limpiarTabla();
          img.cr.txt(salesTable.ventasTab,
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from ventas",
              ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == dicesTXT) {

        try {
          dicesTable.limpiarTabla();
          img.cr.txt(dicesTable.dadosTab, "select * from dados", ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == notesTXT) {

        try {
          notesTable.limpiarTabla();
          img.cr.txt(notesTable.notasTab, "select * from notas", ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == puzzleTXT) {

        try {
          puzzleTable.limpiarTabla();
          img.cr.txt(puzzleTable.rompeTab, "select * from rompecabezas", ".txt");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == inventoryTXT) {

        try {
          inventoryTable.limpiarTabla();
          img.cr.txt(inventoryTable.inventarioTab, "select * from inventario", ".txt");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == cashRegisterTXT) {

        try {
          cashRegisterTable.limpiarTabla();
          img.cr.txt(cashRegisterTable.registradoraTab,
              "select Usuario, Productos_Vendidos,Total_Ventas,Productos_Comprados,Total_Compras from registros",
              ".txt");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == loansTXT) {

        try {
          loansTable.limpiarTabla();
          img.cr.txt(loansTable.prestamosTab,
              "select Usuario, Nombre, Documento, Referencia, Tel?fono, Plazo, Valor from pr?stamos",
              ".txt");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == guessPDF) {

        try {
          guessTable.limpiarTabla();
          img.cr.pdf(guessTable.adivinarTab, "Adivinar N?mero", "select * from adivinar", ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == hangmanPDF) {

        try {
          hangmanTable.limpiarTabla();
          img.cr.pdf(hangmanTable.ahorcadoTab, "Ahorcadito", "select * from ahorcado", ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == purchasesPDF) {

        try {
          purchasesTable.limpiarTabla();
          img.cr.pdf(purchasesTable.comprasTab, "Compras",
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from compras",
              ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == salesPDF) {

        try {
          salesTable.limpiarTabla();
          img.cr.pdf(salesTable.ventasTab, "Ventas",
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from ventas",
              ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == dicesPDF) {

        try {
          dicesTable.limpiarTabla();
          img.cr.pdf(dicesTable.dadosTab, "Juego de Dados", "select * from dados", ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == notesPDF) {

        try {
          notesTable.limpiarTabla();
          img.cr.pdf(notesTable.notasTab, "Notas", "select * from notas", ".pdf");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == puzzlePDF) {

        try {
          puzzleTable.limpiarTabla();
          img.cr.pdf(puzzleTable.rompeTab, "Rompecabezas", "select * from rompecabezas", ".pdf");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == inventoryPDF) {

        try {
          inventoryTable.limpiarTabla();
          img.cr
              .pdf(inventoryTable.inventarioTab, "Inventario", "select * from inventario", ".pdf");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == cashRegisterPDF) {

        try {
          cashRegisterTable.limpiarTabla();
          img.cr.pdf(cashRegisterTable.registradoraTab, "Registradora",
              "select Usuario, Productos_Vendidos,Total_Ventas,Productos_Comprados,Total_Compras from registros",
              ".pdf");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == loansPDF) {

        try {
          loansTable.limpiarTabla();
          img.cr.pdf(loansTable.prestamosTab, "Pr?stamos",
              "select Usuario, Nombre, Documento, Referencia, Tel?fono, Plazo, Valor from pr?stamos",
              ".pdf");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == guessEXCEL) {

        try {
          guessTable.limpiarTabla();
          img.cr.excel(guessTable.adivinarTab, "select * from adivinar", ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == hangmanEXCEL) {

        try {
          hangmanTable.limpiarTabla();
          img.cr.excel(hangmanTable.ahorcadoTab, "select * from ahorcado", ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == purchasesEXCEL) {

        try {
          purchasesTable.limpiarTabla();
          img.cr.excel(purchasesTable.comprasTab,
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from compras",
              ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == salesEXCEL) {

        try {
          salesTable.limpiarTabla();
          img.cr.excel(salesTable.ventasTab,
              "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from ventas",
              ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == dicesEXCEL) {

        try {
          dicesTable.limpiarTabla();
          img.cr.excel(dicesTable.dadosTab, "select * from dados", ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == notesEXCEL) {

        try {
          notesTable.limpiarTabla();
          img.cr.excel(notesTable.notasTab, "select * from notas", ".xls");
        } catch (Exception ex) {

        }

      } else if (e.getSource() == puzzleEXCEL) {

        try {
          puzzleTable.limpiarTabla();
          img.cr.excel(puzzleTable.rompeTab, "select * from rompecabezas", ".xls");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == inventoryEXCEL) {

        try {
          inventoryTable.limpiarTabla();
          img.cr.excel(inventoryTable.inventarioTab, "select * from inventario", ".xls");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == cashRegisterEXCEL) {

        try {
          cashRegisterTable.limpiarTabla();
          img.cr.excel(cashRegisterTable.registradoraTab,
              "select Usuario, Productos_Vendidos,Total_Ventas,Productos_Comprados,Total_Compras from registros",
              ".xls");
        } catch (Exception ex) {

        }
      } else if (e.getSource() == loansEXCEL) {

        try {
          loansTable.limpiarTabla();
          img.cr.excel(loansTable.prestamosTab,
              "select Usuario, Nombre, Documento, Referencia, Teléfono, Plazo, Valor from préstamos",
              ".xls");
        } catch (Exception ex) {

        }
      }
    }
  }
}