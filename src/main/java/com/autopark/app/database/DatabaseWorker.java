package com.autopark.app.database;

import com.autopark.app.entities.Bus;
import com.autopark.app.entities.Route;
import com.autopark.app.entities.User;
import com.autopark.app.entities.Work;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Singleton класс для работы с БД
 * @author Bezdushnyi Vladyslav
 */

public class DatabaseWorker {
    private static DatabaseWorker instance;
    private static final Logger log = Logger.getLogger(DatabaseWorker.class);
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/motorpoll?serverTimezone=UTC";
    private String username = "root";
    private String password = "qwerty";


    /**Метод для подключения к базе данных
     * @throws SQLException
     */
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

    /**
     * Метод для получения экземпляра класса
     * @return class instance
     * @throws SQLException
     */
    public static DatabaseWorker getInstance() throws SQLException {
        if(instance == null){
            instance = new DatabaseWorker();
        } else if(instance.getConnection().isClosed()){
            instance = new DatabaseWorker();
        }
        return instance;
    }


    /**
     * Получение списка юзеров
     * @return ArrayList<User>
     * @throws SQLException
     */
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

    /**
     * Метод для добавления юзера в БД;
     * Используется при регистрации
     * @param user
     */
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

    /**
     * Метод который меняет статус водителя с "free" на "busy"
     * Используется при подтверждении водителем заказа
     * @param id
     */
    public void updateUserToBusy(int id){
        String sql = "UPDATE users SET Activity='busy' WHERE idUsers=" + id;
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    /**
     * Метод для получения всех маршрутов
     * @return ArrayList<Route>
     * @throws SQLException
     */
    public ArrayList<Route> getRouteList() throws SQLException {
        log.info("Retrieving route list");
        ArrayList<Route> routeList = new ArrayList<>();
        ResultSet routes = connection.createStatement().executeQuery("SELECT * FROM route");
        while (routes.next()){
            routeList.add(new Route(routes.getInt(1),routes.getString(2)));
        }
        return routeList;
    }

    //Не ипользется
    /*(public void addRoute(Route route){
        log.info("Creating a route");
        String sql = "INSERT INTO route (Name) values ('"+route.getName()+"')";
        try {
            connection.createStatement().execute(sql);
        }catch (SQLException e){
            log.error(e);
        }
    }*/

    /**
     * Метод для получения всех автобусов
     * @return ArrayList<Bus>
     * @throws SQLException
     */
    public ArrayList<Bus> getBusList() throws SQLException{
        log.info("Retrieving bus list");
        ArrayList<Bus> busList = new ArrayList<>();
        ResultSet buses = connection.createStatement().executeQuery("SELECT  * FROM bus");
        while (buses.next()){
            busList.add(new Bus(buses.getInt(1),buses.getString(2), buses.getString(3)));
        }
        return busList;
    }

    //Не используется
    /*public void addBus(Bus bus){
        log.info("Creating a bus");
        String sql = "INSERT INTO bus (BusName, Activity) VALUES ('"+bus.getName()+"','"+bus.getActivity()+"')";
        try{
            connection.createStatement().executeQuery(sql);
        }catch (SQLException e){
            log.error(e);
        }
    }*/

    /**
     * Метод обновляет статус атобуса с "free" на "busy"
     * Используется при подтверждении водителем заказа
     * @param id
     */
    public void updateBusToBusy(int id){
        String sql = "UPDATE bus SET Activity='busy' WHERE idBus=" + id;
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            log.error(e);
        }
    }


    /**
     * Метод для создания нового заказа
     * @param work
     */
    public void addWork(Work work){
        log.info("Creating some work");
        String sql = "INSERT INTO work (Users_id, Route_id, Bus_id, Accepted) " +
                "VALUES ('" + work.getUserId() + "','" + work.getRouteId() + "','" + work.getBusId() + "','" + work.getAccepted() + "')";
        try{
            log.info(sql);
            connection.createStatement().executeUpdate(sql);
        }catch (SQLException e){
            log.error(e);
        }
    }

    /**
     * Метод для получения всех существующих заказов
     * @return ArrayList<Work>
     * @throws SQLException
     */
    public ArrayList<Work> getAllWork() throws SQLException{
        log.info("Retrieving work list");
        ArrayList<Work> workList = new ArrayList<>();
        ResultSet works = connection.createStatement().executeQuery("SELECT * FROM work");
        while (works.next()){
            workList.add(new Work(works.getInt(1),works.getInt(2),works.getInt(3),works.getInt(4),works.getString(5)));
        }
        return workList;
    }

    /**
     * Метод используется для смены статуса подтверждения заказа с "N" (No) на "Y" (Yes)
     * @param workId
     */
    public void acceptWork(int workId){
        String sql = "UPDATE work SET Accepted='Y' WHERE idWork = " +workId;
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            log.error(e);
        }
    }


    /**
     * Возвращает из БД имя водителя по его ID
     * @param id
     * @return Driver name
     */
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

    /**
     * Возвращает из БД номер автобуса по его ID
     * @param id
     * @return Bus name
     */
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

    /**
     * Возвращает из БД название маршрута по его ID
     * @param id
     * @return Route name
     */
    public String getRouteNameById(int id){
        String routeName = "";
        String sql = "SELECT * FROM route WHERE idRoute = " + id;
        try{
            ResultSet name = connection.createStatement().executeQuery(sql);
            while (name.next()){
                routeName = name.getString("Name");
            }
        } catch (SQLException e){
            log.error(e);
        }
        return routeName;
    }
}
