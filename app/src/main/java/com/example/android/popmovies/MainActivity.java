package com.example.android.popmovies;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements Adapter_ViewHolder.ListItemClickListner {
    RecyclerView mRecyclerView;
    Adapter_ViewHolder mAdapter;
    ImageView image;
    private Toast mtoast;
    private static final int NUM_ITEMS = 20;
    ArrayList<Movie> movies;
    private Adapter_ViewHolder.ImageViewHolder holder;
    String popular="popular";
    String toprated="top_rated";
    GridLayoutManager gridlayout;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        image = (ImageView) findViewById(R.id.thumbimage);
        savedInstanceState=bundle;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            gridlayout = new GridLayoutManager(MainActivity.this, 4);
            updatelayout(gridlayout, savedInstanceState,popular);
        }
        else
        {
            gridlayout = new GridLayoutManager(MainActivity.this, 2);
            updatelayout(gridlayout, savedInstanceState,popular);
        }


    }


    public void updatelayout(GridLayoutManager gridlayout,Bundle savedInstanceState,String s)
    {
        if(!online())
            mtoast.makeText(MainActivity.this,"This app need Internet Connection",Toast.LENGTH_LONG).show();
        else {
            mRecyclerView.setLayoutManager(gridlayout);


            if (bundle == null) {
                FetchTask fetchTask = new FetchTask();

                try {
                    movies = fetchTask.execute(s).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                movies = savedInstanceState.getParcelableArrayList("movie");
            }

            mAdapter = new Adapter_ViewHolder(NUM_ITEMS, this, movies);
            mRecyclerView.setAdapter(mAdapter);
        }

    }

    public boolean online()
    {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menu1 = getMenuInflater();
        menu1.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ((item.getItemId()) == R.id.mostpopular) {
            updatelayout(gridlayout, bundle, popular);
        } else
            updatelayout(gridlayout, bundle, toprated);
        return true;

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", movies);
        super.onSaveInstanceState(outState);
    }



    @Override
    public void onListItemClick(Movie movie) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("movie",movie);
        startActivity(intent);

    }




    public class FetchTask extends AsyncTask<String, Void, ArrayList<Movie>> {


        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {
            NetworkUtils.string=strings[0];
            URL url = NetworkUtils.buildUrl();


            try {

                String httprespondString = NetworkUtils.getResponseFromHttpUrl(url);
                movies = openJobjects.getSimpleStringFromJson(MainActivity.this, httprespondString);
                return movies;

            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
        }
    }
}