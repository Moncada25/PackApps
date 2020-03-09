package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Numbers extends JDialog implements MouseListener {

  private JLabel btnprimos, btncalcular, btnphi, btnpi, btnotros, welcome;

  private Resources resources = new Resources();

  public Numbers(JFrame parent, boolean modal) {

    super(parent, modal);

    Componentes();
  }

  public Numbers() {
    Componentes();
  }

  public JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBackground(new Color(0, 0, 0));
    panel.setBorder(resources.core.bordeAzul("Select Number"));

    btnphi = resources.getLabel("  Phi ?  ", resources.core.TEXT_COLOR, panel, resources.core.MEDIUM);
    btnphi.setBorder(resources.core.MEDIO);
    btnphi.addMouseListener(this);

    btnpi = resources.getLabel("  Pi ?  ", resources.core.TEXT_COLOR, panel, resources.core.MEDIUM);
    btnpi.setBorder(resources.core.MEDIO);
    btnpi.addMouseListener(this);

    btncalcular = resources.getLabel("  Calculadora  ", resources.core.MAIN_COLOR, panel, resources.core.MEDIUM);
    btncalcular.setBorder(resources.core.HARD);
    btncalcular.addMouseListener(this);

    btnprimos = resources.getLabel("  Primo  ", resources.core.TEXT_COLOR, panel, resources.core.MEDIUM);
    btnprimos.setBorder(resources.core.MEDIO);
    btnprimos.addMouseListener(this);

    btnotros = resources.getLabel("  Otros  ", resources.core.TEXT_COLOR, panel, resources.core.MEDIUM);
    btnotros.setBorder(resources.core.MEDIO);
    btnotros.addMouseListener(this);

    return panel;
  }

  public void start(JFrame parent) {
    setSize(980, 570);
    setMinimumSize(new Dimension(480, 380));
    setMaximumSize(new Dimension(1295, 820));
    setLocationRelativeTo(parent);
    setTitle("Numbers");
    resources.core.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  // Se crean los componentes de la ventana
  public void Componentes() {

    add(getPanel(), BorderLayout.SOUTH);

    ImageIcon imagen = new ImageIcon(resources.getImage("math.jpg"));
    welcome = new JLabel();
    welcome.setIcon(imagen);
    welcome.setSize(1280, 720);
    welcome.addMouseListener(this);
    add(welcome, BorderLayout.CENTER);

    repaint();
  }

  private void btnPiAP() {

    Pi jd = new Pi(this, true);
    jd.setBounds(0, 0, 430, 280);
    jd.setResizable(false);
    jd.setLocationRelativeTo(null);
    jd.setTitle("Pi ?");
    resources.core.fadeIn(jd);
    setVisible(false);
    jd.setVisible(true);
  }

  private void btnPhiAP() {

    Phi jd = new Phi(this, true);
    jd.setBounds(0, 0, 440, 300);
    jd.setResizable(false);
    jd.setLocationRelativeTo(null);
    jd.setTitle("Phi ?");
    resources.core.fadeIn(jd);
    setVisible(false);
    jd.setVisible(true);
  }

  private void btnCalculadoraAP() {

    Calculadora jd = new Calculadora(this, true);
    jd.setBounds(0, 0, 320, 320);
    jd.setResizable(false);
    jd.setLocationRelativeTo(null);
    jd.setTitle("Calculadora Estándar");
    resources.core.fadeIn(jd);
    setVisible(false);
    jd.setVisible(true);
  }

  private void btnPrimosAP() {
    Primos jd = new Primos(this, true);
    jd.setBounds(0, 0, 430, 270);
    jd.setResizable(false);
    jd.setLocationRelativeTo(null);
    jd.setTitle("Primos");
    resources.core.fadeIn(jd);
    setVisible(false);
    jd.setVisible(true);
  }

  private void btnOtrosAP() {

    Otros jd = new Otros(this, true);
    jd.setBounds(0, 0, 430, 300);
    jd.setResizable(false);
    jd.setLocationRelativeTo(null);
    jd.setTitle("Trucazos");
    resources.core.fadeIn(jd);
    setVisible(false);
    jd.setVisible(true);
  }

  @Override
  public void paint(Graphics g) {
    Dimension d = getSize();
    Dimension m = getMaximumSize();
    boolean resize = d.width > m.width || d.height > m.height;
    d.width = Math.min(m.width, d.width);
    d.height = Math.min(m.height, d.height);
    if (resize) {
      Point p = getLocation();
      setVisible(false);
      setSize(d);
      setLocation(p);
      setVisible(true);
    }
    super.paint(g);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == btnprimos) {
      btnPrimosAP();
      setVisible(true);
    } else if (e.getSource() == btncalcular) {
      btnCalculadoraAP();
      setVisible(true);
    } else if (e.getSource() == btnphi) {
      btnPhiAP();
      setVisible(true);
    } else if (e.getSource() == btnpi) {
      btnPiAP();
      setVisible(true);
    } else if (e.getSource() == btnotros) {
      btnOtrosAP();
      setVisible(true);
    } else if (e.getSource() == welcome) {
      resources.core.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == btnphi) {
      btnphi.setCursor(resources.core.MANO);
    } else if (e.getSource() == btnotros) {
      btnotros.setCursor(resources.core.MANO);
    } else if (e.getSource() == btncalcular) {
      btncalcular.setCursor(resources.core.MANO);
    } else if (e.getSource() == btnpi) {
      btnpi.setCursor(resources.core.MANO);
    } else if (e.getSource() == btnprimos) {
      btnprimos.setCursor(resources.core.MANO);
    }
  }

  @Override
  public void mouseExited(MouseEvent arg0) {

  }

  @Override
  public void mousePressed(MouseEvent arg0) {

  }

  @Override
  public void mouseReleased(MouseEvent arg0) {

  }
}