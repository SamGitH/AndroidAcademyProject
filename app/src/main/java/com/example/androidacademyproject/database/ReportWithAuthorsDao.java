package com.example.androidacademyproject.database;

import com.example.androidacademyproject.database.model.ReportWithAuthor;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface ReportWithAuthorsDao {

    @Transaction
    @Query("SELECT * FROM reportdb")
    Observable <List<ReportWithAuthor>> loadReportsWithAuthors();
}
