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
import javax.swing.JOptionPane;

public class Rompecabezas extends JDialog implements Runnable, ActionListener {

  Resources h = new Resources();
  private JButton[][] tablero;
  private JLabel tiempo, txtturno;
  private JButton btnsalir, btnplay, btnreset;
  private int movimientos = 0;
  private Thread hiloTime;
  // private Thread hiloMP3;
  private boolean cronometroActivo;
  private int tam, lado, time;
  private Color color = h.cr.AZUL;
  private Color rojo = h.cr.ROJO;

  public Rompecabezas(JFrame parent, boolean modal, int cuadrado, int lado, int minutos) {

    super(parent, modal);
    this.tam = cuadrado;
    this.time = minutos;
    this.lado = lado;
    componentes();
  }

  public Rompecabezas() {
    componentes();
  }

  // Se crean los componentes de la ventana
  public void componentes() {

    setLayout(null);
    setDefaultCloseOperation(0);
    setIconImage(new ImageIcon(h.getImage("rompecabezas.png")).getImage());

    btnsalir = h.getButton("Return", h.cr.ROJO, this, this);
    btnsalir.setBounds(330, 300, 86, 30);

    btnplay = h.getButton("Play", h.cr.AZUL, this, this);
    btnplay.setBounds(70, 300, 86, 30);

    btnreset = h.getButton("Stop", h.cr.ROJO, this, this);
    btnreset.setBounds(200, 300, 86, 30);
    btnreset.setEnabled(false);

    txtturno = h.getLabel("", rojo, this, h.cr.MEDIUM);
    txtturno.setBounds(335, 90, 200, 100);

    tiempo = h.getLabel("", rojo, this, new Font("Times New Roman", 0, 45));
    tiempo.setBounds(335, 5, 200, 60);

    tablero = new JButton[tam][tam];

    int x = 15;
    int y = 15;

    for (int f = 0; f < tablero.length; f++) {
      for (int c = 0; c < tablero.length; c++) {
        tablero[f][c] = h.getButton(".", null, this, this);
        tablero[f][c].setBounds(x, y, lado, lado);
        tablero[f][c].setEnabled(false);

        x = x + lado;
      }
      x = 15;
      y = y + lado;
    }
  }

