package com.example.androidacademyproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NetworkModule {

    private static final String BASE_URL = "https://storage.yandexcloud.net/";
    private Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit;
    public FoursquareService foursquareService = retrofit.create(FoursquareService.class);

    class VenuesResponse {
        List<Group> groups;

        class Group {
            List<Item> items;
        }

        class Item {
            Venue venue;
        }
    }

    public interface FoursquareService {
        @GET("devfestapi/data.json")
        Call<VenuesResponse> getVenues(String location);
    }
}
