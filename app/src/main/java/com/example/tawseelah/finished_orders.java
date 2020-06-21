package com.example.tawseelah;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class finished_orders extends AppCompatActivity {
    String stat;
    private DatabaseReference databaseReference;
    private ArrayList<get_all_orders_all_users> list3;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_orders);
        recyclerView = findViewById(R.id.finish_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Requests").child(firebaseUser.getUid());
        list3 = new ArrayList<get_all_orders_all_users>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    stat = String.valueOf(ds.child("meal_states").getValue());
                    if (stat.equals("finish")) {
                        list3.add(ds.getValue(get_all_orders_all_users.class));
                    }


                }
                adapter_for_res_orders adapter = new adapter_for_res_orders(getApplicationContext(), list3);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
