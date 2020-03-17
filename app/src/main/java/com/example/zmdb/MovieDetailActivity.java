package com.example.zmdb;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zmdb.models.Cast;
import com.example.zmdb.retrofit.MovieRequest;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";

    private ImageView imgMovieBackdrop;
    private ImageView imgItemMoviePoster;
    private TextView tvMovieTitle;
    private TextView tvMovieScore;
    private TextView tvMovieGenre;
    private TextView tvMovieOverview;
    private TextView tvMovieReleaseDate;
    private ProgressBar pbCastData;
    private RecyclerView rvCast;
    private MovieRequest movieRequest = new MovieRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setTitle("Movie Detail");

        imgMovieBackdrop = findViewById(R.id.img_movie_backdrop);
        imgItemMoviePoster = findViewById(R.id.img_item_movie_poster);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvMovieScore = findViewById(R.id.tv_movie_score);
        tvMovieGenre = findViewById(R.id.tv_movie_genre);
        tvMovieOverview = findViewById(R.id.tv_movie_overview);
        tvMovieReleaseDate = findViewById(R.id.tv_movie_release_date);
        rvCast = findViewById(R.id.rv_cast);
        pbCastData = findViewById(R.id.pb_cast_data);

        int movieId = getIntent().getIntExtra("movie_id", 0);
        String poster_path = getIntent().getStringExtra("poster_path");
        String backdrop_path = getIntent().getStringExtra("backdrop_path");
        String movie_title = getIntent().getStringExtra("movie_title");
        float movie_score = getIntent().getFloatExtra("movie_score", 0);
        String movie_genres = getIntent().getStringExtra("movie_genres");
        String movie_overview = getIntent().getStringExtra("movie_overview");
        String movie_release_date = getIntent().getStringExtra("movie_release_date");

        Glide.with(this)
                .load(IMG_BASE_URL + poster_path)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(imgItemMoviePoster);

        Glide.with(this)
                .load(IMG_BASE_URL + backdrop_path)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(imgMovieBackdrop);

        tvMovieTitle.setText(movie_title);
        tvMovieScore.setText(String.valueOf(movie_score));
        tvMovieGenre.setText(movie_genres);
        tvMovieOverview.setText(movie_overview);
        tvMovieReleaseDate.setText(movie_release_date);

        rvCast.setHasFixedSize(true);
        rvCast.setLayoutManager(new LinearLayoutManager(this));

        movieRequest.setOnGetCastCallBack(new MovieRequest.OnGetCastCallBack() {
            @Override
            public void onSuccess(List<Cast> castList) {
                CastAdapter castAdapter = new CastAdapter(castList);
                rvCast.setAdapter(castAdapter);
                castAdapter.notifyDataSetChanged();
                pbCastData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure() {

            }
        });

        movieRequest.getCast(movieId);
    }
}
