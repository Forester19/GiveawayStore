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
public class CommandInvoker {
    private EnumMap<CommandEnum, CommandTypical> commands = new EnumMap<>(CommandEnum.class);
    private JDBCConnectionPool jdbcConnectionPool = JDBCConnectionPool.getInstanceConnectionPool();

    public CommandInvoker() throws PersistException {
        FactoryDAO factoryDAO = new MySqlDaoFactory();
        commands.put(CommandEnum.LOGIN_FORM, new LoginFormExecution((AbstractDao) factoryDAO.getDao(User.class,jdbcConnectionPool)));
        commands.put(CommandEnum.SIGNUP_FORM, new SignUpFormExecution((AbstractDao) factoryDAO.getDao(User.class,jdbcConnectionPool)));
        commands.put(CommandEnum.ADD_NEW_PRODUCT_FORM, new AddNewProductByAdminExecution((AbstractDao) factoryDAO.getDao(Product.class,jdbcConnectionPool),
                (AbstractDao) factoryDAO.getDao(Image.class,jdbcConnectionPool)));
        commands.put(CommandEnum.DELETE_SESSION_FORM,new RemoveSession());
    }

    public CommandTypical getCommand(String nameCommand) {

        CommandEnum currentCommand = CommandEnum.valueOf(nameCommand);
        return commands.get(currentCommand);
    }
}
