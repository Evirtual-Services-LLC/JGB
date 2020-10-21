package com.evs.jgb.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListResponse<T> {
    @SerializedName("content")
    private ArrayList<T> list;

    public ListResponse(String status, String msg, ArrayList<T> items) {

        this.list = items;
    }
    public ArrayList<T> getList() {
        return list;
    }


}
