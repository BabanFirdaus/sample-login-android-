package com.nzf.retrofitexample.Models
import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("token")
    var token: String
)