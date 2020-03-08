package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Triqui extends JDialog implements ActionListener {

    private JLabel txtnom1, txtnom2, txtturno, pointsX, pointsO, img;
    private JButton btnsalir, btnreset, btnplay;
    public static JButton[][] tablero;
    public JTextField nombre1, nombre2;
    public int turno = 0;
    public static int jugadasX = 0;
    public int jugadasO = 0;
    public int Tf;
    public int Tc;
    public int sw = 0;
    public int lim = 0;
    public int lim2;
    public int pX = 0;
    public int pO = 0;
    private boolean win = false;

    Resources h = new Resources();
    public Color colorO = h.core.ROJO;
    public Color colorX = h.core.AZUL;
    public Color color = new Color(100, 220, 0);
    public boolean vsCPU;

    // Constructor que recibe la ventana padre y el valor modal
    public Triqui(JFrame parent, boolean modal, boolean vsCPU) {

        super(parent, modal);
        this.vsCPU = vsCPU;
        componentes();
    }

    // Se crean los componentes de la ventana
    public void componentes() {

        setLayout(null);
        setDefaultCloseOperation(0);
        setIconImage(new ImageIcon(new Resources().getImage("triqui.png")).getImage());

        btnsalir = h.getButton("Return", h.core.ROJO, this, this);
        btnsalir.setBounds(310, 300, 86, 30);

        btnreset = h.getButton("Reset", h.core.AZUL, this, this);
        btnreset.setEnabled(false);
        btnreset.setBounds(180, 300, 86, 30);

        btnplay = h.getButton("Play", h.core.AZUL, this, this);
        btnplay.setBounds(50, 300, 86, 30);

        tablero = new JButton[3][3];
        int x = 50;
        int y = 50;

        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                tablero[f][c] = h.getButton("", null, this, this);
                tablero[f][c].setBounds(x, y, 70, 70);
                tablero[f][c].setFont(new Font("Times New Roman", 0, 45));
                tablero[f][c].setOpaque(true);
                x = x + 70;
            }
            x = 50;
            y = y + 70;
        }

        txtnom1 = h.getLabel("<html><em><strong>Player X</strong></em></html>", colorX, this, h.core.MEDIUM);
        txtnom1.setBounds(50, 10, 100, 30);

        pointsX = h.getLabel("", color, this, h.core.BIG);
        pointsX.setBounds(130, 10, 100, 30);

        if (!vsCPU) {
            txtnom2 = h.getLabel("<html><em><strong>Player O</strong></em></html>", colorO, this, h.core.MEDIUM);
            txtnom2.setBounds(260, 10, 100, 30);
        } else {
            txtnom2 = h.getLabel("<html><em><strong>CPU O</strong></em></html>", colorO, this, h.core.MEDIUM);
            txtnom2.setBounds(273, 10, 100, 30);
        }

        pointsO = h.getLabel("", color, this, h.core.BIG);
        pointsO.setBounds(340, 10, 100, 30);

        txtturno = h.getLabel("", null, this, h.core.MEDIUM);
        txtturno.setBounds(270, 200, 200, 100);

        img = h.getLabel("", null, this, null);
        img.setBounds(300, 90, 96, 96);

        nombre1 = new JTextField("X");

        if (!vsCPU) {
            nombre2 = new JTextField("O");
        } else {
            nombre2 = new JTextField("CPU");
        }
    }

    // Constructor que no recibe parámetros
    public Triqui() {
        componentes();
    }

    // Bloquea y pinta los botones al ganar
    public void blockTab(String win) {

        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {

                if (tablero[f][c].getText().equals(win)) {

                    if (win.equals("X")) {
                        tablero[f][c].setBackground(colorX);
                        tablero[f][c].setForeground(getForeground());
                    } else {
                        tablero[f][c].setBackground(colorO);
                        tablero[f][c].setForeground(getForeground());
                    }
                }
            }
        }
        turno = 0;
    }

    // cuenta los puntos de cada jugador
    public void countPoints(String win) {

        if (win.equals("X")) {

            switch (pX) {
                case 0:
                    pointsX.setText("<html><em><strong>|</strong></em></html>");
                    pX++;
                    break;
                case 1:
                    pointsX.setText("<html><em><strong>||</strong></em></html>");
                    pX++;
                    break;
                case 2:
                    pointsX.setText("<html><em><strong>|||</strong></em></html>");
                    pX++;
                    break;
                case 3:
                    pointsX.setText("<html><em><strong>||||</strong></em></html>");
                    pX++;
                    break;
                case 4:
                    pointsX.setText("<html><em><strong>|||||</strong></em></html>");
                    pX++;
                    break;
                default:
                    break;
            }

            if (pX == 5) {
                txtturno.setText("<html><em><strong>¡Champion " + nombre1.getText() + "!</strong></em></html>");
                img.setIcon(new ImageIcon(new Resources().getImage("triquito.png")));
            }

        } else {

            switch (pO) {
                case 0:
                    pointsO.setText("<html><em><strong>|</strong></em></html>");
                    pO++;
                    break;
                case 1:
                    pointsO.setText("<html><em><strong>||</strong></em></html>");
                    pO++;
                    break;
                case 2:
                    pointsO.setText("<html><em><strong>|||</strong></em></html>");
                    pO++;
                    break;
                case 3:
                    pointsO.setText("<html><em><strong>||||</strong></em></html>");
                    pO++;
                    break;
                case 4:
                    pointsO.setText("<html><em><strong>|||||</strong></em></html>");
                    pO++;
                    break;
                default:
                    break;
            }

            if (pO == 5) {
                txtturno.setText("<html><em><strong>¡Champion " + nombre2.getText() + "!</strong></em></html>");
                img.setIcon(new ImageIcon(new Resources().getImage("triquito.png")));
            }
        }
    }

    // Condiciones para la victoria
    public void winner() {

        // Player 1
        if (tablero[0][0].getText().equals("X") && tablero[0][1].getText().equals("X")
                && tablero[0][2].getText().equals("X")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre1.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorX);
            win = true;

            countPoints("X");
            blockTab("X");

        } else if (tablero[1][0].getText().equals("X") && tablero[1][1].getText().equals("X")
                && tablero[1][2].getText().equals("X")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre1.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorX);
            win = true;

            countPoints("X");
            blockTab("X");

        } else if (tablero[2][0].getText().equals("X") && tablero[2][1].getText().equals("X")
                && tablero[2][2].getText().equals("X")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre1.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorX);
            win = true;

            countPoints("X");
            blockTab("X");

        } else if (tablero[0][0].getText().equals("X") && tablero[1][0].getText().equals("X")
                && tablero[2][0].getText().equals("X")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre1.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorX);
            win = true;

            countPoints("X");
            blockTab("X");

        } else if (tablero[0][1].getText().equals("X") && tablero[1][1].getText().equals("X")
                && tablero[2][1].getText().equals("X")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre1.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorX);
            win = true;

            countPoints("X");
            blockTab("X");

        } else if (tablero[0][2].getText().equals("X") && tablero[1][2].getText().equals("X")
                && tablero[2][2].getText().equals("X")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre1.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorX);
            win = true;

            countPoints("X");
            blockTab("X");

        } else if (tablero[0][0].getText().equals("X") && tablero[1][1].getText().equals("X")
                && tablero[2][2].getText().equals("X")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre1.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorX);
            win = true;

            countPoints("X");
            blockTab("X");

        } else if (tablero[0][2].getText().equals("X") && tablero[1][1].getText().equals("X")
                && tablero[2][0].getText().equals("X")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre1.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorX);
            win = true;

            countPoints("X");
            blockTab("X");
        }

        // Player 2
        if (tablero[0][0].getText().equals("O") && tablero[0][1].getText().equals("O")
                && tablero[0][2].getText().equals("O")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre2.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorO);
            win = true;

            countPoints("O");
            blockTab("O");

        } else if (tablero[1][0].getText().equals("O") && tablero[1][1].getText().equals("O")
                && tablero[1][2].getText().equals("O")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre2.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorO);
            win = true;

            countPoints("O");
            blockTab("O");

        } else if (tablero[2][0].getText().equals("O") && tablero[2][1].getText().equals("O")
                && tablero[2][2].getText().equals("O")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre2.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorO);
            win = true;

            countPoints("O");
            blockTab("O");

        } else if (tablero[0][0].getText().equals("O") && tablero[1][0].getText().equals("O")
                && tablero[2][0].getText().equals("O")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre2.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorO);
            win = true;

            countPoints("O");
            blockTab("O");

        } else if (tablero[0][1].getText().equals("O") && tablero[1][1].getText().equals("O")
                && tablero[2][1].getText().equals("O")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre2.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorO);
            win = true;

            countPoints("O");
            blockTab("O");

        } else if (tablero[0][2].getText().equals("O") && tablero[1][2].getText().equals("O")
                && tablero[2][2].getText().equals("O")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre2.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorO);
            win = true;

            countPoints("O");
            blockTab("O");

        } else if (tablero[0][0].getText().equals("O") && tablero[1][1].getText().equals("O")
                && tablero[2][2].getText().equals("O")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre2.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorO);
            win = true;

            countPoints("O");
            blockTab("O");

        } else if (tablero[0][2].getText().equals("O") && tablero[1][1].getText().equals("O")
                && tablero[2][0].getText().equals("O")) {
            txtturno.setText("<html><em><strong>¡Winner " + nombre2.getText() + "!</strong></em></html>");
            txtturno.setForeground(colorO);
            win = true;

            countPoints("O");
            blockTab("O");
        }
    }

    public void btnPlayAP() {

        if (!win) {
            txtturno.setText("<html><em><strong>Turn of " + nombre1.getText() + "</strong></em></html>");
            txtturno.setForeground(colorX);
            turno = 1;
        }

        nombre1.setEnabled(false);
        nombre2.setEnabled(false);

        btnsalir.setEnabled(false);
        btnreset.setEnabled(true);
        btnplay.setEnabled(false);
    }

    public void btnResetAP() {

        txtturno.setText("");

        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                tablero[f][c].setText("");
                tablero[f][c].setEnabled(true);
                tablero[f][c].setBackground(getBackground());
            }
        }

        btnsalir.setEnabled(true);
        btnplay.setEnabled(true);
        btnreset.setEnabled(false);

        if (pX == 5 || pO == 5) {
            pointsX.setText("");
            pointsO.setText("");
            pX = 0;
            pO = 0;
        }

        img.setIcon(null);
        turno = 0;
        lim = 0;
        lim2 = 0;
        sw = 0;
        jugadasX = 0;
        jugadasO = 0;
        win = false;
    }

    public void btnTableroAP(ActionEvent e) {

        for (int f = 0; f < 3; f++) {

            for (int c = 0; c < 3; c++) {

                if (turno == 1) {

                    if (e.getSource() == tablero[f][c]) {

                        if (tablero[f][c].getText().equals("")) {

                            if (lim != 1) {
                                tablero[f][c].setText("X");
                                tablero[f][c].setForeground(colorX);
                                jugadasX++;
                                turno = 2;

                                if (!vsCPU) {

                                    txtturno.setText(
                                            "<html><em><strong>Turn of " + nombre2.getText() + "</strong></em></html>");
                                    txtturno.setForeground(colorO);
                                }
                            }

                            if (lim == 1) {
                                txtturno.setText("<html><em><strong>Select one</strong></em></html>");
                                txtturno.setForeground(colorX);
                            }

                            if (sw == 1) {
                                tablero[Tf][Tc].setText("");
                                tablero[Tf][Tc].setBackground(getBackground());
                                tablero[f][c].setText("X");
                                tablero[f][c].setForeground(colorX);

                                if (!vsCPU) {

                                    txtturno.setText(
                                            "<html><em><strong>Turn of " + nombre2.getText() + "</strong></em></html>");
                                    txtturno.setForeground(colorO);
                                }

                                sw = 0;
                                turno = 2;
                            }

                            if (jugadasX == 3) {
                                lim = 1;
                            }

                            winner();
                        } else if (tablero[f][c].getText().equals("X")) {

                            if (sw == 0 && lim == 1) {
                                tablero[f][c].setBackground(color);
                                Tf = f;
                                Tc = c;

                                txtturno.setText("<html><em><strong>Move to</strong></em></html>");

                                sw = 1;
                            } else if (sw == 1 && lim == 1) {

                                if (tablero[f][c].getBackground() == color) {
                                    tablero[f][c].setBackground(getBackground());
                                    txtturno.setText("<html><em><strong>Select one</strong></em></html>");
                                    sw = 0;
                                } else if (tablero[Tf][Tc].getBackground() == color) {
                                    tablero[Tf][Tc].setBackground(getBackground());
                                    tablero[f][c].setBackground(color);
                                    Tf = f;
                                    Tc = c;
                                }
                            }

                        } else {
                            txtturno.setText("<html><em><strong>Try again</strong></em></html>");
                        }
                    }

                } else if (turno == 2) {

                    if (!vsCPU) {

                        if (e.getSource() == tablero[f][c]) {

                            if (tablero[f][c].getText().equals("")) {

                                if (lim2 != 1) {
                                    tablero[f][c].setText("O");
                                    tablero[f][c].setForeground(colorO);
                                    jugadasO++;
                                    turno = 1;
                                    txtturno.setText(
                                            "<html><em><strong>Turn of " + nombre1.getText() + "</strong></em></html>");
                                    txtturno.setForeground(colorX);
                                }

                                if (lim2 == 1) {
                                    txtturno.setText("<html><em><strong>Select one</strong></em></html>");
                                    txtturno.setForeground(colorO);
                                }

                                if (sw == 1) {
                                    tablero[Tf][Tc].setText("");
                                    tablero[Tf][Tc].setBackground(getBackground());
                                    tablero[f][c].setText("O");
                                    tablero[f][c].setForeground(colorO);
                                    txtturno.setText(
                                            "<html><em><strong>Turn of " + nombre1.getText() + "</strong></em></html>");
                                    txtturno.setForeground(colorX);
                                    sw = 0;
                                    turno = 1;
                                }

                                if (jugadasO == 3) {
                                    lim2 = 1;
                                }

                                winner();
                            } else if (tablero[f][c].getText().equals("O")) {

                                if (sw == 0 && lim2 == 1) {
                                    tablero[f][c].setBackground(color);
                                    Tf = f;
                                    Tc = c;

                                    txtturno.setText("<html><em><strong>Move to</strong></em></html>");

                                    sw = 1;
                                } else if (sw == 1 && lim2 == 1) {

                                    if (tablero[f][c].getBackground() == color) {
                                        tablero[f][c].setBackground(getBackground());
                                        txtturno.setText("<html><em><strong>Select one</strong></em></html>");
                                        sw = 0;
                                    } else if (tablero[Tf][Tc].getBackground() == color) {
                                        tablero[Tf][Tc].setBackground(getBackground());
                                        tablero[f][c].setBackground(color);
                                        Tf = f;
                                        Tc = c;
                                    }
                                }

                            } else {
                                txtturno.setText("<html><em><strong>Try again</strong></em></html>");
                            }
                        }

                        // vs CPU
                    } else {

                        if (jugadasO < 3) {
                            moverCPU();
                            jugadasO++;
                        } else {
                            quitarCPU();
                        }

                        turno = 1;

                        winner();
                    }
                }
            }
        }
    }

    public int[] getBest() {

        int pos[] = new int[2];

        int[][] puntos = getPoints();

        int aux = h.core.enteroAleatorio(0, 2);

        pos[0] = puntos[aux][0];
        pos[1] = puntos[aux][1];

        return pos;
    }

    public void quitarCPU() {

        int[] pos = getBest();

        tablero[pos[0]][pos[1]].setText("");
        //tablero[puntos[0][0]][puntos[0][1]].setForeground(getForeground());

        moverCPU();
    }

    public int[][] getPoints() {

        int puntos[][] = new int[3][2];
        int count = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (tablero[i][j].getText().equals("O")) {

                    if (count == 0) {
                        puntos[count][count] = i;
                        puntos[count][1] = j;
                    } else if (count == 1) {
                        puntos[count][count - 1] = i;
                        puntos[count][count] = j;
                    } else {
                        puntos[count][count - 2] = i;
                        puntos[count][count - 1] = j;
                    }

                    count++;
                }
            }
        }
        return puntos;
    }

    public void moverCPU() {

        // mira si puede ganar
        if (CPU.mejorJugada("O")) {

            int[] posiciones = CPU.getPosiciones();

            tablero[posiciones[0]][posiciones[1]].setText("O");
            tablero[posiciones[0]][posiciones[1]].setForeground(colorO);

            // mira si hay peligro de que el usuario gane
        } else if (CPU.mejorJugada("X")) {

            int[] posiciones = CPU.getPosiciones();

            tablero[posiciones[0]][posiciones[1]].setText("O");
            tablero[posiciones[0]][posiciones[1]].setForeground(colorO);

        } else {

            if (tablero[1][1].getText().equals("")) {
                tablero[1][1].setText("O");
                tablero[1][1].setForeground(colorO);
            } else if (tablero[0][0].getText().equals("")) {
                tablero[0][0].setText("O");
                tablero[0][0].setForeground(colorO);
            } else if (tablero[2][2].getText().equals("")) {
                tablero[2][2].setText("O");
                tablero[2][2].setForeground(colorO);
            }
        }
    }

    // Eventos
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnplay) {
            btnPlayAP();
        } else if (e.getSource() == btnreset) {
            btnResetAP();
        } else if (e.getSource() == btnsalir) {
            h.core.fadeOut(this);
        } else {
            btnTableroAP(e);
        }
    }
}

