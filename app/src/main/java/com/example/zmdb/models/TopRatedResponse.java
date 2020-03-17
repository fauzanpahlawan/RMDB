package com.example.zmdb.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedResponse {
    @SerializedName("page")
    int page;

    @SerializedName("total_results")
    int total_results;

    @SerializedName("total_pages")
    int total_pages;

    @SerializedName("results")
    private List<Movie> Movies;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getMovies() {
        return Movies;
    }

    public void setMovies(List<Movie> movies) {
        this.Movies = movies;
    }
}
