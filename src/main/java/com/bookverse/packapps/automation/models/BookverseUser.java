package com.bookverse.packapps.automation.models;

public record BookverseUser(
    String name,
    String lastName,
    String phone,
    String occupation,
    String address,
    String username,
    String password,
    String email,
    String gender,
    String book
) {

  public BookverseUser(String name, String password, String book) {
    this(name, "", "", "", "", "", password, "", "", book);
  }
}