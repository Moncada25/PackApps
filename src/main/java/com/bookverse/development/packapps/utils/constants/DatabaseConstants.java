package com.bookverse.development.packapps.utils.constants;

public final class DatabaseConstants {

  public static final String GUESS_NUMBER = "Guess Number";
  public static final String HANGMAN = "Hangman";
  public static final String DICES = "Dices";
  public static final String PUZZLE = "Puzzle";
  public static final String NOTES = "Notes";
  public static final String FEEDBACK = "Feedback";
  public static final String INVENTORY = "Inventory";
  public static final String CASH_REGISTER = "Cash Register";
  public static final String LOANS = "Loans";
  public static final String USERS = "Users";
  public static final String PURCHASES = "Purchases";
  public static final String SALES = "Sales";
  public static final String BOOKS = "Libro";

  public static final String NAME_DB = "packapps";
  public static final String HOSTNAME_DB = "localhost";
  public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  public static final String JDBC_URL = String.format(
      "jdbc:mysql://%s:3306/%s?serverTimezone=UTC", HOSTNAME_DB, NAME_DB
  );

  private DatabaseConstants() {
  }
}