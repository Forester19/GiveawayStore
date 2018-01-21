package ua.com.company.store.validation.signup;

import ua.com.company.store.validation.ValidatorAbstract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Владислав on 29.11.2017.
 */
public class EmailValidator extends ValidatorAbstract {
    @Override
    public boolean validateInput(String... inputText) {
        String input = inputText[2];
        boolean result = true;
        if (input.isEmpty() || input.contains("<script>") || !regexValidEmail(input)){
            result = false;
        }
        return result;

    }
    private boolean regexValidEmail(String  email){
        Pattern pattern = Pattern.compile("[a-zA-Z]{1}[a-zA-Z\\d\\u002e\\u005f]+@([a-zA-Z]+\\u002e{1,2}(net|com|org|ru|ua))");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
