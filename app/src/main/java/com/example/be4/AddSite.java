package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Objects;

public class AddSite extends AppCompatActivity {

    public String[] items= new String[]{"item1","item2","item3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // here you can use array or list
        ArrayAdapter adapter= new ArrayAdapter(AddSite.this, R.layout.support_simple_spinner_dropdown_item, items);
        final Spinner itemsSpinner= (Spinner) findViewById(R.id.planets_spinner);
        itemsSpinner.setAdapter(adapter);
    }
}