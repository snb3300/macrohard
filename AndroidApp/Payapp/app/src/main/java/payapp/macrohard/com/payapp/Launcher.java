package payapp.macrohard.com.payapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
We are using AppCompat library to support older version of Android
 */

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }
}
