package ua.com.company.store.controller.impl.redirection;

import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 13.12.2017.
 */
public class AdminPage implements CommandTypical {
    private UserService userService;

    public AdminPage(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        if (req.getAttribute("user") != null) {
            user = (User) req.getAttribute("user");
        } else {
            user = (User) req.getSession().getAttribute("user");
        }
        if (req.getSession() == null || req.getSession().getAttribute("user") == null || !user.isRole()) {
          return "/view/accessErrorPage.jsp";
        }
        req.setAttribute("listOfUsers", userService.getAllUsers());
        return "/view/adminPage.jsp";
    }
}
