package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Otros extends JDialog implements ActionListener, MouseListener {

    private JButton btncolor, btnstart1, btnstart2, btnstart3;
    private JLabel mensaje, primero, segundo, forma1, forma2;
    private JTextField txt1, txt2, txt3;
    private JLabel red, green, blue;
    private JComboBox<String> redcom, greencom, bluecom;

    Resources h = new Resources();

    public Otros(JDialog parent, boolean modal) {

        super(parent, modal);
        componentes();
    }

    // Se crean los componentes de la ventana
    public void componentes() {

        setLayout(null);// Permite el posicionamiento absoluto de los componentes

        btnstart1 = h.getButton("Show", h.core.TEXT_COLOR, this, this);
        btnstart1.setBounds(330, 125, 75, 25);

        btnstart3 = h.getButton("Show", h.core.TEXT_COLOR, this, this);
        btnstart3.setBounds(330, 225, 75, 25);

        btnstart2 = h.getButton("Show", h.core.TEXT_COLOR, this, this);
        btnstart2.setBounds(330, 175, 75, 25);

        primero = h.getLabel("<html><strong>Generar RGB</strong></html>", h.core.MAIN_COLOR, this, h.core.MEDIUM);
        primero.setBounds(25, 70, 280, 30);

        btncolor = h.getButton("Color", null, this, this);
        btncolor.setBounds(350, 75, 70, 25);

        red = h.getLabel("<html><strong>Red</strong></html>", h.core.MAIN_COLOR, this, null);
        red.setBounds(170, 95, 50, 20);

        redcom = new JComboBox<String>();
        redcom.setBounds(170, 75, 57, 25);
        for (int i = 0; i <= 255; i++) {
            redcom.addItem(String.valueOf(i));
        }
        add(redcom);

        green = h.getLabel("<html><strong>Green</strong></html>", new Color(100, 220, 0), this, null);
        green.setBounds(230, 95, 50, 20);

        greencom = new JComboBox<String>();
        greencom.setBounds(230, 75, 57, 25);
        for (int i = 0; i <= 255; i++) {
            greencom.addItem(String.valueOf(i));
        }
        add(greencom);

        blue = h.getLabel("<html><strong>Blue</strong></html>", h.core.TEXT_COLOR, this, null);
        blue.setBounds(290, 95, 50, 20);

        bluecom = new JComboBox<String>();
        bluecom.setBounds(290, 75, 57, 25);
        for (int i = 0; i <= 255; i++) {
            bluecom.addItem(String.valueOf(i));
        }
        add(bluecom);

        forma1 = h.getLabel("<html><strong>Entero ? Binario</strong></html>", h.core.MAIN_COLOR, this, h.core.MEDIUM);
        forma1.setBounds(25, 120, 200, 30);

        segundo = h.getLabel("<html><strong>Invertir cifras</strong></html>", h.core.MAIN_COLOR, this, h.core.MEDIUM);
        segundo.setBounds(25, 220, 260, 30);

        forma2 = h.getLabel("<html><strong>Binario ? Entero</strong></html>", h.core.MAIN_COLOR, this, h.core.MEDIUM);
        forma2.setBounds(25, 170, 280, 30);

        txt1 = new JTextField();
        txt1.setBounds(200, 125, 100, 25);
        txt1.setHorizontalAlignment(JTextField.CENTER);
        add(txt1);

        txt1.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt1KeyPressed(e);
            }

            private void txt1KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt1.getText().length() > 0) {

                    h.core.enteroBinario(Integer.parseInt(txt1.getText()));

                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    h.core.campoVacio();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void keyTyped(KeyEvent e) {
                txt1KeyTyped(e);
            }

            private void txt1KeyTyped(KeyEvent e) {
                h.core.solonumeros(e.getKeyChar(), e, txt1.getText(), 9);
            }
        });

        txt2 = new JTextField();
        txt2.setBounds(200, 175, 100, 25);
        txt2.setHorizontalAlignment(JTextField.CENTER);
        add(txt2);

        txt2.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt2KeyPressed(e);
            }

            private void txt2KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt2.getText().length() > 0) {

                    h.core.binarioEntero(txt2.getText());

                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    h.core.campoVacio();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void keyTyped(KeyEvent e) {
                txt2KeyTyped(e);
            }

            private void txt2KeyTyped(KeyEvent e) {
                h.core.solobinario(e.getKeyChar(), e, txt2.getText());
            }
        });

        txt3 = new JTextField();
        txt3.setBounds(200, 225, 100, 25);
        txt3.setHorizontalAlignment(JTextField.CENTER);
        add(txt3);

        txt3.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt3KeyPressed(e);
            }

            private void txt3KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt3.getText().length() > 0) {
                    h.core.invertirCifras(txt3.getText());
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    h.core.campoVacio();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void keyTyped(KeyEvent e) {
                txt3KeyTyped(e);
            }

            private void txt3KeyTyped(KeyEvent e) {
                h.core.solonumeros(e.getKeyChar(), e, txt3.getText(), 9);
            }
        });

        mensaje = h.getLabel("<html><em><strong>Otras cositas...</strong></em></html>", h.core.MAIN_COLOR, this, h.core.BIG);
        mensaje.addMouseListener(this);
        mensaje.setBounds(120, 20, 200, 30);
    }

    public void btnColorAP() {

        String cad1 = (String) redcom.getSelectedItem();
        String cad2 = (String) greencom.getSelectedItem();
        String cad3 = (String) bluecom.getSelectedItem();

        int rojo = Integer.parseInt(cad1);
        int verde = Integer.parseInt(cad2);
        int azul = Integer.parseInt(cad3);

        Color color = new Color(rojo, verde, azul);
        btncolor.setBackground(color);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btncolor) {
            btnColorAP();
        } else if (e.getSource() == btnstart1) {

            if (txt1.getText().length() > 0) {
                h.core.enteroBinario(Integer.parseInt(txt1.getText()));
            } else {
                h.core.campoVacio();
            }

        } else if (e.getSource() == btnstart2) {

            if (txt2.getText().length() > 0) {
                h.core.binarioEntero(txt2.getText());
            } else {
                h.core.campoVacio();
            }
        } else if (e.getSource() == btnstart3) {

            if (txt3.getText().length() > 0) {
                h.core.invertirCifras(txt3.getText());
            } else {
                h.core.campoVacio();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == mensaje) {
            h.core.fadeOut(this);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

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