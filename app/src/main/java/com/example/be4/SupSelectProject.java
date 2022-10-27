package com.example.be4;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.be4.models.Item;
import com.example.be4.models.ProgramAdapter;
import com.example.be4.models.ProgramAdapterForSite;
import com.example.be4.models.SiteDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SupSelectProject extends AppCompatActivity {

    ProgressBar progressBarSelectProject;
    ScrollView selectSupProjectScroll;
    LinearLayout basicDataProject, spinnerArea, btnArea;
    TextView projectName, projectSupName;
    ArrayList<Item> itemArrayList;
    ArrayList<Item> supervisorSelectedList;
    ArrayList<Item> filerArrayList;
    ArrayList<String> spinnerItemList;
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<String> itemListId = new ArrayList<>();
    ArrayList<String> itemListItemCounts = new ArrayList<>();
    ArrayList<String> itemRemainList =new ArrayList<>();
    FirebaseFirestore db;
    SiteDetails siteDetail;
    ArrayAdapter adapter;
    Spinner spinner;
    ListView projectsSectionsListView;
    Button addItemBtn;

//    ArrayAdapter adapter = new ArrayAdapter(AddSite.this, R.layout.support_simple_spinner_dropdown_item, supervisorList);


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
        spinnerArea = findViewById(R.id.spinnerArea);
        spinner = findViewById(R.id.itemListSup);
        projectsSectionsListView = findViewById(R.id.projectsSectionsListView);
        addItemBtn = findViewById(R.id.addItemBtn);
        btnArea = findViewById(R.id.btnArea);

        Intent intent = getIntent();
        itemArrayList = new ArrayList<>();
        supervisorSelectedList = new ArrayList<>();
        filerArrayList = new ArrayList<>();
        spinnerItemList = new ArrayList<>();
        spinnerItemList.add("Select Item");


        db = FirebaseFirestore.getInstance();


        siteDetail = (SiteDetails) intent.getSerializableExtra("selectionProject");
        System.out.println(siteDetail.getId());

        progressBarSelectProject.setVisibility(View.VISIBLE);
        basicDataProject.setVisibility(View.GONE);
        selectSupProjectScroll.setVisibility(View.GONE);
        spinnerArea.setVisibility(View.GONE);
        btnArea.setVisibility(View.GONE);

        getTheItemSet();


        projectName.setText("Site: " + siteDetail.getSiteName());
        projectSupName.setText("Sup: " + siteDetail.getSupName());


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getTheSupervisorItemList(SiteDetails siteDetail) {

        List<Item> items = new ArrayList<>();
        System.out.println(siteDetail.getId());
        db.collection("sites").
                document(siteDetail.getId())
                .get().addOnCompleteListener(
                        new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    List<Item> listOfItems = documentSnapshot.toObject(SiteDetails.class).getSiteItems();
                                    items.addAll(listOfItems);
                                    System.out.println(listOfItems.get(0).getItemName());
                                    CheckTheList(items);
                                }
                            }
                        }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SupSelectProject.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void CheckTheList(List<Item> items) {
        for (Item i : items) {
            for (Item j : itemArrayList) {
                System.out.println(i.getId() + j.getId());
                if (!Objects.equals(i.getId(), j.getId())) {
                    System.out.println("Item Name :" + i.getItemName());
                    filerArrayList.add(i);
                    spinnerItemList.add(j.getItemName());
                } else {
                    itemNames.add(j.getItemName());
                    itemListId.add(j.getId());
                    itemListItemCounts.add(Integer.toString(j.getCount()));
                }
            }
        }
//        itemNames.add("slkdsd");
//        itemListId.add("s;kdskdsdk;");
//        itemListItemCounts.add("5");
        adapter = new ArrayAdapter(SupSelectProject.this, R.layout.support_simple_spinner_dropdown_item, spinnerItemList);
        spinner.setAdapter(adapter);

        ProgramAdapterForSite programAdapter = new ProgramAdapterForSite(this, itemListId, itemNames, itemListItemCounts,itemArrayList,itemRemainList);
        projectsSectionsListView.setAdapter(programAdapter);


        progressBarSelectProject.setVisibility(View.GONE);
        basicDataProject.setVisibility(View.VISIBLE);
        selectSupProjectScroll.setVisibility(View.VISIBLE);
        spinnerArea.setVisibility(View.VISIBLE);
        btnArea.setVisibility(View.VISIBLE);


        addItemBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(SupSelectProject.this, Integer.toString(spinner.getSelectedItemPosition()), Toast.LENGTH_SHORT).show();
                        int i = spinner.getSelectedItemPosition() - 1;
                        if (spinner.getSelectedItemPosition() != 0) {
                            itemListId.add(itemArrayList.get(i).getId());
                            itemNames.add(itemArrayList.get(i).getItemName());
                            itemRemainList.add( Integer.toString(itemArrayList.get(i).getCount()));
//                            Integer.toString(itemArrayList.get(i).getCount())
                            itemListItemCounts.add("0");

                            spinnerItemList.remove(spinner.getSelectedItemPosition());
                            itemArrayList.remove(i);

//                            after Adding
                        }
                        programAdapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                    }
                }
        );

    }

    private void getTheItemSet() {
        db.collection("items").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        int count = ((Long) documentSnapshot.getData().get("count")).intValue();
                        System.out.println(count);
                        itemArrayList.add(new Item(documentSnapshot.getId(), documentSnapshot.getData().get("itemName").toString(), count));
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        getTheSupervisorItemList(siteDetail);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SupSelectProject.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}