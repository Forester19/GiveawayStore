package ua.com.company.store.controller.impl.redirection;

import ua.com.company.store.constants.Redirection;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.utils.RedirectionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 07.12.2017.
 */
public class CommandPageNotFound implements CommandTypical {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    return Redirection.PAGE_NOT_FOUND + " " + RedirectionManager.REDIRECTION;
    }

    @Override
    public String toString() {
        return "CommandPageNotFound{}";
    }
}
