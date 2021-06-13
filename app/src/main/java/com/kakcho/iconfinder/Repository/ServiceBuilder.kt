package com.kakcho.iconfinder.Repository

import com.kakcho.iconfinder.Network.RetroFitAPI_RxJava
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    const val BASE_URL = "https://api.iconfinder.com/"
    private val client = OkHttpClient
        .Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RetroFitAPI_RxJava::class.java)

    fun buildService(): RetroFitAPI_RxJava {
        return retrofit
    }
}