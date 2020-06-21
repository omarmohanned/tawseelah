package com.example.tawseelah;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class driver_sign_up extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    private EditText firstname, secondname, emailaddress, phonenumber, car_type, car_color, password;
    private Button register;
    private FirebaseUser firebaseUser;
    private ImageButton show_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_up);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firstname = findViewById(R.id.firstname);
        secondname = findViewById(R.id.secondname);
        password = findViewById(R.id.password);
        emailaddress = findViewById(R.id.emailaddress);
        phonenumber = findViewById(R.id.phone1);
        car_type = findViewById(R.id.car_type);
        car_color = findViewById(R.id.car_color);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(firstname.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
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

                } else if (TextUtils.isEmpty(car_type.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
                    car_type.setHint("*");
                    car_type.setHintTextColor(getResources().getColor(R.color.red));

                } else if (TextUtils.isEmpty(car_color.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
                    car_color.setHint("*");
                    car_color.setHintTextColor(getResources().getColor(R.color.red));

                } else if (TextUtils.isEmpty(password.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_LONG).show();
                    password.setHint("*");
                    password.setHintTextColor(getResources().getColor(R.color.red));
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(emailaddress.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("firstname").setValue(firstname.getText().toString());
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("secondname").setValue(secondname.getText().toString());
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("emailaddress").setValue(emailaddress.getText().toString());
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("phonenumber").setValue(phonenumber.getText().toString());
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("cartype").setValue(car_type.getText().toString());
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("carcolor").setValue(car_color.getText().toString());
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("uid_driver").setValue(firebaseUser.getUid());
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("SumAllbill").setValue("0");
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("cashdesired").setValue("0");
                                databaseReference.child("Driver").child(firebaseUser.getUid()).child("num_of_taken_orders").setValue("0");

                                Toast.makeText(getApplicationContext(), "welcome you are now registered as a Driver  ", Toast.LENGTH_LONG).show();
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




}
