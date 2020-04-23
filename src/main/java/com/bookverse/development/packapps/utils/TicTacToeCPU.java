package com.bookverse.development.packapps.utils;

import com.bookverse.development.packapps.views.TicTacToe;

public class TicTacToeCPU {

  private static int[] positions = new int[2];

  public static boolean bestMove(String player) {

    boolean thereIsDanger = false;

    if (TicTacToe.board[0][0].getText().equals(player) && TicTacToe.board[0][1].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 2;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[0][0].getText().equals(player) && TicTacToe.board[0][2].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 1;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[0][1].getText().equals(player) && TicTacToe.board[0][2].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 0;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.board[1][0].getText().equals(player) && TicTacToe.board[1][1].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 2;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[1][0].getText().equals(player) && TicTacToe.board[1][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[1][1].getText().equals(player) && TicTacToe.board[1][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 0;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.board[2][0].getText().equals(player) && TicTacToe.board[2][1].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 2;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[2][0].getText().equals(player) && TicTacToe.board[2][2].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 1;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[2][1].getText().equals(player) && TicTacToe.board[2][2].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 0;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.board[0][0].getText().equals(player) && TicTacToe.board[1][0].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 0;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[0][0].getText().equals(player) && TicTacToe.board[2][0].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 0;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[1][0].getText().equals(player) && TicTacToe.board[2][0].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 0;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.board[0][1].getText().equals(player) && TicTacToe.board[1][1].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 1;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[0][1].getText().equals(player) && TicTacToe.board[2][1].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[1][1].getText().equals(player) && TicTacToe.board[2][1].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 1;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.board[0][2].getText().equals(player) && TicTacToe.board[1][2].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 2;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[0][2].getText().equals(player) && TicTacToe.board[2][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 2;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[1][2].getText().equals(player) && TicTacToe.board[2][2].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 2;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.board[0][0].getText().equals(player) && TicTacToe.board[1][1].getText()
        .equals(player)) {

      positions[0] = 2;
      positions[1] = 2;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[0][0].getText().equals(player) && TicTacToe.board[2][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[1][1].getText().equals(player) && TicTacToe.board[2][2].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 0;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }
    }

    if (TicTacToe.board[2][0].getText().equals(player) && TicTacToe.board[1][1].getText()
        .equals(player)) {

      positions[0] = 0;
      positions[1] = 2;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[2][0].getText().equals(player) && TicTacToe.board[0][2].getText()
        .equals(player)) {

      positions[0] = 1;
      positions[1] = 1;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
        thereIsDanger = true;

        return thereIsDanger;
      }

    } else if (TicTacToe.board[1][1].getText().equals(player) && TicTacToe.board[0][2].getText()
        .equals(player)) {
      positions[0] = 2;
      positions[1] = 0;

      if (TicTacToe.board[positions[0]][positions[1]].getText().equals("")) {
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