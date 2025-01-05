package com.bookverse.packapps.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.SneakyThrows;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bookverse.packapps.utils.constants.DatabaseConstants;
import com.bookverse.packapps.automation.utils.SerenityConf;

public final class DatabaseConnection {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);
  private static Connection connection;

  private DatabaseConnection() {
  }

  @SneakyThrows
  public static Connection getConnection() {

    if (connection != null) {
      return connection;
    }

    try (BasicDataSource basicDataSource = new BasicDataSource()) {
      basicDataSource.setDriverClassName(DatabaseConstants.JDBC_DRIVER);
      basicDataSource.setUsername(SerenityConf.getDatabaseConfig("username"));
      basicDataSource.setPassword(SerenityConf.getDatabaseConfig("password"));
      basicDataSource.setUrl(DatabaseConstants.JDBC_URL);
      basicDataSource.setMaxTotal(250);
      basicDataSource.setMaxIdle(100);
      basicDataSource.setMinIdle(50);

      connection = basicDataSource.getConnection();
    } catch (SQLException e) {
      throw new SQLException("Failed to connect to database ", e);
    }

    return connection;
  }

  public static void close(ResultSet rs) {

    try {
      rs.close();
    } catch (SQLException e) {
      LOGGER.error("Failed close of ResultSet ", e);
    }
  }

  public static void close(PreparedStatement stmt) {

    try {
      stmt.close();
    } catch (SQLException e) {
      LOGGER.error("Failed close of PreparedStatement ", e);
    }
  }

  public static void close(Connection conn) {

    try {
      conn.close();
    } catch (SQLException e) {
      LOGGER.error("Failed close of Connection ", e);
    }
  }
}