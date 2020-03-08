package com.bookverse.development.packapps.utils;

import static com.bookverse.development.packapps.utils.AppsConstants.DICES;
import static com.bookverse.development.packapps.utils.AppsConstants.GUESS_NUMBER;
import static com.bookverse.development.packapps.utils.AppsConstants.HANGMAN;

import com.bookverse.development.packapps.core.Resources;

public class Querys {

  private static Resources resources = new Resources();

  public static String getDataByID(String table){
    return "SELECT * FROM "+table+" WHERE ID ='" + resources.cr.ingreseNumero("Enter a ID", 6) + "'";
  }

  public static String getDataByNickname(String table){
    return "SELECT * FROM "+table+" WHERE Nickname ='" + resources.cr.ingreseNickname("Enter a Nickname", 20) + "'";
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
    return "INSERT INTO "+Format.tableName(HANGMAN)+" (Nickname,Mistakes,State, Category, Date) VALUES (?,?,?,?,?)";
  }

  public static String insertDices(){
    return "INSERT INTO "+Format.tableName(DICES)+" (Nickname,Winner,Round,Date) VALUES (?,?,?,?)";
  }
}