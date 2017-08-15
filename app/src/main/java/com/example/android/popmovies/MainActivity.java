package com.example.android.popmovies;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final int numberoftheitems = 20;
    ArrayList<movie> movies;
    private Adapter_ViewHolder.ImageViewHolder holder;
    int id=R.id.mostpopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        image = (ImageView) findViewById(R.id.thumb_image);
        GridLayoutManager gridlayout = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(gridlayout);
        mRecyclerView.setHasFixedSize(true);

        if(savedInstanceState == null) {
            FetchTask fetchTask = new FetchTask();

            try {
                movies = fetchTask.execute().get();
            }
             catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else {
            movies = savedInstanceState.getParcelableArrayList("movie");
        }

        mAdapter = new Adapter_ViewHolder(numberoftheitems, this,movies);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menu1 = getMenuInflater();
        menu1.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        id=item.getItemId();
       if(id!=R.id.mostpopular)
           return true;

       else
           {

        try {
            movies = new FetchTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            e.printStackTrace();

    }
               mAdapter = new Adapter_ViewHolder(numberoftheitems, this,movies);
             mAdapter.updateList(movies);
        return true;
    }

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", movies);
        super.onSaveInstanceState(outState);
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onListItemClick(movie movie) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("movie",movie);
        startActivity(intent);

    }


    public class FetchTask extends AsyncTask<Void, Void, ArrayList<movie>> {


        @Override
        protected ArrayList<movie> doInBackground(Void... voids) {
            URL url = NetworkUtils.buildUrl(id);

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
        protected void onPostExecute(ArrayList<movie> movies) {
            super.onPostExecute(movies);
        }
    }
}

