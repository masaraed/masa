package com.example.android.popmovies;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie implements Parcelable {

    private String title;
    private String posterPath;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private double voteAverage;
    public Movie(String t, String pp, String ot, String ov, String rd, double va)
    {
        title=t;
        posterPath=pp;
        originalTitle=ot;
        overview=ov;
        releaseDate=rd;
        voteAverage=va;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        posterPath = in.readString();
        voteAverage = in.readDouble();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle()
    {
        return title;
    }
    public String getPoster_path()
    {
        return posterPath;
    }
    public String etOriginal_title() {return originalTitle;}
    public String getOverview()
    {
        return overview;
    }
    public String getRelease_date() {
        return releaseDate;
    }
    public Double getVoteAverage()
    {
        return voteAverage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(originalTitle);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeDouble(voteAverage);




    }
}

