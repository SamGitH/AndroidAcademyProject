package com.example.androidacademyproject.database;

import com.example.androidacademyproject.Author;
import com.example.androidacademyproject.Report;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface AuthorDao {

    @Query("SELECT * FROM report")
    Observable <List<Author>> getAll();

    @Insert
    Observable <List<Author>> insertAll(Author... authors);
    //@Query("SELECT * FROM author")
    //List<Author> getAll();

    //@Insert
    //void insertAll(Author... authors);

    @Delete
    void delete(Author author);

    @Query("DELETE FROM author")
    void deleteAll();
}
