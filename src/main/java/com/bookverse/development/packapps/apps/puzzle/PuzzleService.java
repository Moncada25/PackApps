package com.bookverse.development.packapps.apps.puzzle;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import lombok.Data;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.GeneralUtils;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Alerts;

@Data
public class PuzzleService {

  private int minutesTimer = 2;
  private int secondsTimer = 0;
  private int moviments = 0;
  private int size;
  private int side;

  public void btnResetAP(PuzzleViewModel model) {

    model.getBtnPlay().setEnabled(true);
    model.getBtnStop().setEnabled(false);
    model.getBtnExit().setEnabled(true);

    insert("Loser", model);

    cleanBoard(model);
  }

  private void cleanBoard(PuzzleViewModel model) {
    model.getLblTurn().setText("");
    model.getTimer().setText("");

    for (JButton[] jButtons : model.getBoard()) {
      for (int j = 0; j < model.getBoard().length; j++) {
        jButtons[j].setEnabled(false);
        jButtons[j].setText(".");
      }
    }
  }

  public void btnPlayAP(PuzzleViewModel model) {

    int n = (int) Math.pow(size, 2) - 1;
    int randomX = GeneralUtils.getIntRandom(0, size - 1);
    int randomY = GeneralUtils.getIntRandom(0, size - 1);

    model.getBoard()[randomX][randomY].setText("");

    for (JButton[] jButtons : model.getBoard()) {
      for (int c = 0; c < model.getBoard().length; c++) {

        if (!jButtons[c].getText().isEmpty()) {
          jButtons[c].setText(String.valueOf(n));
          n--;
        }
      }
    }

    unlock(model.getBoard());

    model.getLblTurn().setText("");
    moviments = 0;

    model.getBtnStop().setEnabled(true);
    model.getBtnPlay().setEnabled(false);
    model.getBtnExit().setEnabled(false);
  }

  public void clickOnBoard(ActionEvent e, PuzzleViewModel model) {

    for (int f = 0; f < model.getBoard().length; f++) {
      for (int c = 0; c < model.getBoard().length; c++) {

        if (e.getSource() == model.getBoard()[f][c]) {
          moviments++;
          makeMovement(f, c, model);
          unlock(model.getBoard());
          getWinner(model);
        }
      }
    }
  }

  public boolean validateVictory(PuzzleViewModel model) {

    int n = 1;
    boolean win = true;

    for (JButton[] jButtons : model.getBoard()) {

      for (int c = 0; c < model.getBoard().length; c++) {

        if (n >= size * size) {
          break;
        }

        if (!jButtons[c].getText().equals(String.valueOf(n))) {
          win = false;
        }

        n++;
      }
    }

    return win;
  }

  private void getWinner(PuzzleViewModel model) {

    model.getLblTurn().setText("Moviments: " + moviments);

    if (validateVictory(model)) {

      model.getLblTurn().setText("You won! With " + moviments + " moviments");
      model.getLblTurn().setForeground(Styles.MAIN_COLOR);

      insert("Winner", model);

      cleanBoard(model);

      model.getBtnExit().setEnabled(true);
      model.getBtnPlay().setEnabled(true);
      model.getBtnStop().setEnabled(false);
      model.getLblTurn().setText("");
      model.getTimer().setText("");
    }
  }

  private String getLevel(PuzzleViewModel model) {

    String level = "";

    if (size == 4) {
      level = "Easy";
    } else if (size == 5) {
      level = "Medium";
    } else if (size == 6) {
      level = "Hard";
    }

    return level + " - " + model.getTimer().getText();
  }

  private void insert(String state, PuzzleViewModel model) {

    if (GeneralUtils.verifyConnection("Data don't saved", true) && Alerts.saveGame()) {

      String[] data = {DatabaseConstants.PUZZLE, Alerts.inputText("Enter a Nickname", 20), state,
          getLevel(model), String.valueOf(moviments), Format.getDate()};

      OlderRepository.insertData(data);
    }
  }

