package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Structures extends JDialog implements MouseListener {

  Resources resources = new Resources();
  private JLabel btnpilas, btncolas, btnsalir, btnmatrices, welcome;

  public Structures(JFrame parent, boolean modal) {

    super(parent, modal);
    Componentes();
  }

  public Structures() {

    Componentes();
  }

  public JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBorder(resources.appConfig.getBorder("Select"));

    btnpilas = resources.getLabel("  Pilas  ", resources.appConfig.TEXT_COLOR, panel, resources.appConfig.MEDIUM);
    btnpilas.setBorder(resources.appConfig.BORDER_BLUE);
    btnpilas.addMouseListener(this);

    btncolas = resources.getLabel("  Colas  ", resources.appConfig.TEXT_COLOR, panel, resources.appConfig.MEDIUM);
    btncolas.setBorder(resources.appConfig.BORDER_BLUE);
    btncolas.addMouseListener(this);

    btnmatrices = resources
        .getLabel("  Matrices  ", resources.appConfig.TEXT_COLOR, panel, resources.appConfig.MEDIUM);
    btnmatrices.setBorder(resources.appConfig.BORDER_BLUE);
    btnmatrices.addMouseListener(this);

    btnsalir = resources.getLabel("  Return  ", resources.appConfig.MAIN_COLOR, panel, resources.appConfig.MEDIUM);
    btnsalir.setBorder(resources.appConfig.BORDER_RED);
    btnsalir.addMouseListener(this);

    return panel;
  }

  public void start(JFrame parent) {
    setSize(717, 380);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Estructuras de Datos");
    resources.appConfig.fadeIn(this);
    parent.setVisible(false);
    resources.appConfig.instruccionesEstructuras();
    setVisible(true);
  }

  // Se crean los componentes de la ventana
  public void Componentes() {

    setDefaultCloseOperation(0);
    setIconImage(new ImageIcon(resources.getImage("estructuras.png")).getImage());

    add(getPanel(), BorderLayout.SOUTH);

    ImageIcon imagen = new ImageIcon(resources.getImage("estructuras_datos.jpg"));
    welcome = new JLabel();
    welcome.setIcon(imagen);
    welcome.setSize(711, 284);
    add(welcome, BorderLayout.CENTER);
  }

  private void PilasAP() {
    Stack pilas = new Stack(this, true);
    pilas.setBounds(0, 0, 900, 600);
    pilas.setResizable(false);
    pilas.setLocationRelativeTo(null);
    pilas.setTitle("Pilas");
    resources.appConfig.fadeIn(pilas);
    setVisible(false);
    pilas.setVisible(true);
  }

  private void ColasAP() {
    Queue queue = new Queue(this, true);
    queue.setBounds(0, 0, 900, 600);
    queue.setResizable(false);
    queue.setLocationRelativeTo(null);
    queue.setTitle("Colas");
    resources.appConfig.fadeIn(queue);
    setVisible(false);
    queue.setVisible(true);
  }

  private void MatricesAP() {
    Matrices matrices = new Matrices(this, true);
    matrices.setBounds(0, 0, 900, 600);
    matrices.setResizable(false);
    matrices.setLocationRelativeTo(null);
    matrices.setTitle("Matrices");
    resources.appConfig.fadeIn(matrices);
    setVisible(false);
    matrices.setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == btnsalir) {
      resources.appConfig.fadeOut(this);
    } else if (e.getSource() == btnpilas) {
      PilasAP();
      setVisible(true);
    } else if (e.getSource() == btncolas) {
      ColasAP();
      setVisible(true);
    } else if (e.getSource() == btnmatrices) {
      MatricesAP();
      setVisible(true);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == btnsalir) {
      btnsalir.setCursor(resources.appConfig.HAND);
    } else if (e.getSource() == btnpilas) {
      btnpilas.setCursor(resources.appConfig.HAND);
    } else if (e.getSource() == btncolas) {
      btncolas.setCursor(resources.appConfig.HAND);
    } else if (e.getSource() == btnmatrices) {
      btnmatrices.setCursor(resources.appConfig.HAND);
    }
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
}