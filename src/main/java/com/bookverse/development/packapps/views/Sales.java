package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.*;
import static com.bookverse.development.packapps.utils.ViewConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.utils.ViewConstants.INVENTORY;
import static com.bookverse.development.packapps.utils.ViewConstants.SALES;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart.Data;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Sales extends JDialog implements ActionListener {

  public JButton btnbuscar;
  public JTextField txtRef, txtTel, txtDoc, txtPrecio;
  public InventoryTable inv = new InventoryTable(this, true, true);
  Resources resources = new Resources();
  private JLabel titulo, referencia, documento, producto, tel, unid, precio, disponibles, mas, menos, cantDisponible,
      txtUnid;
  private JButton btningresar, btnsalir;
  private ButtonGroup btnGroup;
  private JRadioButton nuevo, usado;
  private boolean refVal = false, telVal = false, docVal = false, canVal = false, preVal = false;

  public Sales(JDialog parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  // Constructor que no recibe parámetros
  public Sales() {

    componentes();
  }

  public void componentes() {

    setLayout(null);
    setDefaultCloseOperation(0);
    setIconImage(new ImageIcon(resources.getImage("vender.png")).getImage());

    btnsalir = resources.getButton("Return", MAIN_COLOR, this, this);
    btnsalir.setBounds(310, 320, 86, 30);

    btningresar = resources.getButton("Vender", TEXT_COLOR, this, this);
    btningresar.setBounds(30, 320, 86, 30);
    btningresar.setEnabled(false);

    btnbuscar = resources.getButton("Buscar", TEXT_COLOR, this, this);
    btnbuscar.setBounds(340, 65, 86, 25);

    titulo = resources.getLabel("<html><strong><em>Registrar Venta</em></strong></html>",
        MAIN_COLOR, this, BIG);
    titulo.setBounds(120, 5, 300, 40);

    referencia = resources
        .getLabel("<html><strong>Referencia</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    referencia.setBounds(30, 60, 100, 30);

    txtRef = new JTextField();
    txtRef.setBounds(180, 65, 150, 30);
    txtRef.setHorizontalAlignment(JTextField.CENTER);
    txtRef.setEnabled(false);
    add(txtRef);

    // Permite detectar lo que se escribe en el campo de referencia
    txtRef.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtRefKeyTyped(evt);
      }

      private void txtRefKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, txtRef.getText(), 10);
      }
    });

    producto = resources
        .getLabel("<html><strong>Estado</strong></html>", TEXT_COLOR, this,
        MEDIUM);
    producto.setBounds(30, 100, 140, 30);

    nuevo = new JRadioButton("<html><strong>Nuevo</strong></html>");
    nuevo.setBounds(175, 100, 78, 30);
    nuevo.setForeground(TEXT_COLOR);
    nuevo.setEnabled(false);
    add(nuevo);

    usado = new JRadioButton("<html><strong>Usado</strong></html>");
    usado.setBounds(255, 100, 75, 30);
    usado.setForeground(TEXT_COLOR);
    usado.setEnabled(false);
    add(usado);

    btnGroup = new ButtonGroup();
    btnGroup.add(nuevo);
    btnGroup.add(usado);

    documento = resources
        .getLabel("<html><strong>Documento</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    documento.setBounds(30, 140, 130, 30);

    txtDoc = new JTextField();
    txtDoc.setBounds(180, 145, 150, 30);
    txtDoc.setHorizontalAlignment(JTextField.CENTER);
    add(txtDoc);

    // Permite detectar lo que se escribe en el campo de documento
    txtDoc.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtDocKeyTyped(evt);
      }

      private void txtDocKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtDoc.getText(), 10);
      }
    });

    tel = resources
        .getLabel("<html><strong>Teléfono</strong></html>", TEXT_COLOR, this,
        MEDIUM);
    tel.setBounds(30, 180, 130, 30);

    txtTel = new JTextField();
    txtTel.setBounds(180, 185, 150, 30);
    txtTel.setHorizontalAlignment(JTextField.CENTER);
    add(txtTel);

    // Permite detectar lo que se escribe en el campo de teléfono
    txtTel.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtTelKeyTyped(evt);
      }

      private void txtTelKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtTel.getText(), 10);
      }

    });

    unid = resources
        .getLabel("<html><strong>Unidades</strong></html>", TEXT_COLOR, this,
        MEDIUM);
    unid.setBounds(30, 220, 130, 30);

    txtUnid = resources.getLabel("1", TEXT_COLOR, this, BIG);
    txtUnid.setBounds(213, 230, 40, 20);

    mas = resources.getLabel("<html><strong>+</strong></html>", MAIN_COLOR, this,
        BIG);
    mas.setBounds(260, 225, 25, 25);

    // Permite hacer clic en la etiqueta "+"
    mas.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        masClcked(e);
      }

      public void masClcked(MouseEvent e) {

        if (!cantDisponible.getText().equals("")) {

          if (Integer.parseInt(txtUnid.getText()) >= Integer.parseInt(cantDisponible.getText())) {
            JOptionPane.showMessageDialog(null, "<html>" + Format.style()
                    + "<strong>Stock insuficiente</strong></html>", "Mensaje",
                JOptionPane.PLAIN_MESSAGE);
          } else {
            txtUnid.setText(String.valueOf(Integer.parseInt(txtUnid.getText()) + 1));
          }
        } else {
          JOptionPane.showMessageDialog(null, "<html>" + Format.style()
                  + "<strong>Seleccione un producto del inventario</strong></html>", "Mensaje",
              JOptionPane.PLAIN_MESSAGE);
        }
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

    menos = resources
        .getLabel("<html><strong>-</strong></html>", MAIN_COLOR, this,
        BIG);
    menos.setBounds(180, 224, 25, 25);

    // Permite hacer clic en la etiqueta "-"
    menos.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        menosClcked(e);
      }

      public void menosClcked(MouseEvent e) {

        if (!cantDisponible.getText().equals("")) {

          if (Integer.parseInt(txtUnid.getText()) > 1) {
            txtUnid.setText(String.valueOf(Integer.parseInt(txtUnid.getText()) - 1));
          }
        } else {
          JOptionPane.showMessageDialog(null, "<html>" + Format.style()
                  + "<strong>Seleccione un producto del inventario</strong></html>", "Mensaje",
              JOptionPane.PLAIN_MESSAGE);
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        menos.setCursor(HAND);
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

    disponibles = resources
        .getLabel("<html><strong>Disp:</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    disponibles.setBounds(340, 220, 130, 30);
    disponibles.setVisible(false);

    cantDisponible = resources.getLabel("", TEXT_COLOR, this, MEDIUM);
    cantDisponible.setBounds(390, 222, 130, 30);

    precio = resources
        .getLabel("<html><strong>Precio</strong></html>", TEXT_COLOR, this,
        MEDIUM);
    precio.setBounds(30, 260, 130, 30);

    txtPrecio = new JTextField("0");
    txtPrecio.setBounds(180, 265, 150, 30);
    txtPrecio.setEnabled(false);
    txtPrecio.setHorizontalAlignment(JTextField.CENTER);
    add(txtPrecio);

    // Permite detectar lo que se escribe en el campo de precio
    txtPrecio.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtPrecioKeyTyped(evt);
      }

      private void txtPrecioKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtPrecio.getText(), 9);
      }
    });
  }

  public void start(JDialog parent) {
    setSize(440, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Vender");
    fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  public void btnSalirAP() {

    txtRef.setText("");
    txtTel.setText("");
    txtUnid.setText("1");
    cantDisponible.setText("");
    txtPrecio.setText("");
    fadeOut(this);
  }

  public void btnIngresarAP() {

    if (Format.verifyReference(txtRef.getText())) {
      txtRef.requestFocus();
      refVal = false;
    } else {
      refVal = true;
    }

    if (refVal) {
      if (Format.verifyDocument(txtDoc.getText())) {
        txtDoc.requestFocus();
        docVal = false;
      } else {
        docVal = true;
      }
    }

    if (refVal && docVal) {

      if (Format.verifyPhone(txtTel.getText())) {
        telVal = true;
      } else {
        txtTel.requestFocus();
        telVal = false;
      }
    }

    if (refVal && telVal && docVal) {

      if (Integer.parseInt(txtUnid.getText()) > Integer.parseInt(cantDisponible.getText())) {
        JOptionPane.showMessageDialog(null, "<html>" + Format.style()
                + "<strong>No hay suficiente stock, revise las unidades introducidas.</strong></html>",
            "Mensaje", JOptionPane.PLAIN_MESSAGE);
        txtUnid.requestFocus();
        canVal = false;
      } else {
        canVal = true;
      }
    }

    if (refVal && telVal && docVal) {

      if (Format.verifyPrice(Double.parseDouble(txtPrecio.getText()))) {
        preVal = true;
      } else {
        preVal = false;
      }
    }

    if (refVal && telVal && docVal && canVal && preVal) {

      Database.store.setTotalLoans(Double.parseDouble(txtPrecio.getText()) * Integer.parseInt(txtUnid.getText()));
      Database.store.setAvailableProducts(Integer.parseInt(txtUnid.getText()));

      cantDisponible.setText(
          String.valueOf(
              Integer.parseInt(cantDisponible.getText()) - Integer.parseInt(txtUnid.getText())));

      // actualizar inventario

      Database.updateInventory(Integer.parseInt(cantDisponible.getText()), txtRef.getText());

      // actualizar registradora
      String user = "";

      user = Database.searchUserLogged("Online");

      if (Database.searchUser(user)) {
        Database.updateSales(user, Database.store.getAvailableProducts(), Database.store.getTotalSales());
      } else {

        String[] datos = {CASH_REGISTER, user, String.valueOf(Database.store.getAvailableProducts()),
            String.valueOf(Database.store.getTotalSales()), String.valueOf(0), String.valueOf(0.0),
            String.valueOf(0.0)};

        Database.insertData(datos);
      }

      // actualizar ventas
      try {

        String[] compra = {SALES, txtRef.getText(), user, txtDoc.getText(),
            txtTel.getText(), getDate(), String.valueOf(txtUnid.getText()),
            String.valueOf(
                Double.parseDouble(txtPrecio.getText()) * Integer.parseInt(txtUnid.getText()))};

        Database.insertData(compra);

      } catch (Exception e) {
        e.printStackTrace();
      }

      Alerts.actionSuccessfully("Lend ", unid.getText(), Double.parseDouble(txtPrecio.getText()) * Integer.parseInt(txtUnid.getText()));
      Database.store.setAvailableProducts(0);
      Database.store.setTotalLoans(0.0);

      btningresar.setEnabled(false);
      disponibles.setVisible(false);
      btnGroup.clearSelection();
      txtRef.setText("");
      txtDoc.setText("");
      txtTel.setText("");
      txtUnid.setText("1");
      txtPrecio.setText("");
      cantDisponible.setText("");
    }

  }

  public void btnBuscarAP() {

    inv.cleanTable();

    // importa el inventario desde sql
    try {
      Database.readTable(inv.viewTable, "select * from inventario", true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    inv.setSize(830, 400);
    inv.setMinimumSize(new Dimension(830, 400));
    inv.setLocationRelativeTo(null);
    inv.setTitle("Productos disponibles");
    fadeIn(inv);
    setVisible(false);
    inv.setVisible(true);
  }

  public void btnImportAP() {

    if (inv.status == 1) {

      Database.searchProductByReference(inv.reference, INVENTORY);

      txtRef.setText(inv.reference);

      if (Database.store.getProductState().equals("Nuevo")) {
        nuevo.setSelected(true);
      } else {
        usado.setSelected(true);
      }

      txtPrecio.setText(String.valueOf(Database.store.getPrice()));
      cantDisponible.setText(String.valueOf(Database.store.getAvailableProducts()));

      btningresar.setEnabled(true);
      disponibles.setVisible(true);
      txtRef.setEnabled(false);
      nuevo.setEnabled(false);
      usado.setEnabled(false);
      txtPrecio.setEnabled(false);

    } else if (inv.status == 2) {
      JOptionPane.showMessageDialog(null, "<html>" + Format.style()
              + "<strong>No existen unidades disponibles del producto seleccionado</strong></html>",
          "Mensaje",
          JOptionPane.PLAIN_MESSAGE);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnsalir) {
      btnSalirAP();
    } else if (e.getSource() == btningresar) {
      btnIngresarAP();
    } else if (e.getSource() == btnbuscar) {
      btnBuscarAP();
      btnImportAP();
      setVisible(true);
    }
  }
}