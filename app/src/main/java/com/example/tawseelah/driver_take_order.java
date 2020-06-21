package com.example.tawseelah;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class driver_take_order extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    String stat;
    ArrayList<get_all_orders_all_users> list3;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_take_order);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("all_Requests");
        recyclerView = findViewById(R.id.driver_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list3 = new ArrayList<get_all_orders_all_users>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    stat = String.valueOf(ds.child("meal_states").getValue());
                    if (stat.equals("waiting")) {
                        list3.add(ds.getValue(get_all_orders_all_users.class));
                    }
                }
                adapter_for_all_aorders_all_users adapter = new adapter_for_all_aorders_all_users(getApplicationContext(), list3);
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
