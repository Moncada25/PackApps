package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.AppConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.utils.AppConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.AppConstants.LOANS;
import static com.bookverse.development.packapps.utils.AppConstants.PURCHASES;
import static com.bookverse.development.packapps.utils.AppConstants.SALES;
import static com.bookverse.development.packapps.utils.AppConstants.USERS;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Querys;
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

  public static String userLogged;
  Resources resources = new Resources();
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

    ImageIcon wallpaper = new ImageIcon(resources.getImage("tienda.jpg"));
    JLabel welcome = new JLabel();
    welcome.setIcon(wallpaper);
    welcome.setSize(620, 300);
    add(welcome, BorderLayout.CENTER);

    JMenuBar menuBar = new JMenuBar();

    JMenu actions = resources.getMenu("Services", "services");
    buy = resources.getMenuItem("Buy", "comprar", this);
    lend = resources.getMenuItem("Lend", "prestar", this);
    sell = resources.getMenuItem("Sell", "vender", this);

    actions.add(buy);
    actions.addSeparator();
    actions.add(lend);
    actions.addSeparator();
    actions.add(sell);

    JMenu records = resources.getMenu("Records", "data");
    inventory = resources.getMenuItem(INVENTORY, "inventario", this);
    cashRegister = resources.getMenuItem(CASH_REGISTER, "registradora", this);
    loans = resources.getMenuItem(LOANS, "prestamos", this);
    purchases = resources.getMenuItem(PURCHASES, "comprar", this);
    sales = resources.getMenuItem(SALES, "vender", this);
    users = resources.getMenuItem(USERS, "usuario", this);

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

    JMenu exit = resources.getMenu("Logout", "exit");
    this.exit = resources.getMenuItem("Exit", "logout", this);

    exit.add(this.exit);

    menuBar.add(actions);
    menuBar.add(records);
    menuBar.add(exit);

    add(menuBar, BorderLayout.NORTH);
  }

  public void start(JDialog parent, String user) {
    userLogged = user;
    setSize(620, 380);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Welcome " + userLogged + "!");
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void btnCashRegistersAP() {

    CashRegisterTable table = new CashRegisterTable(this, true);

    table.limpiarTabla();

    try {
      Database.readTable(table.registradoraTab, Querys.getAllData(CASH_REGISTER), true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    table.setSize(950, 400);
    table.setMinimumSize(new Dimension(950, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(CASH_REGISTER);
    AppConfig.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  public void btnInventoryTableAP(boolean search) {

    InventoryTable table = new InventoryTable(this, true, search);

    table.cleanTable();

    try {
      Database.readTable(table.viewTable, Querys.getAllData(INVENTORY), true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle("Available products");
    AppConfig.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  private void btnLoansTableAP() {

    LoansTable table = new LoansTable(this, true);

    table.limpiarTabla();

    try {
      Database.readTable(table.viewTable, Querys.getAllData(LOANS), true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(LOANS);
    AppConfig.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  private void btnPurchasesTableAP() {

    SalesTable table = new SalesTable(this, true);

    table.limpiarTabla();

    try {
      Database.readTable(table.viewTable, Querys.getAllData(PURCHASES), true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(PURCHASES);
    AppConfig.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  private void btnSalesTableAP() {

    VentasTabla table = new VentasTabla(this, true);

    table.limpiarTabla();

    try {
      Database.readTable(table.ventasTab, SALES, true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    table.setSize(830, 400);
    table.setMinimumSize(new Dimension(830, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(SALES);
    AppConfig.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  public void btnUsersTableAP() {

    UsuariosTabla table = new UsuariosTabla(this, true);

    table.limpiarTabla();

    try {
      Database.readTable(table.usuariosTab, Querys.getAllData(USERS), true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    table.setSize(520, 400);
    table.setMinimumSize(new Dimension(520, 400));
    table.setMaximumSize(new Dimension(1280, 720));
    table.setLocationRelativeTo(null);
    table.setTitle(USERS);
    AppConfig.fadeIn(table);
    setVisible(false);
    table.setVisible(true);
  }

  private void logout() {
    Database.recordLogin("Offline", userLogged);
    AppConfig.fadeOut(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == exit) {
      logout();
    } else {

      if (AppConfig.verifyConnection("Make sure you are connected to a network", true)) {

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