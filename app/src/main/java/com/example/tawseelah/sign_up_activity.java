package com.example.tawseelah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class sign_up_activity extends AppCompatActivity {
    Spinner spinner;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity);
        spinner = findViewById(R.id.choose_for_fragment);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line
                , getResources().getStringArray(R.array.choose_sign_up));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        Toast.makeText(getApplicationContext(), "Driver sign up", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),driver_sign_up.class));
                        break;

                    case 2:
                        Toast.makeText(getApplicationContext(), "restaurant sign up", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),resturant_sign_up.class));

                        break;


                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "NothingSelected", Toast.LENGTH_LONG).show();
            }
        });


    }
}
