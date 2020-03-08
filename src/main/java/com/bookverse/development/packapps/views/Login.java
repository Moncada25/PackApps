package com.bookverse.development.packapps.views;

import static javax.swing.WindowConstants.*;

import com.bookverse.development.packapps.core.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Login extends JDialog implements ActionListener {

  Resources img = new Resources();

  private JLabel ingreso, usuario, contrasena, men;
  private JButton btningresar, btnsalir, btnregistrar;
  private JTextField txtUsuario;
  private JPasswordField txtPass;

  public Login(JFrame parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  // Constructor que no recibe parámetros
  public Login() {
    componentes();
  }

  public void componentes() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(img.getImage("compraventa.png")).getImage());

    btnsalir = img.getButton("Return", img.cr.ROJO, this, this);
    btnsalir.setBounds(200, 225, 90, 30);

    men = img.getLabel("<html><strong>¿No estás registrado?</strong></html>", img.cr.ROJO, this,
        img.cr.SMALL);
    men.setBounds(112, 260, 300, 50);

    btnregistrar = img.getButton("Regístrate", img.cr.AZUL, this, this);
    btnregistrar.setBounds(130, 300, 110, 30);

    btningresar = img.getButton("Ingresar", img.cr.AZUL, this, this);
    btningresar.setBounds(80, 225, 90, 30);

    ingreso = img
        .getLabel("<html><strong><em>Iniciar Sesión</em></strong></html>", img.cr.ROJO, this,
            img.cr.BIG);
    ingreso.setBounds(100, 5, 200, 40);

    usuario = img
        .getLabel("<html><strong>Username</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
    usuario.setBounds(145, 60, 100, 30);

    txtUsuario = new JTextField();
    txtUsuario.setBounds(110, 100, 150, 30);
    txtUsuario.setHorizontalAlignment(JTextField.CENTER);
    add(txtUsuario);

    // Permite detectar lo que se escribe en el campo de usuario
    txtUsuario.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtUsuarioKeyPressed(e);
      }

      private void txtUsuarioKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnIngresarAP();
          setVisible(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent evt) {
        txtUsuarioKeyTyped(evt);
      }

      private void txtUsuarioKeyTyped(KeyEvent evt) {
        img.cr.soloAlfa(evt.getKeyChar(), evt, txtUsuario.getText(), 12);
      }
    });

    contrasena = img
        .getLabel("<html><strong>Password</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
    contrasena.setBounds(145, 135, 120, 30);

    txtPass = new JPasswordField();
    txtPass.setBounds(110, 175, 150, 30);
    txtPass.setHorizontalAlignment(JTextField.CENTER);
    add(txtPass);

    // Permite detectar lo que se escribe en el campo de contraseña
    txtPass.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtPassKeyPressed(e);
      }

      private void txtPassKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnIngresarAP();
          setVisible(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent evt) {
        txtCodKeyTyped(evt);
      }

      private void txtCodKeyTyped(KeyEvent evt) {
        img.cr.soloAlfa(evt.getKeyChar(), evt, String.valueOf(txtPass.getPassword()), 20);
      }
    });
  }

  public void btnIngresarAP() {

    if (txtUsuario.getText().trim().equals("") || String.valueOf(txtPass.getPassword()).trim()
        .equals("")) {
      img.cr.mostrarMensaje("Advertencia", "Rellene los campos solicitados");
    } else {

      Opciones op = new Opciones(this, true);

      try {
        if (img.db.buscarEmpleado(txtUsuario.getText(),
            img.cr.Encriptar(String.valueOf(txtPass.getPassword()), true))) {

          img.db.login("Online", txtUsuario.getText());

          op.setSize(620, 380);
          op.setResizable(false);
          op.setLocationRelativeTo(null);
          op.setTitle("¡Bienvenido " + txtUsuario.getText() + "!");
          img.cr.fadeIn(op);
          setVisible(false);
          op.setVisible(true);
          txtPass.setText("");
          txtUsuario.setText("");

        } else {
          img.cr.mostrarMensaje("Advertencia", "Datos incorrectos, inténtelo de nuevo.");
          txtPass.setText("");
          txtUsuario.setText("");
          txtUsuario.requestFocus();
        }
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
  }

  public void btnRegistrarAP() {

    Registrar reg = new Registrar(this, true);

    reg.setSize(460, 300);
    reg.setResizable(false);
    reg.setLocationRelativeTo(null);
    reg.setTitle("Añadir Usuario");
    img.cr.fadeIn(reg);
    setVisible(false);
    reg.setVisible(true);
    txtPass.setText("");
  }

  // Eventos al presionar un botón
  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnsalir) {
      img.cr.fadeOut(this);
    } else if (img.cr.comprobarConexion("Conéctate para continuar", true)) {

      if (e.getSource() == btningresar) {
        btnIngresarAP();
        setVisible(true);
      } else if (e.getSource() == btnregistrar) {
        btnRegistrarAP();
        setVisible(true);
      }
    }
  }
}