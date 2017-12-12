package ua.com.company.store.controller.impl.redirection;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Владислав on 06.12.2017.
 */
public class RemoveSession implements CommandTypical {
    private Logger logger = Logger.getRootLogger();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
       User user = null;
        Date createTime = null;
        // Get last access time of this web page.
        Date lastAccessTime = null;

        if (session.equals(null) || session.getAttribute("user").equals(null) ){
            PrintWriter printWriter = resp.getWriter();
            printWriter.print("Session is null");
            return null;
        }

        user = (User) session.getAttribute("user");
        req.setAttribute("user",null);
        createTime = new Date(session.getCreationTime());
        lastAccessTime =
                new Date(session.getLastAccessedTime());
        session.invalidate();
        logger.info("Removed session with ID " + user.getNickname() + "\n" + "Time online " + (lastAccessTime.getMinutes()-createTime.getMinutes()) + " min.");
    return "/main.jsp";
    }
}
