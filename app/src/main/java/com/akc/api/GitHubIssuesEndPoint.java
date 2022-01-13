package com.akc.api;

import com.akc.model.GitHubIssues;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubIssuesEndPoint {
    @GET("/repos/{owner}/{repo}/issues")
    Call<List<GitHubIssues>> getIssues(@Path("owner") String owner, @Path("repo") String repository);

}
