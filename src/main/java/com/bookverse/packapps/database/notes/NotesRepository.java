package com.bookverse.packapps.database.notes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import lombok.SneakyThrows;
import com.bookverse.packapps.database.DatabaseConnection;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.GeneralUtils;
import com.bookverse.packapps.utils.ui.Alerts;

public final class NotesRepository {

  private NotesRepository() {
  }

  @SneakyThrows
  public static void insert(
      String state,
      String scale,
      String name,
      double totalPercentage,
      double totalNote
  ) {

    if (GeneralUtils.verifyConnection("Data don't saved", true) && Alerts.saveGame()) {

      PreparedStatement preparedStatement = null;
      
      try {

        preparedStatement = DatabaseConnection.getConnection().prepareStatement(
            NotesQueries.insertNewNote()
        );

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, scale);
        preparedStatement.setInt(3, Integer.parseInt(String.format("%.0f", totalPercentage)));
        preparedStatement.setString(4, String.format("%.2f", totalNote));
        preparedStatement.setString(5, state);
        preparedStatement.setString(6, Format.getDate());
        preparedStatement.execute();
      } catch (SQLException e) {
        Alerts.message("Error", e.getMessage());
      } finally {
        assert preparedStatement != null;
        DatabaseConnection.close(preparedStatement);
      }
    }
  }
}
