package ua.com.company.store.controller.impl;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;

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
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userID = null;
        Date createTime = null;
        // Get last access time of this web page.
        Date lastAccessTime = null;

        if (session.getAttribute("userID") == null || session.getAttribute("adminID") == null){
            PrintWriter printWriter = resp.getWriter();
            printWriter.print("Session is null");
            return;
        }

        userID = session.getAttribute("userID").toString();
        createTime = new Date(session.getCreationTime());
        lastAccessTime =
                new Date(session.getLastAccessedTime());
        session.invalidate();
        logger.info("Removed session with ID " + userID + "\n" + "Time online " + (lastAccessTime.getMinutes()-createTime.getMinutes()));
    }
}
