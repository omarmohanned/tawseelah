package com.example.tawseelah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ORDERS extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        spinner = findViewById(R.id.spinner_for_order);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.orders_choose));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int ret, long l) {
                switch (ret) {
                    case 1:
                        Toast.makeText(getApplicationContext(), "Waiting orders", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), waiting_orders.class));
                        break;

                    case 2:
                        Toast.makeText(getApplicationContext(), "canceled orders", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), canceled_orders.class));
                        break;

                    case 3:
                        Toast.makeText(getApplicationContext(), "Finished orders ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), finished_orders.class));
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(), "Taken orders  ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),taken_orders.class));
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(), "All orders ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), all_orders.class));
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Nothing Selected", Toast.LENGTH_LONG).show();
            }
        });
    }
}
