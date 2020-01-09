package com.autopark.app.model;

import com.autopark.app.entities.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instance;
    private static final Logger log = Logger.getLogger(Model.class);
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/motorpoll?serverTimezone=UTC";
    private String username = "root";
    private String password = "qwerty";
    private List<User> userList;


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
        ArrayList<User> userList = new ArrayList<>();
        ResultSet users = connection.createStatement().executeQuery("SELECT * FROM users");
        while (users.next()){
            userList.add(new User(users.getInt(1),users.getString(2),users.getString(3),
                    users.getString(4), users.getString(5), users.getString(6)));
        }
        return userList;
    }

    public void addUser(User user){
        try{
            String sql = "insert into users (Name, Surname, Role, Activity, Password) values ('TestName', 'TestSurname', 'U', 'free', 'qwerty')";
            connection.createStatement().executeQuery(sql);
            //connection.createStatement().executeQuery("INSERT INTO users (Name, Surname, Role, Activity, Password)" +
            //        "VALUES ('"+user.getName()+"','"+user.getSurname()+"','"+user.getRole()+"','"+user.getActivity()+
            //        "','"+user.getPassword()+"');");
        } catch (SQLException e) {
            log.error("Model class occurred: unable to execute insert statement (user table)");
        }
    }

    public void prepareDrivers(){
        userList.clear();
    }

    /*public void addUser(User user){
        model.add(user);
    }

    public List<String> getNames(){
        return model.stream().map(User::getName).collect(Collectors.toList());
    }

    public List<String> getSurnames(){
        return model.stream().map(User::getSurname).collect(Collectors.toList());
    }

    public List<String> getRoles(){
        return model.stream().map(User::getRole).collect(Collectors.toList());
    }

    public List<String> getActivities(){
        return model.stream().map(User::getActivity).collect(Collectors.toList());
    }*/
}
