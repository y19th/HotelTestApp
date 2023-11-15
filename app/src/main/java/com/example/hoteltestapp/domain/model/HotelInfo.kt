package com.example.hoteltestapp.domain.model


data class HotelInfo (
    val id: Int = -1,
    val name: String = "",
    val adress: String = "",
    val price: Long = 0L,
    val priceDesc: String = "",
    val rating: Int = -1,
    val ratingDesc: String = "",
    val imageUrls: List<String> = emptyList(),
    val hotelDesc: HotelDescription = HotelDescription()
)