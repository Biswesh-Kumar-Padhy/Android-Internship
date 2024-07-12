package com.biswesh.a11_api_calls

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biswesh.a11_api_calls.ui.theme._11APICallsTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _11APICallsTheme {
                val productVM: ProductViewModel by viewModels()
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { productVM.fetchProducts()
                    }) {
                        Text(text = "Click to make a API")
                    }
                }
            }
        }
    }
}

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val desc: String,
    val category: String,
    val image: String,
    val rating: Rating
)
data class Rating (
    val rate: Double, val count: Int
)
interface ApiService{ //api service
    @GET("products")
    suspend fun getProducts(): List<Product>
}
object RetrofitClient { //retrofit object
        private const val BASE_URL = "https://fakestoreapi.com/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java) //interoperable with java
    }
}
class ProductRepository(private val apiService: ApiService){
    suspend fun getProducts(): List<Product>{
        return apiService.getProducts()
    }
}
class ProductViewModel: ViewModel(){ //fetch product func
    private val _products = MutableLiveData<List<Product>>() //products->live data
    val product: LiveData<List<Product>> get() = _products // actual products which will be exposed to UI

    private val repository = ProductRepository(RetrofitClient.apiService) //repository get the objects of Retrofit Client API service
    fun fetchProducts() {
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