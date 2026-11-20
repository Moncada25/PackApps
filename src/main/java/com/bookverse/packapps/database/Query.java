package com.bookverse.packapps.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public record Query(String sql, Object... values) {
  public PreparedStatement prepare(Connection connection) throws SQLException {
    var statement = connection.prepareStatement(sql);
    for (int i = 0; i < values.length; i++) statement.setObject(i + 1, values[i]);
    return statement;
  }
}
