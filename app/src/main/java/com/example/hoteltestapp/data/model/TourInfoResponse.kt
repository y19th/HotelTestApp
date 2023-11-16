package com.example.hoteltestapp.data.model

import com.example.hoteltestapp.domain.model.TourInfo
import com.google.gson.annotations.SerializedName

data class TourInfoResponse(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("hotel_name") val hotelName: String = "",
    @SerializedName("hotel_adress") val hotelAddress: String = "",
    @SerializedName("horating") val hotelRating: Int = 0,
    @SerializedName("rating_name") val ratingDesc: String = "",
    @SerializedName("departure") val departure: String = "",
    @SerializedName("arrival_country") val arrivalCountry: String = "",
    @SerializedName("tour_date_start") val dateStart: String = "",
    @SerializedName("tour_date_stop") val dateStop: String = "",
    @SerializedName("number_of_nights") val nights: Int = 0,
    @SerializedName("room") val roomDesc: String = "",
    @SerializedName("nutrition") val food: String = "",
    @SerializedName("tour_price") val tourPrice: Long = 0L,
    @SerializedName("fuel_charge") val fuelCharge: Long = 0L,
    @SerializedName("service_charge") val serviceCharge: Long = 0L
) {
    fun toTourInfo() = TourInfo(
        id,
        hotelName,
        hotelAddress,
        hotelRating,
        ratingDesc,
        departure,
        arrivalCountry,
        dateStart,
        dateStop,
        nights,
        roomDesc,
        food,
        tourPrice,
        fuelCharge,
        serviceCharge
    )
}
