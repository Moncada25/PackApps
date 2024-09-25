package com.bookverse.development.packapps.apps.services;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.bookverse.development.packapps.apps.models.Dice;
import com.bookverse.development.packapps.apps.repositories.OlderRepository;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.other.GeneralUtils;

import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.DICES;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.apps.utils.other.Format.getDate;

public final class DicesGameService {

  private static final Dice d1 = new Dice();
  private static final Dice d2 = new Dice();
  private static final Dice d3 = new Dice();
  private static int points1 = 0, points2 = 0, points3 = 0, round = 1, turn = 1;
  private static boolean winner = false;
  
  public static void clickOnThrow(
      JTextField player1, JTextField player2, JTextField player3, JButton btnExit, JButton btnThrow,
      JButton btnReset, JLabel lblPoints1, JLabel lblPoints2, JLabel lblPoints3, JLabel dice1,
      JLabel dice2, JLabel dice3, JDialog parent
  ) {

    if (!player1.getText().equals(player2.getText()) && !player1.getText().equals(player3.getText())
        && !player2.getText().equals(player3.getText()) && !player1.getText().isEmpty() && !player2
        .getText().isEmpty() && !player3.getText().isEmpty()) {

      if (btnThrow.getText().equals("Throw")) {
        clickOnStart(player1, player2, player3, btnThrow, btnExit, dice1, dice2, dice3, parent);
      } else if (btnThrow.getText().equals("Stop")) {
        clickOnStop(
            player1, player2, player3, btnThrow, btnReset, lblPoints1,
            lblPoints2, lblPoints3, dice1, dice2, dice3, parent
        );
      }

    } else {
      Alerts.message("Warning", "Some fields are empty or their text are repeated.");
    }
  }

  private static void clickOnStart(
      JTextField player1, JTextField player2, JTextField player3,
      JButton btnThrow, JButton btnExit, JLabel dice1, JLabel dice2, JLabel dice3, JDialog parent) {

    dice1.setIcon(new ImageIcon(Resources.getImage("01.gif")));
    dice2.setIcon(new ImageIcon(Resources.getImage("02.gif")));
    dice3.setIcon(new ImageIcon(Resources.getImage("03.gif")));

    btnThrow.setText("Stop");
    btnThrow.setBackground(MAIN_COLOR);

    btnExit.setEnabled(false);
    player1.setEnabled(false);
    player2.setEnabled(false);
    player3.setEnabled(false);

    if (turn == 1) {
      parent.setTitle("Round " + round + " - Turn of " + player1.getText());
    } else if (turn == 2) {
      parent.setTitle("Round " + round + " - Turn of " + player2.getText());
    } else if (turn == 3) {
      parent.setTitle("Round " + round + " - Turn of " + player3.getText());
    }
  }

  private static void clickOnStop(
      JTextField player1, JTextField player2, JTextField player3, JButton btnThrow, JButton btnReset,
      JLabel lblPoints1, JLabel lblPoints2, JLabel lblPoints3, JLabel dice1, JLabel dice2,
      JLabel dice3, JDialog parent
  ) {

    if (round <= 5 && !winner) {

      if (turn == 1) {

        if (addPoints(player1, btnThrow, btnReset, dice1, dice2, dice3)) {
          points1 += d1.getValue() + d2.getValue() + d3.getValue();
          lblPoints1.setText("<html><center><strong>" + points1 + "</strong></center></html>");
        }

        turn = 2;
      } else if (turn == 2) {

        if (addPoints(player1, btnThrow, btnReset, dice1, dice2, dice3)) {
          points2 += d1.getValue() + d2.getValue() + d3.getValue();
          lblPoints2.setText("<html><center><strong>" + points2 + "</strong></center></html>");
        }

        turn = 3;
      } else if (turn == 3) {

        if (addPoints(player1, btnThrow, btnReset, dice1, dice2, dice3)) {
          points3 += d1.getValue() + d2.getValue() + d3.getValue();
          lblPoints3.setText("<html><center><strong>" + points3 + "</strong></center></html>");
        }

        turn = 1;
        round++;
      }
    } else if (!winner) {

      dice1.setIcon(null);
      dice2.setIcon(null);
      dice3.setIcon(null);

      highestScore(player1, player2, player3, btnThrow, btnReset, parent);
    }

    if (round < 6) {
      btnThrow.setText("Throw");
      btnThrow.setBackground(TEXT_COLOR);
    } else if (!winner) {
      round--;
      highestScore(player1, player2, player3, btnThrow, btnReset, parent);
    }
  }

