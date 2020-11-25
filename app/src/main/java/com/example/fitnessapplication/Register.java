package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    private EditText name,email,age,weight,password;
    private Button register;
    private TextView go_to_login_btn;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fStore;
    private String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        password = findViewById(R.id.password);


        register = (Button)findViewById(R.id.register_btn);
        go_to_login_btn = findViewById(R.id.already_have_an_account);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        go_to_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });



//        if (firebaseAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//
//        }



    }

    public void register_user(View v){
        final String email_1 = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        final String name_1 = name.getText().toString();
        final String age_1 = age.getText().toString();
        final String weight_1 = weight.getText().toString();
        if(TextUtils.isEmpty(email_1)){
            email.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(pass)){
            password.setError("Password is required");
            return;
        }
        if (pass.length() < 6){
            password.setError("Password must be greater than 6 character");
        }

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email_1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Register.this,"User is registered",Toast.LENGTH_LONG).show();
                    userID = firebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("Name",name_1);
                    user.put("Email",email_1);
                    user.put("Age",age_1);
                    user.put("Weight",weight_1);
                    user.put("Calories","0");
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG,"OnSuccess: New profile is created for "+ userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG,"OnFailure: "+ e.toString());
                        }
                    });
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else{
                    Toast.makeText(Register.this,"Error! "+task.getException().getMessage() ,Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }


    }
