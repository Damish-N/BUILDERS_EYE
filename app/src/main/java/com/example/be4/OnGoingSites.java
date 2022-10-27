package com.example.be4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.be4.models.Item;
import com.example.be4.models.Site;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class OnGoingSites extends AppCompatActivity {

    private ListView siteListView;
    private Button addBtn, updateSections;
    private EditText itemEdt;
    private ArrayList<String> sitesList;
    private ArrayList<Item> supervisorListEmail;
    private ArrayList<Site> siteArrayList = new ArrayList<Site>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ScrollView scrollViewOnGoing;
    ProgressBar progressBarOnGoing;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_goning_sites);
        Objects.requireNonNull(getSupportActionBar()).hide();

        siteListView = findViewById(R.id.sectionItems);
        scrollViewOnGoing = findViewById(R.id.scrollViewOnGoing);
        progressBarOnGoing = findViewById(R.id.progressBarOnGoing);

        progressBarOnGoing.setVisibility(View.VISIBLE);
        scrollViewOnGoing.setVisibility(View.GONE);
//        addBtn = findViewById(R.id.addItemSite);
//        itemEdt = findViewById(R.id.idEdtSiteName);
//        updateSections = findViewById(R.id.updateSections);

        sitesList = new ArrayList<>();
        supervisorListEmail = new ArrayList<Item>();

        Item item = new Item("Site", 5);


        sitesList.add("site-1");
        supervisorListEmail.add(item);
        supervisorListEmail.add(new Item("Site1", 5));
        supervisorListEmail.add(new Item("Site2", 5));
        supervisorListEmail.add(new Item("Site3", 5));

        System.out.println(supervisorListEmail.get(0).getItemName());


        getTheSiteData();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, sitesList,supervisorListEmail);
//        android.R.layout.s


//        addBtn.setOnClickListener(
//                view -> {
//                    String item = itemEdt.getText().toString();
//
//                    // on below line we are checking if item is not empty
//                    if (!item.isEmpty()) {
//
//                        // on below line we are adding item to our list.
////                        lngList.add(item);
//                        sitesList.add(item);
//                        // on below line we are notifying adapter
//                        // that data in list is updated to
//                        // update our list view.
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//        );

//        updateSections.setOnClickListener(
//                view -> {
//                    Toast.makeText(this, "Update list", Toast.LENGTH_SHORT).show();
//                }
//        );

    }

    private void getTheSiteData() {
        db.collection("sites").get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        System.out.println(document.getId());
                                        siteArrayList.add(new Site(
                                                document.getData().get("siteName").toString(), document.getData().get("supName").toString()
                                        ));
                                    }
                                    System.out.println("Size: " + siteArrayList.size());
                                    showAdapter(siteArrayList);
                                    progressBarOnGoing.setVisibility(View.GONE);
                                    scrollViewOnGoing.setVisibility(View.VISIBLE);

                                }
                            }
                        }
                ).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(OnGoingSites.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

    }

    private void showAdapter(ArrayList<Site> siteArrayList) {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, OnGoingSites.this.siteArrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText("Site Name: " + OnGoingSites.this.siteArrayList.get(position).getSiteName());
                text2.setText("Site Sup Name: " + OnGoingSites.this.siteArrayList.get(position).getSupervisorName());

                view.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(OnGoingSites.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
                            }
                        }
                );


                return view;
            }
        };

        siteListView.setAdapter(adapter);


    }


}