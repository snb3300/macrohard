package payapp.macrohard.com.payapp.ui.activity;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import payapp.macrohard.com.payapp.R;
import payapp.macrohard.com.payapp.utils.Validator;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Bind(R.id.button_send)
    Button sendButton;
    @Bind(R.id.editText_email)
    EditText emailEditText;

    Resources res;
    Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        res = getResources();
        validator = new Validator();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_send)
    public void resetPassword(View view){
        boolean validationError = false;

        String email = emailEditText.getText().toString().trim();
        if(email.isEmpty()){
            emailEditText.setError(res.getString(R.string.error_email));
            validationError = true;
        }else if(!validator.EmailValidator(email)){
            emailEditText.setError(Html.fromHtml(res.getString(R.string.invalid_email)));
            validationError = true;
        }

        if(validationError){
            Toast.makeText(this, "Fail", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.progress_signup));
        dialog.show();

        ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                dialog.dismiss();
                if (e == null) {
                    Toast.makeText(ForgotPasswordActivity.this,"Instructions to reset password has been successfully sent",Toast.LENGTH_LONG).show();
                    // An email was successfully sent with reset instructions.
                } else {
                    // Something went wrong. Look at the ParseException to see what's up.
                    Toast.makeText(ForgotPasswordActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

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


}
