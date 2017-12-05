package ua.com.company.store.controller;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandInvoker;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.dao.exceptions.PersistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by Владислав on 22.11.2017.
 */
@WebServlet(name = "default servlet ", urlPatterns = {"/loginForm","/signUpForm","/newProductForm"})
@MultipartConfig
public class DefaultServlet extends HttpServlet {
    private Logger logger = Logger.getRootLogger();
    private CommandInvoker commandInvoker = new CommandInvoker();

    public DefaultServlet() throws PersistException {

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    process(req,resp);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String COMMAND = "command";
        String commandName = req.getParameter(COMMAND);
        if (commandName == null) {
            commandName = (String) req.getAttribute(COMMAND);
           }
           logger.info("Form with command: " + commandName);
        CommandTypical commandTypical = commandInvoker.getCommand(commandName);
        commandTypical.execute(req,resp);
    }

}