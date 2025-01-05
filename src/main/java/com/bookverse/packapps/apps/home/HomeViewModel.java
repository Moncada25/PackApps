package com.bookverse.packapps.apps.home;

import com.bookverse.packapps.apps.tables.DicesTable;
import com.bookverse.packapps.apps.tables.GuessNumberTable;
import com.bookverse.packapps.apps.tables.HangmanTable;
import com.bookverse.packapps.apps.tables.InventoryTable;
import com.bookverse.packapps.apps.tables.LoansTable;
import com.bookverse.packapps.apps.tables.NotesTable;
import com.bookverse.packapps.views.older.store.CashRegisterTable;
import com.bookverse.packapps.views.older.store.PurchasesTable;
import com.bookverse.packapps.apps.tables.PuzzleTable;
import com.bookverse.packapps.views.older.store.SalesTable;
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
