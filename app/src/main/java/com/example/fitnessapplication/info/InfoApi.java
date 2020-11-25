package com.example.fitnessapplication.info;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InfoApi {

    @GET("v3/e7a36537-572a-420c-a30d-559a2ddeddb6")
    Call<List<Info>> getInfo();
}
