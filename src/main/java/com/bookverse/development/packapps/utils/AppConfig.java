package com.bookverse.development.packapps.utils;

public enum AppConfig {

  TITLE("titleApp"),
  PASSWORD_DBA("passwordDBA"),
  STORE_MANAGER_KEY("storeManagerKey"),
  DEVELOPER_EMAIL("developerEmail"),
  DEFAULT_ENCRYPT_KEY("defaultEncryptKey"),
  CURRENT_SYSTEM("currentSystem"),
  FILE_PROPERTIES("src/main/resources/data/config.properties");

  private String property;

  AppConfig(String property) {
    this.property = property;
  }

  public String getProperty() {
    return property;
  }
}