package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class OwnerHomePage extends AppCompatActivity {

    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home_page);
        Objects.requireNonNull(getSupportActionBar()).hide();
        loginBtn = findViewById(R.id.logOut);
        loginBtn.setOnClickListener(
                view -> Toast.makeText(OwnerHomePage.this, "Log out", Toast.LENGTH_SHORT).show()
        );
    }
}