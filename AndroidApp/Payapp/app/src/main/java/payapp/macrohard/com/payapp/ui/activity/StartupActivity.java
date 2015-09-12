package payapp.macrohard.com.payapp.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import payapp.macrohard.com.payapp.R;
import payapp.macrohard.com.payapp.utils.Constants;

/*
We are using AppCompat library to support older version of Android
 */

public class StartupActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 4;

    @Bind(R.id.button_signup)
    Button signUp;

    @Bind(R.id.button_login)
    Button logIn;

    Typeface regularFont;
    Typeface boldFont;
    ViewPager mPager;
    ScreenSlidePagerAdapter mPagerAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        // Enable Local Datastore.
        //Parse.enableLocalDatastore(this);
        //Parse.initialize(this, Constants.PARSE_APP_ID, Constants.PARSE_CLIENT_KEY);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        initializeUI();
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();
    }

    private void initializeUI(){
        regularFont = Typeface.createFromAsset(getAssets(), Constants.REGULAR_FONT);
        boldFont = Typeface.createFromAsset(getAssets(),Constants.BOLD_FONT);
        ButterKnife.bind(this);
        logIn.setTypeface(regularFont);
        signUp.setTypeface(regularFont);
    }

    @OnClick(R.id.button_signup)
    public void signUp(View view) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_login)
    public void logIn(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
