package ua.com.company.store.controller.command;

import ua.com.company.store.controller.impl.LoginFormExecution;
import ua.com.company.store.model.dao.exceptions.PersistException;
import ua.com.company.store.model.dao.factory.FactoryDAO;
import ua.com.company.store.model.dao.factory.MySqlDaoFactory;
import ua.com.company.store.model.entity.User;

import java.util.EnumMap;

/**
 * Created by Владислав on 22.11.2017.
 */
public class CommandInvoker {
    private EnumMap<CommandEnum, CommandTypical> commands = new EnumMap<>(CommandEnum.class);

    public CommandInvoker() throws PersistException {
        FactoryDAO factoryDAO = new MySqlDaoFactory();
        commands.put(CommandEnum.LOGIN_FORM, new LoginFormExecution(factoryDAO.getDao(User.class)));
    }

    public CommandTypical getCommand(String nameCommand) {

        CommandEnum currentCommand = CommandEnum.valueOf(nameCommand);
        return commands.get(currentCommand);
    }
}
