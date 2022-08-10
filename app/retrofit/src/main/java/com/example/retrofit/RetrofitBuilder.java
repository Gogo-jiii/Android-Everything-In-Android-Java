package com.example.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static RetrofitBuilder instance = null;
    public String BASE_URL_GET = "https://simplifiedcoding.net/demos/";
    public String BASE_URL_POST = "https://reqres.in";
    private Retrofit.Builder builder;
    private ApiInterface apiInterface;

    private RetrofitBuilder() {
    }

    public static RetrofitBuilder getInstance() {
        if (instance == null) {
            instance = new RetrofitBuilder();
        }
        return instance;
    }

    void build(String url) {
        builder = new Retrofit.Builder();
        builder.baseUrl(url);
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    ApiCalls callApi() {
        return new ApiCalls(apiInterface);
    }
}