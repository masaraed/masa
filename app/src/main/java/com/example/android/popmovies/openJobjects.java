package com.example.android.popmovies;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.util.ArrayList;


public class openJobjects {
    public static ArrayList<movie> getSimpleStringFromJson(Context context, String httpstring)
            throws JSONException {

        final String LIST = "results";

        ArrayList<movie> parsedData = new ArrayList<movie>(20);

        JSONObject movieJson = new JSONObject(httpstring);

        if (movieJson.has("cod")) {
            int errorCode = movieJson.getInt("cod");

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return null;
                default:
                    return null;
            }
        }

        JSONArray movieArray = movieJson.getJSONArray(LIST);


        for (int i = 0; i < 20; i++) {
            String title;
            String poster_path;
            String original_title;
            String overview;
            String release_date;
            double vote_average;

            JSONObject moviej = movieArray.getJSONObject(i);


            title = moviej.getString("title");
            poster_path= moviej.getString("poster_path");
            original_title= moviej.getString("original_title");
            overview= moviej.getString("overview");
            release_date= moviej.getString("release_date");
            vote_average= moviej.getDouble("vote_average");

            movie m=new movie(title,poster_path,original_title,overview,release_date,vote_average);
            parsedData.add(m);

        }

        return parsedData;
    }

}