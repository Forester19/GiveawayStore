package ua.com.company.store.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.company.store.model.dao.connection.JDBCConnectionPool;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.entity.Image;
import ua.com.company.store.model.entity.Product;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by Владислав on 05.12.2017.
 */
public class ProductDAOTest {
    private int[] idArrays;
    private ProductDAO productDAO;
    private ImageDAO imageDAO;
    @Before
    public void setUp() throws Exception {
        JDBCConnectionPool jdbcConnectionPool = JDBCConnectionPool.getInstanceConnectionPool();
        productDAO = new ProductDAO(jdbcConnectionPool);
        imageDAO = new ImageDAO(jdbcConnectionPool);

    }

    @After
    public void tearDown() throws Exception {
        productDAO.delete(new Product(idArrays[1]));
        imageDAO.delete(new Image(idArrays[0]));

    }

    @Test
    public void insertImageAndProduct() throws Exception {
        idArrays = productDAO.insertImageAndProduct(new Image(0,"fewfef","fwefwef"), new Product(0,"fewef","fewefw",123,0));

    }

}