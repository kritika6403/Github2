package com.akc.model;

import com.google.gson.annotations.SerializedName;

public class GitHubRepo {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    @SerializedName("owner")
    private  String owner;

    @SerializedName("fullname")
    private String fullname;

    public GitHubRepo(String fullname) {
        this.setFullname(fullname);
    }

    public GitHubRepo(
            String language,
            String description,
            String owner,
            String name) {

        this.setLanguage(language);
        this.setDescription(description);
        this.setName(name);
        this.setOwner(owner);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
