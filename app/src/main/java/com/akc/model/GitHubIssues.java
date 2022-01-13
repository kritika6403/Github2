package com.akc.model;

public class GitHubIssues {
    String title;
    String state;

    public GitHubIssues(String title, String state) {
        this.title = title;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setState(String state) {
        this.state = state;
    }
}
