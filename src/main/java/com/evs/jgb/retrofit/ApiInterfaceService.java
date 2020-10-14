package com.evs.jgb.retrofit;

import com.evs.jgb.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInterfaceService {
    private static final String URL = "https://www.powerflexweb.com/api_content/common/";
    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().retryOnConnectionFailure(true).connectTimeout(15, TimeUnit.SECONDS);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS);

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private static OkHttpClient.Builder getHttpClient() {
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpClient.addInterceptor(new LogInterceptor());
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            httpClient.addInterceptor(interceptor);
        }
        return httpClient;
    }

    private static Retrofit retrofit;

    public static ApiInterface getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient().build())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }


}
