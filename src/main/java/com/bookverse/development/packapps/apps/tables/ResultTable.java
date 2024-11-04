package com.bookverse.development.packapps.apps.tables;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResultTable extends JDialog {

  private DefaultTable model = new DefaultTable();
  private JTable tabResult = new JTable(model);

  public ResultTable(JDialog parent, boolean modal, Map<String, Integer> columns) {
    super(parent, modal);
    createComponents(columns);
  }

  private void createComponents(Map<String, Integer> columns) {

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    tabResult.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(tabResult);
    getContentPane().add(scroll, BorderLayout.CENTER);

    pack();

    TableRowSorter<TableModel> tableModelTableRowSorter = new TableRowSorter<>(model);
    tabResult.setRowSorter(tableModelTableRowSorter);

    columns.keySet().forEach(column -> model.addColumn(column));

    AtomicInteger index = new AtomicInteger();

    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    tcr.setHorizontalAlignment(SwingConstants.CENTER);

    columns.keySet().forEach(column -> {
      tabResult.getColumnModel().getColumn(index.get()).setPreferredWidth(columns.get(column));
      tabResult.getColumnModel().getColumn(index.get()).setCellRenderer(tcr);
      index.getAndIncrement();
    });
  }

  public void cleanTable(DefaultTableModel model) {

    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
  }
}