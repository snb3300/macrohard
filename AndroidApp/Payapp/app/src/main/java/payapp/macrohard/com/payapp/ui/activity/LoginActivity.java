package payapp.macrohard.com.payapp.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import payapp.macrohard.com.payapp.R;
import payapp.macrohard.com.payapp.utils.Constants;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.editText_email) EditText emailEditText;
    @Bind(R.id.editText_password) EditText passwordEditText;
    @Bind(R.id.button_login) Button loginButton;
    @Bind(R.id.text_forgot_password) TextView forgotpasswordTextView;
    @Bind(R.id.button_fblogin) Button buttonFBLogin;
    Resources res;
    Typeface regularFont;
   // LoginButton fbLoginButton;
    private Dialog progressDialog;
//    private CallbackManager mCallbackManager;
//    private FacebookCallback<LoginResult> mcallback = new FacebookCallback<LoginResult>() {
//        @Override
//        public void onSuccess(LoginResult loginResult) {
//            AccessToken accessToken = loginResult.getAccessToken();
//            Profile profile = Profile.getCurrentProfile();
//
//            if(profile != null){
//                Log.v("Profile",""+profile.getFirstName());
//            }else{
//                Log.v("profile","null");
//            }
//        }
//
//        @Override
//        public void onCancel() {
//            Log.v("onCancel","onCancel");
//        }
//
//        @Override
//        public void onError(FacebookException e) {
//            Log.v("onError",""+e.getMessage());
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        res = getResources();
        initializeUI();
        setFont();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
            // Go to the user info activity
            showUserDetailsActivity();
        }
       // facebookLogin();
    }

//    private void facebookLogin(){
//        mCallbackManager = CallbackManager.Factory.create();
//        fbLoginButton.setReadPermissions();
//        fbLoginButton.registerCallback(mCallbackManager, mcallback);
//    }

    private void setFont(){
       // fbLoginButton.setTypeface(regularFont);
        loginButton.setTypeface(regularFont);
        emailEditText.setTypeface(regularFont);
        passwordEditText.setTypeface(regularFont);
        forgotpasswordTextView.setTypeface(regularFont);
    }

    private void initializeUI(){
        ButterKnife.bind(this);
        regularFont.createFromAsset(getAssets(), Constants.REGULAR_FONT);
        //fbLoginButton = (LoginButton)findViewById(R.id.authButton);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
        //ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
        //mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @OnClick(R.id.button_login)
    public void login(View view) {
        boolean validationError = false;

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();


        if(email.isEmpty()){
            emailEditText.setError(Html.fromHtml(res.getString(R.string.error_email)));
            validationError = true;
        }

        if(password.isEmpty()){
            passwordEditText.setError(Html.fromHtml(res.getString(R.string.error_password)));
            validationError = true;
        }

        if(validationError){
            Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_LONG)
                    .show();
            return;
        }


        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage(getString(R.string.progress_login));
        dialog.show();
        // Call the Parse login method
        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser email, ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(LoginActivity.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

    }

    @OnClick(R.id.text_forgot_password)
    public void forgotPassword(View view){
        Intent i = new Intent(this,ForgotPasswordActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.button_fblogin)
    public void onLoginClick(View v) {
       progressDialog = ProgressDialog.show(LoginActivity.this, "", "Logging in...", true);

        List<String> permissions = Arrays.asList("public_profile", "email");
        // NOTE: for extended permissions, like "user_about_me", your app must be reviewed by the Facebook team
        // (https://developers.facebook.com/docs/facebook-login/permissions/)
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permissions, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                progressDialog.dismiss();
                if (user == null) {
                    Log.d(PayAppAplication.TAG, "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.d(PayAppAplication.TAG, "User signed up and logged in through Facebook!");
                    showUserDetailsActivity();
                } else {
                    Log.d(PayAppAplication.TAG, "User logged in through Facebook!");
                    showUserDetailsActivity();
                }
            }
        });


    }

    private void showUserDetailsActivity() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }


}
