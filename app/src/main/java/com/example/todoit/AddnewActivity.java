package com.example.todoit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class AddnewActivity extends AppCompatActivity {
    TextView header, titleActivity,descActivity, timeActivity;
    EditText titleplan, descplan, dateplan;
    Button btnSave, btnCancel;

    DatabaseReference reference;
    Integer id = new Random().nextInt();
    String key = Integer.toString(id);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);

        header = findViewById(R.id.header);
        titleActivity = findViewById(R.id.descActivity);
        timeActivity = findViewById(R.id.timeActivity);

        titleplan= findViewById(R.id.titleplan);
        descplan = findViewById(R.id.descplan);
        dateplan = findViewById(R.id.dateplan);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(AddnewActivity.this,MainActivity.class);
                startActivity(aa);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert into database
                reference = FirebaseDatabase.getInstance().getReference().child("Plan").child("Todo" + id);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("title").setValue(titleplan.getText().toString());
                        dataSnapshot.getRef().child("description").setValue(descplan.getText().toString());
                        dataSnapshot.getRef().child("date").setValue(dateplan.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(key);

                        Intent a = new Intent (AddnewActivity.this, MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
