package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;

public class SuperviserHomePage extends AppCompatActivity {

    LinearLayout mainStoreSupervisor, onGoingSitesSupervisor, supervisorProjectBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sh.getString("email", "");

        System.out.println("Your email is:" + s1);


        setContentView(R.layout.activity_superviser_home_page);
        Objects.requireNonNull(getSupportActionBar()).hide();
        mainStoreSupervisor = (LinearLayout) findViewById(R.id.mainStoreSupervisor);
        onGoingSitesSupervisor = (LinearLayout) findViewById(R.id.onGoingSitesSupervisor);
        supervisorProjectBtn = (LinearLayout) findViewById(R.id.supervisorProjectBtn);

        mainStoreSupervisor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SuperviserHomePage.this, MainStoreOwner.class);
                        startActivity(intent);
                    }
                }
        );

        onGoingSitesSupervisor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SuperviserHomePage.this, OnGoingSites.class);
                        startActivity(intent);
                    }
                }
        );

        supervisorProjectBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SuperviserHomePage.this, "Clicked His project Button", Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }
}