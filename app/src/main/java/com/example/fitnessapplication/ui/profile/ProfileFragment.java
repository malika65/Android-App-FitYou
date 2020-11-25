package com.example.fitnessapplication.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitnessapplication.Login;
import com.example.fitnessapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileFragment extends Fragment {

    private TextInputEditText fullname,age,email;
    private TextView big_name,big_email,weight,calories;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String userID;
    private ImageView logout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_profile, container, false);

       fullname = root.findViewById(R.id.full_nameTxt);
       age = root.findViewById(R.id.ageTxt);
       email = root.findViewById(R.id.emailTxt);
       weight = root.findViewById(R.id.weight_label);
       calories = root.findViewById(R.id.burned_label);



       big_name = root.findViewById(R.id.full_name);
       big_email = root.findViewById(R.id.email);
       fAuth = FirebaseAuth.getInstance();
       fStore = FirebaseFirestore.getInstance();
       userID = fAuth.getCurrentUser().getUid();
       DocumentReference documentReference = fStore.collection("users").document(userID);
       documentReference.addSnapshotListener( new EventListener<DocumentSnapshot>() {
           @Override
           public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
               fullname.setText(documentSnapshot.getString("Name"));
               email.setText(documentSnapshot.getString("Email"));
               age.setText(documentSnapshot.getString("Age"));
               big_name.setText(documentSnapshot.getString("Name"));
               big_email.setText(documentSnapshot.getString("Email"));
               weight.setText(documentSnapshot.getString("Weight"));
               calories.setText(documentSnapshot.getString("Calories"));


           }
       });

        logout = root.findViewById(R.id.logout_info);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                fStore.terminate();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
        return root;
    }


}
//
//
//  userID = mFirebaseAuth.getInstance().getCurrentUser().getUid();
//          DocumentReference documentReference = mFirebaseFirestore.getInstance().collection("users").document(userID);
//          Map<String,Object> user = new HashMap<>();
//
//        String result_1 = burned.getText().toString();
//        String result = String.valueOf((Integer.parseInt(m) * Integer.parseInt(s)*cal) + Double.parseDouble(result_1));
//        user.put("Calories",result);
//        documentReference.update(user);
//        finish();