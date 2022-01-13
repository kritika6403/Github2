package com.akc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akc.github2.R;
import com.akc.model.GitHubIssues;
import com.akc.model.GitHubRepo;

import java.util.List;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssueViewHolder> {
    private final List<GitHubIssues> issues;
    private int rowLayout;
    private Context context;


    public IssuesAdapter(List<GitHubIssues> issues, int rowLayout, Context context) {
        this.issues = issues;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @NonNull
    @Override
    public IssuesAdapter.IssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new IssueViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull IssuesAdapter.IssueViewHolder holder, int position) {
          holder.title.setText(issues.get(position).getTitle());
          holder.status.setText(issues.get(position).getState());
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    public class IssueViewHolder extends RecyclerView.ViewHolder {
        LinearLayout issue_item_layout;
        TextView title, status;
        public IssueViewHolder(@NonNull View itemView) {
            super(itemView);
            issue_item_layout = itemView.findViewById(R.id.issues_item_layout);
            title = (TextView) itemView.findViewById(R.id.issue_title);
            status = (TextView) itemView.findViewById(R.id.issue_status);

        }
    }

}
