package com.example.androidacademyproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.androidacademyproject.App;
import com.example.androidacademyproject.Author;
import com.example.androidacademyproject.R;
import com.example.androidacademyproject.Report;
import com.example.androidacademyproject.ReportAdapter;
import com.example.androidacademyproject.database.AppDatabase;
import com.example.androidacademyproject.model.DevfestModel;
import com.example.androidacademyproject.model.Speaker;
import com.example.androidacademyproject.model.Talk;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReportsActivity extends AppCompatActivity {

    private List<Report> reports = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();

    //AsyncReportsLoader loader = new AsyncReportsLoader();

    private final ReportAdapter reportAdapter = new ReportAdapter(reports, new ReportAdapter.Listener() {
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


//        App.getDevfestService().getData().enqueue(new Callback<DevfestModel>() {
//            @Override
//            public void onResponse(Call<DevfestModel> call, Response<DevfestModel> response) {
//                List<ReportDB> reportDBS = new ArrayList<>();
//                List<AuthorDB> authorDBS = new ArrayList<>();
//
//                for (Speaker speaker : response.body().getSpeakers()) {
//                    authorDBS.add(new AuthorDB(
//                            speaker.getId(),
//                            speaker.getPhoto(),
//                            speaker.getFirstName() + " " + speaker.getLastName(),
//                            speaker.getJobTitle(),
//                            speaker.getLocation(),
//                            speaker.getAbout()
//                    ));
//                }
//
//                for (Talk talk : response.body().getSchedule().getTalks()) {
//                    reportDBS.add(new ReportDB(
//                                    0,
//                                    talk.getTitle(),
//                                    talk.getRoom(),
//                                    talk.getTrack(),
//                                    talk.getTime(),
//                                    talk.getTime(),
//                                    talk.getDescription(),
//                                    talk.getSpeaker()
//                            )
//                    );
//                }
//
//                Completable.mergeArray(
//                        App.getDb().authorDao().insertOrReplace(authorDBS),
//                        App.getDb().reportDao().insertOrReplace(reportDBS)
//                ).subscribeOn(Schedulers.io())
//                        .subscribe();
//            }
//
//            @Override
//            public void onFailure(Call<DevfestModel> call, Throwable t) {
//
//            }
//        });




        App.getDb().reportWithAuthorsDao().loadReportsWithAuthors()
                .subscribeOn(Schedulers.io())
                .doOnSuccess(list -> {
                    Log.d("TAG", "SUCCESS!!!");
                })
                .observeOn(Schedulers.computation())
                .flatMapObservable(list -> Observable.fromIterable(list))
                .doOnNext(item -> {
                    Log.d("TAG", "SUCCESS!!!");
                })
                .subscribe();
    }

    void oldOnCreate() {
        //generateReports();
        //loader.execute();

        final AppDatabase db = AppDatabase.getAppDatabase(this);

        restoreData(db);
        App.getDevfestService().getData().enqueue(new Callback<DevfestModel>() {
            @Override
            public void onResponse(Call<DevfestModel> call, Response<DevfestModel> response) {
                List<Talk> talks = response.body().getSchedule().getTalks();
                List<Speaker> speakers = response.body().getSpeakers();
                List<Report> reports = new ArrayList<>();
                List<Author> authors = new ArrayList<>();
                //db.reportDao().deleteAll();

                for (Speaker speaker : speakers) {
                    String jobTitle;
                    if (speaker.getJobTitle() == null && speaker.getId().equals("konstantin-tskhovrebov"))
                        jobTitle = "Senior Developer";
                    else
                        jobTitle = speaker.getJobTitle();
                    authors.add(new Author(
                            speaker.getPhoto(),
                            speaker.getFirstName() + " " + speaker.getLastName(),
                            jobTitle + ", " + speaker.getCompany(),
                            speaker.getLocation(),
                            speaker.getAbout(),
                            speaker.getId()
                    ));
                }

//                for(Talk talk : talks){
//                    reports.add(new Report(
//                            talk.getTitle(),
//                            "Room " + talk.getRoom(),
//                            talk.getTrack(),
//                            talk.getTime(),
//                            "27 November",
//                            talk.getDescription(),
//                            talk.getSpeaker()
//                    ));
//                }

//                Disposable subscribeReport = db.reportDao().insertAll(reports.toArray(new Report[reports.size()]))
////                        .subscribeOn(Schedulers.io())
////                        .subscribe();
////                Disposable subscribeAuthor = db.authorDao().insertAll(authors.toArray(new Author[authors.size()]))
////                        .subscribeOn(Schedulers.io())
////                        .subscribe();
                //reportAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DevfestModel> call, Throwable t) {
                Log.d("TAG", "DONE");
            }
        });

//        RecyclerView recyclerView = findViewById(R.id.activity_reports_rv);
//        recyclerView.setAdapter(reportAdapter);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void restoreData(AppDatabase db) {
//        Disposable subscribeReport = db.reportDao().getAll()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(reportsGet -> {
//                    reports = reportsGet;
//                    for (Report report : reports) {
//                        for (Author author : authors) {
//                            if (report.author.id.equals(author.id))
//                                report.au(author);
//                        }
//                    }
//                    reportAdapter.notifyDataSetChanged();
//                });
//        Disposable subscribeAuthor = db.authorDao().getAll()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(authorsGet -> {
//                    authors = authorsGet;
//                    for (Report report : reports) {
//                        for (Author author : authors) {
//                            if (report.getAuthorID().equals(author.getId()))
//                                report.setAuthor(author);
//                        }
//                    }
//                    reportAdapter.notifyDataSetChanged();
//                });
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
