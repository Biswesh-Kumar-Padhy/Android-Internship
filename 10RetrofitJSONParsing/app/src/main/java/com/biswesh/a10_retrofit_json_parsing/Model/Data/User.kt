package com.biswesh.a10_retrofit_json_parsing.Model.Data

data class User (
    val id: Int,
    val email: String,
    val username: String,
//    @SerializedName
    val password: String,
    val name: Name,
    val phone: String,
)
data class Name(
    val firstname: String, val lastname: String
)