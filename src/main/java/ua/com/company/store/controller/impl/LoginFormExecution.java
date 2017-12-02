package ua.com.company.store.controller.impl;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.dao.daoAbstract.GenericDAO;
import ua.com.company.store.model.dao.factory.FactoryDAO;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.validation.ValidatorAbstract;
import ua.com.company.store.validation.registration.LoginValidator;
import ua.com.company.store.validation.registration.PasswordValidator;

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
    private AbstractDao genericDAO;

    public LoginFormExecution(AbstractDao genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isValid = false;

        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        isValid = doValidationInputs(login,pass);

        if (isValid){
            logger.info("Validated inputs successful " + login + " -- " + pass);
            if (validationOnAdmin(login,pass)) {
                logger.info("log in admin with login " + login);
                req.setAttribute("admin",login);
                req.getRequestDispatcher("view/jspFiles/AdminPage.jsp").forward(req, resp);

            }else {
                if (validationOnUser(login,pass)) {
                    logger.info("log in user with login " + login);
                    req.setAttribute("user", login);
                   req.getRequestDispatcher("view/jspFiles/UserPage.jsp").forward(req, resp);
               }else {
                    logger.info("log in unknown information  " + login);
                    resp.getOutputStream().print("Please sign in here before!!!");
                   req.getRequestDispatcher("index.jsp").include(req, resp);

               }
            }
        }else {
            logger.info("Validated inputs unsuccessful " + login + " -- " + pass);
            req.setAttribute("error", " NUll inputs!!!");
            req.getRequestDispatcher("view/jspFiles/ErrorInput.jsp").forward(req,resp);

        }

    }
    private boolean validationOnAdmin(String login, String password){
        List<User> userList = genericDAO.getAll();
        boolean isAdmin = false;
        for (User user: userList){
            if (user.getNickname().equals(login) && user.getPassword().equals(password)
                    && user.isRole()){
            isAdmin = true;
            }
        }
        return isAdmin;
    }
    private boolean validationOnUser(String login, String password){
        List<User> userList = genericDAO.getAll();
        boolean isUser = false;
        for (User user: userList){
            if (user.getNickname().equals(login) && user.getPassword().equals(password)){
                isUser = true;
            }
        }
        return isUser;

    }
    private boolean doValidationInputs(String login, String pass){
        ValidatorAbstract validatorAbstractLogin = new LoginValidator();
        ValidatorAbstract validatorAbstractPassword = new PasswordValidator();
        validatorAbstractLogin.setNextValidator(validatorAbstractPassword);
        return  validatorAbstractLogin.validate(login,pass);
    }
}
