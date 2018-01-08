package ua.com.company.store.service;

import ua.com.company.store.controller.DefaultServlet;
import ua.com.company.store.controller.utils.JDBCConnectionPoolManager;
import ua.com.company.store.model.dao.daoAbstract.GenericDAO;
import ua.com.company.store.model.dao.exceptions.PersistException;
import ua.com.company.store.model.dao.factory.MySqlDaoFactory;
import ua.com.company.store.model.dao.impl.ProductDAO;
import ua.com.company.store.model.dao.impl.ProductImageDAO;
import ua.com.company.store.model.entity.Image;
import ua.com.company.store.model.entity.Order;
import ua.com.company.store.model.entity.Product;
import ua.com.company.store.model.entity.additional.ProductImage;

import java.util.List;

import static ua.com.company.store.model.dao.connection.JDBCConnectionPool.getInstanceConnectionPool;

/**
 * Created by Владислав on 19.12.2017.
 */
public class ProductImageService {
    private GenericDAO genericDAO;

    public ProductImageService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }
    private static class Holder{
        static ProductImageService INSTANCE;

        static {
            try {
                INSTANCE = new ProductImageService(new MySqlDaoFactory(JDBCConnectionPoolManager.getInstance().getJdbcConnectionPool()).getDao(ProductImage.class));
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
    }
    public static ProductImageService getInstance(){
        return ProductImageService.Holder.INSTANCE;
    }
    public List<ProductImage> getAllProducts(){
        ProductImageDAO productImageDAO = (ProductImageDAO) genericDAO;
        return productImageDAO.getAllProductsWithImage();
    }
    public void addProductAndImage(Product product, Image image){
       ProductImageDAO productImageDAO = (ProductImageDAO) genericDAO;
        productImageDAO.insertImageAndProduct(image,product);
    }
}
