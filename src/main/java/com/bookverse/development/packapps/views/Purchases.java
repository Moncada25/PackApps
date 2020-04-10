package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.BIG;
import static com.bookverse.development.packapps.core.Settings.HAND;
import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;
import static com.bookverse.development.packapps.core.Settings.fadeIn;
import static com.bookverse.development.packapps.utils.DatabaseConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.utils.DatabaseConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.DatabaseConstants.PURCHASES;

import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.models.Database;
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

  private InventoryTable inventoryTable = new InventoryTable(this, true, true);
  private Resources resources = new Resources();
  private JLabel more, less, lblUnits;
  private JButton btnSubmit, btnExit, btnSearch;
  private JTextField txtReference, txtDocument, txtPhone, txtPrice;
  private JRadioButton radioNew, radioUsed;

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

    btnSubmit = resources.getButton("Buy", TEXT_COLOR, this, this);
    btnSubmit.setBounds(30, 320, 95, 30);

    btnSearch = resources.getButton("Search", TEXT_COLOR, this, this);
    btnSearch.setBounds(340, 65, 86, 25);

    JLabel title = resources
        .getLabel("<html><strong><em>Register purchase</em></strong></html>",
            MAIN_COLOR, this, BIG);
    title.setBounds(110, 5, 300, 40);

    JLabel reference = resources
        .getLabel("<html><strong>Reference</strong></html>", TEXT_COLOR,
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

    JLabel state = resources
        .getLabel("<html><strong>State</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    state.setBounds(30, 100, 140, 30);

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

    more = resources
        .getLabel("<html><strong>+</strong></html>", MAIN_COLOR, this, BIG);
    more.setBounds(260, 225, 25, 25);

    more.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        moreClicked();
      }

      public void moreClicked() {
        lblUnits.setText(String.valueOf(Integer.parseInt(lblUnits.getText()) + 1));
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
    Settings.fadeOut(this);
  }

  private void btnSubmitAP() {

    if (!Format.verifyReference(txtReference.getText())) {
      Alerts.message("Verify!", "Input a valid reference.");
      txtReference.requestFocus();
    } else {

      if (!Format.verifyDocument(txtDocument.getText())) {
        Alerts.message("Verify!", "Input a valid document.");
        txtDocument.requestFocus();
      } else {

        if (!Format.verifyPhone(txtPhone.getText())) {
          Alerts.message("Verify!", "Input a valid phone.");
          txtPhone.requestFocus();
        } else {

          if (!Format.verifyPrice(Double.parseDouble(txtPrice.getText()))) {
            Alerts.message("Verify!", "Input a valid price.");
            txtPrice.requestFocus();
          } else {

            double totalPurchase =
                Double.parseDouble(txtPrice.getText()) * Integer.parseInt(lblUnits.getText());
            int productCount = Integer.parseInt(lblUnits.getText());

            String stateProduct = "";
            boolean existProduct = false;

            if (radioUsed.isSelected()) {
              stateProduct = "Used";
            } else if (radioNew.isSelected()) {
              stateProduct = "New";
            }

            if (Database.searchProductByReference(txtReference.getText(), INVENTORY)) {

              if (stateProduct.equals(Database.store.getProductState())
                  && Double.parseDouble(txtPrice.getText()) == Database.store.getPrice()) {
                Database
                    .updateInventory(Integer.parseInt(lblUnits.getText()), txtReference.getText(),
                        true);
              } else {
                Alerts.existProduct();
                existProduct = true;
              }

            } else {
              String[] data = {INVENTORY, txtReference.getText(), stateProduct,
                  String.valueOf(Double.parseDouble(txtPrice.getText())), lblUnits.getText()};
              Database.insertData(data);
            }

            if (!existProduct) {
              String user = HomeStore.userLogged;

              if (Database.searchDataUserInCashRegister(user)) {
                Database.updatePurchases(user, productCount, totalPurchase);
              } else {
                String[] data = {CASH_REGISTER, user, String.valueOf(0), String.valueOf(0.0),
                    String.valueOf(productCount), String.valueOf(totalPurchase),
                    String.valueOf(0.0)};
                Database.insertData(data);
              }

              try {

                String[] purchase = {PURCHASES, txtReference.getText(), user, txtDocument.getText(),
                    txtPhone.getText(), Settings.getDate(), String.valueOf(lblUnits.getText()),
                    String.valueOf(totalPurchase)};

                Database.insertData(purchase);

              } catch (Exception e) {
                Alerts.error(e, PURCHASES);
              }

              Alerts.actionSuccessfully("purchased", lblUnits.getText(), totalPurchase);
              Database.store.setUnitsActual(0);
              Database.store.setTotalPurchases(0.0);

              txtReference.setEnabled(true);
              radioNew.setEnabled(true);
              radioUsed.setEnabled(true);
              txtPrice.setEnabled(true);
              txtReference.setText("");
              txtDocument.setText("");
              txtPhone.setText("");
              lblUnits.setText("1");
              txtPrice.setText("0");
            }
          }
        }
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
    Settings.fadeIn(this);
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