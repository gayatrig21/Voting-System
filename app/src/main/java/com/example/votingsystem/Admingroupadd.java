package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admingroupadd extends AppCompatActivity {
EditText gid,gname,gdesc;
boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admingroupadd);
        gid=(EditText)findViewById(R.id.grpid);
        gname=(EditText)findViewById(R.id.grpname);
        gdesc=(EditText)findViewById(R.id.grpdesc);
    }


    public void process(View view)
    {
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            String id = gid.getText().toString().trim();
            String name = gname.getText().toString().trim();
            String desc = gdesc.getText().toString().trim();

            Groupdataholder obj = new Groupdataholder(name, desc);
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference node = db.getReference("groups");

            node.child(id).setValue(obj);
            gid.setText("");
            gname.setText("");
            gdesc.setText("");
        }

    }
    private boolean CheckAllFields() {
        if (gid.length() == 0) {
            gid.setError("ID is required");
            return false;
        }

        if (gname.length() == 0) {
            gname.setError("Name is required");
            return false;
        }
        if (gdesc.length() == 0) {
            gdesc.setError("Description is required");
            return false;
        }



        // after all validation return true.
        return true;
    }
}
