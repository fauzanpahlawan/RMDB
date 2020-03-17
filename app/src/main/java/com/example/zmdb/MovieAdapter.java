package com.example.zmdb;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zmdb.models.Movie;
import com.example.zmdb.models.MovieGenres;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";

    private OnItemClicCallBack onItemClicCallBack;

    private List<Movie> movies;
    private List<MovieGenres> movieGenres;

    void setOnItemClicCallBack(OnItemClicCallBack onItemClicCallBack) {
        this.onItemClicCallBack = onItemClicCallBack;
    }

    MovieAdapter(List<Movie> movies, List<MovieGenres> movieGenres) {
        this.movies = movies;
        this.movieGenres = movieGenres;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        String moviePosterUrl = IMG_BASE_URL + movie.getPoster_path();
        Glide.with(holder.itemView.getContext())
                .load(moviePosterUrl)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .override(120, 160)
                .into(holder.imgMoviePoster);
        holder.tvMovieTitle.setText(movie.getTitle());
        holder.tvMovieScore.setText(String.valueOf(movie.getVote_average()));
        holder.tvMovieGenres.setText(getMovieGenres(movie.getGenre_ids()));
        holder.tvMovieOverview.setText(movie.getOverview());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicCallBack.onItemClicked(
                        movies.get(holder.getAdapterPosition()),
                        MovieAdapter.this.getMovieGenres(movie.getGenre_ids()));
            }
        });
    }

    private String getMovieGenres(List<Integer> genreIds) {
        List<String> theMovieGenres = new ArrayList<>();
        for (MovieGenres genres : movieGenres) {
            for (int id : genreIds) {
                if (genres.getId() == id) {
                    theMovieGenres.add(genres.getName());
                }
            }
        }
        return TextUtils.join(", ", theMovieGenres);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMoviePoster;
        TextView tvMovieTitle, tvMovieGenres, tvMovieOverview, tvMovieScore;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMoviePoster = itemView.findViewById(R.id.img_item_movie_poster);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvMovieScore = itemView.findViewById(R.id.tv_movie_score);
            tvMovieGenres = itemView.findViewById(R.id.tv_movie_genre);
            tvMovieOverview = itemView.findViewById(R.id.tv_movie_overview);
        }
    }

    public interface OnItemClicCallBack {
        void onItemClicked(Movie movie, String genre);
    }
}
