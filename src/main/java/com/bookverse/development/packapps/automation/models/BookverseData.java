package com.bookverse.development.packapps.automation.models;

public class BookverseData {

  private String user;
  private String password;
  private String url;
  private String book;

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public String getUrl() {
    return url;
  }

  public String getBook() {
    return book;
  }

  public BookverseData(String user, String pass, String url, String book) {
    this.user = user;
    this.password = pass;
    this.url = url;
    this.book = book;
  }
}
