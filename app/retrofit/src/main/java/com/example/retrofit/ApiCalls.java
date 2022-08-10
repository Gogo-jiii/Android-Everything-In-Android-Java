package com.example.retrofit;

import java.util.List;

import retrofit2.Call;

public class ApiCalls {

    private ApiInterface apiInterface;

    public ApiCalls(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    Call<List<GetRequestModel>> getHeros(){
        return apiInterface.getHeros();
    }

    Call<PostResponseModel> postHeros(PostRequestModel postRequestModel){
        return apiInterface.postHeros(postRequestModel);
    }
}