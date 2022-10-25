package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UpdateItemPage extends AppCompatActivity {

    TextView itemNameDescription, countItem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_page);
        Intent intent = getIntent();
        itemNameDescription = findViewById(R.id.itemNameDescription);
        countItem = findViewById(R.id.countItem);


        itemNameDescription.setText(intent.getStringExtra("itemName"));
        countItem.setText(intent.getStringExtra("count"));

    }
}