package com.autopark.app.servlets;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.Route;
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
 * Сервлет вывода всех маршрутов
 * @author Bezdushnyi Vladyslav
 */

public class RouteListServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RouteListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseWorker model = null;
        try {
            model = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            log.error("Unable to get instance ", e);
        }
        List<Route> routes = null;
        try {
            routes = model.getRouteList();
        } catch (SQLException e) {
            log.error(e);
        }

        req.setAttribute("routes", routes);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/userRouteList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
