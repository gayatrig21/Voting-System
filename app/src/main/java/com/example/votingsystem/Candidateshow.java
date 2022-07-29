package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Candidateshow extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Candidatedataholder cgrp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidateshow);
        cgrp=new Candidatedataholder();
        listView=(ListView)findViewById(R.id.listviewcandreport);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("candidates");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.candidatedata,R.id.txtusercandname, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    cgrp=ds.getValue(Candidatedataholder.class);
                    list.add(cgrp.getCname().toString()+" \n"+cgrp.getCdesc().toString());
                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
