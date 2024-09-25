package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.DICES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.HANGMAN;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.NOTES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.PUZZLE;

import com.bookverse.development.packapps.apps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.apps.utils.constants.Styles;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class HangmanTable extends JDialog implements ActionListener, MouseListener {

  private Table model = new Table();
  public final JTable viewTable = new JTable(model);
  TableRowSorter<TableModel> rowSorter;
  private JLabel title, message;
  private JLabel[] tables = new JLabel[5];
  private JMenuItem create, read, delete, update;
  private String[] columns = {"ID", "NICKNAME", "MISTAKES", "STATE", "CATEGORY", "DATE"};
  

  public HangmanTable(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public JPanel getPanel() {

    JPanel panel = new JPanel(new GridLayout());
    JPanel row = new JPanel(new FlowLayout());
    String[] images = {"adivinar.png", "ahorcado.png", "dado.png", "notas.png", "rompecabezas.png"};

    panel.setBorder(Resources.getBorder("Select table"));

    title = new JLabel();
    title.setFont(Styles.BIG);
    title.setForeground(MAIN_COLOR);
    title.addMouseListener(this);

    message = new JLabel();
    message.setFont(Styles.BIG);
    message.setForeground(TEXT_COLOR);
    message.addMouseListener(this);

    IntStream.range(0, tables.length).forEach(i -> {
      tables[i] = new JLabel();
      tables[i].setIcon(new ImageIcon(Resources.getImage(images[i])));
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
    setIconImage(new ImageIcon(Resources.getImage("ahorcado.png")).getImage());

    IntStream.range(0, columns.length).forEach(i -> model.addColumn(columns[i]));

    viewTable.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(viewTable);
    add(scroll, BorderLayout.CENTER);

    int[] sizes = {20, 170, 40, 20, 75, 100};
    IntStream.range(0, viewTable.getColumnCount()).forEach(i -> viewTable.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]));

    JMenuBar menuBar = new JMenuBar();

    JMenu crud = Resources.getMenu("CRUD", "mysql");
    create = Resources.getMenuItem("Create", "create", this);
    read = Resources.getMenuItem("Read", "read", this);
    update = Resources.getMenuItem("Update", "update", this);
    delete = Resources.getMenuItem("Delete", "delete", this);

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

    rowSorter = new TableRowSorter<>(model);
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
            searchResult(90, Queries.getDataByID(Format.tableName(HANGMAN)));
            setVisible(true);
          } else if (option.toString().equals("Nickname")) {
            searchResult(250, Queries.getDataByNickname(Format.tableName(HANGMAN)));
            setVisible(true);
          }

        } catch (Exception e) {
          Alerts.error(e, HANGMAN);
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

        if (GeneralUtils.loginDBA()) {
          OlderRepository.updateData(Alerts.inputText("Enter a Nickname", 20),
              String.valueOf(model.getValueAt(selectedRow, 0)), Format.tableName(HANGMAN));

          dispose();
          new Index().hangmanTableAP();
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

        if (GeneralUtils.loginDBA()) {
          OlderRepository.deleteData(IDs, Format.tableName(HANGMAN));
          dispose();
          new Index().hangmanTableAP();
        }
      }

    } else {
      Alerts.message("Delete", "Empty table");
    }
  }

  private void searchResult(int alto, String sql) {

    TableResult table = new TableResult(this, true, columns);
    table.cleanTable((DefaultTableModel) table.tabResult.getModel());

    try {

      if (OlderRepository.readTable(table.tabResult, sql, false)) {
        table.setBounds(0, 0, 780, alto);
        table.setResizable(false);
        table.setLocationRelativeTo(null);
        table.setTitle("Search result");
        setVisible(false);
        table.setVisible(true);
      }

    } catch (Exception e1) {
      Alerts.error(e1, HANGMAN);
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
      setVisible(false);
      new Hangman(this, true).start(this);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == tables[0]) {
      setVisible(false);
      new Index().guessNumberTableAP();
    } else if (e.getSource() == tables[1]) {
      Alerts.message("Message", "You're here!");
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
      tables[0].setCursor(Styles.POINT);
      title.setText("    " + GUESS_NUMBER);
    } else if (e.getSource() == tables[1]) {
      tables[1].setCursor(Styles.LOADER);
      title.setText("    " + HANGMAN);
      message.setText("       You're here");
    } else if (e.getSource() == tables[2]) {
      tables[2].setCursor(Styles.RESIZE);
      title.setText("    " + DICES);
    } else if (e.getSource() == tables[3]) {
      tables[3].setCursor(Styles.TEXT);
      title.setText("    " + NOTES);
    } else if (e.getSource() == tables[4]) {
      tables[4].setCursor(Styles.HAND);
      title.setText("    " + PUZZLE);
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == tables[0]) {
      title.setText("");
    } else if (e.getSource() == tables[1]) {
      message.setText("");
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