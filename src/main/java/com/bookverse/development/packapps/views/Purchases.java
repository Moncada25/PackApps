package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.HAND;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.fadeIn;
import static com.bookverse.development.packapps.core.AppConfig.getDate;
import static com.bookverse.development.packapps.utils.ViewConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.ViewConstants.PURCHASES;

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

public class Purchases extends JDialog implements ActionListener {

  public InventoryTable inventoryTable = new InventoryTable(this, true, true);
  private Resources resources = new Resources();
  private JLabel mas;
  private JLabel less;
  private JLabel lblUnits;
  private JButton btnSubmit, btnExit, btnSearch;
  private JTextField txtReference, txtDocument, txtPhone, txtPrice;
  private JRadioButton radioNew, radioUsed;
  private boolean validPhone = false, validDocument = false, validPrice = false;

  public Purchases() {
    createComponents();
  }

  public Purchases(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(resources.getImage("comprar.png")).getImage());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(310, 320, 95, 30);

    btnSubmit = resources.getButton("Purchase", TEXT_COLOR, this, this);
    btnSubmit.setBounds(30, 320, 95, 30);

    btnSearch = resources.getButton("Search", TEXT_COLOR, this, this);
    btnSearch.setBounds(340, 65, 86, 25);

    JLabel tittle = resources
        .getLabel("<html><strong><em>Register purchase</em></strong></html>",
            MAIN_COLOR, this, BIG);
    tittle.setBounds(110, 5, 300, 40);

    JLabel reference = resources
        .getLabel("<html><strong>Referencia</strong></html>", TEXT_COLOR,
            this, MEDIUM);
    reference.setBounds(30, 60, 100, 30);

    txtReference = new JTextField();
    txtReference.setBounds(180, 65, 150, 30);
    txtReference.setHorizontalAlignment(JTextField.CENTER);
    add(txtReference);

