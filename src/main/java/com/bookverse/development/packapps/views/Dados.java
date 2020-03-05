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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Dados extends JDialog implements ActionListener {

    private JLabel dado1, dado2, dado3, points1, points2, points3;
    private JButton btnsalir, btntirar, btnreset;
    private JTextField player1, player2, player3;
    private int puntos1 = 0, puntos2 = 0, puntos3 = 0, ronda = 1, turno = 1;
    private boolean winner = false;
    private ImageIcon icon;
    private Resources h = new Resources();

    Dado d1 = new Dado();
    Dado d2 = new Dado();
    Dado d3 = new Dado();

    public Dados(JFrame parent, boolean modal) {

        super(parent, modal);
        componentes();
    }

    public void componentes() {

        setLayout(null);
        setDefaultCloseOperation(0);
        setIconImage(new ImageIcon(h.getImage("dado.png")).getImage());

        btnsalir = h.getButton("Return", h.cr.ROJO, this, this);
        btnsalir.setBounds(330, 320, 86, 30);

        btntirar = h.getButton("Spin", h.cr.AZUL, this, this);
        btntirar.setBounds(25, 320, 86, 30);

        btnreset = h.getButton("Reset", h.cr.AZUL, this, this);
        btnreset.setBounds(185, 320, 86, 30);
        btnreset.setEnabled(false);

        dado1 = h.getLabel("", null, this, null);
        dado1.setBounds(25, 10, 80, 80);

        dado2 = h.getLabel("", null, this, null);
        dado2.setBounds(185, 10, 80, 80);

        dado3 = h.getLabel("", null, this, null);
        dado3.setBounds(330, 10, 80, 80);

        player1 = new JTextField("Player 1");
        player1.setBounds(25, 150, 100, 30);
        player1.setHorizontalAlignment(JTextField.CENTER);
        add(player1);

        player1.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                player1KeyTyped(e);
            }

            private void player1KeyTyped(KeyEvent e) {
                h.cr.soloTexto(e.getKeyChar(), e, player1.getText(), 10);
            }
        });

        points1 = h.getLabel("", h.cr.ROJO, this, h.cr.MEDIUM);
        points1.setBounds(45, 185, 120, 60);

        player2 = new JTextField("Player 2");
        player2.setBounds(170, 150, 100, 30);
        player2.setHorizontalAlignment(JTextField.CENTER);
        add(player2);

        player2.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                player2KeyTyped(e);
            }

            private void player2KeyTyped(KeyEvent e) {
                h.cr.soloTexto(e.getKeyChar(), e, player2.getText(), 10);
            }
        });

        points2 = h.getLabel("", h.cr.ROJO, this, h.cr.MEDIUM);
        points2.setBounds(195, 189, 120, 60);

        player3 = new JTextField("Player 3");
        player3.setBounds(317, 150, 100, 30);
        player3.setHorizontalAlignment(JTextField.CENTER);
        add(player3);

        player3.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                player3KeyTyped(e);
            }

            private void player3KeyTyped(KeyEvent e) {
                h.cr.soloTexto(e.getKeyChar(), e, player3.getText(), 10);
            }
        });

        points3 = h.getLabel("", h.cr.ROJO, this, h.cr.MEDIUM);
        points3.setBounds(340, 185, 120, 60);
    }

    // Constructor
    public Dados() {

        componentes();
    }

    public ImageIcon icon(int n) {

        icon = new ImageIcon(h.getImage(n + ".png"));

        return icon;
    }

    public void btnLanzarAP() {

        if (!player1.getText().equals(player2.getText()) && !player1.getText().equals(player3.getText()) && !player2.getText().equals(player3.getText()) && !player1.getText().equals("") && !player2.getText().equals("") && !player3.getText().equals("")) {

            if (btntirar.getText().equals("Spin")) {

                dado1.setIcon(new ImageIcon(h.getImage("01.gif")));
                dado2.setIcon(new ImageIcon(h.getImage("02.gif")));
                dado3.setIcon(new ImageIcon(h.getImage("03.gif")));

                btntirar.setText("Stop");
                btntirar.setBackground(h.cr.ROJO);

                btnsalir.setEnabled(false);
                player1.setEnabled(false);
                player2.setEnabled(false);
                player3.setEnabled(false);

                if (turno == 1) {
                    setTitle("Ronda " + ronda + " - Turno de " + player1.getText());
                } else if (turno == 2) {
                    setTitle("Ronda " + ronda + " - Turno de " + player2.getText());
                } else if (turno == 3) {
                    setTitle("Ronda " + ronda + " - Turno de " + player3.getText());
                }

            } else if (btntirar.getText().equals("Stop")) {

                if (ronda <= 5 && !winner) {

                    if (turno == 1) {

                        dado1.setIcon(icon(d1.Lanzar()));
                        dado2.setIcon(icon(d2.Lanzar()));
                        dado3.setIcon(icon(d3.Lanzar()));

                        // Valida si los dados son iguales
                        if ((d1.getValor() == d2.getValor()) && (d1.getValor() == d3.getValor())) {
                            JOptionPane.showMessageDialog(null, "<html>" + h.cr.styleJOption() + "<strong>" + player1.getText()
                                    + " ha ganado, ¡sacó los tres dados iguales!</strong></html>", "Felicitaciones", JOptionPane.PLAIN_MESSAGE);

                            dado1.setIcon(null);
                            dado2.setIcon(null);
                            dado3.setIcon(null);

                            insertar(player1.getText(), "¡Dados iguales!");
                            winner = true;
                            btnreset.setEnabled(true);
                            btntirar.setEnabled(false);

                        } else {
                            puntos1 += d1.getValor() + d2.getValor() + d3.getValor();
                            points1.setText("<html><center><strong>Points</strong><br>" + puntos1
                                    + "</center></html>");
                        }
                        turno = 2;
                    } else if (turno == 2) {

                        dado1.setIcon(icon(d1.Lanzar()));
                        dado2.setIcon(icon(d2.Lanzar()));
                        dado3.setIcon(icon(d3.Lanzar()));

                        // Valida si los dados son iguales
                        if ((d1.getValor() == d2.getValor()) && (d1.getValor() == d3.getValor())) {
                            JOptionPane.showMessageDialog(null, "<html>" + h.cr.styleJOption() + "<strong>" + player2.getText()
                                    + " ha ganado, ¡sacó los tres dados iguales!</strong></html>", "Felicitaciones", JOptionPane.PLAIN_MESSAGE);

                            dado1.setIcon(null);
                            dado2.setIcon(null);
                            dado3.setIcon(null);

                            insertar(player2.getText(), "¡Dados iguales!");
                            btnreset.setEnabled(true);
                            btntirar.setEnabled(false);
                            winner = true;

                        } else {
                            puntos2 += d1.getValor() + d2.getValor() + d3.getValor();
                            points2.setText("<html><center><strong>Points</strong><br>" + puntos2
                                    + "</center></html>");
                        }
                        turno = 3;
                    } else if (turno == 3) {

                        dado1.setIcon(icon(d1.Lanzar()));
                        dado2.setIcon(icon(d2.Lanzar()));
                        dado3.setIcon(icon(d3.Lanzar()));

                        // Valida si los dados son iguales
                        if ((d1.getValor() == d2.getValor()) && (d1.getValor() == d3.getValor())) {
                            JOptionPane.showMessageDialog(null, "<html>" + h.cr.styleJOption() + "<strong>" + player3.getText()
                                    + " ha ganado, ¡sacó los tres dados iguales!</strong></html>", "Felicitaciones", JOptionPane.PLAIN_MESSAGE);

                            dado1.setIcon(null);
                            dado2.setIcon(null);
                            dado3.setIcon(null);

                            insertar(player3.getText(), "¡Dados iguales!");
                            btnreset.setEnabled(true);
                            btntirar.setEnabled(false);
                            winner = true;

                        } else {
                            puntos3 += d1.getValor() + d2.getValor() + d3.getValor();
                            points3.setText("<html><center><strong>Points</strong><br>" + puntos3
                                    + "</center></html>");
                        }
                        turno = 1;
                        ronda++;
                    }
                } else if (!winner && ronda > 5) {

                    dado1.setIcon(null);
                    dado2.setIcon(null);
                    dado3.setIcon(null);

                    mayorPuntaje();
                }

                if (ronda < 6) {
                    btntirar.setText("Spin");
                    btntirar.setBackground(h.cr.AZUL);
                } else if (!winner) {
                    ronda--;
                    mayorPuntaje();
                }
            }

        } else {

            JOptionPane.showMessageDialog(null, "Algunos campos están vacíos o sus nombres están repetidos.",
                    "Advertencia", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void mayorPuntaje() {

        if (puntos1 == puntos2 && puntos1 == puntos3) {

            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>El juego ha terminado, ¡hubo un triple empate!</strong></html>", "Felicitaciones", JOptionPane.PLAIN_MESSAGE);
            insertar(player1.getText() + ", " + player2.getText() + " & " + player3.getText(),
                    puntos1 + " points");

        } else if (puntos1 == puntos2) {

            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>El juego ha terminado, ¡hubo un empate!</strong></html>", "Felicitaciones", JOptionPane.PLAIN_MESSAGE);

            insertar(player1.getText() + " & " + player2.getText(), puntos1 + " points");

        } else if (puntos1 == puntos3) {

            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>El juego ha terminado, ¡hubo un empate!</strong></html>", "Felicitaciones", JOptionPane.PLAIN_MESSAGE);
            insertar(player1.getText() + " & " + player3.getText(), puntos1 + " points");

        } else if (puntos2 == puntos3) {

            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>El juego ha terminado, ¡hubo un empate!</strong></html>", "Felicitaciones", JOptionPane.PLAIN_MESSAGE);
            insertar(player2.getText() + " & " + player3.getText(), puntos2 + " points");

        } else {

            if (puntos1 > puntos2 && puntos1 > puntos3) {
                JOptionPane.showMessageDialog(null,
                        "<html>" + h.cr.styleJOption() + "<strong>" + player1.getText()
                                + "</strong> con <strong>" + puntos1
                                + "</strong> puntos.</html>", "Jugador ganador", JOptionPane.PLAIN_MESSAGE);

                insertar(player1.getText(), puntos1 + " points");
            } else if (puntos2 > puntos1 && puntos2 > puntos3) {
                JOptionPane.showMessageDialog(null,
                        "<html>" + h.cr.styleJOption() + "<strong>" + player2.getText()
                                + "</strong> con <strong>" + puntos2
                                + "</strong> puntos.</html>", "Jugador ganador", JOptionPane.PLAIN_MESSAGE);
                insertar(player2.getText(), puntos2 + " points");
            } else {
                JOptionPane.showMessageDialog(null,
                        "<html>" + h.cr.styleJOption() + "<strong>" + player3.getText()
                                + "</strong> con <strong>" + puntos3
                                + "</strong> puntos.</html>", "Jugador ganador", JOptionPane.PLAIN_MESSAGE);
                insertar(player3.getText(), puntos3 + " points");
            }
        }

        btnreset.setEnabled(true);
        btntirar.setEnabled(false);
        setTitle("GAME OVER");
    }

    public void insertar(String name, String win) {

        if (h.cr.comprobarConexion("Datos no guardados", true) && h.cr.saveGame()) {
            try {

                String data[] = {"dados", name, win, String.valueOf(ronda), h.cr.obtenerDate()};

                h.db.insertData(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void btnResetAP() {

        ronda = 1;
        turno = 1;
        winner = false;
        setTitle("Juego de Dados ¡Lánzalos!");
        btntirar.setText("Spin");
        btntirar.setBackground(h.cr.AZUL);
        btntirar.setEnabled(true);
        btnreset.setEnabled(false);
        btnsalir.setEnabled(true);

        puntos1 = 0;
        points1.setText("");
        player1.setEnabled(true);

        puntos2 = 0;
        points2.setText("");
        player2.setEnabled(true);

        puntos3 = 0;
        points3.setText("");
        player3.setEnabled(true);

        dado1.setIcon(null);
        dado2.setIcon(null);
        dado3.setIcon(null);
    }

    // Eventos
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btntirar) {
            btnLanzarAP();
        }

        if (e.getSource() == btnsalir) {
            h.cr.fadeOut(this);
        }

        if (e.getSource() == btnreset) {
            btnResetAP();
        }
    }
}

class Dado {

    private int valor;

    public int Lanzar() {
        valor = (1 + (int) (Math.random() * 6));
        return valor;
    }

    public int getValor() {
        return valor;
    }
}