package com.example.zmdb.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieGenreResponse {
    @SerializedName("genres")
    List<MovieGenres> genres;

    public List<MovieGenres> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieGenres> genres) {
        this.genres = genres;
    }
}
