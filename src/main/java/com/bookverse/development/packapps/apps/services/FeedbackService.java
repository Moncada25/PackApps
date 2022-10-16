package com.bookverse.development.packapps.apps.services;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bookverse.development.packapps.apps.repositories.Database;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.other.GeneralUtilities;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;

import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.FEEDBACK;

public final class FeedbackService {

  public static void sendCommentary(String username, String commentary) {

    if (GeneralUtilities.verifyConnection("Make sure you are connected to a network", true)) {

      String[] data = {FEEDBACK, username, commentary, Format.getDate()};

      if (Database.insertData(data)) {

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style()
                + "<strong><center>Commentary sent</center></strong><br>"
                + "Feedback sent, your opinion will be taken into account."
                + "</html>",
            "Successfully", JOptionPane.PLAIN_MESSAGE);
      }
    }
  }

  public static void clickOnSend(JTextArea area, JTextField user) {

    if (user.getText().equals("")) {

      JOptionPane.showMessageDialog(null,
          "<html>" + Format.style() + "<strong>Username empty</strong></html>",
          "Verify!!",
          JOptionPane.PLAIN_MESSAGE);
      user.requestFocus();

    } else {

      if (area.getText().trim().length() < 3) {
        Alerts.message("Verify", "Message too short");
        area.requestFocus();
      } else {
        sendCommentary(user.getText(), area.getText());

        user.setText("");
        area.setText("");
      }
    }
  }

  private FeedbackService(){
  }
}
