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

public class CashRegisterTable extends JDialog {

  public JTable registradoraTab;
  private JScrollPane scroll;
  private Table modelo = new Table();
  private TableRowSorter<TableModel> ordenar;
  private String[] columnas = {"ID", "USUARIO", "VENTAS", "TOTAL VENTAS", "COMPRAS",
      "TOTAL COMPRAS", "TOTAL PRÉSTAMOS"};

  public CashRegisterTable(JDialog parent, boolean modal) {

    super(parent, modal);

    componentes();

  }

  // Constructor que recibe la ventana padre y el valor modal
  public CashRegisterTable(JFrame parent, boolean modal) {

    super(parent, modal);

    componentes();

  }

  // Constructor que no recibe parámetros
  public CashRegisterTable() {
    componentes();
  }

  // Crea los componentes de la tabla
  private void componentes() {

    setIconImage(new ImageIcon(new Resources().getImage("registradora.png")).getImage());

    /* TABLA */
    for (int i = 0; i < columnas.length; i++) {
      modelo.addColumn(columnas[i]);
    }

    registradoraTab = new JTable(modelo);
    registradoraTab.getTableHeader().setReorderingAllowed(false);
    scroll = new JScrollPane(registradoraTab);
    getContentPane().add(scroll, BorderLayout.CENTER);

    int[] anchos = {30,30, 20, 80, 20, 80, 100};
    for (int i = 0; i < registradoraTab.getColumnCount(); i++) {
      registradoraTab.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
    }

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    pack();

    ordenar = new TableRowSorter<TableModel>(modelo);
    registradoraTab.setRowSorter(ordenar);

    for (int i = 0; i < columnas.length; i++) {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      registradoraTab.getColumnModel().getColumn(i).setCellRenderer(tcr);
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