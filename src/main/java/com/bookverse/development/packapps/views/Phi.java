package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Phi extends JDialog implements ActionListener, MouseListener {

    private JButton btnstart, btnstart2, btnstart3;
    private JLabel primero, segundo, forma1, forma2, mensaje, valor;
    private JTextField txt1, txt2, txt3;

    Resources h = new Resources();

    public Phi(JDialog parent, boolean modal) {

        super(parent, modal);
        componentes();
    }

    public void componentes() {

        setLayout(null);

        primero = h.getLabel("<html><strong>Serie de Fibonacci</strong></html>", h.core.ROJO, this, h.core.MEDIUM);
        primero.setBounds(25, 70, 260, 30);

        forma1 = h.getLabel("<html><strong>Enésimo número de Fibonacci</strong></html>", h.core.ROJO, this, h.core.MEDIUM);
        forma1.setBounds(25, 120, 260, 30);

        btnstart = h.getButton("Show", h.core.AZUL, this, this);
        btnstart.setBounds(360, 75, 70, 25);

        txt1 = new JTextField();
        txt1.setBounds(295, 75, 50, 25);
        txt1.setHorizontalAlignment(JTextField.CENTER);
        add(txt1);

        txt1.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt1KeyPressed(e);
            }

            private void txt1KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt1.getText().length() > 0) {
                    h.core.generarFibonacci(Integer.parseInt(txt1.getText()));
                    valor.setText(String.valueOf(h.core.getPhi()) + " ...");
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
                h.core.solonumeros(e.getKeyChar(), e, txt1.getText(), 2);
            }
        });

        segundo = h.getLabel("<html><strong>Valor aproximado</strong></html>", h.core.ROJO, this, h.core.MEDIUM);
        segundo.setBounds(25, 220, 260, 30);

        valor = h.getLabel("", h.core.AZUL, this, h.core.MEDIUM);
        valor.setBounds(200, 220, 280, 30);

        forma2 = h.getLabel("<html><strong>Proporción áurea</strong></html>", h.core.ROJO, this, h.core.MEDIUM);
        forma2.setBounds(25, 170, 280, 30);

        btnstart3 = h.getButton("Show", h.core.AZUL, this, this);
        btnstart3.setBounds(360, 175, 70, 25);

        btnstart2 = h.getButton("Show", h.core.AZUL, this, this);
        btnstart2.setBounds(360, 125, 70, 25);

        txt2 = new JTextField();
        txt2.setBounds(295, 125, 50, 25);
        txt2.setHorizontalAlignment(JTextField.CENTER);
        add(txt2);

        txt2.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt2KeyPressed(e);
            }

            private void txt2KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt2.getText().length() > 0) {

                    h.core.ObtenerFibonnaci(Integer.parseInt(txt2.getText()));

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
                h.core.solonumeros(e.getKeyChar(), e, txt2.getText(), 2);
            }
        });

        txt3 = new JTextField();
        txt3.setBounds(295, 175, 50, 25);
        txt3.setHorizontalAlignment(JTextField.CENTER);
        add(txt3);

        txt3.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt3KeyPressed(e);
            }

            private void txt3KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt3.getText().length() > 0) {

                    h.core.proporcionAurea(Double.parseDouble(txt3.getText()));

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
                h.core.solonumeros(e.getKeyChar(), e, txt3.getText(), 4);
            }
        });

        mensaje = h.getLabel("<html><em><strong>Aproximarse a ?</strong></em></html>", h.core.ROJO, this, h.core.BIG);
        mensaje.addMouseListener(this);
        mensaje.setBounds(100, 20, 250, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnstart) {

            if (txt1.getText().length() > 0) {
                h.core.generarFibonacci(Integer.parseInt(txt1.getText()));
                valor.setText(String.valueOf(h.core.getPhi()) + "...");
            } else {
                h.core.campoVacio();
            }

        } else if (e.getSource() == btnstart2) {

            if (txt2.getText().length() > 0) {
                h.core.ObtenerFibonnaci(Integer.parseInt(txt2.getText()));
            } else {
                h.core.campoVacio();
            }
        } else if (e.getSource() == btnstart3) {

            if (txt3.getText().length() > 0) {
                h.core.proporcionAurea(Double.parseDouble(txt3.getText()));
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
    public void mouseEntered(MouseEvent arg0) {

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