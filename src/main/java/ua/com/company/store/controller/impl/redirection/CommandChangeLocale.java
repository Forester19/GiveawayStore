package ua.com.company.store.controller.impl.redirection;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.locale.AppLocale;
import ua.com.company.store.locale.MessageLocale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Владислав on 07.12.2017.
 */
public class CommandChangeLocale implements CommandTypical {
    private Logger logger = Logger.getRootLogger();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setLocale(req);
        return "/main.jsp";
    }

    private void setLocale(HttpServletRequest req) {
        String selectedLang = req.getParameter("lang");
        logger.info("Chosen language " + selectedLang);
        Locale locale = AppLocale.forValue(selectedLang);
        req.getSession().setAttribute("locale", locale);
        MessageLocale.setResourceBundleLocale(locale);
    }

    @Override
    public String toString() {
        return "ChangeLocale{}";
    }
}
