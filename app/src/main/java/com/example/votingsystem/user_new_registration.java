package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_new_registration extends AppCompatActivity {
    Button BSelectImage;
    EditText etname,etmobno,etdob,etadd,etuid,etpass,etconfirmpass;
    String name,mobno,dob,add,uid,pass,confirmpass,canset="0";
private DatabaseReference reference;
    ImageView IVPreviewImage;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int CAMERA_PERM_CODE = 101;
    //ImageView selectedImage;
    Button camerabutton;
    Bitmap image;
    boolean isAllFieldsChecked = false;
    // constant to compare
    // the activity result code
    int SELECT_PICTURE=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_new_registration);
        reference= FirebaseDatabase.getInstance().getReference("credentials");
        BSelectImage = findViewById(R.id.user_nr_selectfile);
        IVPreviewImage = findViewById(R.id.user_nr_imageview);
        etname = findViewById(R.id.user_nr_name);
        etmobno = findViewById(R.id.user_nr_mob_no);
        etdob = findViewById(R.id.user_nr_dob);
        etadd = findViewById(R.id.user_nr_address);
        etuid = findViewById(R.id.user_nr_uniqueid);
        etpass = findViewById(R.id.user_nr_password);
        etconfirmpass = findViewById(R.id.user_nr_confirm);
        //selectedImage = findViewById(R.id.display_imgview);
        camerabutton = findViewById(R.id.user_nr_capturefile);
        camerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermissions();
            }
        });
        // handle the Choose Image button to trigger
        // the image chooser function
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {


            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    //IVPreviewImage.setImageResource(android.R.color.transparent);
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
        if (requestCode == CAMERA_REQUEST_CODE)
            image = (Bitmap) data.getExtras().get("data");
        IVPreviewImage.setImageBitmap(image);
    }

    public void newuserreg(View view)
    {
        isAllFieldsChecked = CheckAllFields();

        if(isAllFieldsChecked) {
            confirmpass = etconfirmpass.getText().toString();
            name = etname.getText().toString();
            mobno = etmobno.getText().toString();
            add = etadd.getText().toString();
            dob = etdob.getText().toString();
            uid = etuid.getText().toString();
            pass = etpass.getText().toString();
            if(pass.equals(confirmpass)) {
                Userdata u1 = new Userdata(name, mobno, dob, add, uid, pass,canset);
                reference.child("User").child(uid).setValue(u1).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(user_new_registration.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(user_new_registration.this, "Data is Saved", Toast.LENGTH_SHORT).show();
                            }
                        });
                etname.setText(" ");
                etmobno.setText(" ");
                etdob.setText(" ");
                etadd.setText(" ");
                etuid.setText(" ");
                etpass.setText(" ");
                etconfirmpass.setText(" ");
            }
            else
            {
                etconfirmpass.setError("password not match");
            }
        }
    }
    private boolean CheckAllFields() {
        if (etname.length() == 0) {
            etname.setError("Name is required");
            return false;
        }

        if (etmobno.length() == 0) {
            etmobno.setError("Mobile number is required");
            return false;
        }
        if (etdob.length() == 0) {
            etdob.setError("Date of Birth is required");
            return false;
        }
        if (etadd.length() == 0) {
            etadd.setError("Address is required");
            return false;
        }
        if (etpass.length() == 0) {
            etpass.setError("Password is required");
            return false;
        }
        if (etuid.length() == 0) {
            etuid.setError("Unique Id is required");
            return false;
        }
        if (etconfirmpass.length() == 0) {
            etconfirmpass.setError("Confirm Your password");
            return false;
        }

        return true;
    }
    public void askCameraPermissions() {
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



    }



