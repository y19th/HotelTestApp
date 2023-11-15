package com.example.hoteltestapp.domain.model

data class HotelDescription(
    val description: String = "",
    val pins: List<String> = emptyList()
)
