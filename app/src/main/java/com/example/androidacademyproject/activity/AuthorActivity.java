package com.example.androidacademyproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androidacademyproject.R;
import com.example.androidacademyproject.Report;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AuthorActivity extends Activity {

    private CircleImageView avatarIV;
    private TextView nameTV;
    private TextView postTV;
    private TextView cityTV;
    private TextView biographyTV;
    private TextView headerTV;
    private TextView roomTV;
    private TextView dateTV;
    private TextView timeTV;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        findViews();
        Report report = getIntent().getParcelableExtra("Author");
        createActivity(report);
    }

    private void findViews(){
        avatarIV = findViewById(R.id.aut_image_author);
        nameTV = findViewById(R.id.aut_tv_author);
        postTV = findViewById(R.id.aut_tv_post);
        cityTV = findViewById(R.id.aut_tv_city);
        biographyTV = findViewById(R.id.aut_tv_review);
        headerTV = findViewById(R.id.aut_tv_header);
        roomTV = findViewById(R.id.aut_tv_room);
        dateTV = findViewById(R.id.aut_tv_date);
        timeTV = findViewById(R.id.aut_tv_time);
    }

    private void createActivity(Report report){
        Picasso.get().load(report.author.avatar).into(avatarIV);
        nameTV.setText(report.author.name);
        postTV.setText(report.author.post);
        cityTV.setText(report.author.city);
        biographyTV.setText(report.author.biography);
        headerTV.setText(report.header);
        roomTV.setText(report.room);
        dateTV.setText(report.date);
        timeTV.setText(report.time);
    }
}
