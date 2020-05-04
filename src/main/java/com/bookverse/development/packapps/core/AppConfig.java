package com.bookverse.development.packapps.core;

public enum AppConfig {

  TITLE("PackApps"),
  PASSWORD_DBA("admin"),
  STORE_MANAGER_KEY("123"),
  DEVELOPER_EMAIL("zanti4020@gmail.com"),
  DEFAULT_ENCRYPT_KEY("PackAppsDefaultKey");

  private String property;

  AppConfig(String property) {
    this.property = property;
  }

  public String getProperty() {
    return property;
  }
}