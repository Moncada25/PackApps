package com.bookverse.development.packapps.apps.utils.constants;

import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.BOOKS;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.DICES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.FEEDBACK;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.HANGMAN;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.INVENTORY;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.LOANS;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.NOTES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.PURCHASES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.PUZZLE;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.SALES;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.USERS;

import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Queries {

  @NotNull
  public static String getDataByID(String table) {
    return "SELECT * FROM " + Format.tableName(table) + " WHERE ID ='" + Alerts
        .inputNumber("Enter a ID", 6) + "'";
  }

  @NotNull
  public static String getDataByNickname(String table) {
    return "SELECT * FROM " + Format.tableName(table) + " WHERE Nickname ='" + Alerts
        .inputText("Enter a Nickname", 20) + "'";
  }

  @NotNull
  @Contract(pure = true)
  public static String getAllData(String table) {
    return "SELECT * FROM " + Format.tableName(table);
  }

  @NotNull
  @Contract(pure = true)
  public static String updateNickname(String nickname, String ID, String table) {
    return "UPDATE " + Format.tableName(table) + " SET Nickname='" + nickname + "' WHERE ID='" + ID
        + "'";
  }

  @NotNull
  @Contract(pure = true)
  public static String deleteDataByID(String id, String table) {
    return "DELETE FROM " + Format.tableName(table) + " WHERE ID ='" + id + "'";
  }

  @NotNull
  public static String insertGuessNumber() {
    return "INSERT INTO " + Format.tableName(GUESS_NUMBER)
        + " (Nickname,Limitation,Level,Date) VALUES (?,?,?,?)";
  }

  @NotNull
  public static String insertHangman() {
    return "INSERT INTO " + Format.tableName(HANGMAN)
        + " (Nickname,Mistakes,State,Category,Date) VALUES (?,?,?,?,?)";
  }

  @NotNull
  public static String insertDices() {
    return "INSERT INTO " + Format.tableName(DICES)
        + " (Nickname,Winner,Round,Date) VALUES (?,?,?,?)";
  }

  @NotNull
  public static String insertPuzzle() {
    return "INSERT INTO " + Format.tableName(PUZZLE)
        + " (Nickname,State,Time,Attempts,Date) VALUES (?,?,?,?,?)";
  }

  @NotNull
  public static String insertNote() {
    return "INSERT INTO " + Format.tableName(NOTES)
        + " (Nickname,Scale,Percent,Note,State,Date) VALUES (?,?,?,?,?,?)";
  }

  @NotNull
  public static String insertFeedback() {
    return "INSERT INTO " + Format.tableName(FEEDBACK) + " (Username,Message,Date) VALUES (?,?,?)";
  }

  @NotNull
  public static String insertInventory() {
    return "INSERT INTO " + Format.tableName(INVENTORY)
        + " (ID,State,Price,Quantity) VALUES (?,?,?,?)";
  }

  @NotNull
  public static String insertRecord() {
    return "INSERT INTO " + Format.tableName(CASH_REGISTER)
        + " (User,SoldProducts,TotalSales,PurchasedProducts,TotalPurchases,TotalLoans) VALUES (?,?,?,?,?,?)";
  }

  @NotNull
  public static String insertLoan() {
    return "INSERT INTO " + Format.tableName(LOANS)
        + " (User,Name,Document,Reference,Phone,TimeLimit,Value) VALUES (?,?,?,?,?,?,?)";
  }

  @NotNull
  public static String insertUser() {
    return "INSERT INTO " + Format.tableName(USERS) + " (Username,Password,Status) VALUES (?,?,?)";
  }

  @NotNull
  public static String insertPurchase() {
    return "INSERT INTO " + Format.tableName(PURCHASES)
        + " (IdProduct,User,Document,Phone,Date,Units,Total) VALUES (?,?,?,?,?,?,?)";
  }

  @NotNull
  public static String insertSale() {
    return "INSERT INTO " + Format.tableName(SALES)
        + " (IdProduct,User,Document,Phone,Date,Units,Total) VALUES (?,?,?,?,?,?,?)";
  }

  public static String updateCashRegisterSales(String user, int soldProducts, double totalSales) {
    return "UPDATE " + Format.tableName(CASH_REGISTER) + " SET SoldProducts ='" + soldProducts
        + "', TotalSales='" + totalSales + "' WHERE User='" + user + "'";
  }

  public static String updateInventory(String reference, int units) {
    return "UPDATE " + Format.tableName(INVENTORY) + " SET Quantity='" + units + "' WHERE ID='"
        + reference + "'";
  }

  public static String getDataProduct(String reference, String table) {
    return "SELECT * FROM " + Format.tableName(table) + " WHERE ID ='" + reference + "'";
  }

  public static String updateCashRegisterPurchases(String user, int purchasedProducts,
      double totalPurchases) {
    return "UPDATE " + Format.tableName(CASH_REGISTER) + " SET PurchasedProducts ='"
        + purchasedProducts + "', TotalPurchases='" + totalPurchases + "' WHERE User='" + user
        + "'";
  }

  public static String updateCashRegisterLoan(String user, double totalLoan) {
    return "UPDATE " + Format.tableName(CASH_REGISTER) + " SET TotalLoans='" + totalLoan
        + "' WHERE User='" + user + "'";
  }

  public static String recordLogin(String status, String user) {
    return "UPDATE " + Format.tableName(USERS) + " SET Status='" + status + "' WHERE Username='"
        + user + "'";
  }

  public static String searchDataUserInCashRegister(String user) {
    return "SELECT * FROM " + Format.tableName(CASH_REGISTER) + " WHERE User='" + user + "'";
  }

  public static String searchUser(String user) {
    return "SELECT * FROM " + Format.tableName(USERS) + " WHERE Username ='" + user + "'";
  }

  public static String searchUserRegister(String user, String password) {
    return "SELECT * FROM " + Format.tableName(USERS) + " WHERE Username='" + user
        + "' AND Password='" + password + "'";
  }

  public static String updatePassword(String user, String newPassword) {
    return "UPDATE " + Format.tableName(USERS) + " SET Password='" + newPassword
        + "' WHERE Username='" + user + "'";
  }

  public static String updateUsername(String user, String newUsername) {
    return "UPDATE " + Format.tableName(USERS) + " SET Username='" + newUsername
        + "' WHERE Username='" + user + "'";
  }

  @NotNull
  public static String getTitleBooks() {
    return "SELECT LibroTitulo FROM " + Format.tableName(BOOKS);
  }
}