class CPU {

    public static boolean mejorJugada(String letra) {

        boolean hayPeligro = false;

        // fila 0
        if (Triqui.tablero[0][0].getText().equals(letra) && Triqui.tablero[0][1].getText().equals(letra)) {

            posiciones[0] = 0;
            posiciones[1] = 2;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[0][0].getText().equals(letra) && Triqui.tablero[0][2].getText().equals(letra)) {

            posiciones[0] = 0;
            posiciones[1] = 1;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[0][1].getText().equals(letra) && Triqui.tablero[0][2].getText().equals(letra)) {

            posiciones[0] = 0;
            posiciones[1] = 0;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }
        }

        // fila 1
        if (Triqui.tablero[1][0].getText().equals(letra) && Triqui.tablero[1][1].getText().equals(letra)) {

            posiciones[0] = 1;
            posiciones[1] = 2;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[1][0].getText().equals(letra) && Triqui.tablero[1][2].getText().equals(letra)) {

            posiciones[0] = 1;
            posiciones[1] = 1;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[1][1].getText().equals(letra) && Triqui.tablero[1][2].getText().equals(letra)) {

            posiciones[0] = 1;
            posiciones[1] = 0;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }
        }

        // fila 2
        if (Triqui.tablero[2][0].getText().equals(letra) && Triqui.tablero[2][1].getText().equals(letra)) {

            posiciones[0] = 2;
            posiciones[1] = 2;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[2][0].getText().equals(letra) && Triqui.tablero[2][2].getText().equals(letra)) {

            posiciones[0] = 2;
            posiciones[1] = 1;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[2][1].getText().equals(letra) && Triqui.tablero[2][2].getText().equals(letra)) {

            posiciones[0] = 2;
            posiciones[1] = 0;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }
        }

        // columna 0
        if (Triqui.tablero[0][0].getText().equals(letra) && Triqui.tablero[1][0].getText().equals(letra)) {

            posiciones[0] = 2;
            posiciones[1] = 0;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[0][0].getText().equals(letra) && Triqui.tablero[2][0].getText().equals(letra)) {

            posiciones[0] = 1;
            posiciones[1] = 0;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[1][0].getText().equals(letra) && Triqui.tablero[2][0].getText().equals(letra)) {

            posiciones[0] = 0;
            posiciones[1] = 0;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }
        }

        // columna 1
        if (Triqui.tablero[0][1].getText().equals(letra) && Triqui.tablero[1][1].getText().equals(letra)) {

            posiciones[0] = 2;
            posiciones[1] = 1;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[0][1].getText().equals(letra) && Triqui.tablero[2][1].getText().equals(letra)) {

            posiciones[0] = 1;
            posiciones[1] = 1;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[1][1].getText().equals(letra) && Triqui.tablero[2][1].getText().equals(letra)) {

            posiciones[0] = 0;
            posiciones[1] = 1;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }
        }

        // columna 2
        if (Triqui.tablero[0][2].getText().equals(letra) && Triqui.tablero[1][2].getText().equals(letra)) {

            posiciones[0] = 2;
            posiciones[1] = 2;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[0][2].getText().equals(letra) && Triqui.tablero[2][2].getText().equals(letra)) {

            posiciones[0] = 1;
            posiciones[1] = 2;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[1][2].getText().equals(letra) && Triqui.tablero[2][2].getText().equals(letra)) {

            posiciones[0] = 0;
            posiciones[1] = 2;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }
        }

        // diagonal 1
        if (Triqui.tablero[0][0].getText().equals(letra) && Triqui.tablero[1][1].getText().equals(letra)) {

            posiciones[0] = 2;
            posiciones[1] = 2;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[0][0].getText().equals(letra) && Triqui.tablero[2][2].getText().equals(letra)) {

            posiciones[0] = 1;
            posiciones[1] = 1;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[1][1].getText().equals(letra) && Triqui.tablero[2][2].getText().equals(letra)) {

            posiciones[0] = 0;
            posiciones[1] = 0;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }
        }

        // diagonal 2
        if (Triqui.tablero[2][0].getText().equals(letra) && Triqui.tablero[1][1].getText().equals(letra)) {

            posiciones[0] = 0;
            posiciones[1] = 2;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[2][0].getText().equals(letra) && Triqui.tablero[0][2].getText().equals(letra)) {

            posiciones[0] = 1;
            posiciones[1] = 1;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }

        } else if (Triqui.tablero[1][1].getText().equals(letra) && Triqui.tablero[0][2].getText().equals(letra)) {
            posiciones[0] = 2;
            posiciones[1] = 0;

            if (Triqui.tablero[posiciones[0]][posiciones[1]].getText().equals("")) {
                hayPeligro = true;

                return hayPeligro;
            }
        }

        return hayPeligro;
    }

    public static int[] posiciones = new int[2];

    public static int[] getPosiciones() {
        return posiciones;
    }
}