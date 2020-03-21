package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.HAND;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfig.SMALL;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.getDate;
import static com.bookverse.development.packapps.utils.AppConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.utils.AppConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.AppConstants.SALES;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.Querys;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Sales extends JDialog implements ActionListener {

  private JTextField txtReference, txtPhone, txtDocument, txtPrice;
  private InventoryTable inventoryTable = new InventoryTable(this, true, true);
  private JButton btnSearch, btnSubmit, btReturn;
  private Resources resources = new Resources();
  private JLabel lblUnits, available, more, less, unitsAvailable, unitsActual;
  private ButtonGroup btnGroup;
  private JRadioButton radioNew, radioUsed;

  public Sales(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("vender.png")).getImage());

    btReturn = resources.getButton("Return", MAIN_COLOR, this, this);
    btReturn.setBounds(310, 320, 86, 30);

    btnSubmit = resources.getButton("Sell", TEXT_COLOR, this, this);
    btnSubmit.setBounds(30, 320, 86, 30);
    btnSubmit.setEnabled(false);

    btnSearch = resources.getButton("Search", TEXT_COLOR, this, this);
    btnSearch.setBounds(340, 65, 86, 25);

    JLabel tittle = resources.getLabel("<html><strong><em>Register sale</em></strong></html>",
        MAIN_COLOR, this, BIG);
    tittle.setBounds(138, 5, 300, 40);

    JLabel reference = resources
        .getLabel("<html><strong>Reference</strong></html>", TEXT_COLOR, this, MEDIUM);
    reference.setBounds(30, 60, 100, 30);

    txtReference = new JTextField();
    txtReference.setBounds(180, 65, 150, 30);
    txtReference.setHorizontalAlignment(JTextField.CENTER);
    txtReference.setEnabled(false);
    add(txtReference);

    JLabel state = resources
        .getLabel("<html><strong>State</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    state.setBounds(30, 100, 140, 30);

    radioNew = new JRadioButton("<html><strong>New</strong></html>");
    radioNew.setBounds(175, 100, 78, 30);
    radioNew.setForeground(TEXT_COLOR);
    radioNew.setEnabled(false);
    add(radioNew);

    radioUsed = new JRadioButton("<html><strong>Used</strong></html>");
    radioUsed.setBounds(255, 100, 75, 30);
    radioUsed.setForeground(TEXT_COLOR);
    radioUsed.setEnabled(false);
    add(radioUsed);

    btnGroup = new ButtonGroup();
    btnGroup.add(radioNew);
    btnGroup.add(radioUsed);

    JLabel document = resources
        .getLabel("<html><strong>Document</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    document.setBounds(30, 140, 130, 30);

    txtDocument = new JTextField();
    txtDocument.setBounds(180, 145, 150, 30);
    txtDocument.setHorizontalAlignment(JTextField.CENTER);
    add(txtDocument);

    txtDocument.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtDocKeyTyped(evt);
      }

      private void txtDocKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtDocument.getText(), 10);
      }
    });

    JLabel phone = resources
        .getLabel("<html><strong>Phone</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    phone.setBounds(30, 180, 130, 30);

    txtPhone = new JTextField();
    txtPhone.setBounds(180, 185, 150, 30);
    txtPhone.setHorizontalAlignment(JTextField.CENTER);
    add(txtPhone);

    txtPhone.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtTelKeyTyped(evt);
      }

      private void txtTelKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtPhone.getText(), 10);
      }
    });

    lblUnits = resources
        .getLabel("<html><strong>Units</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    lblUnits.setBounds(30, 220, 130, 30);

    unitsActual = resources.getLabel("1", TEXT_COLOR, this, BIG);
    unitsActual.setBounds(213, 230, 40, 20);

    more = resources.getLabel("<html><strong>+</strong></html>", MAIN_COLOR, this,
        BIG);
    more.setBounds(260, 225, 25, 25);

    more.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        moreClicked();
      }

      public void moreClicked() {

        if (!unitsAvailable.getText().equals("")) {

          String formatUnitsAvailable = unitsAvailable.getText().replace("<html><strong>", "")
              .replace("</strong></html>", "");

          if (Integer.parseInt(unitsActual.getText()) >= Integer.parseInt(formatUnitsAvailable)) {
            Alerts.message("Message", "Insufficient stock");
          } else {
            unitsActual.setText(String.valueOf(Integer.parseInt(unitsActual.getText()) + 1));
          }
        } else {
          Alerts.message("Message", "Select a product from inventory");
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        more.setCursor(HAND);
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }
    });

    less = resources
        .getLabel("<html><strong>-</strong></html>", MAIN_COLOR, this, BIG);
    less.setBounds(180, 224, 25, 25);

    less.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        lessClicked();
      }

      public void lessClicked() {

        if (!unitsAvailable.getText().equals("")) {

          if (Integer.parseInt(unitsActual.getText()) > 1) {
            unitsActual.setText(String.valueOf(Integer.parseInt(unitsActual.getText()) - 1));
          }
        } else {
          Alerts.message("Message", "Select a product from inventory.");
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        less.setCursor(HAND);
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

    });

    available = resources
        .getLabel("<html><strong>Available: </strong></html>", TEXT_COLOR, this, SMALL);
    available.setBounds(340, 224, 130, 30);
    available.setVisible(false);

    unitsAvailable = resources.getLabel("", TEXT_COLOR, this, SMALL);
    unitsAvailable.setBounds(408, 224, 130, 30);

    JLabel price = resources
        .getLabel("<html><strong>Price</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    price.setBounds(30, 260, 130, 30);

    txtPrice = new JTextField("0");
    txtPrice.setBounds(180, 265, 150, 30);
    txtPrice.setEnabled(false);
    txtPrice.setHorizontalAlignment(JTextField.CENTER);
    add(txtPrice);
  }

  public void start(JDialog parent) {
    setSize(440, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Sell");
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void btnReturnAP() {
    txtReference.setText("");
    txtPhone.setText("");
    unitsActual.setText("1");
    unitsAvailable.setText("");
    txtPrice.setText("");
    AppConfig.fadeOut(this);
  }

  private void btnSubmitAP() {

    if (!Format.verifyDocument(txtDocument.getText())) {
      Alerts.message("Verify!", "Input a valid document.");
      txtDocument.requestFocus();
    } else {
      if (!Format.verifyPhone(txtPhone.getText())) {
        Alerts.message("Verify!", "Input a valid phone.");
        txtPhone.requestFocus();
      } else {

        String formatUnitsAvailable = unitsAvailable.getText().replace("<html><strong>", "").replace("</strong></html>", "");

        Database.store.setTotalSales(Double.parseDouble(txtPrice.getText()) * Integer.parseInt(unitsActual.getText()));
        Database.store.setUnitsActual(Integer.parseInt(unitsActual.getText()));
        Database.updateInventory(Integer.parseInt(formatUnitsAvailable), txtReference.getText(), false);

        String user = HomeStore.userLogged;

        if (Database.searchDataUserInCashRegister(user)) {
          Database.updateSales(user, Database.store.getUnitsActual(), Database.store.getTotalSales());
        } else {

          String[] data = {CASH_REGISTER, user,
              String.valueOf(Database.store.getUnitsActual()),
              String.valueOf(Database.store.getTotalSales()), String.valueOf(0),
              String.valueOf(0.0),
              String.valueOf(0.0)};

          Database.insertData(data);
        }

        try {

          String[] sale = {SALES, txtReference.getText(), user, txtDocument.getText(),
              txtPhone.getText(), getDate(), String.valueOf(unitsActual.getText()),
              String.valueOf(
                  Double.parseDouble(txtPrice.getText()) * Integer
                      .parseInt(unitsActual.getText()))};

          Database.insertData(sale);

        } catch (Exception e) {
          e.printStackTrace();
        }

        Alerts.actionSuccessfully("lend", unitsActual.getText(), Double.parseDouble(txtPrice.getText()) * Integer.parseInt(unitsActual.getText()));
        Database.store.setUnitsActual(0);
        Database.store.setTotalLoans(0.0);

        btnSubmit.setEnabled(false);
        available.setVisible(false);
        btnGroup.clearSelection();
        txtReference.setText("");
        txtDocument.setText("");
        txtPhone.setText("");
        unitsActual.setText("1");
        txtPrice.setText("");
        unitsAvailable.setText("");
      }
    }
  }

  private void btnSearchAP() {

    inventoryTable.cleanTable();

    try {
      Database.readTable(inventoryTable.viewTable, Querys.getAllData(INVENTORY), true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    inventoryTable.setSize(830, 400);
    inventoryTable.setMinimumSize(new Dimension(830, 400));
    inventoryTable.setLocationRelativeTo(null);
    inventoryTable.setTitle("Available products");
    AppConfig.fadeIn(inventoryTable);
    setVisible(false);
    inventoryTable.setVisible(true);
  }

  private void importDataProduct() {

    if (inventoryTable.status == 1) {
      Database.searchProductByReference(inventoryTable.reference, INVENTORY);
      txtReference.setText(inventoryTable.reference);

      if (Database.store.getProductState().equals("New")) {
        radioNew.setSelected(true);
      } else if (Database.store.getProductState().equals("Used")) {
        radioUsed.setSelected(true);
      }

      txtPrice.setText(String.valueOf(Database.store.getPrice()));
      unitsAvailable
          .setText("<html><strong>" + Database.store.getUnitsActual() + "</strong></html>");

      btnSubmit.setEnabled(true);
      available.setVisible(true);

    } else if (inventoryTable.status == 2) {
      Alerts.message("Warning", "There are no units available for the selected product.");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btReturn) {
      btnReturnAP();
    } else if (e.getSource() == btnSubmit) {
      btnSubmitAP();
    } else if (e.getSource() == btnSearch) {
      btnSearchAP();
      importDataProduct();
      setVisible(true);
    }
  }
}