package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    public Button button,user_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.adminbtn);
        user_button= (Button) findViewById(R.id.userbtn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AdminLogin.class);
                startActivity(intent);

            }
        });
        user_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, user_login.class);
                startActivity(intent);

            }
        });
    }
}
