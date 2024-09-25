package com.bookverse.development.packapps.apps.services;

import java.awt.Desktop;
import java.net.URL;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bookverse.development.packapps.apps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.apps.utils.constants.ArrayData;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Effects;

public final class WhatsAppService {

  public static void clickOnReturn(JTextField number, JDialog parent) {
    number.setText("");
    number.setEnabled(true);
    Effects.fadeOut(parent);
  }

  public static void clickOnOpen(JTextField number, JComboBox<String> listCountry,
      JTextArea message) {

    if (GeneralUtils.verifyConnection("Connect to see more!", true)) {

      if (number.getText().length() >= 8) {

        if (!"Select country".equals(listCountry.getSelectedItem().toString())) {

          try {
            Desktop.getDesktop().browse(new URL(
                "https://web.whatsapp.com/send?phone=" + ArrayData.getCountryCode(
                    listCountry.getSelectedItem().toString()) + number.getText()
                    + "&text=" + message.getText().replaceAll("\\s", "+")).toURI());
          } catch (Exception ex) {
            Alerts.error(ex, "Opening URL");
          }

        } else {
          Alerts.message("Verify!", "Select a country");
        }

      } else {

        Alerts.message("Verify!", "Phone number too short.");
        number.requestFocus();
      }
    }
  }

  private WhatsAppService() {
  }
}
