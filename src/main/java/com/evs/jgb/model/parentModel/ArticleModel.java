package com.evs.jgb.model.parentModel;

import com.google.gson.annotations.SerializedName;

public class ArticleModel {
    @SerializedName("id_cr")
    private String id_cr;
    @SerializedName("title")
    private String title;
    @SerializedName("id_element")
    private String id_element;
    @SerializedName("body_combine")
    private String body_combine;

    public String getBody_combine() {
        return body_combine;
    }

    public String getId_cr() {
        return id_cr;
    }

    public String getTitle() {
        return title;
    }

    public String getId_element() {
        return id_element;
    }
}
