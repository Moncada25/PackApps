package com.bookverse.development.packapps.automation.models;

public class Chronus {

  private String user;
  private String pass;
  private String url;
  private String hours;
  private String category;
  private String description;

  public String getUser() {
    return user;
  }

  public String getPass() {
    return pass;
  }

  public String getUrl() {
    return url;
  }

  public String getHours(){
    return hours;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Chronus(String user, String pass, String url, String hours, String category,
      String description) {
    this.user = user;
    this.pass = pass;
    this.url = url;
    this.hours = hours;
    this.category = category;
    this.description = description;
  }
}