  public void btnResetAP() {

    pararCronometro();
    btnplay.setEnabled(true);
    btnreset.setEnabled(false);
    btnsalir.setEnabled(true);

    insertar("Loser");

    txtturno.setText("");
    tiempo.setText("");

    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero.length; j++) {
        tablero[i][j].setEnabled(false);
        tablero[i][j].setText(".");
      }
    }
  }

  public void btnPlayAP() {

    int n = (int) Math.pow(tam, 2) - 1;

    tablero[(int) (Math.random() * tam)][(int) (Math.random() * tam)].setText("");

    for (int f = 0; f < tablero.length; f++) {
      for (int c = 0; c < tablero.length; c++) {

        if (!tablero[f][c].getText().equals("")) {
          tablero[f][c].setText(String.valueOf(n));
          n--;
        }
      }
    }

    unlock();

    txtturno.setText("");
    movimientos = 0;

    btnreset.setEnabled(true);
    btnplay.setEnabled(false);
    btnsalir.setEnabled(false);

    iniciarCronometro();
  }

  //Valida si el tablero está ordenado
  public boolean validaVictoria() {

    int n = 1;
    boolean win = true;

    for (int f = 0; f < tablero.length; f++) {

      for (int c = 0; c < tablero.length; c++) {

        if (n >= tam * tam) {
          break;
        }

        if (!tablero[f][c].getText().equals(String.valueOf(n))) {
          win = false;
        }

        n++;
      }
    }

    if (win) {
      return true;
    } else {
      return false;
    }
  }

  // verifica cuando se gana
  public void getWinner() {

    txtturno.setText("Moves: " + movimientos);

    if (validaVictoria()) {

      pararCronometro();

      txtturno.setText("<html>"
          + "<center><strong>¡Has ganado!</strong></center>"
          + "<center>En " + movimientos + " jugadas</center>"
          + "</html>");
      txtturno.setForeground(rojo);

      insertar("Winner");

      txtturno.setText("");
      tiempo.setText("");

      for (int i = 0; i < tablero.length; i++) {
        for (int j = 0; j < tablero.length; j++) {
          tablero[i][j].setEnabled(false);
          tablero[i][j].setText(".");
        }
      }

      //btnreset.setEnabled(false);
      btnsalir.setEnabled(true);
      btnplay.setEnabled(true);
      btnreset.setEnabled(false);
      txtturno.setText("");
      tiempo.setText("");
    }
  }

  public String getLevel() {

    String level = "";

    if (tam == 4) {
      level = "Easy";
    } else if (tam == 5) {
      level = "Medium";
    } else if (tam == 6) {
      level = "Hard";
    }

    return level + " - " + tiempo.getText();
  }

  private void insertar(String state) {

    if (h.cr.comprobarConexion("Datos no guardados", true) && h.cr.saveGame()) {

      String data[] = {"rompecabezas", h.cr.ingreseNickname("Ingrese un Nickname", 20), state,
          getLevel(), String.valueOf(movimientos), h.cr.obtenerDate()};

      h.db.insertData(data);
    }
  }

  // Se inicia el cronómetro
  public void iniciarCronometro() {
    cronometroActivo = true;
    hiloTime = new Thread((Runnable) this);
    hiloTime.start();

    // hiloMP3 = new hiloMP3();
    // hiloMP3.start();
  }

  // Esto es para parar el temporizador
  public void pararCronometro() {
    cronometroActivo = false;
  }

  //desbloquea los posibles moviminetos a medida que se juega
  public void unlock() {

    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero.length; j++) {

        if (tablero[i][j].getText().equals("")) {

          if ((i == 0 && j == 0)) {
            tablero[0][1].setEnabled(true);
            tablero[0][1].setBackground(color);

            tablero[1][0].setEnabled(true);
            tablero[1][0].setBackground(color);
          } else if (i == tablero.length - 1 && j == tablero.length - 1) {
            tablero[tablero.length - 2][tablero.length - 1].setEnabled(true);
            tablero[tablero.length - 2][tablero.length - 1].setBackground(color);

            tablero[tablero.length - 1][tablero.length - 2].setEnabled(true);
            tablero[tablero.length - 1][tablero.length - 2].setBackground(color);
          } else if (i == 0 && j == tablero.length - 1) {
            tablero[0][tablero.length - 2].setEnabled(true);
            tablero[0][tablero.length - 2].setBackground(color);

            tablero[1][tablero.length - 1].setEnabled(true);
            tablero[1][tablero.length - 1].setBackground(color);
          } else if (i == tablero.length - 1 && j == 0) {
            tablero[tablero.length - 2][0].setEnabled(true);
            tablero[tablero.length - 2][0].setBackground(color);

            tablero[tablero.length - 1][1].setEnabled(true);
            tablero[tablero.length - 1][1].setBackground(color);
          } else if (i == 0 && j != 0 && j != tablero.length - 1) {
            tablero[0][j - 1].setEnabled(true);
            tablero[0][j - 1].setBackground(color);

            tablero[0][j + 1].setEnabled(true);
            tablero[0][j + 1].setBackground(color);

            tablero[1][j].setEnabled(true);
            tablero[1][j].setBackground(color);
          } else if (j == tablero.length - 1 && i != 0 && i != tablero.length - 1) {
            tablero[i - 1][j].setEnabled(true);
            tablero[i - 1][j].setBackground(color);

            tablero[i + 1][j].setEnabled(true);
            tablero[i + 1][j].setBackground(color);

            tablero[i][tablero.length - 2].setEnabled(true);
            tablero[i][tablero.length - 2].setBackground(color);
          } else if (i == tablero.length - 1 && j != 0 && j != tablero.length - 1) {
            tablero[tablero.length - 1][j - 1].setEnabled(true);
            tablero[tablero.length - 1][j - 1].setBackground(color);

            tablero[tablero.length - 1][j + 1].setEnabled(true);
            tablero[tablero.length - 1][j + 1].setBackground(color);

            tablero[tablero.length - 2][j].setEnabled(true);
            tablero[tablero.length - 2][j].setBackground(color);
          } else if (j == 0 && i != 0 && i != tablero.length - 1) {
            tablero[i - 1][j].setEnabled(true);
            tablero[i - 1][j].setBackground(color);

            tablero[i + 1][j].setEnabled(true);
            tablero[i + 1][j].setBackground(color);

            tablero[i][j + 1].setEnabled(true);
            tablero[i][j + 1].setBackground(color);
          } else {
            tablero[i - 1][j].setEnabled(true);
            tablero[i - 1][j].setBackground(color);

            tablero[i + 1][j].setEnabled(true);
            tablero[i + 1][j].setBackground(color);

            tablero[i][j + 1].setEnabled(true);
            tablero[i][j + 1].setBackground(color);

            tablero[i][j - 1].setEnabled(true);
            tablero[i][j - 1].setBackground(color);
          }
        }
      }
    }
  }

  public void mover(int f, int c) {

    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero.length; j++) {

        if (tablero[i][j].getText().equals("")) {
          tablero[i][j].setText(tablero[f][c].getText());
          tablero[i][j].setEnabled(true);
          tablero[f][c].setEnabled(false);
          tablero[f][c].setText("");
        }
        tablero[i][j].setEnabled(false);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnplay) {
      btnPlayAP();
    } else if (e.getSource() == btnsalir) {
      h.cr.fadeOut(this);
    } else if (e.getSource() == btnreset) {
      btnResetAP();
    }

    for (int f = 0; f < tablero.length; f++) {
      for (int c = 0; c < tablero.length; c++) {

        if (e.getSource() == tablero[f][c]) {
          movimientos++;
          mover(f, c);
          unlock();
          getWinner();
        }
      }
    }
  }

  @Override
  public void run() {

    Integer minutos = time - 1, segundos = 59, milesimas = 1000;
    // min es minutos, seg es segundos y mil es milesimas de segundo
    String min = "", seg = "";

    try {
      // Mientras cronometroActivo sea verdadero entonces seguira
      // aumentando el tiempo
      while (cronometroActivo) {
        Thread.sleep(
            4); // recibe la cantidad de milisegundos de pausa que se har? cada que se ejecute
        // el hilo
        // Decrementamos 4 milesimas de segundo
        milesimas -= 4;

        // Cuando llega a 0 osea 1 segundo, disminuye 1 segundo
        // y las milesimas de segundo de nuevo a 1000
        if (milesimas == 0) {
          milesimas = 1000;
          segundos -= 1;
          // Si los segundos llegan a 0 entonces disminuye 1 los minutos
          // y los segundos vuelven a 59
          if (segundos == 0) {

            if (minutos > 0) {
              segundos = 59;
              minutos--;
            }
          }
        }

        // Esto solamente es estetica para que siempre este en formato
        // 00:00
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

        // Colocamos en la etiqueta la informacion

        tiempo.setText(min + ":" + seg);

        if (minutos <= 0 && segundos <= 0) {
          btnResetAP();
        }
      }

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null,
          "<html>" + h.cr.styleJOption() + "<strong>" + e.getMessage() + "</strong></html>");
    }
  }
}