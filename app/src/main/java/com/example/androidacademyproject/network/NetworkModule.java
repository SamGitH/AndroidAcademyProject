package com.example.androidacademyproject.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {

    private static final String BASE_URL = "https://storage.yandexcloud.net/";
    public static DevfestService getDevfestService(){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        return retrofit.create(DevfestService.class);
    }
}
