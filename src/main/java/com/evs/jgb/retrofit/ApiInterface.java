package com.evs.jgb.retrofit;

import com.evs.jgb.model.ParentReponse;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.ui.activity.ArticleDetailsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("read_mod.php")
    Call<ListResponse<SectionModel>> Modulelist(
            @Field("id_language") String action,
            @Field("id_module") String userId,
            @Field("token") String email,
            @Field("api_id") String fullName
    );

    @FormUrlEncoded
    @POST("read.php")
    Call<ListResponse<ArticleModel>> ArticleModulelist(
            @Field("id_division") String devision_id,
            @Field("id_module") String module_id,
            @Field("id_category") String id_category,
            @Field("id_element") String id_element,
            @Field("search_field") String search_field,
            @Field("search_text") String search_text,
            @Field("id_language") String id_language,
            @Field("columns") String columns,
            @Field("order_by") String order_by,
            @Field("order_direction") String order_direction,
            @Field("date_activation") String date_activation,
            @Field("date_activation_operator") String date_activation_operator,
            @Field("contentModificationDate") String contentModificationDate,
            @Field("top_content") String top_content,
            @Field("token") String token,
            @Field("api_id") String api_id
    );

    @FormUrlEncoded
    @POST("read.php")
    Call<ListResponse<ArticleModel>> legalModulelist(
            @Field("id_division") String devision_id,
            @Field("id_module") String module_id,
            @Field("id_category") String id_category,
            @Field("id_element") String id_element,
            @Field("search_field") String search_field,
            @Field("search_text") String search_text,
            @Field("id_language") String id_language,
            @Field("columns") String columns,
            @Field("order_by") String order_by,
            @Field("order_direction") String order_direction,
            @Field("date_activation") String date_activation,
            @Field("date_activation_operator") String date_activation_operator,
            @Field("contentModificationDate") String contentModificationDate,
            @Field("top_content") String top_content,
            @Field("token") String token,
            @Field("api_id") String api_id
    );

    @FormUrlEncoded
    @POST("read.php")
    Call<ListResponse<ArticleDetailsModel>> ArticleDetailsModule(
            @Field("id_division") String devision_id,
            @Field("id_module") String module_id,
            @Field("id_category") String id_category,
            @Field("id_element") String id_element,
            @Field("search_field") String search_field,
            @Field("search_text") String search_text,
            @Field("id_language") String id_language,
            @Field("columns") String columns,
            @Field("order_by") String order_by,
            @Field("order_direction") String order_direction,
            @Field("date_activation") String date_activation,
            @Field("date_activation_operator") String date_activation_operator,
            @Field("contentModificationDate") String contentModificationDate,
            @Field("top_content") String top_content,
            @Field("token") String token,
            @Field("api_id") String api_id
    );

}
