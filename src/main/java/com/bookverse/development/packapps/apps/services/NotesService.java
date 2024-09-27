package com.bookverse.development.packapps.apps.services;

import java.util.Objects;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.bookverse.development.packapps.apps.repositories.NotesRepository;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Resources;

public final class NotesService {

  public static final int MAX_NOTES = 10;
  private static int maxNote;
  private static int minNote;
  private static int thereAreNotes = 1;
  private static float missingNote = 0;
  private static float totalNote = 0;
  private static float totalPercentage = 0;
  private static float[] percentagesNumbers = new float[MAX_NOTES];
  private static float[] notesNumbers = new float[MAX_NOTES];

  private NotesService() {
  }

  private static void setScale(JRadioButton scale1, JRadioButton scale2) {

    if (scale1.isSelected()) {
      maxNote = 5;
      minNote = 3;
    } else if (scale2.isSelected()) {
      maxNote = 10;
      minNote = 5;
    }
  }

  private static boolean validateFields(JTextField txtName, JTextField[] notesFields) {
    return IntStream.range(0, thereAreNotes).noneMatch(i -> notesFields[i].getText().isEmpty()
        || Float.parseFloat(notesFields[i].getText()) > maxNote
        || txtName.getText().trim().isEmpty());
  }

  public static void clickOnAdd(
      JTextField[] notesFields, JComboBox<String>[] percentagesBoxes, JButton btnAddNote, JButton btnDeleteNote, JButton btnReset
  ) {

    notesFields[thereAreNotes].setVisible(true);
    percentagesBoxes[thereAreNotes].setVisible(true);
    btnReset.setEnabled(true);
    btnDeleteNote.setEnabled(true);

    thereAreNotes++;

    if (thereAreNotes == 10) {
      btnAddNote.setEnabled(false);
    }
  }

  public static void clickOnDelete(
      JTextField[] notesFields, JComboBox<String>[] percentagesBoxes, JButton btnAddNote, JButton btnDeleteNote, JButton btnReset
  ) {

    notesFields[thereAreNotes - 1].setVisible(false);
    percentagesBoxes[thereAreNotes - 1].setVisible(false);
    notesFields[thereAreNotes - 1].setText("");
    percentagesBoxes[thereAreNotes - 1].setSelectedIndex(19);
    btnAddNote.setEnabled(true);

    thereAreNotes--;

    if (thereAreNotes == 1) {
      btnReset.setEnabled(false);
      btnDeleteNote.setEnabled(false);
    }
  }

  private static void parseData(JTextField[] notesFields, JComboBox<String>[] percentagesBoxes) {
    IntStream.range(0, thereAreNotes).forEach(i -> {
      percentagesNumbers[i] = Integer.parseInt(
          Objects.requireNonNull(percentagesBoxes[i].getSelectedItem()).toString()
      );
      notesNumbers[i] = Float.parseFloat(notesFields[i].getText());
    });
  }

  private static void addNotes() {

    IntStream.range(0, thereAreNotes).forEach(i -> {
      totalPercentage += percentagesNumbers[i];
      totalNote += (notesNumbers[i] * percentagesNumbers[i]) / 100;
    });

    missingNote = (minNote - totalNote) / ((100 - totalPercentage) / 100);
  }

