package ua.com.company.store.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Created by Владислав on 09.01.2018.
 */
public class FilterSecondPassword implements Filter {
    private Logger logger = Logger.getRootLogger();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String pass1 = servletRequest.getParameter("password");
        String pass2 = servletRequest.getParameter("password2");
        System.out.println(pass1);
        System.out.println(pass2);
        if (!Objects.equals(pass1, pass2) || pass1.equals("1234") || pass2.contains("<script>")){

            PrintWriter printWriter = servletResponse.getWriter();
            printWriter.println("You have enter a wrong password");
            servletRequest.getRequestDispatcher("/view/signUpPage.jsp").include(servletRequest,servletResponse);

            logger.info("Works filter" + this.toString() +"\n"+
            "password does not equals");
        }
        else {
            logger.info("Works filter" + this.toString() +"\n"+
            "passwords equals");
            servletRequest.setCharacterEncoding("Cp1251");
            servletResponse.setCharacterEncoding("Cp1251");
            filterChain.doFilter(servletRequest,servletResponse);
         }
    }

    @Override
    public void destroy() {

    }
}
