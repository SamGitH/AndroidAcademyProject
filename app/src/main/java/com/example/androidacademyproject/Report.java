package com.example.androidacademyproject;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Report implements Parcelable {

    public final String header;
    public final String room;
    public final String platform;
    public final String time;
    public final String date;
    public final String text;

    @NonNull
    public final Author author;

    public Report(String header, String room, String platform, String time, String date, String text, @NonNull Author author) {
        this.header = header;
        this.room = room;
        this.platform = platform;
        this.time = time;
        this.date = date;
        this.text = text;
        this.author = author;
    }

    public Report(Parcel in) {
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
}
