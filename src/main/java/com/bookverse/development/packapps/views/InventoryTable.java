package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.HAND;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.getBorder;
import static com.bookverse.development.packapps.core.AppConfig.loginDBA;
import static com.bookverse.development.packapps.utils.AppConstants.INVENTORY;

import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.models.Table;
import com.bookverse.development.packapps.utils.Alerts;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jetbrains.annotations.NotNull;

public class InventoryTable extends JDialog implements MouseListener {

  public JLabel[] actions = new JLabel[4];
  public JTable viewTable;
  public String reference = "";
  public int status = 0;
  private JLabel tittle;
  private Table model = new Table();
  private TableRowSorter<TableModel> rowSorter;
  private String[] columns = {"REFERENCE", "STATE", "PRICE", "QUANTITY"};
  private Resources resources = new Resources();
  private boolean search;

  public InventoryTable(JDialog parent, boolean modal, boolean search) {
    super(parent, modal);
    this.search = search;
    createComponents();
  }

  public InventoryTable(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  @NotNull
  private JPanel getPanel() {

    JPanel panel = new JPanel(new GridLayout());

    JPanel row = new JPanel(new FlowLayout());

    String[] images = {"eliminar.png", "select.png", "read.png", "refresh.png"};

    panel.setBorder(getBorder("Select action"));

    tittle = new JLabel();
    tittle.setFont(BIG);
    tittle.setForeground(MAIN_COLOR);

    JLabel message = new JLabel();
    message.setFont(BIG);
    message.setForeground(TEXT_COLOR);

    IntStream.range(0, actions.length).forEach(i -> {
      actions[i] = new JLabel();
      actions[i].setIcon(new ImageIcon(resources.getImage(images[i])));
      actions[i].addMouseListener(this);
      row.add(actions[i]);
    });

    if (search) {
      actions[0].setVisible(false);
    } else {
      actions[1].setVisible(false);
    }

    panel.add(tittle, BorderLayout.EAST);
    panel.add(row, BorderLayout.CENTER);
    panel.add(message, BorderLayout.WEST);

    return panel;
  }

  private void createComponents() {

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("inventario.png")).getImage());

    IntStream.range(0, columns.length).forEach(i -> model.addColumn(columns[i]));

    viewTable = new JTable(model);
    viewTable.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(viewTable);
    add(scroll, BorderLayout.CENTER);

    int[] sizes = {130, 80, 80, 40};
    IntStream.range(0, viewTable.getColumnCount())
        .forEach(i -> viewTable.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]));

    add(getPanel(), BorderLayout.SOUTH);

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

  private void btnSelectAP() {

    int selectedRow;

    selectedRow = viewTable.getSelectedRow();

    if (selectedRow == -1) {
      Alerts.message("Message", "No record selected");
      status = 0;
    } else {

      if (!String.valueOf(model.getValueAt(selectedRow, 3)).equals("0")) {
        reference = String.valueOf(model.getValueAt(selectedRow, 0));
        status = 1;
      } else {
        reference = String.valueOf(model.getValueAt(selectedRow, 0));
        status = 2;
      }
      dispose();
    }
  }

  private void btnSearchAP() {

    String search = searchProduct();

    if (!search.equals("")) {
      rowSorter.setRowFilter(RowFilter.regexFilter(search, 0));
    }
  }

  private void btnDeleteAP() {

    int selectedRow;

    selectedRow = viewTable.getSelectedRow();

    if (selectedRow == -1) {
      Alerts.message("Delete", "No record selected");
    } else {

      if (loginDBA()) {

        int[] rows = viewTable.getSelectedRows();
        String[] IDs = Arrays.stream(rows).mapToObj(row -> String.valueOf(model.getValueAt(row, 0)))
            .toArray(String[]::new);

        Database.deleteData(IDs, INVENTORY);
        dispose();
        new HomeStore().btnInventoryTableAP(false);
      }
    }
  }

  private void btnRefreshAP() {
    dispose();
    new HomeStore().btnInventoryTableAP(search);
  }

  public String searchProduct() {

    String aux = Alerts.inputText("What reference are you looking for?");

    if (aux == null) {
      aux = "";
    }

    return aux;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      btnDeleteAP();
    } else if (e.getSource() == actions[1]) {
      btnSelectAP();
    } else if (e.getSource() == actions[2]) {
      btnSearchAP();
    } else if (e.getSource() == actions[3]) {
      btnRefreshAP();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      actions[0].setCursor(HAND);
      tittle.setText("Eliminar Producto");
    } else if (e.getSource() == actions[1]) {
      actions[1].setCursor(HAND);
      tittle.setText("Seleccionar Producto");
    } else if (e.getSource() == actions[2]) {
      actions[2].setCursor(HAND);
      tittle.setText("Buscar Referencia");
    } else if (e.getSource() == actions[3]) {
      actions[3].setCursor(HAND);
      tittle.setText("Actualizar Tabla");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      tittle.setText("");
    } else if (e.getSource() == actions[1]) {
      tittle.setText("");
    } else if (e.getSource() == actions[2]) {
      tittle.setText("");
    } else if (e.getSource() == actions[3]) {
      tittle.setText("");
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}