package com.bookverse.development.packapps.models;

import com.bookverse.development.packapps.utils.AppConfigUtility;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.jetbrains.annotations.NotNull;

public class AppConfigModel {

  private static ResourceBundle configProperties;

  static {
    try {
      FileInputStream file = new FileInputStream(AppConfigUtility.FILE_PROPERTIES.getProperty());
      configProperties = new PropertyResourceBundle(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private AppConfigModel() {
  }

  @NotNull
  public static String getTitleApp() {
    return configProperties.getString(AppConfigUtility.TITLE.getProperty());
  }

  @NotNull
  public static String getPasswordDBA() {
    return configProperties.getString(AppConfigUtility.PASSWORD_DBA.getProperty());
  }

  @NotNull
  public static String getStoreManagerKey() {
    return configProperties.getString(AppConfigUtility.STORE_MANAGER_KEY.getProperty());
  }

  @NotNull
  public static String getDefaultEncryptKey() {
    return configProperties.getString(AppConfigUtility.DEFAULT_ENCRYPT_KEY.getProperty());
  }
}
