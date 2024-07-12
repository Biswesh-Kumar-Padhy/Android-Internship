package com.biswesh.a10_retrofit_json_parsing.Model.Reposistory

import com.biswesh.a10_retrofit_json_parsing.Model.API.ApiService
import com.biswesh.a10_retrofit_json_parsing.Model.Data.Product

class ProductRepository(private val apiService: ApiService){
    suspend fun getProducts(): List<Product>{
        return apiService.getProducts()
    }
}