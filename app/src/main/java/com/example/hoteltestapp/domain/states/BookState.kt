package com.example.hoteltestapp.domain.states

import com.example.hoteltestapp.domain.model.TourInfo

data class BookState(
    val tourInfo: TourInfo = TourInfo(),
    val phone: String = "",
    val email: String = "",

    val isEmailError: Boolean = false,
    val isPhoneError: Boolean = false
)