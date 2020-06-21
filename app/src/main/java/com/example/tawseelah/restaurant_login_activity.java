package com.example.tawseelah;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class restaurant_login_activity extends AppCompatActivity {
    private EditText email, password;
    private Button login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login_activity);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter email", Toast.LENGTH_LONG).show();
                    email.setHint("*");
                    email.setHintTextColor(getResources().getColor(R.color.red));
                    return;
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please Enter password", Toast.LENGTH_LONG).show();
                    password.setHint("*");
                    password.setHintTextColor(getResources().getColor(R.color.red));
                    return;
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "welcome", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),returant_work_activity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "please try again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });



    }
}
