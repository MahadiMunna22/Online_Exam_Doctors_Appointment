package com.example.a201714032;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class History extends AppCompatActivity  {

    private ListView listView;
    DatabaseReference databaseReference;
    private List<StoreResult> resultList;
    private CustomAdapter customAdapter;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("Basic History");

        databaseReference = FirebaseDatabase.getInstance().getReference("Appointment History");
        resultList = new ArrayList<>();

        customAdapter = new CustomAdapter(History.this, resultList);

        listView = findViewById(R.id.listViewId);


    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                resultList.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    StoreResult storeResult = dataSnapshot1.getValue(StoreResult.class) ;
                    resultList.add(storeResult);
                }
                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }


}
