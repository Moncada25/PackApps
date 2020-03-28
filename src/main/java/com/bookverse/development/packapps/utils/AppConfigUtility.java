package com.bookverse.development.packapps.utils;

public enum AppConfigUtility {

  TITLE("PackApps"),
  PASSWORD_DBA("admin"),
  STORE_MANAGER_KEY("123"),
  DEFAULT_ENCRYPT_KEY("PackAppsDefaultKey");

  private String property;

  AppConfigUtility(String property) {
    this.property = property;
  }

  public String getProperty() {
    return property;
  }
}
