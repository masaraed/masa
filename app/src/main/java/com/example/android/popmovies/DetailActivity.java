package com.example.android.popmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView dtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        dtext = (TextView) findViewById(R.id.dt_activity);
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movieobj") ){

            movie movie = (movie)intentThatStartedThisActivity.getParcelableExtra("movieobj");


            dtext.setText(movie.getTitle());

        }
    }
}