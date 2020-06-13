package com.bookverse.development.packapps.models;

import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.AppConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class DataConfig {

  private static ResourceBundle configProperties;

  static {
    try {
      FileInputStream file = new FileInputStream(AppConfig.FILE_PROPERTIES.getProperty());
      configProperties = new PropertyResourceBundle(file);
    } catch (IOException e) {
      Alerts.message("Error", e.getMessage());
    }
  }

  private DataConfig() {
  }

  public static String getTitleApp() {
    return configProperties.getString(AppConfig.TITLE.getProperty());
  }

  public static String getPasswordDBA() {
    return configProperties.getString(AppConfig.PASSWORD_DBA.getProperty());
  }

  public static String getStoreManagerKey() {
    return configProperties.getString(AppConfig.STORE_MANAGER_KEY.getProperty());
  }

  public static String getDefaultEncryptKey() {
    return configProperties.getString(AppConfig.DEFAULT_ENCRYPT_KEY.getProperty());
  }

  public static String getDeveloperEmail() {
    return configProperties.getString(AppConfig.DEVELOPER_EMAIL.getProperty());
  }
}
