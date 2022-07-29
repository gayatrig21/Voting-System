package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;


import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

public class user_login extends AppCompatActivity {
    public Button login_button, nr_button;
    EditText etuid, etpass;
    String uid, pass;
    DatabaseReference reference;
   // public static final int CAMERA_REQUEST_CODE = 102;
   // public static final int CAMERA_PERM_CODE = 101;
    /*ImageView selectedImage;
    Button camerabutton;
    Bitmap image;*/
    boolean isAllFieldsChecked = false;
    boolean fingerprintflag= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        etuid = findViewById(R.id.user_username);
        etpass = findViewById(R.id.user_password);
        reference = FirebaseDatabase.getInstance().getReference("credentials");
        TextView msgtex = findViewById(R.id.msgtext);
        final Button loginbutton = findViewById(R.id.fingerprintbutton);

        // creating a variable for our BiometricManager
        // and lets check if our user can use biometric sensor or not
        BiometricManager biometricManager = androidx.biometric.BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {

            // this means we can use biometric sensor
            case BiometricManager.BIOMETRIC_SUCCESS:
                msgtex.setText("You can use the fingerprint sensor to login");
                msgtex.setTextColor(Color.parseColor("#fafafa"));

                break;

            // this means that the device doesn't have fingerprint sensor
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                msgtex.setText("This device doesnot have a fingerprint sensor");
                loginbutton.setVisibility(View.GONE);
                fingerprintflag= true;
                break;

            // this means that biometric sensor is not available
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                msgtex.setText("The biometric sensor is currently unavailable");
                loginbutton.setVisibility(View.GONE);
                fingerprintflag= true;
                break;

            // this means that the device doesn't contain your fingerprint
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                msgtex.setText("Your device doesn't have fingerprint enrolled");
                loginbutton.setVisibility(View.GONE);
                fingerprintflag= true;
                break;
        }
        // creating a variable for our Executor
        Executor executor = ContextCompat.getMainExecutor(this);
        // this will give us result of AUTHENTICATION
        final BiometricPrompt biometricPrompt = new BiometricPrompt(user_login.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            // THIS METHOD IS CALLED WHEN AUTHENTICATION IS SUCCESS
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Fingerprint verified success", Toast.LENGTH_SHORT).show();
                loginbutton.setText("Verified");
                loginbutton.setBackgroundColor(Color.GREEN);
                fingerprintflag= true;
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        // creating a variable for our promptInfo
        // BIOMETRIC DIALOG
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Voting System")
                .setDescription("Use your fingerprint to login ").setNegativeButtonText("Cancel").build();
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);

            }
        });
       // selectedImage = findViewById(R.id.display_imgview);
       // camerabutton = findViewById(R.id.btn_takephoto);
        login_button = (Button) findViewById(R.id.btn_login);
        nr_button = (Button) findViewById(R.id.user_login_nr);



        nr_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_login.this, user_new_registration.class);
                startActivity(intent);

            }
        });


/*        camerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermissions();
            }
        });

*/
    }

  /*  public void askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {

            openCamera();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestcode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestcode, permissions, grantResults);
        if (requestcode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED) {
                openCamera();
            }
        } else {
            Toast.makeText(this, "Camera permission is required to use camera", Toast.LENGTH_SHORT).show();

        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE)
            image = (Bitmap) data.getExtras().get("data");
        selectedImage.setImageBitmap(image);
    }
*/
    public void login(View view) {
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked && fingerprintflag) {
            uid = etuid.getText().toString();
            pass = etpass.getText().toString();
            reference.child("User").child(uid).addListenerForSingleValueEvent(listener);
        }
    }
    private boolean CheckAllFields() {
        if (etuid.length() == 0) {
            etuid.setError("Username is required");
            return false;
        }

        if (etpass.length() == 0) {
            etpass.setError("Password is required");
            return false;
        }
        return true;
    }


    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                String password = snapshot.child("pass").getValue(String.class);
                if (password.equals(pass)) {
                    Intent intent = new Intent(user_login.this, Usergroupview.class);
                    intent.putExtra("userusername",etuid.getText().toString());
                    startActivity(intent);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
Toast.makeText(user_login.this,error.toString(),Toast.LENGTH_SHORT).show();
        }

    };
}

