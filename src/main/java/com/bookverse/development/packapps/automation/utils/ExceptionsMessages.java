package com.bookverse.development.packapps.automation.utils;

public enum ExceptionsMessages {

  SEARCH_BOOK_ERROR("Failure searching book"),
  REGISTER_USER_ERROR("Failure registering new user"),
  TIMESHEET_ENTRY_ERROR("Failure register timesheet entry");

  private String property;

  ExceptionsMessages(String property) {
    this.property = property;
  }

  public String getProperty() {
    return property;
  }
}