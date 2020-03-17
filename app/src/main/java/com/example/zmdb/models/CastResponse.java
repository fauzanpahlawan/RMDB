package com.example.zmdb.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("cast")
    private List<Cast> casts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }
}
