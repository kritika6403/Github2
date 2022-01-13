package com.akc.model;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("bio")
    private  String bio;

    @SerializedName("public_repos")
    private String public_repos;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("email")
    private String email;

    public GitHubUser(String email,
                      String avatar,
                      String public_repos,
                      String following,
                      String followers,
                      String name,
                      String bio,
                      String login) {
        this.setEmail(email);
        this.setAvatar(avatar);
        this.setBio(bio);
        this.setPublic_repos(public_repos);
        this.setFollowing(following);
        this.setFollowers(followers);
        this.setName(name);
        this.setLogin(login);
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
