package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;


public class Admincandidateadd extends AppCompatActivity {
    EditText caname,caadd,camob,cadesc,cagrpid,caid;
    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincandidateadd);
        caid=(EditText)findViewById(R.id.candid);
        caname=(EditText)findViewById(R.id.candname);
        caadd=(EditText)findViewById(R.id.candadd);
        camob=(EditText)findViewById(R.id.candmob);
        cadesc=(EditText)findViewById(R.id.canddesc);
        cagrpid=(EditText)findViewById(R.id.candgrp);
    }
    public void process1(View view)
    {

        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            String id = caid.getText().toString().trim();
            String cname = caname.getText().toString().trim();
            String cdesc = cadesc.getText().toString().trim();
            String cadd = caadd.getText().toString().trim();
            String cmob = camob.getText().toString().trim();
            String grpid = cagrpid.getText().toString().trim();

            Candidatedataholder obj = new Candidatedataholder(cname, cadd, cmob, cdesc, grpid);
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference node = db.getReference("candidates");

            node.child(id).setValue(obj);
            caid.setText("");
            caname.setText("");
            caadd.setText("");
            camob.setText("");
            cadesc.setText("");
            cagrpid.setText("");
        }

    }
    private boolean CheckAllFields() {
        if (caid.length() == 0) {
            caid.setError("ID is required");
            return false;
        }

        if (caname.length() == 0) {
            caname.setError("Name is required");
            return false;
        }
        if (caadd.length() == 0) {
            caadd.setError("Address is required");
            return false;
        }
        if (camob.length() == 0) {
            camob.setError("Mobile no is required");
            return false;
        }

        if (cadesc.length() == 0) {
            cadesc.setError("Description is required");
            return false;
        }
        if (cagrpid.length() == 0) {
            cagrpid.setError("Group ID is required");
            return false;
        }



        // after all validation return true.
        return true;
    }
}
