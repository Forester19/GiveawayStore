package ua.com.company.store.controller.impl.executions;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.entity.Order;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.OrderService;
import ua.com.company.store.service.UserService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
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
            return "/view/accessErrorPage.jsp";
        }

        String userNameByOrder = req.getParameter("userName");
        User user = userService.getUserByNickName(userNameByOrder);
        Order order = orderService.getByParameter(user);
        sendSessageConfirmOrder();

    //    orderService.deleteOrder(order);
        return "/view/adminPage.jsp";
    }
    private void sendSessageConfirmOrder(){
        String to = "vd123@@mail.com";
        String from = "vdvoreckij4@gmail.com";
        String host = "smtp.gmail.com";
        String password = "Forester18";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject("Testing message");
            mimeMessage.setText("Testing test");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);

            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
      logger.info("Successfully sent message to " + to);
        } catch (MessagingException e) {
            logger.info("Fail sending");
            e.printStackTrace();

        }

    }

}
