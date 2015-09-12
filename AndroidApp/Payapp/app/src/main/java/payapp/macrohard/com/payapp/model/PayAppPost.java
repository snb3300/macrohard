package payapp.macrohard.com.payapp.model;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by nishant on 9/12/15.
 */
public class PayAppPost extends ParseObject {

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser value) {
        put("user", value);
    }
}