  public static void clickOnCalculate(
      JRadioButton scale1,
      JRadioButton scale2,
      JLabel image,
      JTextField txtName,
      JTextField[] notesFields,
      JComboBox<String>[] percentagesBoxes,
      JButton btnAddNote,
      JButton btnDeleteNote,
      JButton btnReset
  ) {

    setScale(scale1, scale2);

    if (validateFields(txtName, notesFields)) {

      totalNote = 0;
      totalPercentage = 0;

      parseData(notesFields, percentagesBoxes);
      addNotes();

      String scale = "";

      if (scale1.isSelected()) {
        scale = "0 to 5";
      } else if (scale2.isSelected()) {
        scale = "1 to 9";
      }

      if (totalPercentage > 100) {
        Alerts.message(
            "Verify!", "<html>" + Format.style() + "<strong>Percentage exceeded</strong></html>"
        );
        return;
      }

      if (totalPercentage == 100) {
        fullPercentage(
            image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset, scale
        );

      } else if (missingNote > maxNote) {
        isImposible(
            image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset, scale
        );
      } else {

        if (totalNote >= 0 && totalNote < minNote) {

          Alerts.message("Result",  "<html>" + Format.style() + "<strong>Name: </strong>" + txtName
              .getText() + "<br>"
              + "<strong>Note necessary to win: </strong>" + String
              .format("%.2f", missingNote) + "<br>"
              + "<strong>Remaining percentage: </strong>" + String
              .format("%.0f", 100 - totalPercentage)
              + "%" + "</html>");

          NotesRepository.insert("Maybe", scale, txtName.getText(), totalPercentage, totalNote);
          clickOnReset(image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);

        } else if (totalNote >= minNote) {

          image.setIcon(new ImageIcon(Resources.getImage("win.png")));

          Alerts.message("You won!", "<html>" + Format.style()
              + "<strong>Congratulations, you've already approved it!</strong><br><br>"
              + "<strong>Name: </strong>" + txtName.getText()
              + "<br>" + "<strong>Accumulated note: </strong>" + String
              .format("%.2f", totalNote)
              + "</html>");

          NotesRepository.insert("Approved", scale, txtName.getText(), totalPercentage, totalNote);
          clickOnReset(image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);
        }
      }

    } else {

      Alerts.message("Verify!",  "<html>" + Format.style()
          + "<strong>Review the following data</strong><br><br>"
          + "<strong>Notes: </strong>less than or equal to " + maxNote + "<br>"
          + "<strong>Fields: </strong>empty" + "</html>");
    }
  }

  private static void isImposible(JLabel image, JTextField txtName, JTextField[] notesFields,
      JComboBox<String>[] percentagesBoxes, JButton btnAddNote, JButton btnDeleteNote,
      JButton btnReset, String scale) {
    image.setIcon(new ImageIcon(Resources.getImage("dead.png")));

    Alerts.message("Ay :(", "<html>" + Format.style()
        + "<strong>There is nothing to do, better cancel...</strong><br><br>"
        + "<strong>Accumulated note: </strong>" + String.format("%.2f", totalNote) + "<br>"
        + "<strong>You would have to get " + String.format("%.2f", missingNote) + " in the "
        + String.format("%.0f", 100 - totalPercentage) + " % remaining</strong></html>");

    NotesRepository.insert("Reproved", scale, txtName.getText(), totalPercentage, totalNote);
    clickOnReset(image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);
  }

  private static void fullPercentage(JLabel image, JTextField txtName, JTextField[] notesFields,
      JComboBox<String>[] percentagesBoxes, JButton btnAddNote, JButton btnDeleteNote,
      JButton btnReset, String scale) {
    if (totalNote < minNote) {
      image.setIcon(new ImageIcon(Resources.getImage("dead.png")));
    } else {
      image.setIcon(new ImageIcon(Resources.getImage("win.png")));
    }

    Alerts.message(
        "Definitive",
        "<html>" + Format.style() + "<strong>Name: </strong>" + txtName.getText()
        + "<br>" + "<strong>Note: </strong>"
        + String.format("%.2f", totalNote) + "</html>"
    );

    if (totalNote < minNote) {
      NotesRepository.insert("Reproved", scale, txtName.getText(), totalPercentage, totalNote);
    } else {
      NotesRepository.insert("Approved", scale, txtName.getText(), totalPercentage, totalNote);
    }

    clickOnReset(image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);
  }

  public static void clickOnReset(
      JLabel image, JTextField txtName, JTextField[] notesFields, JComboBox<String>[] percentagesBoxes, JButton btnAddNote, JButton btnDeleteNote, JButton btnReset
  ) {

    IntStream.range(1, thereAreNotes).forEach(i -> {
      notesFields[i].setVisible(false);
      percentagesBoxes[i].setVisible(false);
      notesFields[i].setText("");
      percentagesBoxes[i].setSelectedIndex(19);
    });

    txtName.setText("");
    notesFields[0].setText("");
    percentagesBoxes[0].setSelectedIndex(19);
    thereAreNotes = 1;
    image.setIcon(null);
    btnAddNote.setEnabled(true);
    btnDeleteNote.setEnabled(false);
    btnReset.setEnabled(false);
  }
}
