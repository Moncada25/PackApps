package com.bookverse.development.packapps.apps.home;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
import javax.swing.JFrame;
import lombok.Data;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.apps.tables.DicesTable;
import com.bookverse.development.packapps.apps.tables.GuessNumberTable;
import com.bookverse.development.packapps.apps.tables.HangmanTable;
import com.bookverse.development.packapps.apps.tables.InventoryTable;
import com.bookverse.development.packapps.apps.tables.LoansTable;
import com.bookverse.development.packapps.apps.tables.NotesTable;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.constants.Queries;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.apps.views.older.CashRegisterTable;
import com.bookverse.development.packapps.apps.views.older.PurchasesTable;
import com.bookverse.development.packapps.apps.views.older.PuzzleTable;
import com.bookverse.development.packapps.apps.views.older.SalesTable;

@Data
public class HomeService {

  private HangmanTable hangmanTable;
  private GuessNumberTable guessNumberTable;
  private PuzzleTable puzzleTable;
  private DicesTable dicesTable;
  private NotesTable notesTable;
  private InventoryTable inventoryTable;
  private CashRegisterTable cashRegisterTable;
  private LoansTable loansTable;
  private PurchasesTable purchasesTable;
  private SalesTable salesTable;

  public void setTables(JFrame parent) {
    hangmanTable = new HangmanTable(parent, true);
    guessNumberTable = new GuessNumberTable(parent, true);
    puzzleTable = new PuzzleTable(parent, true);
    dicesTable = new DicesTable(parent, true);
    notesTable = new NotesTable(parent, true);
    inventoryTable = new InventoryTable(parent, true);
    cashRegisterTable = new CashRegisterTable(parent, true);
    loansTable = new LoansTable(parent, true);
    purchasesTable = new PurchasesTable(parent, true);
    salesTable = new SalesTable(parent, true);
  }

  public void guessNumberTableAP() {

    guessNumberTable.cleanTable();

    try {
      OlderRepository.readTable(
          guessNumberTable.viewTable,
          Queries.getAllData(Format.tableName(DatabaseConstants.GUESS_NUMBER)),
          true
      );
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.GUESS_NUMBER);
    }

    guessNumberTable.setSize(830, 400);
    guessNumberTable.setLocationRelativeTo(null);
    guessNumberTable.setMinimumSize(new Dimension(830, 400));
    guessNumberTable.setMaximumSize(new Dimension(1280, 720));
    guessNumberTable.setTitle(DatabaseConstants.GUESS_NUMBER + " Information");
    Effects.fadeIn(guessNumberTable);
    guessNumberTable.setVisible(true);
  }

  public void hangmanTableAP() {

    hangmanTable.cleanTable();

    try {
      OlderRepository
          .readTable(hangmanTable.viewTable, Queries.getAllData(Format.tableName(
              DatabaseConstants.HANGMAN)), true);
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.HANGMAN);
    }

    hangmanTable.setSize(830, 400);
    hangmanTable.setLocationRelativeTo(null);
    hangmanTable.setMinimumSize(new Dimension(830, 400));
    hangmanTable.setMaximumSize(new Dimension(1280, 720));
    hangmanTable.setTitle(DatabaseConstants.HANGMAN + " Information");
    Effects.fadeIn(hangmanTable);
    hangmanTable.setVisible(true);
  }

  public boolean dicesTableAP(Window parent) {
    boolean aux = false;

    dicesTable.cleanTable();

    try {
      aux = OlderRepository.readTable(dicesTable.viewTable,
          Queries.getAllData(DatabaseConstants.DICES), true);
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.DICES);
    }

    if (aux) {
      parent.setVisible(false);
      dicesTable.setSize(830, 400);
      dicesTable.setLocationRelativeTo(null);
      dicesTable.setMinimumSize(new Dimension(830, 400));
      dicesTable.setMaximumSize(new Dimension(1280, 720));
      dicesTable.setTitle(DatabaseConstants.DICES + " Information");
      Effects.fadeIn(dicesTable);
      dicesTable.setVisible(true);
    }

    return aux;
  }

  public void notesTableAP() {
    notesTable.cleanTable();

    try {
      OlderRepository.readTable(notesTable.viewTable, Queries.getAllData(Format.tableName(
              DatabaseConstants.NOTES)),
          true);
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.NOTES);
    }

    notesTable.setSize(830, 400);
    notesTable.setLocationRelativeTo(null);
    notesTable.setMinimumSize(new Dimension(830, 400));
    notesTable.setMaximumSize(new Dimension(1280, 720));
    notesTable.setTitle(DatabaseConstants.NOTES + " Information");
    Effects.fadeIn(notesTable);
    notesTable.setVisible(true);
  }

  public void puzzleTableAP(Dialog parent) {
    puzzleTable.cleanTable();

    try {
      OlderRepository.readTable(puzzleTable.viewTable, Queries.getAllData(Format.tableName(
              DatabaseConstants.PUZZLE)),
          true);
    } catch (Exception e1) {
      Alerts.error(e1, DatabaseConstants.PUZZLE);
    }

    puzzleTable.setSize(830, 400);
    puzzleTable.setLocationRelativeTo(null);
    puzzleTable.setMinimumSize(new Dimension(830, 400));
    puzzleTable.setMaximumSize(new Dimension(1280, 720));
    puzzleTable.setTitle(DatabaseConstants.PUZZLE + " Information");
    parent.setVisible(false);
    Effects.fadeIn(puzzleTable);
    puzzleTable.setVisible(true);
  }
}
