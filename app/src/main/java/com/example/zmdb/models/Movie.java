package com.example.zmdb.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("id")
    private int id;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("genre_ids")
    private List<Integer> genre_ids;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
