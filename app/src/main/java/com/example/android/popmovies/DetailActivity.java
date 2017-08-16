package com.example.android.popmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    TextView dtext,namemovie,description;
    ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        dtext = (TextView) findViewById(R.id.details);
        namemovie= (TextView) findViewById(R.id.moviename);
        description=(TextView) findViewById(R.id.description);
        poster = (ImageView) findViewById(R.id.image);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movie") ){

          movie movie = intentThatStartedThisActivity.getParcelableExtra("movie");
            dtext.setText(movie.getRelease_date()+"\n"+movie.getVoteAverage().toString());
            Picasso.with(DetailActivity.this).load("http://image.tmdb.org/t/p/w185/"+movie.getPoster_path()).resize(185 , 278).into(poster);
            namemovie.setText(movie.getTitle());
           description.setText(movie.getOverview());

        }
    }
}