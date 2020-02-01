package com.autopark.app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.Work;
import com.autopark.app.misc.UserQueryOutputHelp;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Сервлет подтвержедния водителем заказа
 * @author Bezdushnyi Vladyslav
 */

public class DriverAcceptServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DriverAcceptServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Вывод всех не подтвержденных заказов данного водителя
        HttpSession session = req.getSession();
        String tmpCurrOnlineId = String.valueOf(session.getAttribute("currOnline"));//Получение из сесси ID залогиненого водителя
        int currOnlineId = Integer.parseInt(tmpCurrOnlineId);
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
            databaseWorker.updateUserToBusy(currOnlineId);
        } catch (SQLException e) {
            log.error(e);
        }
        List<Work> works = null;
        try {
            works = databaseWorker.getAllWork();
        } catch (SQLException e) {
            log.error(e);
        }
        //Получение всех не подтвержденных заказов связанных с данным водителем
        List<Work> currentIdWork = new ArrayList<>();
        for (Work work: works) {
            if(work.getUserId() == currOnlineId && work.getAccepted().equals("N"))
                currentIdWork.add(work);
        }
        UserQueryOutputHelp userQueryOutputHelp = null;
        List<UserQueryOutputHelp> userQueryOutputHelpList = new ArrayList<>();
        //Цикл замены ID полученых из таблицы БД данными
        for (Work work:currentIdWork) {
            userQueryOutputHelp = new UserQueryOutputHelp(work.getId(), databaseWorker.getRouteNameById(work.getRouteId()),
                    databaseWorker.getBusNameById(work.getBusId()), work.getAccepted());
            userQueryOutputHelpList.add(userQueryOutputHelp);
        }
        req.setAttribute("workList", userQueryOutputHelpList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/userQuery.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Получение ID заказа и смена его статуса
        BasicConfigurator.configure();
        String workId = req.getParameter("id");
        log.info(workId);
        int id;
        try{
            id = Integer.parseInt(workId);
        }catch (java.lang.Exception e){
            id = 0;
        }
        try {
            DatabaseWorker databaseWorker = DatabaseWorker.getInstance();
            databaseWorker.acceptWork(id);
            List<Work> work = databaseWorker.getAllWork();
            for (Work work1:work) {
                if(work1.getId() == id)
                    databaseWorker.updateBusToBusy(work1.getBusId());
            }
            UserQueryOutputHelp userQueryOutputHelp = null;
            List<UserQueryOutputHelp> userQueryOutputHelpList = new ArrayList<>();
            //Цикл замены ID полученых из таблицы БД данными
            for (Work works:work) {
                userQueryOutputHelp = new UserQueryOutputHelp(works.getId(), databaseWorker.getRouteNameById(works.getRouteId()),
                        databaseWorker.getBusNameById(works.getBusId()), works.getAccepted());
                userQueryOutputHelpList.add(userQueryOutputHelp);
            }
            req.setAttribute("workList", userQueryOutputHelpList);
        } catch (SQLException e) {
            log.error(e);
        }
        doGet(req, resp);
    }
}
