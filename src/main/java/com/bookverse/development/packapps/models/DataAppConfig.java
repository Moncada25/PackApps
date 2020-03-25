package com.bookverse.development.packapps.models;

import com.bookverse.development.packapps.utils.ConfigData;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.jetbrains.annotations.NotNull;

public class DataAppConfig {

  private static ResourceBundle configProperties;

  static {
    try {
      FileInputStream file = new FileInputStream(ConfigData.FILE_PROPERTIES.getProperty());
      configProperties = new PropertyResourceBundle(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private DataAppConfig() {
  }

  @NotNull
  public static String getTitleApp() {
    return configProperties.getString(ConfigData.TITLE.getProperty());
  }

  @NotNull
  public static String getPasswordDBA() {
    return configProperties.getString(ConfigData.PASSWORD_DBA.getProperty());
  }

  @NotNull
  public static String getStoreManagerKey() {
    return configProperties.getString(ConfigData.STORE_MANAGER_KEY.getProperty());
  }

  @NotNull
  public static String getDefaultEncryptKey() {
    return configProperties.getString(ConfigData.DEFAULT_ENCRYPT_KEY.getProperty());
  }
}
