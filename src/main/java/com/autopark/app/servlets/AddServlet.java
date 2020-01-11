package com.autopark.app.servlets;


import com.autopark.app.entities.User;
import com.autopark.app.database.DatabaseWorker;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AddServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicConfigurator.configure();
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String pass = req.getParameter("pass");
        User user = new User(name, surname,"U","free", pass);
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            log.error("AddServlet error occurred: unable to get instance ", e);
        }
        databaseWorker.addUser(user);

        req.setAttribute("userName", name);
        doGet(req, resp);
    }
}
