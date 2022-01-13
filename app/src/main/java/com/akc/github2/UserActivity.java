package com.akc.github2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.akc.api.APIClient;
import com.akc.api.GitHubUserEndPoints;
import com.akc.model.GitHubUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    ImageView avatarImg;
    TextView userNameTV, followersTV, followingTV, logIn,email,bio, public_repo,ownedrepos;

    Bundle extras;
    String newString;

    Bitmap myImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarImg = (ImageView) findViewById(R.id.avatar);
        userNameTV = (TextView) findViewById(R.id.username);
        followersTV = (TextView) findViewById(R.id.followers);
        followingTV = (TextView) findViewById(R.id.following);
        logIn = (TextView) findViewById(R.id.login);
        email = (TextView) findViewById(R.id.email);
        bio = (TextView) findViewById(R.id.bio);
        public_repo = (TextView) findViewById(R.id.public_repos);
        ownedrepos = (TextView) findViewById(R.id.ownedReposBtn);

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        System.out.println(newString);
        loadData();
    }

    public void loadOwnRepos(View view){

        Intent intent = new Intent(UserActivity.this,Repositories.class);
        intent.putExtra("username", newString);
        startActivity(intent);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
            return null;
        }
    }


    public void loadData() {
        final GitHubUserEndPoints apiService =
                APIClient.getClient().create(GitHubUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {

            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser>
                    response) {

                ImageDownloader task = new ImageDownloader();

                try {
                    myImage = task.execute(response.body().getAvatar()).get();

                } catch (Exception e) {

                    e.printStackTrace();
                }

                avatarImg.setImageBitmap(myImage);
                avatarImg.getLayoutParams().height=220;
                avatarImg.getLayoutParams().width=220;

                if(response.body().getName() == null){
                    userNameTV.setText("No name provided");
                } else {
                    userNameTV.setText(response.body().getName());
                }
                //bio
                if (response.body().getBio()==null){
                    bio.setVisibility(View.GONE);
                }
                else {
                    bio.setText(response.body().getBio());
                }
                 public_repo.setText(response.body().getPublic_repos());
                followersTV.setText( response.body().getFollowers());
                followingTV.setText(response.body().getFollowing());
                logIn.setText(response.body().getLogin());

                if(response.body().getEmail() == null){
                    email.setVisibility(View.GONE);
                } else{
                    email.setText("Email: " + response.body().getEmail());
                }



            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                System.out.println("Failed!" + t.toString());
            }
        });
    }
}

