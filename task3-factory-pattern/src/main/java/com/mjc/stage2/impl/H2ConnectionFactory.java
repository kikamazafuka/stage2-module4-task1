package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {

    @Override
    public Connection createConnection() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = H2ConnectionFactory.class
                    .getClassLoader().getResourceAsStream("h2database.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String driver = properties.getProperty("jdbc_driver");
        String url = properties.getProperty("db_url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

