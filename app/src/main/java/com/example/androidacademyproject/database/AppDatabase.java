package com.example.androidacademyproject.database;

import android.content.Context;

import com.example.androidacademyproject.Author;
import com.example.androidacademyproject.Report;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Report.class, Author.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase singleton;
    private static final String DATABASE_NAME = "reports.db";
    public abstract ReportDao reportDao();
    public abstract AuthorDao authorDao();

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
