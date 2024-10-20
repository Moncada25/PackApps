package com.bookverse.development.packapps.utils.other;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bookverse.development.packapps.utils.constants.AppConfig;
import com.bookverse.development.packapps.utils.ui.Alerts;

public final class Config {

  private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

  private static ResourceBundle config;

  static {
    try {
      FileInputStream file = new FileInputStream(AppConfig.FILE_PROPERTIES.getProperty());
      config = new PropertyResourceBundle(file);
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
      Alerts.message("Error", e.getMessage());
    }
  }

  public static String get(String key) {
    return config.getString(key);
  }

  private Config() {
  }
}
