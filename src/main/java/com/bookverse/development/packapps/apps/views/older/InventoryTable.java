package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.HAND;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.apps.utils.ui.Resources.getBorder;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.INVENTORY;

import com.bookverse.development.packapps.apps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.repositories.OlderRepository;
import com.bookverse.development.packapps.apps.utils.ui.Table;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
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

  protected static final JLabel[] actions = new JLabel[4];
  private int status = 0;
  private String reference = "";
  private Table model = new Table();
  public final JTable viewTable = new JTable(model);
  private JLabel title;
  private TableRowSorter<TableModel> rowSorter;
  private String[] columns = {"REFERENCE", "STATE", "PRICE", "QUANTITY"};
  
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

    title = new JLabel();
    title.setFont(BIG);
    title.setForeground(MAIN_COLOR);

    JLabel message = new JLabel();
    message.setFont(BIG);
    message.setForeground(TEXT_COLOR);

    IntStream.range(0, actions.length).forEach(i -> {
      actions[i] = new JLabel();
      actions[i].setIcon(new ImageIcon(Resources.getImage(images[i])));
      actions[i].addMouseListener(this);
      row.add(actions[i]);
    });

    if (search) {
      actions[0].setVisible(false);
    } else {
      actions[1].setVisible(false);
    }

    panel.add(title, BorderLayout.EAST);
    panel.add(row, BorderLayout.CENTER);
    panel.add(message, BorderLayout.WEST);

    return panel;
  }

  private void createComponents() {

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("inventario.png")).getImage());

    IntStream.range(0, columns.length).forEach(i -> model.addColumn(columns[i]));

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

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  private void btnSelectAP() {

    int selectedRow;

    selectedRow = viewTable.getSelectedRow();

    if (selectedRow == -1) {
      Alerts.message("Message", "No record selected.");
      setStatus(0);
    } else {

      if (!String.valueOf(model.getValueAt(selectedRow, 3)).equals("0")) {
        setReference(String.valueOf(model.getValueAt(selectedRow, 0)));
        setStatus(1);
      } else {
        setReference(String.valueOf(model.getValueAt(selectedRow, 0)));
        setStatus(2);
      }
      dispose();
    }
  }

  private void btnSearchAP() {

    String search = searchProduct();

    if (!search.isEmpty()) {
      rowSorter.setRowFilter(RowFilter.regexFilter(search, 0));
    }
  }

  private void btnDeleteAP() {

    int selectedRow;

    selectedRow = viewTable.getSelectedRow();

    if (selectedRow == -1) {
      Alerts.message("Delete", "No record selected");
    } else {

      if (GeneralUtils.loginDBA()) {

        int[] rows = viewTable.getSelectedRows();
        String[] IDs = Arrays.stream(rows).mapToObj(row -> String.valueOf(model.getValueAt(row, 0)))
            .toArray(String[]::new);

        OlderRepository.deleteData(IDs, INVENTORY);
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
      title.setText("Delete product");
    } else if (e.getSource() == actions[1]) {
      actions[1].setCursor(HAND);
      title.setText("Select product");
    } else if (e.getSource() == actions[2]) {
      actions[2].setCursor(HAND);
      title.setText("Search reference");
    } else if (e.getSource() == actions[3]) {
      actions[3].setCursor(HAND);
      title.setText("Refresh table");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      title.setText("");
    } else if (e.getSource() == actions[1]) {
      title.setText("");
    } else if (e.getSource() == actions[2]) {
      title.setText("");
    } else if (e.getSource() == actions[3]) {
      title.setText("");
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}