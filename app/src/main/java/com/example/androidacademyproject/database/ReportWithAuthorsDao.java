package com.example.androidacademyproject.database;

import com.example.androidacademyproject.database.model.ReportWithAuthor;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface ReportWithAuthorsDao {

    @Query("SELECT * FROM reportdb")
    Single<List<ReportWithAuthor>> loadReportsWithAuthors();
}
