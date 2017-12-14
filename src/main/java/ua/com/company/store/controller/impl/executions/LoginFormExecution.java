package ua.com.company.store.controller.impl.executions;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.controller.utils.RedirectionManager;
import ua.com.company.store.controller.utils.ServletWrapper;
import ua.com.company.store.controller.utils.SessionManager;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.dto.LoginDto;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.UserService;
import ua.com.company.store.validation.ValidatorAbstract;
import ua.com.company.store.validation.signup.LoginValidator;
import ua.com.company.store.validation.signup.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Владислав on 22.11.2017.
 */
public class LoginFormExecution implements CommandTypical {
    private Logger logger = Logger.getRootLogger();
    private UserService userService;

    public LoginFormExecution(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        User user;

        LoginDto loginDto = getLoginInputs(login, password);

        if (doValidationInputs(loginDto)) {
            user = userService.getUserByNickName(loginDto.getLogin());
            System.out.println(user.toString());

            logger.info("Successful validated --" + user.getNickname());
            if (SessionManager.getSessionManager().isUserLoggedIn(session)) {
                logger.info("redirection because user in session is already exist ");
                RedirectionManager.getRediractionManger().redirect(new ServletWrapper(req, resp), "/main.jsp");
                return RedirectionManager.REDIRECTION;
            }
            session.setAttribute("user", user);
            req.setAttribute("user", user);
            return "/main.jsp";

        } else {
            logger.info("Validated inputs unsuccessful " + loginDto.getLogin() + " -- " + loginDto.getPassword());
            req.setAttribute("error", " NUll inputs!!!");
            return "/view/someErrorsByInputs.jsp";
        }
    }


    private boolean doValidationInputs(LoginDto loginDto) {
        ValidatorAbstract validatorAbstractLogin = new LoginValidator();
        ValidatorAbstract validatorAbstractPassword = new PasswordValidator();
        validatorAbstractLogin.setNextValidator(validatorAbstractPassword);
        return validatorAbstractLogin.validate(loginDto.getLogin(), loginDto.getPassword());
    }

    private LoginDto getLoginInputs(String login, String password) {
        return new LoginDto(login, password);
    }

    private boolean validationOnUser(User user) {
        boolean isUser = false;
        List<User> userList = userService.getAllUsers();
        for (User user1 : userList) {
            if (user.equals(user1)) {
                isUser = true;
            } else {
                isUser = false;
            }
        }
        return isUser;
    }
}
