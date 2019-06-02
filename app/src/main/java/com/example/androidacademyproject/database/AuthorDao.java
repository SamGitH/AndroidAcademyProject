package com.example.androidacademyproject.database;

import com.example.androidacademyproject.database.model.AuthorDB;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Observable;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AuthorDao {

    @Query("SELECT * FROM authordb")
    Observable <List<AuthorDB>> getAll();

    @Insert (onConflict = REPLACE)
    Completable insertAll(List <AuthorDB> authors);

    @Delete
    Completable delete(AuthorDB author);

    @Query("DELETE FROM authordb")
    Completable deleteAll();
}
