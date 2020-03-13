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

public class Pi extends JDialog implements ActionListener, MouseListener {

    private JButton btnsalir, btnstart, btnstart2;
    private JLabel primero, segundo, forma1, forma2, mensaje;
    private JTextField txt1, txt2;

    Resources h = new Resources();

    public Pi(JDialog parent, boolean modal) {

        super(parent, modal);
        componentes();
    }

    public void componentes() {

        setLayout(null);// Permite el posicionamiento absoluto de los componentes

        primero = h.getLabel("<html><strong>Primera suma de series</strong></html>", h.appConfig.MAIN_COLOR, this, h.appConfig.MEDIUM);
        primero.setBounds(25, 70, 280, 30);

        forma1 = h.getLabel("? = 4/1 - 4/3 + 4/5 - 4/7 + 4/9...", h.appConfig.TEXT_COLOR, this, h.appConfig.MEDIUM);
        forma1.setBounds(25, 90, 280, 60);

        btnstart = h.getButton("Show", h.appConfig.TEXT_COLOR, this, this);
        btnstart.setBounds(315, 110, 75, 25);

        txt1 = new JTextField();
        txt1.setBounds(315, 75, 100, 25);
        txt1.setHorizontalAlignment(JTextField.CENTER);
        add(txt1);

        txt1.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt1KeyPressed(e);
            }

            private void txt1KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt1.getText().length() > 0) {
                    h.appConfig.calcularPi(Double.parseDouble(txt1.getText()));
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    h.appConfig.campoVacio();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void keyTyped(KeyEvent e) {
                txt1KeyTyped(e);
            }

            private void txt1KeyTyped(KeyEvent e) {
                h.appConfig.solonumeros(e.getKeyChar(), e, txt1.getText(), 9);
            }
        });

        segundo = h.getLabel("<html><strong>Segunda suma de series</strong></html>", h.appConfig.MAIN_COLOR, this, h.appConfig.MEDIUM);
        segundo.setBounds(25, 160, 280, 30);

        forma2 = h.getLabel("?²/6 = 1/1² + 1/2² + 1/3² + 1/4²...", h.appConfig.TEXT_COLOR, this, h.appConfig.MEDIUM);
        forma2.setBounds(25, 180, 280, 60);

        btnstart2 = h.getButton("Show", h.appConfig.TEXT_COLOR, this, this);
        btnstart2.setBounds(315, 200, 75, 25);

        txt2 = new JTextField();
        txt2.setBounds(315, 165, 100, 25);
        txt2.setHorizontalAlignment(JTextField.CENTER);
        add(txt2);

        txt2.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt2KeyPressed(e);
            }

            private void txt2KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt2.getText().length() > 0) {

                    h.appConfig.calcularPi2(Double.parseDouble(txt2.getText()));

                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    h.appConfig.campoVacio();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void keyTyped(KeyEvent e) {
                txt2KeyTyped(e);
            }

            private void txt2KeyTyped(KeyEvent e) {
                h.appConfig.solonumeros(e.getKeyChar(), e, txt2.getText(), 9);
            }
        });

        mensaje = h.getLabel("<html><em><strong>Aproximarse a ?</strong></em></html>", h.appConfig.MAIN_COLOR, this, h.appConfig.BIG);
        mensaje.addMouseListener(this);
        mensaje.setBounds(100, 20, 250, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnsalir) {
            dispose();
        } else if (e.getSource() == btnstart) {

            if (txt1.getText().length() > 0) {
                h.appConfig.calcularPi(Double.parseDouble(txt1.getText()));
            } else {
                h.appConfig.campoVacio();
            }

        } else if (e.getSource() == btnstart2) {

            if (txt2.getText().length() > 0) {
                h.appConfig.calcularPi2(Double.parseDouble(txt2.getText()));
            } else {
                h.appConfig.campoVacio();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == mensaje) {
            h.appConfig.fadeOut(this);
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