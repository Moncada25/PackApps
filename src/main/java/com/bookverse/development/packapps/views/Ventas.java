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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ventas extends JDialog implements ActionListener {

    Resources img = new Resources();

    private JLabel titulo, referencia, documento, producto, tel, unid, precio, disponibles, mas, menos, cantDisponible,
            txtUnid;
    private JButton btningresar, btnsalir;
    public JButton btnbuscar;
    public JTextField txtRef, txtTel, txtDoc, txtPrecio;
    private ButtonGroup btnGroup;
    private JRadioButton nuevo, usado;
    private boolean refVal = false, telVal = false, docVal = false, canVal = false, preVal = false;
    public InventarioTabla inv = new InventarioTabla(this, true, true);

    public Ventas(JDialog parent, boolean modal) {

        super(parent, modal);

        componentes();
    }

    public void componentes() {

        setLayout(null);
        setDefaultCloseOperation(0);
        setIconImage(new ImageIcon(img.getImage("vender.png")).getImage());

        btnsalir = img.getButton("Return", img.core.ROJO, this, this);
        btnsalir.setBounds(310, 320, 86, 30);

        btningresar = img.getButton("Vender", img.core.AZUL, this, this);
        btningresar.setBounds(30, 320, 86, 30);
        btningresar.setEnabled(false);

        btnbuscar = img.getButton("Buscar", img.core.AZUL, this, this);
        btnbuscar.setBounds(340, 65, 86, 25);

        titulo = img.getLabel("<html><strong><em>Registrar Venta</em></strong></html>", img.core.ROJO, this, img.core.BIG);
        titulo.setBounds(120, 5, 300, 40);

        referencia = img.getLabel("<html><strong>Referencia</strong></html>", img.core.AZUL, this, img.core.MEDIUM);
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
                img.core.soloAlfa(evt.getKeyChar(), evt, txtRef.getText(), 10);
            }
        });

        producto = img.getLabel("<html><strong>Estado</strong></html>", img.core.AZUL, this, img.core.MEDIUM);
        producto.setBounds(30, 100, 140, 30);

        nuevo = new JRadioButton("<html><strong>Nuevo</strong></html>");
        nuevo.setBounds(175, 100, 78, 30);
        nuevo.setForeground(img.core.AZUL);
        nuevo.setEnabled(false);
        add(nuevo);

        usado = new JRadioButton("<html><strong>Usado</strong></html>");
        usado.setBounds(255, 100, 75, 30);
        usado.setForeground(img.core.AZUL);
        usado.setEnabled(false);
        add(usado);

        btnGroup = new ButtonGroup();
        btnGroup.add(nuevo);
        btnGroup.add(usado);

        documento = img.getLabel("<html><strong>Documento</strong></html>", img.core.AZUL, this, img.core.MEDIUM);
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
                img.core.solonumeros(evt.getKeyChar(), evt, txtDoc.getText(), 10);
            }
        });

        tel = img.getLabel("<html><strong>Teléfono</strong></html>", img.core.AZUL, this, img.core.MEDIUM);
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
                img.core.solonumeros(evt.getKeyChar(), evt, txtTel.getText(), 10);
            }

        });

        unid = img.getLabel("<html><strong>Unidades</strong></html>", img.core.AZUL, this, img.core.MEDIUM);
        unid.setBounds(30, 220, 130, 30);

        txtUnid = img.getLabel("1", img.core.AZUL, this, img.core.BIG);
        txtUnid.setBounds(213, 230, 40, 20);

        mas = img.getLabel("<html><strong>+</strong></html>", img.core.ROJO, this, img.core.BIG);
        mas.setBounds(260, 225, 25, 25);

        // Permite hacer clic en la etiqueta "+"
        mas.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                masClcked(e);
            }

            public void masClcked(MouseEvent e) {

                if (!cantDisponible.getText().equals("")) {

                    if (Integer.parseInt(txtUnid.getText()) >= Integer.parseInt(cantDisponible.getText())) {
                        JOptionPane.showMessageDialog(null, "<html>" + img.core.styleJOption() + "<strong>Stock insuficiente</strong></html>", "Mensaje", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        txtUnid.setText(String.valueOf(Integer.parseInt(txtUnid.getText()) + 1));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "<html>" + img.core.styleJOption() + "<strong>Seleccione un producto del inventario</strong></html>", "Mensaje",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mas.setCursor(img.core.MANO);
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

        menos = img.getLabel("<html><strong>-</strong></html>", img.core.ROJO, this, img.core.BIG);
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
                    JOptionPane.showMessageDialog(null, "<html>" + img.core.styleJOption() + "<strong>Seleccione un producto del inventario</strong></html>", "Mensaje",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menos.setCursor(img.core.MANO);
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

        disponibles = img.getLabel("<html><strong>Disp:</strong></html>", img.core.AZUL, this, img.core.MEDIUM);
        disponibles.setBounds(340, 220, 130, 30);
        disponibles.setVisible(false);

        cantDisponible = img.getLabel("", img.core.AZUL, this, img.core.MEDIUM);
        cantDisponible.setBounds(390, 222, 130, 30);

        precio = img.getLabel("<html><strong>Precio</strong></html>", img.core.AZUL, this, img.core.MEDIUM);
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
                img.core.solonumeros(evt.getKeyChar(), evt, txtPrecio.getText(), 9);
            }

        });
    }

    // Constructor que no recibe parámetros
    public Ventas() {

        componentes();
    }

    public void btnSalirAP() {

        txtRef.setText("");
        txtTel.setText("");
        txtUnid.setText("1");
        cantDisponible.setText("");
        txtPrecio.setText("");
        img.core.fadeOut(this);
    }

    public void btnIngresarAP() {

        if (img.core.cantidadDigitos(txtRef.getText())) {
            txtRef.requestFocus();
            refVal = false;
        } else {
            refVal = true;
        }

        if (refVal) {
            if (img.core.valDoc(txtDoc.getText())) {
                txtDoc.requestFocus();
                docVal = false;
            } else {
                docVal = true;
            }
        }

        if (refVal && docVal) {

            if (img.core.telefono(txtTel.getText())) {
                telVal = true;
            } else {
                txtTel.requestFocus();
                telVal = false;
            }
        }

        if (refVal && telVal && docVal) {

            if (Integer.parseInt(txtUnid.getText()) > Integer.parseInt(cantDisponible.getText())) {
                JOptionPane.showMessageDialog(null, "<html>" + img.core.styleJOption() + "<strong>No hay suficiente stock, revise las unidades introducidas.</strong></html>",
                        "Mensaje", JOptionPane.PLAIN_MESSAGE);
                txtUnid.requestFocus();
                canVal = false;
            } else {
                canVal = true;
            }
        }

        if (refVal && telVal && docVal) {

            if (img.core.validarValor(Double.parseDouble(txtPrecio.getText()))) {
                preVal = true;
            } else {
                preVal = false;
            }
        }

        if (refVal && telVal && docVal && canVal && preVal) {

            img.core.setTotalVenta(Double.parseDouble(txtPrecio.getText()) * Integer.parseInt(txtUnid.getText()));
            img.core.setContProd(Integer.parseInt(txtUnid.getText()));

            cantDisponible.setText(
                    String.valueOf(Integer.parseInt(cantDisponible.getText()) - Integer.parseInt(txtUnid.getText())));

            // actualizar inventario
            try {

                img.database.updateCantidad(Integer.parseInt(cantDisponible.getText()), txtRef.getText(), "inventario");

            } catch (SQLException ex) {
                Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
            }

            // actualizar registradora
            String user = "";

            try {
                user = img.database.returnUser("Online");
            } catch (SQLException e2) {
                e2.printStackTrace();
            }

            try {
                if (img.database.buscarUser(user)) {
                    img.database.registrarVentas(user, img.core.getContProd(), img.core.getTotalVenta());
                } else {

                    String[] datos = {"registros", user, String.valueOf(img.core.getContProd()),
                            String.valueOf(img.core.getTotalVenta()), String.valueOf(0), String.valueOf(0.0),
                            String.valueOf(0.0)};

                    img.database.insertData(datos);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            // actualizar ventas
            try {

                String[] compra = {"ventas", txtRef.getText(), user, txtDoc.getText(),
                        txtTel.getText(), img.core.obtenerDate(), String.valueOf(txtUnid.getText()),
                        String.valueOf(Double.parseDouble(txtPrecio.getText()) * Integer.parseInt(txtUnid.getText()))};

                img.database.insertData(compra);

            } catch (Exception e) {
                e.printStackTrace();
            }

            img.core.mostrarTotalVenta();
            img.core.setContProd(0);
            img.core.setTotalVenta(0);

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

        inv.limpiarTabla();

        // importa el inventario desde sql
        try {
            img.database.readTable(inv.inventarioTab, "select * from inventario", true);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        inv.setSize(830, 400);
        inv.setMinimumSize(new Dimension(830, 400));
        inv.setLocationRelativeTo(null);
        inv.setTitle("Productos disponibles");
        img.core.fadeIn(inv);
        setVisible(false);
        inv.setVisible(true);
    }

    public void btnImportAP() {

        if (inv.status == 1) {

            img.database.importarDatos(inv.referencia);

            txtRef.setText(inv.referencia);

            if (img.database.getEst().equals("Nuevo")) {
                nuevo.setSelected(true);
            } else {
                usado.setSelected(true);
            }

            txtPrecio.setText(String.valueOf(img.database.getPrecio()));
            cantDisponible.setText(String.valueOf(img.database.getDisp()));

            btningresar.setEnabled(true);
            disponibles.setVisible(true);
            txtRef.setEnabled(false);
            nuevo.setEnabled(false);
            usado.setEnabled(false);
            txtPrecio.setEnabled(false);

        } else if (inv.status == 2) {
            JOptionPane.showMessageDialog(null, "<html>" + img.core.styleJOption() + "<strong>No existen unidades disponibles del producto seleccionado</strong></html>", "Mensaje",
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