package com.autopark.app.servlets;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.Bus;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminBusListServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AdminBusListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicConfigurator.configure();
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            log.error("Unable to get instance ", e);
        }
        List<Bus> buses = null;
        try {
            buses = databaseWorker.getBusList();
        } catch (SQLException e) {
            log.error("Unable to get bus list ", e);
        }


        req.setAttribute("buses", buses);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/adminBusList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
