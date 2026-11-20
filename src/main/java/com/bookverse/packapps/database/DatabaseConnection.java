package com.bookverse.packapps.database;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {
  private DatabaseConnection() {}

  public static Path dataDirectory() {
    return Path.of(
        System.getenv()
            .getOrDefault(
                "PACKAPPS_HOME", Path.of(System.getProperty("user.home"), ".packapps").toString()));
  }

  public static Connection getConnection() throws SQLException {
    String url = System.getenv("PACKAPPS_MYSQL_URL");
    if (url != null && !url.isBlank()) {
      if (!url.startsWith("jdbc:mariadb://"))
        throw new SQLException("Expected a jdbc:mariadb:// URL");
      return DriverManager.getConnection(
          url, System.getenv("PACKAPPS_MYSQL_USER"), System.getenv("PACKAPPS_MYSQL_PASSWORD"));
    }
    try {
      Files.createDirectories(dataDirectory());
      var connection =
          DriverManager.getConnection("jdbc:sqlite:" + dataDirectory().resolve("packapps.db"));
      try (var statement = connection.createStatement()) {
        statement.execute("PRAGMA busy_timeout=5000");
        try (var version = statement.executeQuery("PRAGMA user_version")) {
          if (!version.next()) throw new SQLException("Cannot determine database version");
          int schemaVersion = version.getInt(1);
          if (schemaVersion == 1) return connection;
          if (schemaVersion > 1)
            throw new SQLException("This database requires a newer PackApps release");
        }
        connection.setAutoCommit(false);
        try (var schema = DatabaseConnection.class.getResourceAsStream("/data/sqlite.sql")) {
          for (String sql : new String(schema.readAllBytes(), StandardCharsets.UTF_8).split(";")) {
            if (!sql.isBlank()) statement.execute(sql);
          }
        }
        statement.execute("PRAGMA user_version=1");
        connection.commit();
        connection.setAutoCommit(true);
        return connection;
      } catch (Exception e) {
        connection.close();
        throw e;
      }
    } catch (IOException e) {
      throw new SQLException("Cannot open the PackApps data directory", e);
    }
  }
}
