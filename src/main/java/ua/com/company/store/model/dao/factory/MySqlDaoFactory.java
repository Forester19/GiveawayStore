package ua.com.company.store.model.dao.factory;

import org.apache.log4j.Logger;
import ua.com.company.store.model.dao.daoAbstract.GenericDAO;
import ua.com.company.store.model.dao.exceptions.PersistException;
import ua.com.company.store.model.dao.impl.UserDAO;
import ua.com.company.store.model.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Владислав on 22.11.2017.
 */
public class MySqlDaoFactory implements FactoryDAO {
    private Map<Class, CreatorDao> creators;
    private Logger logger = Logger.getRootLogger();
    @Override
    public GenericDAO getDao(Class daoClass) throws PersistException {
       CreatorDao creatorDao = creators.get(daoClass);
       if (creatorDao == null){
           logger.info("Dao creator is null");
           throw new PersistException("Dao creator is null");
       }
        return creatorDao.create();
    }

    public MySqlDaoFactory() {
        creators = new HashMap<>();
        creators.put(User.class, UserDAO::new);
    }
}
