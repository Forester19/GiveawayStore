package ua.com.company.store.constants;

/**
 * Created by Владислав on 03.01.2018.
 */
public class Redirection {

    // ERRORS PART
    public static String ACCESS_ERROR_PAGE = "/viewPublic/errors/accessErrorPage.jsp";
    public static String BAD_WAY_PASSWORDS = "/viewPublic/errors/badWayPassword.jsp";
    public static String EXIST_USER_ERROR = "/viewPublic/errors/existUserError.jsp";
    public static String NO_EXIST_USER_PAGE = "/viewPublic/errors/noExistUserPage.jsp";
    public static String PAGE_NOT_FOUND = "/viewPublic/errors/pageNotFound.jsp";
    public static String ERRORS_WITH_INPUTS = "/viewPublic/errors/someErrorByInputs.jsp";



    //MAIN PART
    public static String MAIN_PAGE= "/main.jsp";
    public static String LOGIN_PAGE = "/viewPublic/loginPage.jsp";
    public static String SIGN_UP_PAGE= "/viewPublic/signUpPage.jsp";
    public static String SUCCESSFUL_CREATED_ORDER= "/viewPublic/successfulCreatedOrder.jsp";

    //PRIVATE PAGES
    public static String ADMIN_PAGE = "/WEB-INF/viewPrivate/adminPage.jsp";
    public static String PERSONAL_PAGE = "/WEB-INF/viewPrivate/personalPage.jsp";

    //Product
    public static String PRODUCT_PAGE = "/viewPublic/productPage.jsp";

}
