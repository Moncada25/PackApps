package com.bookverse.packapps.apps.tables;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.LinkedHashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.bookverse.packapps.utils.constants.DatabaseConstants;
import com.bookverse.packapps.utils.GeneralUtils;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.repositories.OlderRepository;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.database.Queries;
import com.bookverse.packapps.utils.ui.factory.MenuItem;
import com.bookverse.packapps.apps.guessnumber.GuessNumberView;

@Data
@EqualsAndHashCode(callSuper = true)
public class GuessNumberTable extends JDialog implements MouseListener {

  private DefaultTable defaultTable = new DefaultTable();
  private JTable viewTable = new JTable(defaultTable);
  private LinkedHashMap<String, Integer> columns = new LinkedHashMap<>();
  
  public GuessNumberTable(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public GuessNumberTable(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    columns.put("ID", 20);
    columns.put("NICKNAME", 200);
    columns.put("LIMIT", 20);
    columns.put("LEVEL", 20);
    columns.put("DATE", 120);
    
    defaultTable.createTable(this, "adivinar.png", columns, this, viewTable);

    JMenuItem create = new MenuItem().setText("Create").setImage("create").build();
    create.addActionListener(e -> btnCreateGuessNumberTable());
    JMenuItem read = new MenuItem().setText("Read").setImage("read").build();
    read.addActionListener(e -> btnConsultGuessNumberTable());
    JMenuItem update = new MenuItem().setText("Update").setImage("update").build();
    update.addActionListener(e -> btnUpdateGuessNumberTable());
    JMenuItem delete = new MenuItem().setText("Delete").setImage("delete").build();
    delete.addActionListener(e -> btnDeleteGuessNumberTable());

    defaultTable.createCrud(this, columns, viewTable, create, read, update, delete);
  }

  public void cleanTable() {

    while (defaultTable.getRowCount() > 0) {
      defaultTable.removeRow(0);
    }
  }

  private void btnConsultGuessNumberTable() {

    if (viewTable.getRowCount() != 0) {

      Object option = Alerts.searchRecords();

      if (option != null) {

        try {

          if (option.toString().equals("ID")) {
            defaultTable.searchResult(this, 90, Queries.getDataByID(Format.tableName(
                DatabaseConstants.GUESS_NUMBER)), columns);
            setVisible(true);
          } else if (option.toString().equals("Nickname")) {
            defaultTable.searchResult(this, 250, Queries.getDataByNickname(Format.tableName(
                DatabaseConstants.GUESS_NUMBER)), columns);
            setVisible(true);
          }

        } catch (Exception e) {
          Alerts.error(e, DatabaseConstants.GUESS_NUMBER);
        }
      }

    } else {
      Alerts.message("Consult", "Empty table");
    }
  }

  private void btnUpdateGuessNumberTable() {

    if (viewTable.getRowCount() != 0) {

      int selectedRow = viewTable.getSelectedRow();

      if (selectedRow == -1) {
        Alerts.message("Update", "No record selected");
      } else {

        if (GeneralUtils.loginDBA()) {

          OlderRepository.updateData(Alerts.inputText("Enter a Nickname", 20),
              String.valueOf(defaultTable.getValueAt(selectedRow, 0)), Format.tableName(
                  DatabaseConstants.GUESS_NUMBER));

          dispose();
          openTable();
        }
      }

    } else {
      Alerts.message("Update", "Empty table");
    }
  }

  private void btnDeleteGuessNumberTable() {

    if (viewTable.getRowCount() != 0) {

      if (viewTable.getSelectedRow() == -1) {
        Alerts.message("Delete", "No record selected");
      } else {

        int[] rows = viewTable.getSelectedRows();
        String[] allIds = Arrays.stream(rows).mapToObj(row ->
            String.valueOf(defaultTable.getValueAt(row, 0))
        ).toArray(String[]::new);

        if (GeneralUtils.loginDBA()) {
          OlderRepository.deleteData(allIds, Format.tableName(DatabaseConstants.GUESS_NUMBER));
          dispose();
          openTable();
        }
      }

    } else {
      Alerts.message("Delete", "Empty table");
    }
  }

  public void btnCreateGuessNumberTable() {

    Object option = JOptionPane.showInputDialog(null, "<html>" + Format.style()
            + "<strong><em>Select difficulty</em></strong></html>",
        "Difficulty level", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Easy", "Hard"},
        "Easy");

    if (option != null) {

      switch (option.toString()) {
        case "Easy":
          setVisible(false);
          new GuessNumberView(this, true, false).start(this);
          setVisible(true);
          break;
        case "Hard":
          setVisible(false);
          new GuessNumberView(this, true, true).start(this);
          setVisible(true);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + option);
      }
    }
  }

  public void openTable() {

    cleanTable();

    try {
      OlderRepository.readTable(
          viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)),
          true
      );
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.GUESS_NUMBER);
    }

    setSize(900, 400);
    setLocationRelativeTo(null);
    setMinimumSize(new Dimension(900, 400));
    setMaximumSize(new Dimension(1280, 720));
    setTitle(DatabaseConstants.GUESS_NUMBER + " Information");
    Effects.fadeIn(this);
    setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == defaultTable.getTables()[0]) {
      Alerts.message("Message", "You're here!");
    }

    if (e.getSource() == defaultTable.getTables()[1]) {
      setVisible(false);
      new HangmanTable(this, true).openTable();
    }

    if (e.getSource() == defaultTable.getTables()[2]) {
      setVisible(false);
      new DicesTable(this, true).openTable(this);
    }

    if (e.getSource() == defaultTable.getTables()[3]) {
      setVisible(false);
      new NotesTable(this, true).openTable();
    }

    if (e.getSource() == defaultTable.getTables()[4]) {
      setVisible(false);
      new PuzzleTable(this, true).openTable();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    int index = 0;

    if (e.getSource() == defaultTable.getTables()[0]) {
      defaultTable.getLblMessage().setText("       You're here");
    } else if (e.getSource() == defaultTable.getTables()[1]) {
      index = 1;
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

    if (e.getSource() == defaultTable.getTables()[0]) {
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