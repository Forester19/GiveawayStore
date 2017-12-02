package ua.com.company.store.validation.registration;

import ua.com.company.store.validation.ValidatorAbstract;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 28.11.2017.
 */
public class PasswordValidator extends ValidatorAbstract {
    @Override
    public boolean validateInput(String ...  inputText) {
        boolean result = true;
        if (inputText[1].isEmpty()){
            result = false;
        }
        return result;
    }
}
