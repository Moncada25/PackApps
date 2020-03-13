package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.models.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class UsuariosTabla extends JDialog implements MouseListener {

  public JTable usuariosTab;
  private JLabel titulo, men;
  private JLabel[] tablas = new JLabel[3];
  private JScrollPane scroll;
  private Table modelo = new Table();
  private TableRowSorter<TableModel> ordenar;
  private String[] columnas = {"USERNAME", "STATUS"};
  private Resources img = new Resources();

  // Constructor que recibe la ventana padre y el valor modal
  public UsuariosTabla(JDialog parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  // Constructor que no recibe parámetros
  public UsuariosTabla() {

    componentes();
  }

  public JPanel getPanel() {

    JPanel panel = new JPanel(new GridLayout());

    JPanel fila = new JPanel(new FlowLayout());

    String[] imgs = {"añadir_usuario.png", "editar_usuario.png", "eliminar_usuario.png"};

    panel.setBorder(img.appConfig.getBorder("Select Action"));

    titulo = new JLabel();
    titulo.setFont(img.appConfig.BIG);
    titulo.setForeground(img.appConfig.MAIN_COLOR);

    men = new JLabel();
    men.setFont(img.appConfig.BIG);
    men.setForeground(img.appConfig.TEXT_COLOR);

    /* ICONOS */
    for (int i = 0; i < tablas.length; i++) {

      tablas[i] = new JLabel();
      tablas[i].setIcon(new ImageIcon(img.getImage(imgs[i])));
      tablas[i].addMouseListener(this);
      fila.add(tablas[i]);
    }

    panel.add(titulo, BorderLayout.EAST);
    panel.add(fila, BorderLayout.CENTER);
    panel.add(men, BorderLayout.WEST);

    return panel;
  }

  // Crea los componentes de la tabla
  private void componentes() {

    setIconImage(new ImageIcon(img.getImage("usuario.png")).getImage());
    add(getPanel(), BorderLayout.SOUTH);

    /* TABLA */
    for (int i = 0; i < columnas.length; i++) {
      modelo.addColumn(columnas[i]);
    }

    usuariosTab = new JTable(modelo);
    usuariosTab.getTableHeader().setReorderingAllowed(false);
    scroll = new JScrollPane(usuariosTab);
    add(scroll, BorderLayout.CENTER);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    pack();

    ordenar = new TableRowSorter<TableModel>(modelo);
    usuariosTab.setRowSorter(ordenar);

    for (int i = 0; i < columnas.length; i++) {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      usuariosTab.getColumnModel().getColumn(i).setCellRenderer(tcr);
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

  private void editarUsuario() {

    if (usuariosTab.getRowCount() != 0) {

      int filaseleccionada = usuariosTab.getSelectedRow();

      boolean isRoot = false;

      if (filaseleccionada != -1) {

        if (String.valueOf(modelo.getValueAt(filaseleccionada, 0)).equals("root")) {
          isRoot = true;
        }
      }

      if (!isRoot) {

        if (filaseleccionada == -1) {

          JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún usuario", "Update",
              JOptionPane.PLAIN_MESSAGE);

        } else {

          String user = String.valueOf(modelo.getValueAt(filaseleccionada, 0));

          try {

            if (img.database.buscarEmpleado(user, img.appConfig
                .Encrypt(img.appConfig.loginUser(), true))) {

              Object opcion = null;

              opcion = JOptionPane.showInputDialog(null,
                  "<html>" + img.appConfig.styleJOption()
                      + "<strong><em>¿Qué quiere actualizar?</em></strong></html>",
                  "Update Data", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Password"},
                  "Password");

              if (opcion != null) {

                if (opcion.toString().equals("Password")) {
                  img.database.updatePassword(user, img.appConfig
                      .Encrypt(img.appConfig.newPassword(), true));
                  JOptionPane.showMessageDialog(null,
                      "<html>" + img.appConfig.styleJOption()
                          + "<strong>Contraseña actualizada</strong></html>",
                      "¡Éxito!", JOptionPane.PLAIN_MESSAGE);
                }
              }

            } else {
              JOptionPane.showMessageDialog(null,
                  "<html>" + img.appConfig.styleJOption()
                      + "<strong>Contraseña incorrecta</strong></html>",
                  "Error", JOptionPane.PLAIN_MESSAGE);
            }
          } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
          }
        }

      } else {
        JOptionPane.showMessageDialog(null, "No es posible editar al usuario root", "Access denied",
            JOptionPane.PLAIN_MESSAGE);
      }

    } else {
      JOptionPane.showMessageDialog(null, "Tabla vacía", "Update", JOptionPane.PLAIN_MESSAGE);
    }
  }

  private void eliminarUsuario() {

    if (usuariosTab.getRowCount() != 0) {

      int[] rows = usuariosTab.getSelectedRows();
      boolean isOnline = false;
      String userOnline = "";

      for (int i = 0; i < rows.length; i++) {

        if (String.valueOf(modelo.getValueAt(rows[i], 0)).equals("root")
            || String.valueOf(modelo.getValueAt(rows[i], 1)).equals("Online")) {
          isOnline = true;
          userOnline = String.valueOf(modelo.getValueAt(rows[i], 0));
        }
      }

      if (!isOnline) {

        if (usuariosTab.getSelectedRow() == -1) {

          JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún usuario", "Delete",
              JOptionPane.PLAIN_MESSAGE);

        } else {

          String[] IDs = new String[rows.length];

          for (int i = 0; i < rows.length; i++) {
            try {
              IDs[i] = String
                  .valueOf(img.database.getIDUser(String.valueOf(modelo.getValueAt(rows[i], 0))));
            } catch (SQLException e) {
              e.printStackTrace();
            }
          }

          if (img.appConfig.loginDBA()) {
            img.database.deleteData(IDs, "usuarios");

            dispose();
            new Opciones().btnUsuariosAP();
          }
        }

      } else {
        JOptionPane.showMessageDialog(null, "No es posible eliminar al usuario " + userOnline,
            "Access denied",
            JOptionPane.PLAIN_MESSAGE);
      }

    } else {
      JOptionPane.showMessageDialog(null, "Tabla vacía", "Delete", JOptionPane.PLAIN_MESSAGE);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == tablas[0]) {
      setVisible(false);
      new Login().btnRegistrarAP();
      new Opciones().btnUsuariosAP();
    } else if (e.getSource() == tablas[1]) {
      editarUsuario();
    } else if (e.getSource() == tablas[2]) {
      eliminarUsuario();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == tablas[0]) {
      tablas[0].setCursor(img.appConfig.HAND);
      titulo.setText("Add User");
    } else if (e.getSource() == tablas[1]) {
      tablas[1].setCursor(img.appConfig.HAND);
      titulo.setText("Edit User");
    } else if (e.getSource() == tablas[2]) {
      tablas[2].setCursor(img.appConfig.HAND);
      titulo.setText("Delete User");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == tablas[0]) {
      titulo.setText("");
    } else if (e.getSource() == tablas[1]) {
      titulo.setText("");
    } else if (e.getSource() == tablas[2]) {
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