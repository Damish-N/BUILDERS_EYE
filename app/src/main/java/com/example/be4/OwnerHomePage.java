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
    LinearLayout mainStoreBtn;

//    @SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home_page);
        Objects.requireNonNull(getSupportActionBar()).hide();
        loginBtn = findViewById(R.id.logOut);
        loginBtn.setOnClickListener(
                view -> Toast.makeText(OwnerHomePage.this, "Log out", Toast.LENGTH_SHORT).show()
        );
        mainStoreBtn =(LinearLayout) findViewById(R.id.mm);
        mainStoreBtn.setOnClickListener(
                view -> {
                    Intent intent = new Intent(OwnerHomePage.this, MainStoreOwner.class);
                    startActivity(intent);
                    Toast.makeText(OwnerHomePage.this, "Clicked owner", Toast.LENGTH_SHORT).show();
                }
        );


    }
}