package com.bookverse.packapps.apps.feedback.comment;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.bookverse.packapps.repositories.OlderRepository;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.GeneralUtils;
import com.bookverse.packapps.utils.ui.Alerts;

import static com.bookverse.packapps.utils.constants.DatabaseConstants.FEEDBACK;

public final class FeedbackService {

  public static void sendCommentary(String username, String commentary) {


    String[] data = {FEEDBACK, username, commentary, Format.getDate()};

    if (OlderRepository.insertData(data)) {

      JOptionPane.showMessageDialog(null,
          "<html>" + Format.style()
              + "<strong><center>Comment saved</center></strong><br>"
              + "Your feedback was saved in the selected database."
              + "</html>",
          "Successfully", JOptionPane.PLAIN_MESSAGE);
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
