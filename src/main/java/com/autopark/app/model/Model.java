package com.autopark.app.model;

import com.autopark.app.entities.Route;
import com.autopark.app.entities.User;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Model instance;
    private static final Logger log = Logger.getLogger(Model.class);
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/motorpoll?serverTimezone=UTC";
    private String username = "root";
    private String password = "qwerty";


    private Model() throws SQLException {
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

    public static Model getInstance() throws SQLException {
        if(instance == null){
            instance = new Model();
        } else if(instance.getConnection().isClosed()){
            instance = new Model();
        }
        return instance;
    }

    public ArrayList<User> getUserList() throws SQLException {
        log.info("Retrieving user list");
        ArrayList<User> userList = new ArrayList<>();
        ResultSet users = connection.createStatement().executeQuery("SELECT * FROM users");
        while (users.next()){
            userList.add(new User(users.getInt(1),users.getString(2),users.getString(3),
                    users.getString(4), users.getString(5), users.getString(6)));
        }
        return userList;
    }

    public void addUser(User user){
        log.info("Creating a user");
        String sql = "insert into users (Name_, Surname, Role, Activity, Password) " +
                "values ('"+user.getName()+"','"+user.getSurname()+"','"+user.getRole()+"','"+user.getActivity()+"','"+user.getPassword()+"')";
        try {
            connection.createStatement().execute(sql);
        }catch (SQLException e){
            log.error(e);
        }
    }

    public ArrayList<Route> getRouteList() throws SQLException {
        log.info("Retrieving route list");
        ArrayList<Route> routeList = new ArrayList<>();
        ResultSet routes = connection.createStatement().executeQuery("SELECT * FROM route");
        while (routes.next()){
            routeList.add(new Route(routes.getInt(1),routes.getString(2)));
        }
        return routeList;
    }

    public void addRoute(Route route){
        log.info("Creating a route");
        String sql = "INSERT INTO route (Name) values ('"+route.getName()+"')";
        try {
            connection.createStatement().execute(sql);
        }catch (SQLException e){
            log.error(e);
        }
    }

}
