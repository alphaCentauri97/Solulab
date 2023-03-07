package com.example.solulabapp;

import com.example.solulabapp.Model.ApiModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("products")
    Call<ApiModel> getProducts();
}
