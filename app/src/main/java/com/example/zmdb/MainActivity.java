package com.example.zmdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zmdb.models.Movie;
import com.example.zmdb.models.MovieGenres;
import com.example.zmdb.retrofit.MovieRequest;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieRequest movieRequest = new MovieRequest();
    private ProgressBar pbMovieData;
    private RecyclerView rvMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Top Rated Movie");

        pbMovieData = findViewById(R.id.pb_movie_data);
        rvMovies = findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        movieRequest.setOnGetMovieGenreCallBack(new MovieRequest.OnGetMovieGenreCallBack() {
            @Override
            public void onSuccess(List<MovieGenres> movieGenres) {
                getMovies(movieGenres);
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        movieRequest.getMovieGenres();
    }

    private void getMovies(final List<MovieGenres> movieGenres) {
        movieRequest.setOnGetMovieCallBack(new MovieRequest.OnGetMovieCallBack() {
            @Override
            public void onSuccess(List<Movie> movies) {
                MovieAdapter movieAdapter = new MovieAdapter(movies, movieGenres);
                rvMovies.setAdapter(movieAdapter);
                movieAdapter.notifyDataSetChanged();
                pbMovieData.setVisibility(View.INVISIBLE);
                movieAdapter.setOnItemClicCallBack(new MovieAdapter.OnItemClicCallBack() {
                    @Override
                    public void onItemClicked(Movie movie, String genre) {
                        Intent movieDetailActivity = new Intent(MainActivity.this, MovieDetailActivity.class);
                        movieDetailActivity.putExtra("movie_id", movie.getId());
                        movieDetailActivity.putExtra("poster_path", movie.getPoster_path());
                        movieDetailActivity.putExtra("backdrop_path", movie.getBackdrop_path());
                        movieDetailActivity.putExtra("movie_title", movie.getTitle());
                        movieDetailActivity.putExtra("movie_score", movie.getVote_average());
                        movieDetailActivity.putExtra("movie_genres", genre);
                        movieDetailActivity.putExtra("movie_overview", movie.getOverview());
                        movieDetailActivity.putExtra("movie_release_date", movie.getRelease_date());
                        startActivity(movieDetailActivity);
                    }
                });
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        movieRequest.getTopRatedMovie();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void intentAboutMe(MenuItem item) {
        Intent aboutActivityIntent = new Intent(this, AboutActivity.class);
        startActivity(aboutActivityIntent);
    }
}
