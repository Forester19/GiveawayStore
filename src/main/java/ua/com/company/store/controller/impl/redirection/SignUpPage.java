package ua.com.company.store.controller.impl.redirection;

import ua.com.company.store.controller.command.CommandTypical;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 08.12.2017.
 */
public class SignUpPage implements CommandTypical {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/view/signUpPage.jsp";
    }

    @Override
    public String toString() {
        return "SignUpPage{}";
    }
}