package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.AppsConstants.DICES;
import static com.bookverse.development.packapps.utils.AppsConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.utils.AppsConstants.HANGMAN;
import static com.bookverse.development.packapps.utils.AppsConstants.NOTES;
import static com.bookverse.development.packapps.utils.AppsConstants.PUZZLE;

import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.models.Table;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.Querys;
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

  public JTable guessNumberTab;
  private JLabel[] tables = new JLabel[5];
  private JLabel tittle, message;
  private Table model = new Table();
  private JMenuItem create, read, delete, update;
  private String[] columns = {"ID", "NICKNAME", "LIMIT", "LEVEL", "DATE"};
  private Resources resources = new Resources();

  public GuessNumberTable(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public JPanel getPanel() {

    JPanel panel = new JPanel(new GridLayout());

    JPanel row = new JPanel(new FlowLayout());

    String[] images = {"adivinar.png", "ahorcado.png", "dado.png", "notas.png", "rompecabezas.png"};

    panel.setBorder(resources.cr.bordeAzul("Select Table"));

    tittle = new JLabel();
    tittle.setFont(resources.cr.BIG);
    tittle.setForeground(resources.cr.ROJO);

    message = new JLabel();
    message.setFont(resources.cr.BIG);
    message.setForeground(resources.cr.AZUL);

    IntStream.range(0, tables.length).forEach(i -> {
      tables[i] = new JLabel();
      tables[i].setIcon(new ImageIcon(resources.getImage(images[i])));
      tables[i].addMouseListener(this);
      row.add(tables[i]);
    });

    panel.add(tittle, BorderLayout.EAST);
    panel.add(row, BorderLayout.CENTER);
    panel.add(message, BorderLayout.WEST);

    return panel;
  }

  private void createComponents() {

    add(getPanel(), BorderLayout.SOUTH);
    setIconImage(new ImageIcon(resources.getImage("adivinar.png")).getImage());

    Arrays.stream(columns).forEach(column -> model.addColumn(column));

    guessNumberTab = new JTable(model);
    guessNumberTab.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(guessNumberTab);
    add(scroll, BorderLayout.CENTER);

    int[] sizes = {20, 200, 20, 20, 100};
    IntStream.range(0, guessNumberTab.getColumnCount())
        .forEach(i -> guessNumberTab.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]));

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
    guessNumberTab.setRowSorter(rowSorter);

    IntStream.range(0, columns.length).forEach(i -> {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      guessNumberTab.getColumnModel().getColumn(i).setCellRenderer(tcr);
    });

    repaint();
  }

  public void cleanTable() {

    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
  }

  private void btnConsultAP() {

    if (guessNumberTab.getRowCount() != 0) {

      Object option;

      option = JOptionPane.showInputDialog(null,
          "<html>" + resources.cr.styleJOption()
              + "<strong><em>What are you looking for?</em></strong></html>",
          "Search records", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"ID", "Nickname"},
          "ID");

      if (option != null) {

        try {

          if (option.toString().equals("ID")) {
            searchResult(90, Querys.getDataByID(Format.tableName(GUESS_NUMBER)));
            setVisible(true);
          } else if (option.toString().equals("Nickname")) {
            searchResult(250, Querys.getDataByNickname(Format.tableName(GUESS_NUMBER)));
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

    if (guessNumberTab.getRowCount() != 0) {

      int selectedRow = guessNumberTab.getSelectedRow();

      if (selectedRow == -1) {
        Alerts.message("Update", "No record selected");
      } else {

        if (resources.cr.loginDBA()) {

          resources.db.updateData(resources.cr.ingreseNickname("Enter a Nickname", 20),
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

    if (guessNumberTab.getRowCount() != 0) {

      if (guessNumberTab.getSelectedRow() == -1) {
        Alerts.message("Delete", "No record selected");
      } else {

        int[] rows = guessNumberTab.getSelectedRows();
        String[] IDs = new String[rows.length];

        for (int i = 0; i < rows.length; i++) {
          IDs[i] = String.valueOf(model.getValueAt(rows[i], 0));
        }

        if (resources.cr.loginDBA()) {
          resources.db.deleteData(IDs, Format.tableName(GUESS_NUMBER));
          dispose();
          new Index().guessNumberTableAP();
        }
      }

    } else {
      Alerts.message("Delete", "Empty table");
    }
  }

  public void btnCreateAP() {

    Object option;

    option = JOptionPane.showInputDialog(null, "<html>" + resources.cr.styleJOption()
            + "<strong><em>Select difficulty</em></strong></html>",
        "Difficulty level", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Easy", "Hard"},
        "Easy");

    if (option != null) {

      if (option.toString().equals("Easy")) {
        setVisible(false);
        new GuessNumber(this, true, false).start(this);
      } else if (option.toString().equals("Hard")) {
        setVisible(false);
        new GuessNumber(this, true, true).start(this);
      }
    }
  }

  private void searchResult(int size, String query) {

    TableResult table = new TableResult(this, true, columns);

    table.cleanTable((DefaultTableModel) table.tabResult.getModel());

    try {

      if (resources.db.readTable(table.tabResult, query, false)) {
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
      new Index().DadosTableAP();
    } else if (e.getSource() == tables[3]) {
      setVisible(false);
      new Index().NotasTableAP();
    } else if (e.getSource() == tables[4]) {
      setVisible(false);
      new Index().RompecabezasTableAP();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == tables[0]) {
      tables[0].setCursor(resources.cr.MIRA);
      tittle.setText("    " + GUESS_NUMBER);
      message.setText("       You're here");
    } else if (e.getSource() == tables[1]) {
      tables[1].setCursor(resources.cr.CARGAR);
      tittle.setText("    " + HANGMAN);
    } else if (e.getSource() == tables[2]) {
      tables[2].setCursor(resources.cr.REDI);
      tittle.setText("    " + DICES);
    } else if (e.getSource() == tables[3]) {
      tables[3].setCursor(resources.cr.TEXT);
      tittle.setText("    " + NOTES);
    } else if (e.getSource() == tables[4]) {
      tables[4].setCursor(resources.cr.MANO);
      tittle.setText("    " + PUZZLE);
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == tables[0]) {
      tittle.setText("");
      message.setText("");
    } else if (e.getSource() == tables[1]) {
      tittle.setText("");
    } else if (e.getSource() == tables[2]) {
      tittle.setText("");
    } else if (e.getSource() == tables[3]) {
      tittle.setText("");
    } else if (e.getSource() == tables[4]) {
      tittle.setText("");
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