package com.bookverse.development.packapps.apps.store.home;

import java.awt.Dimension;
import javax.swing.JDialog;
import lombok.Data;
import com.bookverse.development.packapps.apps.tables.InventoryTable;
import com.bookverse.development.packapps.apps.tables.LoansTable;
import com.bookverse.development.packapps.database.Queries;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.views.older.store.CashRegisterTable;
import com.bookverse.development.packapps.views.older.store.PurchasesTable;
import com.bookverse.development.packapps.views.older.store.SalesTable;
import com.bookverse.development.packapps.views.older.store.UsersTable;

@Data
public class HomeService {

  private String userLogged;

  public void clickOnCashRegister(JDialog parent) {

    CashRegisterTable table = new CashRegisterTable(parent, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(DatabaseConstants.CASH_REGISTER), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(950, 400);
    table.setMinimumSize(new Dimension(950, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(DatabaseConstants.CASH_REGISTER);
    Effects.fadeIn(table);
    parent.setVisible(false);
    table.setVisible(true);
  }

  public void clickOnInventoryTable(JDialog parent, boolean search) {

    InventoryTable table = new InventoryTable(parent, true, search);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(DatabaseConstants.INVENTORY), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle("Available products");
    Effects.fadeIn(table);
    parent.setVisible(false);
    table.setVisible(true);
  }

  public void clickOnLoansTable(JDialog parent) {

    LoansTable table = new LoansTable(parent, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(DatabaseConstants.LOANS), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(DatabaseConstants.LOANS);
    Effects.fadeIn(table);
    parent.setVisible(false);
    table.setVisible(true);
  }

  public void clickOnPurchasesTable(JDialog parent) {

    PurchasesTable table = new PurchasesTable(parent, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(DatabaseConstants.PURCHASES), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(DatabaseConstants.PURCHASES);
    Effects.fadeIn(table);
    parent.setVisible(false);
    table.setVisible(true);
  }

  public void clickOnSalesTable(JDialog parent) {

    SalesTable table = new SalesTable(parent, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(DatabaseConstants.SALES), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(850, 400);
    table.setMinimumSize(new Dimension(850, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(DatabaseConstants.SALES);
    Effects.fadeIn(table);
    parent.setVisible(false);
    table.setVisible(true);
  }

  public void clickOnUsersTable(JDialog parent) {

    UsersTable table = new UsersTable(parent, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(DatabaseConstants.USERS), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(620, 410);
    table.setMinimumSize(new Dimension(620, 410));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(DatabaseConstants.USERS);
    Effects.fadeIn(table);
    parent.setVisible(false);
    table.setVisible(true);
  }

  public void clickOnLogout(JDialog parent) {
    OlderRepository.recordLogin("Offline", userLogged);
    Effects.fadeOut(parent);
  }
}
