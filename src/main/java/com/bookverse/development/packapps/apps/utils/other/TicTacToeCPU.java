package com.bookverse.development.packapps.apps.utils.other;

import com.bookverse.development.packapps.apps.views.older.TicTacToe;

public final class TicTacToeCPU {

  private static int[] positions = new int[2];

  public static boolean bestMove(String player) {

    boolean thereIsDanger = false;

    if (TicTacToe.getBoard()[0][0].getText().equals(player) && TicTacToe.getBoard()[0][1].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 2;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[0][0].getText().equals(player) && TicTacToe.getBoard()[0][2]
        .getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 1;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[0][1].getText().equals(player) && TicTacToe.getBoard()[0][2]
        .getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 0;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.getBoard()[1][0].getText().equals(player) && TicTacToe.getBoard()[1][1].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 2;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[1][0].getText().equals(player) && TicTacToe.getBoard()[1][2]
        .getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[1][1].getText().equals(player) && TicTacToe.getBoard()[1][2]
        .getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 0;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.getBoard()[2][0].getText().equals(player) && TicTacToe.getBoard()[2][1].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 2;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[2][0].getText().equals(player) && TicTacToe.getBoard()[2][2]
        .getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 1;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[2][1].getText().equals(player) && TicTacToe.getBoard()[2][2]
        .getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 0;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.getBoard()[0][0].getText().equals(player) && TicTacToe.getBoard()[1][0].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 0;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[0][0].getText().equals(player) && TicTacToe.getBoard()[2][0]
        .getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 0;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[1][0].getText().equals(player) && TicTacToe.getBoard()[2][0]
        .getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 0;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.getBoard()[0][1].getText().equals(player) && TicTacToe.getBoard()[1][1].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 1;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[0][1].getText().equals(player) && TicTacToe.getBoard()[2][1]
        .getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[1][1].getText().equals(player) && TicTacToe.getBoard()[2][1]
        .getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 1;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.getBoard()[0][2].getText().equals(player) && TicTacToe.getBoard()[1][2].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 2;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[0][2].getText().equals(player) && TicTacToe.getBoard()[2][2]
        .getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 2;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[1][2].getText().equals(player) && TicTacToe.getBoard()[2][2]
        .getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 2;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.getBoard()[0][0].getText().equals(player) && TicTacToe.getBoard()[1][1].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 2;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[0][0].getText().equals(player) && TicTacToe.getBoard()[2][2]
        .getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[1][1].getText().equals(player) && TicTacToe.getBoard()[2][2]
        .getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 0;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.getBoard()[2][0].getText().equals(player) && TicTacToe.getBoard()[1][1].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 2;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[2][0].getText().equals(player) && TicTacToe.getBoard()[0][2]
        .getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.getBoard()[1][1].getText().equals(player) && TicTacToe.getBoard()[0][2]
        .getText()
        .equals(player)) {
      positions[0] = 2;
      positions[1] = 0;

      if (TicTacToe.getBoard()[positions[0]][positions[1]].getText().isEmpty()) {
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