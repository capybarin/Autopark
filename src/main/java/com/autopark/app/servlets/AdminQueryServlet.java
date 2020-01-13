package com.autopark.app.servlets;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.Work;
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

public class AdminQueryServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AddServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/adminQuery.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicConfigurator.configure();
        req.setCharacterEncoding("UTF-8");
        String driver = req.getParameter("driver");
        String route = req.getParameter("route");
        String bus = req.getParameter("bus");

        int driverId = Integer.parseInt(driver);
        int routeId = Integer.parseInt(route);
        int busId = Integer.parseInt(bus);

        try {
            DatabaseWorker databaseWorker = DatabaseWorker.getInstance();
            Work work = new Work(driverId,routeId,busId,"N");
            databaseWorker.addWork(work); //Уже работает, но почему то выполняется 2 раза. Возможно проблема в написаном фронт енде
            List<Work> works = databaseWorker.getAllWork();
            req.setAttribute("workList", works);
        } catch (SQLException e) {
            log.error(e);
        }

        doGet(req, resp);
    }
}
