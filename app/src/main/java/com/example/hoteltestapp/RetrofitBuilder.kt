package com.example.hoteltestapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://run.mocky.io/"

class RetrofitBuilder {


    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> instance(service: Class<T>): T {
        return retrofitBuilder.create(service)
    }

}