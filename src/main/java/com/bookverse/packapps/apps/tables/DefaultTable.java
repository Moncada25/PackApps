package com.bookverse.packapps.apps.tables;

import com.bookverse.packapps.utils.ui.factory.Menu;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.repositories.OlderRepository;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.constants.DatabaseConstants;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultTable extends DefaultTableModel {

  private JMenuBar menuBar = new JMenuBar();
  private JLabel lblTitleTable;
  private JLabel lblMessage;
  private JLabel[] tables = new JLabel[5];
  private String[] tableTitles = {
      DatabaseConstants.GUESS_NUMBER,
      DatabaseConstants.HANGMAN,
      DatabaseConstants.DICES,
      DatabaseConstants.NOTES,
      DatabaseConstants.PUZZLE
  };

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }

  public void createTable(
      JDialog parent,
      String image,
      Map<String, Integer> columns,
      MouseListener listener,
      JTable viewTable
  ) {

    parent.setIconImage(new ImageIcon(Resources.getImage(image)).getImage());
    parent.add(getTablesPanel(listener), BorderLayout.SOUTH);

    columns.keySet().forEach(this::addColumn);

    viewTable.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(viewTable);
    parent.add(scroll, BorderLayout.CENTER);
  }

  public void createCrud(
      JDialog parent,
      Map<String, Integer> columns,
      JTable viewTable,
      JMenuItem create,
      JMenuItem read,
      JMenuItem update,
      JMenuItem delete
  ) {

    JMenu actions = new Menu().setText("CRUD").setImage("mysql").build();

    actions.add(create);
    actions.addSeparator();
    actions.add(read);
    actions.addSeparator();
    actions.add(update);
    actions.addSeparator();
    actions.add(delete);

    menuBar.add(actions);
    parent.add(menuBar, BorderLayout.NORTH);
    parent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    parent.pack();

    TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(this);
    viewTable.setRowSorter(rowSorter);

    AtomicInteger index = new AtomicInteger();

    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    tcr.setHorizontalAlignment(SwingConstants.CENTER);

    columns.keySet().forEach(column -> {
      viewTable.getColumnModel().getColumn(index.get()).setCellRenderer(tcr);
      viewTable.getColumnModel().getColumn(index.get()).setPreferredWidth(columns.get(column));
      index.getAndIncrement();
    });

    parent.repaint();
  }

  public void searchResult(JDialog parent, int size, String query, Map<String, Integer> columns) {

    ResultTable resultTable = new ResultTable(parent, true, columns);

    resultTable.cleanTable((DefaultTableModel) resultTable.getTabResult().getModel());

    try {

      if (OlderRepository.readTable(resultTable.getTabResult(), query, false)) {
        resultTable.setBounds(0, 0, 780, size);
        resultTable.setResizable(false);
        resultTable.setLocationRelativeTo(null);
        resultTable.setTitle("Search result");
        parent.setVisible(false);
        resultTable.setVisible(true);
      }

    } catch (Exception e1) {
      Alerts.error(e1, "Search result");
    }
  }

  private JPanel getTablesPanel(MouseListener parent) {

    JPanel panel = new JPanel(new GridLayout());
    JPanel row = new JPanel(new FlowLayout());

    String[] images = {"adivinar.png", "ahorcado.png", "dado.png", "notas.png", "rompecabezas.png"};

    panel.setBorder(Resources.getBorder("Select table"));

    lblTitleTable = new JLabel();
    lblTitleTable.setFont(Styles.BIG);
    lblTitleTable.setForeground(Styles.MAIN_COLOR);
    lblTitleTable.addMouseListener(parent);

    lblMessage = new JLabel();
    lblMessage.setFont(Styles.BIG);
    lblMessage.setForeground(Styles.TEXT_COLOR);
    lblMessage.addMouseListener(parent);

    IntStream.range(0, tables.length).forEach(i -> {
      tables[i] = new JLabel();
      tables[i].setIcon(new ImageIcon(Resources.getImage(images[i])));
      tables[i].addMouseListener(parent);
      tables[i].setCursor(Styles.HAND);
      row.add(tables[i]);
    });

    panel.add(lblTitleTable, BorderLayout.EAST);
    panel.add(row, BorderLayout.CENTER);
    panel.add(lblMessage, BorderLayout.WEST);

    return panel;
  }
}