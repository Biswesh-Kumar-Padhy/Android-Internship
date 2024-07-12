package com.biswesh.a10_retrofit_json_parsing.Model.Data

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val desc: String,
    val category: String,
    val image: String,
    val rating: Rating

)
data class Rating(
    val rate: Double, val count: Int
)
