package ua.com.company.store.controller.impl;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.dao.daoAbstract.GenericDAO;
import ua.com.company.store.model.dao.factory.FactoryDAO;
import ua.com.company.store.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Владислав on 22.11.2017.
 */
public class LoginFormExecution implements CommandTypical{
    private Logger logger = Logger.getRootLogger();
    private GenericDAO genericDAO;

    public LoginFormExecution(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        List list = genericDAO.getAll();
        resp.getWriter().write(login + " "  + pass);
        req.getRequestDispatcher("view/jspFiles/SuccessfulLoginPage.jsp").forward(req,resp);


        logger.info(list.size());
    }
}
