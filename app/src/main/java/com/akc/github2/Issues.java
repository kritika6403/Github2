package com.akc.github2;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akc.adapter.IssuesAdapter;
import com.akc.api.APIClient;
import com.akc.api.GitHubIssuesEndPoint;
import com.akc.api.GitHubRepoEndPoint;
import com.akc.model.GitHubIssues;
import com.akc.model.GitHubRepo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Issues extends AppCompatActivity {
    private String receivedUserName;
    RecyclerView recyclerView;
    List<GitHubIssues> mIssues = new ArrayList<>();
    RecyclerView.Adapter adapter;
    private String reposName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);

        recyclerView =(RecyclerView) findViewById(R.id.issue_recyclerView);

        Bundle extras = getIntent().getExtras();
        receivedUserName = extras.getString("username");
        reposName = extras.getString("repos");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IssuesAdapter(mIssues, R.layout.list_item_issues,getApplicationContext());
        recyclerView.setAdapter(adapter);
            loadIssues();

    }
    public void loadIssues(){
        GitHubIssuesEndPoint apiService =
                APIClient.getClient().create( GitHubIssuesEndPoint.class);

        Call<List<GitHubIssues>> call = apiService.getIssues(receivedUserName,reposName);
        call.enqueue(new Callback<List<GitHubIssues>>() {

            @Override
            public void onResponse(Call<List<GitHubIssues>> call, Response<List<GitHubIssues>> response) {
                mIssues.clear();
                mIssues.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubIssues>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Issues", t.toString());
            }

        });
    }
}
