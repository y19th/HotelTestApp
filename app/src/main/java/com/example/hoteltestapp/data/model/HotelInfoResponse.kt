package com.example.hoteltestapp.data.model

import com.example.hoteltestapp.domain.model.HotelInfo
import com.google.gson.annotations.SerializedName

data class HotelInfoResponse(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("name") val name: String = "",
    @SerializedName("adress") val adress: String = "",
    @SerializedName("minimal_price") val price: Long = 0L,
    @SerializedName("price_for_it") val priceDesc: String = "",
    @SerializedName("rating") val rating: Int = -1,
    @SerializedName("rating_name") val ratingDesc: String = "",
    @SerializedName("image_urls") val imageUrls: List<String> = emptyList(),
    @SerializedName("about_the_hotel") val hotelDesc: HotelDescriptionResponse = HotelDescriptionResponse()
) {
    fun toHotelInfo() = HotelInfo(id, name, adress, price, priceDesc, rating, ratingDesc, imageUrls,hotelDesc.toHotelDescription())
}
