package com.example.android.popmovies;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by masa on 8/13/17.
 */

public class NetworkUtils {
    final static String BASE_URL =
            "http://api.themoviedb.org/3/movie/";
    final static String apikey="api_key=0d8a00c9715591d453d2184f3aab8cae";
     public static String string;

    public static URL buildUrl() {

        Uri builtUri=null;

            builtUri= Uri.parse(BASE_URL).buildUpon().appendPath(string).appendQueryParameter("api_key","0d8a00c9715591d453d2184f3aab8cae")
                    .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}