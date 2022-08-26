package com.nzf.retrofitexample.Api

import com.nzf.retrofitexample.Models.LoginParam
import com.nzf.retrofitexample.Models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {


    @POST("login")
    fun userLogin(@Body loginParam: LoginParam):Call<LoginResponse>

}