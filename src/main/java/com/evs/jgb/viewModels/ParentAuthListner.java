package com.evs.jgb.viewModels;

import androidx.lifecycle.LiveData;

import com.evs.jgb.model.ParentReponse;

public interface ParentAuthListner {
    void onStarted();

    void onOrderSuccess(LiveData<ParentReponse> user);

    void onFailure(String message);
}
