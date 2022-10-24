package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class OnGoingSites extends AppCompatActivity {

    private ListView siteListView;
    private Button addBtn, updateSections;
    private EditText itemEdt;
    private ArrayList<String> sitesList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_goning_sites);
        Objects.requireNonNull(getSupportActionBar()).hide();


        siteListView = findViewById(R.id.sectionItems);
        addBtn = findViewById(R.id.addItemSite);
        itemEdt = findViewById(R.id.idEdtSiteName);
        updateSections = findViewById(R.id.updateSections);

        sitesList = new ArrayList<>();

        sitesList.add("site-1");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sitesList);
//        android.R.layout.s
        siteListView.setAdapter(adapter);

        addBtn.setOnClickListener(
                view -> {
                    String item = itemEdt.getText().toString();

                    // on below line we are checking if item is not empty
                    if (!item.isEmpty()) {

                        // on below line we are adding item to our list.
//                        lngList.add(item);
                        sitesList.add(item);
                        // on below line we are notifying adapter
                        // that data in list is updated to
                        // update our list view.
                        adapter.notifyDataSetChanged();
                    }
                }
        );

        updateSections.setOnClickListener(
                view -> {
                    Toast.makeText(this, "Update list", Toast.LENGTH_SHORT).show();
                }
        );

    }
}