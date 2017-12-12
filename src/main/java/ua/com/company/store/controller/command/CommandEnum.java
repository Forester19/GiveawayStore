package ua.com.company.store.controller.command;

import ua.com.company.store.controller.impl.executions.LoginFormExecution;
import ua.com.company.store.controller.impl.executions.SignUpFormExecution;
import ua.com.company.store.controller.impl.redirection.*;
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
    SIGNUP_PAGE {{
        this.key = "GET:/store/signUp";
        this.command = new SignUpPage();

    }},
    LOGIN_PAGE {{
        this.key = "GET:/store/login";
        this.command = new LoginPage();

    }},
    LOGIN_FORM {{
        this.key = "GET:/store/loginForm";
        this.command = new LoginFormExecution(UserService.getInstance());

    }},
    LOGOUT_FORM {{
        this.key = "GET:/store/logout";
        this.command = new RemoveSession();

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
