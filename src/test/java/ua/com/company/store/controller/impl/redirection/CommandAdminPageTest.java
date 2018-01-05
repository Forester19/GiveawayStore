package ua.com.company.store.controller.impl.redirection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.company.store.constants.Redirection;
import ua.com.company.store.controller.utils.RedirectionManager;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by Владислав on 03.01.2018.
 */
public class CommandAdminPageTest {
   private UserService userService;
    private CommandAdminPage commandAdminPage;
    private HttpServletRequest request;
    private HttpServletResponse response;
    @Before
    public void setUp() throws Exception {
        initObjectsMocking();
        commandAdminPage = new CommandAdminPage(userService);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void noVerifUserInSessionRedirectionTest() throws ServletException, IOException {
        when(request.getAttribute("user")).thenReturn(new User());

        String expectedResultResource = Redirection.ACCESS_ERROR_PAGE+ " " + RedirectionManager.REDIRECTION;
        String actualResult = commandAdminPage.execute(request,response);


        Assert.assertEquals(expectedResultResource,actualResult);
        verify(request,times(2)).getAttribute("user");

    }
    @Test
    public void verifUserInSessionRedirectionTest() throws ServletException, IOException {
        HttpSession session = mock(HttpSession.class);
        User user = mock(User.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(user.isRole()).thenReturn(true);

        String expectedResultResource = Redirection.ADMIN_PAGE+ " " + RedirectionManager.REDIRECTION;
        String actualResult = commandAdminPage.execute(request,response);


        Assert.assertEquals(expectedResultResource,actualResult);

        verify(request, times(4)).getSession();
        verify(request).getAttribute("user");
        verify(user).isRole();
       // verify(request).setAttribute(anyString(), mock(List.class));
    }

    private void initObjectsMocking() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        userService = mock(UserService.class);
    }

}