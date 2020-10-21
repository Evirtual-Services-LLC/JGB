package com.evs.jgb.model;
import com.google.gson.annotations.SerializedName;


public class SectionModel {

//       "id_module": "m007",
    //        "name_module": "Adults With Disabilities",
//        "native_term": "Adults With Disabilities",
//        "id_division": "d02",
//        "name_division": "Aging"
    @SerializedName("id_module")
    private String id_module;

    @SerializedName("name_module")
    private String name_module;

    @SerializedName("native_term")
    private String native_term;

    @SerializedName("id_division")
    private String id_division;

    @SerializedName("name_division")
    private String name_division;

    public String getId_module() {
        return id_module;
    }

    public void setId_module(String id_module) {
        this.id_module = id_module;
    }

    public String getName_module() {
        return name_module;
    }

    public void setName_module(String name_module) {
        this.name_module = name_module;
    }

    public String getNative_term() {
        return native_term;
    }

    public void setNative_term(String native_term) {
        this.native_term = native_term;
    }

    public String getId_division() {
        return id_division;
    }

    public void setId_division(String id_division) {
        this.id_division = id_division;
    }

    public String getName_division() {
        return name_division;
    }

    public void setName_division(String name_division) {
        this.name_division = name_division;
    }
}
