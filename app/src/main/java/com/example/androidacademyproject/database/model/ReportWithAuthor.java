package com.example.androidacademyproject.database.model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ReportWithAuthor {

    @Embedded
    public ReportDB report;

    @Relation(parentColumn = "authorId", entityColumn = "id", entity = AuthorDB.class)
    public List<AuthorDB> author;
}