  private void unlock(JButton[][] board) {

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {

        if (board[i][j].getText().isEmpty()) {

          if (i == 0 && j == 0) {
            board[0][1].setEnabled(true);
            board[0][1].setBackground(Styles.TEXT_COLOR);

            board[1][0].setEnabled(true);
            board[1][0].setBackground(Styles.TEXT_COLOR);
          } else if (i == board.length - 1 && j == board.length - 1) {
            board[board.length - 2][board.length - 1].setEnabled(true);
            board[board.length - 2][board.length - 1].setBackground(Styles.TEXT_COLOR);

            board[board.length - 1][board.length - 2].setEnabled(true);
            board[board.length - 1][board.length - 2].setBackground(Styles.TEXT_COLOR);
          } else if (i == 0 && j == board.length - 1) {
            board[0][board.length - 2].setEnabled(true);
            board[0][board.length - 2].setBackground(Styles.TEXT_COLOR);

            board[1][board.length - 1].setEnabled(true);
            board[1][board.length - 1].setBackground(Styles.TEXT_COLOR);
          } else if (i == board.length - 1 && j == 0) {
            board[board.length - 2][0].setEnabled(true);
            board[board.length - 2][0].setBackground(Styles.TEXT_COLOR);

            board[board.length - 1][1].setEnabled(true);
            board[board.length - 1][1].setBackground(Styles.TEXT_COLOR);
          } else if (i == 0 && j != board.length - 1) {
            board[0][j - 1].setEnabled(true);
            board[0][j - 1].setBackground(Styles.TEXT_COLOR);

            board[0][j + 1].setEnabled(true);
            board[0][j + 1].setBackground(Styles.TEXT_COLOR);

            board[1][j].setEnabled(true);
            board[1][j].setBackground(Styles.TEXT_COLOR);
          } else if (j == board.length - 1 && i != 0 && i != board.length - 1) {
            board[i - 1][j].setEnabled(true);
            board[i - 1][j].setBackground(Styles.TEXT_COLOR);

            board[i + 1][j].setEnabled(true);
            board[i + 1][j].setBackground(Styles.TEXT_COLOR);

            board[i][board.length - 2].setEnabled(true);
            board[i][board.length - 2].setBackground(Styles.TEXT_COLOR);
          } else if (i == board.length - 1 && j != 0 && j != board.length - 1) {
            board[board.length - 1][j - 1].setEnabled(true);
            board[board.length - 1][j - 1].setBackground(Styles.TEXT_COLOR);

            board[board.length - 1][j + 1].setEnabled(true);
            board[board.length - 1][j + 1].setBackground(Styles.TEXT_COLOR);

            board[board.length - 2][j].setEnabled(true);
            board[board.length - 2][j].setBackground(Styles.TEXT_COLOR);
          } else if (j == 0 && i != board.length - 1) {
            validateBorder(board, i, j);
          } else {
            validateBorder(board, i, j);
            board[i][j - 1].setEnabled(true);
            board[i][j - 1].setBackground(Styles.TEXT_COLOR);
          }
        }
      }
    }
  }

  private void validateBorder(JButton[][] board, int i, int j) {
    board[i - 1][j].setEnabled(true);
    board[i - 1][j].setBackground(Styles.TEXT_COLOR);
    board[i + 1][j].setEnabled(true);
    board[i + 1][j].setBackground(Styles.TEXT_COLOR);
    board[i][j + 1].setEnabled(true);
    board[i][j + 1].setBackground(Styles.TEXT_COLOR);
  }

  private void makeMovement(int f, int c, PuzzleViewModel model) {

    for (JButton[] jButtons : model.getBoard()) {
      for (int j = 0; j < model.getBoard().length; j++) {

        if (jButtons[j].getText().isEmpty()) {
          jButtons[j].setText(model.getBoard()[f][c].getText());
          jButtons[j].setEnabled(true);
          model.getBoard()[f][c].setEnabled(false);
          model.getBoard()[f][c].setText("");
        }
        jButtons[j].setEnabled(false);
      }
    }
  }
}
