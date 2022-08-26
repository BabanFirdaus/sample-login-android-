package com.nzf.retrofitexample.Models


import com.google.gson.annotations.SerializedName

data class LoginParam(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)