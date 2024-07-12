package com.biswesh.a10_retrofit_json_parsing.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biswesh.a10_retrofit_json_parsing.Model.API.RetrofitClient
import com.biswesh.a10_retrofit_json_parsing.Model.Data.Product
import com.biswesh.a10_retrofit_json_parsing.Model.Reposistory.ProductRepository
import kotlinx.coroutines.launch


class ProductViewModel : ViewModel() { //fetch product func
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val repository = ProductRepository(RetrofitClient.apiService)

    init {
        fetchProducts()
    }
    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val productList = repository.getProducts()
                _products.postValue(productList)
                println("API DATA CALLED: $productList")
            } catch (e: Exception){
                println("ERROR: $e")
            }
        }
    }
}
