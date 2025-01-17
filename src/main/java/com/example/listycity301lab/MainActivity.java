/**
 * Copyright 2025 - Brian Tang
 *
 * CMPUT 301
 *
 * Jan 17 2025
 */
package com.example.listycity301lab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int cityListIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        Button addCity = findViewById(R.id.add_city);
        Button deleteCity = findViewById(R.id.delete_city);

        String [] cities = {"Edmonton", "Calgary", "Vancouver", "Tokyo", "Pyongyang", "Chennai"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Keeping track of last selected city for deletion
        cityList.setOnItemClickListener(((parent, view, position, id) -> {
            cityListIndex = position;
        }));

        // Add City button prompts the user to enter a city name to add to the city list
        addCity.setOnClickListener(v -> {
            // Create an AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Add City");

            // Add an input edit field
            EditText input = new EditText(MainActivity.this);
            input.setHint("City Name");
            builder.setView(input);

            // Confirmation
            builder.setPositiveButton("Confirm", (dialog, which) -> {
                String cityName = input.getText().toString();
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });

        // Delete City button removes the last selected city from the city list
        deleteCity.setOnClickListener(v -> {
            if (cityListIndex != -1) {
                dataList.remove(cityListIndex);
                cityAdapter.notifyDataSetChanged();
                cityListIndex = -1;
            }
        });


    }
}