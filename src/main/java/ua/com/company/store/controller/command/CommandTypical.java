package ua.com.company.store.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 22.11.2017.
 */
public interface CommandTypical {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
