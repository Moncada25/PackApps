package com.bookverse.development.packapps.apps.services;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Alerts;

import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.FEEDBACK;

public final class FeedbackService {

  public static void sendCommentary(String username, String commentary) {

    if (GeneralUtils.verifyConnection("Make sure you are connected to a network", true)) {

      String[] data = {FEEDBACK, username, commentary, Format.getDate()};

      if (OlderRepository.insertData(data)) {

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

    if (user.getText().isEmpty()) {

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
