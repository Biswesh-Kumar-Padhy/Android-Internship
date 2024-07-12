package com.biswesh.a10_retrofit_json_parsing.Model.API

import com.biswesh.a10_retrofit_json_parsing.Model.Data.Product
import com.biswesh.a10_retrofit_json_parsing.Model.Data.User
import retrofit2.http.GET

interface ApiService{
    @GET("products")
    suspend fun getProducts(): List<Product>
    @GET("users")
    suspend fun getUsers(): List<User>
}