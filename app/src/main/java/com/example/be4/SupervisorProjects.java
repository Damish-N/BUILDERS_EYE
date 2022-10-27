package com.example.be4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.be4.models.Item;
import com.example.be4.models.Site;
import com.example.be4.models.SiteDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class SupervisorProjects extends AppCompatActivity {

    ListView projectsSectionsListView;
    TextView noData;
    private ArrayList<Site> siteArrayList = new ArrayList<Site>();
    private ArrayList<QueryDocumentSnapshot> queryDocumentSnapshots = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressBar progressBarSupProjects;
    ScrollView scrollSupProjects;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_projects);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sh.getString("email", "");

        projectsSectionsListView = findViewById(R.id.projectsSectionsListView);
        progressBarSupProjects = findViewById(R.id.progressBarSupProjects);
        scrollSupProjects = findViewById(R.id.scrollSupProjects);
        noData = findViewById(R.id.noData);

        progressBarSupProjects.setVisibility(View.VISIBLE);
        scrollSupProjects.setVisibility(View.GONE);
        noData.setVisibility(View.GONE);


        getTheSiteData(s1);


    }

    private void getTheSiteData(String s1) {
        db.collection("sites").get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        System.out.println(document.getId());
                                        System.out.println(s1 + " " + document.getData().get("supEmail"));
                                        if (s1.equals(document.getData().get("supEmail").toString())) {
                                            siteArrayList.add(new Site(
                                                    document.getData().get("siteName").toString(), document.getData().get("supName").toString()
                                            ));
                                            queryDocumentSnapshots.add(document);
                                        }

                                    }
                                    System.out.println("Size: " + siteArrayList.size());
                                    progressBarSupProjects.setVisibility(View.GONE);
                                    if (!siteArrayList.isEmpty()) {
                                        showAdapter(siteArrayList, queryDocumentSnapshots);
                                        scrollSupProjects.setVisibility(View.VISIBLE);
                                    } else {
                                        noData.setVisibility(View.VISIBLE);
                                    }


                                }
                            }
                        }
                ).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SupervisorProjects.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

    }

    private void showAdapter(ArrayList<Site> siteArrayList, ArrayList<QueryDocumentSnapshot> queryDocumentSnapshots) {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, SupervisorProjects.this.siteArrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText("Site Name: " + siteArrayList.get(position).getSiteName());
                text2.setText("Site Sup Name: " + siteArrayList.get(position).getSupervisorName());

                view.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SiteDetails siteDetails = new SiteDetails();
                                ArrayList<Item> itemArrayList = (ArrayList<Item>) queryDocumentSnapshots.get(position).getData().get("siteItems");
                                siteDetails.setSiteName(queryDocumentSnapshots.get(position).getData().get("siteName").toString());
                                siteDetails.setSupName(queryDocumentSnapshots.get(position).getData().get("supName").toString());
                                siteDetails.setSupEmail(queryDocumentSnapshots.get(position).getData().get("supEmail").toString());
                                siteDetails.setId(queryDocumentSnapshots.get(position).getId());
                                siteDetails.setSiteItems(itemArrayList);
//                                System.out.println(queryDocumentSnapshots.get(position).getData().get("siteItems").getClass().getSimpleName());

//                                siteDetails.setSiteItems();
                                Intent intent = new Intent(getBaseContext(), SupSelectProject.class);
                                intent.putExtra("selectionProject", (Serializable) siteDetails);
                                startActivity(intent);
                                Toast.makeText(SupervisorProjects.this, queryDocumentSnapshots.get(position).getData().get("siteName").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );


                return view;
            }
        };

        projectsSectionsListView.setAdapter(adapter);

    }

}