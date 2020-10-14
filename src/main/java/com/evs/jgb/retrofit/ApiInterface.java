package com.evs.jgb.retrofit;

import com.evs.jgb.model.ParentReponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("read_mod.php")
    @FormUrlEncoded
    Call<ParentReponse> parentLists(
            @Field("id_language") String id_lang,
            @Field("id_module") String id_module,
            @Field("token") String token,
            @Field("api_id") String api_id);

}
