package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;

public class OwnerHomePage extends AppCompatActivity {

    Button loginBtn;
    LinearLayout mainStoreBtn, onGoingBtn, reportsBtn;

    //    @SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home_page);
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
        reportsBtn = (LinearLayout) findViewById(R.id.reports);
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
        reportsBtn.setOnClickListener(
                view -> {
                    Intent intent = new Intent(OwnerHomePage.this, Reports.class);
                    startActivity(intent);
                }
        );

    }
}