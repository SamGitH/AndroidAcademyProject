package com.example.androidacademyproject;

import android.app.Application;

import com.example.androidacademyproject.network.DevfestService;
import com.example.androidacademyproject.network.NetworkModule;

public class App extends Application {

    private final static DevfestService devfestService = NetworkModule.getDevfestService();

    public static DevfestService getDevfestService() {
        return devfestService;
    }
}
