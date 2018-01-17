package ua.com.company.store.controller.impl.executions;

import org.apache.log4j.Logger;
import ua.com.company.store.constants.Redirection;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 14.12.2017.
 */
public class CommandDeleteUserExecution implements CommandTypical{
    private UserService userService;
private Logger logger  = Logger.getRootLogger();

    public CommandDeleteUserExecution(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFormSession;
        if (req.getAttribute("user") != null) {
            userFormSession= (User) req.getAttribute("user");
        } else {
            userFormSession= (User) req.getSession().getAttribute("user");
        }
        if (req.getSession() == null || req.getSession().getAttribute("user") == null || !userFormSession.isRole()) {
            return Redirection.ACCESS_ERROR_PAGE;
        }

    String id = req.getParameter("id");
    User user = userService.getById(Integer.parseInt(id));

    userService.deleteUser(user);
    logger.info("Successful deleted user " + user.toString());
        req.setAttribute("listOfUsers", userService.getAllUsers());
    return Redirection.ADMIN_PAGE;
    }
}
