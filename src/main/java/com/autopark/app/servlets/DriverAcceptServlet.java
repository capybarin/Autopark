package com.autopark.app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.Work;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class DriverAcceptServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DriverAcceptServlet.class);

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        File tmp = new File(System.getProperty("user.home"), "tmp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(tmp));
        int temp = Integer.parseInt(reader.readLine());
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            log.error(e);
        }
        List<Work> works = null;
        try {
            works = databaseWorker.getAllWork();
        } catch (SQLException e) {
            log.error(e);
        }
        List<Work> currentIdWork = new ArrayList<>();
        for (Work work: works) {
            if(work.getUserId() == temp && work.getAccepted().equals("N"))
                currentIdWork.add(work);
        }
        req.setAttribute("workList", currentIdWork);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/userQuery.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BasicConfigurator.configure();
        log.info("Submit button pressed");
        doGet(req, resp);
    }
}
