package com.example.androidacademyproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.androidacademyproject.activity.AuthorActivity;
import com.example.androidacademyproject.activity.ReportActivity;
import com.example.androidacademyproject.model.DevfestModel;
import com.example.androidacademyproject.model.Speaker;
import com.example.androidacademyproject.model.Talk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReportsActivity extends Activity {

    private final List<Report> reports = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();
    //AsyncReportsLoader loader = new AsyncReportsLoader();

    private final ReportAdapter reportAdapter = new ReportAdapter(reports, new ReportAdapter.Listener() {
        @Override
        public void onReportClicked(Report report) {
            Intent intent = new Intent(ReportsActivity.this, ReportActivity.class);
            intent.putExtra("Report", report);
            startActivity(intent);
        }
        @Override
        public void onAuthorClicked(Report report){
            Intent intent = new Intent(ReportsActivity.this, AuthorActivity.class);
            intent.putExtra("Author", report);
            startActivity(intent);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        //generateReports();
        //loader.execute();

        App.getDevfestService().getData().enqueue(new Callback<DevfestModel>() {
            @Override
            public void onResponse(Call<DevfestModel> call, Response<DevfestModel> response) {
                List<Talk> talks = response.body().getSchedule().getTalks();
                List<Speaker> speakers = response.body().getSpeakers();

                for(Speaker speaker : speakers){
                    authors.add(new Author(
                            speaker.getPhoto(),
                            speaker.getFirstName() + " " + speaker.getLastName(),
                            speaker.getJobTitle() + ", " + speaker.getCompany(),
                            speaker.getLocation(),
                            speaker.getAbout(),
                            speaker.getId()
                    ));
                }

                //Author author = new Author(R.drawable.images, "Ivan Vanko", "Developer", "Moscow, Russia", "Some biography");

                for(Talk talk : talks){
                    reports.add(new Report(
                            talk.getTitle(),
                            "Room " + talk.getRoom(),
                            talk.getTrack(),
                            talk.getTime(),
                            "27 November",
                            talk.getDescription(),
                            talk.getSpeaker()
                    ));
                }

                for (Report report : reports) {
                    for (Author author : authors) {
                        if (report.getAuthorID().equals(author.getId()))
                            report.setAuthor(author);
                    }
                }
                reportAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DevfestModel> call, Throwable t) {
                Log.d("TAG", "DONE");
            }
        });

        RecyclerView recyclerView = findViewById(R.id.activity_reports_rv);
        recyclerView.setAdapter(reportAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
    }

//    private void generateReports(){
//        Author author = new Author(R.drawable.images,"Ivan Vanko","Developer","Moscow, Russia","Some biography");
//        reports.add(new Report("Web performance","Room 1","Android",author,"12:25","27 November","Some text"));
//    }

    /*
    private class AsyncReportsLoader extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //Call<VenuesResponse> call = getApp().api.getVenues();

            Author author = new Author(R.drawable.images,"Ivan Vanko","Developer","Moscow, Russia","Some biography");
            reports.add(new Report("Web performance","Room 1","Android",author,"12:25","27 November","Some text"));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
    */
}
