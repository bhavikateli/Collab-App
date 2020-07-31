package com.bhavikateli.collab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

public class OptionActivity extends AppCompatActivity {

    Button btnCommonRoomChat;
    LoginButton fb_login_button;
    ImageView ivProfileImageOptions;
    TextView info;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        ivProfileImageOptions = findViewById(R.id.ivProfileImageOptions);
        fb_login_button = findViewById(R.id.fb_login_button);
        info = findViewById(R.id.info);

        callbackManager = CallbackManager.Factory.create();
        fb_login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               // Log.i("OptionActivity", "user id: " + loginResult.getAccessToken().getUserId());
                info.setText("User id: " + loginResult.getAccessToken().getUserId());
                String imageUrl = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?return_ssl_resources=1";
                Picasso.get().load(imageUrl).into(ivProfileImageOptions);
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");

            }

            @Override
            public void onError(FacebookException error) {
                info.setText("Login attempt failed.");

            }
        });

        btnCommonRoomChat = findViewById(R.id.btnCommonRoomChat);
        btnCommonRoomChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OptionActivity.this, ChatActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}