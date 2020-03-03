package com.bookverse.development.packapps.models;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceService {

    private static BasicDataSource basicDataSource;

    /* LOCAL */
    private static final String user = "root";
    private static final String pass = "";
    private static final String database = "packapps";
    private static final String hostname = "localhost";

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String port = "3306";
    private static final String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;

    public DataSourceService() {

        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(pass);
        basicDataSource.setUrl(url);
        basicDataSource.setMaxTotal(250);
        basicDataSource.setMaxIdle(100);
        basicDataSource.setMinIdle(50);

        // Opcional. Sentencia SQL que le puede servir a BasicDataSource
        // para comprobar que la conexion es correcta.
        // basicDataSource.setValidationQuery("select *");
    }

    public BasicDataSource getDataSource() {
        return basicDataSource;
    }
}