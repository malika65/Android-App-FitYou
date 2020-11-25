package com.example.fitnessapplication.ui.about;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapplication.R;
import com.example.fitnessapplication.info.Adaptery;
import com.example.fitnessapplication.info.Info;
import com.example.fitnessapplication.info.InfoApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AboutFragment extends Fragment {

    RecyclerView recyclerView;
    List<Info> infoList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewInfo);
        infoList = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InfoApi infoApi = retrofit.create(InfoApi.class);
        Call<List<Info>> call = infoApi.getInfo();
        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                if(response.code() != 200){
                    return;// handle error and display it
                }

                List<Info> infos = response.body();

                for (Info info : infos){

                    infoList.add(info);

                }
                PutDataIntoRecyclerView(infoList);


            }

            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {

            }
        });
        return root;
    }

    private void PutDataIntoRecyclerView(List<Info> infoList) {
        Adaptery adaptery = new Adaptery(getContext(),infoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptery);

    }
}