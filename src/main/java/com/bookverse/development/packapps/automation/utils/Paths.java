package com.bookverse.development.packapps.automation.utils;

public enum Paths {

  ULTIMATIX("https://auth.ultimatix.net/utxLogin/login"),
  BOOKVERSE_DEVELOPMENT("http://localhost/Bookverse/"),
  BOOKVERSE_PRODUCTION("http://bookverse.vzpla.net/");

  private String property;

  Paths(String property) {
    this.property = property;
  }

  public String getProperty() {
    return property;
  }
}
