package com.example.androidacademyproject;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Author implements Parcelable {

    @NonNull
    public final String id;
    public final String avatar;
    public final String name;
    public final String post;
    public final String city;
    public final String biography;

    public Author(@NonNull String id, String avatar, String name, String post, String city, String biography) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.post = post;
        this.city = city;
        this.biography = biography;
    }

    public Author(Parcel in) {
        id = in.readString();
        avatar = in.readString();
        name = in.readString();
        post = in.readString();
        city = in.readString();
        biography = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(avatar);
        dest.writeString(name);
        dest.writeString(post);
        dest.writeString(city);
        dest.writeString(biography);
    }

    public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {

        @Override
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
