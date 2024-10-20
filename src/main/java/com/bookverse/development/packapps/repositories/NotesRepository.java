package com.bookverse.development.packapps.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bookverse.development.packapps.database.DatabaseConnection;
import com.bookverse.development.packapps.utils.constants.Queries;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.other.GeneralUtils;
import com.bookverse.development.packapps.utils.ui.Alerts;

public final class NotesRepository {

  public static void insert(
      String state, String scale, String name, double totalPercentage, double totalNote) {

    if (GeneralUtils.verifyConnection("Data don't saved", true) && Alerts.saveGame()) {

      try {

        PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(Queries.insertNote());

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, scale);
        preparedStatement.setInt(3, Integer.parseInt(String.format("%.0f", totalPercentage)));
        preparedStatement.setString(4, String.format("%.2f", totalNote));
        preparedStatement.setString(5, state);
        preparedStatement.setString(6, Format.getDate());
        preparedStatement.execute();

      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private NotesRepository() {
  }
}
