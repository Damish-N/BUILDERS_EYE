package com.example.be4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateItemPage extends AppCompatActivity {

    TextView itemNameDescription, countItem;
    Button addItemPlus, addItemMinus, updateTheItem;
    FirebaseFirestore firebaseFirestore;


    int n = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_page);
        Intent intent = getIntent();
        itemNameDescription = findViewById(R.id.itemNameDescription);
        countItem = findViewById(R.id.countItem);
        addItemPlus = findViewById(R.id.addItemPlus);
        addItemMinus = findViewById(R.id.addItemMinus);
        updateTheItem = findViewById(R.id.updateTheItem);

        firebaseFirestore = FirebaseFirestore.getInstance();

        String id;
        String number = intent.getStringExtra("count");
        n = Integer.parseInt(number);
        id = intent.getStringExtra("id");
        itemNameDescription.setText(intent.getStringExtra("itemName"));
        countItem.setText(intent.getStringExtra("count"));
        DocumentReference itemDoc = firebaseFirestore.collection("items").document(id);

        System.out.println(id);


        addItemPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++n;
                countItem.setText(String.valueOf(n));
            }
        });
        addItemMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n>0){
                    --n;
                    countItem.setText(String.valueOf(n));
                }
            }
        });

        updateTheItem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemDoc.update("count", n)
                                .addOnSuccessListener(
                                        new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(UpdateItemPage.this, "Success", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(UpdateItemPage.this, MainStoreOwner.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                ).addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(UpdateItemPage.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                );
                    }
                }
        );


    }
}