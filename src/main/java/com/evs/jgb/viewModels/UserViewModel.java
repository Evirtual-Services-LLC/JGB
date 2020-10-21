package com.evs.jgb.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.evs.jgb.model.SectionModel;
import com.evs.jgb.model.parentModel.ParentModel;
import com.evs.jgb.repository.Repository;
import com.evs.jgb.retrofit.ListResponse;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {
    private MutableLiveData<ArrayList<SectionModel>> parentList = new MutableLiveData<>();


    public LiveData<ArrayList<SectionModel>> getDivisionData() {
        return parentList;
    }

    public void hitParentDivision(final AuthListener authListener) {
        authListener.onStarted();
        Repository.getInstance().executeParentingDivision(authListener);
    }

    public void hitAgingDivision(AuthListener authListener) {
        authListener.onStarted();
        Repository.getInstance().executeAgingDivision(authListener);
    }

    public void hitBalancingDivision(AuthListener authListener) {
        authListener.onStarted();
        Repository.getInstance().executeBalancingDivision(authListener);
    }

    public void hitThrivingDivision(AuthListener authListener) {
        authListener.onStarted();
        Repository.getInstance().executeThrivingParenting(authListener);
    }

    public void hitWorkingDivision(AuthListener authListener) {
        authListener.onStarted();
        Repository.getInstance().executeWokringParenting(authListener);
    }

    public void hitLivingDivision(AuthListener authListener) {
        authListener.onStarted();
        Repository.getInstance().executeLivingParenting(authListener);
    }


}
