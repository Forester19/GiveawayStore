package ua.com.company.store.controller.command;

import ua.com.company.store.controller.impl.LoginFormExecution;

import java.util.EnumMap;

/**
 * Created by Владислав on 22.11.2017.
 */
public class CommandInvoker {
    private EnumMap<CommandEnum, CommandTypical> commands = new EnumMap<>(CommandEnum.class);

    public CommandInvoker() {
        commands.put(CommandEnum.LOGIN_FORM, new LoginFormExecution());
    }

    public CommandTypical getCommand(String nameCommand) {

        CommandEnum currentCommand = CommandEnum.valueOf(nameCommand);
        return commands.get(currentCommand);
    }
}
