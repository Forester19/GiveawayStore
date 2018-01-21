package ua.com.company.store.service;

import ua.com.company.store.exceptions.PersistException;
import ua.com.company.store.model.dao.daoAbstract.GenericDAO;
import ua.com.company.store.model.dao.factory.MySqlDaoFactory;
import ua.com.company.store.model.entity.Review;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.utils.JDBCConnectionPoolManager;

/**
 * Created by Владислав on 21.01.2018.
 */
public class ReviewService {
    private GenericDAO genericDAO;

    public ReviewService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    private static class Holder {
        static ReviewService INSTANCE;

        static {
            try {
                INSTANCE = new ReviewService(MySqlDaoFactory.getMysqlDaoFactory(JDBCConnectionPoolManager.getInstance().getJdbcConnectionPool()).getDao(Review.class));
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
    }
    public static ReviewService getInstance() {
        return ReviewService.Holder.INSTANCE;
    }

}
