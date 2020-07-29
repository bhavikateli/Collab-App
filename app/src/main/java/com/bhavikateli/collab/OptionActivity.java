package com.bhavikateli.collab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OptionActivity extends AppCompatActivity {

    Button btnCommonRoomChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        btnCommonRoomChat = findViewById(R.id.btnCommonRoomChat);
        btnCommonRoomChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OptionActivity.this, ChatActivity.class);
                startActivity(i);
            }
        });
    }
}