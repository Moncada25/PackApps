package com.bookverse.development.packapps.models;

import com.bookverse.development.packapps.utils.GenericConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.jetbrains.annotations.NotNull;

public class DataAppConfig {

  private static ResourceBundle configProperties;

  static {
    try {
      FileInputStream file = new FileInputStream(GenericConstants.FILE_PROPERTIES);
      configProperties = new PropertyResourceBundle(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private DataAppConfig() {
  }

  @NotNull
  public static String getTitleApp() {
    return configProperties.getString(GenericConstants.TITLE);
  }

  @NotNull
  public static String getPasswordDBA() {
    return configProperties.getString(GenericConstants.PASSWORD_DBA);
  }

  @NotNull
  public static String getStoreManagerKey() {
    return configProperties.getString(GenericConstants.STORE_MANAGER_KEY);
  }

  @NotNull
  public static String getDefaultEncryptKey() {
    return configProperties.getString(GenericConstants.DEFAULT_ENCRYPT_KEY);
  }
}
