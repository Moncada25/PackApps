package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.HAND;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.LOADER;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.POINT;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.RESIZE;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.apps.utils.ui.Resources.getBorder;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.DICES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.HANGMAN;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.NOTES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.PUZZLE;

import com.bookverse.development.packapps.apps.utils.other.GeneralUtilities;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.repositories.OlderRepository;
import com.bookverse.development.packapps.apps.utils.ui.Table;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.constants.Queries;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GuessNumberTable extends JDialog implements ActionListener, MouseListener {

  private Table model = new Table();
  public final JTable viewTable = new JTable(model);
  private JLabel[] tables = new JLabel[5];
  private JLabel title, message;
  private JMenuItem create, read, delete, update;
  private String[] columns = {"ID", "NICKNAME", "LIMIT", "LEVEL", "DATE"};
  private Resources resources = new Resources();

  public GuessNumberTable(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new GridLayout());

    JPanel row = new JPanel(new FlowLayout());

    String[] images = {"adivinar.png", "ahorcado.png", "dado.png", "notas.png", "rompecabezas.png"};

    panel.setBorder(getBorder("Select table"));

    title = new JLabel();
    title.setFont(BIG);
    title.setForeground(MAIN_COLOR);

    message = new JLabel();
    message.setFont(BIG);
    message.setForeground(TEXT_COLOR);

    IntStream.range(0, tables.length).forEach(i -> {
      tables[i] = new JLabel();
      tables[i].setIcon(new ImageIcon(resources.getImage(images[i])));
      tables[i].addMouseListener(this);
      row.add(tables[i]);
    });

    panel.add(title, BorderLayout.EAST);
    panel.add(row, BorderLayout.CENTER);
    panel.add(message, BorderLayout.WEST);

    return panel;
  }

  private void createComponents() {

    add(getPanel(), BorderLayout.SOUTH);
    setIconImage(new ImageIcon(resources.getImage("adivinar.png")).getImage());

    Arrays.stream(columns).forEach(column -> model.addColumn(column));

    viewTable.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(viewTable);
    add(scroll, BorderLayout.CENTER);

    int[] sizes = {20, 200, 20, 20, 100};
    IntStream.range(0, viewTable.getColumnCount())
        .forEach(i -> viewTable.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]));

    JMenuBar menuBar = new JMenuBar();

    JMenu crud = resources.getMenu("CRUD", "mysql");
    create = resources.getMenuItem("Create", "create", this);
    read = resources.getMenuItem("Read", "read", this);
    update = resources.getMenuItem("Update", "update", this);
    delete = resources.getMenuItem("Delete", "delete", this);

    crud.add(create);
    crud.addSeparator();
    crud.add(read);
    crud.addSeparator();
    crud.add(update);
    crud.addSeparator();
    crud.add(delete);

    menuBar.add(crud);
    add(menuBar, BorderLayout.NORTH);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    pack();

    TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(model);
    viewTable.setRowSorter(rowSorter);

    IntStream.range(0, columns.length).forEach(i -> {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      viewTable.getColumnModel().getColumn(i).setCellRenderer(tcr);
    });

    repaint();
  }

  public void cleanTable() {

    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
  }

  private void btnConsultAP() {

    if (viewTable.getRowCount() != 0) {

      Object option = Alerts.searchRecords();

      if (option != null) {

        try {

          if (option.toString().equals("ID")) {
            searchResult(90, Queries.getDataByID(Format.tableName(GUESS_NUMBER)));
            setVisible(true);
          } else if (option.toString().equals("Nickname")) {
            searchResult(250, Queries.getDataByNickname(Format.tableName(GUESS_NUMBER)));
            setVisible(true);
          }

        } catch (Exception e) {
          Alerts.error(e, GUESS_NUMBER);
        }
      }

    } else {
      Alerts.message("Consult", "Empty table");
    }
  }

  private void btnUpdateAP() {

    if (viewTable.getRowCount() != 0) {

      int selectedRow = viewTable.getSelectedRow();

      if (selectedRow == -1) {
        Alerts.message("Update", "No record selected");
      } else {

        if (GeneralUtilities.loginDBA()) {

          OlderRepository.updateData(Alerts.inputText("Enter a Nickname", 20),
              String.valueOf(model.getValueAt(selectedRow, 0)), Format.tableName(GUESS_NUMBER));

          dispose();
          new Index().guessNumberTableAP();
        }
      }

    } else {
      Alerts.message("Update", "Empty table");
    }
  }

  private void btnDeleteAP() {

    if (viewTable.getRowCount() != 0) {

      if (viewTable.getSelectedRow() == -1) {
        Alerts.message("Delete", "No record selected");
      } else {

        int[] rows = viewTable.getSelectedRows();
        String[] IDs = Arrays.stream(rows).mapToObj(row -> String.valueOf(model.getValueAt(row, 0)))
            .toArray(String[]::new);

        if (GeneralUtilities.loginDBA()) {
          OlderRepository.deleteData(IDs, Format.tableName(GUESS_NUMBER));
          dispose();
          new Index().guessNumberTableAP();
        }
      }

    } else {
      Alerts.message("Delete", "Empty table");
    }
  }

  public void btnCreateAP() {

    Object option = JOptionPane.showInputDialog(null, "<html>" + Format.style()
            + "<strong><em>Select difficulty</em></strong></html>",
        "Difficulty level", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Easy", "Hard"},
        "Easy");

    if (option != null) {

      switch (option.toString()) {
        case "Easy":
          setVisible(false);
          new GuessNumber(this, true, false).start(this);
          break;
        case "Hard":
          setVisible(false);
          new GuessNumber(this, true, true).start(this);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + option.toString());
      }
    }
  }

  private void searchResult(int size, String query) {

    TableResult table = new TableResult(this, true, columns);
    table.cleanTable((DefaultTableModel) table.tabResult.getModel());

    try {

      if (OlderRepository.readTable(table.tabResult, query, false)) {
        table.setBounds(0, 0, 780, size);
        table.setResizable(false);
        table.setLocationRelativeTo(null);
        table.setTitle("Search result");
        setVisible(false);
        table.setVisible(true);
      }

    } catch (Exception e1) {
      Alerts.error(e1, GUESS_NUMBER);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == delete) {
      btnDeleteAP();
    } else if (e.getSource() == update) {
      btnUpdateAP();
    } else if (e.getSource() == read) {
      btnConsultAP();
    } else if (e.getSource() == create) {
      btnCreateAP();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == tables[0]) {
      Alerts.message("Message", "You're here!");
    } else if (e.getSource() == tables[1]) {
      setVisible(false);
      new Index().hangmanTableAP();
    } else if (e.getSource() == tables[2]) {
      setVisible(false);
      new Index().dicesTableAP();
    } else if (e.getSource() == tables[3]) {
      setVisible(false);
      new Index().notesTableAP();
    } else if (e.getSource() == tables[4]) {
      setVisible(false);
      new Index().puzzleTableAP();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == tables[0]) {
      tables[0].setCursor(POINT);
      title.setText("    " + GUESS_NUMBER);
      message.setText("       You're here");
    } else if (e.getSource() == tables[1]) {
      tables[1].setCursor(LOADER);
      title.setText("    " + HANGMAN);
    } else if (e.getSource() == tables[2]) {
      tables[2].setCursor(RESIZE);
      title.setText("    " + DICES);
    } else if (e.getSource() == tables[3]) {
      tables[3].setCursor(TEXT);
      title.setText("    " + NOTES);
    } else if (e.getSource() == tables[4]) {
      tables[4].setCursor(HAND);
      title.setText("    " + PUZZLE);
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == tables[0]) {
      title.setText("");
      message.setText("");
    } else if (e.getSource() == tables[1]) {
      title.setText("");
    } else if (e.getSource() == tables[2]) {
      title.setText("");
    } else if (e.getSource() == tables[3]) {
      title.setText("");
    } else if (e.getSource() == tables[4]) {
      title.setText("");
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void paint(Graphics g) {
    Dimension d = getSize();
    Dimension m = getMaximumSize();
    boolean resize = d.width > m.width || d.height > m.height;
    d.width = Math.min(m.width, d.width);
    d.height = Math.min(m.height, d.height);
    if (resize) {
      Point p = getLocation();
      setVisible(false);
      setSize(d);
      setLocation(p);
      setVisible(true);
    }
    super.paint(g);
  }
}