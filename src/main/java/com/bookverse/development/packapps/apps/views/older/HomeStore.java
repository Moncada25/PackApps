package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.LOANS;
import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.PURCHASES;
import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.SALES;
import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.USERS;

import com.bookverse.development.packapps.apps.tables.InventoryTable;
import com.bookverse.development.packapps.apps.tables.LoansTable;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.constants.Queries;
import com.bookverse.development.packapps.utils.ui.Effects;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HomeStore extends JDialog implements ActionListener {

  
  private static String userLogged;
  private JMenuItem sell, buy, lend, cashRegister, inventory, loans, purchases, sales, users, exit;

  public HomeStore(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public HomeStore() {
    createComponents();
  }

  private void createComponents() {

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    ImageIcon wallpaper = new ImageIcon(Resources.getImage("tienda.jpg"));
    JLabel welcome = new JLabel();
    welcome.setIcon(wallpaper);
    welcome.setSize(620, 300);
    add(welcome, BorderLayout.CENTER);

    JMenuBar menuBar = new JMenuBar();

    JMenu actions = Resources.getMenu("Services", "services");
    buy = Resources.getMenuItem("Buy", "comprar", this);
    lend = Resources.getMenuItem("Lend", "prestar", this);
    sell = Resources.getMenuItem("Sell", "vender", this);

    actions.add(buy);
    actions.addSeparator();
    actions.add(lend);
    actions.addSeparator();
    actions.add(sell);

    JMenu records = Resources.getMenu("Records", "data");
    inventory = Resources.getMenuItem(INVENTORY, "inventario", this);
    cashRegister = Resources.getMenuItem(CASH_REGISTER, "registradora", this);
    loans = Resources.getMenuItem(LOANS, "prestamos", this);
    purchases = Resources.getMenuItem(PURCHASES, "comprar", this);
    sales = Resources.getMenuItem(SALES, "vender", this);
    users = Resources.getMenuItem(USERS, "usuario", this);

    records.add(purchases);
    records.addSeparator();
    records.add(inventory);
    records.addSeparator();
    records.add(loans);
    records.addSeparator();
    records.add(cashRegister);
    records.addSeparator();
    records.add(users);
    records.addSeparator();
    records.add(sales);

    JMenu exit = Resources.getMenu("Logout", "exit");
    this.exit = Resources.getMenuItem("Exit", "logout", this);

    exit.add(this.exit);

    menuBar.add(actions);
    menuBar.add(records);
    menuBar.add(exit);

    add(menuBar, BorderLayout.NORTH);
  }

  public void start(JDialog parent, String user) {
    setUserLogged(user);
    setSize(620, 380);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Welcome " + userLogged + "!");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void btnCashRegistersAP() {

    CashRegisterTable table = new CashRegisterTable(this, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(CASH_REGISTER), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(950, 400);
    table.setMinimumSize(new Dimension(950, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(CASH_REGISTER);
    Effects.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  public void btnInventoryTableAP(boolean search) {

    InventoryTable table = new InventoryTable(this, true, search);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(INVENTORY), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle("Available products");
    Effects.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  private void btnLoansTableAP() {

    LoansTable table = new LoansTable(this, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(LOANS), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(LOANS);
    Effects.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  private void btnPurchasesTableAP() {

    PurchasesTable table = new PurchasesTable(this, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(PURCHASES), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(PURCHASES);
    Effects.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  private void btnSalesTableAP() {

    SalesTable table = new SalesTable(this, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(SALES), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(850, 400);
    table.setMinimumSize(new Dimension(850, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(SALES);
    Effects.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  public void btnUsersTableAP() {

    UsersTable table = new UsersTable(this, true);

    table.cleanTable();

    try {
      OlderRepository.readTable(table.viewTable, Queries.getAllData(USERS), true);
    } catch (Exception e1) {
      Alerts.message("Error", e1.getMessage());
    }

    table.setSize(620, 410);
    table.setMinimumSize(new Dimension(620, 410));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(USERS);
    Effects.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  public static String getUserLogged() {
    return userLogged;
  }

  public static void setUserLogged(String user) {
    userLogged = user;
  }

  private void logout() {
    OlderRepository.recordLogin("Offline", userLogged);
    Effects.fadeOut(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == exit) {
      logout();
    } else {

      if (GeneralUtils.verifyConnection("Make sure you are connected to a network", true)) {

        if (e.getSource() == buy) {
          new Purchases(this, true).start(this);
          setVisible(true);
        } else if (e.getSource() == sell) {
          new Sales(this, true).start(this);
          setVisible(true);
        } else if (e.getSource() == lend) {
          new Loans(this, true).start(this);
          setVisible(true);
        } else if (e.getSource() == cashRegister) {
          btnCashRegistersAP();
          setVisible(true);
        } else if (e.getSource() == inventory) {
          btnInventoryTableAP(false);
          setVisible(true);
        } else if (e.getSource() == loans) {
          btnLoansTableAP();
          setVisible(true);
        } else if (e.getSource() == purchases) {
          btnPurchasesTableAP();
          setVisible(true);
        } else if (e.getSource() == sales) {
          btnSalesTableAP();
          setVisible(true);
        } else if (e.getSource() == users) {
          btnUsersTableAP();
          setVisible(true);
        }
      }
    }
  }
}