package com.example.androidacademyproject;

import android.app.Application;

import com.example.androidacademyproject.database.AppDatabase;
import com.example.androidacademyproject.network.DevfestService;
import com.example.androidacademyproject.network.NetworkModule;
import com.facebook.stetho.Stetho;

import androidx.room.Room;

public class App extends Application {

    private final static DevfestService devfestService = NetworkModule.getDevfestService();
    private static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = AppDatabase.getAppDatabase(this);
        db = Room.databaseBuilder(this, AppDatabase.class, "reports.db")
                .fallbackToDestructiveMigration()
                .build();
        Stetho.initializeWithDefaults(this);
    }

    public static DevfestService getDevfestService() {
        return devfestService;
    }

    public static AppDatabase getDb() {
        return db;
    }
}
