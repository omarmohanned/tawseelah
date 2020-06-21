package com.example.tawseelah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view_order extends AppCompatActivity {
    private TextView res_name_confirm, cus_name_confirm, meal_name_confirm, meal_price_confirm, start_place_confirm, end_place_confirm, dilivary_bill, cash_desired;
    private Button take_order, view_place;
    private DatabaseReference databaseReference, databaseReference1, databaseReference2;
    private FirebaseUser firebaseUser;
    private Double all;
    private int num_of_taken_orders;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        view_place = findViewById(R.id.view_place);
        res_name_confirm = findViewById(R.id.res_name_confirm);
        cus_name_confirm = findViewById(R.id.cus_name_confirm);
        meal_name_confirm = findViewById(R.id.meal_name_confirm);
        meal_price_confirm = findViewById(R.id.meal_price_confirm);
        start_place_confirm = findViewById(R.id.start_place_confirm);
        end_place_confirm = findViewById(R.id.end_place_confirm);
        dilivary_bill = findViewById(R.id.dilivary_bill);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        take_order = findViewById(R.id.take_order);
        cash_desired = findViewById(R.id.cash_desired);


        final String res_name_data = getIntent().getStringExtra("res_name_data");
        res_name_confirm.setText("restaurant name:   " + res_name_data);

        final String cust_name_data = getIntent().getStringExtra("cust_name_data");
        cus_name_confirm.setText("customer name:    " + cust_name_data);

        final String meal_name_data = getIntent().getStringExtra("meal_name_data");
        meal_name_confirm.setText("meal name:     " + meal_name_data);

        final String meal_price_data = getIntent().getStringExtra("meal_price_data");
        meal_price_confirm.setText("meal price:      " + meal_price_data + "  " + "JD");

        final String from_data = getIntent().getStringExtra("from_data");
        start_place_confirm.setText("start point:    " + from_data);

        final String to_data = getIntent().getStringExtra("to_data");
        end_place_confirm.setText("end point:    " + to_data);

        final String cust_phone_data = getIntent().getStringExtra("cust_phone_data");
        meal_name_confirm.setText("customer phone number:    " + cust_phone_data);

        final String bill_data = getIntent().getStringExtra("bill_data");
        dilivary_bill.setText("delivery  cost:     " + bill_data + "  " + "JD");

        assert bill_data != null;
        final Double cashdesierd1 = Double.parseDouble(bill_data) * 0.1;

        cash_desired.setText("cash desired:   " + cashdesierd1 + " cents");
        final String cash_desierd_string = Double.toString(cashdesierd1);

        final String key = getIntent().getStringExtra("key");
        final String uid = getIntent().getStringExtra("uid");

        final String longitude = getIntent().getStringExtra("longitude");
        final String latitude = getIntent().getStringExtra("latitude");

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Driver").child(firebaseUser.getUid());
        final String[] cash_desierd_before_sum = new String[1];
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cash_desierd_before_sum[0] = String.valueOf(dataSnapshot.child("cashdesired").getValue());
                Toast.makeText(getApplicationContext(), "previous cash desired: " + cash_desierd_before_sum[0], Toast.LENGTH_LONG).show();
                all = Double.parseDouble(cash_desierd_before_sum[0]);
                all += cashdesierd1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        view_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send_data = new Intent(getApplicationContext(), MapsActivity_for_admin.class);
                send_data.putExtra("latitude", latitude);
                send_data.putExtra("longitude", longitude);
                send_data.putExtra("name_for_marker", res_name_data);
                Toast.makeText(getApplicationContext(), longitude + latitude + key, Toast.LENGTH_LONG).show();
                startActivity(send_data);

            }
        });
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Driver").child(firebaseUser.getUid());
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = String.valueOf(dataSnapshot.child("num_of_taken_orders").getValue());
                num_of_taken_orders = Integer.parseInt(a);
                Toast.makeText(getApplicationContext(), "Number of taken orders is" + " " + String.valueOf(num_of_taken_orders) + " " + "from 2 ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        take_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num_of_taken_orders < 2) {
                    num_of_taken_orders++;
                    long time = System.currentTimeMillis();
                    String time1 = Long.toString(time);
                    Snackbar.make(view, "YOU HAVE TAKEN THE ORDER: " + meal_name_data + "for restaurant : " + res_name_data + "for customer: " + cust_name_data, Snackbar.LENGTH_LONG).show();
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("res_name").setValue(res_name_data);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("coust_name").setValue(cust_name_data);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("meal_name").setValue(meal_name_data);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("meal_price").setValue(meal_price_data);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("start_point").setValue(from_data);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("end_point").setValue(to_data);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("cus_phone_num").setValue(cust_phone_data);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("dilivary_cost").setValue(bill_data);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("cash_desierd").setValue(cash_desierd_string);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("driver_uid").setValue(firebaseUser.getUid());
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("meal_states").setValue("taken");
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("key").setValue(time1);
                    databaseReference.child("Driver").child(firebaseUser.getUid()).child("SumAllbill").setValue(String.valueOf(bill_data));
                    databaseReference.child("Driver").child(firebaseUser.getUid()).child("cashdesired").setValue(String.valueOf(all));
                    databaseReference.child("Driver").child(firebaseUser.getUid()).child("num_of_taken_orders").setValue(String.valueOf(num_of_taken_orders));
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("key_res").setValue(key);
                    databaseReference.child("taken_orders").child(firebaseUser.getUid()).child(time1).child("uid_res").setValue(uid);
                    databaseReference.child("all_Requests").child(key).child("meal_states").setValue("taken");
                    databaseReference.child("Requests").child(uid).child(key).child("meal_states").setValue("taken");
                    startActivity(new Intent(getApplicationContext(), driver_main.class));
                } else {
                    Snackbar.make(view, "you cant take any orders", Snackbar.LENGTH_LONG).show();
                }

            }
        });


    }
}
