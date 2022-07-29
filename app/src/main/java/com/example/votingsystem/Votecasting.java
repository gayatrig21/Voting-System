package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Votecasting extends AppCompatActivity {
    Button b1, b2, b3;
    LinearLayout l1, l2, l3;
    EditText euid;
    String uid;
    TextView t2;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votecasting);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        l1 = findViewById(R.id.user_candidate_linear1);
        l2 = findViewById(R.id.user_candidate_linear2);
        l3 = findViewById(R.id.user_candidate_linear3);
        t2 = findViewById(R.id.userid);
        t2.setText(String.format("%s", getIntent().getStringExtra("usernamefromfrpview")));
       uid =t2.getText().toString();
        reference= FirebaseDatabase.getInstance().getReference("credentials").child("User").child(uid);
        Userdata u1 = new Userdata();
        b1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                b1.setBackgroundColor(Color.GREEN);
               // reference.child("User").child(uid).addListenerForSingleValueEvent(listener);
                //reference.child("").setValue("1");
                reference.child("canset").setValue("1");
                //b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b2.setBackgroundColor(Color.GRAY);
                b3.setBackgroundColor(Color.GRAY);
                Toast.makeText(Votecasting.this,"Thank you for voting",Toast.LENGTH_SHORT).show();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    b2.setBackgroundColor(Color.GREEN);
                     reference.child("canset").setValue("1");
                     b1.setEnabled(false);
                     b3.setEnabled(false);
                     b1.setBackgroundColor(Color.GRAY);
                     b3.setBackgroundColor(Color.GRAY);
                Toast.makeText(Votecasting.this,"Thank you for voting",Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  b3.setBackgroundColor(Color.GREEN);
                reference.child("canset").setValue("1");
                  b1.setEnabled(false);
                  b2.setEnabled(false);
                  b2.setBackgroundColor(Color.GRAY);
                  b1.setBackgroundColor(Color.GRAY);
                Toast.makeText(Votecasting.this,"Thank you for voting",Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                reference.child("canset").setValue("1");


                }
            }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(Votecasting.this, "data not find", Toast.LENGTH_SHORT).show();
        }
    };
*/
    }