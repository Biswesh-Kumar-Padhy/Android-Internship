package com.biswesh.a10_retrofit_json_parsing.Model.Reposistory

import com.biswesh.a10_retrofit_json_parsing.Model.API.ApiService
import com.biswesh.a10_retrofit_json_parsing.Model.Data.User

class UserRepository(private val apiService: ApiService){
    suspend fun getUsers(): List<User>{
        return apiService.getUsers()
    }
}