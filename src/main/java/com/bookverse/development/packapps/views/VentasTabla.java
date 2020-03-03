package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
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

public class VentasTabla extends JDialog {

  public JTable ventasTab;
  private JScrollPane scroll;
  private Table modelo = new Table();
  private TableRowSorter<TableModel> ordenar;
  private String[] columnas = {"PRODUCTO", "USUARIO", "DOCUMENTO", "TELÉFONO", "FECHA", "UNIDADES",
      "TOTAL"};

  // Constructor que recibe la ventana padre y el valor modal
  public VentasTabla(JDialog parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  //Constructor que recibe la ventana padre y el valor modal
  public VentasTabla(JFrame parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  // Constructor que no recibe parámetros
  public VentasTabla() {

    componentes();
  }

  // Crea los componentes de la tabla
  private void componentes() {

    setIconImage(new ImageIcon(new Resources().getImage("vender.png")).getImage());

    /* TABLA */
    for (int i = 0; i < columnas.length; i++) {
      modelo.addColumn(columnas[i]);
    }

    ventasTab = new JTable(modelo);
    ventasTab.getTableHeader().setReorderingAllowed(false);
    scroll = new JScrollPane(ventasTab);
    getContentPane().add(scroll, BorderLayout.CENTER);

    int[] anchos = {60, 35, 70, 50, 105, 50, 40};
    for (int i = 0; i < ventasTab.getColumnCount(); i++) {
      ventasTab.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
    }

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    pack();

    ordenar = new TableRowSorter<TableModel>(modelo);
    ventasTab.setRowSorter(ordenar);

    for (int i = 0; i < columnas.length; i++) {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      ventasTab.getColumnModel().getColumn(i).setCellRenderer(tcr);
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