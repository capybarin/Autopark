package com.autopark.app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class DriverAcceptServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DriverAcceptServlet.class);

    //TODO: делать отображение задачи на основе ид, типа отправлять ид залогиненого челбаса, и смотреть если задача в которой его ид фигугриурет, и если есть то выводить, нет - то нет
    //TODO: походу надо писать файл
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        File tmp = new File(System.getProperty("user.home"), "tmp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(tmp));
        int temp = Integer.parseInt(reader.readLine());
        log.info(temp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BasicConfigurator.configure();
    }
}
