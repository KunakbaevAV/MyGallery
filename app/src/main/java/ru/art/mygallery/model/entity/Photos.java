package ru.art.mygallery.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    @Expose
    @SerializedName("totalHits")
    public int totalHits;

    @Expose
    @SerializedName("hits")
    public List<Hit> hits;
}
