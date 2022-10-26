package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.be4.models.Item;
import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OnGoingSites extends AppCompatActivity {

    private ListView siteListView;
    private Button addBtn, updateSections;
    private EditText itemEdt;
    private ArrayList<String> sitesList;
    private ArrayList<Item> supervisorListEmail;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_goning_sites);
        Objects.requireNonNull(getSupportActionBar()).hide();

        siteListView = findViewById(R.id.sectionItems);
//        addBtn = findViewById(R.id.addItemSite);
//        itemEdt = findViewById(R.id.idEdtSiteName);
//        updateSections = findViewById(R.id.updateSections);

        sitesList = new ArrayList<>();
        supervisorListEmail = new ArrayList<Item>();

        Item item = new Item("Site", 5);


        sitesList.add("site-1");
        supervisorListEmail.add(item);
        supervisorListEmail.add(new Item("Site1", 5));
        supervisorListEmail.add(new Item("Site2", 5));
        supervisorListEmail.add(new Item("Site3", 5));

        System.out.println(supervisorListEmail.get(0).getItemName());


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, sitesList,supervisorListEmail);
//        android.R.layout.s

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, supervisorListEmail) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(supervisorListEmail.get(position).getItemName());
                text2.setText(String.valueOf(supervisorListEmail.get(position).getCount()));
                return view;
            }
        };

        siteListView.setAdapter(adapter);

//        addBtn.setOnClickListener(
//                view -> {
//                    String item = itemEdt.getText().toString();
//
//                    // on below line we are checking if item is not empty
//                    if (!item.isEmpty()) {
//
//                        // on below line we are adding item to our list.
////                        lngList.add(item);
//                        sitesList.add(item);
//                        // on below line we are notifying adapter
//                        // that data in list is updated to
//                        // update our list view.
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//        );

//        updateSections.setOnClickListener(
//                view -> {
//                    Toast.makeText(this, "Update list", Toast.LENGTH_SHORT).show();
//                }
//        );

    }
}