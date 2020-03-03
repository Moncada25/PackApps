package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Compras extends JDialog implements ActionListener {

    Resources img = new Resources();
    public Ventas vent = new Ventas();

    private JLabel titulo, referencia, documento, producto, tel, unid, precio, mas, menos, txtUnid;
    private JButton btningresar, btnsalir, btnbuscar;
    private JTextField txtRef, txtDoc, txtTel, txtPrecio;
    private ButtonGroup btnGroup;
    private JRadioButton nuevo, usado;
    private boolean refVal = false, telVal = false, docVal = false, preVal = false;
    InventarioTabla inv = new InventarioTabla(this, true, true);

    public Compras() {
        componentes();
    }

    public Compras(JDialog parent, boolean modal) {

        super(parent, modal);

        componentes();
    }

    public void componentes() {

        setLayout(null);
        setIconImage(new ImageIcon(img.getImage("comprar.png")).getImage());
        setDefaultCloseOperation(0);

        btnsalir = img.getBoton("Return", img.cr.ROJO, this, this);
        btnsalir.setBounds(310, 320, 95, 30);

        btningresar = img.getBoton("Comprar", img.cr.AZUL, this, this);
        btningresar.setBounds(30, 320, 95, 30);

        btnbuscar = img.getBoton("Buscar", img.cr.AZUL, this, this);
        btnbuscar.setBounds(340, 65, 86, 25);

        titulo = img.getLabel("<html><strong><em>Registrar Compra</em></strong></html>", img.cr.ROJO, this, img.cr.BIG);
        titulo.setBounds(110, 5, 300, 40);

        referencia = img.getLabel("<html><strong>Referencia</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
        referencia.setBounds(30, 60, 100, 30);

        txtRef = new JTextField();
        txtRef.setBounds(180, 65, 150, 30);
        txtRef.setHorizontalAlignment(JTextField.CENTER);
        add(txtRef);

        // Permite detectar lo que se escribe en el campo de referencia
        txtRef.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtRefKeyTyped(evt);
            }

            private void txtRefKeyTyped(KeyEvent evt) {
                img.cr.soloAlfa(evt.getKeyChar(), evt, txtRef.getText(), 15);
            }
        });

        producto = img.getLabel("<html><strong>Estado</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
        producto.setBounds(30, 100, 140, 30);

        nuevo = new JRadioButton("<html><strong>Nuevo</strong></html>");
        nuevo.setForeground(img.cr.AZUL);
        nuevo.setBounds(175, 100, 78, 30);
        add(nuevo);

        usado = new JRadioButton("<html><strong>Usado</strong></html>");
        usado.setForeground(img.cr.AZUL);
        usado.setBounds(255, 100, 75, 30);
        usado.setSelected(true);
        add(usado);

        btnGroup = new ButtonGroup();
        btnGroup.add(nuevo);
        btnGroup.add(usado);

        documento = img.getLabel("<html><strong>Documento</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
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
                img.cr.solonumeros(evt.getKeyChar(), evt, txtDoc.getText(), 10);
            }
        });

        tel = img.getLabel("<html><strong>Teléfono</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
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
                img.cr.solonumeros(evt.getKeyChar(), evt, txtTel.getText(), 10);
            }

        });

        unid = img.getLabel("<html><strong>Unidades</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
        unid.setBounds(30, 220, 130, 30);

        txtUnid = img.getLabel("1", img.cr.AZUL, this, img.cr.BIG);
        txtUnid.setBounds(213, 230, 30, 20);

        mas = img.getLabel("<html><strong>+</strong></html>", img.cr.ROJO, this, img.cr.BIG);
        mas.setBounds(260, 225, 25, 25);

        // Permite hacer clic en la etiqueta "+"
        mas.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                masClcked(e);
            }

            public void masClcked(MouseEvent e) {
                txtUnid.setText(String.valueOf(Integer.parseInt(txtUnid.getText()) + 1));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mas.setCursor(img.cr.MANO);
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

        menos = img.getLabel("<html><strong>-</strong></html>", img.cr.ROJO, this, img.cr.BIG);
        menos.setBounds(180, 224, 25, 25);

        // Permite hacer clic en la etiqueta "-"
        menos.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                menosClcked(e);
            }

            public void menosClcked(MouseEvent e) {

                if (Integer.parseInt(txtUnid.getText()) > 1) {
                    txtUnid.setText(String.valueOf(Integer.parseInt(txtUnid.getText()) - 1));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menos.setCursor(img.cr.MANO);
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

        precio = img.getLabel("<html><strong>Precio</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
        precio.setBounds(30, 260, 130, 30);

        txtPrecio = new JTextField("0");
        txtPrecio.setBounds(180, 265, 150, 30);
        txtPrecio.setHorizontalAlignment(JTextField.CENTER);
        add(txtPrecio);

        // Permite detectar lo que se escribe en el campo de precio
        txtPrecio.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }

            private void txtPrecioKeyTyped(KeyEvent evt) {
                img.cr.solonumeros(evt.getKeyChar(), evt, txtPrecio.getText(), 9);
            }

        });
    }

    public void btnSalirAP() {

        txtRef.setText("");
        txtTel.setText("");
        txtUnid.setText("1");
        txtPrecio.setText("");
        img.cr.fadeOut(this);
    }

    public void btnIngresarAP() {

        if (img.cr.cantidadDigitos(txtRef.getText())) {
            txtRef.requestFocus();
            refVal = false;
        } else {
            refVal = true;
        }

        if (refVal) {
            if (img.cr.valDoc(txtDoc.getText())) {
                txtDoc.requestFocus();
                docVal = false;
            } else {
                docVal = true;
            }
        }

        if (refVal && docVal) {

            if (img.cr.telefono(txtTel.getText())) {
                telVal = true;
            } else {
                txtTel.requestFocus();
                telVal = false;
            }
        }

        if (refVal && telVal && docVal) {

            if (img.cr.validarValor(Double.parseDouble(txtPrecio.getText()))) {
                preVal = true;
            } else {
                preVal = false;
            }
        }

        if (refVal && telVal && docVal && preVal) {

            img.cr.setTotalCompra(Double.parseDouble(txtPrecio.getText()) * Integer.parseInt(txtUnid.getText()));
            img.cr.setContProd(Integer.parseInt(txtUnid.getText()));

            // actualizar inventario
            String estado = "";
            boolean productoExiste = false;

            if (usado.isSelected()) {
                estado = "Usado";
            } else {
                estado = "Nuevo";
            }

            try {

                if (img.db.buscarRef(txtRef.getText())) {

                    if (estado.equals(img.db.getEst()) && Double.parseDouble(txtPrecio.getText()) == img.db.getPrecio()) {

                        img.db.acumularInventario(Integer.parseInt(txtUnid.getText()), txtRef.getText());

                    } else {

                        img.cr.productoExiste();
                        productoExiste = true;
                    }

                } else {

                    String[] datos = {"inventario", txtRef.getText(), estado,
                            String.valueOf(Double.parseDouble(txtPrecio.getText())), txtUnid.getText()};

                    img.db.insertData(datos);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            if (!productoExiste) {

                // actualizar registradora
                String user = "";

                try {
                    user = img.db.returnUser("Online");
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }

                try {

                    if (img.db.buscarUser(user)) {
                        img.db.registrarCompras(user, img.cr.getContProd(), img.cr.getTotalCompra());
                    } else {

                        String[] datos = {"registros", user, String.valueOf(0), String.valueOf(0.0),
                                String.valueOf(img.cr.getContProd()), String.valueOf(img.cr.getTotalCompra()),
                                String.valueOf(0.0)};

                        img.db.insertData(datos);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                // actualizar compras
                try {

                    String[] compra = {"compras", txtRef.getText(), user, txtDoc.getText(),
                            txtTel.getText(), img.cr.obtenerDate(), String.valueOf(txtUnid.getText()), String.valueOf(
                            Double.parseDouble(txtPrecio.getText()) * Integer.parseInt(txtUnid.getText()))};

                    img.db.insertData(compra);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                img.cr.mostrarTotalCompra();
                img.cr.setContProd(0);
                img.cr.setTotalCompra(0);

                txtRef.setEnabled(true);
                nuevo.setEnabled(true);
                usado.setEnabled(true);
                txtPrecio.setEnabled(true);
                txtRef.setText("");
                txtDoc.setText("");
                txtTel.setText("");
                txtUnid.setText("1");
                txtPrecio.setText("");
            }
        }
    }

    public void btnBuscarAP() {

        inv.limpiarTabla();

        // importa el inventario desde sql
        try {
            img.db.importarTabla(inv.inventarioTab, "select * from inventario", true);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        inv.setSize(830, 400);
        inv.setMinimumSize(new Dimension(830, 400));
        inv.setLocationRelativeTo(null);
        inv.setTitle("Productos disponibles");
        img.cr.fadeIn(inv);
        setVisible(false);
        inv.setVisible(true);
    }

    public void btnImportAP() {

        if (inv.status == 1 || inv.status == 2) {

            try {
                img.db.importarDatos(inv.referencia);

                txtRef.setText(inv.referencia);

                if (img.db.getEst().equals("Nuevo")) {
                    nuevo.setSelected(true);
                } else {
                    usado.setSelected(true);
                }

                txtPrecio.setText(String.valueOf(img.db.getPrecio()));

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            txtRef.setEnabled(false);
            nuevo.setEnabled(false);
            usado.setEnabled(false);
            txtPrecio.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {// Eventos al presionar un bot?n

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