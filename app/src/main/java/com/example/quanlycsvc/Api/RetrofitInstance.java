package com.example.quanlycsvc.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static String api = "https://kimhoang.site/api/v1/";
    public static RetrofitInstance retrofitInstance;
    public ApiInterface apiInterface;
    RetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }
    public static RetrofitInstance getRetrofitInstance(){
        if(retrofitInstance == null){
            retrofitInstance = new RetrofitInstance();
        }
        return  retrofitInstance;
    }
}
