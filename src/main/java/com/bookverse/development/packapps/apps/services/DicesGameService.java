package com.bookverse.development.packapps.apps.services;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.bookverse.development.packapps.models.Dice;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.other.GeneralUtils;

import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.DICES;
import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.utils.other.Format.getDate;

public final class DicesGameService {

  private static final Dice d1 = new Dice();
  private static final Dice d2 = new Dice();
  private static final Dice d3 = new Dice();
  private static int points1 = 0;
  private static int points2 = 0;
  private static int points3 = 0;
  private static int round = 1;
  private static int turn = 1;
  private static boolean winner = false;

  private DicesGameService() {
  }

  public static void clickOnThrow(
      List<JTextField> players,
      JButton btnExit,
      JButton btnThrow,
      JButton btnReset,
      List<JLabel> lblPoints,
      List<JLabel> dices,
      JDialog parent
  ) {

    if (!players.getFirst().getText().equals(players.get(1).getText())
        && !players.getFirst().getText().equals(players.get(2).getText())
        && !players.get(1).getText().equals(players.get(2).getText())
        && !players.getFirst().getText().isEmpty()
        && !players.get(1).getText().isEmpty()
        && !players.get(2).getText().isEmpty())
    {

      if (btnThrow.getText().equals("Throw")) {
        clickOnStart(players, btnThrow, btnExit, dices, parent);
      } else if (btnThrow.getText().equals("Stop")) {
        clickOnStop(players, btnThrow, btnReset, lblPoints, dices, parent);
      }

    } else {
      Alerts.message("Warning", "Some fields are empty or their text are repeated.");
    }
  }

  private static void clickOnStart(
      List<JTextField> players,
      JButton btnThrow,
      JButton btnExit,
      List<JLabel> dices,
      JDialog parent
  ) {

    dices.getFirst().setIcon(new ImageIcon(Resources.getImage("01.gif")));
    dices.get(1).setIcon(new ImageIcon(Resources.getImage("02.gif")));
    dices.get(2).setIcon(new ImageIcon(Resources.getImage("03.gif")));

    btnThrow.setText("Stop");
    btnThrow.setBackground(MAIN_COLOR);

    btnExit.setEnabled(false);
    players.getFirst().setEnabled(false);
    players.get(1).setEnabled(false);
    players.get(2).setEnabled(false);

    if (turn == 1) {
      parent.setTitle("Round " + round + " - Turn of " + players.getFirst().getText());
    } else if (turn == 2) {
      parent.setTitle("Round " + round + " - Turn of " + players.get(1).getText());
    } else if (turn == 3) {
      parent.setTitle("Round " + round + " - Turn of " + players.get(2).getText());
    }
  }

  private static void clickOnStop(
      List<JTextField> players,
      JButton btnThrow,
      JButton btnReset,
      List<JLabel> lblPoints,
      List<JLabel> dices,
      JDialog parent
  ) {

    if (round <= 5 && !winner) {

      if (turn == 1) {
        firstTurn(players, btnThrow, btnReset, lblPoints, dices);
      } else if (turn == 2) {
        secondTurn(players, btnThrow, btnReset, lblPoints, dices);
      } else if (turn == 3) {
        thirdTurn(players, btnThrow, btnReset, lblPoints, dices);
      }

    } else if (!winner) {

      dices.getFirst().setIcon(null);
      dices.get(1).setIcon(null);
      dices.get(2).setIcon(null);

      highestScore(players, btnThrow, btnReset, parent);
    }

    if (round < 6) {
      btnThrow.setText("Throw");
      btnThrow.setBackground(TEXT_COLOR);
    } else if (!winner) {
      round--;
      highestScore(players, btnThrow, btnReset, parent);
    }
  }

  private static void firstTurn(
      List<JTextField> players,
      JButton btnThrow,
      JButton btnReset,
      List<JLabel> lblPoints,
      List<JLabel> dices
  ) {

    if (addPoints(players.getFirst(), btnThrow, btnReset, dices)) {
      points1 += d1.getValue() + d2.getValue() + d3.getValue();
      lblPoints.getFirst()
          .setText("<html><center><strong>" + points1 + "</strong></center></html>");
    }

    turn = 2;
  }

