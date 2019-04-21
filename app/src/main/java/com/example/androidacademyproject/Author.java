package com.example.androidacademyproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Author implements Parcelable {

    private int avatar;
    private String name;
    private String post;
    private String city;
    private String biography;

    public Author(int avatar, String name, String post, String city, String biography) {
        this.avatar = avatar;
        this.name = name;
        this.post = post;
        this.city = city;
        this.biography = biography;
    }

    public Author(Parcel in){
        avatar = in.readInt();
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
        dest.writeInt(avatar);
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

    public int getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public String getCity() {
        return city;
    }

    public String getBiography() {
        return biography;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
