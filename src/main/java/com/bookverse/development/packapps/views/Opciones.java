package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Opciones extends JDialog implements ActionListener {

  public Compras compra = new Compras();
  public RegistradoraTabla tabla = new RegistradoraTabla();
  Prestamos pres = new Prestamos();
  Resources img = new Resources();

  private JMenuBar barra;
  private JMenu acciones, registros, exit;
  private JMenuItem vender, comprar, prestar, registradora, inventario, prestamos, compras, ventas, usuarios, salir;
  private JLabel welcome;

  public Opciones(JDialog parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  // Constructor que no recibe parámetros
  public Opciones() {

    componentes();
  }

  // Se crean los componentes de la ventana
  public void componentes() {

    setDefaultCloseOperation(0);

    ImageIcon imagen = new ImageIcon(img.getImage("tienda.jpg"));
    welcome = new JLabel();
    welcome.setIcon(imagen);
    welcome.setSize(620, 300);
    add(welcome, BorderLayout.CENTER);

    // Creo la barra de menú
    barra = new JMenuBar();

    // Pestaña de acciones y sus items
    acciones = img.getMenu("Servicios", "services");
    comprar = img.getMenuItem("Comprar", "comprar", this);
    prestar = img.getMenuItem("Prestar", "prestar", this);
    vender = img.getMenuItem("Vender", "vender", this);

    acciones.add(comprar);
    acciones.addSeparator();
    acciones.add(prestar);
    acciones.addSeparator();
    acciones.add(vender);

    // Pestaña de registros y sus items
    registros = img.getMenu("Data", "data");
    inventario = img.getMenuItem("Inventario", "inventario", this);
    registradora = img.getMenuItem("Registradora", "registradora", this);
    prestamos = img.getMenuItem("Préstamos", "prestamos", this);
    compras = img.getMenuItem("Compras", "comprar", this);
    ventas = img.getMenuItem("Ventas", "vender", this);
    usuarios = img.getMenuItem("Usuarios", "usuario", this);

    registros.add(compras);
    registros.addSeparator();
    registros.add(inventario);
    registros.addSeparator();
    registros.add(prestamos);
    registros.addSeparator();
    registros.add(registradora);
    registros.addSeparator();
    registros.add(usuarios);
    registros.addSeparator();
    registros.add(ventas);

    // Menú de exit y sus items
    exit = img.getMenu("Logout", "exit");
    salir = img.getMenuItem("Cerrar sesión", "logout", this);

    exit.add(salir);

    // Añado los menús a la barra
    barra.add(acciones);
    barra.add(registros);
    barra.add(exit);

    // Añado la barra de menú al frame
    add(barra, BorderLayout.NORTH);
  }

  public void btnComprarAP() {
    Compras compra = new Compras(this, true);
    compra.setSize(440, 400);
    compra.setResizable(false);
    compra.setLocationRelativeTo(null);
    compra.setTitle("Comprar");
    img.cr.fadeIn(compra);
    setVisible(false);
    compra.setVisible(true);
  }

  public void btnVenderAP() {
    Ventas vent = new Ventas(this, true);
    vent.setSize(440, 400);
    vent.setResizable(false);
    vent.setLocationRelativeTo(null);
    vent.setTitle("Vender");
    img.cr.fadeIn(vent);
    setVisible(false);
    vent.setVisible(true);
  }

  public void btnPrestarAP() {
    Prestamos pres = new Prestamos(this, true);
    pres.setSize(450, 400);
    pres.setResizable(false);
    pres.setLocationRelativeTo(null);
    pres.setTitle("Prestar");
    img.cr.fadeIn(pres);
    setVisible(false);
    pres.setVisible(true);
  }

  public void btnRegistrosAP() {

    RegistradoraTabla tab = new RegistradoraTabla(this, true);

    tab.limpiarTabla();

    // importa los registros desde sql
    try {
      img.db.importarTabla(tab.registradoraTab,
          "select Usuario, Productos_Vendidos,Total_Ventas,Productos_Comprados,Total_Compras, Total_Prestamos from registros",
          true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    tab.setSize(950, 400);
    tab.setMinimumSize(new Dimension(950, 400));
    tab.setMaximumSize(new Dimension(1280, 720));
    tab.setLocationRelativeTo(null);
    tab.setTitle("Registradora");
    img.cr.fadeIn(tab);
    setVisible(false);
    tab.setVisible(true);
  }

  public void btnInventarioAP(boolean buscar) {

    InventarioTabla inv = new InventarioTabla(this, true, buscar);

    inv.limpiarTabla();

    // importa el inventario desde sql
    try {
      img.db.importarTabla(inv.inventarioTab, "select * from inventario", true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    inv.setSize(830, 400);
    inv.setMinimumSize(new Dimension(830, 400));
    inv.setMaximumSize(new Dimension(1280, 720));
    inv.setLocationRelativeTo(null);
    inv.setTitle("Productos");
    img.cr.fadeIn(inv);
    setVisible(false);
    inv.setVisible(true);
  }

  public void btnPrestamosAP() {

    PrestamosTabla tabP = new PrestamosTabla(this, true);

    tabP.limpiarTabla();

    // importa los préstamos desde sql
    try {
      img.db.importarTabla(tabP.prestamosTab,
          "select Usuario, Nombre, Documento, Referencia, Teléfono, Plazo, Valor from préstamos",
          true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    tabP.setSize(830, 400);
    tabP.setMinimumSize(new Dimension(830, 400));
    tabP.setMaximumSize(new Dimension(1280, 720));
    tabP.setLocationRelativeTo(null);
    tabP.setTitle("Préstamos");
    img.cr.fadeIn(tabP);
    setVisible(false);
    tabP.setVisible(true);
  }

  public void btnComprasAP() {

    ComprasTabla tabP = new ComprasTabla(this, true);

    tabP.limpiarTabla();

    try {
      img.db.importarTabla(tabP.comprasTab,
          "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from compras",
          true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    tabP.setSize(830, 400);
    tabP.setMinimumSize(new Dimension(830, 400));
    tabP.setMaximumSize(new Dimension(1280, 720));
    tabP.setLocationRelativeTo(null);
    tabP.setTitle("Compras");
    img.cr.fadeIn(tabP);
    setVisible(false);
    tabP.setVisible(true);
  }

  public void btnVentasAP() {

    VentasTabla tabP = new VentasTabla(this, true);

    tabP.limpiarTabla();

    try {
      img.db.importarTabla(tabP.ventasTab,
          "select IDPRODUCTO, Usuario, Documento, Telefono, Date, Unidades, Total from ventas",
          true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    tabP.setSize(830, 400);
    tabP.setMinimumSize(new Dimension(830, 400));
    tabP.setMaximumSize(new Dimension(1280, 720));
    tabP.setLocationRelativeTo(null);
    tabP.setTitle("Ventas");
    img.cr.fadeIn(tabP);
    setVisible(false);
    tabP.setVisible(true);
  }

  public void btnUsuariosAP() {

    UsuariosTabla tabP = new UsuariosTabla(this, true);

    tabP.limpiarTabla();

    try {
      img.db.importarTabla(tabP.usuariosTab, "select User_Name, Status from usuarios", true);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    tabP.setSize(520, 400);
    tabP.setMinimumSize(new Dimension(520, 400));
    tabP.setMaximumSize(new Dimension(1280, 720));
    tabP.setLocationRelativeTo(null);
    tabP.setTitle("Usuarios Registrados");
    img.cr.fadeIn(tabP);
    setVisible(false);
    tabP.setVisible(true);
  }

  public void loguot() {

    try {
      img.db.login("Offline", img.db.returnUser("Online"));
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    img.cr.fadeOut(this);
  }

  // Eventos al presionar un botón
  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == salir) {
      loguot();
    } else {

      if (img.cr.comprobarConexion("No disponible sin conexión", true)) {

        if (e.getSource() == comprar) {
          btnComprarAP();
          setVisible(true);
        } else if (e.getSource() == vender) {
          btnVenderAP();
          setVisible(true);
        } else if (e.getSource() == prestar) {
          btnPrestarAP();
          setVisible(true);
        } else if (e.getSource() == registradora) {
          btnRegistrosAP();
          setVisible(true);
        } else if (e.getSource() == inventario) {
          btnInventarioAP(false);
          setVisible(true);
        } else if (e.getSource() == prestamos) {
          btnPrestamosAP();
          setVisible(true);
        } else if (e.getSource() == compras) {
          btnComprasAP();
          setVisible(true);
        } else if (e.getSource() == ventas) {
          btnVentasAP();
          setVisible(true);
        } else if (e.getSource() == usuarios) {
          btnUsuariosAP();
          setVisible(true);
        }
      }
    }
  }
}