  private static void secondTurn(
      List<JTextField> players,
      JButton btnThrow,
      JButton btnReset,
      List<JLabel> lblPoints,
      List<JLabel> dices
  ) {

    if (addPoints(players.get(1), btnThrow, btnReset, dices)) {
      points2 += d1.getValue() + d2.getValue() + d3.getValue();
      lblPoints.get(1)
          .setText("<html><center><strong>" + points2 + "</strong></center></html>");
    }

    turn = 3;
  }

  private static void thirdTurn(
      List<JTextField> players,
      JButton btnThrow,
      JButton btnReset,
      List<JLabel> lblPoints,
      List<JLabel> dices
  ) {

    if (addPoints(players.get(2), btnThrow, btnReset, dices)) {
      points3 += d1.getValue() + d2.getValue() + d3.getValue();
      lblPoints.get(2).setText(
          "<html><center><strong>" + points3 + "</strong></center></html>"
      );
    }

    turn = 1;
    round++;
  }

  private static boolean addPoints(
      JTextField player, JButton btnThrow, JButton btnReset, List<JLabel> dices
  ) {

    dices.getFirst().setIcon(getIcon(d1.throwDices()));
    dices.get(1).setIcon(getIcon(d2.throwDices()));
    dices.get(2).setIcon(getIcon(d3.throwDices()));

    if ((d1.getValue() == d2.getValue()) && (d1.getValue() == d3.getValue())) {
      Alerts.message(
          "Congratulations", player.getText() + " has won, took all three equals dices!"
      );

      dices.getFirst().setIcon(null);
      dices.get(1).setIcon(null);
      dices.get(2).setIcon(null);

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

  private static void highestScore(
      List<JTextField> players, JButton btnThrow, JButton btnReset, JDialog parent
  ) {

    if (points1 > points2 && points1 > points3) {
      Alerts.message("Congratulations",
          players.getFirst().getText() + " is the winner, scored " + points1 + " points!");
      insertResults(players.getFirst().getText(), points1 + " points");
    } else if (points2 > points1 && points2 > points3) {
      Alerts.message("Congratulations",
          players.get(1).getText() + " is the winner, scored " + points2 + " points!");
      insertResults(players.get(1).getText(), points2 + " points");
    } else if (points3 > points1 && points3 > points2) {
      Alerts.message("Congratulations",
          players.get(2).getText() + " is the winner, scored " + points3 + " points!");
      insertResults(players.get(2).getText(), points3 + " points");
    } else {

      if (points1 == points2 && points1 == points3) {
        Alerts.message("Congratulations", "The game ended, there was a triple tie!");
        insertResults(players.getFirst().getText() + ", " + players.get(1).getText() + " & " + players.get(2).getText(),
            points1 + " points");
      } else if (points1 == points2) {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(players.getFirst().getText() + " & " + players.get(1).getText(), points1 + " points");
      } else if (points1 == points3) {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(players.getFirst().getText() + " & " + players.get(2).getText(), points1 + " points");
      } else {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(players.get(1).getText() + " & " + players.get(2).getText(), points2 + " points");
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

  public static void btnResetAP(
      List<JTextField> players,
      JButton btnExit,
      JButton btnThrow,
      JButton btnReset,
      List<JLabel> lblPoints,
      List<JLabel> dices,
      JDialog parent
  ) {

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
    lblPoints.getFirst().setText("");
    players.getFirst().setEnabled(true);

    points2 = 0;
    lblPoints.get(1).setText("");
    players.get(1).setEnabled(true);

    points3 = 0;
    lblPoints.get(2).setText("");
    lblPoints.get(2).setEnabled(true);

    dices.getFirst().setIcon(null);
    dices.get(1).setIcon(null);
    dices.get(2).setIcon(null);
  }
}
