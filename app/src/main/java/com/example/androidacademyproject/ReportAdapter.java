package com.example.androidacademyproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReportAdapter extends  RecyclerView.Adapter<ReportAdapter.ReportViewHolder>{

    private final List<Report> reports ;
    private final Listener onReportClickListener;

    public ReportAdapter(List<Report> reports, Listener onReportClickListener) {
        this.reports = reports;
        this.onReportClickListener = onReportClickListener;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.report_item, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReportClickListener.onStudentClick((Report) v.getTag());
            }
        });
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder viewHolder, int i) {
        Report report = reports.get(i);
        viewHolder.bind(report);
        viewHolder.itemView.setTag(report);
    }

    interface Listener{
        void onStudentClick(Report report);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    static final class ReportViewHolder extends RecyclerView.ViewHolder {

        private final TextView headerTV;
        private final TextView roomTV;
        private final TextView platformTV;
        private final TextView authorTV;
        private final TextView informationTV;


        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTV = itemView.findViewById(R.id.report_item_tv_header);
            roomTV = itemView.findViewById(R.id.report_item_tv_room);
            platformTV = itemView.findViewById(R.id.report_item_tv_android);
            authorTV = itemView.findViewById(R.id.report_item_tv_author);
            informationTV = itemView.findViewById(R.id.report_item_aut_tv_post);
        }

        private void bind(@NonNull Report report) {
            headerTV.setText(report.getHeader());
            roomTV.setText(report.getRoom());
            platformTV.setText(report.getPlatform());
            authorTV.setText(report.getAuthor());
            informationTV.setText(report.getInformation());
        }

    }
}
