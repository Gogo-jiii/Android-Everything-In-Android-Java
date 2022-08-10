package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

interface ApiInterface {

    @GET("marvel")
    Call<List<GetRequestModel>> getHeros();

    @POST("/api/users")
    Call<PostResponseModel> postHeros(@Body PostRequestModel postRequestModel);
}