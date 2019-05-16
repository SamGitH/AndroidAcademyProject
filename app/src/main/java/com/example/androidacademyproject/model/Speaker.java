package com.example.androidacademyproject.model;

public class Speaker {

    String id;
    String firstName;
    String lastName;
    String location;
    String jobTitle;
    String company;
    String about;
    String photo;
    String flagImage;

    Links links;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLocation() {
        return location;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getAbout() {
        return about;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFlagImage() {
        return flagImage;
    }

    public Links getLinks() {
        return links;
    }
}
