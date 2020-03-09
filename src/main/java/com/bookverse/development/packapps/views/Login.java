package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
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

public class Login extends JDialog implements ActionListener {

  Resources resources = new Resources();

  private JLabel ingreso, usuario, contrasena, men;
  private JButton btningresar, btnsalir, btnregistrar;
  private JTextField txtUsuario;
  private JPasswordField txtPass;

  public Login(JFrame parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  // Constructor que no recibe par�metros
  public Login() {
    componentes();
  }

  public void start(JFrame parent) {
    setSize(375, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Buy and Sell");
    resources.core.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }
  
  public void componentes() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("compraventa.png")).getImage());

    btnsalir = resources.getButton("Return", resources.core.MAIN_COLOR, this, this);
    btnsalir.setBounds(200, 225, 90, 30);

    men = resources
        .getLabel("<html><strong>�No est�s registrado?</strong></html>", resources.core.MAIN_COLOR, this,
        resources.core.SMALL);
    men.setBounds(112, 260, 300, 50);

    btnregistrar = resources.getButton("Reg�strate", resources.core.TEXT_COLOR, this, this);
    btnregistrar.setBounds(130, 300, 110, 30);

    btningresar = resources.getButton("Ingresar", resources.core.TEXT_COLOR, this, this);
    btningresar.setBounds(80, 225, 90, 30);

    ingreso = resources
        .getLabel("<html><strong><em>Iniciar Sesi�n</em></strong></html>", resources.core.MAIN_COLOR, this,
            resources.core.BIG);
    ingreso.setBounds(100, 5, 200, 40);

    usuario = resources
        .getLabel("<html><strong>Username</strong></html>", resources.core.TEXT_COLOR, this, resources.core.MEDIUM);
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
        resources.core.soloAlfa(evt.getKeyChar(), evt, txtUsuario.getText(), 12);
      }
    });

    contrasena = resources
        .getLabel("<html><strong>Password</strong></html>", resources.core.TEXT_COLOR, this, resources.core.MEDIUM);
    contrasena.setBounds(145, 135, 120, 30);

    txtPass = new JPasswordField();
    txtPass.setBounds(110, 175, 150, 30);
    txtPass.setHorizontalAlignment(JTextField.CENTER);
    add(txtPass);

    // Permite detectar lo que se escribe en el campo de contrase�a
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
        resources.core.soloAlfa(evt.getKeyChar(), evt, String.valueOf(txtPass.getPassword()), 20);
      }
    });
  }

  public void btnIngresarAP() {

    if (txtUsuario.getText().trim().equals("") || String.valueOf(txtPass.getPassword()).trim()
        .equals("")) {
      resources.core.mostrarMensaje("Advertencia", "Rellene los campos solicitados");
    } else {

      Opciones op = new Opciones(this, true);

      try {
        if (resources.database.buscarEmpleado(txtUsuario.getText(),
            resources.core.Encriptar(String.valueOf(txtPass.getPassword()), true))) {

          resources.database.login("Online", txtUsuario.getText());

          op.setSize(620, 380);
          op.setResizable(false);
          op.setLocationRelativeTo(null);
          op.setTitle("�Bienvenido " + txtUsuario.getText() + "!");
          resources.core.fadeIn(op);
          setVisible(false);
          op.setVisible(true);
          txtPass.setText("");
          txtUsuario.setText("");

        } else {
          resources.core.mostrarMensaje("Advertencia", "Datos incorrectos, int�ntelo de nuevo.");
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
    reg.setTitle("A�adir Usuario");
    resources.core.fadeIn(reg);
    setVisible(false);
    reg.setVisible(true);
    txtPass.setText("");
  }

  // Eventos al presionar un bot�n
  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnsalir) {
      resources.core.fadeOut(this);
    } else if (resources.core.comprobarConexion("Con�ctate para continuar", true)) {

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