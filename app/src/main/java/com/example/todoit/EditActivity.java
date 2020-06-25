package com.example.todoit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditActivity extends AppCompatActivity {
    EditText titleplan, descplan, dateplan;
    Button btnUpdate, btnDelete;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        titleplan = findViewById(R.id.titleplan);
        descplan = findViewById(R.id.descplan);
        dateplan = findViewById(R.id.dateplan);


        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        //get values
        titleplan.setText(getIntent().getStringExtra("titleplan"));
        descplan.setText(getIntent().getStringExtra("descplan"));
        dateplan.setText(getIntent().getStringExtra("dateplan"));
        final String keyplan = getIntent().getStringExtra("key");

        reference = FirebaseDatabase.getInstance().getReference().child("Plan").child("Todo" + keyplan);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){

                           Intent a = new Intent (EditActivity.this, MainActivity.class);
                           startActivity(a);
                       }else{
                           Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                       }
                   }
               });

            }
        });
        // handling event button
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("titleplan").setValue(titleplan.getText().toString());
                        dataSnapshot.getRef().child("descplan").setValue(descplan.getText().toString());
                        dataSnapshot.getRef().child("dateplan").setValue(dateplan.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(keyplan);

                        Intent a = new Intent (EditActivity.this, MainActivity.class);
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
