package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Primos extends JDialog implements Runnable, ActionListener, MouseListener {

    private JButton btnsalir, btnplay, btnstart, btnstart2;
    private JLabel primero, segundo, tercero, desde, hasta, mensaje, crono;
    private JTextField txtdesde, txthasta, txtverificar;
    private boolean cronometroActivo;
    private Thread hilo;
    Resources h = new Resources();

    public Primos(JDialog parent, boolean modal) {

        super(parent, modal);
        componentes();
    }

    public void componentes() {

        setLayout(null);// Permite el posicionamiento absoluto de los componentes

        btnplay = h.getBoton("Show", h.cr.AZUL, this, this);
        btnplay.setBounds(315, 195, 75, 25);

        primero = h.getLabel("<html><strong>Buscar rango</strong></html>", h.cr.ROJO, this, h.cr.MEDIUM);
        primero.setBounds(25, 70, 120, 30);

        btnstart = h.getBoton("Show", h.cr.AZUL, this, this);
        btnstart.setBounds(315, 75, 75, 25);

        segundo = h.getLabel("<html><strong>Verificar</strong></html>", h.cr.ROJO, this, h.cr.MEDIUM);
        segundo.setBounds(25, 130, 100, 30);

        txtverificar = new JTextField();
        txtverificar.setBounds(160, 135, 70, 25);
        txtverificar.setHorizontalAlignment(JTextField.CENTER);
        add(txtverificar);

        txtverificar.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txtVerificarKeyPressed(e);
            }

            private void txtVerificarKeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txtverificar.getText().length() > 0) {
                    h.cr.verificarPrimo(Integer.parseInt(txtverificar.getText()));
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    h.cr.campoVacio();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void keyTyped(KeyEvent e) {
                txtVerificarKeyTyped(e);
            }

            private void txtVerificarKeyTyped(KeyEvent e) {
                h.cr.solonumeros(e.getKeyChar(), e, txtverificar.getText(), 6);
            }
        });

        btnstart2 = h.getBoton("Show", h.cr.AZUL, this, this);
        btnstart2.setBounds(315, 135, 75, 25);

        txtdesde = new JTextField();
        txtdesde.setBounds(160, 75, 50, 25);
        txtdesde.setHorizontalAlignment(JTextField.CENTER);
        add(txtdesde);

        txtdesde.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txtDesdeKeyPressed(e);
            }

            private void txtDesdeKeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void keyTyped(KeyEvent e) {
                txtDesdeKeyTyped(e);
            }

            private void txtDesdeKeyTyped(KeyEvent e) {
                h.cr.solonumeros(e.getKeyChar(), e, txtdesde.getText(), 4);
            }
        });

        desde = h.getLabel("<html><strong>Desde</strong></html>", h.cr.AZUL, this, h.cr.SMALL);
        desde.setBounds(160, 90, 60, 30);

        txthasta = new JTextField();
        txthasta.setBounds(220, 75, 50, 25);
        txthasta.setHorizontalAlignment(JTextField.CENTER);
        add(txthasta);

        txthasta.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txtHastaKeyPressed(e);
            }

            private void txtHastaKeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void keyTyped(KeyEvent e) {
                txtHastaKeyTyped(e);
            }

            private void txtHastaKeyTyped(KeyEvent e) {
                h.cr.solonumeros(e.getKeyChar(), e, txthasta.getText(), 4);
            }
        });

        hasta = h.getLabel("<html><strong>Hasta</strong></html>", h.cr.AZUL, this, h.cr.SMALL);
        hasta.setBounds(220, 90, 60, 30);

        tercero = h.getLabel("<html><strong>Cronómetro</strong></html>", h.cr.ROJO, this, h.cr.MEDIUM);
        tercero.setBounds(25, 190, 130, 30);

        crono = h.getLabel("00:00:000", h.cr.AZUL, this, h.cr.MEDIUM);
        crono.setBounds(160, 190, 100, 30);

        mensaje = h.getLabel("<html><em><strong>Números Primos</strong></em></html>", h.cr.ROJO, this, h.cr.BIG);
        mensaje.addMouseListener(this);
        mensaje.setBounds(100, 15, 280, 40);
    }

    public void btnResetAP() {

        pararCronometro();
        btnplay.setEnabled(true);
    }

    // Se inicia el cronómetro
    public void iniciarCronometro() {

        if (cronometroActivo) {
            pararCronometro();
            cronometroActivo = false;
            btnplay.setText("Start");
        } else {
            btnplay.setText("Stop");
            cronometroActivo = true;
            hilo = new Thread((Runnable) this);
            hilo.start();
        }
    }

    // Esto es para parar el cronómetro
    public void pararCronometro() {
        cronometroActivo = false;
    }

    @Override
    public void run() {
        Integer minutos = 0, segundos = 0, milesimas = 0;
        // min es minutos, seg es segundos y mil es milesimas de segundo
        String min = "", seg = "", mil = "";
        try {
            // Mientras cronometroActivo sea verdadero entonces seguira
            // aumentando el tiempo
            while (cronometroActivo) {
                Thread.sleep(4);
                // Incrementamos 4 milesimas de segundo
                milesimas += 4;

                // Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                // y las milesimas de segundo de nuevo a 0
                if (milesimas == 1000) {
                    milesimas = 0;
                    segundos += 1;
                    // Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    // y los segundos vuelven a 0
                    if (segundos == 60) {
                        segundos = 0;
                        minutos++;
                    }
                }

                // Esto solamente es estetica para que siempre este en formato
                // 00:00:000
                if (minutos < 10) {
                    min = "0" + minutos;
                } else {
                    min = minutos.toString();
                }

                if (segundos < 10) {
                    seg = "0" + segundos;
                } else {
                    seg = segundos.toString();
                }

                if (milesimas < 10) {
                    mil = "00" + milesimas;
                } else if (milesimas < 100) {
                    mil = "0" + milesimas;
                } else {
                    mil = milesimas.toString();
                }

                // Colocamos en la etiqueta la informacion
                crono.setText(min + ":" + seg + ":" + mil);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>" + e.getMessage() + "</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnsalir) {
            dispose();
        } else if (e.getSource() == btnstart) {

            if (txtdesde.getText().length() > 0 && txthasta.getText().length() > 0) {
                h.cr.buscarPrimos(Integer.parseInt(txtdesde.getText()), Integer.parseInt(txthasta.getText()));
            } else {
                h.cr.campoVacio();
            }

        } else if (e.getSource() == btnstart2) {

            if (txtverificar.getText().length() > 0) {
                h.cr.verificarPrimo(Integer.parseInt(txtverificar.getText()));
            } else {
                h.cr.campoVacio();
            }
        } else if (e.getSource() == btnplay) {
            iniciarCronometro();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == mensaje) {
            h.cr.fadeOut(this);
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