package com.bookverse.development.packapps.apps.store.signup;

import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.constants.AppConfig;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.other.Config;
import com.bookverse.development.packapps.utils.other.Crypto;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;

public class SignUpService {

  public void clickOnReturn(SignUpViewModel model) {
    model.getTxtUser().setText("");
    model.getTxtUser().setEnabled(true);
    model.getTxtCodManager().setText("");
    model.getTxtPassword().setEnabled(true);
    model.getTxtPassword().setText("");
    Effects.fadeOut(model.getParent());
  }

  public void clickOnSignUp(SignUpViewModel model) {

    if (String.valueOf(model.getTxtCodManager().getPassword())
        .equals(Config.get(AppConfig.STORE_MANAGER_KEY.getProperty()))) {

      model.getTxtUser().setEnabled(true);
      model.getTxtPassword().setEnabled(true);
      model.getTxtUser().requestFocus();

      if (Format.verifyCredentials(model.getTxtUser().getText()) && Format
          .verifyCredentials(String.valueOf(model.getTxtPassword().getPassword()))) {

        if (!OlderRepository.userAlreadyExist(model.getTxtUser().getText())) {

          String[] data = {DatabaseConstants.USERS, model.getTxtUser().getText(), Crypto.encrypt(String.valueOf(
              model.getTxtPassword().getPassword()), true), "Offline"};

          OlderRepository.insertData(data);
          Alerts.message("Message", "Registered user!");

          model.getTxtUser().setText("");
          model.getTxtPassword().setText("");
          model.getTxtCodManager().setText("");
          Effects.fadeOut(model.getParent());
        } else {
          Alerts.message("Message", "User already exists, please try to login.");
          Effects.fadeOut(model.getParent());
        }

      } else {
        Alerts.message("Message", "The username and / or password are too weak, please try again.");
      }

    } else {
      Alerts.message("Message", "Wrong manager key, please try again.");
      model.getTxtUser().setEnabled(false);
      model.getTxtPassword().setEnabled(false);
      model.getTxtCodManager().setText("");
      model.getTxtCodManager().requestFocus();
    }
  }
}
