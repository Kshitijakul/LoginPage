package com.example.loginapp;

import static com.example.loginapp.R.layout.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView mImgCharacter1, mImgCharacter2, mImgCharacter3;
    private EditText mEdtUser;
    private Button mBtnSave;

    private int mUserImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        init();

        mBtnSave.setOnClickListener(new BtnSaveClickListener());

        View.OnClickListener imgCharactersClickListener = new ImgCharactersClickListener();
        mImgCharacter1.setOnClickListener(imgCharactersClickListener);
        mImgCharacter2.setOnClickListener(imgCharactersClickListener);
        mImgCharacter3.setOnClickListener(imgCharactersClickListener);

    }

    private class BtnSaveClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //code to send the data back to prev act
            Intent intentResult = new Intent();
            intentResult.putExtra("name", mEdtUser.getText().toString());
            intentResult.putExtra("user_image_id", mUserImageId);

            setResult(1, intentResult);

            finish();
        }
    }

    private class ImgCharactersClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view == mImgCharacter1) {
                mUserImageId = R.drawable.doraemon;
            }
            else {
                if(view == mImgCharacter2) {
                    mUserImageId = R.drawable.shinchan;
                }
                else {
                    mUserImageId = R.drawable.tom;
                }
            }
        }
    }

    private void init() {
        mImgCharacter1 = findViewById(R.id.imgCharacter1);
        mImgCharacter2 = findViewById(R.id.imgCharacter2);
        mImgCharacter3 = findViewById(R.id.imgCharacter3);

        mEdtUser = findViewById(R.id.edtUser);
        mBtnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        mUserImageId = intent.getIntExtra("user_image_id", R.mipmap.ic_launcher);

        if(mUserImageId == R.drawable.doraemon) {
            mImgCharacter1.setBackgroundColor(Color.BLACK);
        }
        if(mUserImageId == R.drawable.shinchan) {
            mImgCharacter2.setBackgroundColor(Color.BLACK);
        }
        if(mUserImageId == R.drawable.tom) {
            mImgCharacter3.setBackgroundColor(Color.BLACK);
        }

        mEdtUser.setText(intent.getStringExtra("name"));


    }
}