package com.example.androidacademyproject.database;

import android.content.Context;

import com.example.androidacademyproject.database.model.AuthorDB;
import com.example.androidacademyproject.database.model.ReportDB;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ReportDB.class, AuthorDB.class}, version = 2, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase singleton;
    private static final String DATABASE_NAME = "reports.db";
    public abstract ReportDao reportDao();
    public abstract AuthorDao authorDao();
    public abstract ReportWithAuthorsDao reportWithAuthorsDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (singleton == null) {
            synchronized (AppDatabase.class) {
                if (singleton == null) {
                    singleton = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME)
                            .build();
                }
            }
        }
        return singleton;
    }
}
