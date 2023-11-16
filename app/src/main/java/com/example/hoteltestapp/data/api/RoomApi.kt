package com.example.hoteltestapp.data.api

import com.example.hoteltestapp.data.model.RoomsResponse
import retrofit2.Call
import retrofit2.http.GET

interface RoomApi {

    @GET("v3/8b532701-709e-4194-a41c-1a903af00195")
    fun getRooms(): Call<RoomsResponse>
}