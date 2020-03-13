package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Prestamos extends JDialog implements ActionListener {

    Resources img = new Resources();
    public PrestamosTabla tabpres = new PrestamosTabla();

    private JLabel titulo, referencia, documento, producto, tel, tiempo, precio;
    private JButton btnprestar, btnsalir;
    private JTextField txtRef, txtDoc, txtTel, txtPrecio, txtnombre;
    private JComboBox<String> semanas, meses;
    private boolean refVal = false, telVal = false, docVal = false, preVal = false;

    // Constructor que recibe la ventana padre y el valor modal
    public Prestamos(JDialog parent, boolean modal) {

        super(parent, modal);

        componentes();
    }

    // Se crean los componentes de la ventana
    public void componentes() {

        setLayout(null);
        setDefaultCloseOperation(0);
        setIconImage(new ImageIcon(img.getImage("prestar.png")).getImage());

        btnsalir = img.getButton("Return", img.appConfig.MAIN_COLOR, this, this);
        btnsalir.setBounds(330, 320, 86, 30);

        btnprestar = img.getButton("Prestar", img.appConfig.TEXT_COLOR, this, this);
        btnprestar.setBounds(30, 320, 86, 30);

        titulo = img.getLabel("<html><strong><em>Registrar Préstamo</em></strong></html>", img.appConfig.MAIN_COLOR, this, img.appConfig.BIG);
        titulo.setBounds(100, 5, 300, 40);

        producto = img.getLabel("<html><strong>A nombre de</strong></html>", img.appConfig.TEXT_COLOR, this, img.appConfig.MEDIUM);
        producto.setBounds(30, 60, 140, 30);

        txtnombre = new JTextField();
        txtnombre.setBounds(180, 65, 200, 30);
        txtnombre.setHorizontalAlignment(JTextField.CENTER);
        add(txtnombre);

        // Permite detectar lo que se escribe en el campo de nombre
        txtnombre.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }

            private void txtnombreKeyTyped(KeyEvent evt) {
                img.appConfig.soloTexto(evt.getKeyChar(), evt, txtnombre.getText(), 25);
            }

        });

        documento = img.getLabel("<html><strong>Documento</strong></html>", img.appConfig.TEXT_COLOR, this, img.appConfig.MEDIUM);
        documento.setBounds(30, 100, 140, 30);

        txtDoc = new JTextField();
        txtDoc.setBounds(180, 105, 200, 30);
        txtDoc.setHorizontalAlignment(JTextField.CENTER);
        add(txtDoc);

        // Permite detectar lo que se escribe en el campo de documento
        txtDoc.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtDocKeyTyped(evt);
            }

            private void txtDocKeyTyped(KeyEvent evt) {
                img.appConfig.solonumeros(evt.getKeyChar(), evt, txtDoc.getText(), 10);
            }
        });

        referencia = img.getLabel("<html><strong>Referencia</strong></html>", img.appConfig.TEXT_COLOR, this, img.appConfig.MEDIUM);
        referencia.setBounds(30, 140, 130, 30);

        txtRef = new JTextField();
        txtRef.setBounds(180, 145, 200, 30);
        txtRef.setHorizontalAlignment(JTextField.CENTER);
        add(txtRef);

        // Permite detectar lo que se escribe en el campo de referencia
        txtRef.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtRefKeyTyped(evt);
            }

            private void txtRefKeyTyped(KeyEvent evt) {
                img.appConfig.soloAlfa(evt.getKeyChar(), evt, txtRef.getText(), 15);
            }
        });

        tel = img.getLabel("<html><strong>Teléfono</strong></html>", img.appConfig.TEXT_COLOR, this, img.appConfig.MEDIUM);
        tel.setBounds(30, 180, 130, 30);

        txtTel = new JTextField();
        txtTel.setBounds(180, 185, 200, 30);
        txtTel.setHorizontalAlignment(JTextField.CENTER);
        add(txtTel);

        // Permite detectar lo que se escribe en el campo de teléfono
        txtTel.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtTelKeyTyped(evt);
            }

            private void txtTelKeyTyped(KeyEvent evt) {
                img.appConfig.solonumeros(evt.getKeyChar(), evt, txtTel.getText(), 10);
            }

        });

        tiempo = img.getLabel("<html><strong>Tiempo</strong></html>", img.appConfig.TEXT_COLOR, this, img.appConfig.MEDIUM);
        tiempo.setBounds(30, 220, 130, 30);

        semanas = new JComboBox<String>();
        semanas.setBounds(180, 227, 105, 25);
        semanas.setModel(new DefaultComboBoxModel<>(new String[]{"Semanas", "1", "2", "3"}));
        add(semanas);

        meses = new JComboBox<String>();
        meses.setBounds(295, 227, 85, 25);
        meses.setModel(new DefaultComboBoxModel<>(new String[]{"Meses", "1", "2", "3", "4", "5", "6"}));
        add(meses);

        precio = img.getLabel("<html><strong>Préstamo de</strong></html>", img.appConfig.TEXT_COLOR, this, img.appConfig.MEDIUM);
        precio.setBounds(30, 260, 130, 30);

        txtPrecio = new JTextField("0");
        txtPrecio.setBounds(180, 265, 200, 30);
        txtPrecio.setHorizontalAlignment(JTextField.CENTER);
        add(txtPrecio);

        // Permite detectar lo que se escribe en el campo de precio
        txtPrecio.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }

            private void txtPrecioKeyTyped(KeyEvent evt) {
                img.appConfig.solonumeros(evt.getKeyChar(), evt, txtPrecio.getText(), 9);
            }

        });
    }

    // Constructor que no recibe parámetros
    public Prestamos() {

        componentes();
    }

    public void btnSalirAP() {

        txtRef.setText("");
        txtTel.setText("");
        txtPrecio.setText("");
        txtTel.setEnabled(true);
        txtPrecio.setEnabled(true);

        img.appConfig.fadeOut(this);
    }

    public void btnPrestarAP() {

        if (img.appConfig.valDoc(txtDoc.getText())) {
            txtRef.setEnabled(false);
            txtTel.setEnabled(false);
            txtPrecio.setEnabled(false);
            semanas.setEnabled(false);
            meses.setEnabled(false);
            txtDoc.requestFocus();
            docVal = false;
        } else {
            txtRef.setEnabled(true);
            txtTel.setEnabled(true);
            txtPrecio.setEnabled(true);
            semanas.setEnabled(true);
            meses.setEnabled(true);
            docVal = true;
        }

        if (docVal) {
            if (img.appConfig.cantidadDigitos(txtRef.getText())) {
                txtTel.setEnabled(false);
                txtPrecio.setEnabled(false);
                semanas.setEnabled(false);
                meses.setEnabled(false);
                txtRef.requestFocus();
                refVal = false;
            } else {
                txtTel.setEnabled(true);
                txtPrecio.setEnabled(true);
                semanas.setEnabled(true);
                meses.setEnabled(true);
                refVal = true;
            }
        }

        if (refVal && docVal) {
            if (img.appConfig.telefono(txtTel.getText())) {
                if (!refVal) {
                    txtPrecio.setEnabled(false);
                    semanas.setEnabled(false);
                    meses.setEnabled(false);
                } else {
                    txtPrecio.setEnabled(true);
                    semanas.setEnabled(true);
                    meses.setEnabled(true);
                }
                telVal = true;
            } else {
                txtPrecio.setEnabled(false);
                semanas.setEnabled(false);
                meses.setEnabled(false);
                txtTel.requestFocus();
                telVal = false;
            }
        }

        if (refVal && telVal && docVal) {

            if (img.appConfig.validarValor(Double.parseDouble(txtPrecio.getText()))) {
                preVal = true;
            } else {
                preVal = false;
                txtPrecio.requestFocus();
            }
        }

        if (refVal && telVal && docVal && preVal
                && (meses.getSelectedItem() != "Meses" || semanas.getSelectedItem() != "Semanas")) {

            GregorianCalendar fecha = new GregorianCalendar();
            DateFormat fechaFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

            if (meses.getSelectedItem() == "Meses") {
                fecha.add(Calendar.DATE, Integer.parseInt(String.valueOf(semanas.getSelectedItem())) * 7);
            } else if (semanas.getSelectedItem() == "Semanas") {
                fecha.add(Calendar.MONTH, Integer.parseInt(String.valueOf(meses.getSelectedItem())));
            } else {
                fecha.add(Calendar.DATE, Integer.parseInt(String.valueOf(semanas.getSelectedItem())) * 7);
                fecha.add(Calendar.MONTH, Integer.parseInt(String.valueOf(meses.getSelectedItem())));
            }

            img.appConfig.setTotalPrestamo(Double.parseDouble(txtPrecio.getText()));

            // actualizar registradora
            String nom = "";

            try {
                nom = img.database.returnUser("Online");
            } catch (SQLException e2) {
                e2.printStackTrace();
            }

            try {

                if (img.database.buscarUser(nom)) {
                    img.database.registrarPrestamos(nom, img.appConfig.getTotalPrestamo());
                } else {

                    String[] datos = {"registros", nom, String.valueOf(0), String.valueOf(0.0),
                            String.valueOf(0), String.valueOf(0.0),
                            String.valueOf(img.appConfig.getTotalPrestamo())};

                    img.database.insertData(datos);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            // registrar préstamo
            try {

                String[] datos = {"prestamos", img.database.returnUser("Online"), txtnombre.getText(), txtDoc.getText(),
                        txtRef.getText(), txtTel.getText(), fechaFormat.format(fecha.getTime()), txtPrecio.getText()};

                img.database.insertData(datos);

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            img.appConfig.setTotalPrestamo(Double.parseDouble(txtPrecio.getText()));
            img.appConfig.mostrarPrestamo(fechaFormat.format(fecha.getTime()), txtnombre.getText());
            img.appConfig.setTotalPrestamo(0);

            txtnombre.setText("");
            txtRef.setText("");
            txtDoc.setText("");
            txtTel.setText("");
            txtPrecio.setText("0");
            txtnombre.requestFocus();
            meses.setSelectedItem("Meses");
            semanas.setSelectedItem("Semanas");
        } else {

            if (refVal && telVal && docVal && preVal) {
                JOptionPane.showMessageDialog(null, "<html>" + img.appConfig.styleJOption() + "<strong>Tiempo no definido</strong></html>", "Mensaje", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    // Eventos al presionar un botón
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnsalir) {
            btnSalirAP();
        } else if (e.getSource() == btnprestar) {
            btnPrestarAP();
        }
    }
}