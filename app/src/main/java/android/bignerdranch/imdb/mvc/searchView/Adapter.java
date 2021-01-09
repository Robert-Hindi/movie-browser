package android.bignerdranch.imdb.mvc.searchView;


import android.bignerdranch.imdb.MainActivity;
import android.bignerdranch.imdb.WebActivity;
import android.bignerdranch.imdb.mvc.model.Movie;
import android.bignerdranch.imdb.R;
import android.content.Context;
import android.content.Intent;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Movie> mMovieList;
    Context mContext;

    public Adapter(List<Movie> mMovieList){
        this.mMovieList = mMovieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Movie movie = mMovieList.get(position);

        Picasso.get().load(movie.getPoster()).into(holder.posterMovie);

        holder.titleMovie.setText(movie.getTitle());
        holder.yearMovie.setText(movie.getYear());
//        holder.urlMovie.setText(movie.getUrl());

        Linkify.addLinks(holder.urlMovie,Linkify.ALL);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,movie.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,WebActivity.class);
                intent.putExtra("url",movie.getUrl());
                intent.putExtra("genre",movie.getGenre());
                mContext.startActivity(intent);
            }

         });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView posterMovie;
        TextView titleMovie;
        TextView yearMovie;
        TextView urlMovie;


        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);

            posterMovie = (ImageView)itemView.findViewById(R.id.poster_imgView);
            titleMovie = (TextView)itemView.findViewById(R.id.title_textView);
            yearMovie = (TextView)itemView.findViewById(R.id.year_textView);
            urlMovie = (TextView) itemView.findViewById(R.id.link_textView);

            cv = (CardView)itemView.findViewById(R.id.card_view);
        }
    }
}

