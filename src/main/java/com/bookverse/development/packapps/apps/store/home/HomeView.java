package com.bookverse.development.packapps.apps.store.home;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.ui.factory.Menu;
import com.bookverse.development.packapps.utils.ui.factory.MenuItem;
import com.bookverse.development.packapps.views.older.store.Loans;
import com.bookverse.development.packapps.views.older.store.Purchases;
import com.bookverse.development.packapps.views.older.store.Sales;

public class HomeView extends JDialog {

  private transient HomeService service = new HomeService();

  public HomeView(JDialog parent, boolean modal) {
    super(parent, modal);
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
    
    JMenu actions = new Menu().setText("Services").setImage("services").build();
    JMenuItem buy = new MenuItem().setText("Buy").setImage("comprar").build();
    buy.addActionListener(e -> {
      new Purchases(this, true).start(this, service);
      setVisible(true);
    });
    JMenuItem lend = new MenuItem().setText("Lend").setImage("prestar").build();
    lend.addActionListener(e -> {
      new Loans(this, true).start(this, service);
      setVisible(true);
    });
    JMenuItem sell = new MenuItem().setText("Sell").setImage("vender").build();
    sell.addActionListener(e -> {
      new Sales(this, true).start(this, service);
      setVisible(true);
    });

    actions.add(buy);
    actions.addSeparator();
    actions.add(lend);
    actions.addSeparator();
    actions.add(sell);

    JMenu records = new Menu().setText("Records").setImage("data").build();
    JMenuItem inventory = new MenuItem().setText("Inventory").setImage("inventario").build();
    inventory.addActionListener(e -> {
      service.clickOnInventoryTable(this, false);
      setVisible(true);
    });
    JMenuItem cashRegister = new MenuItem().setText("Cash Register").setImage("registradora").build();
    cashRegister.addActionListener(e -> {
      service.clickOnCashRegister(this);
      setVisible(true);
    });
    JMenuItem loans = new MenuItem().setText("Loans").setImage("prestamos").build();
    loans.addActionListener(e -> {
      service.clickOnLoansTable(this);
      setVisible(true);
    });
    JMenuItem purchases = new MenuItem().setText("Purchases").setImage("comprar").build();
    purchases.addActionListener(e -> {
      service.clickOnPurchasesTable(this);
      setVisible(true);
    });
    JMenuItem sales = new MenuItem().setText("Sales").setImage("vender").build();
    sales.addActionListener(e -> {
      service.clickOnSalesTable(this);
      setVisible(true);
    });
    JMenuItem users = new MenuItem().setText("Users").setImage("usuario").build();
    users.addActionListener(e -> {
      service.clickOnUsersTable(this);
      setVisible(true);
    });

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

    JMenu exit = new Menu().setText("Exit").setImage("exit").build();
    JMenuItem logout = new MenuItem().setText("Logout").setImage("logout").build();
    logout.addActionListener(e -> service.clickOnLogout(this));

    exit.add(logout);

    menuBar.add(actions);
    menuBar.add(records);
    menuBar.add(exit);

    add(menuBar, BorderLayout.NORTH);
  }

  public void start(JDialog parent, String user) {
    service.setUserLogged(user);
    setSize(620, 380);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Welcome " + service.getUserLogged() + "!");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }
}