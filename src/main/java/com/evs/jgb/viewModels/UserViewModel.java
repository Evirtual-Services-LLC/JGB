package com.evs.jgb.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.evs.jgb.model.parentModel.ParentModel;
import com.evs.jgb.repository.Repository;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ParentModel>> parentList = new MutableLiveData<>();

    public LiveData<ArrayList<ParentModel>> getParentData() {
        return parentList;
    }

    public void hitParentDetails(String id_lan, String id_module,String token,String api_id, ParentAuthListner authListener) {
        authListener.onStarted();
        Repository.getInstance().executeProductDetails(id_lan, id_module,token,api_id, authListener);
    }


}
