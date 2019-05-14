package com.example.androidacademyproject.network;

import com.example.androidacademyproject.model.DevfestModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DevfestService {

    @GET("devfestapi/data.json")
    Call<DevfestModel> getData();
}
