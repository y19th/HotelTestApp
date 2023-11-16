package com.example.hoteltestapp.data.model

import com.google.gson.annotations.SerializedName

data class RoomsResponse(
    @SerializedName("rooms") val rooms: List<RoomResponse> = emptyList()
)
