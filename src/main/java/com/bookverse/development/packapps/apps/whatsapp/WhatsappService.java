package com.bookverse.development.packapps.apps.whatsapp;

import java.util.Map;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lombok.SneakyThrows;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;

public class WhatsappService {

  private static final String SELECT_COUNTRY = "Select country";
  private static final String URL = "https://web.whatsapp.com/send?phone=%s&text=%s";
  private static final Map<String, String> COUNTRIES = Map.of(
      "Argentina", "54",
      "Bolivia", "591",
      "Brasil", "55",
      "Colombia", "57",
      "Costa Rica", "506",
      "Chile", "56",
      "Ecuador", "593",
      "España", "34",
      "Estados Unidos", "1",
      "Venezuela", "58"
  );

  public void clickOnReturn(JTextField number, JDialog parent) {
    number.setText("");
    number.setEnabled(true);
    Effects.fadeOut(parent);
  }

  @SneakyThrows
  public void clickOnOpen(JTextField number, JComboBox<String> countries, JTextArea message) {

    if (GeneralUtils.verifyConnection("Connect to see more!", true)) {

      if (number.getText().length() >= 8) {

        String country = Objects.requireNonNull(countries.getSelectedItem()).toString();

        if (SELECT_COUNTRY.equals(country)) {
          Alerts.message("Verify!", "Select a country");
        } else {

          GeneralUtils.openUrl(String.format(
              URL,
              COUNTRIES.get(country) + number.getText(),
              message.getText().replaceAll("\\s", "+"))
          );
        }

      } else {

        Alerts.message("Verify!", "Phone number too short.");
        number.requestFocus();
      }
    }
  }

  public void fillCountries(JComboBox<String> countries) {
    countries.addItem(SELECT_COUNTRY);
    for (String country : COUNTRIES.keySet()) {
      countries.addItem(country);
    }
  }
}
