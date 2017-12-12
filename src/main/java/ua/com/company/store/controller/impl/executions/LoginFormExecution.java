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
        boolean isValid = false;

        HttpSession session = req.getSession();
        User user = null;

        LoginDto loginDto = new LoginDto(
                req.getParameter("login"), req.getParameter("password"));
        if (doValidationInputs(loginDto)) {
            user = userService.getUserByNickName(loginDto.getLogin());
            if (validationOnUser(user)) {
                if (SessionManager.getSessionManager().isUserLoggedIn(session)) {
                    RedirectionManager.getRediractionManger().redirect(new ServletWrapper(req, resp), "/main.jsp");
                    return RedirectionManager.REDIRECTION;
                }
                session.setAttribute("user", user);
                req.setAttribute("user", user);
                return "/main.jsp";
            }else {
                req.setAttribute("userEmail",user.getEmail());
                return "/view/existUserError.jsp";
            }
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

    private boolean validationOnUser(User user) {
        List<User> userList = userService.getAllUsers();
        for (User user1 : userList) {
            if (user.equals(user1)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
