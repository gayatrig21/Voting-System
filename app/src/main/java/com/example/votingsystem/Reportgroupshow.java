package com.example.votingsystem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Reportgroupshow extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Groupdataholder grp;

    TextView t2;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportgroupshow);
        grp=new Groupdataholder();
        listView=(ListView)findViewById(R.id.listviewgrpreport);
        t2=(TextView) findViewById(R.id.count);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("groups");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.groupreportshow,R.id.txtusergrpnamereport, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    grp=ds.getValue(Groupdataholder.class);
                    list.add(grp.getName().toString()+" \n"+grp.getDesc().toString());
                    i = i+1;
                }
                listView.setAdapter(adapter);
                t2.setText(Integer.toString(i));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
