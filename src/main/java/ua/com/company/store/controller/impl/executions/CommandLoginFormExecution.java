package ua.com.company.store.controller.impl.executions;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.controller.utils.CookiesAction;
import ua.com.company.store.controller.utils.RedirectionManager;
import ua.com.company.store.controller.utils.ServletWrapper;
import ua.com.company.store.controller.utils.SessionManager;
import ua.com.company.store.hashing.PasswordHashing;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.dto.LoginDto;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.UserService;
import ua.com.company.store.validation.ValidatorAbstract;
import ua.com.company.store.validation.login.LoginUpValidator;
import ua.com.company.store.validation.login.PasswordUpValidator;
import ua.com.company.store.validation.signup.LoginValidator;
import ua.com.company.store.validation.signup.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

/**
 * Created by Владислав on 22.11.2017.
 */
public class CommandLoginFormExecution implements CommandTypical {
    private Logger logger = Logger.getRootLogger();
    private UserService userService;
    private PasswordHashing passwordHashing;

    public CommandLoginFormExecution(UserService userService, PasswordHashing passwordHashing) {
        this.userService = userService;
        this.passwordHashing = passwordHashing;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String checkedIds = req.getParameter("checkMeOut");

        HttpSession session = req.getSession(true);
        User user;
        LoginDto loginDto = getLoginInputs(login, password);
        if (doValidationInputs(loginDto)) {
            logger.info("Successful validation inputs");
            if (CookiesAction.getUserFromCookie(req, userService).size() == 0) {
                logger.info("User don't exist in cookie(successful)");
                user = userService.getUserByNickName(loginDto.getLogin());
                if (user == null) {
                    return "/view/noExistUserPage.jsp";
                }


                logger.info("Successful validated --" + user.getNickname());
                if (SessionManager.getSessionManager().isUserLoggedIn(session)) {
                    logger.info("redirection because user in session is already exist ");
                    RedirectionManager.getRediractionManger().redirect(new ServletWrapper(req, resp), "/main.jsp");
                    return RedirectionManager.REDIRECTION;
                }
                if (validationOnUser(loginDto)) {
                    logger.info("Successful validation on registered before user");
                    session.setAttribute("user", user);
                    req.setAttribute("user", user);

                    if (checkedIds != null && checkedIds.equals("OK")) {
                        CookiesAction.setCookieUserInform(resp, user);
                        logger.info("Marked checkbox and added user in cookie successfully");
                    }else {
                        logger.info("Continue execution without adding in cookie ");
                    }
                    return "/main.jsp";
                } else {
                    return "/view/noExistUserPage.jsp";
                }

            } else {
                logger.info("User exist in cookie so do redirect to main page");
                List list = CookiesAction.getUserFromCookie(req, userService);
                User user1 = (User) list.get(0);
                session.setAttribute("user", user1);
                req.setAttribute("user", user1);
                return "/main.jsp";

            }
        }else {
            logger.info("Validated inputs failed " + loginDto.getLogin() + " -- " + loginDto.getPassword());
            req.setAttribute("error", " NUll inputs!!!");
            return "/view/someErrorsByInputs.jsp";

        }
    }


    private boolean doValidationInputs(LoginDto loginDto) {
        ValidatorAbstract validatorAbstractLogin = new LoginUpValidator();
        ValidatorAbstract validatorAbstractPassword = new PasswordUpValidator();
        validatorAbstractLogin.setNextValidator(validatorAbstractPassword);
        return validatorAbstractLogin.validate(loginDto.getLogin(), loginDto.getPassword());
    }

    private LoginDto getLoginInputs(String login, String password) {
        return new LoginDto(login, password);
    }

    private boolean validationOnUser(LoginDto loginDto) {
        for (User user: userService.getAllUsers()){
            try {
                if (Objects.equals(user.getNickname(), loginDto.getLogin())
                        && user.getPassword().equals(passwordHashing.hashingPassword(loginDto.getPassword()))){
                    return true;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
