package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.*;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Registrar extends JDialog implements ActionListener {

    Resources resources = new Resources();
    public HomeStore op = new HomeStore();

    private JLabel registrarse, usuario, pass, codEncargado;
    private JTextField txtUsuario;
    private JButton btnregistro, btncancelar;
    private JPasswordField txtPass, txtCodProp;

    public Registrar(JDialog parent, boolean modal) {

        super(parent, modal);

        componentes();
    }

    // Se crean los componentes de la ventana
    public void componentes() {

        setLayout(null);// Permite el posicionamiento absoluto de los componentes
        setIconImage(new ImageIcon(resources.getImage("añadir_usuario.png")).getImage());
        setDefaultCloseOperation(0);

        btnregistro = resources.getButton("Registrar", TEXT_COLOR, this, this);
        btnregistro.setBounds(60, 215, 100, 30);

        btncancelar = resources.getButton("Return", MAIN_COLOR, this, this);
        btncancelar.setBounds(300, 215, 86, 30);

        registrarse = resources
            .getLabel("<html><strong><em>Registrarse</em></strong></html>", MAIN_COLOR, this, BIG);
        registrarse.setBounds(150, 5, 200, 40);

        usuario = resources
            .getLabel("<html><strong>Username</strong></html>", TEXT_COLOR, this, MEDIUM);
        usuario.setBounds(30, 60, 180, 30);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(250, 65, 150, 30);
        txtUsuario.setHorizontalAlignment(JTextField.CENTER);
        add(txtUsuario);

        // Permite detectar lo que se escribe en el campo de usuario
        txtUsuario.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }

            private void txtUsuarioKeyTyped(KeyEvent evt) {
                Format.onlyAlfa(evt.getKeyChar(), evt, txtUsuario.getText(), 12);
            }
        });

        pass = resources
            .getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this, MEDIUM);
        pass.setBounds(30, 110, 120, 30);

        txtPass = new JPasswordField();
        txtPass.setHorizontalAlignment(JTextField.CENTER);
        txtPass.setBounds(250, 115, 150, 30);
        add(txtPass);

        txtPass.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtCodKeyTyped(evt);
            }

            private void txtCodKeyTyped(KeyEvent evt) {
                Format.onlyAlfa(evt.getKeyChar(), evt, String.valueOf(txtPass.getPassword()), 20);
            }
        });

        codEncargado = resources.getLabel("<html><strong>Manager Key</strong></html>", TEXT_COLOR, this, MEDIUM);
        codEncargado.setBounds(30, 160, 180, 30);

        txtCodProp = new JPasswordField();
        txtCodProp.setHorizontalAlignment(JTextField.CENTER);
        txtCodProp.setBounds(250, 165, 150, 30);
        add(txtCodProp);

        // Permite detectar lo que se escribe en el campo de usuario
        txtCodProp.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent evt) {
                txtCodKeyTyped(evt);
            }

            private void txtCodKeyTyped(KeyEvent evt) {
                Format.onlyAlfa(evt.getKeyChar(), evt, String.valueOf(txtCodProp.getPassword()), 20);
            }
        });
    }

    // Constructor que no recibe parámetros
    public Registrar() {
        componentes();
    }

    public void btnCancelarAP() {

        txtUsuario.setText("");
        txtPass.setText("");
        txtCodProp.setText("");
        txtUsuario.setEnabled(true);
        txtPass.setEnabled(true);
        fadeOut(this);
    }

    public void btnRegistroAP() {

        if (!Database.searchUser(txtUsuario.getText())) {

            if (String.valueOf(txtCodProp.getPassword()).equals("123")) {
                txtUsuario.setEnabled(true);
                txtPass.setEnabled(true);

                if (Format.verifyDocument(txtUsuario.getText())
                        && Format.verifyDocument(String.valueOf(txtPass.getPassword()))) {

                    String[] datos = {"usuarios", txtUsuario.getText(), AppConfig.encrypt(String.valueOf(txtPass.getPassword()), true),
                            "Offline"};

                    Database.insertData(datos);

                    JOptionPane.showMessageDialog(null, "<html>" + Format.style() + "<strong>¡Usuario registrado!</strong></html>", "Mensaje",
                            JOptionPane.PLAIN_MESSAGE);
                    txtUsuario.setText("");
                    txtPass.setText("");
                    txtCodProp.setText("");
                    fadeOut(this);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "<html>" + Format.style() + "<strong>El nombre de usuario y/o la contraseña son demasiado débiles, inténtalo nuevamente.</strong></html>", "Mensaje",
                            JOptionPane.PLAIN_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "<html>" + Format.style() + "<strong>Código de gerente incorrecto, inténtelo de nuevo.</strong></html>", "Mensaje",
                        JOptionPane.PLAIN_MESSAGE);
                txtUsuario.setEnabled(false);
                txtPass.setEnabled(false);
                txtCodProp.setText("");
                txtCodProp.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(null, "<html>" + Format.style() + "<strong>El usuario ya existe, intente iniciando sesión.</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
            fadeOut(this);
        }

    }

    // Eventos al presionar un botón
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btncancelar) {
            btnCancelarAP();
        } else if (e.getSource() == btnregistro) {
            btnRegistroAP();
        }
    }
}