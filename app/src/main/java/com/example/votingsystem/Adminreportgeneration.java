package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Adminreportgeneration extends AppCompatActivity {
TextView t1,t2,t3,t4,textcanpage;
    FirebaseDatabase database;
    DatabaseReference ref,ref1,ref2;
    int i,j,k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminreportgeneration);
        database= FirebaseDatabase.getInstance();
        ref=database.getReference("groups");
        ref1=database.getReference("candidates");
        ref2=database.getReference("credentials").child("User");
        t2= (TextView) findViewById(R.id.txttotalgrp);
        t3= (TextView) findViewById(R.id.txttotalcan);
        t4= (TextView) findViewById(R.id.txttotaluser);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){

                    i = i+1;
                }

                t2.setText(Integer.toString(i));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){

                    j = j+1;
                }

                t3.setText(Integer.toString(j));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){

                    k = k+1;
                }

                t4.setText(Integer.toString(k));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        t1= (TextView) findViewById(R.id.txtgrpshowreport);
        t1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminreportgeneration.this, Reportgroupshow.class);
                startActivity(intent);

            }
        });
        textcanpage= (TextView) findViewById(R.id.candshowtext);
        textcanpage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminreportgeneration.this, Candidateshow.class);
                startActivity(intent);

            }
        });
    }
}
