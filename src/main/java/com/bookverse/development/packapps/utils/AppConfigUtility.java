package com.bookverse.development.packapps.utils;

public enum AppConfigUtility {

  TITLE("titleApp"),
  PASSWORD_DBA("passwordDBA"),
  STORE_MANAGER_KEY("storeManagerKey"),
  DEFAULT_ENCRYPT_KEY("defaultEncryptKey"),
  FILE_PROPERTIES("src/main/resources/data/config.properties");

  private String property;

  AppConfigUtility(String property) {
    this.property = property;
  }

  public String getProperty() {
    return property;
  }
}
