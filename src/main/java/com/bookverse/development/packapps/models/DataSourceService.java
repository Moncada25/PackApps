package com.bookverse.development.packapps.models;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceService {

  private static final String USER = "root";
  private static final String PASS = "";
  private static final String DATABASE = "packapps";
  private static final String HOSTNAME = "localhost";
  private static final String DRIVER = "com.mysql.jdbc.Driver";
  private static final String PORT = "3306";
  private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE;
  private static BasicDataSource basicDataSource;

  public DataSourceService() {
    basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName(DRIVER);
    basicDataSource.setUsername(USER);
    basicDataSource.setPassword(PASS);
    basicDataSource.setUrl(URL);
    basicDataSource.setMaxTotal(250);
    basicDataSource.setMaxIdle(100);
    basicDataSource.setMinIdle(50);
  }

  public BasicDataSource getDataSource() {
    return basicDataSource;
  }
}