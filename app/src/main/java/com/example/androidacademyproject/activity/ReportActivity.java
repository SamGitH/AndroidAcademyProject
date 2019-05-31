package com.example.androidacademyproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidacademyproject.R;
import com.example.androidacademyproject.Report;
import com.example.androidacademyproject.ReportsActivity;

public class ReportActivity extends Activity implements View.OnClickListener{

    private final int BUT_AUTHOR = R.id.bt_author;
    private final int BUT_REPORTS = R.id.bt_report;
    private Report report;

    private TextView timeTV;
    private TextView dateTV;
    private TextView headerTV;
    private TextView roomTV;
    private TextView platformTV;
    private Button nameBT;
    private TextView textTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        findViewById(BUT_AUTHOR).setOnClickListener(this);
        findViewById(BUT_REPORTS).setOnClickListener(this);
        findViews();
        report = getIntent().getParcelableExtra("Report");
        createActivity(report);
    }

    private void findViews(){
        timeTV = findViewById(R.id.tv_time);
        dateTV = findViewById(R.id.tv_date);
        headerTV = findViewById(R.id.tv_header);
        roomTV = findViewById(R.id.tv_room);
        platformTV = findViewById(R.id.tv_android);
        nameBT = findViewById(R.id.bt_author);
        textTV = findViewById(R.id.tv_main_text);
    }

    private void createActivity(Report report){
        timeTV.setText(report.getTime());
        dateTV.setText(report.getDate());
        headerTV.setText(report.getHeader());
        roomTV.setText(report.getRoom());
        //if(report.getPlatform().equals("common"))
        //    platformTV.setBackground(R.drawable.ic_rectangle_mediumorchid);
        platformTV.setText(report.getPlatform());
        nameBT.setText(report.getAuthor().getName());
        textTV.setText(report.getText());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == BUT_AUTHOR) {
            Intent intent = new Intent(ReportActivity.this, AuthorActivity.class);
            intent.putExtra("Author", report);
            startActivity(intent);
        }
        else if (v.getId() == BUT_REPORTS)
            startActivity(new Intent(this, ReportsActivity.class));
    }
}
