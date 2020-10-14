package com.evs.jgb.model.parentModel;

import com.google.gson.annotations.SerializedName;

public class ParentModel {
//    @SerializedName("id_module")
//    private String id_module;
//    @SerializedName("name_module")
//    private String name_module;
//    @SerializedName("native_term")
//    private String native_term;
//    @SerializedName("id_division")
//    private String id_division;
//    @SerializedName("name_division")
//    private String name_division;
//
//    public String getId_module() {
//        return id_module;
//    }
//
//    public String getName_module() {
//        return name_module;
//    }
//
//    public String getNative_term() {
//        return native_term;
//    }
//
//    public String getId_division() {
//        return id_division;
//    }
//
//    public String getName_division() {
//        return name_division;
//    }

    private String id;
    private String name;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ParentModel(String id, String name, String image){
        this.id=id;
        this.name=name;
        this.image=image;
    }
}
