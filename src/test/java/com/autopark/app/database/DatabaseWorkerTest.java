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
}