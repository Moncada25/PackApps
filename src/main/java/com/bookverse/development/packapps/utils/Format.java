package com.bookverse.development.packapps.utils;

public class Format {

  public static String tableName(String table){
    return table.replaceAll("\\s", "").toLowerCase();
  }
}
