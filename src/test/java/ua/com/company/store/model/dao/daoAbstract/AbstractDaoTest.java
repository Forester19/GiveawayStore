package ua.com.company.store.model.dao.daoAbstract;


import org.junit.Assert;
import org.junit.Test;
import ua.com.company.store.model.dao.impl.UserDAO;

/**
 * Created by Владислав on 24.11.2017.
 */
public class AbstractDaoTest {
    @Test
    public void insert() throws Exception {

    }

    @org.junit.Test
    public void getById() throws Exception {

    }

    @org.junit.Test
    public void getAll() throws Exception {

    }

    @org.junit.Test
    public void getConnectionFromPool() throws Exception {
          AbstractDao abstractDao = new UserDAO();
         Assert.assertNotNull(abstractDao.getConnectionFromPool());
    }

}