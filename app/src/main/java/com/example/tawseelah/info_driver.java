package com.example.tawseelah;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

public class info_driver extends AppCompatActivity {
    private TextView name_driver_view, secondname1, car_color1, car_type1, email_address1, phone_number1, sum_bill, cash;
    private DatabaseReference firstname, databaseReference, secondname, car_color, car_type, email_adddress, phone_number, sum_bill1, cash1;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_driver);
        name_driver_view = findViewById(R.id.name_driver_view);
        secondname1 = findViewById(R.id.second_name);
        car_color1 = findViewById(R.id.car_color1);
        car_type1 = findViewById(R.id.car_type1);
        email_address1 = findViewById(R.id.email_address1);
        phone_number1 = findViewById(R.id.phone_number1);
        sum_bill = findViewById(R.id.sum_bill);
        cash = findViewById(R.id.cash);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        firstname = databaseReference.child("Driver").child(firebaseUser.getUid()).child("firstname");
        secondname = databaseReference.child("Driver").child(firebaseUser.getUid()).child("secondname");
        car_color = databaseReference.child("Driver").child(firebaseUser.getUid()).child("carcolor");
        car_type = databaseReference.child("Driver").child(firebaseUser.getUid()).child("cartype");
        email_adddress = databaseReference.child("Driver").child(firebaseUser.getUid()).child("emailaddress");
        phone_number = databaseReference.child("Driver").child(firebaseUser.getUid()).child("phonenumber");
        sum_bill1 = databaseReference.child("Driver").child(firebaseUser.getUid()).child("SumAllbill");
        cash1 = databaseReference.child("Driver").child(firebaseUser.getUid()).child("cashdesired");


        sum_bill1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sum_bill.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        cash1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cash.setText(String.valueOf(dataSnapshot.getValue(String.class)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firstname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue(String.class);
                name_driver_view.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "There is no data", Toast.LENGTH_LONG).show();
            }
        });
        secondname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue(String.class);
                secondname1.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "There is no data", Toast.LENGTH_LONG).show();
            }
        });
        car_color.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue(String.class);
                car_color1.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        car_type.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                car_type1.setText(s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        email_adddress.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                email_address1.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        phone_number.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                phone_number1.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
