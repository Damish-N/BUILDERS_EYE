package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;

public class OwnerHomePage extends AppCompatActivity {

    Button loginBtn;
    LinearLayout mainStoreBtn, onGoingBtn, addASite;

    //    @SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home_page);


        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sh.getString("email", "");

        System.out.println("Your email is:" + s1);


        Objects.requireNonNull(getSupportActionBar()).hide();
        loginBtn = findViewById(R.id.logOut);
        loginBtn.setOnClickListener(

                view -> {
                    Intent intent = new Intent(OwnerHomePage.this, StartPage.class);
                    startActivity(intent);
                    Toast.makeText(OwnerHomePage.this, "Log out", Toast.LENGTH_SHORT).show();
                }
        );
        mainStoreBtn = (LinearLayout) findViewById(R.id.mm);
        onGoingBtn = (LinearLayout) findViewById(R.id.onGoingBtn);
        addASite = (LinearLayout) findViewById(R.id.addASite);
        mainStoreBtn.setOnClickListener(
                view -> {
                    Intent intent = new Intent(OwnerHomePage.this, MainStoreOwner.class);
                    startActivity(intent);
                    Toast.makeText(OwnerHomePage.this, "Clicked owner", Toast.LENGTH_SHORT).show();
                }
        );

        onGoingBtn.setOnClickListener(
                view -> {
                    Intent intent = new Intent(OwnerHomePage.this, OnGoingSites.class);
                    startActivity(intent);
//                    Toast.makeText(OwnerHomePage.this, "Clicked owner", Toast.LENGTH_SHORT).show();
                }
        );
        addASite.setOnClickListener(
                view -> {
                    Intent intent = new Intent(OwnerHomePage.this, AddSite.class);
                    startActivity(intent);
                }
        );

    }
}