package com.example.tawseelah;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

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

public class all_drivers extends AppCompatActivity {
    DatabaseReference databaseReference;
    ArrayList<get_driver_info_from_data_dase> list;
    RecyclerView rec1;
    private SearchView search;
    private String search1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_drivers);
        rec1 = findViewById(R.id.recy);
        search = findViewById(R.id.search);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Driver");
        rec1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list = new ArrayList<get_driver_info_from_data_dase>();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                Toast.makeText(getApplicationContext(), "searching for: " + s, Toast.LENGTH_LONG).show();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            search1 = String.valueOf(ds.child("phonenumber").getValue());
                            if (search1.contains(s)) {
                                list.add(ds.getValue(get_driver_info_from_data_dase.class));

                            }
                        }
                        adapter_driver adapter_driver = new adapter_driver(list);
                        rec1.setAdapter(adapter_driver);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(getApplicationContext(), "changing Text", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        list.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    list.add(ds.getValue(get_driver_info_from_data_dase.class));
                }
                adapter_driver adapter_driver = new adapter_driver(list);
                rec1.setAdapter(adapter_driver);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
