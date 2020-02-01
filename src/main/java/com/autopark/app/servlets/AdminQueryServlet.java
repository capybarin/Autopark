package com.autopark.app.servlets;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.Work;
import com.autopark.app.misc.AdminQueryOutputHelp;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервлет создание заказа админом
 * @author Bezdushnyi Vladyslav
 */

public class AdminQueryServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AdminQueryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            DatabaseWorker databaseWorker = DatabaseWorker.getInstance();
            List<Work> works = databaseWorker.getAllWork(); //Получение всех заказов
            AdminQueryOutputHelp adminQueryOutputHelp = null;
            List<AdminQueryOutputHelp> adminQueryOutputHelpArrayList = new ArrayList<>();
            //Цикл для обработки заказов, где вместо ID полученых из БД подставляются данные
            for (Work work1: works) {
                adminQueryOutputHelp = new AdminQueryOutputHelp(databaseWorker.getDriverNameById(work1.getId()),
                        databaseWorker.getRouteNameById(work1.getRouteId()),
                        databaseWorker.getBusNameById(work1.getBusId()),
                        work1.getAccepted());
                adminQueryOutputHelpArrayList.add(adminQueryOutputHelp);
            }
            log.info(adminQueryOutputHelpArrayList);
            req.setAttribute("work", adminQueryOutputHelpArrayList);
        } catch (SQLException e) {
            log.error(e);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/adminQuery.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicConfigurator.configure();
        //Создание заказа
        String driver = req.getParameter("driver");
        String route = req.getParameter("route");
        String bus = req.getParameter("bus");

        int driverId;
        int routeId;
        int busId;
        try{
            driverId = Integer.parseInt(driver);
            routeId = Integer.parseInt(route);
            busId = Integer.parseInt(bus);
        }catch (java.lang.Exception e){
            driverId = 0;
            routeId = 0;
            busId = 0;
        }

        try {
            DatabaseWorker databaseWorker = DatabaseWorker.getInstance();
            Work work = new Work(driverId,routeId,busId,"N");
            databaseWorker.addWork(work);
            List<Work> works = databaseWorker.getAllWork();
            AdminQueryOutputHelp adminQueryOutputHelp = null;
            List<AdminQueryOutputHelp> adminQueryOutputHelpArrayList = new ArrayList<>();
            for (Work work1: works) {
                adminQueryOutputHelp = new AdminQueryOutputHelp(databaseWorker.getDriverNameById(work1.getId()),
                        databaseWorker.getRouteNameById(work1.getRouteId()),
                        databaseWorker.getBusNameById(work1.getBusId()),
                        work1.getAccepted());
                adminQueryOutputHelpArrayList.add(adminQueryOutputHelp);
            }
            log.info(adminQueryOutputHelpArrayList);
            req.setAttribute("work", adminQueryOutputHelpArrayList);
        } catch (SQLException e) {
            log.error(e);
        }

        doGet(req, resp);
    }
}
