package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Adivinar extends JDialog implements ActionListener {

    private JButton btnplay, btnsalir;
    private JLabel mensaje, resp, men, x;
    private JTextField numero;
    private int numero_usuario, numero_aleatorio;
    private int min = 1, max;
    private int intentos = 0;
    private boolean isHard;
    public Resources h = new Resources();

    public Adivinar(JFrame parent, boolean modal, boolean isHard) {

        super(parent, modal);
        this.isHard = isHard;
        Componentes();

    }

    public Adivinar() {
        Componentes();
    }

    // Se crean los componentes de la ventana
    public void Componentes() {

        setLayout(null);
        setDefaultCloseOperation(0);
        setIconImage(new ImageIcon(h.getImage("adivinar.png")).getImage());

        btnsalir = h.getBoton("Return", h.cr.ROJO, this, this);
        btnsalir.setBounds(310, 245, 86, 30);

        btnplay = h.getBoton("Play", h.cr.AZUL, this, this);
        btnplay.setBounds(310, 200, 86, 30);

        numero = new JTextField();
        numero.setBounds(180, 190, 80, 35);
        numero.setHorizontalAlignment(JTextField.CENTER);
        add(numero);
        numero.setVisible(false);

        numero.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txtNumKeyPressed(e);
            }

            // Eventos al presionar una tecla
            public void txtNumKeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    try {

                        if (!numero.getText().equals("")) {
                            numero_usuario = Integer.parseInt(numero.getText());
                            adivinar_numero();
                            numero.setText("");

                            if (isHard) {
                                resp.setVisible(false);
                            } else {
                                men.setVisible(false);
                            }

                            numero.requestFocus();
                        } else {
                            men.setVisible(false);
                            resp.setVisible(true);
                            resp.setText("<html>"
                                    + "<center><strong>No se ha ingresado nada</strong></center>"
                                    + "</html>");
                        }

                    } catch (Exception e2) {
                        // JOptionPane.showMessageDialog(null, e2.getMessage(), "Error",
                        // JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            public void keyTyped(KeyEvent e) {
                txtNumKeyTyped(e);
            }

            public void txtNumKeyTyped(KeyEvent e) {
                h.cr.solonumeros(e.getKeyChar(), e, numero.getText(), 6);
            }
        });

        mensaje = h.getLabel("", h.cr.ROJO, this, h.cr.BIG);
        mensaje.setBounds(90, 5, 320, 100);

        resp = h.getLabel("", h.cr.AZUL, this, h.cr.MEDIUM);
        resp.setBounds(90, 110, 300, 70);

        men = h.getLabel("", h.cr.AZUL, this, h.cr.MEDIUM);
        men.setBounds(90, 110, 300, 70);

        x = h.getLabel("", null, this, null);
        x.setBounds(30, 200, 80, 80);
    }

    public void btnPlayAP() {

        max = Integer.parseInt(h.cr.ingreseNumero("Número máximo a adivinar", 6));

        numero_aleatorio = h.cr.enteroAleatorio(min, max);

        mensaje.setText("<html><em>"
                + "<center><strong>¡Adivina el número!</strong></center>"
                + "<center>Está entre el " + min + " y el " + max + "</center>"
                + "<em></html>");

        men.setText("<html>"
                + "<center><strong>¿Qué número estoy pensando?</strong></center>"
                + "</html>");
        men.setVisible(true);
        x.setIcon(new ImageIcon(h.getImage("x.png")));

        numero.setVisible(true);
        btnplay.setEnabled(false);

        if (isHard) {
            btnsalir.setEnabled(true);
        } else {
            btnsalir.setEnabled(false);
        }

        intentos = 0;

        numero.setText("");
    }

    // Eventos al presionar un botón
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnsalir) {
            h.cr.fadeOut(this);
        } else if (e.getSource() == btnplay) {
            btnPlayAP();
        }
    }

    // Se ejecuta el juego
    public void adivinar_numero() {

        if (numero_usuario < numero_aleatorio) {
            intentos++;
            resp.setText("<html>"
                    + "<center><strong>El número que buscas es mayor</strong></center>"
                    + "</html>");

        } else if (numero_usuario > numero_aleatorio) {
            intentos++;
            resp.setText("<html>"
                    + "<center><strong>El número que buscas es menor</strong></center>"
                    + "</html>");
        } else {
            intentos++;
            men.setText("");
            resp.setVisible(true);
            resp.setBounds(115, 110, 300, 70);
            resp.setText("<html>"
                    + "<center><strong>¡Lo has encontrado!</strong></center>"
                    + "<center>El número era " + numero_aleatorio + "</center>"
                    + "<center>Te ha tomado " + intentos + " intento(s)</center>"
                    + "</html>");
            btnplay.setEnabled(true);
            btnsalir.setEnabled(true);

            if (h.cr.comprobarConexion("Datos no guardados", true) && h.cr.saveGame()) {

                String level = "";

                if (isHard) {
                    level = "Hard";
                } else {
                    level = "Easy";
                }

                try {
                    String data[] = {"adivinar", h.cr.ingreseNickname("Ingrese un Nickname", 20), String.valueOf(max), level + " - " + String.valueOf(intentos),
                            h.cr.obtenerDate()};
                    h.db.insertData(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            numero.setVisible(false);
            mensaje.setText("");
            resp.setText("");
            resp.setBounds(90, 110, 300, 70);
            x.setIcon(null);
        }
    }
}