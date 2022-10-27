package com.example.be4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.be4.models.Item;
import com.example.be4.models.SiteDetails;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class SupSelectProject extends AppCompatActivity {

    ProgressBar progressBarSelectProject;
    ScrollView selectSupProjectScroll;
    LinearLayout basicDataProject;
    TextView projectName, projectSupName;
    ArrayList<Item> itemArrayList ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sup_select_project);
        Objects.requireNonNull(getSupportActionBar()).hide();
        progressBarSelectProject = findViewById(R.id.progressBarSelectProject);
        basicDataProject = findViewById(R.id.basicDataProject);
        selectSupProjectScroll = findViewById(R.id.selectSupProjectScroll);
        projectName = findViewById(R.id.projectName);
        projectSupName = findViewById(R.id.projectSupName);

        Intent intent = getIntent();
        itemArrayList = new ArrayList<>();

        SiteDetails siteDetail = (SiteDetails) intent.getSerializableExtra("selectionProject");
        System.out.println(siteDetail.getId());

//        progressBarSelectProject.setVisibility(View.VISIBLE);
//        basicDataProject.setVisibility(View.GONE);
//        selectSupProjectScroll.setVisibility(View.GONE);

        getTheItemSet();


        projectName.setText("Site: " + siteDetail.getSiteName());
        projectSupName.setText("Sup: " + siteDetail.getSupName());


    }

    private void getTheItemSet() {
    }
}