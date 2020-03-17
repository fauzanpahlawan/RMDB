package com.example.zmdb.retrofit;

import com.example.zmdb.models.Cast;
import com.example.zmdb.models.CastResponse;
import com.example.zmdb.models.Movie;
import com.example.zmdb.models.MovieGenreResponse;
import com.example.zmdb.models.MovieGenres;
import com.example.zmdb.models.TopRatedResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRequest {

    private static final String API_KEY = "7a682e187f6e1a9304cd3774e37a06a6";
    private static final String LANGUAGE = "en-US";

    private OnGetMovieCallBack onGetMovieCallBack;
    private OnGetMovieGenreCallBack onGetMovieGenreCallBack;
    private OnGetCastCallBack onGetCastCallBack;

    public void setOnGetMovieCallBack(OnGetMovieCallBack onGetMovieCallBack) {
        this.onGetMovieCallBack = onGetMovieCallBack;
    }

    public void setOnGetMovieGenreCallBack(OnGetMovieGenreCallBack onGetMovieGenreCallBack) {
        this.onGetMovieGenreCallBack = onGetMovieGenreCallBack;
    }

    public void setOnGetCastCallBack(OnGetCastCallBack onGetCastCallBack) {
        this.onGetCastCallBack = onGetCastCallBack;
    }

    private TMDBApi tmdbApi = RetrofitInstance.getRetrofitInstance().create(TMDBApi.class);

    public void getTopRatedMovie() {
        tmdbApi.getTopRatedMovies(API_KEY, LANGUAGE, 1);
        Call<TopRatedResponse> call = tmdbApi.getTopRatedMovies(API_KEY, LANGUAGE, 1);
        call.enqueue(new Callback<TopRatedResponse>() {
            @Override
            public void onResponse(Call<TopRatedResponse> call, Response<TopRatedResponse> response) {
                if (response.isSuccessful()) {
                    TopRatedResponse topRatedResponse = response.body();
                    if (topRatedResponse != null) {
                        onGetMovieCallBack.onSuccess(topRatedResponse.getMovies());
                    } else {
                        onGetMovieCallBack.onFailure();
                    }
                } else {
                    onGetMovieCallBack.onFailure();
                }
            }

            @Override
            public void onFailure(Call<TopRatedResponse> call, Throwable t) {
                onGetMovieCallBack.onFailure();
            }
        });
    }

    public void getMovieGenres() {
        tmdbApi.getMovieGenres(API_KEY, LANGUAGE);
        Call<MovieGenreResponse> call = tmdbApi.getMovieGenres(API_KEY, LANGUAGE);
        call.enqueue(new Callback<MovieGenreResponse>() {
            @Override
            public void onResponse(Call<MovieGenreResponse> call, Response<MovieGenreResponse> response) {
                if (response.isSuccessful()) {
                    MovieGenreResponse movieGenreResponse = response.body();
                    if (movieGenreResponse != null) {
                        onGetMovieGenreCallBack.onSuccess(movieGenreResponse.getGenres());
                    } else {
                        onGetMovieGenreCallBack.onFailure();
                    }
                } else {
                    onGetMovieGenreCallBack.onFailure();
                }
            }

            @Override
            public void onFailure(Call<MovieGenreResponse> call, Throwable t) {

            }
        });
    }

    public void getCast(int movie_id) {
        tmdbApi.getCast(movie_id, API_KEY);
        Call<CastResponse> call = tmdbApi.getCast(movie_id, API_KEY);
        call.enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                if (response.isSuccessful()) {
                    CastResponse castResponse = response.body();
                    if (castResponse != null) {
                        onGetCastCallBack.onSuccess(castResponse.getCasts());
                    } else {
                        onGetCastCallBack.onFailure();
                    }
                } else {
                    onGetCastCallBack.onFailure();
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                onGetCastCallBack.onFailure();
            }
        });
    }

    public interface OnGetMovieCallBack {
        void onSuccess(List<Movie> movies);

        void onFailure();
    }

    public interface OnGetMovieGenreCallBack {
        void onSuccess(List<MovieGenres> movieGenres);

        void onFailure();
    }

    public interface OnGetCastCallBack {
        void onSuccess(List<Cast> casts);

        void onFailure();
    }
}
