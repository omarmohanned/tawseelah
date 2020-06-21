package com.example.tawseelah;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminstrater_work_on extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference databaseReference1, databaseReference2;
    ArrayList<get_data_res> list2;
    ArrayList<get_driver_info_from_data_dase> get_driver_info_from_data_dases;
    private TextView driver_num, res_num;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminstrater_work_on);
        firebaseAuth = FirebaseAuth.getInstance();
        driver_num = findViewById(R.id.driver_num);
        res_num = findViewById(R.id.res_num);
        get_driver_info_from_data_dases = new ArrayList<get_driver_info_from_data_dase>();
        list2 = new ArrayList<get_data_res>();
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Driver");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                get_driver_info_from_data_dases.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    get_driver_info_from_data_dases.add(ds.getValue(get_driver_info_from_data_dase.class));
                    if (get_driver_info_from_data_dases.size() == 1) {
                        driver_num.setText(String.valueOf(get_driver_info_from_data_dases.size()) + "  Driver");

                    } else {
                        driver_num.setText(String.valueOf(get_driver_info_from_data_dases.size()) + "  Drivers");

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("restaurant owner");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    list2.add(ds.getValue(get_data_res.class));
                    if (list2.size() == 1) {
                        res_num.setText(String.valueOf(list2.size()) + " restaurant ");
                    } else {
                        res_num.setText(String.valueOf(list2.size()) + " restaurants ");
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.adminstrater_work_on, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all_orders) {
            startActivity(new Intent(getApplicationContext(), all_orders.class));
            Toast.makeText(getApplicationContext(), "active order ", Toast.LENGTH_LONG).show();
        } else if (id == R.id.all_drivers) {
            startActivity(new Intent(getApplicationContext(), all_drivers.class));
            Toast.makeText(getApplicationContext(), "active driver ", Toast.LENGTH_LONG).show();
        } else if (id == R.id.resturant) {
            startActivity(new Intent(getApplicationContext(), all_resturants.class));
            Toast.makeText(getApplicationContext(), "active restaurant ", Toast.LENGTH_LONG).show();
        } else if (id == R.id.change_password) {
            startActivity(new Intent(getApplicationContext(), change_password_admin.class));
            Toast.makeText(getApplicationContext(), "change password ", Toast.LENGTH_LONG).show();
        } else if (id == R.id.log_out) {
            Toast.makeText(getApplicationContext(), "succefully signed out", Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
