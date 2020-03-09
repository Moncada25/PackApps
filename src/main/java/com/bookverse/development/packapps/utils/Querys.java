package com.bookverse.development.packapps.utils;

import static com.bookverse.development.packapps.utils.TableConstants.*;

import com.bookverse.development.packapps.core.Core;

public class Querys {

  public static String getDataByID(String table){
    return "SELECT * FROM "+table+" WHERE ID ='" + Core.enterNumber("Enter a ID", 6) + "'";
  }

  public static String getDataByNickname(String table){
    return "SELECT * FROM "+table+" WHERE Nickname ='" + Core.enterNickname("Enter a Nickname", 20) + "'";
  }

  public static String getAllData(String table){
    return "SELECT * FROM "+table;
  }

  public static String updateNickname(String nickname, String ID, String table){
    return "UPDATE " + table + " SET Nickname='" + nickname + "' WHERE ID='" + ID + "'";
  }

  public static String deleteDataByID(String id, String table){
    return "DELETE FROM " + table + " WHERE ID ='" + id + "'";
  }

  public static String insertGuessNumber(){
    return "INSERT INTO " + Format.tableName(GUESS_NUMBER) + " (Nickname,Limitation,Level,Date) VALUES (?,?,?,?)";
  }

  public static String insertHangman(){
    return "INSERT INTO "+Format.tableName(HANGMAN)+" (Nickname,Mistakes,State,Category,Date) VALUES (?,?,?,?,?)";
  }

  public static String insertDices(){
    return "INSERT INTO "+Format.tableName(DICES)+" (Nickname,Winner,Round,Date) VALUES (?,?,?,?)";
  }

  public static String insertPuzzle(){
    return "INSERT INTO "+Format.tableName(PUZZLE)+" (Nickname,State,Time,Attempts,Date) VALUES (?,?,?,?,?)";
  }

  public static String insertNote(){
    return "INSERT INTO "+Format.tableName(NOTES)+" (Nickname,Scale,Percent,Note,State,Date) VALUES (?,?,?,?,?,?)";
  }

  public static String insertFeedback(){
    return "INSERT INTO "+Format.tableName(FEEDBACK)+" (Username,Message,Date) VALUES (?,?,?)";
  }

  public static String insertInventory(){
    return "INSERT INTO "+Format.tableName(INVENTORY)+" (ID,State,Price,Quantity) VALUES (?,?,?,?)";
  }

  public static String insertRecord(){
    return "INSERT INTO "+Format.tableName(RECORDS)+" (User,SoldProducts,TotalSales,PurchasedProducts,TotalPurchases,TotalLoans) VALUES (?,?,?,?,?,?)";
  }

  public static String insertLoan(){
    return "INSERT INTO "+Format.tableName(LOANS)+" (User,Name,Document,Reference,Phone,TimeLimit,Value) VALUES (?,?,?,?,?,?,?)";
  }

  public static String insertUser(){
    return "INSERT INTO "+Format.tableName(USERS)+" (Username,Password,Status) VALUES (?,?,?)";
  }

  public static String insertPurchase(){
    return "INSERT INTO "+Format.tableName(PURCHASES)+" (IdProduct,User,Document,Phone,Date,Units,Total) VALUES (?,?,?,?,?,?,?)";
  }

  public static String insertSale(){
    return "INSERT INTO "+Format.tableName(SALES)+" (IdProduct,User,Document,Phone,Date,Units,Total) VALUES (?,?,?,?,?,?,?)";
  }
}