package com.autopark.app.database;

import com.autopark.app.entities.Bus;
import com.autopark.app.entities.Route;
import com.autopark.app.entities.User;
import com.autopark.app.entities.Work;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseWorker {
    private static DatabaseWorker instance;
    private static final Logger log = Logger.getLogger(DatabaseWorker.class);
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/motorpoll?serverTimezone=UTC";
    private String username = "root";
    private String password = "qwerty";


    //TODO: делать отображение задачи на основе ид, типа отправлять ид залогиненого челбаса, и смотреть если задача в которой его ид фигугриурет, и если есть то выводить, нет - то нет

    private DatabaseWorker() throws SQLException {
        BasicConfigurator.configure();
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

    public static DatabaseWorker getInstance() throws SQLException {
        if(instance == null){
            instance = new DatabaseWorker();
        } else if(instance.getConnection().isClosed()){
            instance = new DatabaseWorker();
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

    public ArrayList<Bus> getBusList() throws SQLException{
        log.info("Retrieving bus list");
        ArrayList<Bus> busList = new ArrayList<>();
        ResultSet buses = connection.createStatement().executeQuery("SELECT  * FROM bus");
        while (buses.next()){
            busList.add(new Bus(buses.getInt(1),buses.getString(2), buses.getString(3)));
        }
        return busList;
    }

    public void addBus(Bus bus){
        log.info("Creating a bus");
        String sql = "INSERT INTO bus (BusName, Activity) VALUES ('"+bus.getName()+"','"+bus.getActivity()+"')";
        try{
            connection.createStatement().executeQuery(sql);
        }catch (SQLException e){
            log.error(e);
        }
    }

    public void makeSomeWork(int userId, int busId, int routeId){
        //INSERT INTO `motorpoll`.`work` (`Users_id`, `Route_id`, `Bus_id`, `Accepted`) VALUES ('1', '1', '1', 'N');
        log.info("Creating some work");
        String sql = "INSERT INTO work (Users_id, Route_id, Bus_id, Accepted) " +
                "VALUES ('" + userId + "','" + busId + "','" + routeId + "','N')";
        try{
            connection.createStatement().executeQuery(sql);
        }catch (SQLException e){
            log.error(e);
        }
    }

    public ArrayList<Work> getAllWork() throws SQLException{
        log.info("Retrieving work list");
        ArrayList<Work> workList = new ArrayList<>();
        ResultSet works = connection.createStatement().executeQuery("SELECT * FROM work");
        while (works.next()){
            workList.add(new Work(works.getInt(1),works.getInt(2),works.getInt(3),works.getInt(4),works.getString(5)));
        }
        return workList;
    }

    //TODO: создать больше гетеров от ид
    public String getDriverNameById(int id){
        String driverName = "";
        String sql = "SELECT * FROM users WHERE idUsers = " + id;
        try{
            ResultSet name = connection.createStatement().executeQuery(sql);
            while (name.next()){
                driverName = name.getString("Name_");
            }
        } catch (SQLException e){
            log.error(e);
        }
        return driverName;
    }

    public String getBusNameById(int id){
        String busName = "";
        String sql = "SELECT * FROM bus WHERE idBus = " + id;
        try{
            ResultSet name = connection.createStatement().executeQuery(sql);
            while (name.next()){
                busName = name.getString("BusName");
            }
        } catch (SQLException e){
            log.error(e);
        }
        return busName;
    }
}
