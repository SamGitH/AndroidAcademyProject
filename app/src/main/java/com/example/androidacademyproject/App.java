package com.example.androidacademyproject;

import android.app.Application;

import com.example.androidacademyproject.database.AppDatabase;
import com.example.androidacademyproject.network.DevfestService;
import com.example.androidacademyproject.network.NetworkModule;
import com.facebook.stetho.Stetho;

public class App extends Application {

    private final static DevfestService devfestService = NetworkModule.getDevfestService();
    private static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = AppDatabase.getAppDatabase(this);
        Stetho.initializeWithDefaults(this);
    }

    public static DevfestService getDevfestService() {
        return devfestService;
    }

    public static AppDatabase getDb() {
        return db;
    }
}
