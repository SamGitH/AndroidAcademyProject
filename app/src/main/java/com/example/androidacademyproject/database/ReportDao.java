package com.example.androidacademyproject.database;

import com.example.androidacademyproject.Report;
import com.example.androidacademyproject.database.model.ReportDB;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Observable;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ReportDao {

    @Query("SELECT * FROM reportdb")
    Observable <List<ReportDB>> getAll();

    @Insert(onConflict = REPLACE)
    Completable insertAll(List <ReportDB> reports);

    @Delete
    Completable delete(ReportDB report);

    @Query("DELETE FROM reportdb")
    Completable deleteAll();
}
