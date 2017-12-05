package ua.com.company.store.controller.impl;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.validation.ValidatorAbstract;
import ua.com.company.store.validation.registration.LoginValidator;
import ua.com.company.store.validation.registration.PasswordValidator;
import ua.com.company.store.validation.signup.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Владислав on 29.11.2017.
 */
public class SignUpFormExecution implements CommandTypical{
    private AbstractDao abstractDao;
    private Logger logger = Logger.getRootLogger();

    public SignUpFormExecution(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");
        User user = new User(0,login,pass,email,false,false);
        if (doValidationInputs(login,pass,email)){
            logger.info("Successful validated inputs signUp");
          doSignUp(user,req,resp);
        }else {
            logger.info("Validated inputs unsuccessful " + login + " -- " + pass);
            req.setAttribute("error", " NUll inputs!!!");
            req.getRequestDispatcher("view/ErrorInput.jsp").forward(req,resp);
        }
    }
    private boolean doValidationInputs(String login, String pass, String email){
        ValidatorAbstract validatorAbstractLogin = new LoginValidator();
        ValidatorAbstract validatorAbstractPassword = new PasswordValidator();
        ValidatorAbstract validatorAbstractEmail = new EmailValidator();
        validatorAbstractLogin.setNextValidator(validatorAbstractPassword);
        validatorAbstractPassword.setNextValidator(validatorAbstractEmail);
        return validatorAbstractLogin.validate(login,pass,email);
    }
    private void doSignUp(User user,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isExistUser = true;
        List<User> list = abstractDao.getAll();
        for (User userFromDB: list){
           if (userFromDB.getEmail().equals(user.getEmail())){
               isExistUser = false;
            }
        }
        if (isExistUser) {
            logger.info("Successful added user " + user.getNickname());
            abstractDao.insert(user);
             req.getRequestDispatcher("view/SuccessfulRegistrationByUser.jsp").forward(req,resp);
        }else {
            StringBuilder sb = new StringBuilder();
            sb.append("<h3>User with email " );
            sb.append(user.getEmail() );
            sb.append(" is already exist.</h3>" );
            logger.info("User with email " +user.getEmail() + " is already exist.");
            req.setAttribute("existUser",sb);
            req.getRequestDispatcher("index.jsp").include(req,resp);
        }
        }

}
