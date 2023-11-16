package com.example.hoteltestapp.data.api

import com.example.hoteltestapp.data.model.HotelInfoResponse
import com.example.hoteltestapp.data.model.RoomResponse
import retrofit2.Call
import retrofit2.http.GET

interface HotelApi {
    @GET("v3/d144777c-a67f-4e35-867a-cacc3b827473")
    fun getHotelInfo(): Call<HotelInfoResponse>
}