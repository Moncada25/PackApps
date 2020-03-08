package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.models.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class InventarioTabla extends JDialog implements MouseListener {

  public JLabel[] actions = new JLabel[4];
  public JTable inventarioTab;
  public String referencia = "";
  public int status = 0;
  private JLabel titulo, men;
  private JScrollPane scroll;
  private Table modelo = new Table();
  private TableRowSorter<TableModel> ordenar;
  private String[] columnas = {"REFERENCIA", "ESTADO", "PRECIO", "UNIDADES"};
  private Resources img = new Resources();
  private boolean buscar;

  public InventarioTabla(JDialog parent, boolean modal, boolean buscar) {

    super(parent, modal);
    this.buscar = buscar;
    componentes();
  }

  // Constructor que recibe la ventana padre y el valor modal
  public InventarioTabla(JFrame parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  public JPanel getPanel() {

    JPanel panel = new JPanel(new GridLayout());

    JPanel fila = new JPanel(new FlowLayout());

    String[] imgs = {"eliminar.png", "select.png", "read.png", "refresh.png"};

    panel.setBorder(img.cr.bordeAzul("Select Action"));

    titulo = new JLabel();
    titulo.setFont(img.cr.BIG);
    titulo.setForeground(img.cr.ROJO);

    men = new JLabel();
    men.setFont(img.cr.BIG);
    men.setForeground(img.cr.AZUL);

    /* ICONOS */
    for (int i = 0; i < actions.length; i++) {

      actions[i] = new JLabel();
      actions[i].setIcon(new ImageIcon(img.getImage(imgs[i])));
      actions[i].addMouseListener(this);
      fila.add(actions[i]);
    }

    if (buscar) {
      actions[0].setVisible(false);
    } else {
      actions[1].setVisible(false);
    }

    panel.add(titulo, BorderLayout.EAST);
    panel.add(fila, BorderLayout.CENTER);
    panel.add(men, BorderLayout.WEST);

    return panel;
  }

  // Crea los componentes de la tabla
  private void componentes() {

    setIconImage(new ImageIcon(img.getImage("inventario.png")).getImage());

    /* TABLA */
    for (int i = 0; i < columnas.length; i++) {
      modelo.addColumn(columnas[i]);
    }

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    inventarioTab = new JTable(modelo);
    inventarioTab.getTableHeader().setReorderingAllowed(false);
    scroll = new JScrollPane(inventarioTab);
    add(scroll, BorderLayout.CENTER);

    int[] anchos = {130, 80, 80, 40};
    for (int i = 0; i < inventarioTab.getColumnCount(); i++) {
      inventarioTab.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
    }

    add(getPanel(), BorderLayout.SOUTH);

    pack();

    ordenar = new TableRowSorter<TableModel>(modelo);
    inventarioTab.setRowSorter(ordenar);

    for (int i = 0; i < columnas.length; i++) {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      inventarioTab.getColumnModel().getColumn(i).setCellRenderer(tcr);
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

  public void btnSelectAP() {

    int filaseleccionada;

    filaseleccionada = inventarioTab.getSelectedRow();

    if (filaseleccionada == -1) {

      JOptionPane.showMessageDialog(null, "No ha seleccionado ningún producto", "Mensaje",
          JOptionPane.PLAIN_MESSAGE);

      status = 0;

    } else {

      if (!String.valueOf(modelo.getValueAt(filaseleccionada, 3)).equals("0")) {
        referencia = String.valueOf(modelo.getValueAt(filaseleccionada, 0));

        status = 1;
      } else {
        referencia = String.valueOf(modelo.getValueAt(filaseleccionada, 0));

        status = 2;
      }

      dispose();
    }
  }

  public void btnBuscarAP() {

    String busqueda = img.cr.buscarProducto();

    if (!busqueda.equals("")) {
      ordenar.setRowFilter(RowFilter.regexFilter(busqueda, 0));
    }
  }

  public void btnEliminarAP() {

    int filaseleccionada;

    filaseleccionada = inventarioTab.getSelectedRow();

    if (filaseleccionada == -1) {

      JOptionPane.showMessageDialog(null, "No ha seleccionado ningún producto", "Mensaje",
          JOptionPane.PLAIN_MESSAGE);

    } else {

      if (img.cr.loginDBA()) {

        int[] rows = inventarioTab.getSelectedRows();
        String[] IDs = new String[rows.length];

        for (int i = 0; i < rows.length; i++) {
          IDs[i] = String.valueOf(modelo.getValueAt(rows[i], 0));
        }

        img.db.deleteData(IDs, "inventario");
        dispose();
        new Opciones().btnInventarioAP(false);
      }
    }
  }

  public void btnRefreshAP() {
    dispose();
    new Opciones().btnInventarioAP(buscar);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      btnEliminarAP();
    } else if (e.getSource() == actions[1]) {
      btnSelectAP();
    } else if (e.getSource() == actions[2]) {
      btnBuscarAP();
    } else if (e.getSource() == actions[3]) {
      btnRefreshAP();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      actions[0].setCursor(img.cr.MANO);
      titulo.setText("Eliminar Producto");
    } else if (e.getSource() == actions[1]) {
      actions[1].setCursor(img.cr.MANO);
      titulo.setText("Seleccionar Producto");
    } else if (e.getSource() == actions[2]) {
      actions[2].setCursor(img.cr.MANO);
      titulo.setText("Buscar Referencia");
    } else if (e.getSource() == actions[3]) {
      actions[3].setCursor(img.cr.MANO);
      titulo.setText("Actualizar Tabla");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      titulo.setText("");
    } else if (e.getSource() == actions[1]) {
      titulo.setText("");
    } else if (e.getSource() == actions[2]) {
      titulo.setText("");
    } else if (e.getSource() == actions[3]) {
      titulo.setText("");
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}