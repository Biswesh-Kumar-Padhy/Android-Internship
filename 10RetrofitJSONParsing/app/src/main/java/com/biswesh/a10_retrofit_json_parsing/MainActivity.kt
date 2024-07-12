package com.biswesh.a10_retrofit_json_parsing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
//import com.biswesh.a10_retrofit_json_parsing.ViewModels.ProductViewModel
//import com.biswesh.a10_retrofit_json_parsing.Views.ProductScreen
import com.biswesh.a10_retrofit_json_parsing.ViewModels.UserViewModel
import com.biswesh.a10_retrofit_json_parsing.Views.UserScreen
import com.biswesh.a10_retrofit_json_parsing.ui.theme._10RetrofitJSONParsingTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _10RetrofitJSONParsingTheme {
//                val productVM: ProductViewModel by viewModels()
//                ProductScreen(productVM)
                val userVM: UserViewModel by viewModels()
                UserScreen(userVM)
            }
        }
    }
}

