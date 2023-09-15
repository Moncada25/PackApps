package com.bookverse.development.packapps.apps.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.SneakyThrows;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants;

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
      basicDataSource.setDriverClassName(DatabaseConstants.DRIVER_DB);
      basicDataSource.setUsername(DatabaseConstants.USER_DB);
      basicDataSource.setPassword(DatabaseConstants.PASSWORD_DB);
      basicDataSource.setUrl(DatabaseConstants.URL_DRIVER_DB);
      basicDataSource.setMaxTotal(250);
      basicDataSource.setMaxIdle(100);
      basicDataSource.setMinIdle(50);

      connection = basicDataSource.getConnection();
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