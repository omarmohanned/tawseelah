package com.example.tawseelah;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class resturant_sign_up extends AppCompatActivity {
    private static final int Requst_location = 1;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private EditText firstname, secondname, emailaddress, phonenumber, restaurantplace, restaurantname, password;
    private Button register, loc;
    private LocationManager locationManager;
    private String longitude;
    private String latitiude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_sign_up);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        loc=findViewById(R.id.loc_get);
        loc.setVisibility(View.INVISIBLE);

        ActivityCompat.requestPermissions(resturant_sign_up.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, Requst_location);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildalertmassagenogps();

        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Requst_location);
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



        firstname = findViewById(R.id.firstname);
        secondname = findViewById(R.id.secondname);
        password = findViewById(R.id.password);
        emailaddress = findViewById(R.id.emailaddress);
        phonenumber = findViewById(R.id.phone1);
        restaurantplace = findViewById(R.id.restaurantplace);
        restaurantname = findViewById(R.id.restaurantname);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(firstname.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "first is empty", Toast.LENGTH_LONG).show();
                    firstname.setHint("*");
                    firstname.setHintTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(secondname.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
                    secondname.setHint("*");
                    secondname.setHintTextColor(getResources().getColor(R.color.red));

                } else if (TextUtils.isEmpty(emailaddress.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
                    emailaddress.setHint("*");
                    emailaddress.setHintTextColor(getResources().getColor(R.color.red));

                } else if (TextUtils.isEmpty(phonenumber.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
                    phonenumber.setHint("*");
                    phonenumber.setHintTextColor(getResources().getColor(R.color.red));

                } else if (TextUtils.isEmpty(restaurantplace.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
                    restaurantplace.setHint("*");
                    restaurantplace.setHintTextColor(getResources().getColor(R.color.red));

                } else if (TextUtils.isEmpty(restaurantname.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
                    restaurantname.setHint("*");
                    restaurantname.setHintTextColor(getResources().getColor(R.color.red));

                } else if (TextUtils.isEmpty(password.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
                    password.setHint("*");
                    password.setHintTextColor(getResources().getColor(R.color.red));
                } else if (longitude == null && latitiude == null) {
                    Toast.makeText(getApplicationContext(), "you have no location please provide us your location", Toast.LENGTH_LONG).show();
                    loc.setVisibility(View.VISIBLE);
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(emailaddress.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                long time = System.currentTimeMillis();
                                String time1 = Long.toString(time);
                                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("firstname2").setValue(firstname.getText().toString());
                                databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("secondname2").setValue(secondname.getText().toString());
                                databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("emailaddress").setValue(emailaddress.getText().toString());
                                databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("phonenumber").setValue(phonenumber.getText().toString());
                                databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("restaurantplace2").setValue(restaurantplace.getText().toString());
                                databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("restaurantname2").setValue(restaurantname.getText().toString());
                                databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("loc_longitude").setValue(longitude);
                                databaseReference.child("restaurant owner").child(firebaseUser.getUid()).child("loc_latitude").setValue(latitiude);

                                Toast.makeText(getApplicationContext(), "welcome you are now registered as a restaurant owner ", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            } else {
                                Toast.makeText(getApplicationContext(), "failed try again " + task.getException(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }
            }
        });
    }




    private void buildalertmassagenogps() {
    }
}
