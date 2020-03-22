package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.HAND;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.getBorder;
import static com.bookverse.development.packapps.core.AppConfig.inputText;
import static com.bookverse.development.packapps.core.AppConfig.loginDBA;
import static com.bookverse.development.packapps.utils.AppConstants.USERS;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.models.Table;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.stream.IntStream;
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

public class UsersTable extends JDialog implements MouseListener {

  public JTable viewTable;
  private JLabel tittle;
  private JLabel[] actions = new JLabel[3];
  private Table model = new Table();
  private String[] columns = {"ID", "USERNAME", "PASSWORD", "STATUS"};
  private Resources resources = new Resources();

  public UsersTable(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new GridLayout());
    JPanel row = new JPanel(new FlowLayout());

    String[] images = {"añadir_usuario.png", "editar_usuario.png", "eliminar_usuario.png"};

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

    panel.add(tittle, BorderLayout.EAST);
    panel.add(row, BorderLayout.CENTER);
    panel.add(message, BorderLayout.WEST);

    return panel;
  }

  private void createComponents() {

    setIconImage(new ImageIcon(resources.getImage("usuario.png")).getImage());
    add(getPanel(), BorderLayout.SOUTH);

    IntStream.range(0, columns.length).forEach(i -> model.addColumn(columns[i]));

    viewTable = new JTable(model);
    viewTable.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(viewTable);
    add(scroll, BorderLayout.CENTER);

    int[] sizes = {30, 70, 130, 30};
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

  private void editUser() {

    int selectedRow = viewTable.getSelectedRow();

    if (selectedRow == -1) {
      Alerts.message("Message", "No record selected.");
    } else {

      if (String.valueOf(model.getValueAt(selectedRow, 1)).equals("root")) {
        Alerts.message("Access denied", "Cannot edit root user");
      } else {

        String user = String.valueOf(model.getValueAt(selectedRow, 1));

        try {

          if (Database.searchUserRegister(user,
              AppConfig.encrypt(Alerts.inputPassword("Enter password"), true))) {

            Object option = null;

            option = JOptionPane.showInputDialog(null,
                "<html>" + Format.style()
                    + "<strong><em>What do you want to update?</em></strong></html>",
                "Update data", JOptionPane.PLAIN_MESSAGE, null,
                new Object[]{"Password", "Username"},
                "Password");

            if (option != null) {

              if (option.toString().equals("Password")) {

                String newPassword = Alerts.inputPassword("Enter new password");

                if (!Format.verifyCredentials(newPassword)) {
                  Alerts.message("Message", "The password are too weak, please try again.");
                } else {

                  Database.updatePassword(user, AppConfig.encrypt(newPassword, true));
                  Alerts.message("Success", "Password updated!");
                  dispose();
                  new HomeStore().btnUsersTableAP();
                }

              } else if (option.toString().equals("Username")) {

                String newUsername = AppConfig.inputText("Enter new username", 20);
                if (!Format.verifyCredentials(newUsername)){
                  Alerts.message("Message", "The username are too weak, please try again.");
                }else{

                  Database.updateUsername(user, newUsername);
                  Alerts.message("Success", "Username updated!");
                  dispose();
                  new HomeStore().btnUsersTableAP();
                }
              }
            }

          } else {
            JOptionPane.showMessageDialog(null,
                "<html>" + Format.style()
                    + "<strong>Incorrect password</strong></html>",
                "Verify!", JOptionPane.PLAIN_MESSAGE);
          }
        } catch (HeadlessException e) {
          Alerts.error(e, "Users table");
        }
      }
    }
  }

  private void deleteUser() {

    int[] selectedRows = viewTable.getSelectedRows();
    boolean isOnline = false;
    String userOnline = "";

    if (viewTable.getSelectedRow() == -1) {
      Alerts.message("Message", "No record selected.");
    } else {

      for (int selectedRow : selectedRows) {

        if (String.valueOf(model.getValueAt(selectedRow, 1)).equals("root") || String
            .valueOf(model.getValueAt(selectedRow, 3)).equals("Online")) {
          isOnline = true;
          userOnline = String.valueOf(model.getValueAt(selectedRow, 1));
        }
      }

      if (!isOnline) {

        if (viewTable.getSelectedRow() == -1) {
          Alerts.message("Message", "No record selected.");
        } else {

          String[] IDs = Arrays.stream(selectedRows).mapToObj(selectedRow ->
              String.valueOf(model.getValueAt(selectedRow, 0))).toArray(String[]::new);

          if (AppConfig.loginDBA()) {
            Database.deleteData(IDs, USERS);
            dispose();
            new HomeStore().btnUsersTableAP();
          }
        }

      } else {
        Alerts.message("Access denied", "User "+userOnline+" cannot be deleted.");
      }
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      setVisible(false);
      new SignUp(this, true).start(this);
      new HomeStore().btnUsersTableAP();
    } else if (e.getSource() == actions[1]) {
      editUser();
    } else if (e.getSource() == actions[2]) {
      deleteUser();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      actions[0].setCursor(HAND);
      tittle.setText("Add User");
    } else if (e.getSource() == actions[1]) {
      actions[1].setCursor(HAND);
      tittle.setText("Edit User");
    } else if (e.getSource() == actions[2]) {
      actions[2].setCursor(HAND);
      tittle.setText("Delete User");
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
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}