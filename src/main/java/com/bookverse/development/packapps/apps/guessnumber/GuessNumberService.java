package com.bookverse.development.packapps.apps.guessnumber;

import javax.swing.ImageIcon;
import lombok.Data;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.GeneralUtils;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Resources;

@Data
public class GuessNumberService {

  private boolean isHard;
  private int numberUser;
  private int numberRandom;
  private int high;
  private int attempts = 0;

  public void clickOnPlay(GuessNumberViewModel model) {

    high = Integer.parseInt(Alerts.inputNumber("Maximum number to guess", 6));

    int minimum = 1;
    numberRandom = GeneralUtils.getIntRandom(minimum, high);

    model.getGuessNumberTitle().setText("<html><em>"
                    + "<center><strong>Guess the number!</strong></center>"
                    + "<center>Is between " + minimum + " and " + high + "</center>"
                    + "<em></html>");

    model.getHelp().setText("<html>"
                 + "<center><strong>What number am I thinking?</strong></center>"
                 + "</html>");
    model.getHelp().setVisible(true);
    model.getQuestion().setIcon(new ImageIcon(Resources.getImage("x.png")));

    model.getTxtNumber().setVisible(true);
    model.getBtnPlay().setEnabled(false);

    model.getBtnReturn().setEnabled(isHard);

    attempts = 0;

    model.getTxtNumber().setText("");
  }

  public void tryToGuess(GuessNumberViewModel model) {

    try {

      if (!model.getTxtNumber().getText().isEmpty()) {
        numberUser = Integer.parseInt(model.getTxtNumber().getText());
        guessNumber(model);
        model.getTxtNumber().setText("");

        if (isHard) {
          model.getResponse().setVisible(false);
        } else {
          model.getHelp().setVisible(false);
        }

        model.getTxtNumber().requestFocus();
      } else {
        model.getHelp().setVisible(false);
        model.getResponse().setVisible(true);
        model.getResponse().setText("<html><center><strong>Enter a number</strong></center></html>");
      }

    } catch (Exception exception) {
      Alerts.error(exception, DatabaseConstants.GUESS_NUMBER);
    }
  }

  private void guessNumber(GuessNumberViewModel model) {

    if (numberUser < numberRandom) {
      attempts++;
      model.getResponse().setText("<html>"
                       + "<center><strong>The number you are looking for is greater</strong></center>"
                       + "</html>");

    } else if (numberUser > numberRandom) {
      attempts++;
      model.getResponse().setText("<html>"
                       + "<center><strong>The number you are looking for is smaller</strong></center>"
                       + "</html>");
    } else {
      attempts++;
      model.getHelp().setText("");
      model.getResponse().setVisible(true);
      model.getResponse().setBounds(60, 110, 300, 70);
      model.getResponse().setText("<html>"
                       + "<center><strong>You found it!</strong></center>"
                       + "<center>The number was " + numberRandom + "</center>"
                       + "<center>You did it in " + attempts + " attempts</center>"
                       + "</html>");
      model.getBtnPlay().setEnabled(true);
      model.getBtnReturn().setEnabled(true);

      saveData();

      model.getTxtNumber().setVisible(false);
      model.getGuessNumberTitle().setText("");
      model.getResponse().setText("");
      model.getResponse().setBounds(90, 110, 300, 70);
      model.getQuestion().setIcon(null);
    }
  }

  private void saveData() {

    if (GeneralUtils.verifyConnection("Data don't saved", true) && Alerts.saveGame()) {

      String level = (isHard) ? "Hard" : "Easy";

      try {
        String[] data = {DatabaseConstants.GUESS_NUMBER,
            Alerts.inputText("Enter a Nickname", 20),
            String.valueOf(high), level + " - " + attempts,
            Format.getDate()
        };
        OlderRepository.insertData(data);
      } catch (Exception e) {
        Alerts.error(e, DatabaseConstants.GUESS_NUMBER);
      }
    }
  }
}
