package ua.com.company.store.controller.impl;

import ua.com.company.store.controller.command.CommandTypical;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 08.12.2017.
 */
public class LoginPage implements CommandTypical {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/view/signUpPage.jsp";
    }

    @Override
    public String toString() {
        return "LoginPage{}";
    }
}
