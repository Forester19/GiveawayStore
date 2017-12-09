package ua.com.company.store.controller.impl;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.UserService;
import ua.com.company.store.validation.ValidatorAbstract;
import ua.com.company.store.validation.signup.LoginValidator;
import ua.com.company.store.validation.signup.PasswordValidator;
import ua.com.company.store.validation.signup.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Владислав on 29.11.2017.
 */
public class SignUpFormExecution implements CommandTypical{
    private Logger logger = Logger.getRootLogger();
    private HttpSession session;
    private UserService userService;

    public SignUpFormExecution(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");

        session = req.getSession(true);

        User user = new User(0,login,pass,email,false,false);

        if (doValidationInputs(login,pass,email)){
            logger.info("Successful validated inputs signUp");
           if (userService.validationUserOnBeforeExist(user)){
               logger.info("Validation on existing user is fallen");
               req.setAttribute("userEmail", user.getEmail());
               return "/view/existUserError.jsp";
           }
           else {
                userService.addUser(user);
               return "/main.jsp";
           }
        }else {
            logger.info("Validated inputs unsuccessful " + login + " -- " + pass);
            req.setAttribute("error", " NUll inputs!!!");
          return "/view/someErrorsByInputs.jsp";
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


}
