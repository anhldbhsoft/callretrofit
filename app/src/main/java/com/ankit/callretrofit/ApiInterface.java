package com.ankit.callretrofit;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/todos?fbclid=IwAR3VMGjDqNOIg-qlgdetr3LqfLllY8AzRNhNNGJdzZz1dwPrfKunUkckeqc")
    Call<List<Example>> getAllPost();
}
