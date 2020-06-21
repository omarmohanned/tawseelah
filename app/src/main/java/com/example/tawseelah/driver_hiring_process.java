package com.example.tawseelah;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class driver_hiring_process extends AppCompatActivity {

    private static final int requst_code = 1;
    private EditText resname, meal_name, meal_price, starting_point, finish_point, cus_name, cus_loc, cus_phone, dilivary_cost;
    private Button hire;
    private DatabaseReference databaseReference;
    private long time;
    private FirebaseUser firebaseUser;
    private LocationManager locationManager;
    private String longitude;
    private String latitiude;
private Switch getloc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_hiring_process);
        getloc=findViewById(R.id.getloc);
getloc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b==true){ ActivityCompat.requestPermissions(driver_hiring_process.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},requst_code);
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                alertmassage();

            }else { if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(driver_hiring_process.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requst_code);
            } else {
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    double latti = location.getLatitude();
                    double longi = location.getLongitude();
                    longitude = String.valueOf(longi);
                    latitiude = String.valueOf(latti);
                    Toast.makeText(getApplicationContext(), longitude + " " + latitiude, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "location not taken please try again", Toast.LENGTH_LONG).show();
                }
            }

            }
        }else
            {Toast.makeText(getApplicationContext()," not checked",Toast.LENGTH_LONG).show();}


    }
});






        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        resname = findViewById(R.id.resname);
        meal_name = findViewById(R.id.meal_name);
        meal_price = findViewById(R.id.meal_price);
        starting_point = findViewById(R.id.starting_point);
        finish_point = findViewById(R.id.finish_point);
        cus_name = findViewById(R.id.cus_name);
        cus_loc = findViewById(R.id.cus_loc);
        cus_phone = findViewById(R.id.cus_phone);
        dilivary_cost = findViewById(R.id.dilivary_cost);
        hire = findViewById(R.id.hire);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(starting_point.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter start point", Toast.LENGTH_LONG).show();
                    starting_point.setHint("*");
                    starting_point.setHintTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(finish_point.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter end point", Toast.LENGTH_LONG).show();
                    finish_point.setHint("*");
                    finish_point.setHintTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(resname.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter restaurant name", Toast.LENGTH_LONG).show();
                    resname.setHint("*");
                    resname.setHintTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(cus_phone.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter customer phone number", Toast.LENGTH_LONG).show();
                    cus_phone.setHint("*");
                    cus_phone.setHintTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(cus_loc.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter costumer location", Toast.LENGTH_LONG).show();
                    cus_loc.setHint("*");
                    cus_loc.setHintTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(cus_name.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter costumer name", Toast.LENGTH_LONG).show();
                    cus_name.setHint("*");
                    cus_name.setHintTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(meal_price.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter meal price in:" + "JD", Toast.LENGTH_LONG).show();
                    meal_price.setHint("*");
                    meal_price.setHintTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(meal_name.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter meal name", Toast.LENGTH_LONG).show();
                    meal_name.setHint("*");
                    meal_name.setHintTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(dilivary_cost.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter meal name", Toast.LENGTH_LONG).show();
                    dilivary_cost.setHint("*");
                    dilivary_cost.setHintTextColor(getResources().getColor(R.color.red));
                } else {

                    Toast.makeText(getApplicationContext(), "request done successfully for;" + firebaseUser.getDisplayName(), Toast.LENGTH_LONG).show();
                    time = System.currentTimeMillis();
                    String time1 = String.valueOf(time);
                    String stat = "waiting";

                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("start_point").setValue(starting_point.getText().toString());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("end_point").setValue(finish_point.getText().toString());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("res_name").setValue(resname.getText().toString());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("cus_phone_num").setValue(cus_phone.getText().toString());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("coust_loc").setValue(cus_loc.getText().toString());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("coust_name").setValue(cus_name.getText().toString());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("meal_price").setValue(meal_price.getText().toString());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("meal_name").setValue(meal_name.getText().toString());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("dilivary_cost").setValue(dilivary_cost.getText().toString());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("meal_states").setValue(stat);
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("key").setValue(time1);
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("uid").setValue(firebaseUser.getUid());
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("loc_longitude").setValue(longitude);
                    databaseReference.child("Requests").child(firebaseUser.getUid()).child(time1).child("loc_latitude").setValue(latitiude);


                    //********************************************************************************************************************************************************
                    databaseReference.child("all_Requests").child(time1).child("start_point").setValue(starting_point.getText().toString());
                    databaseReference.child("all_Requests").child(time1).child("end_point").setValue(finish_point.getText().toString());
                    databaseReference.child("all_Requests").child(time1).child("res_name").setValue(resname.getText().toString());
                    databaseReference.child("all_Requests").child(time1).child("cus_phone_num").setValue(cus_phone.getText().toString());
                    databaseReference.child("all_Requests").child(time1).child("coust_loc").setValue(cus_loc.getText().toString());
                    databaseReference.child("all_Requests").child(time1).child("coust_name").setValue(cus_name.getText().toString());
                    databaseReference.child("all_Requests").child(time1).child("meal_price").setValue(meal_price.getText().toString());
                    databaseReference.child("all_Requests").child(time1).child("meal_name").setValue(meal_name.getText().toString());
                    databaseReference.child("all_Requests").child(time1).child("dilivary_cost").setValue(dilivary_cost.getText().toString());
                    databaseReference.child("all_Requests").child(time1).child("meal_states").setValue(stat);
                    databaseReference.child("all_Requests").child(time1).child("key").setValue(time1);
                    databaseReference.child("all_Requests").child(time1).child("uid").setValue(firebaseUser.getUid());
                    databaseReference.child("all_Requests").child(time1).child("loc_longitude").setValue(longitude);
                    databaseReference.child("all_Requests").child(time1).child("loc_latitude").setValue(latitiude);


                }


            }
        });
    }

    private void alertmassage() {
    }


    }

