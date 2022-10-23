package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StartPage extends AppCompatActivity {

     private  LinearLayout supervisorBtn, ownerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_start_page);
        supervisorBtn = (LinearLayout) findViewById(R.id.supervierBtn);
        supervisorBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(StartPage.this, "Clicked owner", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        ownerBtn = (LinearLayout) findViewById(R.id.ownerBtn);
        ownerBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(StartPage.this, "Clicked Supervisor", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}