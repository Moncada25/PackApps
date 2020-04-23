package com.bookverse.development.packapps.automation.exceptions;

public class BookNotFound extends AssertionError {

  public BookNotFound(String message, Throwable cause) {
    super(message, cause);
  }
}