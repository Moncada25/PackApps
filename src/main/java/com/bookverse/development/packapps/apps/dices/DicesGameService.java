package com.bookverse.development.packapps.apps.dices;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.Data;
import com.bookverse.development.packapps.models.Dice;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.GeneralUtils;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.Format;

@Data
public class DicesGameService {

  private final Dice d1 = new Dice();
  private final Dice d2 = new Dice();
  private final Dice d3 = new Dice();
  private int points1 = 0;
  private int points2 = 0;
  private int points3 = 0;
  private int round = 1;
  private int turn = 1;
  private boolean winner = false;

  private boolean addPoints(
      JTextField player,
      JButton btnThrow,
      JButton btnReset,
      List<JLabel> dices
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

  private void clickOnStart(DicesGameViewModel model) {

    model.getDices().getFirst().setIcon(new ImageIcon(Resources.getImage("01.gif")));
    model.getDices().get(1).setIcon(new ImageIcon(Resources.getImage("02.gif")));
    model.getDices().get(2).setIcon(new ImageIcon(Resources.getImage("03.gif")));

    model.getBtnThrow().setText("Stop");
    model.getBtnThrow().setBackground(Styles.MAIN_COLOR);

    model.getBtnExit().setEnabled(false);
    model.getPlayers().getFirst().setEnabled(false);
    model.getPlayers().get(1).setEnabled(false);
    model.getPlayers().get(2).setEnabled(false);

    if (turn == 1) {
      model.getParent()
          .setTitle("Round " + round + " - Turn of " + model.getPlayers().getFirst().getText());
    } else if (turn == 2) {
      model.getParent()
          .setTitle("Round " + round + " - Turn of " + model.getPlayers().get(1).getText());
    } else if (turn == 3) {
      model.getParent()
          .setTitle("Round " + round + " - Turn of " + model.getPlayers().get(2).getText());
    }
  }

  private void clickOnStop(DicesGameViewModel model) {

    if (round <= 5 && !winner) {

      if (turn == 1) {
        firstTurn(model);
      } else if (turn == 2) {
        secondTurn(model);
      } else if (turn == 3) {
        thirdTurn(model);
      }

    } else if (!winner) {

      model.getDices().getFirst().setIcon(null);
      model.getDices().get(1).setIcon(null);
      model.getDices().get(2).setIcon(null);

      highestScore(model);
    }

    if (round < 6) {
      model.getBtnThrow().setText("Throw");
      model.getBtnThrow().setBackground(Styles.TEXT_COLOR);
    } else if (!winner) {
      round--;
      highestScore(model);
    }
  }

  private void firstTurn(DicesGameViewModel model) {

    if (addPoints(model.getPlayers().getFirst(), model.getBtnThrow(), model.getBtnReset(), model.getDices())) {
      points1 += d1.getValue() + d2.getValue() + d3.getValue();
      model.getLblPoints().getFirst()
          .setText("<html><center><strong>" + points1 + "</strong></center></html>");
    }

    turn = 2;
  }

  private void secondTurn(DicesGameViewModel model) {

    if (addPoints(model.getPlayers().get(1), model.getBtnThrow(), model.getBtnReset(), model.getDices())) {
      points2 += d1.getValue() + d2.getValue() + d3.getValue();
      model.getLblPoints().get(1)
          .setText("<html><center><strong>" + points2 + "</strong></center></html>");
    }

    turn = 3;
  }

  private void thirdTurn(DicesGameViewModel model) {

    if (addPoints(model.getPlayers().get(2), model.getBtnThrow(), model.getBtnReset(), model.getDices())) {
      points3 += d1.getValue() + d2.getValue() + d3.getValue();
      model.getLblPoints().get(2).setText(
          "<html><center><strong>" + points3 + "</strong></center></html>"
      );
    }

    turn = 1;
    round++;
  }

  private ImageIcon getIcon(int n) {
    return new ImageIcon(Resources.getImage(n + ".png"));
  }

  private void highestScore(DicesGameViewModel model) {

    if (points1 > points2 && points1 > points3) {
      Alerts.message(
          "Congratulations",
          model.getPlayers().getFirst().getText() + " is the winner, scored " + points1 + " points!"
      );
      insertResults(model.getPlayers().getFirst().getText(), points1 + " points");
    } else if (points2 > points1 && points2 > points3) {
      Alerts.message(
          "Congratulations",
          model.getPlayers().get(1).getText() + " is the winner, scored " + points2 + " points!"
      );
      insertResults(model.getPlayers().get(1).getText(), points2 + " points");
    } else if (points3 > points1 && points3 > points2) {
      Alerts.message(
          "Congratulations",
          model.getPlayers().get(2).getText() + " is the winner, scored " + points3 + " points!"
      );
      insertResults(model.getPlayers().get(2).getText(), points3 + " points");
    } else {

      if (points1 == points2 && points1 == points3) {
        Alerts.message("Congratulations", "The game ended, there was a triple tie!");
        insertResults(
            model.getPlayers().getFirst().getText() + ", " + model.getPlayers().get(1).getText()
                + " & " + model.getPlayers().get(2)
                .getText(),
            points1 + " points");
      } else if (points1 == points2) {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(
            model.getPlayers().getFirst().getText() + " & " + model.getPlayers().get(1).getText(),
            points1 + " points");
      } else if (points1 == points3) {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(
            model.getPlayers().getFirst().getText() + " & " + model.getPlayers().get(2).getText(),
            points1 + " points");
      } else {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(
            model.getPlayers().get(1).getText() + " & " + model.getPlayers().get(2).getText(),
            points2 + " points");
      }
    }

    model.getBtnReset().setEnabled(true);
    model.getBtnThrow().setEnabled(false);
    model.getParent().setTitle("GAME OVER");
  }

  public void insertResults(String name, String win) {

    if (GeneralUtils.verifyConnection("Data don't saved", true) && Alerts.saveGame()) {
      try {
        String[] data = {DatabaseConstants.DICES, name, win, String.valueOf(round),
            Format.getDate()};
        OlderRepository.insertData(data);
      } catch (Exception e) {
        Alerts.error(e, DatabaseConstants.DICES);
      }
    }
  }

  public void clickOnThrow(DicesGameViewModel model) {

    if (!model.getPlayers().getFirst().getText().equals(model.getPlayers().get(1).getText())
        && !model.getPlayers().getFirst().getText().equals(model.getPlayers().get(2).getText())
        && !model.getPlayers().get(1).getText().equals(model.getPlayers().get(2).getText())
        && !model.getPlayers().getFirst().getText().isEmpty()
        && !model.getPlayers().get(1).getText().isEmpty()
        && !model.getPlayers().get(2).getText().isEmpty()) {

      if (model.getBtnThrow().getText().equals("Throw")) {
        clickOnStart(model);
      } else if (model.getBtnThrow().getText().equals("Stop")) {
        clickOnStop(model);
      }

    } else {
      Alerts.message("Warning", "Some fields are empty or their text are repeated.");
    }
  }

  public void btnResetAP(DicesGameViewModel model) {

    round = 1;
    turn = 1;
    winner = false;
    model.getParent().setTitle(DatabaseConstants.DICES + ", throw them!");
    model.getBtnThrow().setText("Throw");
    model.getBtnThrow().setBackground(Styles.TEXT_COLOR);
    model.getBtnThrow().setEnabled(true);
    model.getBtnReset().setEnabled(false);
    model.getBtnExit().setEnabled(true);

    points1 = 0;
    model.getLblPoints().getFirst().setText("");
    model.getPlayers().getFirst().setEnabled(true);

    points2 = 0;
    model.getLblPoints().get(1).setText("");
    model.getPlayers().get(1).setEnabled(true);

    points3 = 0;
    model.getLblPoints().get(2).setText("");
    model.getPlayers().get(2).setEnabled(true);

    model.getDices().getFirst().setIcon(null);
    model.getDices().get(1).setIcon(null);
    model.getDices().get(2).setIcon(null);
  }
}
