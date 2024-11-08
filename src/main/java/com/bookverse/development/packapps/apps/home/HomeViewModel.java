package com.bookverse.development.packapps.apps.home;

import com.bookverse.development.packapps.apps.tables.DicesTable;
import com.bookverse.development.packapps.apps.tables.GuessNumberTable;
import com.bookverse.development.packapps.apps.tables.HangmanTable;
import com.bookverse.development.packapps.apps.tables.InventoryTable;
import com.bookverse.development.packapps.apps.tables.LoansTable;
import com.bookverse.development.packapps.apps.tables.NotesTable;
import com.bookverse.development.packapps.views.older.store.CashRegisterTable;
import com.bookverse.development.packapps.views.older.store.PurchasesTable;
import com.bookverse.development.packapps.apps.tables.PuzzleTable;
import com.bookverse.development.packapps.views.older.store.SalesTable;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import lombok.Data;

@Data
public class HomeViewModel {
  private JLabel welcome;
  private JMenuItem[] wallpapers = new JMenuItem[14];
  private JMenuItem darkMode;
  private JMenuItem defaultMode;
  private JMenuItem textureMode;
  private JMenuItem mintMode;
  private JMenuItem classicMode;
  private JMenuItem macMode;
  private JMenuItem grayMode;
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
}
