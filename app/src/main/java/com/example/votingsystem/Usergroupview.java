package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Usergroupview extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Groupdataholder grp;
    Button b1;
    TextView t1;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usergroupview);
       t1 = (TextView)findViewById(R.id.usernameget);
        t1.setText(String.format("%s", getIntent().getStringExtra("userusername")));
        uid=t1.getText().toString();
        grp=new Groupdataholder();
        listView=(ListView)findViewById(R.id.listviewgrp);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("groups");
        ref1=database.getReference("credentials").child("User").child(uid);;
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.usergroup,R.id.txtusergrpname, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    grp=ds.getValue(Groupdataholder.class);
                    list.add(grp.getName().toString()+" \n"+grp.getDesc().toString());

                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void linearclick(View view)
    {
        ref1.addListenerForSingleValueEvent(listener);

    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                String value = snapshot.child("canset").getValue(String.class);
                if (value.equals("1")) {
                    Intent intent = new Intent(Usergroupview.this, Alreadyvotedpage.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(Usergroupview.this, Votecasting.class);
                    intent.putExtra("usernamefromfrpview", t1.getText().toString());
                    startActivity(intent);
                }
            }
        }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(Usergroupview.this,error.toString(),Toast.LENGTH_SHORT).show();
        }

    };
    }

