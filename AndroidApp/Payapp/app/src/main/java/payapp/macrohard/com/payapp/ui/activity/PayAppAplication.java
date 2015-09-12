package payapp.macrohard.com.payapp.ui.activity;

import android.app.Application;

import com.parse.Parse;

import payapp.macrohard.com.payapp.utils.Constants;

/**
 * Created by nishant on 9/11/15.
 */
public class PayAppAplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //ParseObject.registerSubclass(PayAppPost.class);
        Parse.initialize(this, Constants.PARSE_APP_ID, Constants.PARSE_CLIENT_KEY);
    }
}
