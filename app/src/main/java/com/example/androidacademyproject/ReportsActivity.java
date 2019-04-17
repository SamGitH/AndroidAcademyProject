package com.example.androidacademyproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;



public class ReportsActivity extends Activity{
    private final List<Report> reports = new ArrayList<>();

    private final ReportAdapter reportAdapter = new ReportAdapter(reports, new ReportAdapter.Listener() {
        @Override
        public void onStudentClick(Report report) {
            startActivity();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        generateReports();

        RecyclerView recyclerView = findViewById(R.id.activity_reports_rv);
        recyclerView.setAdapter(reportAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void startActivity() {
        startActivity(new Intent(this,MainActivity.class ));
    }

    private void generateReports(){
        reports.add(new Report("Kotlin-friendly Annotation Processing","Аудитория 1","Android","Сергей Рябов","Консультант в Mobile Nomand"));
        reports.add(new Report("Kotlin-friendly Annotation Processing","Аудитория 1","Android","Сергей Рябов","Консультант в Mobile Nomand"));
        reports.add(new Report("Kotlin-friendly Annotation Processing","Аудитория 1","Android","Сергей Рябов","Консультант в Mobile Nomand"));
        reports.add(new Report("Kotlin-friendly Annotation Processing","Аудитория 1","Android","Сергей Рябов","Консультант в Mobile Nomand"));
        reports.add(new Report("Kotlin-friendly Annotation Processing","Аудитория 1","Android","Сергей Рябов","Консультант в Mobile Nomand"));
        reports.add(new Report("Kotlin-friendly Annotation Processing","Аудитория 1","Android","Сергей Рябов","Консультант в Mobile Nomand"));
    }
}
