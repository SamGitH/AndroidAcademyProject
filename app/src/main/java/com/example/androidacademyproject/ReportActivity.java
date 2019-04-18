package com.example.androidacademyproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReportActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        findViewById(R.id.bt_author).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_author)
            startActivity(new Intent(this, AuthorActivity.class));
    }
}
