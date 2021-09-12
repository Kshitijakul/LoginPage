package com.example.loginapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView mTxtTitle, mTxtSubTitle;
    private LinearLayout mContainer;

    private ImageView mImgIndia, mImgCanada;
    private EditText mEdtUsername, mEdtPassword;
    private Button mBtnLogin, mBtnForgotPassword;

    private int mSelectedCountry = 1;

    public static final int COUNTRY_INDIA = 1;
    public static final int COUNTRY_CANADA = 2;

    public static final String KEY_USERNAME = "username";
    public static final String KEY_COUTRY_CODE = "countrycode";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mt("onCreate");

        init();

        mBtnLogin.setOnClickListener(new BtnLoginClickListener());

        mBtnForgotPassword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Password sent to your email...", Toast.LENGTH_LONG)
                                .show();
                    }
                }
        );

        mImgIndia.setOnClickListener(this);
        mImgCanada.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mt("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mt("onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mt("onRestart");
    }

    @Override
    protected void onPause() {
        mt("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        mt("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mt("onDestroy");
        super.onDestroy();
    }

    private void init() {
        setContentView(R.layout.activity_main);

        mImgIndia = findViewById(R.id.indiaflag);
        mImgCanada = findViewById(R.id.canadaflag);
        mEdtUsername = findViewById(R.id.edtusername);
        mEdtPassword = findViewById(R.id.edtpassword);
        mBtnLogin = findViewById(R.id.btnlogin);
        mBtnForgotPassword = findViewById(R.id.forgotpassword);
    }

    private class BtnLoginClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (mEdtUsername.getText().toString().equals("kshitija") && mEdtPassword.getText().toString().equals("kshitija")) {
                mt("Login Successful!");
                //code to start HomeActivity
                Intent intentHomeActivity =
                        new Intent(
                                MainActivity.this,
                                HomeActivity.class
                        );

                //put the input data into the intent
                intentHomeActivity.putExtra(KEY_USERNAME, mEdtUsername.getText().toString());
                intentHomeActivity.putExtra(KEY_COUTRY_CODE, mSelectedCountry );

                startActivity(intentHomeActivity);


            } else {
                Toast.makeText(
                        MainActivity.this,
                        "Login Failed!",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
    }

    private class BtnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //code to exe when button is touched
            return false;
        }
    }

    private class BtnKeyListener implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            //code to exe when key pressesd
            return false;
        }
    }


    private class ImgCountryClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view == mImgIndia) {
                mt("India");
                mSelectedCountry = 1;
            }
            if(view.getId() == R.id.canadaflag) {
                mt("Canada");
                mSelectedCountry = 2;
            }

        }
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        Log.e("myapp", text);
    }

    @Override
    public void onClick(View view) {
        if(view == mImgIndia) {
            mt("India");
            mSelectedCountry = 1;
            mImgIndia.setBackgroundColor(Color.BLUE);
            mImgCanada.setBackgroundColor(Color.WHITE);
        }
        if(view.getId() == R.id.canadaflag) {
            mt("Canada");
            mSelectedCountry = 2;
            mImgIndia.setBackgroundColor(Color.WHITE);
            mImgCanada.setBackgroundColor(Color.BLUE);
        }
    }
}

