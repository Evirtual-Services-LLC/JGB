package com.evs.jgb.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.evs.jgb.model.ParentReponse;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.viewModels.ParentAuthListner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static Repository repository;

    public static Repository getInstance() {
        if (repository != null) {
            return repository;
        } else {
            repository = new Repository();
            return repository;
        }
    }
    public void executeProductDetails(String id_lang, String id_module,String token,String api_id, final ParentAuthListner authListener) {
        //noinspection NullableProblems
        ApiInterfaceService.getApiService().parentLists(id_lang, id_module, token,api_id)
                .enqueue(new Callback<ParentReponse>() {
                    @Override
                    public void onResponse(Call<ParentReponse> call, Response<ParentReponse> response) {
                        if (response.isSuccessful()) {
                            authListener.onOrderSuccess(new MutableLiveData(response.body()));
//                            loginResponse.setValue(response.body());
                        } else {
                            switch (response.code()) {
                                case 404:
                                    authListener.onFailure("not found");
                                    break;
                                case 500:
                                    authListener.onFailure("server Error");
                                    break;
                                default:
                                    authListener.onFailure("unknown error " + response.code());
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ParentReponse> call, Throwable t) {
                        authListener.onFailure(" network failure :( inform the user and possibly retry");
                        Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");
                    }
                });
    }



}
