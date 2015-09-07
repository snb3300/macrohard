package payapp.macrohard.com.payapp.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import payapp.macrohard.com.payapp.R;
import payapp.macrohard.com.payapp.constants.Constants;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.editText_username)
    EditText username;
    @Bind(R.id.editText_password)
    EditText password;
    @Bind(R.id.button_login)
    Button login;


    Typeface regularFont;
    LoginButton fbLoginButton;
    private CallbackManager mCallbackManager;
    private FacebookCallback<LoginResult> mcallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            if(profile != null){
                Log.v("Profile",""+profile.getFirstName());
            }else{
                Log.v("profile","null");
            }
        }

        @Override
        public void onCancel() {
            Log.v("onCancel","onCancel");
        }

        @Override
        public void onError(FacebookException e) {
            Log.v("onError",""+e.getMessage());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        initializeUI();
        setFont();
        facebookLogin();
    }

    private void facebookLogin(){
        mCallbackManager = CallbackManager.Factory.create();
        fbLoginButton.setReadPermissions();
        fbLoginButton.registerCallback(mCallbackManager, mcallback);
    }

    private void setFont(){
        fbLoginButton.setTypeface(regularFont);
        login.setTypeface(regularFont);
        username.setTypeface(regularFont);
        password.setTypeface(regularFont);
    }

    private void initializeUI(){
        ButterKnife.bind(this);
        regularFont.createFromAsset(getAssets(), Constants.REGULAR_FONT);
        fbLoginButton = (LoginButton)findViewById(R.id.authButton);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
