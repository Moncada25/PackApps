package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Estructuras extends JDialog implements MouseListener {

    private JLabel btnpilas, btncolas, btnsalir, btnmatrices, welcome;
    Resources h = new Resources();

    public Estructuras(JFrame parent, boolean modal) {

        super(parent, modal);
        Componentes();
    }

    public Estructuras() {

        Componentes();
    }

    public JPanel getPanel() {

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(h.cr.bordeAzul("Select"));

        btnpilas = h.getLabel("  Pilas  ", h.cr.AZUL, panel, h.cr.MEDIUM);
        btnpilas.setBorder(h.cr.MEDIO);
        btnpilas.addMouseListener(this);

        btncolas = h.getLabel("  Colas  ", h.cr.AZUL, panel, h.cr.MEDIUM);
        btncolas.setBorder(h.cr.MEDIO);
        btncolas.addMouseListener(this);

        btnmatrices = h.getLabel("  Matrices  ", h.cr.AZUL, panel, h.cr.MEDIUM);
        btnmatrices.setBorder(h.cr.MEDIO);
        btnmatrices.addMouseListener(this);

        btnsalir = h.getLabel("  Return  ", h.cr.ROJO, panel, h.cr.MEDIUM);
        btnsalir.setBorder(h.cr.HARD);
        btnsalir.addMouseListener(this);

        return panel;
    }

    // Se crean los componentes de la ventana
    public void Componentes() {

        setDefaultCloseOperation(0);
        setIconImage(new ImageIcon(h.getImage("estructuras.png")).getImage());

        add(getPanel(), BorderLayout.SOUTH);

        ImageIcon imagen = new ImageIcon(h.getImage("estructuras_datos.jpg"));
        welcome = new JLabel();
        welcome.setIcon(imagen);
        welcome.setSize(711, 284);
        add(welcome, BorderLayout.CENTER);
    }

    private void PilasAP() {
        Pilas pilas = new Pilas(this, true);
        pilas.setBounds(0, 0, 900, 600);
        pilas.setResizable(false);
        pilas.setLocationRelativeTo(null);
        pilas.setTitle("Pilas");
        h.cr.fadeIn(pilas);
        setVisible(false);
        pilas.setVisible(true);
    }

    private void ColasAP() {
        Colas colas = new Colas(this, true);
        colas.setBounds(0, 0, 900, 600);
        colas.setResizable(false);
        colas.setLocationRelativeTo(null);
        colas.setTitle("Colas");
        h.cr.fadeIn(colas);
        setVisible(false);
        colas.setVisible(true);
    }

    private void MatricesAP() {
        Matrices matrices = new Matrices(this, true);
        matrices.setBounds(0, 0, 900, 600);
        matrices.setResizable(false);
        matrices.setLocationRelativeTo(null);
        matrices.setTitle("Matrices");
        h.cr.fadeIn(matrices);
        setVisible(false);
        matrices.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == btnsalir) {
            h.cr.fadeOut(this);
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
            btnsalir.setCursor(h.cr.MANO);
        } else if (e.getSource() == btnpilas) {
            btnpilas.setCursor(h.cr.MANO);
        } else if (e.getSource() == btncolas) {
            btncolas.setCursor(h.cr.MANO);
        } else if (e.getSource() == btnmatrices) {
            btnmatrices.setCursor(h.cr.MANO);
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