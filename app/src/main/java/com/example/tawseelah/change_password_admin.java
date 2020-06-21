package com.example.tawseelah;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class change_password_admin extends AppCompatActivity {
    private EditText email;
    private Button change_password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_admin);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_change_password);
        change_password = findViewById(R.id.change_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(view, "enter your email", Snackbar.LENGTH_LONG).show();
                    email.setHint("*");
                    email.setHintTextColor(getResources().getColor(R.color.red, null));

                } else {firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                  if(task.isSuccessful()){Snackbar.make(getCurrentFocus(),"check your email admin to change password ",Snackbar.LENGTH_LONG).show();
                      email.setText(" ");
                      }
                    }
                });
                }
            }
        });
    }
}
