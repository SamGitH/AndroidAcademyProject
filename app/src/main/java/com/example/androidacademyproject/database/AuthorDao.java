package com.example.androidacademyproject.database;

import com.example.androidacademyproject.Author;
import com.example.androidacademyproject.database.model.AuthorDB;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface AuthorDao {

    @Query("SELECT * FROM authordb")
    Observable<List<AuthorDB>> getAll();

    @Insert
    Completable insertOrReplace(@NonNull List<AuthorDB> authors);

    @Delete
    Completable delete(@NonNull AuthorDB author);

    @Query("DELETE FROM authordb")
    Completable deleteAll();
}
