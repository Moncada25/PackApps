package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Core.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Core.TEXT_COLOR;

import com.bookverse.development.packapps.models.Resources;
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
import org.jetbrains.annotations.NotNull;

public class Triqui extends JDialog implements ActionListener {

  public static JButton[][] board;
  public JTextField txtName1, txtName2;
  public Color colorO = MAIN_COLOR;
  public Color colorX = TEXT_COLOR;
  public Color color = new Color(100, 220, 0);
  public boolean vsCPU;
  Resources resources = new Resources();
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

  public Triqui(JFrame parent, boolean modal, boolean vsCPU) {
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
    resources.core.fadeIn(this);
    parent.setVisible(false);
    resources.core.instruccionesTriqui();
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(new Resources().getImage("triqui.png")).getImage());

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(310, 300, 86, 30);

    btnReset = resources.getButton("Reset", TEXT_COLOR, this, this);
    btnReset.setEnabled(false);
    btnReset.setBounds(180, 300, 86, 30);

    btnPlay = resources.getButton("Play", TEXT_COLOR, this, this);
    btnPlay.setBounds(50, 300, 86, 30);

    board = new JButton[3][3];
    int x = 50;
    int y = 50;

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {
        board[f][c] = resources.getButton("", null, this, this);
        board[f][c].setBounds(x, y, 70, 70);
        board[f][c].setFont(new Font("Times New Roman", Font.PLAIN, 45));
        board[f][c].setOpaque(true);
        x = x + 70;
      }
      x = 50;
      y = y + 70;
    }

    JLabel lblName1 = resources
        .getLabel("<html><em><strong>Player X</strong></em></html>", colorX, this,
            resources.core.MEDIUM);
    lblName1.setBounds(50, 10, 100, 30);

    pointsX = resources.getLabel("", color, this, resources.core.BIG);
    pointsX.setBounds(130, 10, 100, 30);

    JLabel lblName2;
    if (!vsCPU) {
      lblName2 = resources
          .getLabel("<html><em><strong>Player O</strong></em></html>", colorO, this,
              resources.core.MEDIUM);
      lblName2.setBounds(260, 10, 100, 30);
    } else {
      lblName2 = resources
          .getLabel("<html><em><strong>CPU O</strong></em></html>", colorO, this,
              resources.core.MEDIUM);
      lblName2.setBounds(273, 10, 100, 30);
    }

    pointsO = resources.getLabel("", color, this, resources.core.BIG);
    pointsO.setBounds(340, 10, 100, 30);

    lblTurn = resources.getLabel("", null, this, resources.core.MEDIUM);
    lblTurn.setBounds(270, 200, 200, 100);

    image = resources.getLabel("", null, this, null);
    image.setBounds(300, 90, 96, 96);

    txtName1 = new JTextField("X");

    if (!vsCPU) {
      txtName2 = new JTextField("O");
    } else {
      txtName2 = new JTextField("CPU");
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

  private void countPoints(@NotNull String winner) {

    if (winner.equals("X")) {

      switch (pointsNumberX) {
        case 0:
          pointsX.setText("<html><em><strong>|</strong></em></html>");
          pointsNumberX++;
          break;
        case 1:
          pointsX.setText("<html><em><strong>||</strong></em></html>");
          pointsNumberX++;
          break;
        case 2:
          pointsX.setText("<html><em><strong>|||</strong></em></html>");
          pointsNumberX++;
          break;
        case 3:
          pointsX.setText("<html><em><strong>||||</strong></em></html>");
          pointsNumberX++;
          break;
        case 4:
          pointsX.setText("<html><em><strong>|||||</strong></em></html>");
          pointsNumberX++;
          break;
        default:
          break;
      }

      if (pointsNumberX == 5) {
        lblTurn.setText(
            "<html><em><strong>¡Champion " + txtName1.getText() + "!</strong></em></html>");
        image.setIcon(new ImageIcon(new Resources().getImage("triquito.png")));
      }

    } else {

      switch (pointsNumberO) {
        case 0:
          pointsO.setText("<html><em><strong>|</strong></em></html>");
          pointsNumberO++;
          break;
        case 1:
          pointsO.setText("<html><em><strong>||</strong></em></html>");
          pointsNumberO++;
          break;
        case 2:
          pointsO.setText("<html><em><strong>|||</strong></em></html>");
          pointsNumberO++;
          break;
        case 3:
          pointsO.setText("<html><em><strong>||||</strong></em></html>");
          pointsNumberO++;
          break;
        case 4:
          pointsO.setText("<html><em><strong>|||||</strong></em></html>");
          pointsNumberO++;
          break;
        default:
          break;
      }

      if (pointsNumberO == 5) {
        lblTurn.setText(
            "<html><em><strong>¡Champion " + txtName2.getText() + "!</strong></em></html>");
        image.setIcon(new ImageIcon(new Resources().getImage("triquito.png")));
      }
    }
  }

  private void winner() {

    if ((board[0][0].getText().equals("X") && board[0][1].getText().equals("X") && board[0][2].getText().equals("X"))
    || (board[1][0].getText().equals("X") && board[1][1].getText().equals("X") && board[1][2].getText().equals("X"))
    || (board[2][0].getText().equals("X") && board[2][1].getText().equals("X") && board[2][2].getText().equals("X"))
    || (board[0][0].getText().equals("X") && board[1][0].getText().equals("X") && board[2][0].getText().equals("X"))
    || (board[0][1].getText().equals("X") && board[1][1].getText().equals("X") && board[2][1].getText().equals("X"))
    || (board[0][2].getText().equals("X") && board[1][2].getText().equals("X") && board[2][2].getText().equals("X"))
    || (board[0][0].getText().equals("X") && board[1][1].getText().equals("X") && board[2][2].getText().equals("X"))
    || (board[0][2].getText().equals("X") && board[1][1].getText().equals("X") && board[2][0].getText().equals("X"))) {

      lblTurn.setText("<html><em><strong>¡Winner " + txtName1.getText() + "!</strong></em></html>");
      lblTurn.setForeground(colorX);
      win = true;

      countPoints("X");
      blockTab("X");
    } else if ((board[0][0].getText().equals("O") && board[0][1].getText().equals("O") && board[0][2].getText().equals("O"))
        || (board[1][0].getText().equals("O") && board[1][1].getText().equals("O") && board[1][2].getText().equals("O"))
        || (board[2][0].getText().equals("O") && board[2][1].getText().equals("O") && board[2][2].getText().equals("O"))
        || (board[0][0].getText().equals("O") && board[1][0].getText().equals("O") && board[2][0].getText().equals("O"))
        || (board[0][1].getText().equals("O") && board[1][1].getText().equals("O") && board[2][1].getText().equals("O"))
        || (board[0][2].getText().equals("O") && board[1][2].getText().equals("O") && board[2][2].getText().equals("O"))
        || (board[0][0].getText().equals("O") && board[1][1].getText().equals("O") && board[2][2].getText().equals("O"))
        || (board[0][2].getText().equals("O") && board[1][1].getText().equals("O") && board[2][0].getText().equals("O"))) {

      lblTurn.setText("<html><em><strong>¡Winner " + txtName2.getText() + "!</strong></em></html>");
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

            if (board[f][c].getText().equals("")) {

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

            } else {
              lblTurn.setText("<html><em><strong>Try again</strong></em></html>");
            }
          }

        } else if (turn == 2) {

          if (!vsCPU) {

            if (e.getSource() == board[f][c]) {

              if (board[f][c].getText().equals("")) {

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

                if (!moveMade && limitMoves2 == 1) {
                  board[f][c].setBackground(color);
                  newF = f;
                  newC = c;

                  lblTurn.setText("<html><em><strong>Move to</strong></em></html>");

                  moveMade = true;
                } else if (moveMade && limitMoves2 == 1) {

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

  @NotNull
  private int[] getBestMove() {

    int[] position = new int[2];

    int[][] points = getPoints();

    int aux = resources.core.enteroAleatorio(0, 2);

    position[0] = points[aux][0];
    position[1] = points[aux][1];

    return position;
  }

  private void moveCPU() {

    int[] position = getBestMove();

    board[position[0]][position[1]].setText("");
    makeMoveCPU();
  }

  @NotNull
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

    if (CPU.bestMove("O")) {

      int[] positions = CPU.getPositions();

      board[positions[0]][positions[1]].setText("O");
      board[positions[0]][positions[1]].setForeground(colorO);

    } else if (CPU.bestMove("X")) {

      int[] positions = CPU.getPositions();

      board[positions[0]][positions[1]].setText("O");
      board[positions[0]][positions[1]].setForeground(colorO);

    } else {

      if (board[1][1].getText().equals("")) {
        board[1][1].setText("O");
        board[1][1].setForeground(colorO);
      } else if (board[0][0].getText().equals("")) {
        board[0][0].setText("O");
        board[0][0].setForeground(colorO);
      } else if (board[2][2].getText().equals("")) {
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
      resources.core.fadeOut(this);
    } else {
      btnBoardAP(e);
    }
  }
}

class CPU {

  private static int[] positions = new int[2];

  protected static boolean bestMove(String player) {

    boolean thereIsDanger = false;

    if (Triqui.board[0][0].getText().equals(player) && Triqui.board[0][1].getText().equals(player)) {

      positions[0] = 0;
      positions[1] = 2;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[0][0].getText().equals(player) && Triqui.board[0][2].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 1;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[0][1].getText().equals(player) && Triqui.board[0][2].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 0;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (Triqui.board[1][0].getText().equals(player) && Triqui.board[1][1].getText().equals(player)) {

      positions[0] = 1;
      positions[1] = 2;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[1][0].getText().equals(player) && Triqui.board[1][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[1][1].getText().equals(player) && Triqui.board[1][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 0;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (Triqui.board[2][0].getText().equals(player) && Triqui.board[2][1].getText().equals(player)) {

      positions[0] = 2;
      positions[1] = 2;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[2][0].getText().equals(player) && Triqui.board[2][2].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 1;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[2][1].getText().equals(player) && Triqui.board[2][2].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 0;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (Triqui.board[0][0].getText().equals(player) && Triqui.board[1][0].getText().equals(player)) {

      positions[0] = 2;
      positions[1] = 0;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[0][0].getText().equals(player) && Triqui.board[2][0].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 0;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[1][0].getText().equals(player) && Triqui.board[2][0].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 0;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (Triqui.board[0][1].getText().equals(player) && Triqui.board[1][1].getText().equals(player)) {

      positions[0] = 2;
      positions[1] = 1;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[0][1].getText().equals(player) && Triqui.board[2][1].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[1][1].getText().equals(player) && Triqui.board[2][1].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 1;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (Triqui.board[0][2].getText().equals(player) && Triqui.board[1][2].getText().equals(player)) {

      positions[0] = 2;
      positions[1] = 2;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[0][2].getText().equals(player) && Triqui.board[2][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 2;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[1][2].getText().equals(player) && Triqui.board[2][2].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 2;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (Triqui.board[0][0].getText().equals(player) && Triqui.board[1][1].getText().equals(player)) {

      positions[0] = 2;
      positions[1] = 2;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[0][0].getText().equals(player) && Triqui.board[2][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[1][1].getText().equals(player) && Triqui.board[2][2].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 0;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (Triqui.board[2][0].getText().equals(player) && Triqui.board[1][1].getText().equals(player)) {

      positions[0] = 0;
      positions[1] = 2;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[2][0].getText().equals(player) && Triqui.board[0][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (Triqui.board[1][1].getText().equals(player) && Triqui.board[0][2].getText()
        .equals(player)) {
      positions[0] = 2;
      positions[1] = 0;

      if (Triqui.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    return thereIsDanger;
  }

  public static int[] getPositions() {
    return positions;
  }
}