package ua.com.company.store.controller.utils;

import org.apache.log4j.Logger;
import ua.com.company.store.model.entity.User;

import javax.servlet.http.HttpSession;

/**
 * Created by Владислав on 09.12.2017.
 */
public class SessionManager {
    private Logger logger = Logger.getRootLogger();

    public SessionManager() {
    }
    private static final class Holder{
       static SessionManager INSTANCE = new SessionManager();
    }
    public static SessionManager getSessionManager(){
        return Holder.INSTANCE;
    }
    public boolean isUserLoggedIn(HttpSession session){
        return session.getAttribute("user") != null;
    }
    public void addUserToSession(HttpSession session, User user){
        logger.info("User has logged in " + user.getNickname());
      session.setAttribute("user",user);
    }
    public User getUserFromSession(HttpSession session){
        return (User)session.getAttribute("user");
    }
    public void invalidateSession(HttpSession session){
        if (session != null && session.getAttribute("user") !=null){
            User user = (User) session.getAttribute("user");
            logger.info("User has logged out " + user.getNickname());
            session.invalidate();
        }
    }
}
