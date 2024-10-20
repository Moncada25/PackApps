package com.bookverse.development.packapps.apps.notes;

import lombok.Data;
import java.util.Objects;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import com.bookverse.development.packapps.repositories.NotesRepository;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Resources;

@Data
public class NotesService {

  private int allNotes = 10;
  private int maxNote;
  private int minNote;
  private int thereAreNotes = 1;
  private float missingNote = 0;
  private float totalNote = 0;
  private float totalPercentage = 0;
  private float[] percentagesNumbers = new float[allNotes];
  private float[] notesNumbers = new float[allNotes];

  public void clickOnAdd(NotesViewModel model) {

    model.getNotesFields()[thereAreNotes].setVisible(true);
    model.getPercentagesBoxes()[thereAreNotes].setVisible(true);
    model.getBtnReset().setEnabled(true);
    model.getBtnDeleteNote().setEnabled(true);

    thereAreNotes++;

    if (thereAreNotes == 10) {
      model.getBtnAddNote().setEnabled(false);
    }
  }

  public void clickOnDelete(NotesViewModel model) {

    model.getNotesFields()[thereAreNotes - 1].setVisible(false);
    model.getPercentagesBoxes()[thereAreNotes - 1].setVisible(false);
    model.getNotesFields()[thereAreNotes - 1].setText("");
    model.getPercentagesBoxes()[thereAreNotes - 1].setSelectedIndex(1);
    model.getBtnAddNote().setEnabled(true);

    thereAreNotes--;

    if (thereAreNotes == 1) {
      model.getBtnReset().setEnabled(false);
      model.getBtnDeleteNote().setEnabled(false);
    }
  }

  public void clickOnReset(NotesViewModel model) {

    IntStream.range(0, thereAreNotes).forEach(i -> {
      model.getNotesFields()[i].setVisible(false);
      model.getPercentagesBoxes()[i].setVisible(false);
      model.getNotesFields()[i].setText("");
      model.getPercentagesBoxes()[i].setSelectedIndex(1);
    });

    model.getNotesFields()[0].setVisible(true);
    model.getPercentagesBoxes()[0].setVisible(true);

    model.getTxtName().setText("");
    model.getNotesFields()[0].setText("");
    model.getPercentagesBoxes()[0].setSelectedIndex(1);
    thereAreNotes = 1;
    model.getImage().setIcon(null);
    model.getBtnAddNote().setEnabled(true);
    model.getBtnDeleteNote().setEnabled(false);
    model.getBtnReset().setEnabled(false);

    model.getContainer().setSize(300, 400);
  }

  public void clickOnCalculate(NotesViewModel model) {

    setScale(model);

    if (validateFields(model)) {

      totalNote = 0;
      totalPercentage = 0;

      parseData(model);
      addNotes();

      if (totalPercentage > 100) {
        Alerts.message(
            "Verify!", "<html>" + Format.style() + "<strong>Percentage exceeded</strong></html>"
        );
        return;
      }

      if (totalPercentage == 100) {
        fullPercentage(model);
      } else if (missingNote > maxNote) {
        isImposible(model);
      } else {

        if (totalNote >= 0 && totalNote < minNote) {

          Alerts.message("Result",  "<html>" + Format.style() + "<strong>Name: </strong>" + model.getTxtName()
              .getText() + "<br>"
              + "<strong>Note necessary to win: </strong>" + String
              .format("%.2f", missingNote) + "<br>"
              + "<strong>Remaining percentage: </strong>" + String
              .format("%.0f", 100 - totalPercentage)
              + "%" + "</html>");

          NotesRepository.insert("Maybe", getScala(model), model.getTxtName().getText(), totalPercentage, totalNote);
          clickOnReset(model);

        } else if (totalNote >= minNote) {

          model.getContainer().setSize(model.getContainer().getWidth(), model.getContainer().getHeight() + 90);

          model.getImage().setIcon(new ImageIcon(Resources.getImage("win.png")));

          Alerts.message("You won!", "<html>" + Format.style()
              + "<strong>Congratulations, you've already approved it!</strong><br><br>"
              + "<strong>Name: </strong>" + model.getTxtName().getText()
              + "<br>" + "<strong>Accumulated note: </strong>" + String
              .format("%.2f", totalNote)
              + "</html>");

          NotesRepository.insert("Approved", getScala(model), model.getTxtName().getText(), totalPercentage, totalNote);
          clickOnReset(model);
        }
      }

    } else {

      Alerts.message("Verify!",  "<html>" + Format.style()
          + "<strong>Review the following data</strong><br><br>"
          + "<strong>Notes: </strong>less than or equal to " + maxNote + "<br>"
          + "<strong>Fields: </strong>empty" + "</html>");
    }
  }

