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
import java.util.stream.Collectors;

public class RouteListServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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
        List<String> routesNames = routes.stream().map(Route::getName).collect(Collectors.toList());
        req.setAttribute("routeNames", routesNames);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/userRouteList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
