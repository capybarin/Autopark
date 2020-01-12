package com.autopark.app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.autopark.app.database.DatabaseWorker;
import com.autopark.app.entities.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
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

        List<String> names = null;
        List<String> surNames = null;
        List<String> activity = null;

        for (User user: users) {
            if (user.getRole().equals("U")){
                names.add(user.getName());
                surNames.add(user.getSurname());
                activity.add(user.getActivity());
            }
        }
        log.info(names);
    }
}
