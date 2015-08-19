package payapp.macrohard.com.payapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
We are using AppCompat library to support older version of Android
 */

public class Launcher extends AppCompatActivity {

    @Bind(R.id.button_signup)
     Button signUp;
    @Bind(R.id.button_login)
     Button logIn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_signup)
    public void signUp(View view) {
        // TODO submit data to server...

    }

    @OnClick(R.id.button_login)
    public void logIn(View view) {
        // TODO submit data to server...
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
