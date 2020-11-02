package com.evs.jgb.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.evs.jgb.model.ParentReponse;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.viewModels.AuthListener;
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
    public void executeParentingDivision( final AuthListener authListener) {
        //noinspection NullableProblems
        ApiInterfaceService.getClient().create(ApiInterface.class).Modulelist("en-US",
                "m007,m008,m014,m015,m012,m011,m009",
//               R.string.apitoken,
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651")
                .enqueue(new Callback<ListResponse<SectionModel>>() {
                    @Override
                    public void onResponse(Call<ListResponse<SectionModel>> call, Response<ListResponse<SectionModel>> response) {
                        if (!response.isSuccessful()) {
                            authListener.onSuccess(new MutableLiveData(response.body()));
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
                    public void onFailure(Call<ListResponse<SectionModel>> call, Throwable t) {
                        authListener.onFailure(" network failure :( inform the user and possibly retry");
                        Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");
                    }
                });


    }public void executeAgingDivision( final AuthListener authListener) {
        //noinspection NullableProblems
        ApiInterfaceService.getClient().create(ApiInterface.class).Modulelist("en-US",
                "m007,m008,m014,m015,m012,m011,m009",
//               R.string.apitoken,
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651")
                .enqueue(new Callback<ListResponse<SectionModel>>() {
                    @Override
                    public void onResponse(Call<ListResponse<SectionModel>> call, Response<ListResponse<SectionModel>> response) {
                        if (!response.isSuccessful()) {
                            authListener.onSuccess(new MutableLiveData(response.body()));
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
                    public void onFailure(Call<ListResponse<SectionModel>> call, Throwable t) {
                        authListener.onFailure(" network failure :( inform the user and possibly retry");
                        Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");
                    }
                });


    }public void executeBalancingDivision( final AuthListener authListener) {
        //noinspection NullableProblems
        ApiInterfaceService.getClient().create(ApiInterface.class).Modulelist("en-US",
                "m022,m017,m018,m020,m021,m016,m019",
//               R.string.apitoken,
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651")
                .enqueue(new Callback<ListResponse<SectionModel>>() {
                    @Override
                    public void onResponse(Call<ListResponse<SectionModel>> call, Response<ListResponse<SectionModel>> response) {
                        if (!response.isSuccessful()) {
                            authListener.onSuccess(new MutableLiveData(response.body()));
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
                    public void onFailure(Call<ListResponse<SectionModel>> call, Throwable t) {
                        authListener.onFailure(" network failure :( inform the user and possibly retry");
                        Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");
                    }
                });


    }public void executeThrivingParenting( final AuthListener authListener) {
        //noinspection NullableProblems
        ApiInterfaceService.getClient().create(ApiInterface.class).Modulelist("en-US",
                "m029,m028,m033,m025,m027,m024,m026,m031,m032,m030",
//               R.string.apitoken,
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651")
                .enqueue(new Callback<ListResponse<SectionModel>>() {
                    @Override
                    public void onResponse(Call<ListResponse<SectionModel>> call, Response<ListResponse<SectionModel>> response) {
                        if (!response.isSuccessful()) {
                            authListener.onSuccess(new MutableLiveData(response.body()));
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
                    public void onFailure(Call<ListResponse<SectionModel>> call, Throwable t) {
                        authListener.onFailure(" network failure :( inform the user and possibly retry");
                        Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");
                    }
                });


    }public void executeWokringParenting( final AuthListener authListener) {
        //noinspection NullableProblems
        ApiInterfaceService.getClient().create(ApiInterface.class).Modulelist("en-US",
                "m034,m036,m455,m035,m037,m038,m039,m324",
//               R.string.apitoken,
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651")
                .enqueue(new Callback<ListResponse<SectionModel>>() {
                    @Override
                    public void onResponse(Call<ListResponse<SectionModel>> call, Response<ListResponse<SectionModel>> response) {
                        if (!response.isSuccessful()) {
                            authListener.onSuccess(new MutableLiveData(response.body()));
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
                    public void onFailure(Call<ListResponse<SectionModel>> call, Throwable t) {
                        authListener.onFailure(" network failure :( inform the user and possibly retry");
                        Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");
                    }
                });


    }
    public void executeLivingParenting( final AuthListener authListener) {
        //noinspection NullableProblems
        ApiInterfaceService.getClient().create(ApiInterface.class).Modulelist("en-US",
                "m312,m319,m316,m323,m451,m314,m313,m317,m318,m315,m321,m320,m322",
//               R.string.apitoken,
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651")
                .enqueue(new Callback<ListResponse<SectionModel>>() {
                    @Override
                    public void onResponse(Call<ListResponse<SectionModel>> call, Response<ListResponse<SectionModel>> response) {
                        if (!response.isSuccessful()) {
                            authListener.onSuccess(new MutableLiveData(response.body()));
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
                    public void onFailure(Call<ListResponse<SectionModel>> call, Throwable t) {
                        authListener.onFailure(" network failure :( inform the user and possibly retry");
                        Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");
                    }
                });

    }

    public   Call<ListResponse<ArticleModel>> executeUploadProfile(String devision_id,String module_id) {
        return ApiInterfaceService.getApiService().ArticleModulelist(devision_id, module_id, "", "001", "", "",
                "en-us", "id_cr,title,id_element,body_combine", "title", "asc", "", "", "",
                "", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651");
    }

}
