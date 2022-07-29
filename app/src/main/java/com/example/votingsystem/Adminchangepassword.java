package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adminchangepassword extends AppCompatActivity {
EditText etusername,etpass,etconpass;
String username,password,conpass;
Button btnconfirm;
    boolean isAllFieldsChecked = false;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminchangepassword);
        etusername=(EditText)findViewById(R.id.adminuname);
        etpass=(EditText)findViewById(R.id.new_pass);
        etconpass=(EditText)findViewById(R.id.confirm_pass);
        btnconfirm=(Button) findViewById(R.id.confirmbtn);
        username =etusername.getText().toString();
        password =etpass.getText().toString();
        conpass =etconpass.getText().toString();
        reference= FirebaseDatabase.getInstance().getReference("credentials").child("Admin").child("gayatri");
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    if (password.equals(conpass)) {
                        reference.child("pass").setValue(password);
                        Intent intent = new Intent(Adminchangepassword.this, AdminLogin.class);
                        startActivity(intent);
                    } else {
                        etconpass.setError("Password not match");
                    }
                }
            }
        });

    }
    private boolean CheckAllFields() {
        if (etusername.length() == 0) {
            etusername.setError("Username is required");
            return false;
        }

        if (etpass.length() == 0) {
            etpass.setError("Password is required");
            return false;
        } else if (etpass.length() < 8) {
            etpass.setError("Password must be minimum 8 characters");
            return false;
        }
        if (etconpass.length() == 0) {
            etconpass.setError("Confirm password");
            return false;
        }


        // after all validation return true.
        return true;
    }
}
