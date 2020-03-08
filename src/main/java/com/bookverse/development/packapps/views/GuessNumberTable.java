package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.AppsConstants.GUESS_NUMBER;

import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.models.Table;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.Querys;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GuessNumberTable extends JDialog implements ActionListener, MouseListener {

  public JTable guessNumberTab;
  private JLabel[] tablas = new JLabel[5];
  private JLabel tittle, message;
  private Table model = new Table();
  private JMenuItem create, read, delete, update;
  private String[] columns = {"ID", "NICKNAME", "LIMIT", "LEVEL", "DATE"};
  private Resources resources = new Resources();

  public GuessNumberTable(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public JPanel getPanel() {

    JPanel panel = new JPanel(new GridLayout());

    JPanel row = new JPanel(new FlowLayout());

    String[] images = {"adivinar.png", "ahorcado.png", "dado.png", "notas.png", "rompecabezas.png"};

    panel.setBorder(resources.cr.bordeAzul("Select Table"));

    tittle = new JLabel();
    tittle.setFont(resources.cr.BIG);
    tittle.setForeground(resources.cr.ROJO);

    message = new JLabel();
    message.setFont(resources.cr.BIG);
    message.setForeground(resources.cr.AZUL);

    IntStream.range(0, tablas.length).forEach(i -> {
      tablas[i] = new JLabel();
      tablas[i].setIcon(new ImageIcon(resources.getImage(images[i])));
      tablas[i].addMouseListener(this);
      row.add(tablas[i]);
    });

    panel.add(tittle, BorderLayout.EAST);
    panel.add(row, BorderLayout.CENTER);
    panel.add(message, BorderLayout.WEST);

    return panel;
  }

  private void createComponents() {

    add(getPanel(), BorderLayout.SOUTH);
    setIconImage(new ImageIcon(resources.getImage("adivinar.png")).getImage());

    Arrays.stream(columns).forEach(column -> model.addColumn(column));

    guessNumberTab = new JTable(model);
    guessNumberTab.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(guessNumberTab);
    add(scroll, BorderLayout.CENTER);

    int[] sizes = {20, 200, 20, 20, 100};
    IntStream.range(0, guessNumberTab.getColumnCount())
        .forEach(i -> guessNumberTab.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]));

    JMenuBar menuBar = new JMenuBar();

    JMenu crud = resources.getMenu("CRUD", "mysql");
    create = resources.getMenuItem("Create", "create", this);
    read = resources.getMenuItem("Read", "read", this);
    update = resources.getMenuItem("Update", "update", this);
    delete = resources.getMenuItem("Delete", "delete", this);

    crud.add(create);
    crud.addSeparator();
    crud.add(read);
    crud.addSeparator();
    crud.add(update);
    crud.addSeparator();
    crud.add(delete);

    menuBar.add(crud);
    add(menuBar, BorderLayout.NORTH);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    pack();

    TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(model);
    guessNumberTab.setRowSorter(rowSorter);

    IntStream.range(0, columns.length).forEach(i -> {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      guessNumberTab.getColumnModel().getColumn(i).setCellRenderer(tcr);
    });

    repaint();
  }

  public void cleanTable() {

    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
  }

  private void btnConsultAP() {

    if (guessNumberTab.getRowCount() != 0) {

      Object option;

      option = JOptionPane.showInputDialog(null,
          "<html>" + resources.cr.styleJOption()
              + "<strong><em>What are you looking for?</em></strong></html>",
          "Search records", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"ID", "Nickname"},
          "ID");

      if (option != null) {

        try {

          if (option.toString().equals("ID")) {
            searchResult(90, Querys.getDataByID(Format.tableName(GUESS_NUMBER)));
            setVisible(true);
          } else if (option.toString().equals("Nickname")) {
            searchResult(250, Querys.getDataByNickname(Format.tableName(GUESS_NUMBER)));
            setVisible(true);
          }

        } catch (Exception e) {
          Alerts.error(e, GUESS_NUMBER);
        }
      }

    } else {
      Alerts.message("Consult", "Empty table");
    }
  }

  private void btnUpdateAP() {

    if (guessNumberTab.getRowCount() != 0) {

      int selectedRow = guessNumberTab.getSelectedRow();

      if (selectedRow == -1) {
        Alerts.message("Update", "No record selected");
      } else {

        if (resources.cr.loginDBA()) {

          try {
            resources.db.updateData(resources.cr.ingreseNickname("Enter a Nickname", 20),
                String.valueOf(model.getValueAt(selectedRow, 0)), Format.tableName(GUESS_NUMBER));
          } catch (SQLException e) {
            e.printStackTrace();
          }

          dispose();
          new Index().guessNumberTableAP();
        }
      }

    } else {
      Alerts.message("Update", "Empty table");
    }
  }

  private void btnDeleteAP() {

    if (guessNumberTab.getRowCount() != 0) {

      if (guessNumberTab.getSelectedRow() == -1) {
        resources.cr.mostrarMensaje("Delete", "No record selected");
      } else {

        int[] rows = guessNumberTab.getSelectedRows();
        String[] IDs = new String[rows.length];

        for (int i = 0; i < rows.length; i++) {
          IDs[i] = String.valueOf(model.getValueAt(rows[i], 0));
        }

        if (resources.cr.loginDBA()) {
          try {
            resources.db.deleteData(IDs, Format.tableName(GUESS_NUMBER));
          } catch (SQLException e) {
            e.printStackTrace();
          }

          dispose();
          new Index().guessNumberTableAP();
        }
      }

    } else {
      resources.cr.mostrarMensaje("Delete", "Empty table");
    }
  }

  public void btnCreateAP() {

    Object option;

    option = JOptionPane.showInputDialog(null, "<html>" + resources.cr.styleJOption()
            + "<strong><em>Select difficulty</em></strong></html>",
        "Difficulty level", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Easy", "Hard"},
        "Easy");

    if (option != null) {

      if (option.toString().equals("Easy")) {
        setVisible(false);
        new GuessNumber(this, true, false).start(this);
      } else if (option.toString().equals("Hard")) {
        setVisible(false);
        new GuessNumber(this, true, true).start(this);
      }
    }
  }

  public void searchResult(int size, String query) {

    ResultadoTabla table = new ResultadoTabla(this, true, columns);

    table.limpiarTabla((DefaultTableModel) table.resultadoTab.getModel());

    try {

      if (resources.db.readTable(table.resultadoTab, query, false)) {
        table.setBounds(0, 0, 780, size);
        table.setResizable(false);
        table.setLocationRelativeTo(null);
        table.setTitle("Search result");
        setVisible(false);
        table.setVisible(true);
      }

    } catch (Exception e1) {
      Alerts.error(e1, GUESS_NUMBER);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == delete) {
      btnDeleteAP();
    } else if (e.getSource() == update) {
      btnUpdateAP();
    } else if (e.getSource() == read) {
      btnConsultAP();
    } else if (e.getSource() == create) {
      btnCreateAP();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == tablas[0]) {
      Alerts.message("Message", "You're here!");
    } else if (e.getSource() == tablas[1]) {
      setVisible(false);
      new Index().ahorcadoTableAP();
    } else if (e.getSource() == tablas[2]) {
      setVisible(false);
      new Index().DadosTableAP();
    } else if (e.getSource() == tablas[3]) {
      setVisible(false);
      new Index().NotasTableAP();
    } else if (e.getSource() == tablas[4]) {
      setVisible(false);
      new Index().RompecabezasTableAP();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == tablas[0]) {
      tablas[0].setCursor(resources.cr.MIRA);
      tittle.setText("    Adivinar Número");
      message.setText("       You're here");
    } else if (e.getSource() == tablas[1]) {
      tablas[1].setCursor(resources.cr.CARGAR);
      tittle.setText("    Ahorcadito");
    } else if (e.getSource() == tablas[2]) {
      tablas[2].setCursor(resources.cr.REDI);
      tittle.setText("    Juego de Dados");
    } else if (e.getSource() == tablas[3]) {
      tablas[3].setCursor(resources.cr.TEXT);
      tittle.setText("    Notas");
    } else if (e.getSource() == tablas[4]) {
      tablas[4].setCursor(resources.cr.MANO);
      tittle.setText("    Rompecabezas");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == tablas[0]) {
      tittle.setText("");
      message.setText("");
    } else if (e.getSource() == tablas[1]) {
      tittle.setText("");
    } else if (e.getSource() == tablas[2]) {
      tittle.setText("");
    } else if (e.getSource() == tablas[3]) {
      tittle.setText("");
    } else if (e.getSource() == tablas[4]) {
      tittle.setText("");
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
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
}                              