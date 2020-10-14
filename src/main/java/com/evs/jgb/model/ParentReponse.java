package com.evs.jgb.model;

import com.evs.jgb.model.parentModel.ParentModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParentReponse {
    @SerializedName("content")
    private List<ParentModel> list_parent;

    public List<ParentModel> getList_parent() {
        return list_parent;
    }
}
