package com.bhavikateli.collab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    EditText etUsernameSignUp;
    EditText etPasswordSignUp;
    EditText etProfileDescriptionSignUp;
    Button btnCaptureImageSignUp;
    Button btnGalleryPictureSignUp;
    Button btnSubmitSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsernameSignUp = findViewById(R.id.etUsernameSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        etProfileDescriptionSignUp = findViewById(R.id.etProfileDescriptionSignUp);
        btnSubmitSignUp = findViewById(R.id.btnSubmitSignUp);
        
        btnSubmitSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Inside submit in button");
                String username = etUsernameSignUp.getText().toString();
                String password = etPasswordSignUp.getText().toString();
                String profileDescription = etProfileDescriptionSignUp.getText().toString();
                createUser(username, password, profileDescription);
            }
        });
    }

    private void createUser(String username, String password, String profileDescription) {
        // Create the ParseUser
        ParseUser user = new ParseUser();

        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.put("profileDescription", profileDescription);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "issue with sign up", e);
                    Toast.makeText(SignUpActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    goMainActivity();
                    Toast.makeText(SignUpActivity.this, "Success in signing up", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}