package ua.com.company.store.controller.impl.executions;

import ua.com.company.store.constants.Redirection;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Владислав on 21.01.2018.
 */
public class CommandCreateReview implements CommandTypical {
    private ReviewService reviewService;

    public CommandCreateReview(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userWhoCreatedReview = (User) session.getAttribute("user");
        String reiew = req.getParameter("review");
        String product = req.getParameter("id");

        req.setAttribute("product",product);

        System.out.println(userWhoCreatedReview + "\n" + reiew + "\n" + Integer.parseInt(product));
        return Redirection.PRODUCT_PAGE;
    }
}
