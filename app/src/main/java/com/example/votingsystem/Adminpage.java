package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
public class Adminpage extends AppCompatActivity {
    public Button button1,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        button1= (Button) findViewById(R.id.cregrp);
        button2= (Button) findViewById(R.id.addcand);
        button3= (Button) findViewById(R.id.viewgrp);
        button4= (Button) findViewById(R.id.resgrp);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminpage.this, Admingroupadd.class);
                startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminpage.this, Admincandidateadd.class);
                startActivity(intent);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminpage.this, AdminGroupdatashow.class);
                startActivity(intent);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminpage.this, Adminreportgeneration.class);
                startActivity(intent);

            }
        });
    }
}
