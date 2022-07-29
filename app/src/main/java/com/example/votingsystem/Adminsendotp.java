package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;

import java.util.concurrent.TimeUnit;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;


public class Adminsendotp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminsendotp);
        final EditText inputMobile=findViewById(R.id.inputmobile);
        Button buttongetotp=findViewById(R.id.btnsendotp);
        final ProgressBar progressbar=findViewById(R.id.progressbar);

        buttongetotp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(inputMobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(Adminsendotp.this, "Enter Mobile", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);
                buttongetotp.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+inputMobile.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        Adminsendotp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressbar.setVisibility(View.GONE);
                                buttongetotp.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressbar.setVisibility(View.GONE);
                                buttongetotp.setVisibility(View.VISIBLE);
                                Toast.makeText(Adminsendotp.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationid, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                progressbar.setVisibility(View.GONE);
                                buttongetotp.setVisibility(View.VISIBLE);
                                Intent intent=new Intent(getApplicationContext(), Verifyotp.class);
                                intent.putExtra("mobile",inputMobile.getText().toString());
                                intent.putExtra("verificationid",verificationid);
                                startActivity(intent);

                            }
                        }
                );


            }
        });
    }
}
