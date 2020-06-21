package com.example.tawseelah;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class waiting_orders extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private ArrayList<get_all_orders_all_users> list2;
    private String stat;

    private adapter_for_res_orders adapter_for_res_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_orders);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Requests").child(firebaseUser.getUid());
        recyclerView = findViewById(R.id.waiting_orders_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list2 = new ArrayList<get_all_orders_all_users>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (!ds.exists()) {
                        Toast.makeText(getApplicationContext(), "no waiting orders is here", Toast.LENGTH_LONG).show();
                    }
                    stat = String.valueOf(ds.child("meal_states").getValue());
                    if (stat.equals("waiting")) {
                        list2.add(ds.getValue(get_all_orders_all_users.class));
                    }
                }


                adapter_for_res_orders adapter = new adapter_for_res_orders(getApplicationContext(), list2);
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "no data is here", Toast.LENGTH_LONG).show();
            }
        });

    }
}
