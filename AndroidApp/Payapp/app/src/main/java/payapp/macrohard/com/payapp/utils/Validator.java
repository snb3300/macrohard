package payapp.macrohard.com.payapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nishant on 9/11/15.
 */
public class Validator {

    private Pattern pattern;
    private Matcher matcher;

    private static final int PASSWORDLENGTH = 6;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean EmailValidator(final String string) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public int getPasswordlength(){
        return PASSWORDLENGTH;
    }

}
