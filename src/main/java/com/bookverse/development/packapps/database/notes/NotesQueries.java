package com.bookverse.development.packapps.database.notes;

import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;

public class NotesQueries {

  public static String insertNewNote() {
    return "INSERT INTO " + Format.tableName(DatabaseConstants.NOTES)
           + " (Nickname,Scale,Percent,Note,State,Date) VALUES (?,?,?,?,?,?)";
  }
}
