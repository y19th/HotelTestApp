package com.example.hoteltestapp.domain.model

import com.google.gson.annotations.SerializedName

data class TourInfo(
    val id: Int = -1,
    val hotelName: String = "",
    val hotelAddress: String = "",
    val hotelRating: Int = 0,
    val ratingDesc: String = "",
    val departure: String = "",
    val arrivalCountry: String = "",
    val dateStart: String = "",
    val dateStop: String = "",
    val nights: Int = 0,
    val roomDesc: String = "",
    val food: String = "",
    val tourPrice: Long = 0L,
    val fuelCharge: Long = 0L,
    val serviceCharge: Long = 0L
)
