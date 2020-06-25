package com.example.todoit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView subtitle, header, footer;
    Button btnAddNew;

    DatabaseReference reference;
    RecyclerView plan;
    ArrayList<myPlan> list;
    PlanAdapter planAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        header = findViewById(R.id.header);
        subtitle = findViewById(R.id.subtitle);
        footer = findViewById(R.id.footer);
        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,AddnewActivity.class);
                startActivity(a);
            }
        });

        //working with data
        plan= findViewById(R.id.plan);
        plan.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<myPlan>();

        // Get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Plan");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            // Set code to retrive data and replace layout
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    myPlan p = dataSnapshot1.getValue(myPlan.class);
                    list.add(p);
                }
                planAdapter = new PlanAdapter(MainActivity.this, list);
                plan.setAdapter(planAdapter);
                planAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // set code to show an error
                Toast.makeText(getApplicationContext() ,"No Data",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
