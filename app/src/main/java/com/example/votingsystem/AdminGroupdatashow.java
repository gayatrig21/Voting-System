package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminGroupdatashow extends AppCompatActivity {
ListView listView;
FirebaseDatabase database;
DatabaseReference ref;
ArrayList<String> list;
ArrayAdapter<String> adapter;
Groupdataholder grp;
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_groupdatashow);
        grp=new Groupdataholder();
        listView=(ListView)findViewById(R.id.listviewgrp);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("groups");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.groupdata,R.id.txtgrpname, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    grp=ds.getValue(Groupdataholder.class);
                    list.add(grp.getName().toString()+" \n"+grp.getDesc().toString()+" \n"+ds.getKey());

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
        Intent intent=new Intent(AdminGroupdatashow.this, Candidateshow.class);
        startActivity(intent);
    }
    public void Updatedata(View view){
        /*//listView=(ListView)findViewById(R.id.listviewgrp);
       /* for(int i=0;i<listView.getChildCount();i++)
        {
            listView.getChildAt(i).setBackgroundColor(Color.BLUE);
        }*/
        /*LinearLayout ll=(LinearLayout)view.getParent();

        b1= (Button) findViewById(R.id.updatebtn);
        b1.setText("Clicked");
        ll.setBackgroundColor(Color.CYAN);
        ll.refreshDrawableState();*/
        /*
        b1.setEnabled(false);
        b1.setBackgroundResource(R.color.colorAccent);*/
    }
}
