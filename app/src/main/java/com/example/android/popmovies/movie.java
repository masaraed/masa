package com.example.android.popmovies;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by masa on 8/13/17.
 */

public class movie implements Parcelable {
    private String title;
    private String poster_path;
    private String original_title;
    private String overview;
    private String release_date;
    private double vote_average;
public movie(String t,String p,String ot,String ov,String re,double vo)
{
    title=t;
    poster_path=p;
    original_title=ot;
    overview=ov;
    release_date=re;
    vote_average=vo;
}

    protected movie(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        vote_average = in.readDouble();
    }

    public static final Creator<movie> CREATOR = new Creator<movie>() {
        @Override
        public movie createFromParcel(Parcel in) {
            return new movie(in);
        }

        @Override
        public movie[] newArray(int size) {
            return new movie[size];
        }
    };

    public String getTitle()
{
    return title;
}
    public String getPoster_path()
    {
        return poster_path;
    }
    public String getOriginal_title() {return original_title;}
    public String getOverview()
    {
        return overview;
    }
    public String getRelease_date()
    {
        return release_date;
    }
    public Double getVoteAverage()
    {
        return vote_average;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(original_title);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeString(poster_path);
        parcel.writeDouble(vote_average);




    }
}
