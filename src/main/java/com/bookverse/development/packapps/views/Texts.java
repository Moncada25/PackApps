package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
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

public class Texts extends JDialog implements MouseListener {

    private JLabel mensaje, encriptar, desencriptar, mayus, minus, salir;
    private JTextArea texto;
    private JScrollPane scroll;
    private Resources resources = new Resources();

    public Texts(JFrame parent, boolean modal) {
        super(parent, modal);
        componentes();
    }

    public Texts() {
        componentes();
    }

    public void start(JFrame parent) {
        setSize(530, 330);
        setLocationRelativeTo(parent);
        setMinimumSize(new Dimension(530, 330));
        setMaximumSize(new Dimension(1280, 720));
        setTitle("Text Editor");
        resources.core.fadeIn(this);
        parent.setVisible(false);
        setVisible(true);
    }
    
    public JPanel getPanel() {

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(resources.core.bordeAzul("Select Action"));

        encriptar = resources.getLabel("  Encrypt  ", resources.core.TEXT_COLOR, panel, resources.core.MEDIUM);
        encriptar.setBorder(resources.core.MEDIO);
        encriptar.addMouseListener(this);

        mayus = resources.getLabel("  UpperCase  ", resources.core.TEXT_COLOR, panel, resources.core.MEDIUM);
        mayus.setBorder(resources.core.MEDIO);
        mayus.addMouseListener(this);

        salir = resources.getLabel("  Return  ", resources.core.MAIN_COLOR, panel, resources.core.MEDIUM);
        salir.setBorder(resources.core.HARD);
        salir.addMouseListener(this);

        minus = resources.getLabel("  LowerCase  ", resources.core.TEXT_COLOR, panel, resources.core.MEDIUM);
        minus.setBorder(resources.core.MEDIO);
        minus.addMouseListener(this);

        desencriptar = resources.getLabel("  Decrypt  ", resources.core.TEXT_COLOR, panel, resources.core.MEDIUM);
        desencriptar.setBorder(resources.core.MEDIO);
        desencriptar.addMouseListener(this);

        return panel;
    }

    // Se crean los componentes de la ventana
    public void componentes() {

        setDefaultCloseOperation(0);

        mensaje = resources
            .getLabel("<html><strong>Write Text...</strong></html>", resources.core.MAIN_COLOR, this, resources.core.MEDIUM);
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
                        "<html>" + resources.core.styleJOption() + "<strong>Texto vacío</strong></html>", "¡Verifique!",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } else if (e.getSource() == minus) {

            if (!texto.getText().equals("")) {
                texto.setText(texto.getText().toLowerCase());
            } else {
                JOptionPane.showMessageDialog(null,
                        "<html>" + resources.core.styleJOption() + "<strong>Texto vacío</strong></html>", "¡Verifique!",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } else if (e.getSource() == encriptar) {

            if (!texto.getText().equals("")) {
                texto.setText(resources.core.Encriptar(texto.getText(), false));
            } else {
                JOptionPane.showMessageDialog(null,
                        "<html>" + resources.core.styleJOption() + "<strong>Texto vacío</strong></html>", "¡Verifique!",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } else if (e.getSource() == desencriptar) {

            if (!texto.getText().equals("")) {
                texto.setText(resources.core.Desencriptar(texto.getText(), false));
            } else {
                JOptionPane.showMessageDialog(null,
                        "<html>" + resources.core.styleJOption() + "<strong>Texto vacío</strong></html>", "¡Verifique!",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } else if (e.getSource() == salir) {
            resources.core.fadeOut(this);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == mayus) {
            mayus.setCursor(resources.core.MANO);
        } else if (e.getSource() == minus) {
            minus.setCursor(resources.core.MANO);
        } else if (e.getSource() == encriptar) {
            encriptar.setCursor(resources.core.MANO);
        } else if (e.getSource() == desencriptar) {
            desencriptar.setCursor(resources.core.MANO);
        } else if (e.getSource() == salir) {
            salir.setCursor(resources.core.MANO);
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