package com.bookverse.development.packapps.models;

import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.constants.AppConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public final class DataSet {

  private static ResourceBundle config;

  static {
    try {
      FileInputStream file = new FileInputStream(AppConfig.FILE_PROPERTIES.getProperty());
      config = new PropertyResourceBundle(file);
    } catch (IOException e) {
      Alerts.message("Error", e.getMessage());
    }
  }

  private DataSet() {
  }

  public static String getTitleApp() {
    return config.getString(AppConfig.TITLE.getProperty());
  }

  public static String getPasswordDBA() {
    return config.getString(AppConfig.PASSWORD_DBA.getProperty());
  }

  public static String getStoreManagerKey() {
    return config.getString(AppConfig.STORE_MANAGER_KEY.getProperty());
  }

  public static String getDefaultEncryptKey() {
    return config.getString(AppConfig.DEFAULT_ENCRYPT_KEY.getProperty());
  }

  public static String getDeveloperEmail() {
    return config.getString(AppConfig.DEVELOPER_EMAIL.getProperty());
  }
}
