package com.akc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.akc.github2.Issues;
import com.akc.github2.LoginActivity;
import com.akc.github2.R;
import com.akc.github2.UserActivity;
import com.akc.model.GitHubRepo;

import java.util.List;



public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder>  {

    private List<GitHubRepo> repos;
    private int rowLayout;
    private Context context;
    private OneNoteListener moneNoteListener;


    public ReposAdapter(List<GitHubRepo> repos, int rowLayout, Context context,OneNoteListener moneNoteListener) {
        this.repos= repos;
        this.rowLayout=rowLayout;
        this.context =context;
        this.moneNoteListener = moneNoteListener;
    }



    @Override
    public ReposAdapter.ReposViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(context).inflate(rowLayout, parent, false);
        return new ReposViewHolder(view, moneNoteListener);
    }


    @Override
    public void onBindViewHolder(ReposViewHolder holder, final int position) {
        holder.repoName.setText(repos.get(position).getName());
        holder.repoDescription.setText(repos.get(position).getDescription());
        holder.repolanguage.setText(repos.get(position).getLanguage());
        holder.repoIssues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),Issues.class);
                i.putExtra("repo",holder.repoName.getText().toString());
                view.getContext().startActivity(i);

            }
        });

    }

    public  interface OneNoteListener{
        void onNoteClick(int position);
    }

    @Override
    public int getItemCount() { return repos.size();}
    public static class ReposViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout reposLayout;
        TextView repoName;
        TextView repoDescription;
        TextView repolanguage;
        TextView repoIssues;
        OneNoteListener oneNoteListener;

        public ReposViewHolder(View v, OneNoteListener oneNoteListener) {
            super(v);
            reposLayout = (LinearLayout) v.findViewById(R.id.repo_item_layout);
            repoName = (TextView) v.findViewById(R.id.repoName);
            repoDescription = (TextView) v.findViewById(R.id.repoDescription);
            repolanguage = (TextView) v.findViewById(R.id.repoLanguage);
            repoIssues = (TextView) v.findViewById(R.id.repoIssues_label);
            this.oneNoteListener = oneNoteListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                oneNoteListener.onNoteClick(getAdapterPosition());
        }
    }

}

