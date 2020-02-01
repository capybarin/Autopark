package com.autopark.app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Сервлет для вывода водителей для админа
 * @author Bezdushnyi Vladyslav
 */

public class AdminDriversListServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AdminDriversListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            log.error("Unable to get instance ", e);
        }
        List<User> users = null;
        try {
            users = databaseWorker.getUserList();
        } catch (SQLException e) {
            log.error("Unable to get user list ", e);
        }


        req.setAttribute("users", users);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/adminDriverList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