  public String[] getPercentages() {
    return new String[]{"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
  }

  private void isImposible(NotesViewModel model) {

    model.getContainer().setSize(model.getContainer().getWidth(), model.getContainer().getHeight() + 90);
    model.getImage().setIcon(new ImageIcon(Resources.getImage("dead.png")));

    Alerts.message("Ay :(", "<html>" + Format.style()
        + "<strong>There is nothing to do, better cancel...</strong><br><br>"
        + "<strong>Accumulated note: </strong>" + String.format("%.2f", totalNote) + "<br>"
        + "<strong>You would have to get " + String.format("%.2f", missingNote) + " in the "
        + String.format("%.0f", 100 - totalPercentage) + " % remaining</strong></html>");

    NotesRepository.insert("Reproved", getScala(model), model.getTxtName().getText(), totalPercentage, totalNote);
    clickOnReset(model);
  }

  private void parseData(NotesViewModel model) {
    IntStream.range(0, thereAreNotes).forEach(i -> {
      percentagesNumbers[i] = Integer.parseInt(
          Objects.requireNonNull(model.getPercentagesBoxes()[i].getSelectedItem()).toString()
      );
      notesNumbers[i] = Float.parseFloat(model.getNotesFields()[i].getText());
    });
  }

  private void addNotes() {

    IntStream.range(0, thereAreNotes).forEach(i -> {
      totalPercentage += percentagesNumbers[i];
      totalNote += (notesNumbers[i] * percentagesNumbers[i]) / 100;
    });

    missingNote = (minNote - totalNote) / ((100 - totalPercentage) / 100);
  }

  private void fullPercentage(NotesViewModel model) {

    model.getContainer().setSize(model.getContainer().getWidth(), model.getContainer().getHeight() + 90);

    if (totalNote < minNote) {
      model.getImage().setIcon(new ImageIcon(Resources.getImage("dead.png")));
    } else {
      model.getImage().setIcon(new ImageIcon(Resources.getImage("win.png")));
    }

    Alerts.message(
        "Definitive",
        "<html>" + Format.style() + "<strong>Name: </strong>" + model.getTxtName().getText()
        + "<br>" + "<strong>Note: </strong>"
        + String.format("%.2f", totalNote) + "</html>"
    );

    if (totalNote < minNote) {
      NotesRepository.insert("Reproved", getScala(model), model.getTxtName().getText(), totalPercentage, totalNote);
    } else {
      NotesRepository.insert("Approved", getScala(model), model.getTxtName().getText(), totalPercentage, totalNote);
    }

    clickOnReset(model);
  }

  private String getScala(NotesViewModel model) {
    if (model.getScale1().isSelected()) {
      return "0 to 5";
    }
    if (model.getScale2().isSelected()) {
      return "1 to 9";
    }
    return "";
  }

  private void setScale(NotesViewModel model) {

    if (model.getScale1().isSelected()) {
      maxNote = 5;
      minNote = 3;
    } else if (model.getScale2().isSelected()) {
      maxNote = 10;
      minNote = 5;
    }
  }

  private boolean validateFields(NotesViewModel model) {

    if (model.getTxtName().getText().trim().isEmpty()) {
      return false;
    }

    for (int i = 0; i < thereAreNotes; i++) {
      if (model.getNotesFields()[i].getText().isEmpty() || Float.parseFloat(model.getNotesFields()[i].getText()) > maxNote) {
        return false;
      }
    }

    return true;
  }
}
