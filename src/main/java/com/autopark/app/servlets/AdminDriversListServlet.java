package com.autopark.app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDriversListServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AdminDriversListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            log.error("Unable to get instance ", e);
        }
        List<User> users = null;
        try {
            users = databaseWorker.getUserList();
        } catch (SQLException e) {
            log.error("Unable to get user list ", e);
        }

        List<String> userNames = users.stream().map(User::getName).collect(Collectors.toList());
        List<String> userSurnames = users.stream().map(User::getSurname).collect(Collectors.toList());
        req.setAttribute("userNames", userNames);
        req.setAttribute("userSurnames", userSurnames);

        List<String> freeNames = new ArrayList<>();
        List<String> freeSurNames = new ArrayList<>();
        List<String> freeActivity = new ArrayList<>();

        for (User user: users) {
            if (user.getRole().equals("U")){
                freeNames.add(user.getName());
                freeSurNames.add(user.getSurname());
                freeActivity.add(user.getActivity());
            }
        }
        req.setAttribute("freeUserNames", freeNames);
        req.setAttribute("freeUserSurnames", freeSurNames);
        req.setAttribute("freeUserActivity", freeActivity);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/adminDriverList.jsp");
        requestDispatcher.forward(req, resp);
    }
}