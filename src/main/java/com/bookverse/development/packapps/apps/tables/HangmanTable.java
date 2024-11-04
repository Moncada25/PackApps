package com.bookverse.development.packapps.apps.tables;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.LinkedHashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.database.Queries;
import com.bookverse.development.packapps.apps.hangman.HangmanView;
import com.bookverse.development.packapps.utils.ui.factory.MenuItem;

@Data
@EqualsAndHashCode(callSuper = true)
public class HangmanTable extends JDialog implements MouseListener {

  private DefaultTable defaultTable = new DefaultTable();
  private JTable viewTable = new JTable(defaultTable);
  private LinkedHashMap<String, Integer> columns = new LinkedHashMap<>();

  public HangmanTable(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public HangmanTable(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    columns.put("ID", 20);
    columns.put("NICKNAME", 100);
    columns.put("MISTAKES", 40);
    columns.put("STATE", 20);
    columns.put("CATEGORY", 75);
    columns.put("DATE", 120);

    defaultTable.createTable(this, "ahorcado.png", columns, this, viewTable);

    JMenuItem create = new MenuItem().setText("Create").setImage("create").build();
    create.addActionListener(e -> {
      setVisible(false);
      new HangmanView(this, true).start(this);
    });
    JMenuItem read = new MenuItem().setText("Read").setImage("read").build();
    read.addActionListener(e -> btnConsultHangmanTable());
    JMenuItem update = new MenuItem().setText("Update").setImage("update").build();
    update.addActionListener(e -> btnUpdateHangmanTable());
    JMenuItem delete = new MenuItem().setText("Delete").setImage("delete").build();
    delete.addActionListener(e -> btnDeleteHangmanTable());

    defaultTable.createCrud(this, columns, viewTable, create, read, update, delete);
  }

  public void cleanTable() {

    while (defaultTable.getRowCount() > 0) {
      defaultTable.removeRow(0);
    }
  }

  public void openTable() {

    cleanTable();

    try {
      OlderRepository.readTable(
          viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.HANGMAN)),
          true
      );
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.HANGMAN);
    }

    setSize(900, 400);
    setLocationRelativeTo(null);
    setMinimumSize(new Dimension(900, 400));
    setMaximumSize(new Dimension(1280, 720));
    setTitle(DatabaseConstants.HANGMAN + " Information");
    Effects.fadeIn(this);
    setVisible(true);
  }

  private void btnConsultHangmanTable() {

    if (viewTable.getRowCount() != 0) {

      Object option = Alerts.searchRecords();

      if (option != null) {

        try {

          if (option.toString().equals("ID")) {
            defaultTable.searchResult(this, 90, Queries.getDataByID(Format.tableName(
                DatabaseConstants.HANGMAN)), columns);
            setVisible(true);
          } else if (option.toString().equals("Nickname")) {
            defaultTable.searchResult(this, 250, Queries.getDataByNickname(Format.tableName(
                DatabaseConstants.HANGMAN)), columns);
            setVisible(true);
          }

        } catch (Exception e) {
          Alerts.error(e, DatabaseConstants.HANGMAN);
        }
      }

    } else {
      Alerts.message("Consult", "Empty table");
    }
  }

  private void btnUpdateHangmanTable() {

    if (viewTable.getRowCount() != 0) {

      int selectedRow = viewTable.getSelectedRow();

      if (selectedRow == -1) {
        Alerts.message("Update", "No record selected");
      } else {

        if (GeneralUtils.loginDBA()) {
          OlderRepository.updateData(Alerts.inputText("Enter a Nickname", 20),
              String.valueOf(defaultTable.getValueAt(selectedRow, 0)), Format.tableName(
                  DatabaseConstants.HANGMAN));

          dispose();
          openTable();
        }
      }

    } else {
      Alerts.message("Update", "Empty table");
    }
  }

  private void btnDeleteHangmanTable() {

    if (viewTable.getRowCount() != 0) {

      if (viewTable.getSelectedRow() == -1) {
        Alerts.message("Delete", "No record selected");
      } else {

        int[] rows = viewTable.getSelectedRows();
        String[] allIds = Arrays.stream(rows).mapToObj(row ->
            String.valueOf(defaultTable.getValueAt(row, 0))
        ).toArray(String[]::new);

        if (GeneralUtils.loginDBA()) {
          OlderRepository.deleteData(allIds, Format.tableName(DatabaseConstants.HANGMAN));
          dispose();
          openTable();
        }
      }

    } else {
      Alerts.message("Delete", "Empty table");
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == defaultTable.getTables()[0]) {
      setVisible(false);
      new GuessNumberTable(this, true).openTable();
    } else if (e.getSource() == defaultTable.getTables()[1]) {
      Alerts.message("Message", "You're here!");
    } else if (e.getSource() == defaultTable.getTables()[2]) {
      setVisible(false);
      new DicesTable(this, true).openTable(this);
    } else if (e.getSource() == defaultTable.getTables()[3]) {
      setVisible(false);
      new NotesTable(this, true).openTable();
    } else if (e.getSource() == defaultTable.getTables()[4]) {
      setVisible(false);
      new PuzzleTable(this, true).openTable();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    int index = 1;

    if (e.getSource() == defaultTable.getTables()[0]) {
      index = 0;
    } else if (e.getSource() == defaultTable.getTables()[1]) {
      defaultTable.getLblMessage().setText("       You're here");
    } else if (e.getSource() == defaultTable.getTables()[2]) {
      index = 2;
    } else if (e.getSource() == defaultTable.getTables()[3]) {
      index = 3;
    } else if (e.getSource() == defaultTable.getTables()[4]) {
      index = 4;
    }

    defaultTable.getLblTitleTable().setText("    " + defaultTable.getTableTitles()[index]);
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == defaultTable.getTables()[1]) {
      defaultTable.getLblMessage().setText("");
    }

    defaultTable.getLblTitleTable().setText("");
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }
}