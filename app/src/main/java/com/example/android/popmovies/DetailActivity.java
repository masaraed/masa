package com.example.android.popmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    TextView dtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        dtext = (TextView) findViewById(R.id.dt_activity);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movie") ){

          movie movie = intentThatStartedThisActivity.getParcelableExtra("movie");
            dtext.setText("Title:"+movie.getTitle()+"\n"+"Original Title:"+movie.getOriginal_title()+"\n"+"Overview:"+movie.getOverview()+"\n"+"Release date"+movie.getRelease_date()+"\n"+"Vote Avarege"+movie.getVoteAverage().toString());



        }
    }
}