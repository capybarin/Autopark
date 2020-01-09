package com.autopark.app.model;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    private static JdbcConnector instance;
    private Connection connection;
    private static final Logger log = Logger.getLogger(JdbcConnector.class);
    private String url = "jdbc:mysql://localhost:3306/motorpoll?serverTimezone=UTC";
    private String username = "root";
    private String password = "qwerty";

    private JdbcConnector() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            log.error("Unable to connect to db ", e);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static JdbcConnector getInstance() throws SQLException {
        if(instance == null){
            instance = new JdbcConnector();
        } else if(instance.getConnection().isClosed()){
            instance = new JdbcConnector();
        }
        return instance;
    }
}
