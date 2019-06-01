package com.example.androidacademyproject.database;

import com.example.androidacademyproject.Report;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface ReportDao {

    @Query("SELECT * FROM report")
    Observable <List<Report>> getAll();

    @Insert
    Observable <List<Report>> insertAll(Report... reports);
    //@Query("SELECT * FROM report")
    //List<Report> getAll();

    //@Insert
    //void insertAll(Report... reports);

    @Delete
    void delete(Report report);

    @Query("DELETE FROM report")
    void deleteAll();
}