  private static boolean addPoints(JTextField player, JButton btnThrow, JButton btnReset, JLabel dice1, JLabel dice2, JLabel dice3) {

    dice1.setIcon(getIcon(d1.throwDices()));
    dice2.setIcon(getIcon(d2.throwDices()));
    dice3.setIcon(getIcon(d3.throwDices()));

    if ((d1.getValue() == d2.getValue()) && (d1.getValue() == d3.getValue())) {
      Alerts.message("Congratulations", player.getText() + " has won, took all three equals dices!");

      dice1.setIcon(null);
      dice2.setIcon(null);
      dice3.setIcon(null);

      insertResults(player.getText(), "Equals dices!");
      btnReset.setEnabled(true);
      btnThrow.setEnabled(false);
      winner = true;
    }

    return (d1.getValue() != d2.getValue()) || (d1.getValue() != d3.getValue());
  }

    private static ImageIcon getIcon(int n) {
    return new ImageIcon(Resources.getImage(n + ".png"));
  }

  private static void highestScore(JTextField player1, JTextField player2, JTextField player3, JButton btnThrow, JButton btnReset, JDialog parent) {

    if (points1 > points2 && points1 > points3) {
      Alerts.message("Congratulations",
          player1.getText() + " is the winner, scored " + points1 + " points!");
      insertResults(player1.getText(), points1 + " points");
    } else if (points2 > points1 && points2 > points3) {
      Alerts.message("Congratulations",
          player2.getText() + " is the winner, scored " + points2 + " points!");
      insertResults(player2.getText(), points2 + " points");
    } else if (points3 > points1 && points3 > points2) {
      Alerts.message("Congratulations",
          player3.getText() + " is the winner, scored " + points3 + " points!");
      insertResults(player3.getText(), points3 + " points");
    } else {

      if (points1 == points2 && points1 == points3) {
        Alerts.message("Congratulations", "The game ended, there was a triple tie!");
        insertResults(player1.getText() + ", " + player2.getText() + " & " + player3.getText(),
            points1 + " points");
      } else if (points1 == points2) {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(player1.getText() + " & " + player2.getText(), points1 + " points");
      } else if (points1 == points3) {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(player1.getText() + " & " + player3.getText(), points1 + " points");
      } else {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(player2.getText() + " & " + player3.getText(), points2 + " points");
      }
    }

    btnReset.setEnabled(true);
    btnThrow.setEnabled(false);
    parent.setTitle("GAME OVER");
  }

  public static void insertResults(String name, String win) {

    if (GeneralUtils.verifyConnection("Data don't saved", true) && Alerts.saveGame()) {
      try {
        String[] data = {DICES, name, win, String.valueOf(round), getDate()};
        OlderRepository.insertData(data);
      } catch (Exception e) {
        Alerts.error(e, DICES);
      }
    }
  }

  public static void btnResetAP(JTextField player1, JTextField player2, JTextField player3, JButton btnExit, JButton btnThrow,
      JButton btnReset, JLabel lblPoints1, JLabel lblPoints2, JLabel lblPoints3, JLabel dice1,
      JLabel dice2, JLabel dice3, JDialog parent) {

    round = 1;
    turn = 1;
    winner = false;
    parent.setTitle(DICES + ", throw them!");
    btnThrow.setText("Throw");
    btnThrow.setBackground(TEXT_COLOR);
    btnThrow.setEnabled(true);
    btnReset.setEnabled(false);
    btnExit.setEnabled(true);

    points1 = 0;
    lblPoints1.setText("");
    player1.setEnabled(true);

    points2 = 0;
    lblPoints2.setText("");
    player2.setEnabled(true);

    points3 = 0;
    lblPoints3.setText("");
    player3.setEnabled(true);

    dice1.setIcon(null);
    dice2.setIcon(null);
    dice3.setIcon(null);
  }
}
