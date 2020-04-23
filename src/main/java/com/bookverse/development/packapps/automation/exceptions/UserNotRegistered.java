package com.bookverse.development.packapps.automation.exceptions;

public class UserNotRegistered extends AssertionError {

  public UserNotRegistered(String message, Throwable cause) {
    super(message, cause);
  }
}