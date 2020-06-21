package com.example.tawseelah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class returant_work_activity extends AppCompatActivity {
    private ImageButton cart, all_orders, logout, res_info;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returant_work_activity);
        firebaseAuth = FirebaseAuth.getInstance();
        cart = findViewById(R.id.cart);
        res_info = findViewById(R.id.res_info);
        all_orders = findViewById(R.id.all_orders);
        logout = findViewById(R.id.logout);
        res_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), orders_res.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), driver_hiring_process.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "succeful sign out", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        all_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ORDERS.class));
            }
        });


    }
}
