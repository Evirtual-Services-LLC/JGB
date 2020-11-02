package com.evs.jgb.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.evs.jgb.model.CategoryModel;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.model.parentModel.ParentModel;
import com.evs.jgb.repository.Repository;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private MutableLiveData<ArrayList<SectionModel>> parentList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ArticleModel>> articleList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<CategoryModel>> categoryList = new MutableLiveData<>();


    public LiveData<ArrayList<SectionModel>> getDivisionData() {
        return parentList;
    }

    public LiveData<ArrayList<ArticleModel>> getArticleData() {
        return articleList;
    }
 public LiveData<ArrayList<CategoryModel>> getCategoryDetails() {
        return categoryList;
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

    public void hitArticleDetails(String devision_id, String module_id,String categry_id, AuthListener authListener) {
        authListener.onStarted();
        ApiInterfaceService.getApiService().ArticleModulelist(devision_id, module_id, categry_id, "001", "", "",
                "en-us", "id_cr,title,id_element,body_combine", "title", "asc", "", "", "",
                "", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651").enqueue(new Callback<ListResponse<ArticleModel>>() {
            @Override
            public void onResponse(Call<ListResponse<ArticleModel>> call, Response<ListResponse<ArticleModel>> response) {
                if (response.isSuccessful()) {
                    authListener.onSuccess(new MutableLiveData(response.body()));
                    Log.e("TAG", response.body().toString());
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
            public void onFailure(Call<ListResponse<ArticleModel>> call, Throwable t) {
                authListener.onFailure(" network failure :( inform the user and possibly retry");
                Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");

            }
        });


    } public void hitCategoryDetails(String module_id, AuthListener authListener) {
        authListener.onStarted();
        ApiInterfaceService.getApiService().categoryList(  "en-us",module_id, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651").enqueue(new Callback<ListResponse<CategoryModel>>() {
            @Override
            public void onResponse(Call<ListResponse<CategoryModel>> call, Response<ListResponse<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    authListener.onSuccess(new MutableLiveData(response.body()));
                    Log.e("TAG", response.body().toString());
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
            public void onFailure(Call<ListResponse<CategoryModel>> call, Throwable t) {
                authListener.onFailure(" network failure :( inform the user and possibly retry");
                Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");

            }
        });


    }

}
