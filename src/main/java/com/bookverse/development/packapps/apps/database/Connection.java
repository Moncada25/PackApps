package com.bookverse.development.packapps.apps.database;

import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.DRIVER_DB;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.PASSWORD_DB;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.URL_DRIVER_DB;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.USER_DB;

import org.apache.commons.dbcp2.BasicDataSource;

public class Connection {

  private static BasicDataSource basicDataSource;

  public Connection() {
    basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName(DRIVER_DB);
    basicDataSource.setUsername(USER_DB);
    basicDataSource.setPassword(PASSWORD_DB);
    basicDataSource.setUrl(URL_DRIVER_DB);
    basicDataSource.setMaxTotal(250);
    basicDataSource.setMaxIdle(100);
    basicDataSource.setMinIdle(50);
  }

  public BasicDataSource getDataSource() {
    return basicDataSource;
  }
}