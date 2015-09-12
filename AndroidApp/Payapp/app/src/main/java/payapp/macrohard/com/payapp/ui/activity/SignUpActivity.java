package payapp.macrohard.com.payapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import payapp.macrohard.com.payapp.R;
import payapp.macrohard.com.payapp.utils.Constants;
import payapp.macrohard.com.payapp.utils.Validator;

public class SignUpActivity extends AppCompatActivity {

    @Bind(R.id.editText_username) EditText usernameEditText;
    @Bind(R.id.editText_email) EditText emailEditText;
    @Bind(R.id.editText_password) EditText passwordEditText;
    @Bind(R.id.button_signup) Button signupButton;

    Typeface regularFont;
    private CallbackManager mCallbackManager;
    LoginButton fbLoginButton;
    SpannableString s;
    Validator validator;
    Resources res;

    private FacebookCallback<LoginResult> mcallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            if(profile != null){
                Log.v("Profile", "" + profile.getFirstName());
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
        setContentView(R.layout.activity_sign_up);
        validator = new Validator();
        res = getResources();
        initializeUI();
        setFont();
        facebookLogin();

        usernameEditText.addTextChangedListener(usernameTextWatcher);
        emailEditText.addTextChangedListener(emailTextWatcher);
        passwordEditText.addTextChangedListener(passwordTextWatcher);


    }

    private void initializeUI(){
        ButterKnife.bind(this);
        regularFont.createFromAsset(getAssets(), Constants.REGULAR_FONT);
        fbLoginButton = (LoginButton)findViewById(R.id.authButton);
       // fbLoginButton.setText("Sign up with Facebook");
    }

    private void setFont(){
        fbLoginButton.setTypeface(regularFont);
        signupButton.setTypeface(regularFont);
        usernameEditText.setTypeface(regularFont);
        passwordEditText.setTypeface(regularFont);
    }

    private void facebookLogin(){
        mCallbackManager = CallbackManager.Factory.create();
        fbLoginButton.setReadPermissions();
        fbLoginButton.registerCallback(mCallbackManager, mcallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private final TextWatcher usernameTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() < 1)
                usernameEditText.setError(res.getString(R.string.error_username));
            else
                usernameEditText.setError(null);
        }

        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher emailTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() < 1)
                emailEditText.setError(Html.fromHtml(res.getString(R.string.error_email)));
            else{
                emailEditText.setError(null);
            }
        }

        public void afterTextChanged(Editable s) {}
    };

    private final TextWatcher passwordTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() < validator.getPasswordlength())
                passwordEditText.setError(Html.fromHtml(res.getString(R.string.error_password)));
            else
                passwordEditText.setError(null);
    }

        public void afterTextChanged(Editable s) {}
    };

    @OnClick(R.id.button_signup)
    public void signUp(View view) {
        boolean validationError = false;
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if(username.isEmpty()){
            validationError = true;
            usernameEditText.setError(Html.fromHtml(res.getString(R.string.error_username)));
        }

        if(email.isEmpty()){
            emailEditText.setError(res.getString(R.string.error_email));
            validationError = true;
        }else if(!validator.EmailValidator(email)){
            emailEditText.setError(Html.fromHtml(res.getString(R.string.invalid_email)));
            validationError = true;
        }

        if(password.isEmpty()){
            passwordEditText.setError(Html.fromHtml(res.getString(R.string.error_password)));
            validationError = true;
        }

        if(validationError){
            Toast.makeText(SignUpActivity.this, "Fail", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
        dialog.setMessage(getString(R.string.progress_signup));
        dialog.show();

        ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);
        user.put("name",username);


        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(SignUpActivity.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
