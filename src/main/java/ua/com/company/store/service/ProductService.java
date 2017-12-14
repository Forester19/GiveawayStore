package ua.com.company.store.service;

import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.dao.daoAbstract.GenericDAO;
import ua.com.company.store.model.dao.exceptions.PersistException;
import ua.com.company.store.model.dao.factory.MySqlDaoFactory;
import ua.com.company.store.model.dao.impl.ProductDAO;
import ua.com.company.store.model.entity.Image;
import ua.com.company.store.model.entity.Product;
import ua.com.company.store.model.entity.User;

import static ua.com.company.store.model.dao.connection.JDBCConnectionPool.getInstanceConnectionPool;

/**
 * Created by Владислав on 13.12.2017.
 */
public class ProductService {
    private GenericDAO genericDAO;
    public ProductService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;

    }
    private static class Holder{
        static ProductService INSTANCE;
        static {
            try {
                INSTANCE = new ProductService(new MySqlDaoFactory().getDao(Product.class, getInstanceConnectionPool()));
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
    }

    public static ProductService  getInstance(){
        return Holder.INSTANCE;
    }
    public int addProduct(Product product){
        return genericDAO.insert(product);
    }
    public void addProductAndImage(Product product, Image image){
        ProductDAO productDAO = (ProductDAO) genericDAO;
        productDAO.insertImageAndProduct(image,product);
    }

}
