package com.autopark.app.servlets;

import com.autopark.app.entities.User;
import com.autopark.app.database.DatabaseWorker;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Сервлет логина
 * @author Bezdushnyi Vladyslav
 */

public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicConfigurator.configure();
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            log.error("Login class error occurred: ", e);
        }
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        boolean loggedIn = false;
        try {
            List<User> users = databaseWorker.getUserList();
            for (User user: users) {
                if (user.getPassword().equals(pass) && user.getName().equals(name)){
                    log.info("Success");
                    if (user.getRole().equals("U")){
                        log.info("Grants user role");
                        req.setAttribute("userName", user.getName());
                        req.setAttribute("role", user.getRole());
                        loggedIn = true;
                        HttpSession session = req.getSession();
                        session.setAttribute("currOnline", user.getId());
                    }
                    if (user.getRole().equals("A")){
                        log.info("Grants admin role");
                        req.setAttribute("userName", user.getName());
                        req.setAttribute("role", user.getRole());
                        loggedIn = true;
                    }
                }
            } if(loggedIn == false) {
                log.info("FAIL");
            }
        } catch (SQLException e) {
            log.error("Login class error occurred: ", e);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);
    }
}
