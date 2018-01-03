package ua.com.company.store.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.company.store.model.dao.connection.JDBCConnectionPool;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.dao.daoAbstract.GenericDAO;
import ua.com.company.store.model.dao.factory.FactoryDAO;
import ua.com.company.store.model.dao.impl.UserDAO;
import ua.com.company.store.model.entity.User;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by Владислав on 02.01.2018.
 */
public class UserServiceTest {
    private UserService userService;
    private GenericDAO genericDAO;
    private FactoryDAO factoryDAO;
    private UserDAO userDAO;
    private JDBCConnectionPool jdbcConnectionPool;

    @Before
    public void setUp() throws Exception {
        factoryDAO = mock(FactoryDAO.class);
       genericDAO = mock(GenericDAO.class);
        jdbcConnectionPool= JDBCConnectionPool.getInstanceConnectionPool();

        userDAO = new UserDAO(jdbcConnectionPool);
        userService = new UserService(genericDAO);
    when(factoryDAO.getDao(User.class)).thenReturn(userDAO);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addUser() throws Exception {
     User user = new User(0,"ewr","eefse","fesfef",false,false);
        userService.addUser(user);
     verify(factoryDAO).getDao(User.class);
     verify(userService).addUser(user);
    }

    @Test
    public void validationUserOnBeforeExist() throws Exception {

    }

    @Test
    public void getAllUsers() throws Exception {

    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void getUserByNickName() throws Exception {

    }

    @Test
    public void markUserAsDefaulter() throws Exception {

    }

}