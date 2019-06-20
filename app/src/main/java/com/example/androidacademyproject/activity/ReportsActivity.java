package com.example.androidacademyproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import com.example.androidacademyproject.App;
import com.example.androidacademyproject.Author;
import com.example.androidacademyproject.R;
import com.example.androidacademyproject.Report;
import com.example.androidacademyproject.ReportAdapter;
import com.example.androidacademyproject.database.AppDatabase;
import com.example.androidacademyproject.database.model.AuthorDB;
import com.example.androidacademyproject.database.model.ReportDB;
import com.example.androidacademyproject.fragments.AuthorFragment;
import com.example.androidacademyproject.network.model.DevfestModel;
import com.example.androidacademyproject.network.model.Speaker;
import com.example.androidacademyproject.network.model.Talk;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReportsActivity extends Activity {

    //private List<Report> reports = new ArrayList<>();
    //private List<Author> authors = new ArrayList<>();
    //AsyncReportsLoader loader = new AsyncReportsLoader();

    private final ReportAdapter reportAdapter = new ReportAdapter(new ArrayList<>(), new ReportAdapter.Listener() {
        @Override
        public void onReportClicked(Report report) {
            Intent intent = new Intent(ReportsActivity.this, ReportActivity.class);
            intent.putExtra("Report", report);
            startActivity(intent);
        }

        @Override
        public void onAuthorClicked(Report report) {
            Intent intent = new Intent(ReportsActivity.this, AuthorActivity.class);
            intent.putExtra("Author", report);
            startActivity(intent);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        //loader.execute();

        restoreData();
        loadFromWebAndPutToDB();

        RecyclerView recyclerView = findViewById(R.id.activity_reports_rv);
        recyclerView.setAdapter(reportAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void loadFromWebAndPutToDB() {
        App.getDevfestService().getData().enqueue(new Callback<DevfestModel>() {
            @Override
            public void onResponse(Call<DevfestModel> call, Response<DevfestModel> response) {
                List<ReportDB> reports = new ArrayList<>();
                List<AuthorDB> authors = new ArrayList<>();

                for (Speaker speaker : response.body().getSpeakers()) {
                    String jobTitle;
                    if (speaker.getJobTitle() == null && speaker.getId().equals("konstantin-tskhovrebov"))
                        jobTitle = "Senior Developer";
                    else
                        jobTitle = speaker.getJobTitle();
                    authors.add(new AuthorDB(
                            speaker.getId(),
                            speaker.getPhoto(),
                            speaker.getFirstName() + " " + speaker.getLastName(),
                            jobTitle + ", " + speaker.getCompany(),
                            speaker.getLocation(),
                            speaker.getAbout()
                    ));
                }

                for (Talk talk : response.body().getSchedule().getTalks()) {
                    reports.add(new ReportDB(
                            0,
                            talk.getTitle(),
                            "Room " + talk.getRoom(),
                            talk.getTrack(),
                            talk.getTime(),
                            "27 November",
                            talk.getDescription(),
                            talk.getSpeaker()
                    ));
                }

                Completable.mergeArray(
                        App.getDb().authorDao().deleteAll(),
                        App.getDb().reportDao().deleteAll()
                ).subscribeOn(Schedulers.io())
                        .andThen(Completable.mergeArray(
                                App.getDb().authorDao().insertAll(authors),
                                App.getDb().reportDao().insertAll(reports)
                        ))
                        .subscribe();
            }

            @Override
            public void onFailure(Call<DevfestModel> call, Throwable t) {
                Log.d("TAG", "DONE");
            }
        });
    }

    private void restoreData() {

        App.getDb().reportWithAuthorsDao().loadReportsWithAuthors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(list -> {
                    Log.d("dd", "gg");
                })
                .flatMapCompletable(reportsDb -> Observable.fromIterable(reportsDb)
                        .map(item -> Report.reportDBtoReport(item.report, item.author.get(0)))
                        .toList()
                        .doOnSuccess(reports -> {
                            reportAdapter.setReports(reports);
                            reportAdapter.notifyDataSetChanged();
                        })
                        .ignoreElement()
                )
                .subscribe();
    }

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
