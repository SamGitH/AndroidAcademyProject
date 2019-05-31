package com.example.androidacademyproject;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Report implements Parcelable{

    @PrimaryKey
    private String authorID;
    private String header;
    private String room;
    private String platform;
    private Author author;
    private String time;
    private String date;
    private String text;

    public Report(String header, String room, String platform, String time, String date, String text, String authorID) {
        this.header = header;
        this.room = room;
        this.platform = platform;
        this.time = time;
        this.date = date;
        this.text = text;
        this.authorID = authorID;
    }

    public Report(String header, String room, String platform, Author author, String time, String date, String text) {
        this.header = header;
        this.room = room;
        this.platform = platform;
        this.author = author;
        this.time = time;
        this.date = date;
        this.text = text;
    }

    public Report (Parcel in){
        header = in.readString();
        room = in.readString();
        platform = in.readString();
        author = in.readParcelable(Author.class.getClassLoader());
        time = in.readString();
        date = in.readString();
        text = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(header);
        dest.writeString(room);
        dest.writeString(platform);
        dest.writeParcelable(author, 0);
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(text);
    }

    public static final Parcelable.Creator<Report> CREATOR = new Parcelable.Creator<Report>() {

        @Override
        public Report createFromParcel(Parcel source) {
            return new Report(source);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public String getHeader() {
        return header;
    }

    public String getRoom() {
        return room;
    }

    public String getPlatform() {
        return platform;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
