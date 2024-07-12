package com.biswesh.a10_retrofit_json_parsing.Model.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient { //retrofit object
    private const val BASE_URL = "https://fakestoreapi.com/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java) //api service
    }
}