package com.bhavikateli.collab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.File;
import java.io.IOException;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class EditProfileActivity extends AppCompatActivity {

    public static final int PICK_PHOTO_CODE = 98;
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_SIGN_UP = 34;
    public String photoFileName = "photo.jpg";
    private File photoFile;
    ImageView ivPreviewProfileChange;
    EditText etChangeUsername;
    EditText etChangePassword;
    EditText etChangeEmail;
    Button btnChangeProfilePictureCamera;
    Button btnChangeProfilePictureGallery;
    EditText etChangeProfileDescription;
    ParseUser user = ParseUser.getCurrentUser();
    Button btnConfirmChange;
    String username;
    String password;
    String profileDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etChangeUsername = findViewById(R.id.etChangeUsername);
        etChangePassword = findViewById(R.id.etChangePassword);
        etChangeEmail = findViewById(R.id.etChangeEmail);
        btnChangeProfilePictureCamera = findViewById(R.id.btnChangeProfilePictureCamera);
        btnChangeProfilePictureGallery = findViewById(R.id.btnChangeProfilePictureGallery);
        etChangeProfileDescription = findViewById(R.id.etChangeProfileDescription);
        btnConfirmChange = findViewById(R.id.btnConfirmChange);
        ivPreviewProfileChange = findViewById(R.id.ivPreviewProfileChange);

        etChangeUsername.setText(user.getUsername());
        ParseFile profileImage = user.getParseFile("profilePicture");
        Glide.with(this)
                .load(profileImage.getUrl())
                .transform(new RoundedCornersTransformation(100, 20))
                .into(ivPreviewProfileChange);

        String email = user.getEmail();
        Log.i("EditProfileActivity", "this is the email " + email);
        if(email != null){
            etChangeEmail.setText(email);
        }
        etChangeProfileDescription.setText(user.getString("profileDescription"));

        username = etChangeUsername.getText().toString();
        password = etChangePassword.getText().toString();
        profileDescription = etChangeProfileDescription.getText().toString();

        btnConfirmChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setUsername(username);
                if(password != null) {
                    user.setPassword(password);
                }
                user.put("profileDescription", profileDescription);
                ParseFile finalProfilePicture = new ParseFile(photoFile);
                user.put("profilePicture", finalProfilePicture);
                try {
                    finalProfilePicture.save();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                etChangeUsername.setText("");
                etChangePassword.setText("");
                etChangeProfileDescription.setText("");
                ivPreviewProfileChange.setImageResource(0);
            }
        });

        btnChangeProfilePictureCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera();
            }
        });

        btnChangeProfilePictureGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPickPhoto(view);

            }
        });

    }

    private void onPickPhoto(View view) {
        // Create intent for picking a photo from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }
    }

    public Bitmap loadFromUri(Uri photoUri) {
        Bitmap image = null;
        try {
            // check version of Android on device
            if(Build.VERSION.SDK_INT > 27){
                // on newer versions of Android, use the new decodeBitmap method
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), photoUri);
                image = ImageDecoder.decodeBitmap(source);
            } else {
                // support older versions of Android by using getBitmap
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }



    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(EditProfileActivity.this, "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(EditProfileActivity.this.getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_SIGN_UP);
        }
    }

    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(EditProfileActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "EditTextActivity");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d("EditTextActivity", "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + fileName);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_SIGN_UP) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                Glide.with(EditProfileActivity.this).load(takenImage).apply(new RequestOptions().override(100,100)).into(ivPreviewProfileChange);
            } else { // Result was a failure
                Toast.makeText(EditProfileActivity.this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }

        else if ((data != null) && requestCode == PICK_PHOTO_CODE) {
            Uri photoUri = data.getData();

            // Load the image located at photoUri into selectedImage
            Bitmap selectedImage = loadFromUri(photoUri);

            // Load the selected image into a preview
            ImageView ivPreview = (ImageView) findViewById(R.id.ivProfilePreviewSignUp);
            ivPreviewProfileChange.setImageBitmap(selectedImage);
        }
    }
}