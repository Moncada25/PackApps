package com.bookverse.packapps.database;

import static com.bookverse.packapps.utils.constants.DatabaseConstants.CASH_REGISTER;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.DICES;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.FEEDBACK;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.GUESS_NUMBER;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.HANGMAN;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.INVENTORY;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.LOANS;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.PURCHASES;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.PUZZLE;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.SALES;
import static com.bookverse.packapps.utils.constants.DatabaseConstants.USERS;

import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.ui.Alerts;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Queries {

  @NotNull
  public static Query getDataByID(String table) {
    return new Query(
        "SELECT * FROM " + Format.tableName(table) + " WHERE ID = ?",
        Alerts.inputNumber("Enter a ID", 6));
  }

  @NotNull
  public static Query getDataByNickname(String table) {
    return new Query(
        "SELECT * FROM " + Format.tableName(table) + " WHERE Nickname = ?",
        Alerts.inputText("Enter a Nickname", 20));
  }

  @NotNull
  @Contract(pure = true)
  public static Query getAllData(String table) {
    return new Query("SELECT * FROM " + Format.tableName(table));
  }

  @NotNull
  @Contract(pure = true)
  public static Query updateNickname(String nickname, String ID, String table) {
    return new Query(
        "UPDATE " + Format.tableName(table) + " SET Nickname=? WHERE ID=?", nickname, ID);
  }

  @NotNull
  public static String insertGuessNumber() {
    return "INSERT INTO "
        + Format.tableName(GUESS_NUMBER)
        + " (Nickname,Limitation,Level,Date) VALUES (?,?,?,?)";
  }

  @NotNull
  public static String insertHangman() {
    return "INSERT INTO "
        + Format.tableName(HANGMAN)
        + " (Nickname,Mistakes,State,Category,Date) VALUES (?,?,?,?,?)";
  }

  @NotNull
  public static String insertDices() {
    return "INSERT INTO "
        + Format.tableName(DICES)
        + " (Nickname,Winner,Round,Date) VALUES (?,?,?,?)";
  }

  @NotNull
  public static String insertPuzzle() {
    return "INSERT INTO "
        + Format.tableName(PUZZLE)
        + " (Nickname,State,Time,Attempts,Date) VALUES (?,?,?,?,?)";
  }

  @NotNull
  public static String insertFeedback() {
    return "INSERT INTO " + Format.tableName(FEEDBACK) + " (Username,Message,Date) VALUES (?,?,?)";
  }

  @NotNull
  public static String insertInventory() {
    return "INSERT INTO "
        + Format.tableName(INVENTORY)
        + " (ID,State,Price,Quantity) VALUES (?,?,?,?)";
  }

  @NotNull
  public static String insertRecord() {
    return "INSERT INTO "
        + Format.tableName(CASH_REGISTER)
        + " (User,SoldProducts,TotalSales,PurchasedProducts,TotalPurchases,TotalLoans) VALUES"
        + " (?,?,?,?,?,?)";
  }

  @NotNull
  public static String insertLoan() {
    return "INSERT INTO "
        + Format.tableName(LOANS)
        + " (User,Name,Document,Reference,Phone,TimeLimit,Value) VALUES (?,?,?,?,?,?,?)";
  }

  @NotNull
  public static String insertUser() {
    return "INSERT INTO " + Format.tableName(USERS) + " (Username,Password,Status) VALUES (?,?,?)";
  }

  @NotNull
  public static String insertPurchase() {
    return "INSERT INTO "
        + Format.tableName(PURCHASES)
        + " (IdProduct,User,Document,Phone,Date,Units,Total) VALUES (?,?,?,?,?,?,?)";
  }

  @NotNull
  public static String insertSale() {
    return "INSERT INTO "
        + Format.tableName(SALES)
        + " (IdProduct,User,Document,Phone,Date,Units,Total) VALUES (?,?,?,?,?,?,?)";
  }

  public static Query updateCashRegisterSales(String user, int soldProducts, double totalSales) {
    return new Query(
        "UPDATE cashregister SET SoldProducts=?, TotalSales=? WHERE User=?",
        soldProducts,
        totalSales,
        user);
  }

  public static Query updateInventory(String reference, int units) {
    return new Query("UPDATE inventory SET Quantity=? WHERE ID=?", units, reference);
  }

  public static Query getDataProduct(String reference, String table) {
    return new Query("SELECT * FROM " + Format.tableName(table) + " WHERE ID=?", reference);
  }

  public static Query updateCashRegisterPurchases(
      String user, int purchasedProducts, double totalPurchases) {
    return new Query(
        "UPDATE cashregister SET PurchasedProducts=?, TotalPurchases=? WHERE User=?",
        purchasedProducts,
        totalPurchases,
        user);
  }

  public static Query updateCashRegisterLoan(String user, double totalLoan) {
    return new Query("UPDATE cashregister SET TotalLoans=? WHERE User=?", totalLoan, user);
  }

  public static Query recordLogin(String status, String user) {
    return new Query("UPDATE users SET Status=? WHERE Username=?", status, user);
  }

  public static Query searchDataUserInCashRegister(String user) {
    return new Query("SELECT * FROM cashregister WHERE User=?", user);
  }

  public static Query searchUser(String user) {
    return new Query("SELECT * FROM users WHERE Username=?", user);
  }

  public static Query updatePassword(String user, String newPassword) {
    return new Query("UPDATE users SET Password=? WHERE Username=?", newPassword, user);
  }

  public static Query updateUsername(String user, String newUsername) {
    return new Query("UPDATE users SET Username=? WHERE Username=?", newUsername, user);
  }
}
