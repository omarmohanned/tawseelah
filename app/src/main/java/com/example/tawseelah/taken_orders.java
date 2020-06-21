package com.example.tawseelah;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class taken_orders extends AppCompatActivity {
    private TextView taken_orders;
    private DatabaseReference databaseReference;
    private ArrayList<get_all_orders_all_users> list;
    private FirebaseUser firebaseUser;
    private  adapter_for_res_orders adapter;
    private RecyclerView taken_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_orders);
        taken_rec=findViewById(R.id.taken_rec);
        taken_rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        list = new ArrayList<get_all_orders_all_users>();
        taken_orders = findViewById(R.id.num_of_taken);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Requests").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String stat;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    stat = String.valueOf(ds.child("meal_states").getValue());
                    if (stat.equals("taken")) {
                        list.add(ds.getValue(get_all_orders_all_users.class));

                    }
                    adapter=new  adapter_for_res_orders(getApplicationContext(),list);
                    taken_rec.setAdapter(adapter);
                    String count= (String.valueOf(list.size()));
                    taken_orders.setText("Number of taken orders : "+count);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
