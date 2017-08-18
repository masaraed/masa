package com.example.android.popmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class DetailActivity extends AppCompatActivity {
    TextView rDate,nameMovie,overview,rate;
    ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        rDate = (TextView) findViewById(R.id.details);
        nameMovie= (TextView) findViewById(R.id.moviename);
        rate= (TextView) findViewById(R.id.rate);

        overview=(TextView) findViewById(R.id.description);
        poster = (ImageView) findViewById(R.id.image);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movie") ){

            Movie movie = intentThatStartedThisActivity.getParcelableExtra("movie");
            String date[] = movie.getRelease_date().split("-");
            int year = Integer.parseInt(date[0]);
            rDate.setText(String.valueOf(year));
            rate.setText(movie.getVoteAverage().toString()+"/10");

            Picasso.with(DetailActivity.this).load("http://image.tmdb.org/t/p/w342/"+movie.getPoster_path()).into(poster);
            nameMovie.setText(movie.getTitle());
            overview.setText(movie.getOverview());

        }
    }
}