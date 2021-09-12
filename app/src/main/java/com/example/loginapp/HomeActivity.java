package com.example.loginapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {

        private String mUsername;
        private int mCountryCode;

        private ImageView mImgCountry;
        private TextView mTxtUsername;

        private ImageView mImgUserImage;
        private TextView mTxtUser;
        private Button mBtnEditProfile;

        private LinearLayout mMainContainer;

        private String[] mArrAdvertisements = {
                "Buy our product now and Pay later  !!!",
                "Join Android and get great Career!"
        };

        private int mUserImageId = R.mipmap.ic_launcher;
        private String mUser = "Guest";


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //setup the UI
            init();

            //obtain the input
            processInput();

            //Code to display advertisements
            manageAdvertisements();

            mBtnEditProfile.setOnClickListener(new BtnEditProfileClickListener());

        }

        private class BtnEditProfileClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                Intent intentEditProfileActivity =
                        new Intent(HomeActivity.this, EditProfileActivity.class);

                intentEditProfileActivity.putExtra("name", mUser);
                intentEditProfileActivity.putExtra("user_image_id", mUserImageId);

                startActivityForResult(intentEditProfileActivity, 1);

            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(data != null) {

                mUserImageId = data.getIntExtra("user_image_id", R.mipmap.ic_launcher);
                mImgUserImage.setImageResource(mUserImageId);

                mUser = data.getStringExtra("name");
                mTxtUser.setText(mUser);
            }
        }

        private void manageAdvertisements() {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

            TextView txtAdvertisement1 = new TextView(this);
            txtAdvertisement1.setLayoutParams(layoutParams);
            txtAdvertisement1.setTextColor(Color.BLACK);
            txtAdvertisement1.setText(mArrAdvertisements[0]);
            mMainContainer.addView(txtAdvertisement1, 0);


            if (mCountryCode == MainActivity.COUNTRY_CANADA) {
                TextView txtAdvertisement2 = new TextView(this);
                txtAdvertisement2.setLayoutParams(layoutParams);
                txtAdvertisement2.setText(mArrAdvertisements[1]);
                mMainContainer.addView(txtAdvertisement2);
            }
        }

        private void processInput() {
            Intent i = getIntent();
            Bundle ip = i.getExtras();

            mUsername = ip.getString(MainActivity.KEY_USERNAME, "Guest");
            mCountryCode = ip.getInt(MainActivity.KEY_COUTRY_CODE, 1);

            mt(mUsername + " " + mCountryCode);

            //bind the data to views
            if (mCountryCode == MainActivity.COUNTRY_INDIA) {
                mImgCountry.setImageResource(R.drawable.iflag);
            }
            if (mCountryCode == MainActivity.COUNTRY_CANADA) {
                mImgCountry.setImageResource(R.drawable.cflag);
            }
            mTxtUsername.setText("Welcome " + mUsername);

        }

        private void init() {
            setContentView(R.layout.activity_home);

            mMainContainer = findViewById(R.id.mainContainer);
            mImgCountry = findViewById(R.id.imgCountry);
            mTxtUsername = findViewById(R.id.txtUsername);
            mImgUserImage = findViewById(R.id.imgUserImage);
            mTxtUser = findViewById(R.id.txtUser);
            mBtnEditProfile = findViewById(R.id.btnEditProfile);

            mImgUserImage.setImageResource(mUserImageId);
            mTxtUser.setText(mUser);
        }

        private void mt(String text) {
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

    }
