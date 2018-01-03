package ua.com.company.store.controller.impl.executions;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.entity.Order;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.OrderService;
import ua.com.company.store.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 31.12.2017.
 */
public class CommandExecuteOrder implements CommandTypical {
    private OrderService orderService;
    private UserService userService;
    private Logger logger = Logger.getRootLogger();


    private String host;
    private String port;
    private String user;
    private String pass;

    public CommandExecuteOrder(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFormSession;
        if (req.getAttribute("user") != null) {
            userFormSession = (User) req.getAttribute("user");
        } else {
            userFormSession = (User) req.getSession().getAttribute("user");
        }
        if (req.getSession() == null || req.getSession().getAttribute("user") == null || !userFormSession.isRole()) {
            return "/view/accessErrorPage.jsp";
        }

        String userNameByOrder = req.getParameter("userName");
        User user = userService.getUserByNickName(userNameByOrder);
        Order order = orderService.getByParameter(user);

        String recipient = "vd1321@mail.ru";
        String subject = "Test";
        String content = "Tested message";

     //   sendingMessageForConfirmOrder(req, recipient, subject, content);
        orderService.deleteOrder(order);
        return "/view/adminPage.jsp";
    }

}
