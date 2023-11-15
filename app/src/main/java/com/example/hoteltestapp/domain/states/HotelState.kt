package com.example.hoteltestapp.domain.states

import com.example.hoteltestapp.domain.model.HotelInfo


data class HotelState(
    val hotelInfo: HotelInfo = HotelInfo()
)
