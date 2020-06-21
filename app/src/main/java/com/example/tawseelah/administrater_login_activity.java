package com.example.tawseelah;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class administrater_login_activity extends AppCompatActivity {
    private EditText email, password;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrater_login_activity);
        email = findViewById(R.id.email);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "email empty", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(password.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "password empty", Toast.LENGTH_LONG).show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setBackgroundColor(getResources().getColor(R.color.red));
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "welcome administrator", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), adminstrater_work_on.class));
                                    } else {
                                        Toast.makeText(getApplicationContext(), "wrong input", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            }
        });
    }
}
