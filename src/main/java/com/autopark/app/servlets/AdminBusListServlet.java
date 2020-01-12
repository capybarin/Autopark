package com.autopark.app.servlets;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.Bus;
import com.autopark.app.entities.User;
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
import java.util.stream.Collectors;

public class AdminBusListServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AdminBusListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicConfigurator.configure();
        req.setCharacterEncoding("UTF-8");
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

        List<String> busNames = buses.stream().map(Bus::getName).collect(Collectors.toList());
        List<String> busActivity = buses.stream().map(Bus::getActivity).collect(Collectors.toList());

        log.info(busNames);
        log.info(busActivity);

        req.setAttribute("busNames", busNames);
        req.setAttribute("busActivity", busActivity);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/adminBusList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
