package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Verifyotp extends AppCompatActivity {
    private EditText inputcode1, inputcode2, inputcode3,inputcode4,inputcode5,inputcode6;
    private String verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);
        TextView textmobile=findViewById(R.id.textmobile);
        textmobile.setText(String.format("+91-%s", getIntent().getStringExtra("mobile")));
        inputcode1=findViewById(R.id.inputcode1);
        inputcode2=findViewById(R.id.inputcode2);
        inputcode3=findViewById(R.id.inputcode3);
        inputcode4=findViewById(R.id.inputcode4);
        inputcode5=findViewById(R.id.inputcode5);
        inputcode6=findViewById(R.id.inputcode6);

        setupOTPInput();
        final ProgressBar progressBar=findViewById(R.id.progressbar);
        final Button buttonverify=findViewById(R.id.btnverifyotp);
        verificationId=getIntent().getStringExtra("verificationid");
        buttonverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Verifyotp.this, Adminchangepassword.class);
                startActivity(intent);
                Toast.makeText(Verifyotp.this,"Code is correct",Toast.LENGTH_SHORT).show();
          //      Toast.makeText(Verifyotp.this,"valid code",Toast.LENGTH_SHORT).show();
               /* if(inputcode1.getText().toString().trim().isEmpty()||
                        inputcode2.getText().toString().trim().isEmpty()||
                        inputcode3.getText().toString().trim().isEmpty()||
                        inputcode4.getText().toString().trim().isEmpty()||
                        inputcode5.getText().toString().trim().isEmpty()||
                        inputcode6.getText().toString().trim().isEmpty()){
                    Toast.makeText(Verifyotp.this,"Please Enter Valid Code",Toast.LENGTH_SHORT).show();
                    return;

                }*/
            /*   String code=inputcode1.getText().toString()+
                        inputcode2.getText().toString()+
                        inputcode3.getText().toString()+
                        inputcode4.getText().toString()+
                        inputcode5.getText().toString()+
                        inputcode6.getText().toString();
               if(code.equals(verificationId))
               {
                   Intent intent=new Intent(Verifyotp.this, Adminchangepassword.class);
                   startActivity(intent);
                   Toast.makeText(Verifyotp.this,"Code is correct",Toast.LENGTH_SHORT).show();

               }
               else
               {
                   Toast.makeText(Verifyotp.this,"please Enter valid code",Toast.LENGTH_SHORT).show();
               }*/
               /* if(verificationId!=null)
                {
                    progressBar.setVisibility(View.VISIBLE);
                    buttonverify.setVisibility(View.INVISIBLE);*/
                    /*PhoneAuthCredential phoneauthcredential=PhoneAuthProvider.getCredential(verificationId,code);
                    FirebaseAuth.getInstance().signInWithCredential(phoneauthcredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            buttonverify.setVisibility(View.VISIBLE);*/
                         /*   if(code.equals(verificationId)){
                                //Intent intent=new Intent(getApplicationContext(),Sendotp.class);
                                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                               // startActivity(intent);
                                Toast.makeText(Verifyotp.this,"valid code",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Verifyotp.this,"The verification code entered was Invalid",Toast.LENGTH_SHORT).show();
                            }
                        }*/

            }

        });
    }
    private void setupOTPInput(){

        inputcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty());
                inputcode2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty());
                inputcode3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty());
                inputcode4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty());
                inputcode5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty());
                inputcode6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
}
