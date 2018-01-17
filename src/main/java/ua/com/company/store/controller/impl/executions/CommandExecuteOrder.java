package ua.com.company.store.controller.impl.executions;

import org.apache.log4j.Logger;
import ua.com.company.store.constants.Redirection;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.entity.Order;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.OrderService;
import ua.com.company.store.service.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Владислав on 31.12.2017.
 */
public class CommandExecuteOrder implements CommandTypical {
    private OrderService orderService;
    private UserService userService;
    private Logger logger = Logger.getRootLogger();


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
            return Redirection.ACCESS_ERROR_PAGE;
        }

        String userNameByOrder = req.getParameter("userName");
        User user = userService.getUserByNickName(userNameByOrder);
        Order order = orderService.getByParameter(user);


        return Redirection.ADMIN_PAGE;
    }

    public void sendMessage(String to, String sub, String msg,final String user,final String pass) throws IOException, MessagingException {
        final Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");



        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,pass);
            }
        });
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(user));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setSubject(sub);
        mimeMessage.setText(msg);

        Transport.send(mimeMessage);
    }

}
