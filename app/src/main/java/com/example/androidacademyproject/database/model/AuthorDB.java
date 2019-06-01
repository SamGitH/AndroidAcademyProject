package com.example.androidacademyproject.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AuthorDB {

    @NonNull
    @PrimaryKey
    public final String id;
    public final String avatar;
    public final String name;
    public final String post;
    public final String city;
    public final String biography;

    public AuthorDB(String id, String avatar, String name, String post, String city, String biography) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.post = post;
        this.city = city;
        this.biography = biography;
    }
}
