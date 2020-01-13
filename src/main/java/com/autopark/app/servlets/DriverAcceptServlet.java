package com.autopark.app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class DriverAcceptServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DriverAcceptServlet.class);
    public int loggedUserId;

    //TODO: делать отображение задачи на основе ид, типа отправлять ид залогиненого челбаса, и смотреть если задача в которой его ид фигугриурет, и если есть то выводить, нет - то нет
    //TODO: походу надо писать файл
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BasicConfigurator.configure();
    }
}
