package com.example.tawseelah;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class driver_signin extends AppCompatActivity {
    private Button signin;
    private EditText emaildriver, passdriver;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_signin);
        firebaseAuth = FirebaseAuth.getInstance();
        signin = findViewById(R.id.signin);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        emaildriver = findViewById(R.id.emaildriver);
        passdriver = findViewById(R.id.passdriver);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(emaildriver.getText().toString().trim(), passdriver.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "welcome driver", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), driver_main.class));
                                finish();


                        } else {
                            Toast.makeText(getApplicationContext(), "try again" + task.getException(), Toast.LENGTH_LONG).show();
                            passdriver.setText("");
                        }
                    }
                });
            }
        });
    }
}
