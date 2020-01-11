package com.autopark.app.servlets;

import com.autopark.app.entities.User;
import com.autopark.app.database.DatabaseWorker;
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

public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Запуск doGet");
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    //TODO: на основе роли отправлять на разные версии одной и той же страницы

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        log.info("Запуск doPost");
        DatabaseWorker databaseWorker = null;
        try {
            databaseWorker = DatabaseWorker.getInstance();
        } catch (SQLException e) {
            log.error("Login class error occurred: ", e);
        }
        log.info("Интстанс готов, беру данные");
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        log.info("Name is "+name+"\nPass is "+pass);
        try {
            List<User> users = databaseWorker.getUserList();
            for (User user: users) {
                log.info("Проход по списку");
                if (user.getPassword().equals(pass) && user.getName().equals(name)){
                    log.info("Логин прошел успешно");
                    if (user.getRole().equals("U")){
                        log.info("Даю роль юзера");
                        req.setAttribute("userName", user.getName());
                        req.setAttribute("role", user.getRole());
                    }
                    if (user.getRole().equals("A")){
                        log.info("Даю роль админа");
                        req.setAttribute("userName", user.getName());
                        req.setAttribute("role", user.getRole());
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Login class error occurred: ", e);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);
    }
}
