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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextPassword, textInputEdittextEmail;
    Button buttonsignUp;
    TextView textviewLogin;
    ProgressBar progressBar;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEdittextEmail = findViewById(R.id.email);
        buttonsignUp = findViewById(R.id.buttonSignUp);
        textviewLogin = findViewById(R.id.loginText);
        progressBar= findViewById(R.id.progress);

        textviewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonsignUp.setOnClickListener(new View.OnClickListener() {
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

        });
    }
}