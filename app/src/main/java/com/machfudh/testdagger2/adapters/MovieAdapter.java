package com.machfudh.testdagger2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.machfudh.testdagger2.R;
import com.machfudh.testdagger2.models.Movie;
import com.machfudh.testdagger2.models.callbacks.CallbakMovie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {

    private final LayoutInflater inflater;
    private List<Movie> movieList;
    private MovieClickListener clickListener;

    public MovieAdapter(MovieClickListener clickListener, LayoutInflater inflater) {
        this.clickListener = clickListener;
        this.inflater = inflater;
        movieList = new ArrayList<>();
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(this.inflater.inflate(R.layout.item_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Movie movie = movieList.get(position);

        holder.mTitle.setText(movie.getTitle());
        holder.mYear.setText(movie.getYear());
        holder.mType.setText(movie.getType());

        Glide.with(holder.itemView.getContext()).load(movie.getPoster()).into(holder.mPoster);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void addMovie(List<Movie> search) {
        movieList.addAll(search);
        notifyDataSetChanged();

    }

    public class Holder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private ImageView mPoster;
        private TextView mTitle, mYear, mType;

        public Holder(View itemView) {
            super(itemView);
            mPoster = (ImageView) itemView.findViewById(R.id.moviePosdter);
            mTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            mYear = (TextView) itemView.findViewById(R.id.movieYear);
            mType = (TextView) itemView.findViewById(R.id.movieType);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(getLayoutPosition());

        }
    }

    public interface MovieClickListener {

        void onClick(int position);
    }

}
