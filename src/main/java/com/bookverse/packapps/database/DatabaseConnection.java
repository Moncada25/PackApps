package com.bookverse.packapps.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lombok.SneakyThrows;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bookverse.packapps.automation.utils.SerenityConf;

public final class DatabaseConnection {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);
  private static BasicDataSource dataSource = null;

  private DatabaseConnection() {
  }

  @SneakyThrows
  public static Connection getConnection() {
    try {
      Connection conn = getDataSource().getConnection();
      if (!conn.isValid(5)) {
        conn.close();
        return getDataSource().getConnection();
      }

      return conn;
    } catch (SQLException e) {
      throw new SQLException("Failed to connect to database: ", e);
    }
  }

  private static BasicDataSource getDataSource() {
    if (dataSource == null) {
      synchronized (DatabaseConnection.class) {
        final String JDBC_URL = String.format(
            "jdbc:mysql://%s:3306/%s?serverTimezone=UTC",
            SerenityConf.getDatabaseConfig("hostname"),
            SerenityConf.getDatabaseConfig("database")
        );

        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername(SerenityConf.getDatabaseConfig("username"));
        dataSource.setPassword(SerenityConf.getDatabaseConfig("password"));
        dataSource.setUrl(JDBC_URL);
        dataSource.setMaxTotal(250);
        dataSource.setMaxIdle(100);
        dataSource.setMinIdle(50);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setRemoveAbandonedOnBorrow(true);
      }
    }
    return dataSource;
  }

  public static void close(PreparedStatement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (SQLException e) {
        LOGGER.error("Failed close of PreparedStatement ", e);
      }
    }
  }
}