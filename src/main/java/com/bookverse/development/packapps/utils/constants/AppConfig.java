package com.bookverse.development.packapps.utils.constants;

import lombok.Getter;

@Getter
public enum AppConfig {

  TITLE("titleApp"),
  PASSWORD_DBA("passwordDBA"),
  STORE_MANAGER_KEY("storeManagerKey"),
  DEVELOPER_EMAIL("developerEmail"),
  DEFAULT_ENCRYPT_KEY("defaultEncryptKey"),
  FILE_PROPERTIES("src/main/resources/data/config.properties");

  private final String property;

  AppConfig(String property) {
    this.property = property;
  }

}