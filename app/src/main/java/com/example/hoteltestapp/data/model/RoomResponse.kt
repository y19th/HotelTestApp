package com.example.hoteltestapp.data.model

import com.example.hoteltestapp.domain.model.RoomModel
import com.google.gson.annotations.SerializedName

data class RoomResponse(
    @SerializedName("id")val id: Int = -1,
    @SerializedName("name")val name: String = "",
    @SerializedName("price")val price: Long = 0L,
    @SerializedName("price_per") val pricePer: String = "",
    @SerializedName("peculiarities")val pins: List<String> = emptyList(),
    @SerializedName("image_urls")val imageUrls: List<String> = emptyList()
) {
    fun toRoomModel() = RoomModel(id, name, price, pricePer, pins, imageUrls)
}

fun List<RoomResponse>.toRoomList(): List<RoomModel> {
    val list = mutableListOf<RoomModel>()
    for(item in this) list.add(item.toRoomModel())
    return list
}
