package com.example.tawseelah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class all_users_forget_password extends AppCompatActivity {
    private EditText email;
    private Button change_password;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users_forget_password);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_change_password);
        change_password = findViewById(R.id.change_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(view, "enter your email", Snackbar.LENGTH_LONG).show();
                    email.setHint("*");
                    email.setHintTextColor(getResources().getColor(R.color.red, null));

                } else {firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){Snackbar.make(view,"check your email to change password ",Snackbar.LENGTH_LONG).show();
                            email.setText(" ");
                        }
                    }
                });
                }
            }
        });
    }
    }

