package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.models.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class LoansTable extends JDialog {

  public JTable viewTable;
  private JScrollPane scroll;
  private Table modelo = new Table();
  private TableRowSorter<TableModel> ordenar;
  private String[] columnas = {"USUARIO", "NOMBRE", "DOCUMENTO", "REFERENCIA", "TEL�FONO", "PLAZO",
      "VALOR"};

  // Constructor que recibe la ventana padre y el valor modal
  public LoansTable(JDialog parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  // Constructor que recibe la ventana padre y el valor modal
  public LoansTable(JFrame parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  // Constructor que no recibe par�metros
  public LoansTable() {

    componentes();
  }

  // Crea los componentes de la tabla
  private void componentes() {

    setIconImage(new ImageIcon(new Resources().getImage("prestar.png")).getImage());

    /* TABLA */
    for (int i = 0; i < columnas.length; i++) {
      modelo.addColumn(columnas[i]);
    }

    viewTable = new JTable(modelo);
    viewTable.getTableHeader().setReorderingAllowed(false);
    scroll = new JScrollPane(viewTable);
    getContentPane().add(scroll, BorderLayout.CENTER);

    int[] anchos = {40, 80, 70, 70, 70, 50, 40};
    for (int i = 0; i < viewTable.getColumnCount(); i++) {
      viewTable.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
    }

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    pack();

    ordenar = new TableRowSorter<TableModel>(modelo);
    viewTable.setRowSorter(ordenar);

    for (int i = 0; i < columnas.length; i++) {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      viewTable.getColumnModel().getColumn(i).setCellRenderer(tcr);
    }

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

  public void limpiarTabla() {

    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
  }
}