package com.example.fitnessapplication.ui.exercise_description;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class JumpingJacks extends AppCompatActivity {

    EditText min, sets;
    Button add_result;
    DatabaseReference databaseReference;
    float cal = (float) 8.63;
    String calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumping_jacks);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
//        Intent intent = getIntent();
//        calories = getIntent().getExtras().getString("Name");
//        Bundle extras = getIntent().getExtras();
//        if(extras == null) {
//            Log.e("keyUser is null","");
//        } else {
//            calories= extras.getString("Calories");
//        }

//        min = findViewById(R.id.minut_num);
//        sets = findViewById(R.id.sets_num);
//        calories = min.getText().toString();
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("users");


//        add_result = findViewById(R.id.add_result1);
//        add_result.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = getIntent();
//                intent.putExtra("Calories",calories);
//                //calories = intent.getExtras().getString("Calories");
//                databaseReference = FirebaseDatabase.getInstance().getReference("users");
//
////                String m = min.getText().toString();
////                HashMap hashMap = new HashMap();
////                hashMap.put("Calories",m);
////                databaseReference.child("users").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
////                    @Override
////                    public void onSuccess(Object o) {
////                        Toast.makeText(JumpingJacks.this,"Successfully added",Toast.LENGTH_LONG).show();
////                    }
////                });

//            }
//        });
//
//
    }
}