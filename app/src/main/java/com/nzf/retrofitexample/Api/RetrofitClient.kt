package com.nzf.retrofitexample.Api

import android.util.Base64
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object RetrofitClient {

    //private val AUTH = "Basic" + Base64.encodeToString("belalkhan:123456".toByteArray(),Base64.NO_WRAP)

    private const val BASE_URL = "https://reqres.in/api/"


    var httpClient: OkHttpClient =
        OkHttpClient.Builder() //here we can add Interceptor for dynamical adding headers
            .addNetworkInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request =
                        chain.request().newBuilder().build()
                    return chain.proceed(request)
                }
            }) //here we adding Interceptor for full level logging
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    val instance: Api by lazy {
        val  retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
        retrofit.create(Api::class.java)

    }
}