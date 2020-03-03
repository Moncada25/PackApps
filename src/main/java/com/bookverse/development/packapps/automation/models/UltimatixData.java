package com.bookverse.development.packapps.automation.models;

public class UltimatixData {

  private String user;
  private String pass;
  private String url;
  private String hours;

  public String getUser() {
    return user;
  }

  public String getPass() {
    return pass;
  }

  public String getUrl(){
    return url;
  }

  public String getHours(){
    return hours;
  }

  public UltimatixData(String user, String pass, String url, String hours) {
    this.user = user;
    this.pass = pass;
    this.url = url;
    this.hours = hours;
  }
}
