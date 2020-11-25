package com.example.fitnessapplication.ui.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fitnessapplication.R;
import com.example.fitnessapplication.ui.add_results.AddingResults;
import com.example.fitnessapplication.ui.exercise_description.FlutterKicks;
import com.example.fitnessapplication.ui.exercise_description.HeelTouch;
import com.example.fitnessapplication.ui.exercise_description.HighStepping;
import com.example.fitnessapplication.ui.exercise_description.JumpingJacks;
import com.example.fitnessapplication.ui.exercise_description.LegRaises;
import com.example.fitnessapplication.ui.exercise_description.MountainClimber;
import com.example.fitnessapplication.ui.exercise_description.Plank;
import com.example.fitnessapplication.ui.exercise_description.ReverseCrunches;

public class ExerciseFragment extends Fragment implements View.OnClickListener {

    private CardView cardView,card2,card3,card4,card5,card6,card7,card8;
    private Button open_adding_page;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_exercise, container, false);

        cardView = root.findViewById(R.id.card1);
        card2 = root.findViewById(R.id.card2);
        card3 = root.findViewById(R.id.card3);
        card4 = root.findViewById(R.id.card4);
        card5 = root.findViewById(R.id.card5);
        card6 = root.findViewById(R.id.card6);
        card7 = root.findViewById(R.id.card7);
        card8 = root.findViewById(R.id.card8);
        cardView.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);



        open_adding_page = root.findViewById(R.id.open_adding_page);
        open_adding_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddingResults.class);
                startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card1:
                startActivity(new Intent(getContext(), JumpingJacks.class));
                break;
            case R.id.card2:
                startActivity(new Intent(getContext(), HighStepping.class));
                break;
            case R.id.card3:
                startActivity(new Intent(getContext(), MountainClimber.class));
                break;
            case R.id.card4:
                startActivity(new Intent(getContext(), ReverseCrunches.class));
                break;
            case R.id.card5:
                startActivity(new Intent(getContext(), LegRaises.class));
                break;
            case R.id.card6:
                startActivity(new Intent(getContext(), HeelTouch.class));
                break;
            case R.id.card7:
                startActivity(new Intent(getContext(), Plank.class));
                break;
            case R.id.card8:
                startActivity(new Intent(getContext(), FlutterKicks.class));
                break;


        }

    }
}