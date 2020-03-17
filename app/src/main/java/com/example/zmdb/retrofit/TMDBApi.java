package com.example.zmdb.retrofit;

import com.example.zmdb.models.CastResponse;
import com.example.zmdb.models.MovieGenreResponse;
import com.example.zmdb.models.TopRatedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBApi {

    @GET("movie/top_rated")
    Call<TopRatedResponse> getTopRatedMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("genre/movie/list")
    Call<MovieGenreResponse> getMovieGenres(
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/credits")
    Call<CastResponse> getCast(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );
}
