package ua.com.company.store.controller.impl;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 22.11.2017.
 */
public class LoginFormExecution implements CommandTypical{
    private Logger logger = Logger.getRootLogger();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        logger.info(login + " " + pass);
    }
}
