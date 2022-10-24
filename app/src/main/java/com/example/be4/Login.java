package com.example.be4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static java.lang.String.valueOf;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView textviewSignup;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    //    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://builder-b02b5-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();
        Map<String,Object> users = new HashMap<>();
        users.put("f","jskdjdsd");


        textInputEditTextUsername = findViewById(R.id.usernamelogin);
        textInputEditTextPassword = findViewById(R.id.passwordlogin);
        buttonLogin = findViewById(R.id.buttonLogin);
        textviewSignup = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        textviewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password, email;
                email = valueOf(textInputEditTextUsername.getText());
                password = valueOf(textInputEditTextPassword.getText());

//                firebaseFirestore.collection("test")
//                        .add(users).addOnSuccessListener(
//                                new OnSuccessListener<DocumentReference>() {
//                                    @Override
//                                    public void onSuccess(DocumentReference documentReference) {
//                                        Toast.makeText(Login.this, "success", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                        ).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Login.this, "success", Toast.LENGTH_SHORT).show();
//                            }
//                        });





                if (!email.isEmpty() && !password.isEmpty()) {
                    textInputEditTextUsername.setEnabled(false);
                    textInputEditTextPassword.setEnabled(false);
                    progressBar.setVisibility(View.VISIBLE);
                    buttonLogin.setEnabled(false);


                    mAuth.signInWithEmailAndPassword("d@gmail.com", "12345678")
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                        textInputEditTextUsername.setEnabled(true);
                                        textInputEditTextPassword.setEnabled(true);
                                        buttonLogin.setEnabled(true);
                                        Toast.makeText(Login.this, "Login Unsurenesss", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                } else {
                    Toast.makeText(Login.this, "User name or password Field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String username, password, email;
//                username = valueOf(textInputEditTextUsername.getText());
//                password = valueOf(textInputEditTextPassword.getText());
//
//                if (!username.equals("") && !password.equals("")) {
//                    progressBar.setVisibility(View.VISIBLE);
//
//                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.hasChild(username)) {
//                                final String getPassword = snapshot.child(username).child("password").getValue(String.class);
//                                if (getPassword.equals(password)) {
//                                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
//
//                                    //Intent intent = new Intent(Login.this, MainStore.class);
//                                    //intent.putExtra("keyusername",username);
//                                    //startActivity(intent);
//                                    startActivity(new Intent(Login.this, MainStore.class));
//                                    finish();
//                                } else {
//                                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Such user is not exsist", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                        }
//
//                    });
//                } else {
//                    Toast.makeText(getApplicationContext(), "all fields are required", Toast.LENGTH_SHORT).show();
//                }
//                progressBar.setVisibility(View.GONE);
//            }
//        });
    }
}
