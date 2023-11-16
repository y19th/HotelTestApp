package com.example.hoteltestapp.data.api

import com.example.hoteltestapp.data.model.TourInfoResponse
import retrofit2.Call
import retrofit2.http.GET

interface BookApi {
    @GET("v3/63866c74-d593-432c-af8e-f279d1a8d2ff")
    fun fetchTourInfo(): Call<TourInfoResponse>
}