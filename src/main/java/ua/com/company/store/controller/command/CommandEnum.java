package ua.com.company.store.controller.command;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.impl.*;
import ua.com.company.store.model.dao.connection.JDBCConnectionPool;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.dao.exceptions.PersistException;
import ua.com.company.store.model.dao.factory.MySqlDaoFactory;
import ua.com.company.store.model.entity.User;
import ua.com.company.store.service.UserService;

/**
 * Created by Владислав on 22.11.2017.
 */
public enum CommandEnum {
    PAGE_NOT_FOUND {
        {
            this.key = "GET:pageNotFound";
            this.command = new PageNotFound();
        }
    },
    HOME {{
        this.key = "GET:/store";
        this.command = new HomePageCommand();
    }
    },
    CHANGE_LOCALE {{

        this.key = "GET:/store/locale";
        this.command = new ChangeLocale();
    }
    },
    LOGIN_PAGE {{
        this.key = "GET:/store/login";
        this.command = new LoginPage();

    }},
    SIGNUP_FORM {{
        this.key = "POST:/store/signUpForm";
        this.command = new SignUpFormExecution(UserService.getInstance());

    }};

    String key;
    CommandTypical command;

    public String getKey() {
        return key;
    }

    public CommandTypical getCommand() {
        return command;
    }

    public static CommandTypical getCommand(String key) {
        CommandTypical resCommand = null;
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (commandEnum.getKey().equals(key)) {
                resCommand = commandEnum.getCommand();
            }
        }
        if (resCommand != null) {
            return resCommand;
        } else {
            return PAGE_NOT_FOUND.getCommand();
        }
    }
}
