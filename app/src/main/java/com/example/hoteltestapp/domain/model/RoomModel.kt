package com.example.hoteltestapp.domain.model

import com.google.gson.annotations.SerializedName

data class RoomModel(
   val id: Int = -1,
   val name: String = "",
   val price: Long = 0L,
   val pricePer: String = "",
   val pins: List<String> = emptyList(),
   val imageUrls: List<String> = emptyList()
)
