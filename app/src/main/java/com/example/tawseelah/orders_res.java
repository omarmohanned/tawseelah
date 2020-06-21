package com.example.tawseelah;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

public class orders_res extends AppCompatActivity {
    private TextView name_res_view1, restaurant_place, owner_name, email_address1, phone_number1;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference, restaurant_place1, owner_name1, email_address11, phone_number11, name_res_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_res);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        name_res_view1 = findViewById(R.id.name_res_view);
        restaurant_place = findViewById(R.id.restaurant_place);
        owner_name = findViewById(R.id.owner_name);
        email_address1 = findViewById(R.id.email_address1);
        phone_number1 = findViewById(R.id.phone_number1);
        restaurant_place1 = databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("restaurantplace2");

        owner_name1 = databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("firstname2");

        email_address11 = databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("emailaddress");

        phone_number11 = databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("phonenumber");
        name_res_view=databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("restaurantname2");

        restaurant_place1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue(String.class);
                restaurant_place.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        owner_name1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue(String.class);
                owner_name.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        email_address11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue(String.class);
                email_address1.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        phone_number11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue(String.class);
                phone_number1.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        name_res_view.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue(String.class);
                name_res_view1.setText(a);}


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
