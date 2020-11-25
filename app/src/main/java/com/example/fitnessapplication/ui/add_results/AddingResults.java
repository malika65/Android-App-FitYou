package com.example.fitnessapplication.ui.add_results;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnessapplication.R;
import com.example.fitnessapplication.ui.exercise.ExerciseFragment;
import com.example.fitnessapplication.ui.profile.ProfileFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class AddingResults extends AppCompatActivity {

    private EditText min , sets;
    private Button add,back;
    FirebaseFirestore mFirebaseFirestore;
    FirebaseAuth mFirebaseAuth;
    private String userID;
    float cal = (float) 8.63;
    private TextView burned;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_results);

        min = findViewById(R.id.minut_num);
        sets = findViewById(R.id.sets_num);
        add = findViewById(R.id.add_to_fr);
        back = findViewById(R.id.go_back);

//        ProfileFragment fragment = (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.profFrag);
//        String result_1 = fragment.get();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = min.getText().toString();
                String s = sets.getText().toString();

                userID = mFirebaseAuth.getInstance().getCurrentUser().getUid();
                DocumentReference documentReference = mFirebaseFirestore.getInstance().collection("users").document(userID);
                Map<String,Object> user = new HashMap<>();

                String result = String.valueOf((Integer.parseInt(m) * Integer.parseInt(s)*cal));
                user.put("Calories",result);

                documentReference.update(user);
                finish();


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
    }
}