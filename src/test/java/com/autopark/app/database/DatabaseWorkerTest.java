package com.autopark.app.database;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseWorkerTest {

    @Test
    public void getDriverNameById() {
        String expected = "Vlad";
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String actual = databaseWorker.getDriverNameById(1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getBusNameById() {
        String expected = "1";
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String actual = databaseWorker.getBusNameById(1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRouteNameById() {
        String expected = "пл. Школьная - ВПЗ";
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String actual = databaseWorker.getRouteNameById(2);
        Assert.assertEquals(expected, actual);
    }
}