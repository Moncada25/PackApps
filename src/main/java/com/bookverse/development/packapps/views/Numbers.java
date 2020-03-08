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

    private Resources img = new Resources();

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
        panel.setBorder(img.core.bordeAzul("Select Number"));

        btnphi = img.getLabel("  Phi ?  ", img.core.AZUL, panel, img.core.MEDIUM);
        btnphi.setBorder(img.core.MEDIO);
        btnphi.addMouseListener(this);

        btnpi = img.getLabel("  Pi ?  ", img.core.AZUL, panel, img.core.MEDIUM);
        btnpi.setBorder(img.core.MEDIO);
        btnpi.addMouseListener(this);

        btncalcular = img.getLabel("  Calculadora  ", img.core.ROJO, panel, img.core.MEDIUM);
        btncalcular.setBorder(img.core.HARD);
        btncalcular.addMouseListener(this);

        btnprimos = img.getLabel("  Primo  ", img.core.AZUL, panel, img.core.MEDIUM);
        btnprimos.setBorder(img.core.MEDIO);
        btnprimos.addMouseListener(this);

        btnotros = img.getLabel("  Otros  ", img.core.AZUL, panel, img.core.MEDIUM);
        btnotros.setBorder(img.core.MEDIO);
        btnotros.addMouseListener(this);

        return panel;
    }

    // Se crean los componentes de la ventana
    public void Componentes() {

        add(getPanel(), BorderLayout.SOUTH);

        ImageIcon imagen = new ImageIcon(img.getImage("math.jpg"));
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
        img.core.fadeIn(jd);
        setVisible(false);
        jd.setVisible(true);
    }

    private void btnPhiAP() {

        Phi jd = new Phi(this, true);
        jd.setBounds(0, 0, 440, 300);
        jd.setResizable(false);
        jd.setLocationRelativeTo(null);
        jd.setTitle("Phi ?");
        img.core.fadeIn(jd);
        setVisible(false);
        jd.setVisible(true);
    }

    private void btnCalculadoraAP() {

        Calculadora jd = new Calculadora(this, true);
        jd.setBounds(0, 0, 320, 320);
        jd.setResizable(false);
        jd.setLocationRelativeTo(null);
        jd.setTitle("Calculadora Estándar");
        img.core.fadeIn(jd);
        setVisible(false);
        jd.setVisible(true);
    }

    private void btnPrimosAP() {
        Primos jd = new Primos(this, true);
        jd.setBounds(0, 0, 430, 270);
        jd.setResizable(false);
        jd.setLocationRelativeTo(null);
        jd.setTitle("Primos");
        img.core.fadeIn(jd);
        setVisible(false);
        jd.setVisible(true);
    }

    private void btnOtrosAP() {

        Otros jd = new Otros(this, true);
        jd.setBounds(0, 0, 430, 300);
        jd.setResizable(false);
        jd.setLocationRelativeTo(null);
        jd.setTitle("Trucazos");
        img.core.fadeIn(jd);
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
            img.core.fadeOut(this);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == btnphi) {
            btnphi.setCursor(img.core.MANO);
        } else if (e.getSource() == btnotros) {
            btnotros.setCursor(img.core.MANO);
        } else if (e.getSource() == btncalcular) {
            btncalcular.setCursor(img.core.MANO);
        } else if (e.getSource() == btnpi) {
            btnpi.setCursor(img.core.MANO);
        } else if (e.getSource() == btnprimos) {
            btnprimos.setCursor(img.core.MANO);
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