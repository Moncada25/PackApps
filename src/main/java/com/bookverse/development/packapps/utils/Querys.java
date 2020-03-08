package com.bookverse.development.packapps.utils;

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
}
