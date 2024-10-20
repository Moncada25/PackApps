package com.bookverse.development.packapps.apps.views.older;

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PurchasesTable extends JDialog {

  private Table model = new Table();
  public final JTable viewTable = new JTable(model);
  private String[] columns = {"ID", "PRODUCT", "USER", "DOCUMENT", "PHONE", "DATE", "UNITS",
      "TOTAL"};

  public PurchasesTable(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public PurchasesTable(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setIconImage(new ImageIcon(Resources.getImage("comprar.png")).getImage());

    IntStream.range(0, columns.length).forEach(i -> model.addColumn(columns[i]));

    viewTable.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(viewTable);
    getContentPane().add(scroll, BorderLayout.CENTER);

    int[] sizes = {30, 70, 40, 70, 70, 140, 30, 70};
    IntStream.range(0, viewTable.getColumnCount())
        .forEach(i -> viewTable.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]));

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

  public void cleanTable() {

    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
  }
}