    txtReference.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtRefKeyTyped(evt);
      }

      private void txtRefKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, txtReference.getText(), 15);
      }
    });

    JLabel product = resources
        .getLabel("<html><strong>State</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    product.setBounds(30, 100, 140, 30);

    radioNew = new JRadioButton("<html><strong>New</strong></html>");
    radioNew.setForeground(TEXT_COLOR);
    radioNew.setBounds(175, 100, 78, 30);
    add(radioNew);

    radioUsed = new JRadioButton("<html><strong>Used</strong></html>");
    radioUsed.setForeground(TEXT_COLOR);
    radioUsed.setBounds(255, 100, 75, 30);
    radioUsed.setSelected(true);
    add(radioUsed);

    ButtonGroup btnGroup = new ButtonGroup();
    btnGroup.add(radioNew);
    btnGroup.add(radioUsed);

    JLabel document = resources
        .getLabel("<html><strong>Document</strong></html>", TEXT_COLOR,
            this, MEDIUM);
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
        .getLabel("<html><strong>Phone</strong></html>", TEXT_COLOR,
            this, MEDIUM);
    phone.setBounds(30, 180, 130, 30);

    txtPhone = new JTextField();
    txtPhone.setBounds(180, 185, 150, 30);
    txtPhone.setHorizontalAlignment(JTextField.CENTER);
    add(txtPhone);

    txtPhone.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtPhoneKeyTyped(evt);
      }

      private void txtPhoneKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtPhone.getText(), 10);
      }

    });

    JLabel units = resources
        .getLabel("<html><strong>Units</strong></html>", TEXT_COLOR,
            this, MEDIUM);
    units.setBounds(30, 220, 130, 30);

    lblUnits = resources.getLabel("1", TEXT_COLOR, this, BIG);
    lblUnits.setBounds(213, 230, 30, 20);

    mas = resources
        .getLabel("<html><strong>+</strong></html>", MAIN_COLOR, this, BIG);
    mas.setBounds(260, 225, 25, 25);

    mas.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        moreClicked();
      }

      public void moreClicked() {
        lblUnits.setText(String.valueOf(Integer.parseInt(lblUnits.getText()) + 1));
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        mas.setCursor(HAND);
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

        if (Integer.parseInt(lblUnits.getText()) > 1) {
          lblUnits.setText(String.valueOf(Integer.parseInt(lblUnits.getText()) - 1));
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

    JLabel price = resources
        .getLabel("<html><strong>Price</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    price.setBounds(30, 260, 130, 30);

    txtPrice = new JTextField("0");
    txtPrice.setBounds(180, 265, 150, 30);
    txtPrice.setHorizontalAlignment(JTextField.CENTER);
    add(txtPrice);

    txtPrice.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtPrecioKeyTyped(evt);
      }

      private void txtPrecioKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtPrice.getText(), 9);
      }

    });
  }

  private void btnExitAP() {
    txtReference.setText("");
    txtPhone.setText("");
    lblUnits.setText("1");
    txtPrice.setText("");
    AppConfig.fadeOut(this);
  }

  private void btnSubmitAP() {

    boolean validReference = false;

    if (Format.verifyReference(txtReference.getText())) {
      txtReference.requestFocus();
      validReference = false;
    } else {
      validReference = true;
    }

    if (validReference) {
      if (Format.verifyDocument(txtDocument.getText())) {
        txtDocument.requestFocus();
        validDocument = false;
      } else {
        validDocument = true;
      }
    }

    if (validReference && validDocument) {

      if (Format.verifyPhone(txtPhone.getText())) {
        validPhone = true;
      } else {
        txtPhone.requestFocus();
        validPhone = false;
      }
    }

    if (validReference && validPhone && validDocument) {

      if (Format.verifyPrice(Double.parseDouble(txtPrice.getText()))) {
        validPrice = true;
      } else {
        txtPhone.requestFocus();
        validPrice = false;
      }
    }

    if (validReference && validPhone && validDocument && validPrice) {

      double totalPurchase =
          Double.parseDouble(txtPrice.getText()) * Integer.parseInt(lblUnits.getText());
      int productCount = Integer.parseInt(lblUnits.getText());

      String state = "";
      boolean existProduct = false;

      if (radioUsed.isSelected()) {
        state = "Used";
      } else {
        state = "New";
      }

      if (Database.searchProductByReference(txtReference.getText(), INVENTORY)) {

        if (state.equals(Database.store.getProductState())
            && Double.parseDouble(txtPrice.getText()) == Database.store.getPrice()) {
          Database.updateInventory(Integer.parseInt(lblUnits.getText()), txtReference.getText());
        } else {
          Alerts.existProduct();
          existProduct = true;
        }

      } else {

        String[] data = {INVENTORY, txtReference.getText(), state,
            String.valueOf(Double.parseDouble(txtPrice.getText())), lblUnits.getText()};

        Database.insertData(data);
      }

      if (!existProduct) {

        String user = "";

        user = Database.searchUserLogged("Online");

        if (Database.searchDataUser(user)) {
          Database.updatePurchases(user, productCount, totalPurchase);
        } else {
          String[] data = {PURCHASES, user, String.valueOf(0), String.valueOf(0.0),
              String.valueOf(productCount), String.valueOf(totalPurchase), String.valueOf(0.0)};

          Database.insertData(data);
        }

        try {

          String[] purchase = {PURCHASES, txtReference.getText(), user, txtDocument.getText(),
              txtPhone.getText(), getDate(), String.valueOf(
              lblUnits.getText()), String.valueOf(
              Double.parseDouble(txtPrice.getText()) * Integer.parseInt(lblUnits.getText()))};

          Database.insertData(purchase);

        } catch (Exception e) {
          e.printStackTrace();
        }

        Alerts.actionSuccessfully("Purchased ", lblUnits.getText(), totalPurchase);
        Database.store.setAvailableProducts(0);
        Database.store.setTotalPurchases(0.0);

        txtReference.setEnabled(true);
        radioNew.setEnabled(true);
        radioUsed.setEnabled(true);
        txtPrice.setEnabled(true);
        txtReference.setText("");
        txtDocument.setText("");
        txtPhone.setText("");
        lblUnits.setText("1");
        txtPrice.setText("");
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
    fadeIn(inventoryTable);
    setVisible(false);
    inventoryTable.setVisible(true);
  }

  private void btnImportAP() {

    if (inventoryTable.status == 1 || inventoryTable.status == 2) {

      Database.searchProductByReference(inventoryTable.reference, INVENTORY);

      txtReference.setText(inventoryTable.reference);

      if (Database.store.getProductState().equals("New")) {
        radioNew.setSelected(true);
      } else {
        radioUsed.setSelected(true);
      }

      txtPrice.setText(String.valueOf(Database.store.getPrice()));

      txtReference.setEnabled(false);
      radioNew.setEnabled(false);
      radioUsed.setEnabled(false);
      txtPrice.setEnabled(false);
    }
  }

  public void start(JDialog parent) {
    setSize(440, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Buy");
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnExit) {
      btnExitAP();
    } else if (e.getSource() == btnSubmit) {
      btnSubmitAP();
    } else if (e.getSource() == btnSearch) {
      btnSearchAP();
      btnImportAP();
      setVisible(true);
    }
  }
}