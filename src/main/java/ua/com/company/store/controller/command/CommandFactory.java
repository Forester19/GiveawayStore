package ua.com.company.store.controller.command;

import ua.com.company.store.controller.impl.AddNewProductByAdminExecution;
import ua.com.company.store.controller.impl.LoginFormExecution;
import ua.com.company.store.controller.impl.RemoveSession;
import ua.com.company.store.controller.impl.SignUpFormExecution;
import ua.com.company.store.model.dao.connection.JDBCConnectionPool;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.dao.exceptions.PersistException;
import ua.com.company.store.model.dao.factory.FactoryDAO;
import ua.com.company.store.model.dao.factory.MySqlDaoFactory;
import ua.com.company.store.model.entity.Image;
import ua.com.company.store.model.entity.Product;
import ua.com.company.store.model.entity.User;

import java.util.EnumMap;

/**
 * Created by Владислав on 22.11.2017.
 */
public class CommandFactory {
    private CommandFactory() {
    }
    public static CommandTypical getCommand(String commandKey){
        return CommandEnum.getCommand(commandKey);
    }
}