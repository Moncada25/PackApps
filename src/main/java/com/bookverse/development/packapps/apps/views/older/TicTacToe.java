package com.bookverse.development.packapps.apps.views.older;

import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.other.TicTacToeCPU;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
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
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.apps.utils.ui.Effects.fadeIn;
import static com.bookverse.development.packapps.apps.utils.other.GeneralUtils.getIntRandom;
import static com.bookverse.development.packapps.apps.utils.ui.Alerts.instruccionesTriqui;

public class TicTacToe extends JDialog implements ActionListener {

  public static final JTextField txtName1 = new JTextField();
  public static final JTextField txtName2 = new JTextField();
  public static final Color colorO = MAIN_COLOR;
  public static final Color colorX = TEXT_COLOR;
  public static final Color color = new Color(100, 220, 0);
  @Getter
  private static final JButton[][] board = new JButton[3][3];

  private final boolean vsCPU;
  private JLabel lblTurn;
  private JLabel pointsX;
  private JLabel pointsO;
  private JLabel image;
  private JButton btnExit, btnReset, btnPlay;
  private int turn = 0;
  private int movesX = 0;
  private int movesO = 0;
  private int newF;
  private int newC;
  private int limitMoves1 = 0;
  private int limitMoves2;
  private int pointsNumberX = 0;
  private int pointsNumberO = 0;
  private boolean win = false;
  private boolean moveMade = false;

