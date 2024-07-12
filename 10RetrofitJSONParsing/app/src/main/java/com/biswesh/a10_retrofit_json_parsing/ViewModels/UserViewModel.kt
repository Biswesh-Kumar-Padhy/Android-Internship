package com.biswesh.a10_retrofit_json_parsing.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biswesh.a10_retrofit_json_parsing.Model.API.RetrofitClient
import com.biswesh.a10_retrofit_json_parsing.Model.Data.User
import com.biswesh.a10_retrofit_json_parsing.Model.Reposistory.UserRepository
import kotlinx.coroutines.launch


class UserViewModel : ViewModel() { //fetch product func
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val repository = UserRepository(RetrofitClient.apiService)

    init {
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.getUsers()
                _users.postValue(userList)
                println("API DATA CALLED: $userList")
            } catch (e: Exception){
                println("ERROR: $e")
            }
        }
    }
}
