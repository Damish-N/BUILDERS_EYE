package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_start_page);
        LinearLayout supervisorBtn = (LinearLayout) findViewById(R.id.supervierBtn);
        supervisorBtn.setOnClickListener(
                view -> {
                    Intent intent = new Intent(StartPage.this, Login.class);
                    startActivity(intent);
                    Toast.makeText(StartPage.this, "Clicked owner", Toast.LENGTH_SHORT).show();
                }
        );
        LinearLayout ownerBtn = (LinearLayout) findViewById(R.id.ownerBtn);
        ownerBtn.setOnClickListener(
                view -> Toast.makeText(StartPage.this, "Clicked Supervisor", Toast.LENGTH_SHORT).show()
        );
    }
}