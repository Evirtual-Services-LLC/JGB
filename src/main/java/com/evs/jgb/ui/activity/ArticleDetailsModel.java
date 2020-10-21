package com.evs.jgb.ui.activity;

import com.google.gson.annotations.SerializedName;

public class ArticleDetailsModel {
    @SerializedName("id_cr")
    private String id_cr;
    @SerializedName("title")
    private String title;
    @SerializedName("body_combine")
    private String body_combine;

    public String getId_cr() {
        return id_cr;
    }

    public String getTitle() {
        return title;
    }

    public String getBody_combine() {
        return body_combine;
    }
}
