package com.example.android.popmovies;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adapter_ViewHolder extends RecyclerView.Adapter<Adapter_ViewHolder.ImageViewHolder>  {
   int numberofitems;
    private ListItemClickListner mOnClickListner;
        private ArrayList<movie> movies;


    public Adapter_ViewHolder(int Numberofitems,ListItemClickListner listner,ArrayList<movie> movies1)
   {
       numberofitems=Numberofitems;
       mOnClickListner=listner;
       movies=movies1;
   }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int inflated=R.layout.image_list;
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(inflated,parent,false);
        return new ImageViewHolder(view);

    }
    public void updateList(ArrayList<movie> movies2) {
        movies = movies2;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
    holder.bind();
    }




    @Override
    public int getItemCount() {
return numberofitems;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

       ImageView m;
        Context context;




       public ImageViewHolder(View view)
       {
           super(view);
           m= view.findViewById(R.id.thumbimage);

           m.setOnClickListener(this);
           m.setAdjustViewBounds(true);
           context = view.getContext();

       }
       public void bind()
       {
           movie  movie =movies.get(getAdapterPosition());

           Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+movie.getPoster_path()).into(m);



       }


            @Override
            public void onClick(View v) {
                movie  movie =movies.get(getAdapterPosition());
                mOnClickListner.onListItemClick(movie);


            }

    }

   public interface ListItemClickListner{
       void onListItemClick(movie movies);

   }


}
