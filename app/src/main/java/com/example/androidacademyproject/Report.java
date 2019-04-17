package com.example.androidacademyproject;

public class Report {
    private String header;
    private String room;
    private String platform;
    private String author;
    private String information;

    public String getHeader() {
        return header;
    }

    public String getRoom() {
        return room;
    }

    public String getPlatform() {
        return platform;
    }

    public String getAuthor() {
        return author;
    }

    public String getInformation() {
        return information;
    }

    public Report(String header, String room, String platform, String author, String information){
        this.header=header;
        this.room=room;
        this.platform=platform;
        this.author=author;
        this.information=information;
    }
}
