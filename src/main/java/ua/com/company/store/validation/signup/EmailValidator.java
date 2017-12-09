package ua.com.company.store.validation.signup;

import ua.com.company.store.validation.ValidatorAbstract;

/**
 * Created by Владислав on 29.11.2017.
 */
public class EmailValidator extends ValidatorAbstract {
    @Override
    public boolean validateInput(String... inputText) {
        boolean result = true;
        if (inputText[2].isEmpty()||inputText[0].contains("<script>")){
            result = false;
        }
        return result;

    }
}
