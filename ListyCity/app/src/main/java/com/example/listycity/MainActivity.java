package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    LinearLayout inputLayout;
    EditText cityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing","Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //Have selection remain highlighted

        inputLayout = findViewById(R.id.input_layout); //Find the hidden input layout (EditText + Confirm button)
        cityInput = findViewById(R.id.city_input); //Find the EditText inside the input layout
    }

    public void addCity(View v) {
        // Show the hidden input bar so the user can type (after add button is pressed)
        inputLayout.setVisibility(View.VISIBLE);
    }

    public void deleteCity(View v) {
        // Get the index of the selected item
        int position = cityList.getCheckedItemPosition();

        if (position != ListView.INVALID_POSITION) {
            // Remove the selected city
            dataList.remove(position);
            // Update the ListView
            cityAdapter.notifyDataSetChanged();
            // Clear selection
            cityList.clearChoices();
        }
    }

    public void confirmCity(View v) {
        // Get the text entered into the EditText (after confirm button is pressed)
        String newCity = cityInput.getText().toString().trim();
        // Only add if the input is not empty
        if (!newCity.isEmpty()) {
            // Add the city to the list
            dataList.add(newCity);
            // Refresh the ListView to show the new city
            cityAdapter.notifyDataSetChanged();
            // Clear the input for next time
            cityInput.setText("");
            // Hide the input bar again
            inputLayout.setVisibility(View.GONE);
        }
    }
}