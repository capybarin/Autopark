package com.autopark.app.servlets;

import com.autopark.app.entities.User;
import com.autopark.app.database.DatabaseWorker;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Сервлет вывода всех пользователей
 * @author Bezdushnyi Vladyslav
 */

public class ListServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ListServlet.class);

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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
