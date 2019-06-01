package com.example.androidacademyproject.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ReportDB {

    @PrimaryKey(autoGenerate = true)
    public final long id;

    public final String header;
    public final String room;
    public final String platform;
    public final String time;
    public final String date;
    public final String text;
    public final String authorId;

    public ReportDB(long id, String header, String room, String platform, String time, String date, String text, String authorId) {
        this.id = id;
        this.header = header;
        this.room = room;
        this.platform = platform;
        this.time = time;
        this.date = date;
        this.text = text;
        this.authorId = authorId;
    }
}