  public TicTacToe(JFrame parent, boolean modal, boolean vsCPU) {
    super(parent, modal);
    this.vsCPU = vsCPU;
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    if (vsCPU) {
      setTitle("Player vs CPU");
    } else {
      setTitle("Player vs Player");
    }
    fadeIn(this);
    parent.setVisible(false);
    instruccionesTriqui();
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("triqui.png")).getImage());

    btnExit = Resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(310, 300, 86, 30);

    btnReset = Resources.getButton("Reset", TEXT_COLOR, this, this);
    btnReset.setEnabled(false);
    btnReset.setBounds(180, 300, 86, 30);

    btnPlay = Resources.getButton("Play", TEXT_COLOR, this, this);
    btnPlay.setBounds(50, 300, 86, 30);

    int x = 50;
    int y = 50;

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {
        board[f][c] = Resources.getButton("", null, this, this);
        board[f][c].setBounds(x, y, 70, 70);
        board[f][c].setFont(new Font("Times New Roman", Font.PLAIN, 45));
        board[f][c].setOpaque(true);
        x = x + 70;
      }
      x = 50;
      y = y + 70;
    }

    JLabel lblName1 = Resources
        .getLabel("<html><em><strong>Player X → </strong></em></html>", colorX, this,
            MEDIUM);
    lblName1.setBounds(50, 10, 120, 40);

    pointsX = Resources.getLabel("<html><em><strong>0</strong></em></html>", colorX, this, MEDIUM);
    pointsX.setBounds(110, 10, 120, 40);

    JLabel lblName2;
    if (!vsCPU) {
      lblName2 = Resources
          .getLabel("<html><em><strong>Player O → </strong></em></html>", colorO, this,
              MEDIUM);
      lblName2.setBounds(255, 10, 120, 40);
    } else {
      lblName2 = Resources
          .getLabel("<html><em><strong>CPU O → </strong></em></html>", colorO, this,
              MEDIUM);
      lblName2.setBounds(273, 10, 120, 40);
    }

    pointsO = Resources.getLabel("<html><em><strong>0</strong></em></html>", colorO, this, MEDIUM);
    pointsO.setBounds(315, 10, 120, 40);

    lblTurn = Resources.getLabel("", null, this, MEDIUM);
    lblTurn.setBounds(245, 200, 200, 100);

    image = Resources.getLabel("", null, this, null);
    image.setBounds(270, 80, 150, 150);

    txtName1.setText("X");

    if (!vsCPU) {
      txtName2.setText("O");
    } else {
      txtName2.setText("CPU");
    }
  }

  private void blockTab(String winner) {

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {

        if (board[f][c].getText().equals(winner)) {

          if ("X".equals(winner)) {
            board[f][c].setBackground(colorX);
          } else {
            board[f][c].setBackground(colorO);
          }
          board[f][c].setForeground(getForeground());
        }
      }
    }
    turn = 0;
  }

  private void count(String winner) {

    if (winner.equals("X")) {
      pointsNumberX++;
      pointsX.setText("<html><em><strong>"+pointsNumberX+"</strong></em></html>");
    } else {
      pointsNumberO++;
      pointsO.setText("<html><em><strong>"+pointsNumberO+"</strong></em></html>");
    }
  }

  private void countPoints(@NotNull String winner) {

    if (winner.equals("X")) {

      count("X");

      printImage(pointsNumberX, txtName1.getText());

    } else {

      count("O");

      printImage(pointsNumberO, txtName2.getText());
    }
  }

  private void printImage(int points, String name) {
    if (points == 5) {
      lblTurn.setText("<html><em><strong>Champion " + name + "!</strong></em></html>");
      image.setIcon(new ImageIcon(Resources.getImage("triquito.png")));
    } else {
      lblTurn.setText("<html><em><strong>Winner " + name + "!</strong></em></html>");
      image.setIcon(new ImageIcon(Resources.getImage("cr7.png")));
    }
  }

  private void winner() {

    if ((board[0][0].getText().equals("X") && board[0][1].getText().equals("X") && board[0][2]
        .getText().equals("X"))
        || (board[1][0].getText().equals("X") && board[1][1].getText().equals("X") && board[1][2]
        .getText().equals("X"))
        || (board[2][0].getText().equals("X") && board[2][1].getText().equals("X") && board[2][2]
        .getText().equals("X"))
        || (board[0][0].getText().equals("X") && board[1][0].getText().equals("X") && board[2][0]
        .getText().equals("X"))
        || (board[0][1].getText().equals("X") && board[1][1].getText().equals("X") && board[2][1]
        .getText().equals("X"))
        || (board[0][2].getText().equals("X") && board[1][2].getText().equals("X") && board[2][2]
        .getText().equals("X"))
        || (board[0][0].getText().equals("X") && board[1][1].getText().equals("X") && board[2][2]
        .getText().equals("X"))
        || (board[0][2].getText().equals("X") && board[1][1].getText().equals("X") && board[2][0]
        .getText().equals("X"))) {

      lblTurn.setText("<html><em><strong>Winner " + txtName1.getText() + "!</strong></em></html>");
      lblTurn.setForeground(colorX);
      win = true;

      countPoints("X");
      blockTab("X");
    } else if (
        (board[0][0].getText().equals("O") && board[0][1].getText().equals("O") && board[0][2]
            .getText().equals("O"))
            || (board[1][0].getText().equals("O") && board[1][1].getText().equals("O")
            && board[1][2].getText().equals("O"))
            || (board[2][0].getText().equals("O") && board[2][1].getText().equals("O")
            && board[2][2].getText().equals("O"))
            || (board[0][0].getText().equals("O") && board[1][0].getText().equals("O")
            && board[2][0].getText().equals("O"))
            || (board[0][1].getText().equals("O") && board[1][1].getText().equals("O")
            && board[2][1].getText().equals("O"))
            || (board[0][2].getText().equals("O") && board[1][2].getText().equals("O")
            && board[2][2].getText().equals("O"))
            || (board[0][0].getText().equals("O") && board[1][1].getText().equals("O")
            && board[2][2].getText().equals("O"))
            || (board[0][2].getText().equals("O") && board[1][1].getText().equals("O")
            && board[2][0].getText().equals("O"))) {

      lblTurn.setText("<html><em><strong>Winner " + txtName2.getText() + "!</strong></em></html>");
      lblTurn.setForeground(colorO);
      win = true;

      countPoints("O");
      blockTab("O");
    }
  }

  private void btnPlayAP() {

    if (!win) {
      lblTurn.setText("<html><em><strong>Turn of " + txtName1.getText() + "</strong></em></html>");
      lblTurn.setForeground(colorX);
      turn = 1;
    }

    txtName1.setEnabled(false);
    txtName2.setEnabled(false);

    btnExit.setEnabled(false);
    btnReset.setEnabled(true);
    btnPlay.setEnabled(false);
  }

  private void btnResetAP() {

    lblTurn.setText("");

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {
        board[f][c].setText("");
        board[f][c].setEnabled(true);
        board[f][c].setBackground(getBackground());
      }
    }

    btnExit.setEnabled(true);
    btnPlay.setEnabled(true);
    btnReset.setEnabled(false);

    if (pointsNumberX == 5 || pointsNumberO == 5) {
      pointsX.setText("");
      pointsO.setText("");
      pointsNumberX = 0;
      pointsNumberO = 0;
    }

    image.setIcon(null);
    turn = 0;
    limitMoves1 = 0;
    limitMoves2 = 0;
    moveMade = false;
    movesX = 0;
    movesO = 0;
    win = false;
  }

  private void btnBoardAP(ActionEvent e) {

    for (int f = 0; f < 3; f++) {

      for (int c = 0; c < 3; c++) {

        if (turn == 1) {

          if (e.getSource() == board[f][c]) {

            if (board[f][c].getText().isEmpty()) {

              if (limitMoves1 != 1) {
                board[f][c].setText("X");
                board[f][c].setForeground(colorX);
                movesX++;
                turn = 2;

                if (!vsCPU) {

                  lblTurn.setText(
                      "<html><em><strong>Turn of " + txtName2.getText() + "</strong></em></html>");
                  lblTurn.setForeground(colorO);
                }
              }

              if (limitMoves1 == 1) {
                lblTurn.setText("<html><em><strong>Select one</strong></em></html>");
                lblTurn.setForeground(colorX);
              }

              if (moveMade) {
                board[newF][newC].setText("");
                board[newF][newC].setBackground(getBackground());
                board[f][c].setText("X");
                board[f][c].setForeground(colorX);

                if (!vsCPU) {

                  lblTurn.setText(
                      "<html><em><strong>Turn of " + txtName2.getText() + "</strong></em></html>");
                  lblTurn.setForeground(colorO);
                }

                moveMade = false;
                turn = 2;
              }

              if (movesX == 3) {
                limitMoves1 = 1;
              }

              winner();
            } else if (board[f][c].getText().equals("X")) {

              limitMoves(f, c);

            } else {
              lblTurn.setText("<html><em><strong>Try again</strong></em></html>");
            }
          }

        } else if (turn == 2) {

          if (!vsCPU) {

            if (e.getSource() == board[f][c]) {

              if (board[f][c].getText().isEmpty()) {

                if (limitMoves2 != 1) {
                  board[f][c].setText("O");
                  board[f][c].setForeground(colorO);
                  movesO++;
                  turn = 1;
                  lblTurn.setText(
                      "<html><em><strong>Turn of " + txtName1.getText() + "</strong></em></html>");
                  lblTurn.setForeground(colorX);
                }

                if (limitMoves2 == 1) {
                  lblTurn.setText("<html><em><strong>Select one</strong></em></html>");
                  lblTurn.setForeground(colorO);
                }

                if (moveMade) {
                  board[newF][newC].setText("");
                  board[newF][newC].setBackground(getBackground());
                  board[f][c].setText("O");
                  board[f][c].setForeground(colorO);
                  lblTurn.setText(
                      "<html><em><strong>Turn of " + txtName1.getText() + "</strong></em></html>");
                  lblTurn.setForeground(colorX);
                  moveMade = false;
                  turn = 1;
                }

                if (movesO == 3) {
                  limitMoves2 = 1;
                }

                winner();
              } else if (board[f][c].getText().equals("O")) {

                limitMoves(f, c);

              } else {
                lblTurn.setText("<html><em><strong>Try again</strong></em></html>");
              }
            }

          } else {

            if (movesO < 3) {
              makeMoveCPU();
              movesO++;
            } else {
              moveCPU();
            }

            turn = 1;
            winner();
          }
        }
      }
    }
  }

  private void limitMoves(int f, int c) {
    if (!moveMade && limitMoves1 == 1) {
      board[f][c].setBackground(color);
      newF = f;
      newC = c;

      lblTurn.setText("<html><em><strong>Move to</strong></em></html>");

      moveMade = true;
    } else if (moveMade && limitMoves1 == 1) {

      if (board[f][c].getBackground() == color) {
        board[f][c].setBackground(getBackground());
        lblTurn.setText("<html><em><strong>Select one</strong></em></html>");
        moveMade = false;
      } else if (board[newF][newC].getBackground() == color) {
        board[newF][newC].setBackground(getBackground());
        board[f][c].setBackground(color);
        newF = f;
        newC = c;
      }
    }
  }

  private int[] getBestMove() {

    int[] position = new int[2];

    int[][] points = getPoints();

    int aux = getIntRandom(0, 2);

    position[0] = points[aux][0];
    position[1] = points[aux][1];

    return position;
  }

  private void moveCPU() {

    int[] position = getBestMove();

    board[position[0]][position[1]].setText("");
    makeMoveCPU();
  }

  private int[][] getPoints() {

    int[][] points = new int[3][2];
    int count = 0;

    for (int i = 0; i < 3; i++) {

      for (int j = 0; j < 3; j++) {

        if (board[i][j].getText().equals("O")) {

          if (count == 0) {
            points[count][count] = i;
            points[count][1] = j;
          } else if (count == 1) {
            points[count][count - 1] = i;
            points[count][count] = j;
          } else {
            points[count][count - 2] = i;
            points[count][count - 1] = j;
          }

          count++;
        }
      }
    }
    return points;
  }

  private void makeMoveCPU() {

    if (TicTacToeCPU.bestMove("O")) {

      int[] positions = TicTacToeCPU.getPositions();

      board[positions[0]][positions[1]].setText("O");
      board[positions[0]][positions[1]].setForeground(colorO);

    } else if (TicTacToeCPU.bestMove("X")) {

      int[] positions = TicTacToeCPU.getPositions();

      board[positions[0]][positions[1]].setText("O");
      board[positions[0]][positions[1]].setForeground(colorO);

    } else {

      if (board[1][1].getText().isEmpty()) {
        board[1][1].setText("O");
        board[1][1].setForeground(colorO);
      } else if (board[0][0].getText().isEmpty()) {
        board[0][0].setText("O");
        board[0][0].setForeground(colorO);
      } else if (board[2][2].getText().isEmpty()) {
        board[2][2].setText("O");
        board[2][2].setForeground(colorO);
      }
    }
  }

  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnPlay) {
      btnPlayAP();
    } else if (e.getSource() == btnReset) {
      btnResetAP();
    } else if (e.getSource() == btnExit) {
      Effects.fadeOut(this);
    } else {
      btnBoardAP(e);
    }
  }
}