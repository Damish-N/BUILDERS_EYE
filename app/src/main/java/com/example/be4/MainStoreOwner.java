package com.example.be4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;


import com.example.be4.models.ProgramAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class MainStoreOwner extends AppCompatActivity {

    private ListView languageLV;
    private Button addBtn, updateItems;
    private EditText itemEdt;
    private ArrayList<String> lngList;
    LinearLayout editArea;
    ScrollView viewArea;
    ProgressBar progressBar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference cities = db.collection("cities");

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<String> itemCounts = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_store_owner);
        Objects.requireNonNull(getSupportActionBar()).hide();
//        progressBar.findViewById(R.id.progressBarOwner);
//        progressBar.setVisibility(View.VISIBLE);
        editArea = findViewById(R.id.editArea);
        viewArea = findViewById(R.id.viewArea);
        editArea.setVisibility(View.GONE);
        viewArea.setVisibility(View.GONE);

        db.collection("items").get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        System.out.println(document.getData().get("count"));
                                        itemNames.add(document.getData().get("itemName").toString());
                                        itemCounts.add(document.getData().get("count").toString());
                                    }
//                                    progressBar.setVisibility(View.VISIBLE);
                                    editArea.setVisibility(View.VISIBLE);
                                    viewArea.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(MainStoreOwner.this, "empty", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                );


        languageLV = findViewById(R.id.mainItem);
        addBtn = findViewById(R.id.addItem);
        updateItems = findViewById(R.id.updateItems);
        itemEdt = findViewById(R.id.idEdtItemName);



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