package com.evs.jgb.viewModels;

import androidx.lifecycle.LiveData;

public interface AuthListener<T> {
    void onStarted();

    void onSuccess(LiveData<T> data);

    void onFailure(String message);

}
