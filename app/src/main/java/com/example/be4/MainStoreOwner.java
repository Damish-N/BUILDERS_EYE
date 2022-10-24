package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.be4.models.ProgramAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class MainStoreOwner extends AppCompatActivity {

    private ListView languageLV;
    private Button addBtn,updateItems;
    private EditText itemEdt;
    private ArrayList<String> lngList;

    ArrayList<String> itemNames = new ArrayList<>() ;
    ArrayList<String> itemCounts = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_store_owner);
        Objects.requireNonNull(getSupportActionBar()).hide();

        languageLV = findViewById(R.id.mainItem);
        addBtn = findViewById(R.id.addItem);
        updateItems=findViewById(R.id.updateItems);
        itemEdt = findViewById(R.id.idEdtItemName);

        itemNames.add("item-1");
        itemNames.add("item-2");

        itemCounts.add("1");
        itemCounts.add("5");


        ProgramAdapter programAdapter = new ProgramAdapter(this, itemNames, itemCounts);
        languageLV.setAdapter(programAdapter);

        updateItems.setOnClickListener(
                view -> {
                    System.out.println("new value of " + itemCounts.get(0).toLowerCase());
                }
        );


//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lngList);
//        languageLV.setAdapter(adapter);

        addBtn.setOnClickListener(
                view -> {
                    String item = itemEdt.getText().toString();
                    String count = "0";

                    // on below line we are checking if item is not empty
                    if (!item.isEmpty()) {

                        // on below line we are adding item to our list.
//                        lngList.add(item);
                        itemNames.add(item);
                        itemCounts.add(count);
                        // on below line we are notifying adapter
                        // that data in list is updated to
                        // update our list view.
                        programAdapter.notifyDataSetChanged();
                    }
                }
        );

    }
}