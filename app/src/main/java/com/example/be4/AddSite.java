package com.example.be4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddSite extends AppCompatActivity {

    public ArrayList<String> supervisorList = new ArrayList<String>();
    public ArrayList<String> supervisorIDList = new ArrayList<String>();

    public String[] items = new String[]{"Select Supervisor", "item2", "item3"};
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressBar progressBar, progressBar2;
    LinearLayout sectionForm;
    EditText editTextAddSite;
    Button addASiteBtn;

    Map<String, Object> siteMap = new HashMap<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
        progressBar = findViewById(R.id.progressBarAddSite);
        progressBar2 = findViewById(R.id.progressBarAddSite2);
        sectionForm = findViewById(R.id.sectionForm);
        editTextAddSite = findViewById(R.id.editTextAddSite);
        addASiteBtn = findViewById(R.id.addASiteBtn);
        Objects.requireNonNull(getSupportActionBar()).hide();

        progressBar.setVisibility(View.VISIBLE);
        progressBar2.setVisibility(View.GONE);

        sectionForm.setVisibility(View.GONE);
        addASiteBtn.setVisibility(View.GONE);

        supervisorList.add("Select Supervisor");
        supervisorIDList.add("No Id");

        getTheSupervisorList();


        // here you can use array or list
        ArrayAdapter adapter = new ArrayAdapter(AddSite.this, R.layout.support_simple_spinner_dropdown_item, supervisorList);
        final Spinner itemsSpinner = (Spinner) findViewById(R.id.planets_spinner);
        itemsSpinner.setAdapter(adapter);


        addASiteBtn.setOnClickListener(
                view -> {
                    if (checkTheValidate(itemsSpinner)) {
                        Toast.makeText(AddSite.this, "Validate", Toast.LENGTH_SHORT).show();
                        System.out.println(itemsSpinner.getSelectedItemPosition());
                        addASiteBtn.setVisibility(View.GONE);
                        progressBar2.setVisibility(View.VISIBLE);

                        saveTheSite(itemsSpinner.getSelectedItemPosition(), editTextAddSite.getText(), itemsSpinner);
                    } else {
                        Toast.makeText(AddSite.this, "Please Fill the Form properly", Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }

    private void saveTheSite(int selectedItemPosition, Editable text, Spinner itemsSpinner) {
        siteMap.put("siteName", text.toString());
        siteMap.put("supName", itemsSpinner.getSelectedItem());
        siteMap.put("supId", supervisorIDList.get(selectedItemPosition));
        db.collection("sites")
                .add(siteMap)
                .addOnCompleteListener(
                        new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(AddSite.this, "Successfully Added Site", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddSite.this, OwnerHomePage.class);
                                startActivity(intent);
                            }
                        }
                ).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddSite.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
    }

    private boolean checkTheValidate(Spinner itemsSpinner) {
        if (itemsSpinner.getSelectedItem().toString() == "Select Supervisor") {
            return false;
        }
        if (TextUtils.isEmpty(editTextAddSite.getText())) {
            return false;
        }
        return true;

    }

    private void getTheSupervisorList() {
        db.collection("users").get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String name;
                                        name = document.getData().get("firstName").toString() + " " + document.getData().get("lastName").toString();
                                        supervisorList.add(name);
                                        supervisorIDList.add(document.getId());
                                        progressBar.setVisibility(View.GONE);
                                        sectionForm.setVisibility(View.VISIBLE);
                                        addASiteBtn.setVisibility(View.VISIBLE);

//                                        System.out.println(document.getData().get("firstName"));
//                                        System.out.println(document.getId());
                                    }
                                }
                            }
                        }
                ).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddSite.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
    }
}