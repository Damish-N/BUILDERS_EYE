package com.example.be4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    TextInputEditText textInputEditFName, textInputEditTextLName, textInputEditTextPassword, textInputEdittextEmail;
    Button buttonsignUp;
    TextView textviewLogin;
    ProgressBar progressBar;
    Map<String, Object> user = new HashMap<>();

    private FirebaseAuth mAuth;

    DatabaseReference reference;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();


        textInputEditFName = findViewById(R.id.Fname);
        textInputEditTextLName = findViewById(R.id.textInputLastName);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEdittextEmail = findViewById(R.id.email);
        buttonsignUp = findViewById(R.id.buttonSignUp);
        textviewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        textviewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonsignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fNameUser, lNameUser, passwordUser, emailUser;
                        fNameUser = String.valueOf(textInputEditFName.getText());
                        lNameUser = String.valueOf(textInputEditTextLName.getText());
                        passwordUser = String.valueOf(textInputEditTextPassword.getText());
                        emailUser = String.valueOf(textInputEdittextEmail.getText());


                        user.put("firstName", fNameUser);
                        user.put("lastName", lNameUser);
                        user.put("email", emailUser);


                        mAuth.createUserWithEmailAndPassword(emailUser, passwordUser)
                                .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {


                                            db.collection("users")
                                                    .add(user)
                                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                                            Toast.makeText(Signup.this, "success", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(Signup.this, Login.class);
                                                            startActivity(intent);
                                                        }
                                                    }).addOnFailureListener(
                                                            new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(Signup.this, "Fail", Toast.LENGTH_SHORT).show();

                                                                }
                                                            }
                                                    );


                                            // Sign in success, update UI with the signed-in user's information
                                            FirebaseUser user = mAuth.getCurrentUser();

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(Signup.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    }
                }
        );





       /* buttonsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, username, password, email;
                fullname = String.valueOf(textInputEditTextFullname.getText());
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                email = String.valueOf(textInputEdittextEmail.getText());





                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (!(dataSnapshot.child("Users").child(username).exists())){

                            HashMap<String,Object> userdataMap =new HashMap<>();
                            userdataMap.put("password",password);
                            userdataMap.put("name",fullname);
                            userdataMap.put("email",email);
                            userdataMap.put("Steel frames",a);
                            userdataMap.put("Escalator",b);
                            userdataMap.put("Concrete Mixer",c);
                            userdataMap.put("earth Compactor",d);
                            userdataMap.put("Steel Bar cutting machine",e);
                            userdataMap.put("Concrete Pumps",f);

                           //userdataMap.put("username",username);


                            reference.child("Users").child(username).updateChildren(userdataMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(), "Congratulations, your account has been created", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                                startActivity(intent);

                                            }else {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(getApplicationContext(), "Network ERROR! Please try again later", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });


                        }else {
                            Toast.makeText(getApplicationContext(), "This"+username+"number already exists", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Please try again using another phone number", Toast.LENGTH_SHORT).show();
                            //Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                            //startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        });*/
    }
}