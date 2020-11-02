package com.evs.jgb.model;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("id_module")
    private String id_module;
    @SerializedName("name_module")
    private String name_module;
    @SerializedName("id_category")
    private String id_category;
    @SerializedName("name_category")
    private String name_category;
    @SerializedName("native_term")
    private String native_term;

    public String getId_module() {
        return id_module;
    }

    public String getName_module() {
        return name_module;
    }

    public String getId_category() {
        return id_category;
    }

    public String getName_category() {
        return name_category;
    }

    public String getNative_term() {
        return native_term;
    }
}
