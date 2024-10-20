package com.bookverse.development.packapps.apps.views.older;

import com.bookverse.development.packapps.utils.ui.Table;
import java.awt.BorderLayout;
import java.util.stream.IntStream;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TableResult extends JDialog {

  private static Table model = new Table();
  public static final JTable tabResult = new JTable(model);

  public TableResult(JDialog parent, boolean modal, String[] columns) {
    super(parent, modal);
    createComponents(columns);
  }

  private void createComponents(String[] columns) {

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    IntStream.range(0, columns.length).forEach(i -> model.addColumn(columns[i]));

    tabResult.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(tabResult);
    getContentPane().add(scroll, BorderLayout.CENTER);

    pack();

    TableRowSorter<TableModel> ordeal = new TableRowSorter<>(model);
    tabResult.setRowSorter(ordeal);

    IntStream.range(0, columns.length).forEach(i -> {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      tabResult.getColumnModel().getColumn(i).setCellRenderer(tcr);
    });
  }

  public void cleanTable(DefaultTableModel model) {

    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
  }
}