package com.bookverse.development.packapps.apps.tictactoe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import lombok.Data;
import com.bookverse.development.packapps.utils.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Resources;

@Data
public class TicTacToeService {

  private Color color = new Color(100, 220, 0);
  private boolean vsCPU;
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
  private int[] cpuPositions = new int[2];

  public boolean bestCpuMove(String player, TicTacToeViewModel model) {
    
    if (model.getBoard()[0][0].getText().equals(player) && model.getBoard()[0][1].getText()
        .equals(player)) {

      cpuPositions[0] = 0;
      cpuPositions[1] = 2;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[0][0].getText().equals(player) && model.getBoard()[0][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 0;
      cpuPositions[1] = 1;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[0][1].getText().equals(player) && model.getBoard()[0][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 0;
      cpuPositions[1] = 0;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }
    }

    if (model.getBoard()[1][0].getText().equals(player) && model.getBoard()[1][1].getText()
        .equals(player)) {

      cpuPositions[0] = 1;
      cpuPositions[1] = 2;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[1][0].getText().equals(player) && model.getBoard()[1][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 1;
      cpuPositions[1] = 1;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[1][1].getText().equals(player) && model.getBoard()[1][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 1;
      cpuPositions[1] = 0;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }
    }

    if (model.getBoard()[2][0].getText().equals(player) && model.getBoard()[2][1].getText()
        .equals(player)) {

      cpuPositions[0] = 2;
      cpuPositions[1] = 2;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[2][0].getText().equals(player) && model.getBoard()[2][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 2;
      cpuPositions[1] = 1;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[2][1].getText().equals(player) && model.getBoard()[2][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 2;
      cpuPositions[1] = 0;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }
    }

    if (model.getBoard()[0][0].getText().equals(player) && model.getBoard()[1][0].getText()
        .equals(player)) {

      cpuPositions[0] = 2;
      cpuPositions[1] = 0;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[0][0].getText().equals(player) && model.getBoard()[2][0]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 1;
      cpuPositions[1] = 0;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[1][0].getText().equals(player) && model.getBoard()[2][0]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 0;
      cpuPositions[1] = 0;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }
    }

    if (model.getBoard()[0][1].getText().equals(player) && model.getBoard()[1][1].getText()
        .equals(player)) {

      cpuPositions[0] = 2;
      cpuPositions[1] = 1;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[0][1].getText().equals(player) && model.getBoard()[2][1]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 1;
      cpuPositions[1] = 1;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[1][1].getText().equals(player) && model.getBoard()[2][1]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 0;
      cpuPositions[1] = 1;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }
    }

    if (model.getBoard()[0][2].getText().equals(player) && model.getBoard()[1][2].getText()
        .equals(player)) {

      cpuPositions[0] = 2;
      cpuPositions[1] = 2;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[0][2].getText().equals(player) && model.getBoard()[2][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 1;
      cpuPositions[1] = 2;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[1][2].getText().equals(player) && model.getBoard()[2][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 0;
      cpuPositions[1] = 2;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }
    }

    if (model.getBoard()[0][0].getText().equals(player) && model.getBoard()[1][1].getText()
        .equals(player)) {

      cpuPositions[0] = 2;
      cpuPositions[1] = 2;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[0][0].getText().equals(player) && model.getBoard()[2][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 1;
      cpuPositions[1] = 1;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }

    } else if (model.getBoard()[1][1].getText().equals(player) && model.getBoard()[2][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 0;
      cpuPositions[1] = 0;

      if (model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty()) {
        return true;
      }
    }

    if (model.getBoard()[2][0].getText().equals(player) && model.getBoard()[1][1].getText()
        .equals(player)) {

      cpuPositions[0] = 0;
      cpuPositions[1] = 2;

      return model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty();

    } else if (model.getBoard()[2][0].getText().equals(player) && model.getBoard()[0][2]
        .getText()
        .equals(player)) {

      cpuPositions[0] = 1;
      cpuPositions[1] = 1;

      return model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty();

    } else if (model.getBoard()[1][1].getText().equals(player) && model.getBoard()[0][2]
        .getText()
        .equals(player)) {
      cpuPositions[0] = 2;
      cpuPositions[1] = 0;

      return model.getBoard()[cpuPositions[0]][cpuPositions[1]].getText().isEmpty();
    }

    return false;
  }

  public void clickOnPlay(TicTacToeViewModel model) {

    if (!win) {
      model.getLblTurn().setText("Turn of " + model.getTxtNameX().getText() + "");
      model.getLblTurn().setForeground(model.getColorX());
      turn = 1;
    }

    model.getTxtNameX().setEnabled(false);
    model.getTxtNameO().setEnabled(false);

    model.getBtnExit().setEnabled(false);
    model.getBtnReset().setEnabled(true);
    model.getBtnPlay().setEnabled(false);
  }

  public void clickOnReset(TicTacToeViewModel model) {

    model.getLblTurn().setText("");

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {
        model.getBoard()[f][c].setText("");
        model.getBoard()[f][c].setEnabled(true);
        model.getBoard()[f][c].setBackground(model.getParent().getBackground());
      }
    }

    model.getBtnExit().setEnabled(true);
    model.getBtnPlay().setEnabled(true);
    model.getBtnReset().setEnabled(false);

    if (pointsNumberX == 5 || pointsNumberO == 5) {
      model.getPointsX().setText("");
      model.getPointsO().setText("");
      pointsNumberX = 0;
      pointsNumberO = 0;
    }

    model.getImage().setIcon(null);
    turn = 0;
    limitMoves1 = 0;
    limitMoves2 = 0;
    moveMade = false;
    movesX = 0;
    movesO = 0;
    win = false;
  }

  public void clickOnBoard(ActionEvent e, TicTacToeViewModel model) {

    for (int f = 0; f < 3; f++) {

      for (int c = 0; c < 3; c++) {

        if (turn == 1) {

          if (e.getSource() == model.getBoard()[f][c]) {

            if (model.getBoard()[f][c].getText().isEmpty()) {

              if (limitMoves1 != 1) {
                model.getBoard()[f][c].setText("X");
                model.getBoard()[f][c].setForeground(model.getColorX());
                movesX++;
                turn = 2;

                if (!vsCPU) {

                  model.getLblTurn().setText("Turn of " + model.getTxtNameO().getText());
                  model.getLblTurn().setForeground(model.getColorO());
                }
              }

              if (limitMoves1 == 1) {
                model.getLblTurn().setText("Select one");
                model.getLblTurn().setForeground(model.getColorX());
              }

              if (moveMade) {
                model.getBoard()[newF][newC].setText("");
                model.getBoard()[newF][newC].setBackground(model.getParent().getBackground());
                model.getBoard()[f][c].setText("X");
                model.getBoard()[f][c].setForeground(model.getColorX());

                if (!vsCPU) {
                  model.getLblTurn().setText("Turn of " + model.getTxtNameO().getText());
                  model.getLblTurn().setForeground(model.getColorO());
                }

                moveMade = false;
                turn = 2;
              }

              if (movesX == 3) {
                limitMoves1 = 1;
              }

              winner(model);
            } else if (model.getBoard()[f][c].getText().equals("X")) {

              limitMoves(f, c, model);

            } else {
              model.getLblTurn().setText("Try again");
            }
          }

        } else if (turn == 2) {

          if (!vsCPU) {

            if (e.getSource() == model.getBoard()[f][c]) {

              if (model.getBoard()[f][c].getText().isEmpty()) {

                if (limitMoves2 != 1) {
                  model.getBoard()[f][c].setText("O");
                  model.getBoard()[f][c].setForeground(model.getColorO());
                  movesO++;
                  turn = 1;
                  model.getLblTurn().setText("Turn of " + model.getTxtNameX().getText());
                  model.getLblTurn().setForeground(model.getColorX());
                }

                if (limitMoves2 == 1) {
                  model.getLblTurn().setText("Select one");
                  model.getLblTurn().setForeground(model.getColorO());
                }

                if (moveMade) {
                  model.getBoard()[newF][newC].setText("");
                  model.getBoard()[newF][newC].setBackground(model.getParent().getBackground());
                  model.getBoard()[f][c].setText("O");
                  model.getBoard()[f][c].setForeground(model.getColorO());
                  model.getLblTurn().setText("Turn of " + model.getTxtNameX().getText());
                  model.getLblTurn().setForeground(model.getColorX());
                  moveMade = false;
                  turn = 1;
                }

                if (movesO == 3) {
                  limitMoves2 = 1;
                }

                winner(model);
              } else if (model.getBoard()[f][c].getText().equals("O")) {

                limitMoves(f, c, model);

              } else {
                model.getLblTurn().setText("Try again");
              }
            }

          } else {

            if (movesO < 3) {
              makeMoveCPU(model);
              movesO++;
            } else {
              moveCPU(model);
            }

            turn = 1;
            winner(model);
          }
        }
      }
    }
  }
  
  private void blockTab(String winner, TicTacToeViewModel model) {

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {

        if (model.getBoard()[f][c].getText().equals(winner)) {

          if ("X".equals(winner)) {
            model.getBoard()[f][c].setBackground(model.getColorX());
          } else {
            model.getBoard()[f][c].setBackground(model.getColorO());
          }
          model.getBoard()[f][c].setForeground(model.getParent().getForeground());
        }
      }
    }
    turn = 0;
  }

  private void count(String winner, TicTacToeViewModel model) {

    if (winner.equals("X")) {
      pointsNumberX++;
      model.getPointsX().setText(String.valueOf(pointsNumberX));
    } else {
      pointsNumberO++;
      model.getPointsO().setText(String.valueOf(pointsNumberO));
    }
  }

  private void countPoints(String winner, TicTacToeViewModel model) {

    if (winner.equals("X")) {

      count("X", model);

      printImage(pointsNumberX, model.getTxtNameX().getText(), model);

    } else {

      count("O", model);

      printImage(pointsNumberO, model.getTxtNameO().getText(), model);
    }
  }

  private void printImage(int points, String name, TicTacToeViewModel model) {
    if (points == 5) {
      model.getLblTurn().setText("Champion " + name + "!");
      model.getImage().setIcon(new ImageIcon(Resources.getImage("triquito.png")));
    } else {
      model.getLblTurn().setText("Winner " + name + "!");
      model.getImage().setIcon(new ImageIcon(Resources.getImage("cr7.png")));
    }
  }

  private void winner(TicTacToeViewModel model) {

    if ((model.getBoard()[0][0].getText().equals("X") && model.getBoard()[0][1].getText().equals("X") && model.getBoard()[0][2]
        .getText().equals("X"))
        || (model.getBoard()[1][0].getText().equals("X") && model.getBoard()[1][1].getText().equals("X") && model.getBoard()[1][2]
        .getText().equals("X"))
        || (model.getBoard()[2][0].getText().equals("X") && model.getBoard()[2][1].getText().equals("X") && model.getBoard()[2][2]
        .getText().equals("X"))
        || (model.getBoard()[0][0].getText().equals("X") && model.getBoard()[1][0].getText().equals("X") && model.getBoard()[2][0]
        .getText().equals("X"))
        || (model.getBoard()[0][1].getText().equals("X") && model.getBoard()[1][1].getText().equals("X") && model.getBoard()[2][1]
        .getText().equals("X"))
        || (model.getBoard()[0][2].getText().equals("X") && model.getBoard()[1][2].getText().equals("X") && model.getBoard()[2][2]
        .getText().equals("X"))
        || (model.getBoard()[0][0].getText().equals("X") && model.getBoard()[1][1].getText().equals("X") && model.getBoard()[2][2]
        .getText().equals("X"))
        || (model.getBoard()[0][2].getText().equals("X") && model.getBoard()[1][1].getText().equals("X") && model.getBoard()[2][0]
        .getText().equals("X"))) {

      model.getLblTurn().setText("Winner " + model.getTxtNameX().getText() + "!");
      model.getLblTurn().setForeground(model.getColorX());
      win = true;

      countPoints("X", model);
      blockTab("X", model);
    } else if (
        (model.getBoard()[0][0].getText().equals("O") && model.getBoard()[0][1].getText().equals("O") && model.getBoard()[0][2]
            .getText().equals("O"))
        || (model.getBoard()[1][0].getText().equals("O") && model.getBoard()[1][1].getText().equals("O")
            && model.getBoard()[1][2].getText().equals("O"))
        || (model.getBoard()[2][0].getText().equals("O") && model.getBoard()[2][1].getText().equals("O")
            && model.getBoard()[2][2].getText().equals("O"))
        || (model.getBoard()[0][0].getText().equals("O") && model.getBoard()[1][0].getText().equals("O")
            && model.getBoard()[2][0].getText().equals("O"))
        || (model.getBoard()[0][1].getText().equals("O") && model.getBoard()[1][1].getText().equals("O")
            && model.getBoard()[2][1].getText().equals("O"))
        || (model.getBoard()[0][2].getText().equals("O") && model.getBoard()[1][2].getText().equals("O")
            && model.getBoard()[2][2].getText().equals("O"))
        || (model.getBoard()[0][0].getText().equals("O") && model.getBoard()[1][1].getText().equals("O")
            && model.getBoard()[2][2].getText().equals("O"))
        || (model.getBoard()[0][2].getText().equals("O") && model.getBoard()[1][1].getText().equals("O")
            && model.getBoard()[2][0].getText().equals("O"))) {

      model.getLblTurn().setText("Winner " + model.getTxtNameO().getText() + "!");
      model.getLblTurn().setForeground(model.getColorO());
      win = true;

      countPoints("O", model);
      blockTab("O", model);
    }
  }

  private void limitMoves(int f, int c, TicTacToeViewModel model) {
    if (!moveMade && limitMoves1 == 1) {
      model.getBoard()[f][c].setBackground(color);
      newF = f;
      newC = c;

      model.getLblTurn().setText("Move to");

      moveMade = true;
    } else if (moveMade && limitMoves1 == 1) {

      if (model.getBoard()[f][c].getBackground() == color) {
        model.getBoard()[f][c].setBackground(model.getParent().getBackground());
        model.getLblTurn().setText("Select one");
        moveMade = false;
      } else if (model.getBoard()[newF][newC].getBackground() == color) {
        model.getBoard()[newF][newC].setBackground(model.getParent().getBackground());
        model.getBoard()[f][c].setBackground(color);
        newF = f;
        newC = c;
      }
    }
  }

  private int[] getBestMove(TicTacToeViewModel model) {

    int[] position = new int[2];

    int[][] points = getPoints(model);

    int aux = GeneralUtils.getIntRandom(0, 2);

    position[0] = points[aux][0];
    position[1] = points[aux][1];

    return position;
  }

  private void moveCPU(TicTacToeViewModel model) {

    int[] position = getBestMove(model);

    model.getBoard()[position[0]][position[1]].setText("");
    makeMoveCPU(model);
  }

  private int[][] getPoints(TicTacToeViewModel model) {

    int[][] points = new int[3][2];
    int count = 0;

    for (int i = 0; i < 3; i++) {

      for (int j = 0; j < 3; j++) {

        if (model.getBoard()[i][j].getText().equals("O")) {

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

  private void makeMoveCPU(TicTacToeViewModel model) {

    if (bestCpuMove("O", model)) {

      int[] positions = getCpuPositions();

      model.getBoard()[positions[0]][positions[1]].setText("O");
      model.getBoard()[positions[0]][positions[1]].setForeground(model.getColorO());

    } else if (bestCpuMove("X", model)) {

      int[] positions = getCpuPositions();

      model.getBoard()[positions[0]][positions[1]].setText("O");
      model.getBoard()[positions[0]][positions[1]].setForeground(model.getColorO());

    } else {

      if (model.getBoard()[1][1].getText().isEmpty()) {
        model.getBoard()[1][1].setText("O");
        model.getBoard()[1][1].setForeground(model.getColorO());
      } else if (model.getBoard()[0][0].getText().isEmpty()) {
        model.getBoard()[0][0].setText("O");
        model.getBoard()[0][0].setForeground(model.getColorO());
      } else if (model.getBoard()[2][2].getText().isEmpty()) {
        model.getBoard()[2][2].setText("O");
        model.getBoard()[2][2].setForeground(model.getColorO());
      }
    }
  }
}
