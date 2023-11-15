package com.example.hoteltestapp.data.model

import com.example.hoteltestapp.domain.model.HotelDescription
import com.google.gson.annotations.SerializedName

data class HotelDescriptionResponse(
    @SerializedName("description") val description: String = "",
    @SerializedName("peculiarities") val pins: List<String> = emptyList()
) {
    fun toHotelDescription() = HotelDescription(description, pins)
}