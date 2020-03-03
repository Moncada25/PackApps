package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Textos extends JDialog implements MouseListener {

    private JLabel mensaje, encriptar, desencriptar, mayus, minus, salir;
    private JTextArea texto;
    private JScrollPane scroll;
    private Resources h = new Resources();

    public Textos(JFrame parent, boolean modal) {
        super(parent, modal);
        componentes();
    }

    public Textos() {
        componentes();
    }

    public JPanel getPanel() {

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(h.cr.bordeAzul("Select Action"));

        encriptar = h.getLabel("  Encrypt  ", h.cr.AZUL, panel, h.cr.MEDIUM);
        encriptar.setBorder(h.cr.MEDIO);
        encriptar.addMouseListener(this);

        mayus = h.getLabel("  UpperCase  ", h.cr.AZUL, panel, h.cr.MEDIUM);
        mayus.setBorder(h.cr.MEDIO);
        mayus.addMouseListener(this);

        salir = h.getLabel("  Return  ", h.cr.ROJO, panel, h.cr.MEDIUM);
        salir.setBorder(h.cr.HARD);
        salir.addMouseListener(this);

        minus = h.getLabel("  LowerCase  ", h.cr.AZUL, panel, h.cr.MEDIUM);
        minus.setBorder(h.cr.MEDIO);
        minus.addMouseListener(this);

        desencriptar = h.getLabel("  Decrypt  ", h.cr.AZUL, panel, h.cr.MEDIUM);
        desencriptar.setBorder(h.cr.MEDIO);
        desencriptar.addMouseListener(this);

        return panel;
    }

    // Se crean los componentes de la ventana
    public void componentes() {

        setDefaultCloseOperation(0);

        mensaje = h.getLabel("<html><strong>Write Text...</strong></html>", h.cr.ROJO, this, h.cr.MEDIUM);
        mensaje.setBounds(30, 15, 370, 50);
        add(mensaje, BorderLayout.NORTH);

        texto = new JTextArea();
        scroll = new JScrollPane(texto);
        scroll.setBounds(30, 60, 420, 200);
        add(scroll, BorderLayout.CENTER);

        add(getPanel(), BorderLayout.SOUTH);

        repaint();
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

        if (e.getSource() == mayus) {

            if (!texto.getText().equals("")) {
                texto.setText(texto.getText().toUpperCase());
            } else {
                JOptionPane.showMessageDialog(null,
                        "<html>" + h.cr.styleJOption() + "<strong>Texto vacío</strong></html>", "¡Verifique!",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } else if (e.getSource() == minus) {

            if (!texto.getText().equals("")) {
                texto.setText(texto.getText().toLowerCase());
            } else {
                JOptionPane.showMessageDialog(null,
                        "<html>" + h.cr.styleJOption() + "<strong>Texto vacío</strong></html>", "¡Verifique!",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } else if (e.getSource() == encriptar) {

            if (!texto.getText().equals("")) {
                texto.setText(h.cr.Encriptar(texto.getText(), false));
            } else {
                JOptionPane.showMessageDialog(null,
                        "<html>" + h.cr.styleJOption() + "<strong>Texto vacío</strong></html>", "¡Verifique!",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } else if (e.getSource() == desencriptar) {

            if (!texto.getText().equals("")) {
                texto.setText(h.cr.Desencriptar(texto.getText(), false));
            } else {
                JOptionPane.showMessageDialog(null,
                        "<html>" + h.cr.styleJOption() + "<strong>Texto vacío</strong></html>", "¡Verifique!",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } else if (e.getSource() == salir) {
            h.cr.fadeOut(this);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == mayus) {
            mayus.setCursor(h.cr.MANO);
        } else if (e.getSource() == minus) {
            minus.setCursor(h.cr.MANO);
        } else if (e.getSource() == encriptar) {
            encriptar.setCursor(h.cr.MANO);
        } else if (e.getSource() == desencriptar) {
            desencriptar.setCursor(h.cr.MANO);
        } else if (e.getSource() == salir) {
            salir.setCursor(h.cr.MANO);
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