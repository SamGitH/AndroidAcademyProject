package com.example.androidacademyproject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        return new ReportViewHolder(view, onReportClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder viewHolder, int i) {
        Report report = reports.get(i);
        viewHolder.bind(report);
    }

    interface Listener{
        void onReportClicked(Report report);
        void onAuthorClicked(Report report);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    static final class ReportViewHolder extends RecyclerView.ViewHolder {

        private final TextView headerTV;
        private final TextView roomTV;
        private final TextView platformTV;
        private final Button authorBT;
        private final TextView informationTV;
        private Report report;

        public ReportViewHolder(@NonNull View itemView, final Listener onReportClickListener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onReportClickListener.onReportClicked(report);
                }
            });

            headerTV = itemView.findViewById(R.id.report_item_tv_header);
            roomTV = itemView.findViewById(R.id.report_item_tv_room);
            platformTV = itemView.findViewById(R.id.report_item_tv_android);
            authorBT = itemView.findViewById(R.id.report_item_bt_author);
            informationTV = itemView.findViewById(R.id.report_item_aut_tv_post);

            authorBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onReportClickListener.onAuthorClicked(report);
                }
            });
        }

        private void bind(@NonNull Report report) {
            this.report = report;
            headerTV.setText(report.getHeader());
            roomTV.setText(report.getRoom());
            platformTV.setText(report.getPlatform());
            authorBT.setText(report.getAuthor().getName());
            informationTV.setText(report.getAuthor().getPost() + " " + report.getAuthor().getCity());
        }

    }
}
