package com.example.androidacademyproject;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.androidacademyproject.database.model.AuthorDB;
import com.example.androidacademyproject.database.model.ReportDB;

import androidx.annotation.NonNull;

public class Report implements Parcelable{

    @NonNull
    public final String header;
    public final String room;
    public final String platform;
    public final String time;
    public final String date;
    public final String text;

    @NonNull
    public final Author author;

    public Report(String header, String room, String platform, String time, String date, String text, Author author) {
        this.header = header;
        this.room = room;
        this.platform = platform;
        this.time = time;
        this.date = date;
        this.text = text;
        this.author = author;
    }


    public Report (Parcel in){
        header = in.readString();
        room = in.readString();
        platform = in.readString();
        time = in.readString();
        date = in.readString();
        text = in.readString();
        author = in.readParcelable(Author.class.getClassLoader());

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
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(text);
        dest.writeParcelable(author, 0);
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

    public static Report reportDBtoReport (ReportDB reportDB, AuthorDB authorDB){
        Author author = new Author(
                authorDB.id,
                authorDB.avatar,
                authorDB.name,
                authorDB.post,
                authorDB.city,
                authorDB.biography);
        Report report = new Report(
                reportDB.header,
                reportDB.room,
                reportDB.platform,
                reportDB.time,
                reportDB.date,
                reportDB.text,
                author);
        return report;
    }

}
