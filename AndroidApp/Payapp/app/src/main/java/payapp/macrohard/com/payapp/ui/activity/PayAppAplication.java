package payapp.macrohard.com.payapp.ui.activity;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import payapp.macrohard.com.payapp.utils.Constants;

/**
 * Created by nishant on 9/11/15.
 */
public class PayAppAplication extends Application{

    static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();
        //ParseObject.registerSubclass(PayAppPost.class);
       //FacebookSdk.sdkInitialize(getApplicationContext());
        Parse.initialize(this, Constants.PARSE_APP_ID, Constants.PARSE_CLIENT_KEY);
        ParseFacebookUtils.initialize(this);
    }
}
